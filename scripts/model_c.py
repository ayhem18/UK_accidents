from itertools import chain
import numpy as np
import pandas as pd
from pyspark.ml.tuning import CrossValidator, ParamGridBuilder
from pyspark.ml.evaluation import BinaryClassificationEvaluator
from pyspark.ml.classification import RandomForestClassifier, LogisticRegression  # for models
from pyspark.ml.feature import VectorAssembler
from pyspark.sql.types import StringType
from pyspark.sql import DataFrame
import pyspark.sql.functions as F
from pyspark.sql.types import IntegerType, DoubleType
from pyspark.sql import SparkSession



SPARK = SparkSession.builder\
    .appName("BDT Project") .config(
    "spark.sql.catalogImplementation",
    "hive") .config(
        "hive.metastore.uris",
        "thrift://sandbox-hdp.hortonworks.com:9083")\
    .config(
            "spark.sql.avro.compression.codec",
    "snappy") .enableHiveSupport() .getOrCreate()


SPARK.sparkContext.setLogLevel('WARN')


MERGED = SPARK.read.format("avro").table('projectdb.merged')
MERGED.createOrReplaceTempView('merged')
MERGED.printSchema()


# let's first save the dataset characteristics
BEFORE_NUM_COLS = len(MERGED.columns)

NANS = MERGED.select([F.count(F.when(F.isnan(c), c)).alias(c)
                     for c in MERGED.columns])

NANS.toPandas().to_csv("output/nan_values.csv", index=False)

BEFORE_NUM_ROWS = MERGED.count()


# time to get rid of the necessary columns

COLS_TO_REMOVE = (
    'loc_east',
    'loc_north',
    'lng',
    'lat',
    'accident_severity'
    'n_cas',
    'highway',
    'junc_detail',
    'junc_control',
    'cross_facilities',
    'did_police_officer_attend_scenery',
    'lsoa_of_accident_location',
    'police_force',
    'acc_index',
    'vehicle_reference',
    'cas_ref',
    'car_passenger',
    'bus_or_coach_passenger',
    'pedestrian_road_maintenance',
    'cas_home_area_type',
    'vehicle_manoeuvre',
    'junction_location',
    'was_vehicle_left_hand',
    'journey_purpose',
    'sex_of_driver',
    'age_of_driver',
    'age_band',
    'propulsion_code',
    'driver_home_area_type',
    'driver_imd_decile',
)

# drop unncessary columns
MERGED = MERGED.drop(*COLS_TO_REMOVE)
# drop nans
MERGED = MERGED.dropna()

# binarizing the target variable


def binarize_target_variable(x):
    """function to binarize the target variable"""
    return int(x in [1, 2])


# create a udf out of the binarize_target_variable
BINARIZE_TARGET_UDF = F.udf(binarize_target_variable, IntegerType())

MERGED = MERGED.withColumn('cas_severity', BINARIZE_TARGET_UDF(F.col('cas_severity')))

MERGED.groupBy('cas_severity').count().show()

# binarize a number of other features
def special_conditions_features(x):
    """ function to convert the special_condition features to binary features"""
    return int(x not in [-1, 0])


SPECIAL_FEATURES = [
    'hazards',
    'hit_object_off',
    'hit_object_in',
    'hit_object_off',
    'special_conds',
    'skidding'
]

SPECIAL_FEATURES_UDF = F.udf(special_conditions_features, IntegerType())

for sf in SPECIAL_FEATURES:
    MERGED = MERGED.withColumn(sf, SPECIAL_FEATURES_UDF(F.col(sf)))

MERGED.select(*SPECIAL_FEATURES).show(10)

# convert the time and date
def convert_time(x):
    """ extract the hour part of the time given as String object"""	
    return int(x[:2])


CONVERT_TIME_UDF = F.udf(convert_time, IntegerType())

MERGED = MERGED.withColumn('time', CONVERT_TIME_UDF(F.col('time')))

# convert district and the new time using their distributions
S = 'severe_accidents_ratio'


DISTRCIT_DIS = MERGED.groupBy('district').agg(F.avg('cas_severity').alias('district_' + S)).select('district', 'district_' + S)
DISTRCIT_DIS.show(10)


TIME_DIS = MERGED.groupBy('time').agg(F.avg('cas_severity').alias('time_' + S)).select('time', 'time_' + S)
TIME_DIS.show(10)

# let's convert the district and the time


MERGED = MERGED.join(TIME_DIS, TIME_DIS.time == MERGED.time).drop('time')
MERGED = MERGED.join(DISTRCIT_DIS, MERGED.district == DISTRCIT_DIS.district).drop('district')


MERGED.select('time_' + S, 'district_' + S, 'cas_severity').show(10)


def combine_special_features(obj_in, obj_out, veh_left, special_cond, hazards):
    return int(obj_in == 1 or obj_out == 1 or veh_left == 1 or special_cond == 1 or hazards == 1)


COMBINE_UDF = F.udf(combine_special_features, IntegerType())

# let's do that
merged = MERGED.withColumn('special_circumstances', COMBINE_UDF(MERGED.hit_object_in, MERGED.hit_object_off, MERGED.veh_leaving, MERGED.special_conds, MERGED.hazards))
MERGED.drop(*SPECIAL_FEATURES[:-1])

# next step: decide which columns to drop
print(MERGED.columns)

# get rid of some features
COLS_TO_REMOVE = ['accident_index', 'accident_severity', 'n_cas', 'date_']

MERGED = MERGED.drop(*COLS_TO_REMOVE)


MERGED = MERGED.dropna()


# let's save the new dimensions of the dataset

AFTER_NUM_COLS = len(MERGED.columns)

