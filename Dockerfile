FROM openjdk:17-oracle
RUN mkdir -p /logs

ENV	PROFILE default
ENV TZ=Asia/Seoul
EXPOSE 8080

ARG JAVA_OPTS

ARG RELEASE_VERSION
ENV DD_VERSION=${RELEASE_VERSION}

ARG JAR_FILE="build/libs/goalajuma-*.jar"

COPY . .

ENTRYPOINT java -jar build/libs/goalajuma-0.0.1-SNAPSHOT.jar