# Build stage — compile from source so the image always matches current code
FROM maven:3.9-eclipse-temurin-21 AS build
WORKDIR /build

# Cache dependencies: only re-downloads when pom.xml changes
COPY pom.xml .
RUN mvn -q -B dependency:go-offline

# Compile and package
COPY src ./src
RUN mvn -q -B -DskipTests clean package

# Runtime stage
FROM eclipse-temurin:21-jre
WORKDIR /app
COPY --from=build /build/target/*.jar app.jar
EXPOSE 8082
ENTRYPOINT ["java","-jar","app.jar"]
