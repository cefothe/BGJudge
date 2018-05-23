# BGJudge
BGJudge is open source system for online executions algorithms.

## Suported languages
* Java 8

## Technologies
We choise to use those  technologies.
* Java 8 SE
* Spring Boot
* Spring Data
* Spring MVC + Thymeleaf

## Deployment

### For production
My suggestion is to use docker-compose. You need to be sure that in docker directory you have latest version on our mvc and worker module.
The you can go into main directory and run:
``
docker-compose up --build
``

### For development
For development you need to start MySQL and RabbitMQ server.
* For MySQL you can run:
  ``
  docker run --name bgjudge -e MYSQL_ROOT_PASSWORD=021358 -p 3306:3306 -d mysql:5.7
  ``
* For RabbitMQ you can run:
  ``
  docker run --name bgjudge-rabbitmq -e RABBITMQ_DEFAULT_USER=guest -e RABBITMQ_DEFAULT_PASS=guest -p 5672:5672 -p 15672:15672 -d rabbitmq:3.6.6-management
  ``

## Authors
* Stefan Angelov (cefothe)
