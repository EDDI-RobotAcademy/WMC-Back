FROM openjdk:17-alpine

ARG JAR_FILE=*.jar
COPY build/libs/Backend-0.0.1-SNAPSHOT.jar app.jar

RUN apk add --no-cache --update bash
# 여기서 wait-for-it.sh 를 설치하고 권한을 준다
ADD https://raw.githubusercontent.com/vishnubob/wait-for-it/master/wait-for-it.sh /
RUN chmod +x /wait-for-it.sh

# Make sure the path to keystore.p12 is correct
COPY ./keystore.p12 /etc/nginx/certs/keystore.p12
RUN chmod 644 /etc/nginx/certs/keystore.p12

ENTRYPOINT [ "java", "-jar", "/app.jar" ]
