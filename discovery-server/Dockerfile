FROM amazoncorretto:17
VOLUME /tmp
COPY build/libs/issueMoa-eureka-server-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]