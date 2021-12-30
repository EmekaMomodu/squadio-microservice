# Squadio Microservice
This is a dockerized microservice with APIs to handle requests to view users, accounts and statements by performing a 
simple search on dates and amount ranges

## Setup
To build the docker image, run `docker build -t squadio-microservice .`

To spin up a container from the created image, run `docker run -p 8080:8080 -d squadio-microservice`

View api documentation at `http://localhost:8080/swagger-ui.html`

