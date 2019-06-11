FROM openjdk:11-jre

ADD build/distributions/poll-service-boot-*.tar /

RUN echo "DB_JDBC_URL = $DB_JDBC_URL"

ENTRYPOINT /poll-service-boot-*/bin/poll-service
