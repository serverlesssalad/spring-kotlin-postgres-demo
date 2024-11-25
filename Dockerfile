# Stage 1: Build
FROM gradle:7.6.2-jdk17 AS build
WORKDIR /app

# Copy Gradle configuration and source code
COPY build.gradle .
COPY settings.gradle .
COPY src/ src/
COPY gradle/ gradle/
COPY gradlew .

# Build the Spring Boot application
RUN ./gradlew bootJar --no-daemon

# Stage 2: Run
FROM openjdk:17-jdk-slim
WORKDIR /app

# Copy the Spring Boot JAR from the build stage
COPY --from=build /app/build/libs/*.jar app.jar

# Expose the application port
EXPOSE 8080

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
