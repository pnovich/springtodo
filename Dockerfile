FROM eclipse-temurin:17-jdk-alpine
VOLUME /tmp
ARG JAR_FILE
COPY target/*.jar springtodo.jar
ENTRYPOINT ["java","-jar","/springtodo.jar"]