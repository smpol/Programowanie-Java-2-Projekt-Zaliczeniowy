FROM openjdk:17

COPY ./target/projekt-0.0.1-SNAPSHOT.jar /tmp
WORKDIR /tmp
ENTRYPOINT ["java", "-jar", "projekt-0.0.1-SNAPSHOT.jar"]

#open port 8080
EXPOSE 8080/tcp

