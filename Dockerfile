FROM openjdk:17-alpine

RUN apk add --no-cache bash

ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} /app/app.jar

ADD https://raw.githubusercontent.com/vishnubob/wait-for-it/master/wait-for-it.sh /
RUN chmod +x /wait-for-it.sh
