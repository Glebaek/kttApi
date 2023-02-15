FROM openjdk:17
EXPOSE 8080
ADD target/kttapi-docker.jar kttapi-docker.jar
ENTRYPOINT ["java","-jar","/kttapi-docker.jar"]