# Stage 1: build the JAR file
FROM maven:3-amazoncorretto-20 as builder
WORKDIR /app
COPY . .
RUN mvn clean package

# Stage 2: create the Docker image
FROM openjdk:20
WORKDIR /app
COPY --from=builder /app/cash-machine-bootstrap/target/cash-machine-bootstrap-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-jar","/app/app.jar"]
