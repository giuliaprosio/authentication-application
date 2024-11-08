<<<<<<< HEAD
## Authentication Backend Application 
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
The first part of the file concerns the set-up of our database 
and the connection to it 

### JWT Token
In this case, you can see I am directing to a folder in `resources`
called `certs` that contains the files `private.pem` and `public.pem`. 
I have set up the JWT oauth to have asymmetric encryption, which means we have to
have a private key and a public key.
In order to generate the two, I used `OpenSSL`.

To do the same, once created the /cert directory, go into that 
from terminal and run the following commands: 

```
# create rsa key pair
openssl genrsa -out keypair.pem 2048

# extract public key
openssl rsa -in keypair.pem -pubout -out public.pem

# create private key in PKCS#8 format
openssl pkcs8 -topk8 -inform PEM -outform PEM -nocrypt -in keypair.pem -out private.pem
```

### OpenAPI
Since I am using OpenAPI to generate the controllers and dtos, it is 
necessary before running the app to create the classes in `target`. To do so, 
if you use maven
run the command:
```
mvn clean compile
```


=======
# Authentication Frontend Application

This project is a frontend for an authorization and authentication 
application built with **React** and **Vite**. 
React serves as the framework for building the UI, while Vite is 
used for fast building and efficient development. 
The app uses `axios` to manage HTTP requests to the backend server. 

## Dependencies
These are the core libraries that you will need: 
```
npm install axios react react-dom react-router-dom bootstrap
```

Vite needs some additional packages for a complete setup, especially 
to handle JSX and other React-specific configutations. These 
packages are typically automatically added when initializing a project with Vite's React template, but here they are explicitly: 
```
npm install --save-dev vite @vitejs/plugin-react
```
## Available Scripts

In the project directory, you can run:

### `npm run dev`

Runs the app in the development mode.\
Open [http://localhost:5173](http://localhost:5173) to view it in your browser.

### `npm run build`

Builds the app for production, creating an optimized bundle in the `dist` folder.\
The production build is optimized for performance and ready for 
deployment. 

### `npm run serve`
After building, you have two options for serving the app: 
1. **Serve directly from the backend server**: copy the contents of the `dist` folder to the `./resources` directory of the backend app. 
2. **Serve with a separate static server**: in a two-tier architecture (preferred by React), the frontend is served separatedly from the backend. 
To serve the frontend separatedly, a possible solution I offer is: 
    1. Install `serve` if you haven't already: 

        ``` 
        npm install -g serve 
        ```

    2. Run the `npm run serve` command (making sure you have built the project in the `dist` folder). 
The application will be accessible at [http://localhost:3000](http://localhost:3000). 
>>>>>>> ed2642917b192e89109486f4c7d5e7019a461550

