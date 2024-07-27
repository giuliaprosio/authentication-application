## Spring Boot Application 
An application to connect a web page with login or creation of a new account. 
The data is set up to be connected to a local MySQL database. 

To complete the connection, before building the app (I use IntelliJ), 
go into the folder 

>./src/main/resources/

and create a new file application.properties. 
If you generate the application using spring boot initializer, the file will
be already created. 
In the file, write the following code:
```
spring.application.name=<your_application_name>

### Webpage configuration
spring.thymeleaf.cache=false
spring.thymeleaf.enabled=true
spring.thymeleaf.prefix=classpath:/templates/
spring.thymeleaf.suffix=.html

### DataSource Configuration
spring.datasource.url=jdbc:mysql://localhost:3306/<your_MySql_database>
spring.datasource.username=root
spring.datasource.password=<db_password>
spring.datasource.driver-class-name=com.mysql.jdbc.Driver

### JPA Configuration
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL8Dialect
```