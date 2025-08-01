FROM  maven:3.9.6-openjdk-23 AS BUILD
COPY . .
RUN mvn clean package -DSkipTests

FROM  openjdk:23-jdk
COPY   --from=build  /target/Courier-0.0.1-SNAPSHOT.jar Courier.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar","-project1.jar"]

