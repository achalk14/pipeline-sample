
<!-- README-TOP -->
# Pipeline Sample


<!-- ABOUT THE PROJECT -->
## About The Project

pipeline-sample is created to demonstrate a simple spring boot application, junit tests for the application and a pipeline that builds, tests, builds a docker image, and pushes it to docker hub. :smile:


<!-- ASSUMPTIONS -->
## Assumptions

Some assumptions were made while writing the application:
- Purpose is to write a workable code along with tests
- 100% code coverage for unit test is not required
- Demonstrate ability to write CRUD operations for an application
- Demonstrate ability to build, test and create artifact (docker image) in an automated pipeline
- Pipeline triggers on two events push and pull requests
- Application should run with simple docker run command

<!-- DESIGN -->


<!-- GETTING STARTED -->
## Getting Started

This is an example of how you may give instructions on setting up your project locally.
To get a local copy up and running follow these simple example steps.

### How to run

Using source code

1. Clone the repo
   ```sh
   git clone https://github.com/achalk14/pipeline-sample.git
   ```
2. Build jar
   ```sh
   mvn -f ./demo-spring/pom.xml clean package
   ```
3. Run the application
   ```sh
   java -jar <path-to-jar-file>
   ```

Using docker

1. Pull docker image
    ```
    docker pull achalk/demo-spring:latest
    ```
2. Run application in a container
    ```
    docker run -p 8080:8080 achalk/demo-spring:latest
    ```


<!-- DESIGN -->
## Design

### Application
This is a demo spring boot application which has '/api/v1/person' endpoint. 
The API accepts four HTTP methods:
-   GET :
    -   This can get a collection of person
    -   This can get a person by id
-   POST:
    -   Create a Person
-   PUT:
    -   Update a Person by id
-   DELETE:
    -   Delete a Person by id

### Test
JUnit is used to test the Data Access Layer for this application. It tests all the methods defined in Data Access Layer.

### Pipeline
Pipeline makes use of GitHub Actions to automate build, test, and push artifact.
It has multiple steps that includes:
-   Cloning the respository
-   Installing JDK 17
-   Building with maven
-   Installing Docker and BuildX
-   Building docker image
-   Pushing docker image to docker hub repository


<!-- CONTACT -->
## Contact

Achal Kumar - [@achalk14](https://twitter.com/achalk14) - achal9256@gmail.com

Project Link: [https://github.com/achalk14/pipeline-sample](https://github.com/achalk14/pipeline-sample)
