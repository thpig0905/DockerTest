FROM amazoncorretto:17

COPY build/libs/test-0.0.1-SNAPSHOT.jar /app/test.jar
COPY entrypoint.sh /app/entrypoint.sh

ENV APP_HOME /app
EXPOSE 8080
VOLUME /app/upload

WORKDIR $APP_HOME

RUN chmod +x /app/entrypoint.sh

ENTRYPOINT ["/app/entrypoint.sh"]