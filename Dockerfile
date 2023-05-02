FROM openjdk:17-alpine

RUN apk update

ARG JAR_FILE=*.jar

COPY ${JAR_FILE} app.jar

ENTRYPOINT [ "java", "-jar", "/app.jar" ]
