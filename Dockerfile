FROM amazoncorretto:11-alpine-jdk
COPY ./build/libs/pi-0.0.1-SNAPSHOT.jar pi-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar", "pi-0.0.1-SNAPSHOT.jar"]