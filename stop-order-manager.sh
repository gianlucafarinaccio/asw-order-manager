#!/bin/bash

# Script per avviare l'applicazione OrderManager 

echo ----------------------------------
echo \>  Stop ORDERMANAGER
echo \> This may take few seconds...
echo ----------------------------------

echo 1. docker compose down
docker compose down
echo
echo ORDERMANAGER stopped!
echo ----------------------------------