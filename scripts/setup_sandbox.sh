## update packages
rpm --import https://repo.mysql.com/RPM-GPG-KEY-mysql-2022
yum update

## vim usage
echo "export TERM=xterm vim" >> /root/.bashrc
source /root/.bashrc

## Correct postgres configuration
sed -i '1s/^/local all all trust\n/' /var/lib/pgsql/9.6/data/pg_hba.conf
sudo systemctl restart postgresql-9.6

## Install Python 3
sudo yum -y groupinstall "Development Tools"
sudo yum -y install openssl-devel bzip2-devel libffi-devel xz-devel

sudo yum -y install wget
wget https://www.python.org/ftp/python/3.8.16/Python-3.8.16.tgz -P ~
tar xvf ~/Python-3.8.16.tgz
cd ~/Python-3.8*/

./configure --enable-optimizations
sudo make altinstall

# Create symlinks for convenience of use
yes | rm /usr/bin/python
ln -s /usr/local/bin/python3.8 /usr/bin/python
mv /usr/bin/pip /usr/bin/pip_bak
ln -s /usr/local/bin/pip3.8 /usr/bin/pip

# Sqoop
wget https://jdbc.postgresql.org/download/postgresql-42.6.0.jar --no-check-certificate -P ~
cp /root/postgresql-42.6.0.jar /usr/hdp/current/sqoop-client/lib/
