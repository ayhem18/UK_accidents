#!/bin/bash

# Install xsv
XSV_VERSION=$(curl -s "https://api.github.com/repos/BurntSushi/xsv/releases/latest" | grep -Po '"tag_name": "\K[0-9.]+')
curl -Lo xsv.tar.gz "https://github.com/BurntSushi/xsv/releases/latest/download/xsv-${XSV_VERSION}-x86_64-unknown-linux-musl.tar.gz"
sudo tar xf xsv.tar.gz -C /usr/local/bin
rm xsv.tar.gz

# Put partition column at the end
xsv cat columns <(xsv select 1-5,7- data/accidents.csv) <(xsv select 6 data/accidents.csv) > data/tmp.csv
mv data/tmp.csv data/accidents.csv

xsv cat columns <(xsv select 1-20,22- data/vehicles.csv) <(xsv select 21 data/vehicles.csv) > data/tmp.csv
mv data/tmp.csv data/vehicles.csv

xsv cat columns <(xsv select 1-6,8- data/casualties.csv) <(xsv select 7 data/casualties.csv) > data/tmp.csv
mv data/tmp.csv data/casualties.csv
