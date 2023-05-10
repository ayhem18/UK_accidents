#!/bin/bash

# Move AVSC schemas to HDFS
hdfs dfs -test -d /project/avsc && hdfs dfs -rm -r /project/avsc
hdfs dfs -mkdir /project/avsc
hdfs dfs -put ./data/avsc/*.avsc /project/avsc

hive -e "drop database if exists projectdb cascade;"
hive -e "create database projectdb;"
hive --database projectdb -f sql/db.hql
# Create a merged dataset for faster queries

hive --database projectdb -f sql/create_merged_table.hql

# create the insights
hive --database projectdb -f sql/stage2.hql

# save the queries as .csv files in the output folder
bash scripts/save_queries.sh



