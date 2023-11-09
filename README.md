# supermarket-meli


[Spring Boot](http://projects.spring.io/spring-boot/) app.

## Requirements

For building and running the application you need:

- [JDK 17+](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html)
- [Maven 3](https://maven.apache.org)
- [Docker Compose](https://docs.docker.com/compose/install/)

## Running the application locally

First navigate to: `br/com/meli/supermarket/infrastructure/db`

[Run](https://docs.docker.com/engine/reference/commandline/compose_up/) the docker container.
```shell
docker compose up
```

Now navigate to: `br/com/meli/supermarket/presenter`

[Install](https://maven.apache.org/run.html) the maven application.
```shell
mvn clean install
```

[Run](https://docs.spring.io/spring-boot/docs/1.5.16.RELEASE/reference/html/using-boot-running-your-application.html#using-boot-running-with-the-maven-plugin) the Spring Boot application.

```shell
mvn spring-boot:run
```

## Copyright

Released under the MIT License.