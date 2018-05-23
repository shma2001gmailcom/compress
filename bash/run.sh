#!/bin/sh
M2_HOME='/home/misha/sym-misha/workspace/apache-maven-3.3.9'
export M2_HOME
mvn=${M2_HOME}/bin/mvn
cd ../
rm -r target
${mvn} clean install -X
cd target
java -jar compress.jar
