# booker
Book management and purchasing platform, 
integrated with payment gateway
## Installation
make sure you have postgres database named bookstoredb ,by default its using username postgres , password postgres
to change the database user credentials, make sure that these properties reflect the changes  in the application properties file:
spring.datasource.username=postgres
spring.datasource.password=postgres
Run the command: 
java -jar booker-core.jar

## Assumptions made
there is a book purchasing and reservation mechanism thats used to dispatch the purchased books to the users

