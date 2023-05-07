#!/bin/bash

psql -U postgres -c "create database project;"
psql -U postgres -d project -f sql/create_table.sql

sqoop import-all-tables     -Dmapreduce.job.user.classpath.first=true     --connect jdbc:postgresql://localhost/project     --username postgres     --warehouse-dir /project     --as-avrodatafile     --compression-codec=snappy     --outdir ./data/avsc     --m 1

