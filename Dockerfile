FROM openjdk:17

COPY ./target/projekt-0.0.1-SNAPSHOT.jar /tmp
WORKDIR /tmp
ENTRYPOINT ["java", "-jar", "projekt-0.0.1-SNAPSHOT.jar"]

EXPOSE 8080/tcp

