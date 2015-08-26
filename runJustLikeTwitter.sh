#!/usr/bin/env bash

buildResults=$(echo `mvn package`)
searchForSuccess=`echo $buildResults | grep -H 'BUILD FAILURE'`

if [[ -z $searchForSuccess ]]; then
    mvn exec:java -Dexec.mainClass="com.codurance.JustLikeTwitter"
else
	echo "Build failure, cannot run JustLikeTwitter."
fi