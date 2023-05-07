SET mapreduce.map.output.compress = true;
SET mapreduce.map.output.compress.codec = org.apache.hadoop.io.compress.SnappyCodec;

-- dynamic partitioning
SET hive.exec.dynamic.partition = true;
SET hive.exec.dynamic.partition.mode = nonstrict;

-- drop tables
DROP TABLE IF EXISTS accident;
DROP TABLE IF EXISTS casualty;
DROP TABLE IF EXISTS vehicle;

-- create tables
CREATE EXTERNAL TABLE accident  STORED AS AVRO LOCATION '/project/accident' TBLPROPERTIES ('avro.schema.url'='/project/avsc/accident.avsc');
CREATE EXTERNAL TABLE casualty STORED AS AVRO LOCATION '/project/casualty' TBLPROPERTIES ('avro.schema.url'='/project/avsc/casualty.avsc');
CREATE EXTERNAL TABLE vehicle  STORED AS AVRO LOCATION '/project/vehicle' TBLPROPERTIES ('avro.schema.url'='/project/avsc/vehicle.avsc');

-- drop partitioned tables
DROP TABLE IF EXISTS accident_part;
DROP TABLE IF EXISTS casualty_part;
DROP TABLE IF EXISTS vehicle_part;

create external table accident_part (
	accident_index  varchar PRIMARY KEY,
	loc_east int,
	loc_north int,
	lng DOUBLE PRECISION , -- can be null for the moment
	lat DOUBLE PRECISION  , -- can be null for the moment
	police_force int,
	accident_severity int,
	n_veh int,
	n_cas int,
	date DATE not null ,
	day_of_week int,
	time varchar,
	district int ,
	highway varchar,
	road_c1 smallint,
	road_n1 smallint,
	road_type smallint,
	speed_limit  smallint,
	junc_detail  smallint,
	junc_control  smallint,
	road_c2 smallint,
	road_n2 smallint,
	cross_control smallint,
	cross_facilities  smallint,
	light smallint,
	weather  smallint,
	road_surface smallint,
	special_conds smallint,
	hazards smallint,
	area_type smallint,
	did_police_officer_attend_scenery varchar,
	lsoa_of_accident_location varchar
);
