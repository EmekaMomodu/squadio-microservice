FROM openjdk:8
WORKDIR /
ADD target/squadio-microservice.jar //
EXPOSE 8080
ENTRYPOINT [ "java", "-Dspring.profiles.active=default", "-jar", "/squadio-microservice.jar"]
