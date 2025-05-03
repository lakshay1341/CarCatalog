# Rental Domain

## Overview

The Rental domain handles all operations related to car rentals, including creating new rentals, updating rental information, retrieving rental details, and managing the rental lifecycle (pickup, return, etc.). This domain integrates with several other domains such as Car, Customer, and Payment.

## Entities

- **RentalCar**: Represents a car rental transaction
- **OrderedAdditional**: Represents additional services added to a rental (e.g., GPS, child seat)

## Endpoints

### Get All Rentals

Retrieves a list of all rentals in the system.

- **URL**: `/api/rentalCars/getAll`
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
          "rentalCarId": 1,
          "startDate": "2023-05-01",
          "finishDate": "2023-05-05",
          "returnDate": "2023-05-05",
          "startKilometer": 15000,
          "returnKilometer": 15500,
          "carId": 1,
          "customerId": 2,
          "rentedCityId": 1,
          "deliveredCityId": 1
        },
        {
          "rentalCarId": 2,
          "startDate": "2023-05-10",
          "finishDate": "2023-05-15",
          "returnDate": null,
          "startKilometer": 8000,
          "returnKilometer": null,
          "carId": 2,
          "customerId": 3,
          "rentedCityId": 2,
          "deliveredCityId": 1
        }
      ]
    }
    ```

### Get Rental By ID

Retrieves detailed information about a specific rental.

- **URL**: `/api/rentalCars/getById`
- **Method**: `GET`
- **Authentication**: Required
- **Query Parameters**:
  - `id`: Rental ID (required)
- **Success Response**:
  - **Code**: 200
  - **Content**:
    ```json
    {
      "success": true,
      "message": "Data brought successfully: 1",
      "data": {
        "rentalCarId": 1,
        "startDate": "2023-05-01",
        "finishDate": "2023-05-05",
        "returnDate": "2023-05-05",
        "startKilometer": 15000,
        "returnKilometer": 15500,
        "carId": 1,
        "carBrandName": "Toyota",
        "carColorName": "Red",
        "carDailyPrice": 150.0,
        "customerId": 2,
        "customerEmail": "john@example.com",
        "rentedCityId": 1,
        "rentedCityName": "Istanbul",
        "deliveredCityId": 1,
        "deliveredCityName": "Istanbul"
      }
    }
    ```
- **Error Response**:
  - **Code**: 404
  - **Content**:
    ```json
    {
      "success": false,
      "message": "Rental car not found with id: 1",
      "data": null
    }
    ```

### Get Rentals By Car ID

Retrieves all rentals for a specific car.

- **URL**: `/api/rentalCars/getByCarId`
- **Method**: `GET`
- **Authentication**: Required
- **Query Parameters**:
  - `carId`: Car ID (required)
- **Success Response**:
  - **Code**: 200
  - **Content**:
    ```json
    {
      "success": true,
      "message": "Rental cars listed by car id: 1",
      "data": [
        {
          "rentalCarId": 1,
          "startDate": "2023-05-01",
          "finishDate": "2023-05-05",
          "returnDate": "2023-05-05",
          "customerId": 2,
          "customerEmail": "john@example.com"
        },
        {
          "rentalCarId": 3,
          "startDate": "2023-06-01",
          "finishDate": "2023-06-05",
          "returnDate": null,
          "customerId": 4,
          "customerEmail": "jane@example.com"
        }
      ]
    }
    ```

### Get Rentals By Customer ID

Retrieves all rentals for a specific customer.

- **URL**: `/api/rentalCars/getByCustomerId`
- **Method**: `GET`
- **Authentication**: Required
- **Query Parameters**:
  - `customerId`: Customer ID (required)
- **Success Response**:
  - **Code**: 200
  - **Content**:
    ```json
    {
      "success": true,
      "message": "Rental cars listed by customer id: 2",
      "data": [
        {
          "rentalCarId": 1,
          "startDate": "2023-05-01",
          "finishDate": "2023-05-05",
          "returnDate": "2023-05-05",
          "carId": 1,
          "carBrandName": "Toyota",
          "carColorName": "Red"
        },
        {
          "rentalCarId": 4,
          "startDate": "2023-07-01",
          "finishDate": "2023-07-05",
          "returnDate": null,
          "carId": 3,
          "carBrandName": "Honda",
          "carColorName": "Blue"
        }
      ]
    }
    ```

### Create Rental for Individual Customer

Creates a new rental for an individual customer.

- **URL**: `/api/rentalCars/addForIndividualCustomer`
- **Method**: `POST`
- **Authentication**: Required
- **Request Body**:
  ```json
  {
    "startDate": "2023-08-01",
    "finishDate": "2023-08-05",
    "carId": 1,
    "customerId": 2,
    "rentedCityId": 1,
    "deliveredCityId": 1
  }
  ```
- **Success Response**:
  - **Code**: 200
  - **Content**:
    ```json
    {
      "success": true,
      "message": "Rental car added successfully",
      "data": {
        "rentalCarId": 5
      }
    }
    ```
- **Error Response**:
  - **Code**: 400
  - **Content**:
    ```json
    {
      "success": false,
      "message": "Car is not available for the selected dates",
      "data": null
    }
    ```

### Create Rental for Corporate Customer

Creates a new rental for a corporate customer.

- **URL**: `/api/rentalCars/addForCorporateCustomer`
- **Method**: `POST`
- **Authentication**: Required
- **Request Body**:
  ```json
  {
    "startDate": "2023-08-01",
    "finishDate": "2023-08-05",
    "carId": 1,
    "customerId": 3,
    "rentedCityId": 1,
    "deliveredCityId": 2
  }
  ```
- **Success Response**:
  - **Code**: 200
  - **Content**:
    ```json
    {
      "success": true,
      "message": "Rental car added successfully",
      "data": {
        "rentalCarId": 6
      }
    }
    ```

### Update Rental

Updates an existing rental's information.

- **URL**: `/api/rentalCars/update`
- **Method**: `PUT`
- **Authentication**: Required (Admin only)
- **Request Body**:
  ```json
  {
    "rentalCarId": 1,
    "startDate": "2023-05-01",
    "finishDate": "2023-05-07",
    "carId": 1,
    "customerId": 2,
    "rentedCityId": 1,
    "deliveredCityId": 2
  }
  ```
- **Success Response**:
  - **Code**: 200
  - **Content**:
    ```json
    {
      "success": true,
      "message": "Rental car updated successfully",
      "data": null
    }
    ```
- **Error Response**:
  - **Code**: 404
  - **Content**:
    ```json
    {
      "success": false,
      "message": "Rental car not found with id: 1",
      "data": null
    }
    ```

### Delete Rental

Removes a rental from the system.

- **URL**: `/api/rentalCars/delete`
- **Method**: `DELETE`
- **Authentication**: Required (Admin only)
- **Request Body**:
  ```json
  {
    "rentalCarId": 1
  }
  ```
- **Success Response**:
  - **Code**: 200
  - **Content**:
    ```json
    {
      "success": true,
      "message": "Rental car deleted successfully",
      "data": null
    }
    ```

### Deliver Car (Return)

Records the return of a rented car.

- **URL**: `/api/rentalCars/deliverTheCar`
- **Method**: `PUT`
- **Authentication**: Required (Admin only)
- **Request Body**:
  ```json
  {
    "rentalCarId": 1,
    "returnDate": "2023-05-05",
    "returnKilometer": 15500
  }
  ```
- **Success Response**:
  - **Code**: 200
  - **Content**:
    ```json
    {
      "success": true,
      "message": "Car delivered successfully",
      "data": null
    }
    ```

### Get Car Rental Status

Checks if a car is available for rent during a specific period.

- **URL**: `/api/rentalCars/getCarRentalStatus`
- **Method**: `GET`
- **Authentication**: Required
- **Query Parameters**:
  - `carId`: Car ID (required)
  - `startDate`: Start date (required, format: YYYY-MM-DD)
  - `finishDate`: End date (required, format: YYYY-MM-DD)
- **Success Response**:
  - **Code**: 200
  - **Content**:
    ```json
    {
      "success": true,
      "message": "Car is available for the selected dates",
      "data": {
        "carId": 1,
        "isAvailable": true
      }
    }
    ```
  - **Alternative Response**:
    ```json
    {
      "success": true,
      "message": "Car is not available for the selected dates",
      "data": {
        "carId": 1,
        "isAvailable": false
      }
    }
    ```

## Ordered Additional Endpoints

### Get All Ordered Additionals

Retrieves a list of all additional services ordered with rentals.

- **URL**: `/api/orderedAdditionals/getAll`
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
          "orderedAdditionalId": 1,
          "additionalId": 1,
          "additionalName": "GPS",
          "rentalCarId": 1,
          "amount": 1
        },
        {
          "orderedAdditionalId": 2,
          "additionalId": 2,
          "additionalName": "Child Seat",
          "rentalCarId": 1,
          "amount": 2
        }
      ]
    }
    ```

