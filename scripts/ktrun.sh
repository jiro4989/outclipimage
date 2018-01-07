#!/bin/bash

set -eux
srcfile=$1
jarname="${srcfile%.*}.jar"

kotlinc-jvm $srcfile -include-runtime -d $jarname
if [ $? -ne 0 ]; then
  exit 1
fi
echo success compile
java -jar $jarname $2 $3
