#!/usr/bin/env bash

RED='\033[0;31m'
CYAN='\033[0;36m'
NC='\033[0m'
CONTAINER="flight_route"
CWD=$(pwd)

# build
./gradlew build -x test

if [ $? -eq 1 ]; then
    printf "${RED}Failed to build application${NC}\n"
    exit
fi

cp ${CWD}/build/libs/flight_route-*.jar ${CWD}/build/libs/flight_route.jar &> /dev/null

RUNNING=$(docker inspect --format="{{ .State.Running }}" $CONTAINER 2> /dev/null)

if [ "$RUNNING" == "true" ]; then
    echo "Container is running, restarting"
    docker restart ${CONTAINER} &> /dev/null
    docker attach ${CONTAINER}
else
    echo "Container is not running, starting"
    START=$(docker start ${CONTAINER} &> /dev/null)

    if [ $? -eq 1 ]; then
        printf "${RED}Container %s doesn't exist. Creating container: ${NC}\n" ${CONTAINER}
        docker run --link postgres --link redis -p 8081:8081 -p 8082:8082 --name flight_route -e ENV=local -v ${CWD}/build/libs/flight_route.jar:/app.jar -v /var/log:/var/log -t flight_route:latest
    else
        docker attach ${CONTAINER}
    fi
fi