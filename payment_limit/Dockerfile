FROM maven:3.9.11-eclipse-temurin-24-alpine AS deps
WORKDIR /home/app
COPY main/pom.xml main/pom.xml
COPY payment/pom.xml payment/pom.xml
COPY payment_limit/pom.xml payment_limit/pom.xml
COPY pom.xml .
WORKDIR /home/app/payment_limit
RUN mvn clean package

FROM maven:3.9.11-eclipse-temurin-24 AS build
COPY --from=deps /root/.m2 /root/.m2
COPY --from=deps /home/app/ /home/app
COPY payment_limit/src /home/app/payment_limit/src
WORKDIR /home/app/payment_limit
RUN mvn clean install -DskipTests=true

FROM eclipse-temurin:24-jre
COPY --from=build /home/app/payment_limit/target/*.jar app.jar
RUN apt-get -y update
RUN apt-get -y install curl
EXPOSE 8084
ENTRYPOINT java -jar app.jar