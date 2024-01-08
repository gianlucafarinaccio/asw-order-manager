DOCKERHUB_USERNAME=aswproject
VERSION=2024-01

docker tag order-service ${DOCKERHUB_USERNAME}/order-service:${VERSION}
docker tag product-service ${DOCKERHUB_USERNAME}/product-service:${VERSION}
docker tag order-validation-service ${DOCKERHUB_USERNAME}/order-validation-service:${VERSION}
docker tag apigateway ${DOCKERHUB_USERNAME}/apigateway:${VERSION}

docker push ${DOCKERHUB_USERNAME}/order-service:${VERSION}
docker push ${DOCKERHUB_USERNAME}/product-service:${VERSION}
docker push ${DOCKERHUB_USERNAME}/order-validation-service:${VERSION}
docker push ${DOCKERHUB_USERNAME}/apigateway:${VERSION}