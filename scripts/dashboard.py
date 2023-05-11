"""
This module is a script for generation of the dashboard with all results and observations
made in this project.
"""

import re
import streamlit as ST
import pandas as pd
import numpy as np
import matplotlib.pyplot as plt
import matplotlib.ticker as mtick

# Load all outputs
QS = [pd.read_csv("output/q" + str(i) + ".csv") for i in range(1, 9)]

ST.write("# Big Data Project  \n _Car Accident Severety_$^{Prediction}$" \
         + " :sunglasses:  \n", "Made by: Ayhem Bouabid & Sinii Viacheslav  \n",
         "*Year*: **2023**")

ST.write("## Data Characteristics \n", "The dataset does not contain any NaN values:\n")
NAN_VALUES = pd.read_csv("output/nan_values.csv")
ST.write(NAN_VALUES)

ST.write("## Data Preprocessing \n")
ACCIDENTS_TEXT = """
	### Accidents
	Going through the explanations of the fields, it is possible to reduce the number of features as some of them do not introduce any additional value for our predictive task.
	The following will be dropped because these values represent the location of the accident with respect to a local geospatial system. This information duplicates another two columns which are longitude and latitude.

	1. Location Easting OSGR (Null if not known)
	2. Location Northing OSGR (Null if not known)

	Other columns:

	3. The police attendance - it happens after the accident happens
	4. Longitude and Latitude might be dropped as the data is already clustered into different districts 
	5. Accident Severity: this value is practically equivalent to the target: the severity of casualities 
	6. Police: The police's intervention takes place generally after the accident. Such intervention could not possible affect the accident's severity and the casualities' seriousness

	Additional remarks:

	* The most seemingly important features are:
	  * 1st /2nd Road Class / if it reflects quality
	  * weather conditions / Light Conditions
	  * Pedestrian Crossing Human control: we don't expect many accidents in conjuctions controlled by police officer: HOWEVER IT MIGHT HAVE SOME OVERLAPPING WITH CONJUNCTION CONTROL
	  * Urban / Rural area: Rural area are more likely to have more fatal accidents: more serious casualities
	  * SPEED LIMIT
	"""
ST.write(ACCIDENTS_TEXT)

VEHICLES_TEXT = """
	### Vehicles
	* The data provides a detailed description of the vehicle
	* Each unique vehicle is defined by the (accident_index, vehicle_reference) tuple. 'vehicle_reference' column indicates vehicle w.r.t. the accident.
	* The fields most likely should be combined into a fewer but more general representations
	* Certain fields might be dropped:
	  * Vehicle Location: can be deduced to a certain extent by the type of the road / location the accident took place
	  * Vehicle Maneouver is to be dropped
	  * There are two Hit Object features that can be merged into one
	  * The IMD level as well as the home area of the driver do not seem to have direct relation with the seriousness of the casuality
	any information about the driver can be found in the casuality table, so it should be dropped from the vehicle table
	"""

ST.write(VEHICLES_TEXT)

DATA_STATS = pd.read_csv("output/data_stats.csv")
ST.write("### Results of Data Preparation  \n " \
	+ "Before the cleaning there were " + str(DATA_STATS.iloc[0, 2]) \
	+ " columns. We performed extensive cleaning and were left with " + str(DATA_STATS.iloc[0, 0]) \
	+ " columns.")
ST.write("### Target  \n Predict casualty severity")

ST.write("## EDA")

# First plot
FIG = plt.figure(figsize=(10, 5))
AX = FIG.add_subplot(1, 1, 1)
VALS = 100 * QS[0].iloc[0, :]
VALS = VALS.astype(int)

AX.bar(QS[0].columns, VALS.tolist())
AX.set_title("Fraction of casualty seriousness")
AX.set_xlabel("Casualty type")
AX.set_ylabel("Percentage")
AX.yaxis.set_major_formatter(mtick.PercentFormatter())

ST.write("""
	First let's see how many casualties of each types are present in the dataset. There is a serious class imbalance in our problem with slight casualties dominating the data.  \nWe decided to merge 'Fatal' and 'Serious' casualties into a single category and to transform our task into binary classification. $0$ will stand for Slight casualties and $1$ for non-Slight.
	""")
ST.write(FIG)


