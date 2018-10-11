#/bin/bash

echo " "
echo "................."
echo "Starting the services . . . "
echo "................."
echo " "

docker-compose kill && docker-compose build --no-cache && docker-compose up -d

echo " "
echo "................."
echo "Services are up "
echo "................."
echo " "
