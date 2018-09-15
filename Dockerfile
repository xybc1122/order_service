FROM openjdk:8-jdk-alpine
VOLUME /tmp
ARG JAR_FILE
COPY ${JAR_FILE} order.jar
ENTRYPOINT ["java","-jar","/order.jar"]