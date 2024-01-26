#!/bin/bash

# Script per buildare l'applicazione OrderManager

echo ----------------------------------
echo \>  Building ORDERMANAGER
echo \> This may take few seconds...
echo ----------------------------------
echo
echo \> 1. gradle build
gradle build
echo ----------------------------------
echo
echo \> 2. docker compose build
docker compose build
echo
echo Building completed!
echo ----------------------------------