FROM amazoncorretto:21-alpine-jdk
ARG JAR_FILE=build/libs/*SNAPSHOT.jar
ARG PROFILES
ARG ENV
COPY ${JAR_FILE} aistock.jar
ENTRYPOINT ["java", "-Dspring.profiles.active=${PROFILES}", "-Dserver.env=${ENV}", "-Duser.timezone=Asia/Seoul", "-jar", "aistock.jar"]