# 407 Trip Calculator

### How to start using any IDE (IntelliJ/Eclipse)

Build the application:

    mvn clean install
    
You can directly run the application by:

    java -jar target\407-trip-calculator-0.0.1-SNAPSHOT.jar
    
### Test the application using postman

Use the following POST request to get the response

    http://localhost:8080/calculate
    
    {
        "startLoc": "QEW",
        "endLoc": "Bronte Road"
    }

Sample Response:

    200 OK
    
    3.5155000000000003
    
### Test using curl command

    curl --location 'http://localhost:8080/calculate' \
    --header 'Content-Type: application/json' \
    --data '{
        "startLoc": "QEW",
        "endLoc": "Bronte Road"
    }'