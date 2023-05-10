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
	accident_severity smallint,
	n_veh smallint,
	n_cas smallint,
	date_ varchar(150),
	day_of_week smallint,
	time varchar(150),
	district smallint,
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
	did_police_officer_attend_scenery smallint,
	lsoa_of_accident_location varchar(150)
) partitioned by (police_force int) stored as avro location '/project/accident_part' tblproperties ('AVRO.COMPRESS'='SNAPPY');

INSERT INTO accident_part partition (police_force) SELECT * FROM accident LIMIT 1000;
--INSERT OVERWRITE TABLE accident_part
--CREATE VIEW view_accident 
--AS 
--INSERT OVERWRITE TABLE accident_part
--SELECT accident_index, loc_east, loc_north, lng, lat, accident_severity, n_veh, n_cas, date_, day_of_week, time , district, highway , road_c1, road_n1, road_type, speed_limit, junc_detail, junc_control, road_c2, road_n2, cross_control, cross_facilities, light, weather, road_surface, special_conds, hazards, area_type, did_police_officer_attend_scenery, police_force
--FROM (
--	SELECT accident_index, loc_east, loc_north, lng, lat, police_force, accident_severity, n_veh, n_cas, date_, day_of_week, time, district, highway, road_c1, road_n1, road_type, speed_limit, junc_detail, junc_control, road_c2, road_n2, cross_control, cross_facilities, light, weather, road_surface, special_conds, hazards, area_type, did_police_officer_attend_scenery
--	FROM accident
--) _alias;

CREATE external TABLE vehicle_part
(
accident_index varchar(150),
vehicle_reference smallint,
veh_type smallint,
towing smallint,
vehicle_manoeuvre smallint,
vehicle_location smallint,
junction_location smallint,
skidding smallint,
hit_object_in smallint,
veh_leaving smallint,
hit_object_off smallint,
first_point_of_impact smallint,
was_vehicle_left_hand smallint,
journey_purpose smallint,
sex_of_driver smallint,
age_of_driver smallint,
age_band smallint,
engine_capacity int,
propulsion_code smallint,
age_of_vehicle int,
driver_home_area_type smallint
) partitioned by (driver_imd_decile smallint) stored as avro location '/project/vehicle_part' tblproperties ('avro.compress'='snappy');


INSERT INTO vehicle_part partition (driver_imd_decile) SELECT * FROM vehicle LIMIT 1000;


CREATE external TABLE casualty_part
(
accident_index varchar(150),
vehicle_reference int,
cas_ref int,
cas_class int,
cas_sex          smallint,
cas_age          smallint,
cas_severity smallint,
pedestrian_locaction          smallint,
pedestrian_movement smallint,
car_passenger smallint,
bus_or_coach_passenger smallint,
pedestrian_road_maintenance smallint,
cas_type smallint,
cas_home_area_type smallint
) partitioned by (cas_age_band smallint) stored as avro location '/project/casualty_part' tblproperties ('avro.compress'='snappy');


INSERT INTO casualty_part partition (cas_age_band) SELECT * FROM casualty LIMIT 1000;
