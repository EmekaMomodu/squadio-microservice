# Squadio Microservice
This is a dockerized microservice with APIs to handle requests to view users, accounts and statements by performing a 
simple search on dates and amount ranges

# Entity Relationship
The Entity Relationship Diagram for the system
![Entity Relationship Diagram](https://github.com/EmekaMomodu/squadio-microservice/blob/main/docs/Entity-Relationship-Diagram.png "Entity Relationship Diagram.")


## Setup
run `mvn clean install` on terminal from project root directory

To build the docker image, run `docker build -t squadio-microservice .`

To spin up a container from the created image, run `docker run -p 8080:8080 -d squadio-microservice`


## API DOC
View api documentation at [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)

Or import postman docs located at [Squadio-Microservice.postman_collection.json](https://github.com/EmekaMomodu/squadio-microservice/blob/main/docs/Squadio-Microservice.postman_collection.json)

## Database Console
View H2 in-memory database at [http://localhost:8080/h2-console](http://localhost:8080/h2-console) login details specified at [application.properties](https://github.com/EmekaMomodu/squadio-microservice/blob/main/src/main/resources/application.properties)
