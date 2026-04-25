FROM openjdk:17
COPY "./target/CLUDFUTBOL-0.0.1-SNAPSHOT.jar.original" "app.jar"
EXPOSE 8215
ENTRYPOINT [ "java" , "-jar", "app.jar" ]
