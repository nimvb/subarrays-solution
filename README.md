# How To Compile And Run

Assuming that maven is already installed, to compile and make `jar` version of the program run the command below

````
./mvnw package
````

To run the application from maven, execute the following command:

````
./mvnw spring-boot:run
````

Note that the default running port for the application is `8080`

To access swagger page, browse to the `/swagger-ui.html` path