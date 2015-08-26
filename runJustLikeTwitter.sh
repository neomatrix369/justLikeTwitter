#!/usr/bin/env bash

buildResults=$(echo `mvn package`)
searchForSuccess=`echo $buildResults | grep -H 'BUILD FAILURE'`

if [[ -z $searchForSuccess ]]; then
    mvn exec:java -Dexec.mainClass="com.codurance.userinterfaces.JustLikeTwitter"
else
	echo "Build failure."	
fi