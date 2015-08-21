#!/usr/bin/env bash

buildResults=$(echo `mvn package`)

if [ buildResults ]; then
    mvn exec:java -Dexec.mainClass="interfaces.JustLikeTwitter"
else
    echo "Build failure."
fi