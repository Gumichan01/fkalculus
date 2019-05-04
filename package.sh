#!/bin/bash

#
# Copyright, Luxon JEAN-PIERRE (2019)
# luxon.jean.pierre@gmail.com
#
# This script is used to launch the program is a terminal properly
#
# This script is under CeCILL v2.1 license.
#

JAR_WITH_DEPENDENCY=$1
PACKAGED_JAR=$2
OS_ENV=`uname`

# main
echo "Operating system environment:" $OS_ENV
mv -v $JAR_WITH_DEPENDENCY $PACKAGED_JAR

# TODO Generate a specific package on Windows
