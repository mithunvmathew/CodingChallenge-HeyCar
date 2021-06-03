FROM openjdk:8-jdk-alpine
MAINTAINER mithun
COPY ./target/coding-challenge-1.0.0.jar heycar-server-1.0.0.jar
RUN sh -c 'touch heycar-server-1.0.0.jar'
ENTRYPOINT ["java","-jar","/heycar-server-1.0.0.jar"]