FROM amazoncorretto:17
COPY build/libs/issueMoa-learning-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java", "-Dspring.profiles.active=docker", "-jar", "/app.jar"]