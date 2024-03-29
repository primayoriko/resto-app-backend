FROM openjdk:8-jdk-alpine

ARG DB_URL_ARG
ARG DB_USERNAME_ARG
ARG DB_PASSWORD_ARG

ARG MAIL_HOST_ARG
ARG MAIL_PORT_ARG
ARG MAIL_USERNAME_ARG
ARG MAIL_PASSWORD_ARG

ARG FIREBASE_PROJECT_ID_ARG
ARG FIREBASE_CREDENTIALS_ARG
ARG FIREBASE_BUCKET_NAME_ARG

ENV DB_URL=$DB_URL_ARG
ENV DB_USERNAME=$DB_USERNAME_ARG
ENV DB_PASSWORD=$DB_PASSWORD_ARG

ENV MAIL_HOST=$MAIL_HOST_ARG
ENV MAIL_PORT=$MAIL_PORT_ARG
ENV MAIL_USERNAME=$MAIL_USERNAME_ARG
ENV MAIL_PASSWORD=$MAIL_PASSWORD_ARG

ENV FIREBASE_PROJECT_ID=$FIREBASE_PROJECT_ID_ARG
ENV FIREBASE_CREDENTIALS=$FIREBASE_CREDENTIALS_ARG
ENV FIREBASE_BUCKET_NAME=$FIREBASE_BUCKET_NAME_ARG

ARG JAR_FILE=target/*.jar

COPY ${JAR_FILE} app.jar

ENTRYPOINT ["java","-jar","/app.jar"]