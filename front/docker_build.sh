#!/usr/bin/env bash

# Если установлен npm нужной версии, то используем npm
if hash npm 2>/dev/null && npm --version | grep [5-6]\.[0-9]\.[0-9] > /dev/null; then
    echo "Build front using NPM " `npm --version`
    npm install
    npm run build
    mv build/*.* build/static
elif hash docker 2>/dev/null; then
    echo "Build front using " `docker --version`
    docker run --rm --name docker-front-build -v "$PWD":/usr/src/app:z -w /usr/src/app node:8-alpine /bin/sh -c "umask 0000 && npm install && npm run build && mv build/*.* build/static"
else
    echo "Build front failed"
    exit 1
fi

exit 0