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
	accident_index varchar(150),
	loc_east int,
	loc_north int,
	lng float,
	lat float,
	accident_severity int,
	n_veh int,
	n_cas int,
	date_ varchar(150),
	day_of_week int,
	time varchar(150),
	district int,
	highway varchar(150),
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
	did_police_officer_attend_scenery int,
	lsoa_of_accident_location varchar(150)
) partitioned by (police_force int) stored as avro location '/project/accident_part' tblproperties ('AVRO.COMPRESS'='SNAPPY');

INSERT INTO accident_part partition (police_force) SELECT * FROM accident;
--INSERT OVERWRITE TABLE accident_part
--CREATE VIEW view_accident 
--AS 
--INSERT OVERWRITE TABLE accident_part
--SELECT accident_index, loc_east, loc_north, lng, lat, accident_severity, n_veh, n_cas, date_, day_of_week, time , district, highway , road_c1, road_n1, road_type, speed_limit, junc_detail, junc_control, road_c2, road_n2, cross_control, cross_facilities, light, weather, road_surface, special_conds, hazards, area_type, did_police_officer_attend_scenery, police_force
--FROM (
--	SELECT accident_index, loc_east, loc_north, lng, lat, police_force, accident_severity, n_veh, n_cas, date_, day_of_week, time, district, highway, road_c1, road_n1, road_type, speed_limit, junc_detail, junc_control, road_c2, road_n2, cross_control, cross_facilities, light, weather, road_surface, special_conds, hazards, area_type, did_police_officer_attend_scenery
--	FROM accident
--) _alias;

