#!/bin/bash

# Move AVSC schemas to HDFS
hdfs dfs -mkdir /project/avsc
hdfs dfs -put ./data/avsc/*.avsc /project/avsc
