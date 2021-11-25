FROM openjdk:11-jdk

EXPOSE 8080

COPY ./target/*.jar app.jar

CMD java -jar app.jar
