# Use an official Java 17 image as the base image
FROM openjdk:17

# Set the working directory to /app
COPY target/*.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]