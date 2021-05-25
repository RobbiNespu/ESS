#!/bin/bash
#
# Copyright (c) 2021-2021
#
# Project :  Advance Software Development - Exam Scheduling System with DFS
# Class name :  /home/robbi/Documents/workplace/java/ESS/docker_cleanup.sh
# Last modified:  5/24/21, 11:14 AM
# User : Robbi Nespu < robbinespu@gmail.com >
#
# License : https://github.com/RobbiNespu/ESS/LICENSE
#

# Origin : https://gist.github.com/beeman/aca41f3ebd2bf5efbd9d7fef09eac54d#gistcomment-3735369
# Creadit:  Richard Abrich (abrichr)
echo "Listing containers..."
containers=$(docker ps -qa)
echo "containers: $containers"

if [ ! -z "$containers" ]; then
  echo "Stopping containers..."
  docker stop "$containers"
  echo "Removing containers..."
  docker rm "$containers"
else
  echo "No containers found"
fi

echo "Listing images..."
images=$(docker images -qa)
echo "images: $images"

if [ ! -z "$images" ]; then
  echo "Removing images..."
  docker rmi -f "$images"
else
  echo "No images found"
fi

echo "Listing volumes..."
volumes=$(docker volume ls -q)
echo "volumes: $volumes"

if [ ! -z "$volumes" ]; then
  echo "Removing volumes..."
  docker volume rm "$volumes"
else
  echo "No volumes found"
fi

echo "Listing networks..."
networks=$(docker network ls -q)
echo "networks: $networks"

if [ ! -z "$networks" ]; then
  echo "Removing networks..."
  docker network rm "$networks"
else
  echo "No networks found"
fi

echo "These should not output any items:"
docker ps -a
docker images -a
docker volume ls

echo "This should only show the default networks:"
docker network ls
