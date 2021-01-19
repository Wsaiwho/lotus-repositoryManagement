FROM java:8
VOLUME /tmp
ADD adminserver-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-jar","app.jar"]
EXPOSE 8443