AFTER_NUM_ROWS = MERGED.count()

# let's save both statistics in a csv file


df_stat = {
    'initial_num_columns': [BEFORE_NUM_COLS],
    'final_num_columns': [AFTER_NUM_COLS],
    'initial_num_rows': [BEFORE_NUM_ROWS],
    'final_num_rows': [AFTER_NUM_ROWS]}

DF_STAT = pd.DataFrame(data=df_stat)

# save the stats
DF_STAT.to_csv('output/data_stats.csv', index=False)


# modeling:
LABEL = 'cas_severity'
FEATS = 'features'
P = 'prediction'

TRAIN_DATA, TEST_DATA = MERGED.randomSplit([0.7, 0.3], seed=69)

# transform the data to be ready for modeling
FEATURE_COLUMNS = [c for c in MERGED.columns if c != LABEL]
VEC = VectorAssembler(inputCols=FEATURE_COLUMNS,
                      outputCol=FEATS)

TRAIN = VEC.transform(TRAIN_DATA)
TEST = VEC.transform(TEST_DATA)

# make sure to cast the label to double
TRAIN = TRAIN.withColumn(LABEL, F.col(LABEL).cast(DoubleType()))
TEST = TEST.withColumn(LABEL, F.col(LABEL).cast(DoubleType()))


# our 2 classifier\ will be Random Forests and Logistic Regression

def kfold_validation_one_hyper(vanilla_model, params, train_data, k=3):
    # build the evaluator

    evaluator = BinaryClassificationEvaluator(labelCol=LABEL)
    # build the paramsGrid object
    p = ParamGridBuilder().addGrid(*params).build()
    crossval = CrossValidator(estimator=vanilla_model,
                              estimatorParamMaps=p,
                              evaluator=evaluator,
                              numFolds=k, seed=69)  # use 3+ folds in practice
    return crossval.fit(train_data)


def kfold_validation(vanilla_model, list_params, train_data, k=3):
    # iterate through each of the list of params and perform cross validation
    # on that list
    model = vanilla_model
    for params in list_params:
        model = kfold_validation_one_hyper(model, params, train_data, k)
        # extract the estimator from the resul

        model = model.getEstimator()
    return model


def evaluate(model, train_data, test_data):

    # fit the model first
    model = model.fit(train_data)

    predictions = model.transform(test_data)

    evaluator = BinaryClassificationEvaluator(labelCol=LABEL)

    # first calculate apr
    auc = evaluator.evaluate(predictions)

    # change the metric to 'auc'
    evaluator.setMetricName('areaUnderPR')
    apr = evaluator.evaluate(predictions)

    # Area under precision-recall curve
    print("Area under PR = %s" % apr)

    # Area under ROC curve
    print("Area under ROC = %s" % auc)

    return predictions, auc, apr



# out 2 classifiers will be Random Forests and Logistic Regression

RFC = RandomForestClassifier(
    labelCol=LABEL, featuresCol=FEATS, predictionCol=P, seed=69)

LR = LogisticRegression(
    labelCol=LABEL,
    featuresCol=FEATS,
    maxIter=100,
    regParam=0.3,
    elasticNetParam=0.8,
    predictionCol=P, 
    weightCol='weight')  


LR_PARAMS = [[LR.regParam, [10.0 ** i for i in range(-3, 1)]], [LR.elasticNetParam, list(np.linspace(0, 1, 6))]]

RF_PARAMS = [[RFC.maxDepth, list(range(4, 8))], [RFC.minInstancesPerNode, [10, 100, 1000]]]

# let's prepare the weights for Logistic Regression
y_collect = MERGED.select(LABEL).groupBy(LABEL).count().collect()
print(y_collect)
unique_y = [x[LABEL] for x in y_collect]
total_y = sum([x["count"] for x in y_collect])
print(total_y)
unique_y_count = len(y_collect)
bin_count = [x["count"] for x in y_collect]
class_weights_spark = {i: ii for i, ii in zip(unique_y, float(total_y) / (unique_y_count * np.array(bin_count)))}
print(class_weights_spark) 

mapping_expr = F.create_map([F.lit(x) for x in chain(*class_weights_spark.items())])

TRAIN_LR = TRAIN.withColumn("weight", mapping_expr.getItem(F.col(LABEL)))

# make sure to cache the train and test dataframes
TRAIN  = TRAIN.cache()
TEST = TEST.cache()
TRAIN_LR = TRAIN_LR.cache()

BEST_LR = kfold_validation(LR, LR_PARAMS, TRAIN_LR)
BEST_RFC = kfold_validation(RFC, RF_PARAMS, TRAIN)


# evaluate both models on the test data using the predefined metrics
LR_PREDS, LR_AUC, LR_APR = evaluate(BEST_LR, TRAIN_LR, TEST)
RF_PREDS, RF_AUC, RF_APR = evaluate(BEST_RFC, TRAIN, TEST)

print("RANDOM FOREST'S METRICS: AUC " + str(RF_AUC) + " Area Under PR " + str(RF_APR))
print("LOGISTIC REGRESSION'S METRICS: AUC " + str(LR_AUC) + " Area Under PR" + str(LR_APR))


# time to save the predictions

RF_PREDS.toPandas().to_csv("output/random_forests_predictions.csv")
LR_PREDS.toPandas().to_csv("output/logistic_regression_predictions.csv")

# save the results
RES_DICT = {
    'area_under_curve': [
        LR_AUC, RF_AUC], 'area_under_pr_curve': [
            LR_APR, RF_APR]}
RES = pd.DataFrame(
    data=RES_DICT,
    index=[
        'logistic_regression',
        'random_forest'])
RES.to_csv('output/metrics.csv', index=True)
