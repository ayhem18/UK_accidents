"""
This module is a script for generation of the dashboard with all results and observations
made in this project.
"""

import streamlit as ST
import pandas as pd
import matplotlib.pyplot as plt
import matplotlib.ticker as mtick

# Load all outputs
QS = [pd.read_csv("output/q" + str(i) + ".csv") for i in range(1, 9)]

ST.write("# Big Data Project  \n _Car Accident Severety_$^{Prediction}$ :sunglasses:  \n", "Made by: Ayhem Bouabid & Sinii Viacheslav \n", "*Year*: **2023**")

ST.write("## Data Characteristics \n", "The dataset does not contain any NaN values:\n")
nan_values = pd.read_csv("output/nan_values.csv")
ST.write(nan_values)

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
ST.markdown(ACCIDENTS_TEXT)

VEHICLES_TEXT = """
	* The data provides a detailed description of the vehicle
	* The fields most likely should be combined into a fewer but more general representations
	* Certain fields might be dropped:
	  * Vehicle Location: can be deduced to a certain extent by the type of the road / location the accident took place
	  * Vehicle Maneouver is to be dropped
	  * There are two Hit Object features that can be merged into one
	  * The IMD level as well as the home area of the driver do not seem to have direct relation with the seriousness of the casuality
	any information about the driver can be found in the casuality table, so it should be dropped from the vehicle table
	"""

ST.markdown(VEHICLES_TEXT)

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
TITLE = "Severity of casualty depending on who is insured"
# plot
cond_plot(QS[1], Y_NAMES, X_NAMES, TITLE)


# Third plot
NICE_NAMES = {"severe_casualties_ratio": "Severe",
              "slight_casualties_ratio": "Slight"}
X_NAMES = [NICE_NAMES[x] for x in QS[2].columns[1:]]
TITLE = "Casualty severity by speciality"
cond_plot(QS[2], ["Non-special", "Special"],
          X_NAMES, TITLE, are_percentages=True)


# Forth plot
cond_plot(QS[3], ["Non-special", "Special"],
          X_NAMES, TITLE, are_percentages=True)

# Fifth plot
FIG = plt.figure(figsize=(10, 5))
AX = FIG.add_subplot(1, 1, 1)

AX.plot(QS[4].iloc[:, 0], QS[4].iloc[:, 1], label="Severe")
#AX.plot(QS[4].iloc[:, 0], QS[4].iloc[:, 2], label="Slight")
AX.set_xlabel("Speed Limit")
AX.set_ylabel("Percentage")
AX.set_title("Casualty severness depending on speed limit")
AX.yaxis.set_major_formatter(mtick.PercentFormatter())
AX.legend(loc="best")

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

ST.write(FIG)


# Seventh plot

FIG = plt.figure(figsize=(10, 5))
AX = FIG.add_subplot(1, 1, 1)

AX.scatter(QS[6].iloc[:, 1], 100 * QS[6].iloc[:, 2])
AX.set_xlabel("Number of accidents")
AX.set_ylabel("Percentage of severe")
AX.set_title("Severeness of accidents in each district")
AX.yaxis.set_major_formatter(mtick.PercentFormatter())


ST.write(FIG)
