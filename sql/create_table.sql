
set datestyle = "SQL, DMY";

-- time to create the table
DROP TABLE IF EXISTS casualty;
DROP TABLE IF EXISTS  vehicle ;
DROP TABLE IF EXISTS accident;

CREATE TABLE accident (
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


CREATE TABLE IF NOT EXISTS vehicle
(
accident_index varchar,
vehicle_reference int,
veh_type int,
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
driver_imd_decile smallint,
driver_home_area_type smallint,
unique (accident_index, vehicle_reference),
PRIMARY KEY (accident_index, vehicle_reference)
--FOREIGN KEY (accident_index) REFERENCES accident(accident_index)
);


-- time to create the table for vehicle
CREATE TABLE IF NOT EXISTS casualty
(
accident_index varchar ,
vehicle_reference int,
cas_ref int,
cas_class int,
cas_sex          smallint,
cas_age          smallint,
cas_age_band     smallint,
cas_severity smallint,
pedestrian_locaction          smallint,
pedestrian_movement smallint,
car_passenger smallint,
bus_or_coach_passenger smallint,
pedestrian_road_maintenance smallint,
cas_type smallint,
cas_home_area_type smallint,
unique (accident_index, vehicle_reference, cas_ref),
primary key (accident_index, vehicle_reference, cas_ref)
--FOREIGN KEY (accident_index) REFERENCES accident (accident_index),
--FOREIGN KEY (vehicle_reference) REFERENCES vehicle (vehicle_reference)
);



\copy accident FROM '/root/UK_accidents/data/accidents.csv'  DELIMITER ',' CSV HEADER;

\copy vehicle FROM '/root/UK_accidents/data/vehicles.csv' DELIMITER ',' CSV HEADER;

\copy casualty FROM '/root/UK_accidents/data/casualties.csv' DELIMITER ',' CSV HEADER;
