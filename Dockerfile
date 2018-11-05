   FROM java:8
   COPY . /var/www/java
   WORKDIR /var/www/java
   RUN javac Sample.java
   ENTRYPOINT ["java", "-jar", "target/junitsample-0.0.1-SNAPSHOT.jar"]