# Second plot

def cond_plot(dataset, y_names_, x_names_, title_, are_percentages=False):
    """
    A function to make a conditional plot.
    y-axis is the condition variable.
    x-axis are other variables on which we make an observation.

    :param dataset: dataset with data for plotting.
    :param y_names_: names of groups inside the conditional variable.
    :param x_names_: names of variables on which we make an observation.
    :param title_: title of the plot.
    :param are_percentages: a boolean variable which indicates whether data is
	percentages in range [0,100] or fractions in range [0, 1].
    """

    inner_fig = plt.figure(figsize=(10, 5))
    inner_ax = inner_fig.add_subplot(1, 1, 1)

    inner_ax.imshow(dataset.iloc[:, 1:])
    # yticks
    inner_ax.set_yticks(list(range(dataset.shape[0])))
    inner_ax.set_yticklabels(y_names_)
    # xticks
    inner_ax.set_xticks(list(range(dataset.shape[1] - 1)))
    inner_ax.set_xticklabels(x_names_)
    inner_ax.set_title(title_)
    # Put percentage values on the graph
    for y_coord in range(dataset.shape[0]):
        for x_coord in range(dataset.shape[1] - 1):
            label = dataset.iloc[y_coord, x_coord + 1]
            if not are_percentages:
                label *= 100
            label = int(label)
            inner_ax.text(x_coord, y_coord, label, color='black', ha='center', va='center')
    ST.write(inner_fig)


# y names
CAS_CLASSES = pd.read_excel(
    "data/Road-Accident-Safety-Data-Guide.xls",
    sheet_name="Casualty Class")
Y_NAMES = CAS_CLASSES\
	.iloc[(QS[1].iloc[:, 0] - 1).tolist(), 1]\
	.astype(str).tolist()

# x names
NICE_NAMES = {"possibly_fatal_percent": "Possibly Fatal",
              "slight_casualities_percent": "Slight"}
X_NAMES = [NICE_NAMES[x] for x in QS[1].columns[1:]]
TITLE = "Severity of casualty depending on who is injured"
# plot

ST.write("Pedestrians have a much higher risk of a serious injury " \
            + "compared to people located in the car.")
cond_plot(QS[1], Y_NAMES, X_NAMES, TITLE)


# Third plot
NICE_NAMES = {"severe_casualties_ratio": "Severe",
              "slight_casualties_ratio": "Slight"}
X_NAMES = [NICE_NAMES[x] for x in QS[2].columns[1:]]
TITLE = "Casualty severity by speciality"

ST.write("We can see that accidents with special accidents"\
	 + "circumstances such as the vehicle leaving "\
	 + "the road, having the car not in its natural "\
	 + "position / direction are 2 likely to have "\
	 + "severe casualties. The first visualization "\
	 + "corresponds to the entire dataset.")
cond_plot(QS[2], ["Non-special", "Special"],
          X_NAMES, TITLE, are_percentages=True)


# Forth plot
ST.write("We can see that this observation is supported further in the 2nd visualization as it demonstrates the influence of such special circumstances on pedestrians' casualties")
cond_plot(QS[3], ["Non-special", "Special"],
          X_NAMES, TITLE, are_percentages=True)

# Fifth plot
FIG = plt.figure(figsize=(10, 5))
AX = FIG.add_subplot(1, 1, 1)

AX.plot(QS[4].iloc[:, 0], QS[4].iloc[:, 1], label="Severe")
#AX.plot(QS[4].iloc[:, 0], QS[4].iloc[:, 2], label="Slight")
AX.set_xlabel("Speed Limit")
AX.set_ylabel("Percentage")
AX.set_title("Casualty severity depending on speed limit.")
AX.yaxis.set_major_formatter(mtick.PercentFormatter())
AX.legend(loc="best")


ST.write("Here we study the severity of the casualty depending on "\
            + "the speed limit of the road where the accident happened. "\
            + "As the speed limit increases, the vehicles become deadlier "\
            + "leading to more serious injuries. Local regulations should be "\
	    + "more cautious about setting the speed limit on some roads.  \n"\
	    + "Thus speed limit math be a very informative feature to predict the casualty severity.")
ST.write(FIG)


