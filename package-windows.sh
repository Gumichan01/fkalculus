#!/bin/bash

#
# Copyright, Luxon JEAN-PIERRE (2019)
# luxon.jean.pierre@gmail.com
#
# This script is used to package the program on Windows.
#
# This script is under CeCILL v2.1 license.
#

git clone https://github.com/Gumichan01/launch4j.git
cd launch4j
ant switch-to-maven
mvn clean package
cd ..
rm -rf launch4j/
