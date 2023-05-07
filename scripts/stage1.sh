#!/bin/bash

# Create database and load data
psql -U postgres -c "drop database if exists project;"
psql -U postgres -c "create database project;"
psql -U postgres -d project -f sql/create_table.sql

# Remove the files created previously
rm -rf ./data/avsc
hdfs dfs -test -d /project && hdfs dfs -rm -r /project

# Transfer data from postgres to hdfs
sqoop import-all-tables     -Dmapreduce.job.user.classpath.first=true     --connect jdbc:postgresql://localhost/project     --username postgres     --warehouse-dir /project     --as-avrodatafile     --compression-codec=snappy     --outdir ./data/avsc     --m 1

