   FROM java:8
   COPY . /var/www/java
   COPY *.jar /var/www/java
   WORKDIR /var/www/java
   RUN javac Sample.java
   ENTRYPOINT ["java", "-jar", "app-sample.jar"]
