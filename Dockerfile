FROM openjdk:8
ADD target/login-service.jar login-service.jar
EXPOSE 9001
ENTRYPOINT ["java","-jar","login-service.jar"]