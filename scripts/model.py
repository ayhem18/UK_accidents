
import numpy as np
from pyspark.sql import SparkSession
from pyspark.sql.types import  *

# import logging
# logging.basicConfig(level="WARNING")


spark = SparkSession.builder\
        .appName("BDT Project")\
        .config("spark.sql.catalogImplementation","hive")\
        .config("hive.metastore.uris", "thrift://sandbox-hdp.hortonworks.com:9083")\
        .config("spark.sql.avro.compression.codec", "snappy")\
        .enableHiveSupport()\
        .getOrCreate()


# spark.sparkContext.setLogLevel("WARNING")

merged = spark.read.format("avro").table('projectdb.merged')
merged.createOrReplaceTempView('merged')
merged.printSchema()




from pyspark.sql.types import StructType, StructField, FloatType, ArrayType, IntegerType, StringType, BooleanType, DateType, MapType
import pyspark.sql.functions as F
import re
import pandas as pd
from pyspark import keyword_only
from pyspark.ml import Transformer
from pyspark.ml.param.shared import Param, Params, TypeConverters
from pyspark.ml.util import DefaultParamsReadable, DefaultParamsWritable
from pyspark.sql import DataFrame
from pyspark.sql.types import StringType
import time
from pyspark.ml.regression import LinearRegression
import math








# time to get rid of the necessary columns

cols_to_remove = ('loc_east', 'loc_north', 'lng', 'lat', 'accident_severity'
                  'n_cas', 'highway', 'junc_detail', 'junc_control', 'cross_facilities', 
                'did_police_officer_attend_scenery', 'lsoa_of_accident_location', 'police_force',
                
                'acc_index', 'vehicle_reference', 'cas_ref', 'car_passenger', 
                'bus_or_coach_passenger', 'pedestrian_road_maintenance', 'cas_home_area_type', 
                
                'vehicle_manoeuvre', 'junction_location', 'was_vehicle_left_hand', 'journey_purpose',
                'sex_of_driver', 'age_of_driver', 'age_band', 'propulsion_code', 'driver_home_area_type', 
                'driver_imd_decile', 
                )

merged = merged.drop(*cols_to_remove)


# drop nans

merged = merged.dropna()

# time for some extra processing

# binarizing the target variable

def binarize_target_variable(x):
    return int(x in [1, 2]) 


# create a udf out of the binarize_target_variable
binarize_target_udf = F.udf(binarize_target_variable, IntegerType())

merged = merged.withColumn('cas_severity', binarize_target_udf(F.col('cas_severity')))
merged.groupBy('cas_severity').count().show()



# binarize a number of other features

def special_conditions_features(x):
    return int(x not in [-1, 0])

special_features = ['hazards', 'hit_object_off', 'hit_object_in', 'hit_object_off', 'special_conds', 'skidding']

special_features_udf = F.udf(special_conditions_features, IntegerType())

for sf in special_features:
  merged = merged.withColumn(sf, special_features_udf(F.col(sf)))

# let's make sure the features are binarized as needed

merged.select(*special_features).show(10)



## convert the time

# convert the time and date
def convert_time(x):
  return int(x[:2])

convert_time_udf = F.udf(convert_time, IntegerType())

merged = merged.withColumn('time', convert_time_udf(F.col('time')))

# convert district and the new time using their distributions 
S = 'severe_accidents_ratio'


district_dis = merged.groupBy('district').agg(F.avg('cas_severity').alias('district_'+S)).select('district', 'district_' + S)
district_dis.show(10)

time_dis = merged.groupBy('time').agg(F.avg('cas_severity').alias('time_'+S)).select('time', 'time_' + S)
time_dis.show(10)

# let's convert the district and the time 


merged = merged.join(time_dis, time_dis.time == merged.time).drop('time')
merged = merged.join(district_dis, merged.district == district_dis.district).drop('district')



merged.select('time_' + S, 'district_'+S, 'cas_severity').show(10)


def combine_special_features(obj_in, obj_out, veh_left, special_cond, hazards):
	return int(obj_in == 1 or obj_out == 1 or veh_left == 1 or special_cond == 1 or hazards == 1)
combine_udf = F.udf(combine_special_features, IntegerType())


# let's do that
merged = merged.withColumn('special_circumstances', combine_udf(merged.hit_object_in, merged.hit_object_off, merged.veh_leaving, merged.special_conds, merged.hazards))
merged.drop(*special_features[:-1])

# next step: decide which columns to drop 
print(merged.columns) 





# get rid of some features
cols_to_remove = ['accident_index', 'accident_severity', 'n_cas','date_',]

