FROM eclipse-temurin:17-jdk AS build

WORKDIR /app

COPY gradlew .
COPY gradle gradle
COPY build.gradle settings.gradle ./

COPY src src
RUN for attempt in 1 2 3 4 5; do \
      if sh ./gradlew --no-daemon bootJar; then exit 0; fi; \
      if [ "$attempt" -lt 5 ]; then \
        delay=$((attempt * 30)); \
        echo "Gradle build failed; retrying in ${delay}s..."; \
        sleep "$delay"; \
      fi; \
    done; \
    exit 1

FROM eclipse-temurin:17-jre

WORKDIR /app

COPY --from=build /app/build/libs/app.jar app.jar

EXPOSE 8080

CMD ["sh", "-c", "java -Dserver.port=${PORT:-8080} -jar app.jar"]
