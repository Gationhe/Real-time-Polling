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

### Spring Security & JWT

1. [【Spring Boot】第12.1課－初探 Spring Security 的認證與授權](https://chikuwacode.github.io/articles/spring-boot-security-authentication-and-authorization/)

2. [【Spring Boot】第12.2課－在 Spring Security 整合資料庫進行認證](https://chikuwacode.github.io/articles/spring-boot-security-authentication-integrating-with-mongodb-database/#%E5%9B%9B%E3%80%81UserDetailsService-%E8%AA%8D%E8%AD%89%E6%9C%8D%E5%8B%99)

3. [【Spring Boot】第12.3課－在 Spring Security 使用 HTTP Basic 認證](https://chikuwacode.github.io/articles/spring-boot-security-http-basic-authentication/)

4. [【Spring Boot】第12.4課－從 Security Context 取得 API 存取方的認證資訊](https://chikuwacode.github.io/articles/spring-boot-security-context-authentication-info/#%E4%BA%94%E3%80%81HTTP-Basic-%E8%AA%8D%E8%AD%89%E7%9A%84%E5%8E%9F%E7%90%86)

5. [【Spring Boot】第12.5課－將 Spring Security 與 JWT 結合，實作登入 API](https://chikuwacode.github.io/articles/spring-boot-security-implement-login-api-with-jwt/)

6. [【Spring Boot】第12.6課－實作 Spring Security 的認證 Filter（以 JWT 為例）](https://chikuwacode.github.io/articles/spring-boot-security-implement-authentication-filter-with-jwt/)