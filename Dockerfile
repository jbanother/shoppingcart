FROM gradle:jdk8 as BUILD

USER root
COPY . /code
WORKDIR /code

RUN gradle build
RUN gradle test

FROM openjdk:8-jre-alpine

COPY --from=BUILD /code/build/libs/code-1.0.jar /opt/code-1.0.jar

ENTRYPOINT [ "java" ]
CMD [ "/opt/code-1.0.jar" ]
