FROM openjdk:24
COPY "./target/CLUDFUTBOL-0.0.1-SNAPSHOT.jar" "app.jar"
EXPOSE 8215
ENTRYPOINT [ "java" , "-jar", "app.jar" ]
