## update packages
#rpm --import https://repo.mysql.com/RPM-GPG-KEY-mysql-2022
#yum -y update

## vim usage
echo "export TERM=xterm vim" >> /root/.bashrc
## permissions to modify hdfs
echo "export HADOOP_USER_NAME=hdfs" >> /root/.bashrc
source /root/.bashrc

## Correct postgres configuration
sed -i '1s/^/local all all trust\n/' /var/lib/pgsql/data/pg_hba.conf
sed -i '1s/^/host all all 0.0.0.0\/0 trust\n/' /var/lib/pgsql/data/pg_hba.conf

sudo systemctl restart postgresql

## Install Python 3
#sudo yum -y groupinstall "Development Tools"
#sudo yum -y install openssl-devel bzip2-devel libffi-devel xz-devel

#sudo yum -y install wget
#wget https://www.python.org/ftp/python/3.8.16/Python-3.8.16.tgz -P ~
#tar xvf ~/Python-3.8.16.tgz -C ~
#cd ~/Python-3.8.16/

#./configure --enable-optimizations
#sudo make altinstall
#cd -

# Create symlinks for convenience of use
#yes | rm /usr/bin/python
#ln -s /usr/local/bin/python3.8 /usr/bin/python3
#mv /usr/bin/pip3 /usr/bin/pip_bak
#ln -s /usr/local/bin/pip3.8 /usr/bin/pip3

# Sqoop
wget https://jdbc.postgresql.org/download/postgresql-42.6.0.jar --no-check-certificate -P ~
cp /root/postgresql-42.6.0.jar /usr/hdp/current/sqoop-client/lib/

# Install Python requirements
#pip3 install -r requirements.txt
