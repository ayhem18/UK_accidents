#!/bin/bash

# Move AVSC schemas to HDFS
#hdfs dfs -mkdir /project/avsc
#hdfs dfs -put ./data/avsc/*.avsc /project/avsc

hive -e "drop database if exists projectdb;"
hive -e "create database projectdb;"
hive --database projectdb -f hql/db.hql
