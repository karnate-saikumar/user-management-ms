# Build stage
FROM gradle:8-jdk17 as builder
WORKDIR /app
COPY . .
RUN gradle build -x test

# Runtime stage
FROM eclipse-temurin:17-jre-alpine
WORKDIR /app
COPY --from=builder /app/build/libs/*.jar user-management-ms.jar
ENTRYPOINT ["java", "-jar", "user-management-ms.jar"]
