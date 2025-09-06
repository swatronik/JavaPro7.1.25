FROM maven:3.9.11-eclipse-temurin-24-alpine AS build
COPY main/src /home/app/src
COPY main/pom.xml /home/app
RUN mvn -f /home/app/pom.xml package

FROM eclipse-temurin:24-jre-alpine
COPY --from=build /home/app/target/*-jar-with-dependencies.jar ./app.jar
EXPOSE 8081
ENTRYPOINT java -jar ./app.jar