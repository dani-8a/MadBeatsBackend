FROM openjdk:23-slim

EXPOSE 8080:8080

RUN mkdir /app

COPY ./target/madbeats-0.0.1-SNAPSHOT.jar /app/madbeats.jar

ENTRYPOINT ["java", "-jar", "/app/madbeats.jar"]