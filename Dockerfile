# Start with a base image containing Java runtime
FROM openjdk:8-jdk-alpine

# Add Maintainer Info

# Add a volume pointing to /tmp
VOLUME /tmp

# Make port 8080 available to the world outside this container
EXPOSE 8080


RUN mkdir /app
WORKDIR /app
COPY . /app
RUN ./gradlew build

ENTRYPOINT ["java","-jar","./build/libs/demo-0.0.1-SNAPSHOT.jar"]