FROM eclipse-temurin:17-jdk-alpine AS home-funny-application

VOLUME /tmp

COPY target/home-funny-application-*.jar app.jar
#将静态资源放到该目录
COPY target/dist/* ./static/

EXPOSE 8080

ENTRYPOINT ["java","-jar","/app.jar"]

