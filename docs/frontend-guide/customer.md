# Customer Domain

## Overview

The Customer domain handles all operations related to customer management in the rental system. This includes both individual customers (persons) and corporate customers (companies). Customers are users who can rent cars and have specific information associated with their accounts.

## Entities

- **Customer**: Base entity for all customer types
- **IndividualCustomer**: Represents a person who can rent cars
- **CorporateCustomer**: Represents a company that can rent cars

## Endpoints

### Get All Customers

Retrieves a list of all customers (both individual and corporate).

- **URL**: `/api/customers/getAll`
- **Method**: `GET`
- **Authentication**: Required (Admin only)
- **Query Parameters**:
  - None
- **Success Response**:
  - **Code**: 200
  - **Content**:
    ```json
    {
      "success": true,
      "message": "Data listed successfully",
      "data": [
        {
          "userId": 1,
          "email": "john@example.com",
          "customerId": 1
        },
        {
          "userId": 2,
          "email": "acme@example.com",
          "customerId": 2
        }
      ]
    }
    ```

### Get Customer By ID

Retrieves detailed information about a specific customer.

- **URL**: `/api/customers/getById`
- **Method**: `GET`
- **Authentication**: Required
- **Query Parameters**:
  - `id`: Customer ID (required)
- **Success Response**:
  - **Code**: 200
  - **Content**:
    ```json
    {
      "success": true,
      "message": "Data listed successfully",
      "data": {
        "userId": 1,
        "email": "john@example.com",
        "customerId": 1,
        "registrationDate": "2023-01-15"
      }
    }
    ```
- **Error Response**:
  - **Code**: 404
  - **Content**:
    ```json
    {
      "success": false,
      "message": "Customer not found with id: 1",
      "data": null
    }
    ```

## Individual Customer Endpoints

### Get All Individual Customers

Retrieves a list of all individual customers.

- **URL**: `/api/individualCustomers/getAll`
- **Method**: `GET`
- **Authentication**: Required (Admin only)
- **Query Parameters**:
  - None
- **Success Response**:
  - **Code**: 200
  - **Content**:
    ```json
    {
      "success": true,
      "message": "Data listed successfully",
      "data": [
        {
          "userId": 1,
          "email": "john@example.com",
          "customerId": 1,
          "individualCustomerId": 1,
          "firstName": "John",
          "lastName": "Doe",
          "nationalIdentity": "12345678901"
        },
        {
          "userId": 3,
          "email": "jane@example.com",
          "customerId": 3,
          "individualCustomerId": 2,
          "firstName": "Jane",
          "lastName": "Smith",
          "nationalIdentity": "98765432109"
        }
      ]
    }
    ```

### Get Individual Customer By ID

Retrieves detailed information about a specific individual customer.

- **URL**: `/api/individualCustomers/getById`
- **Method**: `GET`
- **Authentication**: Required
- **Query Parameters**:
  - `id`: Individual Customer ID (required)
- **Success Response**:
  - **Code**: 200
  - **Content**:
    ```json
    {
      "success": true,
      "message": "Data listed successfully",
      "data": {
        "userId": 1,
        "email": "john@example.com",
        "customerId": 1,
        "individualCustomerId": 1,
        "firstName": "John",
        "lastName": "Doe",
        "nationalIdentity": "12345678901",
        "registrationDate": "2023-01-15"
      }
    }
    ```
- **Error Response**:
  - **Code**: 404
  - **Content**:
    ```json
    {
      "success": false,
      "message": "Individual customer not found with id: 1",
      "data": null
    }
    ```

### Add Individual Customer

Creates a new individual customer in the system.

- **URL**: `/api/individualCustomers/add`
- **Method**: `POST`
- **Authentication**: Not required (public registration)
- **Request Body**:
  ```json
  {
    "email": "alex@example.com",
    "password": "password123",
    "firstName": "Alex",
    "lastName": "Johnson",
    "nationalIdentity": "45678901234"
  }
  ```
- **Success Response**:
  - **Code**: 200
  - **Content**:
    ```json
    {
      "success": true,
      "message": "Individual customer added successfully",
      "data": null
    }
    ```
- **Error Response**:
  - **Code**: 400
  - **Content**:
    ```json
    {
      "success": false,
      "message": "Email already exists",
      "data": null
    }
    ```
  - **Alternative Error**:
    ```json
    {
      "success": false,
      "message": "National identity already exists",
      "data": null
    }
    ```

### Update Individual Customer

Updates an existing individual customer's information.

- **URL**: `/api/individualCustomers/update`
- **Method**: `PUT`
- **Authentication**: Required
- **Request Body**:
  ```json
  {
    "individualCustomerId": 1,
    "userId": 1,
    "email": "john.doe@example.com",
    "password": "newpassword123",
    "firstName": "John",
    "lastName": "Doe",
    "nationalIdentity": "12345678901"
  }
  ```
- **Success Response**:
  - **Code**: 200
  - **Content**:
    ```json
    {
      "success": true,
      "message": "Individual customer updated successfully",
      "data": null
    }
    ```
- **Error Response**:
  - **Code**: 404
  - **Content**:
    ```json
    {
      "success": false,
      "message": "Individual customer not found with id: 1",
      "data": null
    }
    ```

### Delete Individual Customer

Removes an individual customer from the system.

- **URL**: `/api/individualCustomers/delete`
- **Method**: `DELETE`
- **Authentication**: Required
- **Request Body**:
  ```json
  {
    "individualCustomerId": 1
  }
  ```
