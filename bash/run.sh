#!/bin/sh

M2_HOME=/opt/apache-maven-3.2.2
export M2_HOME
mvn=${M2_HOME}/bin/mvn
cd ../
${mvn} clean install
cd target
java -jar compress.jar
