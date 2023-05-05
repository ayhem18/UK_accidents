# define the links for the data
ACCIDENTS_LINK =  'https://drive.google.com/file/d/10cx3efdBpO6vdpv5kLnnJMv4O6OON8OR/view?usp=share_link'

CASUALTIES_LINK = 'https://drive.google.com/file/d/13BDX1dJzcT7y-E-7nNaCRTDfTIdQ1-e2/view?usp=share_link'

VEHICLES_LINK = 'https://drive.google.com/file/d/1JMbQPn4HBapqGv8nDjTf1m5aF-9-v9G0/view?usp=share_link'


import gdown

def get_gdrive_data_url(sharable_link: str, des_dir: str):
    """Download the content of a file through a google drive sharable link
    Args:
        sharable_link (str): the link
        des_dir (str): the file's saving location 
    """

    # first grab the id from the sharable link
    id = sharable_link.split("/")[5]
    # use the id to build the download url
    download_url = f'https://drive.google.com/uc?id={id}'
    # use gdown API to download the file's content
    gdown.download(download_url, des_dir, quiet=False)


import os
from pathlib import Path
HOME = os.getcwd()

# first check if the HOME path represents the path to the entire directory
folders = {'scripts', 'notebooks', 'models', 'sql'}

if not folders.issubset(set(os.listdir(HOME))): # make sure we are in the current directory 
    HOME = Path(HOME).parent

# define the path to the folder where all the data will be saved
DATA_FOLDER_PATH = os.path.join(HOME, 'data')

# create the folder in question
os.makedirs(DATA_FOLDER_PATH, exist_ok=True)

CA_PATH = os.path.join(DATA_FOLDER_PATH, 'casualities.csv')
ACC_PATH = os.path.join(DATA_FOLDER_PATH, 'accidents.csv')
VE_PATH = os.path.join(DATA_FOLDER_PATH, 'vehicles.csv')

# download each of the files

# each line of code in this block is computationally expensive: 
# let's avoid running it if possible
if not os.path.exists(CA_PATH):
    get_gdrive_data_url(CASUALTIES_LINK, CA_PATH)

if not os.path.exists(ACC_PATH):
    get_gdrive_data_url(ACCIDENTS_LINK, ACC_PATH)

if not os.path.exists(VE_PATH):
    get_gdrive_data_url(VEHICLES_LINK, VE_PATH)

# now time for preprocessing
import pandas as pd

# let's load the data as needed
CA = pd.read_csv(CA_PATH)
ACC = pd.read_csv(ACC_PATH)
VE = pd.read_csv(VE_PATH)


acc_drop_cols = ['Location_Easting_OSGR', 'Location_Northing_OSGR','Police_Force', 'Did_Police_Officer_Attend_Scene_of_Accident', 'LSOA_of_Accident_Location', 'Number_of_Casualties']
ACC.drop(columns=acc_drop_cols, inplace=True)


# let's provide better names (and mainly shorter) for the featrures
new_acc_cols = {'Longitude': 'lng', 'Latitude': 'lat', 'Accident_Severity': 'y', 'Number_of_Vehicles': 'n_vehs', 'Local_Authority_(District)': 'district',
                'Local_Authority_(Highway)': 'highway', '1st_Road_Class': 'road_c1', '1st_Road_Number': 'road_n1', 'Road_Type':'road_type'
                , 'Light_Conditions': 'light', 'Weather_Conditions': 'weather', 'Road_Surface_Conditions': 'road_surface', 'Urban_or_Rural_Area': 'area_type'
                , "Junction_Detail": "junc_detail", "Junction_Control": "junc_control", "2nd_Road_Class": "road_c2", "2nd_Road_Number": "road_n2", 
                "Pedestrian_Crossing-Human_Control": "cross_control", "Pedestrian_Crossing-Physical_Facilities": "cross_facilities", 
                "Special_Conditions_at_Site": "special_conds", "Carriageway_Hazards": "hazards"}

