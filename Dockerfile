FROM eclipse-temurin:17.0.8_7-jre
EXPOSE 8080
#ARG JAR_FILE=build/libs/weather-test-task-0.0.1-SNAPSHOT.jar
WORKDIR /weather-test-task
COPY build/libs/*.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]
