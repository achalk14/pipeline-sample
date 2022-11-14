# Multi-Stage Build
# build
FROM maven:3.8.6-eclipse-temurin-17-alpine AS build
COPY demo-spring/src /home/demo-spring/src
COPY demo-spring/pom.xml /home/demo-spring/pom.xml
RUN mvn -f /home/demo-spring/pom.xml clean package

# package
FROM openjdk:17-jdk-alpine
COPY --from=build /home/demo-spring/target/demo-spring-0.0.1-SNAPSHOT.jar /usr/local/lib/demo-spring.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/usr/local/lib/demo-spring.jar"]