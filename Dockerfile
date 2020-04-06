FROM openjdk:8-jdk-alpine
VOLUME /tmp
ADD /target/school-auth-service.jar school-auth-service.jar
ENTRYPOINT ["java", "-jar", "school-auth-service.jar"]
EXPOSE 8082
