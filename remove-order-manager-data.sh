#!/bin/bash

# Script per rimuovere volumi docker delll'applicazione OrderManager

echo ----------------------------------
echo \>  Removing ORDERMANAGER data
echo \> This may take few seconds...
echo ----------------------------------

docker volume rm order-service-db-data
docker volume rm product-service-db-data
docker volume rm order-validation-service-db-data
docker volume rm kafka-data

echo ----------------------------------
echo \>  Removed \!
echo ----------------------------------