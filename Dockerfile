# =========================
# Stage 1: Build bằng Maven
# =========================
FROM maven:3.9.6-eclipse-temurin-17 AS build

WORKDIR /app

# Copy file pom trước để tối ưu cache
COPY pom.xml .
# RUN mvn dependency:go-offline

# Copy source code
COPY src ./src

# Build project
RUN mvn clean package -DskipTests


# =========================
# Stage 2: Runtime (nhẹ)
# =========================
FROM eclipse-temurin:17-jdk-alpine

WORKDIR /app

# Copy file jar từ stage build
# COPY --from=build /app/target/*.jar app.jar
COPY --from=build /app/target/*-SNAPSHOT.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]