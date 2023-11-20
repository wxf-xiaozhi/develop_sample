FROM docker-mirror.gwm.cn/java:8

VOLUME /tmp
ADD target/skypivot-sys-server.jar app.jar
EXPOSE 8101
RUN ln -sf /usr/share/zoneinfo/Asia/Shanghai /etc/localtime && echo 'Asia/Shanghai' >/etc/timezone
ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]
