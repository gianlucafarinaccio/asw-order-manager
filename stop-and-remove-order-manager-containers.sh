#############################################
# .sh file to stop and remove every container
#############################################

# stop
docker stop $(docker ps -a | grep om | awk '{print $1}')
docker stop consul

# remove
docker rm $(docker ps -a | grep om | awk '{print $1}')
docker rm consul

docker network rm order-manager-net