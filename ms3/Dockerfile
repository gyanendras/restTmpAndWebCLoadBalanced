FROM openjdk:17-jdk-alpine 
COPY target/ms-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-Dspring.profiles.active=dev","-jar","/app.jar"] 