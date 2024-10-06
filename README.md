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

### DataSource Configuration
spring.datasource.url=jdbc:mysql://localhost:3306/<your_MySql_database>
spring.datasource.username=root
spring.datasource.password=<db_password>

### JPA Configuration
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL8Dialect

### JWT token 
rsa.private-key=classpath:certs/private.pem
rsa.public-key=classpath:certs/public.pem
```

### DataSource Configuration and JPA Configuration
The first part of the file concerns the set up of our database 
and the connection to it 

### JWT Token
In this case, you can see I am directing to a folder in `resources`
called `certs` that contains the files `private.pem` and `public.pem`. 
I have set up the JWT oauth to have asymmetrics encryption, which means we have to
have a private key and a public key.
In order to generate the two, I used `OpenSSL`.

Once created the /cert directory, go into that 
from terminal and run the following commands: 

```
# create rsa key pair
openssl genrsa -out keypair.pem 2048

# extract public key
openssl rsa -in keypair.pem -pubout -out public.pem

# create private key in PKCS#8 format
openssl pkcs8 -topk8 -inform PEM -outform PEM -nocrypt -in keypair.pem -out private.pem
```

