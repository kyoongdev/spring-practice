
FROM openjdk:17

WORKDIR /usr/src/app

ARG JAR_PATH=./build/libs/*.jar

COPY ${JAR_PATH} app.jar

CMD ["java","-jar","./app.jar"]