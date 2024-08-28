# Build Stage
FROM maven:3.8.4-openjdk-17 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

# Run Stage
FROM openjdk:17.0.2-jdk-slim
WORKDIR /app
COPY --from=build /app/target/ShopIt-0.0.1-SNAPSHOT.jar ShopIt.jar
EXPOSE 2828
ENTRYPOINT ["java","-jar","ShopIt.jar"]
