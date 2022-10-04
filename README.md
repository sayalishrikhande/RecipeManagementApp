# RecipeManagementApp
 Recipe Management App
 An app which allows users to manage their recipes. The app has implementation of create, read, update, delete. Also it allows user to search based on one or more criteria. The application also has authentcation implemented using JWT.

Technologies and libraries used : - Spring Boot MVC - Spring Data - H2 DB - JWT - Mockito - Junit - Swagger - JAVA 1.8 - Maven, Git

Design Principle and Pattern used In Project : SOLID design pattern is used. S - Single responsibility principle. Service and repository having their own responsibility. O - Open close principle. Reposirtoy class are open for extension and closed for modifications I - Interface Segregation Principle . Separated out interface for Service as well repository D - Dependency Inversion . Spring By default following this. Classes should depend upon interfaces or abstract classes instead of concrete classes and functions.

Database selection thoughts : For the given use case I have used H2 database as it is an embeded relational database which requires no separate configuration for spring boot. Also it is fast being an in memory databse and is ready to use by adding the h2 database dependency.

You can access and clone the code repository using the git command under a directory: git clone https://github.com/sayalishrikhande/RecipeManagementApp.git

Once the application starts up successfully you can access the below mentioned resources:

To check the health of the application - http://localhost:8080/actuator/health
Swagger UI for API documentation & endpoints http://localhost:8080/swagger-ui.html
REST APIs: The REST APIs will return proper response code in the case of success or errorneous cases. Following are the HTTP response codes returned from the APIs. 200 - OK 201 - CREATED 204 - NO_CONTENT 400 - BAD_REQUEST 404 - NOT_FOUND 500 - INTERNAL_SERVER_ERROR

Swagger UI Integrated: Url to access - http://localhost:8080/swagger-ui.html

Few of the enhancement thoughts:
User management
Code style and formatter checks
Quality checks
