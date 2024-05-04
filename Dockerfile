# Use the official maven/Java 8 image to create a build artifact.
FROM maven:3.8.4-openjdk-8 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

# Use adoptopenjdk for the runtime image
FROM adoptopenjdk/openjdk8:alpine-slim

# Copy the built artifact from the build stage.
COPY --from=build /app/target/discord-0.0.1-SNAPSHOT.jar /app.jar

# Make port 8080 available to the world outside this container
EXPOSE 8080

# Run the jar file
ENTRYPOINT ["java","-jar","/app.jar"]
