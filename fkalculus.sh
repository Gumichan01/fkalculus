#!/bin/bash

#
# Copyright, Luxon JEAN-PIERRE (2019)
# luxon.jean.pierre@gmail.com
#
# This script is used to launch the program is a terminal properly
#
# This script is under CeCILL v2.1 license.
#

RLWRAP="rlwrap"
JAVA="java"
JARGS="-jar"
JAR="*-standalone.jar"
FKALCULUS_VERSION=`echo $JAR | cut -d '-' -f2`
wrapper=

checkCommand()
{
    command=$1
    location=`whereis $command | cut -d ':' -f2`
    [ -z "$location" ] && return 1 || return 0
}

warn()
{
    echo "warning: no wrapper command enabled"
    echo "note: Please install $RLWRAP in order to get command history and line editing"
}

# Main code
$(checkCommand $RLWRAP)
[ $? -eq 1 ] && warn || wrapper=$RLWRAP
echo "FKalculus" $FKALCULUS_VERSION
$wrapper $JAVA $JARGS $JAR
echo # Just to produce new line
