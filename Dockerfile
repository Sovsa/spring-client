#
# Build stage
#
FROM maven:3.9.7-eclipse-temurin-21 AS build
WORKDIR /home/client-app
COPY pom.xml .
RUN mvn verify --fail-never
COPY src ./src
RUN mvn package
EXPOSE 8080
ENTRYPOINT ["java","-jar","/home/client-app/target/spring-client-0.0.1-SNAPSHOT.jar"]