# Sixth plot
FIG = plt.figure(figsize=(10, 5))
AX = FIG.add_subplot(1, 1, 1)
VALS = QS[5].iloc[:, 1]
VALS = VALS.astype(int)

AX.bar(["Slight", "Severe"], VALS.tolist())
AX.set_title("Age of casualties depending on severity")
AX.set_xlabel("Casualty type")
AX.set_ylabel("Average Age")

ST.write("Now we want to see whether there is a connection between casualty severity "\
	    + "and the age of a person. The graph below indicates that on average older people "\
	    + "have a higher risk of fatal or serious injury compared to youngsters. "\
	    + "This information may be crucial when medical aid decide who has to have "\
	    + "a higher priority for help.  \n The age feature may be useful for predictions.")
ST.write(FIG)


# Seventh plot

FIG = plt.figure(figsize=(10, 5))
AX = FIG.add_subplot(1, 1, 1)

AX.scatter(QS[6].iloc[:, 1], 100 * QS[6].iloc[:, 2])
AX.set_xlabel("Number of accidents")
AX.set_ylabel("Percentage of severe")
AX.set_title("Severeness of accidents in each district")
AX.yaxis.set_major_formatter(mtick.PercentFormatter())


ST.write("For each district in the country we extracted two values: "\
	    + "total number of accidents that took place there and the "\
	    + "percentage of severe accidents. The graph below shows that "\
	    + "districts with less accidents are more dangerous. Small number "\
	    + "of accidents may be due to a low number of people living there, "\
	    + "i.e. districts in the countryside or suburbs. Thus, they are probably "\
	    + "being less developed and have worse conditions leading to more severe "\
	    + "accidents taking place.")
ST.write(FIG)


## Predictions
PREDICTIONS_TEXT = "## Predictions.  \n We trained two models - "\
	+ "Logistic Regression and Random Forest. The table below "\
	+ "shows the probabilities of class 1 (Non-Slight injury) "\
	+ "in the opinion of each model along with real "\
	+ "values of the samples. We took 10 random samples "\
	+ "for illustrative purposes."
ST.write(PREDICTIONS_TEXT)

LR_PREDICTIONS = pd.read_csv('output/logistic_regression_predictions.csv')
RF_PREDICTIONS = pd.read_csv('output/random_forests_predictions.csv')

# Take 10 random samples
INDICES = np.random.randint(0, LR_PREDICTIONS.shape[0], size=(10,))

LR_PREDS_10 = LR_PREDICTIONS.iloc[INDICES, -2]\
	.apply(lambda x: float(re.findall(r'\d+.\d+', x)[1])).to_numpy().reshape(-1, 1)
RF_PREDS_10 = RF_PREDICTIONS.iloc[INDICES, -2]\
	.apply(lambda x: float(re.findall(r'\d+.\d+', x)[1])).to_numpy().reshape(-1, 1)
REAL = LR_PREDICTIONS.iloc[INDICES, 29]\
	.to_numpy().reshape(-1, 1)

DF = np.hstack((LR_PREDS_10, RF_PREDS_10, REAL))
DF = pd.DataFrame(DF, columns=['Logistic Regression', 'Random Forest', 'Real'])
ST.write(DF)


## Metrics
METRICS_TEXT = \
	"## Evaluation.  \n"\
	+ "Here are the Area Under ROC and Area Under PR metrics "\
	+ "which we used to evaluate our models. As expected, "\
	+ "a more complex and expressive model - Random Forest - "\
	+ "have higher metrics values, i.e. performs better."
METRICS = pd.read_csv('output/metrics.csv')
METRICS.columns = [u'Model', u'area_under_curve', u'area_under_pr_curve']
ST.write(METRICS_TEXT)
ST.write(METRICS)

ST.write("### Confusion Matrix. Logistic Regression\n")
CONF_MATRIX = pd.read_csv("output/logistic_regression_CM.csv")
cond_plot(CONF_MATRIX, ['TP', 'TN'], ['FP', 'FN'], "Confusion Matrix")

ST.write("### Confusion Matrix. Random Forest\n")
CONF_MATRIX = pd.read_csv("output/random_forest_CM.csv")
cond_plot(CONF_MATRIX, ['TP', 'TN'], ['FP', 'FN'], "Confusion Matrix")
