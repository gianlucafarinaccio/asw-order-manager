###################################
# .sh file to build every container
###################################

# --rm = Remove intermediate containers after a successful build
# -t = Name and optionally a tag in the name:tag format

docker build --rm -t om-order-service ./order-service
docker build --rm -t om-product-service ./product-service
docker build --rm -t om-order-validation-service ./order-validation-service

docker build --rm -t om-apigateway ./api-gateway