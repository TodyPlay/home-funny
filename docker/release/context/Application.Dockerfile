FROM eclipse-temurin:17-jdk-alpine AS home-funny-application

VOLUME /tmp

COPY target/home-funny-application-*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java","-jar","/app.jar"]

