#!/bin/bash

# first make sure to go to the current directory

cd /
cd ~/UK_accidents

num_queries=8

# remove any directory named query and its subdirectories
rm -rf queries

# remove any csv files in the output directory
rm -rf output/*.csv

# create a queries folder to save the queries' results
for i in $(seq 1 $num_queries)
do
  mkdir -p "queries/q$i"
done

hive -f hql/stage2.hql --database projectdb

# create  csv file 1
echo "fatal_casualities_number,serious_casualities_number,slight_casualities_number" > output/q1.csv
cat queries/q1/* >> output/q1.csv

# create csv file 2
echo "casualty_class,possibly_fatal_percent,slight_casualities_percent" > output/q2.csv
cat queries/q2/* >> output/q2.csv

# create csv file 3
echo "is_special_accident,severe_casualties_ratio,slight_casualties_ratio" > output/q3.csv
cat queries/q3/* >> output/q3.csv

# create csv file 4
echo "is_special_accident,severe_casualties_ratio,slight_casualties_ratio" > output/q4.csv
cat queries/q4/* >> output/q4.csv

# create csv file 5
echo "speed_limit,severe_casualties_ratio,slight_casualties_ratio" > output/q5.csv
cat queries/q5/* >> output/q5.csv

# create csv file 6
echo "casualty_severity,average_age" > output/q6.csv
cat queries/q6/* >> output/q6.csv


# create csv file 7
echo "district,accidents_number,severe_casualties_ratio,severe_casualties_number" > output/q7.csv
cat queries/q7/* >> output/q7.csv


echo "hour,accidents_number,severe_casualties_ratio,severe_casualties_number" > output/q8.csv
cat queries/q8/* >> output/q8.csv


# delete the queries directories as it is no longer needed
rm -rf queries


