
-- not needed in hadoop
-- SELECT 'CREATE DATABASE bd_project'
-- WHERE NOT EXISTS (SELECT FROM pg_database WHERE datname = 'bd_project')\gexec


-- time to create the table
DROP TABLE IF EXISTS casualty;
DROP TABLE IF EXISTS  vehicle ;
DROP TABLE IF EXISTS accident;

CREATE TABLE IF NOT EXISTS accident (
accident_index  int PRIMARY KEY,
lng DOUBLE PRECISION , -- can be null for the moment
lat DOUBLE PRECISION  , -- can be null for the moment
date DATE not null ,
district int ,
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
area_type smallint
);


CREATE TABLE IF NOT EXISTS vehicle
(
accident_index int,
vehicle_reference int,
veh_type int,
towing smallint,
reversed smallint,
object_in smallint,
veh_left smallint,
object_out smallint,
impact smallint,
cc int,
veh_age smallint,
PRIMARY KEY (vehicle_reference),
FOREIGN KEY (accident_index) REFERENCES accident(accident_index)
);


-- time to create the table for vehicle
CREATE TABLE IF NOT EXISTS casualty(
key serial PRIMARY KEY,
accident_index       int ,
vehicle_reference          int,
cas_type         smallint,
cas_sex          smallint,
cas_age          smallint,
cas_age_band     smallint,
cas_y            smallint,
ped_loc          smallint,
FOREIGN KEY (accident_index) REFERENCES accident (accident_index),
FOREIGN KEY (vehicle_reference) REFERENCES vehicle (vehicle_reference)
);


-- the file path might be problematic depending on the platform: linux or Windows

-- add your local machine part before \UK_accidents_project\data\preprocessed_data\accidents_v1.csv, same for other files

\copy accident FROM 'C:\Users\bouab\DEV\UK_accidents_project\data\preprocessed_data\accidents_v1.csv'  DELIMITER ',' CSV HEADER;

\copy vehicle FROM 'C:\Users\bouab\DEV\UK_accidents_project\data\preprocessed_data\vehicles_v1.csv' DELIMITER ',' CSV HEADER;

\copy casualty FROM 'C:\Users\bouab\DEV\UK_accidents_project\data\preprocessed_data\casualties_v1.csv' DELIMITER ',' CSV HEADER;

