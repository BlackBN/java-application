FROM openjdk:8-jdk-alpine as build

ENV TZ=Asia/Shanghai
RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone

VOLUME /tmp
WORKDIR /opt/
# 加入打包好的jar文件(xxxxx改为自己文件名)
ADD ./target/smcp.jar /smcp.jar
ADD ./application-premain-jar-with-dependencies.jar /javaagent-jar/application-premain-jar-with-dependencies.jar
ADD ./jmx_prometheus_javaagent-0.16.1.jar /jmx_prometheus_javaagent-0.16.1.jar
ADD ./prometheus-jmx-config.yaml /prometheus-jmx-config.yaml
COPY ../nacos-consumer-1/src/main/resources/application.yml /opt/test/application.yml

ENTRYPOINT ["java","-javaagent:/javaagent-jar/application-premain-jar-with-dependencies.jar","-javaagent:/jmx_prometheus_javaagent-0.16.1.jar=5555:/prometheus-jmx-config.yaml","-Dspring.config.location=/opt/test/application.yml","-jar","/smcp.jar"]