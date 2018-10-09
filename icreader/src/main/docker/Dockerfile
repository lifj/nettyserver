FROM frolvlad/alpine-oraclejdk8:slim
VOLUME /tmp
ADD icreader-1.0.jar app.jar
RUN sh -c 'touch /app.jar'
ENV JAVA_OPTS="$JAVA_OPTS -Duser.timezone=Asia/Shanghai -Djava.security.egd=file:/dev/./urandom -Xmx1024m"
EXPOSE 7788
EXPOSE 20882
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /app.jar" ]