- **Success Response**:
  - **Code**: 200
  - **Content**:
    ```json
    {
      "success": true,
      "message": "Individual customer deleted successfully",
      "data": null
    }
    ```
- **Error Response**:
  - **Code**: 400
  - **Content**:
    ```json
    {
      "success": false,
      "message": "Customer cannot be deleted because they have active rentals",
      "data": null
    }
    ```

## Corporate Customer Endpoints

### Get All Corporate Customers

Retrieves a list of all corporate customers.

- **URL**: `/api/corporateCustomers/getAll`
- **Method**: `GET`
- **Authentication**: Required (Admin only)
- **Query Parameters**:
  - None
- **Success Response**:
  - **Code**: 200
  - **Content**:
    ```json
    {
      "success": true,
      "message": "Data listed successfully",
      "data": [
        {
          "userId": 2,
          "email": "acme@example.com",
          "customerId": 2,
          "corporateCustomerId": 1,
          "companyName": "Acme Corporation",
          "taxNumber": "1234567890"
        },
        {
          "userId": 4,
          "email": "globex@example.com",
          "customerId": 4,
          "corporateCustomerId": 2,
          "companyName": "Globex Corporation",
          "taxNumber": "0987654321"
        }
      ]
    }
    ```

### Get Corporate Customer By ID

Retrieves detailed information about a specific corporate customer.

- **URL**: `/api/corporateCustomers/getById`
- **Method**: `GET`
- **Authentication**: Required
- **Query Parameters**:
  - `id`: Corporate Customer ID (required)
- **Success Response**:
  - **Code**: 200
  - **Content**:
    ```json
    {
      "success": true,
      "message": "Data listed successfully",
      "data": {
        "userId": 2,
        "email": "acme@example.com",
        "customerId": 2,
        "corporateCustomerId": 1,
        "companyName": "Acme Corporation",
        "taxNumber": "1234567890",
        "registrationDate": "2023-01-20"
      }
    }
    ```
- **Error Response**:
  - **Code**: 404
  - **Content**:
    ```json
    {
      "success": false,
      "message": "Corporate customer not found with id: 1",
      "data": null
    }
    ```

### Add Corporate Customer

Creates a new corporate customer in the system.

- **URL**: `/api/corporateCustomers/add`
- **Method**: `POST`
- **Authentication**: Not required (public registration)
- **Request Body**:
  ```json
  {
    "email": "wayne@example.com",
    "password": "password123",
    "companyName": "Wayne Enterprises",
    "taxNumber": "5678901234"
  }
  ```
- **Success Response**:
  - **Code**: 200
  - **Content**:
    ```json
    {
      "success": true,
      "message": "Corporate customer added successfully",
      "data": null
    }
    ```
- **Error Response**:
  - **Code**: 400
  - **Content**:
    ```json
    {
      "success": false,
      "message": "Email already exists",
      "data": null
    }
    ```
  - **Alternative Error**:
    ```json
    {
      "success": false,
      "message": "Tax number already exists",
      "data": null
    }
    ```

### Update Corporate Customer

Updates an existing corporate customer's information.

- **URL**: `/api/corporateCustomers/update`
- **Method**: `PUT`
- **Authentication**: Required
- **Request Body**:
  ```json
  {
    "corporateCustomerId": 1,
    "userId": 2,
    "email": "acme.corp@example.com",
    "password": "newpassword123",
    "companyName": "Acme Corporation",
    "taxNumber": "1234567890"
  }
  ```
- **Success Response**:
  - **Code**: 200
  - **Content**:
    ```json
    {
      "success": true,
      "message": "Corporate customer updated successfully",
      "data": null
    }
    ```
- **Error Response**:
  - **Code**: 404
  - **Content**:
    ```json
    {
      "success": false,
      "message": "Corporate customer not found with id: 1",
      "data": null
    }
    ```

### Delete Corporate Customer

Removes a corporate customer from the system.

- **URL**: `/api/corporateCustomers/delete`
- **Method**: `DELETE`
- **Authentication**: Required
- **Request Body**:
  ```json
  {
    "corporateCustomerId": 1
  }
  ```
- **Success Response**:
  - **Code**: 200
  - **Content**:
    ```json
    {
      "success": true,
      "message": "Corporate customer deleted successfully",
      "data": null
    }
    ```
- **Error Response**:
  - **Code**: 400
  - **Content**:
    ```json
    {
      "success": false,
      "message": "Customer cannot be deleted because they have active rentals",
      "data": null
    }
    ```

## Notes for Frontend Developers

1. **Customer Types**: The system supports two types of customers:
   - Individual: Regular personal customers with first name, last name, and national identity
   - Corporate: Business customers with company name and tax number

2. **Registration Flow**:
   - Users can register as either individual or corporate customers
   - Email addresses must be unique across all users
   - National identity numbers must be unique for individual customers
   - Tax numbers must be unique for corporate customers

3. **Authentication**: After registration, customers need to log in using their email and password (see User domain documentation).

4. **Validation**:
   - Email must be in valid format
   - Password must be at least 3 characters (should be stronger in production)
   - National identity must be exactly 11 digits
   - Tax number must be exactly 10 digits

5. **Customer Profile**: Consider implementing a profile page where customers can view and update their information.

## TODOs

- Add support for customer profile pictures
- Implement address information for customers
- Add support for customer verification (email verification, phone verification)
- Implement customer loyalty program
- Add support for customer preferences (preferred car types, etc.)
