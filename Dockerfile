FROM openjdk:8-jdk-alpine

ENV DB_URL=jdbc:postgresql://bdmid7wvnx33jsug41vz-postgresql.services.clever-cloud.com:5432/bdmid7wvnx33jsug41vz
ENV DB_USERNAME=uqcrcujsixa6a6uhfkha
ENV DB_PASSWORD=xpc9JRXAfLageF5GHuWY

ENV MAIL_HOST=smtp.gmail.com
ENV MAIL_PORT=587
ENV MAIL_USERNAME=rendrayulizar@gmail.com
ENV MAIL_PASSWORD=akundummy

ARG JAR_FILE=target/*.jar

COPY ${JAR_FILE} app.jar

ENTRYPOINT ["java","-jar","/app.jar"]