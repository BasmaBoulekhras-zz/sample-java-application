   FROM java:8
   COPY . /var/www/java
   WORKDIR /var/www/java
   RUN javac Sample.java
   CMD ["java", "Sample"]
   CMD exec /bin/bash -c "trap : TERM INT; sleep infinity & wait"
