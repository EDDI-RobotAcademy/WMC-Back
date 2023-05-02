FROM openjdk:17-alpine

RUN apk update

COPY target/*.jar app.jar

ENTRYPOINT [ "java", "-jar", "/app.jar" ]
