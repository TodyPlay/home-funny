FROM eclipse-temurin:17-jdk-alpine AS Gateway

VOLUME /tmp

COPY ../home-funny-application/target/home-funny-application-*.jar app.jar

ENTRYPOINT ["java","-jar","/app.jar"]

FROM eclipse-temurin:17-jdk-alpine AS Application

VOLUME /tmp

COPY ../home-funny-gateway/target/home-funny-gateway-*.jar app.jar
#将静态资源放到该目录
COPY ../home-funny-front/target/dist/* /static/

ENTRYPOINT ["java","-jar","/app.jar"]

