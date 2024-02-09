# IN306Blog

## Version 0.7.3

Dev Port 8881 (application.properties)
Prod Port 9090 (application.properties)
Keycloak default Quarkus,
Rollen User
Authentifizierung mit Keycloak for Blogid
Eigene Error Klasse,
HeaderParameter,
Put war bereits drin Version 0.5.0
CRUD Author, Blog und Comments, POST, DELETE, PUT und GET
DTOs
Swagger
Dockerfile
Docker-compose.yml
Docker image und container mysql und phpmyadmin
mysql port 3307

## Starten
```./mvnw quarkus:dev```

http://localhost:8881/q/dev-ui/io.quarkus.quarkus-oidc/keycloak-provider?state=CDsQcFs&session_state=66c516c5-ebf6-436d-aa45-4b8da2dedb02&code=0f10ce47-a480-434a-962a-bc757eba0451.66c516c5-ebf6-436d-aa45-4b8da2dedb02.dc4b672a-f24c-4f1c-986c-a4c871f0223d

## Bedienung per URL GET Dev Mode
http://localhost:8881/blogs/1
http://localhost:8881/blogs
http://localhost:8881/author
http://localhost:8881/author/1
http://localhost:8881/comment
http://localhost:8881/comment/1

## Bedienung per URL POST  
http://localhost:8881/blogs     (JSON Body)
http://localhost:8881/author    (JSON Body)
http://localhost:8881/comment   (JSON Body)

## Bedienung per URL PUT    
http://localhost:8881/blogs/1   (JSON Body)
http://localhost:8881/author/1  (JSON Body)
http://localhost:8881/comment/1 (JSON Body)

## Bedienung per URL DELETE
http://localhost:8881/blogs/1
http://localhost:8881/author/1  
http://localhost:8881/comment/1

## Bedienung per URL HeaderParameter
http://localhost:8881/blogs/1
http://localhost:8881/blogs
http://localhost:8881/author
http://localhost:8881/author/1
http://localhost:8881/comment
http://localhost:8881/comment/1

## Bedienung per URL Swagger
http://localhost:8881/q/swagger-ui/

## Keycloak
name => rollen => password
alice => admin, user => alice
bob => author => bob
u1 => user => user => u1

## Bedienung per URL Docker
http://localhost:8181/ (phpmyadmin)

## Technologies
docker image und container mysql und phpmyadmin
mysql port 3307 
phpmyadmin port 8181

## Installation
cmd öffnen
cd ins Verzeichnis Dockerfile und docker-compose.yml navigieren
docker compose up -d
phpmyadmin im Webbrowser http://localhost:8181/
### Features
SWAGGER, CRUD Blog und Author, POST, DELETE, PUT und GET

## Swagger
http://localhost:8881/q/swagger-ui/

# code-with-quarkus

This project uses Quarkus, the Supersonic Subatomic Java Framework.

If you want to learn more about Quarkus, please visit its website: https://quarkus.io/ .

## Running the application in dev mode

You can run your application in dev mode that enables live coding using:
```shell script
./mvnw compile quarkus:dev
```

> **_NOTE:_**  Quarkus now ships with a Dev UI, which is available in dev mode only at http://localhost:8080/q/dev/.

## Packaging and running the application

The application can be packaged using:
```shell script
./mvnw package
```
It produces the `quarkus-run.jar` file in the `target/quarkus-app/` directory.
Be aware that it’s not an _über-jar_ as the dependencies are copied into the `target/quarkus-app/lib/` directory.

The application is now runnable using `java -jar target/quarkus-app/quarkus-run.jar`.

If you want to build an _über-jar_, execute the following command:
```shell script
./mvnw package -Dquarkus.package.type=uber-jar
```

The application, packaged as an _über-jar_, is now runnable using `java -jar target/*-runner.jar`.

## Creating a native executable

You can create a native executable using:
```shell script
./mvnw package -Pnative
```

Or, if you don't have GraalVM installed, you can run the native executable build in a container using:
```shell script
./mvnw package -Pnative -Dquarkus.native.container-build=true
```

You can then execute your native executable with: `./target/code-with-quarkus-1.0.0-SNAPSHOT-runner`

If you want to learn more about building native executables, please consult https://quarkus.io/guides/maven-tooling.

## Provided Code

### RESTEasy Reactive

Easily start your Reactive RESTful Web Services

[Related guide section...](https://quarkus.io/guides/getting-started-reactive#reactive-jax-rs-resources)
