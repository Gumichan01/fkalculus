#!/bin/bash

#
# Copyright, Luxon JEAN-PIERRE (2019)
# luxon.jean.pierre@gmail.com
#
# This script is used to launch the program in a terminal properly
#
# This script is under CeCILL v2.1 license.
#

RLWRAP="rlwrap"
JAVA="java"
JARGS="-jar"
JAR="*-standalone.jar"
FKALCULUS_VERSION=`echo $JAR | cut -d '-' -f2`
FKALCULUS_ARGS=$*
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

isSnapshot() {
    if [[ $JAR == *"SNAPSHOT"* ]]; then
        return 0
    else
        return 1
    fi
}

# Main code
$(checkCommand $RLWRAP)
[ $? -eq 1 ] && warn || wrapper=$RLWRAP
echo -n "FKalculus" $FKALCULUS_VERSION
isSnapshot; [ $? -eq 0 ] && echo "-SNAPSHOT" || echo ""
$wrapper $JAVA $JARGS $JAR $FKALCULUS_ARGS
echo # Just to produce new line
