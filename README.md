# Real-time-Polling

## Introduction

### Environment

* DataBase: MySQL
* Back-end: Java (Spring Boot)
* Front-end: TS / JS (Angular)

## Get Started

1. Add the file **application.properties** in the path: ```demoBackend/src/main/resources/application.properties```

2. Type the following info. in the file **application.properties**:

```
spring.application.name=demo

spring.jpa.hibernate.ddl-auto=update
spring.datasource.url=jdbc:mysql://localhost:3306/test
spring.datasource.username=**your username**
spring.datasource.password=**your password**
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.jpa.show-sql: true
```

## Reference