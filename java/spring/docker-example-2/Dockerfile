FROM gradle:8.11.1-jdk23 AS builder
WORKDIR /app

COPY gradle/wrapper gradle/wrapper
COPY build.gradle .
COPY settings.gradle .
COPY gradlew .
COPY gradlew.bat .

RUN ./gradlew dependencies

COPY src ./src
RUN ./gradlew build

FROM openjdk:23
WORKDIR /app
COPY --from=builder /app/build/libs/*.jar app.jar
CMD ["java", "-jar", "app.jar"]