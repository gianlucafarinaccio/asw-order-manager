#################################
# .sh file to run every container
#################################

# --network = Connect a container to a network
# -d = Run container in background and print container ID
# -e = Set environment variables
# --name = Assign a name to the container

echo Creating a Docker network named order-manager-net
docker network create order-manager-net   #create a network named order-manager-net
echo

echo Starting Consul in a Docker Container
docker run -d --network=order-manager-net --name=consul docker.io/hashicorp/consul
echo

echo Starting order-service in a Docker Container
docker run -d --network=order-manager-net -e "SPRING_PROFILES_ACTIVE=order-service" -e "ASW_ORDERMANAGER_ORDERSERVICE_INSTANCENAME=os1" --name=order-service order-service
echo

echo Starting product-service in a Docker Container
docker run -d --network=order-manager-net -e "SPRING_PROFILES_ACTIVE=product-service" -e "ASW_ORDERMANAGER_PRODUCTSERVICE_INSTANCENAME=ps1" --name=product-service product-service
echo

echo Starting order-validation-service in a Docker Container
docker run -d --network=order-manager-net -e "SPRING_PROFILES_ACTIVE=order-validation-service" -e "ASW_ORDERMANAGER_ORDERVALIDATIONSERVICE_INSTANCENAME=ov1" --name=order-validation-service order-validation-service
echo

#docker run -d --network=order-manager-net -e "ASW_ORDERMANAGER_ORDERVALIDATIONSERVICE_INSTANCENAME=ov1-SYNC" --name=order-validation-service order-manager-order-validation-service
#docker run -d --network=order-manager-net -e "ASW_ORDERMANAGER_ORDERVALIDATIONSERVICE_INSTANCENAME=ov2-ASYNC" --name=order-validation-service-async order-manager-order-validation-service-async

echo Starting api-gateway in a Docker Container
docker run -d --network=order-manager-net -p 8080:8080 --name=apigateway apigateway