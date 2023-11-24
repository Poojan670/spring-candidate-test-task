<div align="center">
    <h1>Candidate Test Task</h1>
</div>

# Setup Instructions:

### Project Description

_Java - Spring Boot Task for candidate Test_

### Requirements

JDK 17

maven

### Using Maven

````
Open src/main/resources/application.properties.

Fill in the following MySQL configurations:

# MySQL Configurations
spring.datasource.url=jdbc:mysql://localhost:3306/your_database_name_here
spring.datasource.username=
spring.datasource.password=
Run the application using Maven:

mvn spring-boot:run
````

### Using Attached JAR
````
use the following MySQL configurations in application.properties:

# MySQL Configurations for JAR execution
spring.datasource.url=jdbc:mysql://localhost:3306/candidate_test_db
spring.datasource.username=poojan
spring.datasource.password=poojan12
MySQL Shell Commands:

sql
CREATE DATABASE candidate_test_db;
CREATE USER 'poojan'@'localhost' IDENTIFIED BY 'poojan12';
GRANT ALL PRIVILEGES ON *.* TO 'poojan'@'localhost' WITH GRANT OPTION;
Execute the JAR file:

java -jar candidate-test-0.1.jar

````

#### Notes
_Ensure that you have a MySQL server running on localhost before executing the application._
