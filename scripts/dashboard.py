import streamlit as st
import pandas as pd
import matplotlib.pyplot as plt
import matplotlib.ticker as mtick

# Load all outputs
qs = [pd.read_csv("output/q" + str(i) + ".csv") for i in range(1, 9)]

## First plot
fig = plt.figure(figsize=(10,5))
ax = fig.add_subplot(1,1,1)
vals = 100 * qs[0].iloc[0, :]
vals = vals.astype(int)
	
ax.bar(qs[0].columns, vals.tolist())
ax.set_title("Fraction of casualty seriousness")
ax.set_xlabel("Casualty type")
ax.set_ylabel("Percentage")
ax.yaxis.set_major_formatter(mtick.PercentFormatter())

st.write(fig)


## Second plot

def cond_plot(ds, y_names, x_names, title, are_percentages=False):
	fig = plt.figure(figsize=(10,5))
	ax = fig.add_subplot(1,1,1)

	ax.imshow(ds.iloc[:, 1:])
	# yticks
	ax.set_yticks(list(range(ds.shape[0])))
	ax.set_yticklabels(y_names)
	# xticks
	ax.set_xticks(list(range(ds.shape[1] - 1)))
	ax.set_xticklabels(x_names)
	ax.set_title(title)
	# Put percentage values on the graph
	for y in range(ds.shape[0]):
	    for x in range(ds.shape[1] - 1):
		label = ds.iloc[y, x + 1]
		if not are_percentages:
			label *= 100
		label = int(label)
		ax.text(x, y, label, color='black', ha='center', va='center')
	st.write(fig)

# ynames
cas_classes = pd.read_excel("data/Road-Accident-Safety-Data-Guide.xls", sheet_name="Casualty Class")
ynames = cas_classes.iloc[(qs[1].iloc[:, 0]-1).tolist(), 1].astype(str).tolist()

# xnames
nice_names = {"possibly_fatal_percent": "Possibly Fatal",
	      "slight_casualities_percent": "Slight"}
xnames = list(map(lambda x: nice_names[x], qs[1].columns[1:]))
title = "Severity of casualty depending on who is insured"
# plot
cond_plot(qs[1], ynames, xnames, title)


# Third plot
nice_names = {"severe_casualties_ratio": "Severe",
	      "slight_casualties_ratio": "Slight"}
xnames = list(map(lambda x: nice_names[x], qs[2].columns[1:]))
title = "Casualty severity by speciality"
cond_plot(qs[2], ["Non-special", "Special"], xnames, are_percentages=True, title)


# Forth plot
cond_plot(qs[3], ["Non-special", "Special"], xnames, are_percentages=True, title)

# Fifth plot
fig = plt.figure(figsize=(10,5))
ax = fig.add_subplot(1,1,1)

ax.plot(qs[4].iloc[:, 0], qs[4].iloc[:, 1], label="Severe")
#ax.plot(qs[4].iloc[:, 0], qs[4].iloc[:, 2], label="Slight")
ax.set_xlabel("Speed Limit")
ax.set_ylabel("Percentage")
ax.set_title("Casualty severness depending on speed limit")
ax.yaxis.set_major_formatter(mtick.PercentFormatter())
ax.legend(loc="best")

st.write(fig)


# Sixth plot
fig = plt.figure(figsize=(10,5))
ax = fig.add_subplot(1,1,1)
vals = qs[5].iloc[:, 1]
vals = vals.astype(int)
	
ax.bar(["Slight", "Severe"], vals.tolist())
ax.set_title("Age of casualties depending on severity")
ax.set_xlabel("Casualty type")
ax.set_ylabel("Average Age")

st.write(fig)


# Seventh plot

fig = plt.figure(figsize=(10,5))
ax = fig.add_subplot(1,1,1)

ax.scatter(qs[6].iloc[:, 1], 100 * qs[6].iloc[:, 2])
ax.set_xlabel("Number of accidents")
ax.set_ylabel("Percentage of severe")
ax.set_title("Severeness of accidents in each district")
ax.yaxis.set_major_formatter(mtick.PercentFormatter())


st.write(fig)
