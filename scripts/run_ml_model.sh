# let's get to the correct directory


# wget https://bootstrap.pypa.io/pip/2.7/get-pip.py
# python get-pip.py
# pip install pandas==0.24.2


echo "spark.driver.memory 12g" >> /etc/spark2/conf/spark-defaults.conf

cd /root/UK_accidents

spark-submit --jars /usr/hdp/current/hive-client/lib/hive-metastore-1.2.1000.2.6.5.0-292.jar,/usr/hdp/current/hive-client/lib/hive-exec-1.2.1000.2.6.5.0-292.jar --packages org.apache.spark:spark-avro_2.12:3.0.3 scripts/model.py