### Get Ordered Additionals By Rental ID

Retrieves all additional services for a specific rental.

- **URL**: `/api/orderedAdditionals/getByRentalCarId`
- **Method**: `GET`
- **Authentication**: Required
- **Query Parameters**:
  - `rentalCarId`: Rental ID (required)
- **Success Response**:
  - **Code**: 200
  - **Content**:
    ```json
    {
      "success": true,
      "message": "Ordered additionals listed by rental car id: 1",
      "data": [
        {
          "orderedAdditionalId": 1,
          "additionalId": 1,
          "additionalName": "GPS",
          "amount": 1
        },
        {
          "orderedAdditionalId": 2,
          "additionalId": 2,
          "additionalName": "Child Seat",
          "amount": 2
        }
      ]
    }
    ```

### Add Ordered Additional

Adds an additional service to a rental.

- **URL**: `/api/orderedAdditionals/add`
- **Method**: `POST`
- **Authentication**: Required
- **Request Body**:
  ```json
  {
    "rentalCarId": 1,
    "additionalId": 3,
    "amount": 1
  }
  ```
- **Success Response**:
  - **Code**: 200
  - **Content**:
    ```json
    {
      "success": true,
      "message": "Ordered additional added successfully",
      "data": null
    }
    ```
- **Error Response**:
  - **Code**: 400
  - **Content**:
    ```json
    {
      "success": false,
      "message": "Maximum units exceeded for this additional service",
      "data": null
    }
    ```

