
# Care For You Insurance

![alt text](https://github.com/mihir2441998/CareForYou-Insurance/blob/main/Architecture%20Diagram.png)


Care for you insurance is a microservice based insurance app which hosts for core services of functioning and all services should be up for proper working.

- Claims app - sends request for claim creation on Kafka and saves claim to Cliams database with proper statuses upon receiving validation reponse from Policy and Customer module.
- Policy app - validates claim by consuming claim creation event sent from claims app and validates details againt policy database and send validation response
- Customer app - validates claim by consuming claim creation event sent from claims app and validates details againt Customer database and send validation response
- Payment app (coming soon)

Application uses postgres database to host tables for all microservices with separate schema.






## Installation

Requires very simple steps to test project.

Prerequisite 
```
- Postgres or any SQL DBMS
- Kafka & Zookeeper
- Intellij Idea (preferred)
```
- Run Kafka and Zookeeper
- Run all microservices in separate Intellij window.
- Create data with provided postman collection in Policy and Customer module.
- Create claim using /claim/create claim API