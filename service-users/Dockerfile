FROM amazoncorretto:17
VOLUME /tmp
COPY build/libs/issueMoa-users-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java", "-Dspring.profiles.active=docker", "-jar", "/app.jar"]
