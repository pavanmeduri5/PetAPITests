# PetAPITests

This is a Rest API test solution for PET endpoints available in petstore.swagger.io swagger documentation. 
Tests are written using a combination of CucumberBDD, RestAssured and Maven.

## Technology Stack

- Java
- Cucumber BDD
- Maven
- RestAssured

## Prerequisites

* [Java 1.8](https://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html) - Java Dev Kit
* [Maven](https://maven.apache.org/download.cgi) - Dependency Manager

## Application Under Test

We are using Restful-PetStore APIs as the Application Under Test.

* URL : https://petstore.swagger.io/

### Execute Tests in local
Cloning the repository
Run the following command in your favourite IDE Terminal to execute tests.

$ mvn compile test
Extent Report will be generated after tests execution under Report folder (both html and pdf)
![image](https://github.com/pavanmeduri5/PetAPITests/assets/50548098/73f8d79e-30da-4e06-9078-b4a3ef9e9514)


```
