SET mapreduce.map.output.compress = true;
SET mapreduce.map.output.compress.codec = org.apache.hadoop.io.compress.SnappyCodec;

-- drop tables
DROP TABLE IF EXISTS accident;
DROP TABLE IF EXISTS casualty;
DROP TABLE IF EXISTS vehicle;

-- create tables
CREATE EXTERNAL TABLE accident  STORED AS AVRO LOCATION '/project/accident' TBLPROPERTIES ('avro.schema.url'='/project/avsc/accident.avsc');
CREATE EXTERNAL TABLE casualty STORED AS AVRO LOCATION '/project/casualty' TBLPROPERTIES ('avro.schema.url'='/project/avsc/casualty.avsc');
CREATE EXTERNAL TABLE vehicle  STORED AS AVRO LOCATION '/project/vehicle' TBLPROPERTIES ('avro.schema.url'='/project/avsc/vehicle.avsc');


