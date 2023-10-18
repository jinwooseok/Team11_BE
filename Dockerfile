FROM openjdk:17-oracle as build

WORKDIR /Team11_BE

COPY . .

RUN mkdir -p /logs

RUN echo "systemProp.http.proxyHost=krmp-proxy.9rum.cc\nsystemProp.http.proxyPort=3128\nsystemProp.https.proxyHost=krmp-proxy.9rum.cc\nsystemProp.https.proxyPort=3128" >> /Team11_BE/gradle/wrapper/gradle-wrapper.properties


RUN ./gradlew clean build

FROM openjdk:17-oracle

COPY --from=build /Team11_BE/build/libs/goalajuma-*.jar .

RUN ls /Team11_BE

ENV	PROFILE default

ENV TZ=Asia/Seoul

EXPOSE 8080

ARG JAVA_OPTS

ARG RELEASE_VERSION
ENV DD_VERSION=${RELEASE_VERSION}

ARG JAR_FILE="/Team11_BE/build/libs/goalajuma-*.jar";

ENTRYPOINT java -XX:MaxGCPauseMillis=100 -XX:InitialRAMPercentage=50.0 -XX:MaxRAMPercentage=80.0 -agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=*:5005 $JAVA_OPTS -jar app.jar