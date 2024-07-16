FROM openjdk:17-jdk-alpine
VOLUME /tmp
COPY target/*.jar SmartMail.jar
ENTRYPOINT ["java","-jar","/SmartMail.jar"]
