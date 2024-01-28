# Interview_SoftEx

Service API for SoftExpert Interview.<br>
The project has Front and Backend<br>

# Requisites To Run

- Java 21
- Node v21.6.1
- Angular v17.1.1

# How to Run
- Run the main java class (InterviewApplication.class)
- Open the Frontend project and in your terminal run ( ng s ) <br>
The application will be working in this URL (http://localhost:8080)

# Endpoint Backend

- POST - http://localhost:8080/api/v1/process ( Send information to calculate )

# Endpoint Fronend

- GET - http://localhost:8080

# Swagger Documentation Endpoint

- http://localhost:8080/softex/swagger-ui/index.html

# Postman File/Collection

- You can use the postman collection called: Interview_SoftExpert.postman_collection.json

# Example of Body Request

- Example Post Client request body (Linux Terminal)
    ```curlrc
        curl http://localhost:8080/sofexpert/process \ 
        --include --header "Content-Type: application/json" \ 
        --request "POST" \
        --data '{ \
             "totalWithoutDiscountOrAdditions": null, \
            "discountInReal": [0.0], \
            "additionsInReal": [], \
            "discountInPercent": [], \
            "additionsInPercent": [], \
            "freight": 0.0, \
            "mapPeople": { \
                "Pablo": [0.0, 0.0], \
                "Eduardo": [0.0, 0.0] \
            }, \
            "receiver": "string" \
            }'
    ```