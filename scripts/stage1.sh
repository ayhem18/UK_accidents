#!/bin/bash

#python3 scripts/preprocess_data.py
#psql -U postgres "create database bd_project;"
#psql -U postgres -d bd_project -f sql/create_table.sql

#sqoop import-all-tables -Dmapreduce.job.user.classpath.first=true --connect jdbc:postgresql://localhost/bd_project --username postgres --warehouse-dir /project --as-avrodatafile --compression-codec=snappy --outdir /project/avsc

sqoop import-all-tables \
    --connect jdbc:postgresql://localhost/bd_project \
    --username postgres \
    --warehouse-dir /project

