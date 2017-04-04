FROM java:8
VOLUME /tmp
ADD target/Bravo-1.0-SNAPSHOT.jar app.jar
EXPOSE 8080
RUN bash -c 'touch /app.jar'
ENTRYPOINT ["java","-jar","/app.jar"]
