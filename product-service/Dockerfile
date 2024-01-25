#############################################
# Dockerfile for product-service microservice
#############################################

# FROM = Create a new build stage from a base image
# ADD = Add local or remote files and directories
# EXPOSE = Describe which ports your application is listening on
# ENTRYPOINT = Specify default executable (run a command)
# HEALTHCHECK = Check a container's health on startup

FROM eclipse-temurin:17-jdk 

ADD build/libs/product-service.jar product-service.jar

EXPOSE 8080

ENTRYPOINT ["java", "-Xmx128m", "-Xms128m", "-jar", "product-service.jar"]

HEALTHCHECK --start-period=30s --interval=10s CMD curl -f http://localhost:8080/actuator/health || exit 1