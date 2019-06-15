#!/bin/bash

#
# Copyright, Luxon JEAN-PIERRE (2019)
# luxon.jean.pierre@gmail.com
#
# This script is used to package the program on Windows.
#
# This script is under CeCILL v2.1 license.
#

git clone https://github.com/Gumichan01/launch4j.git && \
cd launch4j && \
git checkout Release_launch4j-3_12 && \
ant switch-to-maven && \
mvn clean package && \
cp bin/bin-win32/*.exe bin/ && \
cd .. && \
java -jar launch4j/launch4j-3.12.jar fkalculus.xml && \
rm -rf launch4j/
