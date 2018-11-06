FROM frolvlad/alpine-oraclejdk8:slim

EXPOSE 8082
VOLUME /tmp /var/log
#COPY flight_route.jar /app.jar

ENTRYPOINT ["java","-Dspring.profiles.active=${ENV}","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]
