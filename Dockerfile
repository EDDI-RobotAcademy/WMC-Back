FROM openjdk:17-alpine

# Add the wait-for-it.sh script
ADD https://raw.githubusercontent.com/vishnubob/wait-for-it/master/wait-for-it.sh /wait-for-it.sh
RUN chmod +x /wait-for-it.sh

# Install bash if required by the wait-for-it.sh script
RUN apk update && apk add --no-cache bash

COPY build/libs/Backend-0.0.1-SNAPSHOT.jar app.jar

ENTRYPOINT [ "java", "-jar", "/app.jar" ]
