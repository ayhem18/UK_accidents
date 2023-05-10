import streamlit as st
import pandas as pd
import matplotlib.pyplot as plt
import matplotlib.ticker as mtick

# Load all outputs
qs = [pd.read_csv("output/q" + str(i) + ".csv") for i in range(1, 9)]

## First plot
fig = plt.figure(figsize=(10,5))
ax = fig.add_subplot(1,1,1)
ax.bar(qs[0].columns, (100 * qs[0].iloc[0, :]).tolist())
ax.set_title("Fraction of casualty seriousness")
ax.set_xlabel("Casualty type")
ax.set_ylabel("Percentage")
ax.yaxis.set_major_formatter(mtick.PercentFormatter())

st.write(fig)


## Second plot
fig = plt.figure(figsize=(10,5))
ax = fig.add_subplot(1,1,1)

ax.imshow(qs[1].iloc[:, 1:3])
# yticks
cas_classes = pd.read_excel("data/Road-Accident-Safety-Data-Guide.xls", sheet_name="Casualty Class")
cas_classes = cas_classes.iloc[(qs[1].iloc[:, 0]-1).tolist(), 1].astype(str).tolist()
ax.set_yticks(list(range(3)))
ax.set_yticklabels(cas_classes)
# xticks
ax.set_xticks(list(range(2)))
nice_names = {"possibly_fatal_percent": "Possibly Fatal",
	      "slight_casualities_percent": "Slight"}
ax.set_xticklabels(list(map(lambda x: nice_names[x], qs[1].columns[1:])))
ax.set_title("Severity of casualty depending on who is insured")
# Put percentage values on the graph
for y in [0, 1, 2]:
    for x in [0, 1]:
        label = qs[1].iloc[y, x + 1]
	label = int(100 * label)
        ax.text(x, y, label, color='black', ha='center', va='center')
st.write(fig)


# Third plot