merged = merged.drop(*cols_to_remove)
# drop any possible nan values
merged = merged.dropna()



# let's descrease the size of the entire dataset to avoid out of memory errors
print("ORIGINAL SIZE " + str(merged.count()))



# merged = merged.rdd.zipWithIndex().map(lambda x: tuple(x)).filter(lambda x: x[0] % 5 == 0).map(lambda x: tuple(x[1:])).toDF()

# merged = merged.rdd.zipWithIndex().filter(lambda x: x[1] % 5 == 0).map(lambda x: x[0]).toDF()

print("REDUCED SIZE " + str(merged.count()))



# modeling:
LABEL = 'cas_severity'
FEATS = 'features'
P = 'prediction'

train_data, test_data = merged.randomSplit([0.7, 0.3], seed=69)

# transform the data to be ready for modeling
from pyspark.ml.feature import VectorAssembler


feature_columns = [c for c in merged.columns if c != LABEL]
vectorAssembler = VectorAssembler(inputCols = feature_columns, 
                                  outputCol = FEATS)
                                  # handleInvalid = 'skip')

train = vectorAssembler.transform(train_data)
test = vectorAssembler.transform(test_data)

# make sure to cast the label to double
train = train.withColumn(LABEL, F.col(LABEL).cast(DoubleType()))
test = test.withColumn(LABEL, F.col(LABEL).cast(DoubleType()))


# out 2 classifiers will be Random Forests and Logistic Regression

from pyspark.mllib.evaluation import BinaryClassificationMetrics # for metrics 
from pyspark.ml.classification import RandomForestClassifier, LogisticRegression # for models

rfc = RandomForestClassifier(labelCol=LABEL, featuresCol=FEATS, predictionCol=P)
lr = LogisticRegression(labelCol=LABEL, featuresCol=FEATS, maxIter=100, regParam=0.3, elasticNetParam=0.8, predictionCol=P)# Fit the model
# lr = LogisticRegression(labelCol=LABEL, maxIter=100, regParam=1, elasticNetParam=0.5)


from pyspark.ml.classification import LogisticRegression
from pyspark.ml.evaluation import BinaryClassificationEvaluator
from pyspark.ml.tuning import CrossValidator, ParamGridBuilder



def kfold_validation_one_hyper(vanilla_model, params, train_data, k=3):
    # build the evaluator

    evaluator = BinaryClassificationEvaluator(labelCol=LABEL)
    # build the paramsGrid object
    p = ParamGridBuilder().addGrid(*params).build()
    crossval = CrossValidator(estimator=vanilla_model,
                          estimatorParamMaps=p,
                          evaluator=evaluator,
                          numFolds=k)  # use 3+ folds in practice
    return crossval.fit(train_data)


def kfold_validation(vanilla_model, list_params, train_data, k=3):
    # iterate through each of the list of params and perform cross validation on that list 
    model = vanilla_model
    for params in list_params:
        model = kfold_validation_one_hyper(model, params, train_data, k)
    return model



def evaluate(model, test_data):
    # first let's predicts
    # model.predictionCol = 'prediction'
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






lr_params = [[lr.regParam, [10 ** i for i in range(-3, 1)]], [lr.elasticNetParam, list(np.linspace(0, 1, 6))]]


rf_params = [[rfc.maxDepth, list(range(4, 8))], [rfc.minInstancesPerNode, [10, 100, 1000]]]




#lr_params_grid = ParamGridBuilder().addGrid(lr.regParam, [0.01, 0.001]).build()

# lr_params_grid = ParamGridBuilder().addGrid(lr.regParam, [10 ** i for i in range(-3, 1)]).addGrid(lr.elasticNetParam, np.linspace(0, 1, 4)).build()
# rf_params_grid = ParamGridBuilder().addGrid(rfc.numTrees, [10, 20, 30]).addGrid(rfc.maxDepth, list(range(4, 7))).build()


# make sure to cache the train and test dataframes
train = train.cache()
test = test.cache()

best_lr = kfold_validation(lr, lr_params, train)
best_rfc = kfold_validation(rfc, rf_params, train) 


# lr = lr.fit(train)

# evaluate both models on the test data using the predefined metrics
# rf_auc, rf_apr = evaluate(best_rfc, test)

lr_preds, lr_auc, lr_apr = evaluate(best_lr, test)
rf_preds, rf_auc, rf_apr = evaluate(best_rfc, test)

# print("RANDOM FOREST'S METRICS: AUC " + str(rf_auc)+ " Area Under PR" + str(rf_apr))
print("LOGISTIC REGRESSION'S METRICS: AUC " + str(lr_auc) + " Area Under PR" + str(lr_apr))









