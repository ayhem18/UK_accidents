#!/bin/bash

# Move AVSC schemas to HDFS
hdfs dfs -put ./data/avsc/*.avsc /project/avsc
