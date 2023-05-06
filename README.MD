This repository is a template for the final project of big data course in IU-2023. It contains the following directories:

- `data/` contains the dataset files.
- `models/` contains the Spark ML models.
- `notebooks/` has the Jupyter or Zeppelin notebooks of your project and used just for learning purposes.
- `output/` represents the output directory for storing the results of the project. It can contain `csv` files, text files. images and any other materials you returned as an ouput of the pipeline.
- `scripts/` is a place for storing `.sh` scripts and `.py` scripts of the pipeline.
- `sql/` is a folder for keeping all `.sql` and `.hql` files.

`requirements.txt` lists the Python packages needed for running your Python scripts. Feel free to add more packages when necessary.

`main.sh` is the main script that will run all scripts of the pipeline stages which will execute the full pipeline and store the results in `output/` folder. During checking your project repo, the grader will run only the main script and check the results in `output/` folder.

**Important Note:** You cannot change the content of the script `main.sh` since it will be used for assessment purposes.

**Another Note:** The notebooks in `notebooks/` folder are used only for learning purposes since you need to put all Python scripts of the pipeline in `scripts/` folder. During the assessment, the grader can delete the folder `notebooks/` to check that your pipeline does not depend on its content.



# How to run

1. create a new branch on git
2. run the python script: scripts\preprocess_data.py from the project director: to download the preprocessed version of the cvs files
3. The main issue with the \copy command: the one that copies data from the csv files to the database tables is that it takes ABSOLUTE PATH
AT the moment, I am not sure how to do that (but at the end it is doable under the assumption that everything will be run in sandbox )
4. change the paths in the last copy command to the absolute paths of the data in your machine 
5. log in to the postgres server from the project directory and run the sql script: sql\create_table.sql

# How to run
Follow this guide to install hadoop sandbox: https://hackmd.io/@firasj/BkSQJQ8eh. (We used version 3.0.1)  
Connect to the sandbox: `ssh -p 2222 root@localhost`  
Clone repository:  
`git clone git@github.com:ayhem18/UK_accidents.git && cd UK_accidents`  
Prepare the sandbox and environment:  
`bash scripts/setup_sandbox.sh`  
`pip install -r requirements`  
Download data:  
`python scripts/preprocess_data.py`  
Create database:  
`psql -U postgres "create database bd_project;"`  
Load data:  
`psql -U postgres -d bd_project -f sql/create_table.sql` 
Ensure that HDFS and YARN services are running...  
Use Sqoop to transfer data from PostgreSQL to HDFS:  
```
sqoop import-all-tables\  
  -Dmapreduce.job.user.classpath.first=true\  
  -Dorg.apache.sqoop.splitter.allow_text_splitter=true\  
  --connect jdbc:postgresql://localhost/bd_project\  
  --username postgres\  
  --warehouse-dir /project\  
  --as-avrodatafile\  
  --compression-codec=snappy\  
  --outdir /project/avsc
```  
