# Stage 1: Build the application
FROM maven:3.9-eclipse-temurin-17 AS build
WORKDIR /app
# Copy the pom.xml and source code
COPY pom.xml .
COPY src ./src
# Build the application
RUN mvn clean package -DskipTests

# Stage 2: Run the application
FROM eclipse-temurin:17-jre-alpine
WORKDIR /app
# Copy the built jar from the previous stage
COPY --from=build /app/target/*.jar app.jar
# Expose the dynamic port used by Render
EXPOSE 8080
# Start the application
ENTRYPOINT ["java", "-jar", "app.jar"]