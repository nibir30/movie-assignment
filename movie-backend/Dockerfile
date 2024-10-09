FROM openjdk:17-alpine
ENV SPRING_PROFILES_ACTIVE=prod
COPY target/movie-backend*.jar /opt/app/app.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/opt/app/app.jar"]