from df_operations import new_col_names, to_columns
ACC = new_col_names(new_acc_cols, ACC)
# make sure to convert them to lowercase
ACC = to_columns(ACC, lambda c: c.lower().strip()) 


# the day of the week does not seem to affect the seriousness of the casuality
ACC.drop(columns=['day_of_week', 'y', 'n_vehs'], inplace=True)
# let's make sure to convert the date to a datetime4
# first convert all dates to the same format

from  datetime import datetime
from dateutil.parser import parse

def uniform_date_format(date_text:str):
    try:
        return datetime.strptime(date_text, '%Y-%m-%d')
    except ValueError:
        pass
    # first parse the date as it is
    dt = parse(date_text)
    return dt.strftime('%d/%m/%Y')

# first make sure to unify the date's format
ACC['date'] = ACC['date'].apply(uniform_date_format)
# convert all the strings to date time objects
ACC['date'] = pd.to_datetime(ACC['date'], format='%d/%m/%Y')



# save the resulting accidents table
processed_data_dir = os.path.join(DATA_FOLDER_PATH, 'preprocessed_data')
os.makedirs(processed_data_dir, exist_ok=True)
ACC.to_csv(os.path.join(processed_data_dir, 'accidents_v1.csv'), index=False) 


# preprocess the Vehicles data

VE = to_columns(VE, lambda x: x.lower().strip())
COLUMNS_TO_REMOVE = ['vehicle_manoeuvre', 'vehicle_location-restricted_lane', 'junction_location', 'was_vehicle_left_hand_drive?', 
                     'journey_purpose_of_driver', 'sex_of_driver', 'age_of_driver','age_band_of_driver', 'propulsion_code',
                     'driver_imd_decile', 'driver_home_area_type']

VE.drop(columns=COLUMNS_TO_REMOVE, inplace=True) 

NEW_VEH_NAMES = {"accident_index": "acc_index", "vehicle_reference": "veh_ref", "vehicle_type": "veh_type", "towing_and_articulation": "towing",
                 "skidding_and_overturning": "reversed", "hit_object_in_carriageway": "object_in", "vehicle_leaving_carriageway": "veh_left", 
                 "hit_object_off_carriageway": "object_out", '1st_point_of_impact':"impact","engine_capacity_(cc)": "cc", "age_of_vehicle": "veh_age"}

VE = new_col_names(NEW_VEH_NAMES, VE) 

def fix_veh_ref(row):
    row['veh_ref'] = f"{row['acc_index']}_{row['veh_ref']}"
    return row

VE = VE.apply(fix_veh_ref, axis=1)

VE.to_csv(os.path.join(processed_data_dir, 'vehicles_v1.csv'), index=False)



# proprocess the casualties data

CA.columns = [c.lower() for c in CA.columns]
# drop the last 6 columns
CA.drop(columns=list(CA.columns)[-6:] + ['casualty_reference'], inplace=True)

# let's fix the naming here
CA_NEW_NAMES = {"accident_index": "acc_index", "vehicle_reference": "veh_ref", 'casualty_class': "cas_type","sex_of_casualty": "cas_sex", 
                'age_of_casualty': "cas_age", 'age_band_of_casualty': "cas_age_band", 'casualty_severity': "cas_y", 'pedestrian_location': "ped_loc"}

CA = new_col_names(CA_NEW_NAMES, CA)

# the same modification applied on the vehicle file should be applied to that of casualties
CA = CA.apply(fix_veh_ref, axis=1)
# as it does not seem possible to make a primary key for each casualty out of the provided data
# we can create a simple serieal key by resetting the index
CA.reset_index(inplace=True)

CA.to_csv(os.path.join(processed_data_dir, 'casualties_v1.csv'), index=False) 

# assert all the veh_ref are different: as they are primary keys for the vehicle table in postgres
assert all(VE['veh_ref'].value_counts() == 1)
# assert the references in the casualties dataset is a subset of the vehicles dataset: database constraints
assert set(CA['veh_ref']).issubset(set(VE['veh_ref']))