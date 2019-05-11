#!/bin/bash

#
# Copyright, Luxon JEAN-PIERRE (2019)
# luxon.jean.pierre@gmail.com
#
# This script is used to package the program on any platform.
# On Windows, it generates a specific executable file.
#
# This script is under CeCILL v2.1 license.
#

JAR_WITH_DEPENDENCY=$1
PACKAGED_JAR=$2
OS_ENV=`uname`

# main
echo "Operating system environment:" $OS_ENV
mv -v $JAR_WITH_DEPENDENCY $PACKAGED_JAR

# Generate a specific package on Windows
if [[ $OS_ENV == *"NT-"* ]]; then
./package-windows.sh
fi