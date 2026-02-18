[![Review Assignment Due Date](https://classroom.github.com/assets/deadline-readme-button-22041afd0340ce965d47ae6ef1cefeee28c7c493a6346c4f15d667ab976d596c.svg)](https://classroom.github.com/a/_nJTMnH4)


Extra's toegevoegd
==================

***Gebruik van een relationele database en Docker***

Wij hebben gekozen om gebruik te maken van PostgreSQL in plaats van H2, dit doen we dmv:

- een docker compose:
```
    version: "3.8"

services:
  birdwatching-db:
    image: postgres:16
    container_name: birdwatching-postgres
    ports:
      - "5432:5432"
    environment:
      POSTGRES_DB: birdwatching_db
      POSTGRES_USER: user
      POSTGRES_PASSWORD: password
    volumes:
      - postgres_data:/var/lib/postgresql/data

volumes:
  postgres_data:
```
- voordat we het birdwatchAPI project opstarten, starten we de db: "docker compose up" (in de directory waar het compose file staat)
---
- in application.properties hebben we deze aanpassingen gemaakt zodat we een PostgreSQL database konden gebruiken:
```
spring.datasource.url=jdbc:postgresql://localhost:5432/birdwatching_db
spring.datasource.username=user
spring.datasource.password=password
spring.datasource.driver-class-name=org.postgresql.Driver

spring.jpa.database-platform=org.hibernate.dialect.PostgreSQLDialect
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
```

- eerste blok om de database te configureren en aan spring boot te laten weten waar de database is

- tweede blok om aan spring boot laten weten dat we PostgreSQL gebruiken (!=MySQL/H2), voor de rest zorgt dit ervoor dat de database automatisch geüpdatet zal worden en dat het de SQL queries zal laten zien op een geformatteerde manier
---

***Toevoegen van SwaggerUI***

Wij hebben ook SwaggerUI toegevoegd
http://localhost:8080/swagger-ui/index.html

- in POM.xml hebben we deze dependency toegevoegd

---
```
<dependency>
    <groupId>org.springdoc</groupId>
    <artifactId>springdoc-openapi-starter-webmvc-ui</artifactId>
    <version>2.6.0</version>
</dependency>
```
---
- in WebSecurityConfiguration hebben we deze lijn aangepast
```
.requestMatchers("/api-docs/**").permitAll()
```
```
.requestMatchers("/v3/api-docs/**").permitAll()
```

- in application.properties hebben we deze configuratie toegevoegd

```
springdoc.enable-data-rest=false
springdoc.enable-hateoas=false
springdoc.override-with-generic-response=false
springdoc.show-actuator=false
```

- omdat spring boot 4 niet compatibel was met deze versie van springdoc openAPI hebben we bepaalde dingen moeten uitzetten zodat Swagger onze endpoints kon zien, zoniet
kregen we errorcode 500 zodra we SwaggerUI openden
---