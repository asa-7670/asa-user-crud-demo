FROM eclipse-temurin
WORKDIR /opt
ADD ./target/asa-user-crud-demo.jar /opt/app.jar
EXPOSE 8181
ENTRYPOINT ["java","-jar", "/opt/app.jar"]