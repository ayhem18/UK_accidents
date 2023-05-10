import streamlit as st
import pandas as pd
import matplotlib.pyplot as plt
import matplotlib.ticker as mtick

# Load all outputs
qs = [pd.read_csv("output/q" + str(i) + ".csv") for i in range(1, 9)]

# First plot
fig = plt.figure(figsize=(10,5))
ax = fig.add_subplot(1,1,1)
ax.bar(qs[0].columns, (100 * qs[0].iloc[0, :]).tolist())
ax.set_title("Fraction of casualty seriousness")
ax.set_xlabel("Casualty type")
ax.set_ylabel("Percentage")
ax.yaxis.set_major_formatter(mtick.PercentFormatter())

st.write(fig)

