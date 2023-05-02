FROM openjdk:17-alpine

RUN apk update

COPY build/libs/Backend-0.0.1-SNAPSHOT.jar app.jar

ENTRYPOINT [ "java", "-jar", "/app.jar" ]
