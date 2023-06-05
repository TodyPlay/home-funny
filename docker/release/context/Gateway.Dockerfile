FROM eclipse-temurin:17-jdk-alpine AS home-funny-gateway

VOLUME /tmp

COPY target/home-funny-gateway-*.jar app.jar

EXPOSE 8081

ENTRYPOINT ["java","-jar","/app.jar"]