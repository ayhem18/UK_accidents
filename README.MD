# Overview
This repository is our final project for the BIG DATA course offered in Innopolis University Spring 2023: UK_accidents. Our main goal is to build a Big Data pipeline starting from extracting the data,
saving it, analyzing it and then introducing Predective analysis. As the repository's name suggests, we are digging deeper into accidents taking place in the United Kingdom. 

See the project's dashboard at `dashboard.pdf`.

# Repository Structure
It consists of the following directories
1. `data/` where the data is saved.
2. `models/` to save the weights and parameters of Pyspark Models
3. `notebooks/` Jupyter notebook for discovering and experimenting with the data.
4. `output/` saves the intermediate and final outputs of the pipeline: Hive Queries as insights, predictions, metrics...
5. `scripts/` the main power engine in the repo: where the driver bash and python code is written
6. `sql/` to save hql and sql scripts

`requirements.txt` The usual Python requirements for any dependencies: We use Python 2.7 as it is compabible with Hadoop 2.6.5 version.

# Different Stages
1. The 1st stage downloads the data, creates the SQL tables and saves it in avro format 
2. The 2nd stage creates Hive Tables and partitions for optimizations purposes, saves a number the results from a number of queries as part of our Exploratory Data Analysis
3. The 3rd stage uses the Hive tables, performs further processing and feature engineering and performs Predective analysis on the processed data
4. The 4th stage uses builds on top of the results of previous stages, by displaying a neat Dashboard with streamlit

# How to run
1. `wget https://archive.cloudera.com/hwx-sandbox/hdp/hdp-2.6.5/HDP_2.6.5_deploy-scripts_180624d542a25.zip`
2. `unzip HDP_2.6.5_deploy-scripts_180624d542a25.zip`
3. `bash docker-deploy-hdp265.sh`
4. Enter the sandbox: `ssh -o ServerAliveInterval=60 -p 2222 root@localhost`
5. Assuming the working directory contains the repository directories, run `main.sh` file: `bash main.sh`

# NB
1. The files in `notebooks/` are not supposed to be run on the virtual machine. The visualization and analytical functionalities there require python.3 version and a large number of additional packages.
2. We assume that the code will be run on a Machine with at least 8GB of ram. In case of unexpected memory issues please refer to the `stage3.sh` script and lower the amount of RAM allocated for Pyspark. This might come with the cost of decrease in performance.  
