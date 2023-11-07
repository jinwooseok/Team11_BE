FROM krmp-d2hub-idock.9rum.cc/goorm/gradle:7.3.1-jdk17
RUN mkdir -p /logs

ENV	PROFILE default
ENV TZ=Asia/Seoul
EXPOSE 8080

ARG JAVA_OPTS

ARG RELEASE_VERSION
ENV DD_VERSION=${RELEASE_VERSION}

ARG JAR_FILE="build/libs/goalajuma-*.jar"

COPY . .
RUN sudo ln -snf /usr/share/zoneinfo/Asia/Seoul /etc/localtime
ENTRYPOINT java -jar -Duser.timezone=Asia/Seoul build/libs/goalajuma-0.0.1-SNAPSHOT.jar