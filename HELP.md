# Read Me First
The following was discovered as part of building this project:

* The JVM level was changed from '11' to '17', review the [JDK Version Range](https://github.com/spring-projects/spring-framework/wiki/Spring-Framework-Versions#jdk-version-range) on the wiki for more details.

# Getting Started

### Reference Documentation
For further reference, please consider the following sections:

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/3.0.0/maven-plugin/reference/html/)
* [Create an OCI image](https://docs.spring.io/spring-boot/docs/3.0.0/maven-plugin/reference/html/#build-image)
* [Spring Data JPA](https://docs.spring.io/spring-boot/docs/3.0.0/reference/htmlsingle/#data.sql.jpa-and-spring-data)

### Guides
The following guides illustrate how to use some features concretely:

* [Accessing Data with JPA](https://spring.io/guides/gs/accessing-data-jpa/)
* [Accessing data with MySQL](https://spring.io/guides/gs/accessing-data-mysql/)
### The used Database 

MYSQl 

### The sample scripts files names:

db.sql
Patient.sql
Appointment.sql


### The Swagger Link :
http://localhost:8080/swagger-ui/index.html
### The Project APIS
http://localhost:8080/clinic/v1/private/api/patient/add-patient  for adding Patient

http://localhost:8080/clinic/v1/private/api/appointment/add-appointment for creating appointment

http://localhost:8080/clinic/v1/private/api/appointment/cancel/11?reason=Reason  for canceling appointment with a reason

http://localhost:8080/clinic/v1/private/api/appointment/appointments/2022-12-02 searching for appointment by date

http://localhost:8080/clinic/v1/private/api/appointment/filter-by-patientname to filter by patient name

http://localhost:8080/clinic/v1/private/api/appointment/1  for viewing a specific patient appointment 


### The collection file for postman already uploaded with the project

Clinic.postman_collection.json
