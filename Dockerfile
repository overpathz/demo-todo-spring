FROM openjdk:17-ea-16-jdk
COPY target/demo-replay-0.0.1-SNAPSHOT.jar demo-replay-1.0.0.jar
ENTRYPOINT ["java", "-jar", "/demo-replay-1.0.0.jar"]