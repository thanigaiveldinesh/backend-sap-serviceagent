<div align="center">
<h1 align="center">:boom: Service Agent SAP Backend :boom:</h1>
</div>

This Spring Boot application provides APIs to manage jobs. It allows creating, updating, deleting, and fetching jobs, including scheduling and tracking their execution status.

## Table of Contents
- [Requirements](#requirements)
- [Installation and Build the Project,Running the Backend](#installation-and-build-the-project-running-the-backend)
- [Run the Backend](#run-the-backend)
- [Testing](#testing)


## Requirements

- Java 17
- Maven 3.8 or later
- IDE (optional, e.g., IntelliJ IDEA, Eclipse)

## Installation and Build the Project, Running the Backend

1. **Clone the Repository**:

   git clone https://github.com/thanigaiveldinesh/backend-sap-serviceagent.git
   
   cd backend-sap-serviceagent
   
2. **Build the project**:
   
    mvn clean package

3. **Run the Backend**:

   Navigate to the target folder

   java -jar target/serviceagent-0.0.1-SNAPSHOT.jar

   Note: Ensure the JAR file is present in the target folder and use the exact name of the generated JAR file when running the application.

   Backend should be started with localhost:80 port.

   
## Testing

To run the unit tests, use:

**mvn test**

The application includes unit tests that verify the functionality of the Service Agent SAP Backend.



