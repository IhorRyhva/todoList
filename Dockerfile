FROM openjdk:20-jdk-slim-bullseye
WORKDIR /app
COPY /target/todoList-0.0.1-SNAPSHOT.jar /app/todoList.jar
ENTRYPOINT ["java", "-jar", "todoList.jar"]