## Notes for Frontend Developers

1. **Rental Process Flow**:
   - Check car availability using the `getCarRentalStatus` endpoint
   - Create a rental using either `addForIndividualCustomer` or `addForCorporateCustomer`
   - Add any additional services using the `orderedAdditionals/add` endpoint
   - Process payment (see Payment domain documentation)

2. **Date Handling**: All dates should be in ISO format (YYYY-MM-DD). The backend validates that:
   - Start date must be today or in the future
   - Finish date must be after start date
   - Return date (when delivering the car) must be between start and finish dates

3. **City Differences**: If the pickup city (`rentedCityId`) and return city (`deliveredCityId`) are different, an additional fee is applied. This is calculated automatically by the backend.

4. **Rental Status**: A rental can have the following statuses:
   - Active: Car is currently rented (start date has passed, return date is null)
   - Completed: Car has been returned (return date is not null)
   - Upcoming: Rental is in the future (start date is in the future)

5. **Customer Types**: The system supports two types of customers:
   - Individual: Regular personal customers
   - Corporate: Business customers with different pricing and policies

## TODOs

- Add support for rental extensions (extending the finish date)
- Implement rental cancellation functionality
- Add support for damage reporting during car return
- Implement rental history and statistics for customers
- Add support for rental reviews and ratings
