--set datestyle = "SQL, DMY";

-- time to create the table
DROP TABLE IF EXISTS casualty;
DROP TABLE IF EXISTS vehicle;
DROP TABLE IF EXISTS accident;

CREATE TABLE accident (
accident_index varchar(150) PRIMARY KEY,
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
lsoa_of_accident_location varchar(150),
police_force smallint
);


CREATE TABLE IF NOT EXISTS vehicle
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
driver_home_area_type smallint,
driver_imd_decile smallint,
unique (accident_index, vehicle_reference),
PRIMARY KEY (accident_index, vehicle_reference),
FOREIGN KEY (accident_index) REFERENCES accident (accident_index)
);


-- time to create the table for vehicle
CREATE TABLE IF NOT EXISTS casualty
(
accident_index varchar(150) ,
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
cas_home_area_type smallint,
cas_age_band     smallint,
unique (accident_index, vehicle_reference, cas_ref),
primary key (accident_index, vehicle_reference, cas_ref),
--FOREIGN KEY (accident_index) REFERENCES accident (accident_index),
--FOREIGN KEY (vehicle_reference) REFERENCES vehicle (vehicle_reference)
FOREIGN KEY (accident_index, vehicle_reference) REFERENCES vehicle (accident_index, vehicle_reference)
);



\copy accident FROM '/root/UK_accidents/data/accidents.csv'  DELIMITER ',' CSV HEADER;

\copy vehicle FROM '/root/UK_accidents/data/vehicles.csv' DELIMITER ',' CSV HEADER;

\copy casualty FROM '/root/UK_accidents/data/casualties.csv' DELIMITER ',' CSV HEADER;
