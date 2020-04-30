FROM openjdk:8-jdk-alpine
RUN mkdir -p /app/
ADD build/libs/demo-0.0.1-SNAPSHOT.jar /app/demo-01.jar
ENTRYPOINT ["java", "-jar", "/app/demo-01.jar"]