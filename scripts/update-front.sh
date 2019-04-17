#!/usr/bin/env bash

cd front

rm -rf ../src/main/resources/static/

./docker_build.sh

mv build/static/ ../src/main/resources/
