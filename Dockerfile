# FROM openjdk:8-jre-alpine
# FROM adoptopenjdk/openjdk11:latest
# FROM gcr.io/distroless/java:11
FROM adoptopenjdk/openjdk11

COPY ./build/libs /app
WORKDIR /app
EXPOSE 8080/tcp

ENTRYPOINT ["java", "-jar", "push.back.jar"]
