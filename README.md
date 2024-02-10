# Spring Boot Product Management System

This project is a simple Spring Boot application for managing products. It includes CRUD operations for products using a RESTful API.

## Table of Contents

- [Technologies Used](#technologies-used)
- [Setup](#setup)
- [Usage](#usage)
- [Endpoints](#endpoints)
- [Sample Requests](#sample-requests)
- [Contributing](#contributing)
- [License](#license)

## Technologies Used

- Java 17
- Spring Boot
- Lombok


## Setup

1. Clone the repository:

    ```bash
    git clone https://github.com/faseelathayattuchira/Cloudbees.git
    ```

2. Navigate to the project directory:

    ```bash
    cd Cloudbees
    ```

3. Build the project:

    ```bash
    ./mvnw clean install
    ```

4. Run the application:

    ```bash
    ./mvnw spring-boot:run
    ```

The application will start on [http://localhost:8080](http://localhost:8080).

## Usage

Describe how users can use your application. Provide any additional setup if needed.

## Endpoints

- **GET /products**: Retrieve all products.
- **GET /products/{id}**: Retrieve a product by ID.
- **POST /products**: Add a new product.
- **PUT /products/{id}**: Update an existing product.
- **DELETE /products/{id}**: Delete a product by ID.


## Sample Requests



### list all Products
curl --location 'http://localhost:8080/product'

### get Product by id
curl --location 'http://localhost:8080/product/1'

### Add a Product
curl --location 'http://localhost:8080/product' \
--header 'Content-Type: application/json' \
--data '{
    "name": "name1",
    "description": "description1",
    "price": 100,
    "quantityAvailable": 100,
    "tax": 0.0,
    "discount": 10.0
}'

### Update a Product
curl --location --request PUT 'http://localhost:8080/product/1' \
--header 'Content-Type: application/json' \
--data '{
    "price":100,
    "quantityAvailable":100
   
}'


### Delete a Product
curl --location --request DELETE 'http://localhost:8080/product/2' \
--header 'Content-Type: application/json'
