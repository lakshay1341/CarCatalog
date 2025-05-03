# Car Domain

## Overview

The Car domain handles all operations related to vehicle management in the rental system. This includes creating, updating, retrieving, and deleting cars, as well as filtering cars by various criteria such as brand, color, and price.

## Entities

- **Car**: The main entity representing a vehicle available for rent
- **Brand**: Represents car manufacturers (e.g., Toyota, BMW)
- **Color**: Represents car colors (e.g., Red, Blue)

## Endpoints

### Get All Cars

Retrieves a list of all available cars.

- **URL**: `/api/cars/getAll`
- **Method**: `GET`
- **Authentication**: Required
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
          "carId": 1,
          "dailyPrice": 150.0,
          "modelYear": 2022,
          "kilometer": 15000,
          "description": "Fuel efficient sedan",
          "colorName": "Red",
          "brandName": "Toyota"
        },
        {
          "carId": 2,
          "dailyPrice": 200.0,
          "modelYear": 2023,
          "kilometer": 5000,
          "description": "Luxury SUV",
          "colorName": "Black",
          "brandName": "BMW"
        }
      ]
    }
    ```

### Get Car By ID

Retrieves detailed information about a specific car.

- **URL**: `/api/cars/getById`
- **Method**: `GET`
- **Authentication**: Required
- **Query Parameters**:
  - `id`: Car ID (required)
- **Success Response**:
  - **Code**: 200
  - **Content**:
    ```json
    {
      "success": true,
      "message": "Data listed successfully",
      "data": {
        "carId": 1,
        "dailyPrice": 150.0,
        "modelYear": 2022,
        "kilometer": 15000,
        "description": "Fuel efficient sedan",
        "colorName": "Red",
        "brandName": "Toyota"
      }
    }
    ```
- **Error Response**:
  - **Code**: 404
  - **Content**:
    ```json
    {
      "success": false,
      "message": "Car not found with id: 1",
      "data": null
    }
    ```

### Add Car

Creates a new car in the system.

- **URL**: `/api/cars/add`
- **Method**: `POST`
- **Authentication**: Required (Admin only)
- **Request Body**:
  ```json
  {
    "dailyPrice": 150.0,
    "modelYear": 2022,
    "description": "Fuel efficient sedan",
    "kilometer": 15000,
    "brandId": 1,
    "colorId": 2
  }
  ```
- **Success Response**:
  - **Code**: 200
  - **Content**:
    ```json
    {
      "success": true,
      "message": "Car added successfully",
      "data": null
    }
    ```
- **Error Response**:
  - **Code**: 400
  - **Content**:
    ```json
    {
      "success": false,
      "message": "Brand not found with id: 1",
      "data": null
    }
    ```

### Update Car

Updates an existing car's information.

- **URL**: `/api/cars/update`
- **Method**: `PUT`
- **Authentication**: Required (Admin only)
- **Request Body**:
  ```json
  {
    "carId": 1,
    "dailyPrice": 160.0,
    "modelYear": 2022,
    "description": "Updated description",
    "kilometer": 16000,
    "brandId": 1,
    "colorId": 2
  }
  ```
- **Success Response**:
  - **Code**: 200
  - **Content**:
    ```json
    {
      "success": true,
      "message": "Car updated successfully",
      "data": null
    }
    ```
- **Error Response**:
  - **Code**: 404
  - **Content**:
    ```json
    {
      "success": false,
      "message": "Car not found with id: 1",
      "data": null
    }
    ```

### Delete Car

Removes a car from the system.

- **URL**: `/api/cars/delete`
- **Method**: `DELETE`
- **Authentication**: Required (Admin only)
- **Request Body**:
  ```json
  {
    "carId": 1
  }
  ```
- **Success Response**:
  - **Code**: 200
  - **Content**:
    ```json
    {
      "success": true,
      "message": "Car deleted successfully",
      "data": null
    }
    ```
- **Error Response**:
  - **Code**: 400
  - **Content**:
    ```json
    {
      "success": false,
      "message": "Car cannot be deleted because it has active rentals",
      "data": null
    }
    ```

### Get Cars By Brand

Retrieves all cars of a specific brand.

- **URL**: `/api/cars/getByBrandId`
- **Method**: `GET`
- **Authentication**: Required
- **Query Parameters**:
  - `brandId`: Brand ID (required)
- **Success Response**:
  - **Code**: 200
  - **Content**:
    ```json
    {
      "success": true,
      "message": "Cars listed by brand id: 1",
      "data": [
        {
          "carId": 1,
          "dailyPrice": 150.0,
          "modelYear": 2022,
          "kilometer": 15000,
          "colorId": 2,
          "colorName": "Red",
          "brandId": 1,
          "brandName": "Toyota"
        },
        {
          "carId": 3,
          "dailyPrice": 130.0,
          "modelYear": 2021,
          "kilometer": 25000,
          "colorId": 1,
          "colorName": "Blue",
          "brandId": 1,
          "brandName": "Toyota"
        }
      ]
    }
    ```

### Get Cars By Color

Retrieves all cars of a specific color.

- **URL**: `/api/cars/getByColorId`
- **Method**: `GET`
- **Authentication**: Required
- **Query Parameters**:
  - `colorId`: Color ID (required)
- **Success Response**:
  - **Code**: 200
  - **Content**:
    ```json
    {
      "success": true,
      "message": "Cars listed by color id: 2",
      "data": [
        {
          "carId": 1,
          "dailyPrice": 150.0,
          "modelYear": 2022,
          "kilometer": 15000,
          "colorId": 2,
          "colorName": "Red",
          "brandId": 1,
          "brandName": "Toyota"
        },
        {
          "carId": 4,
          "dailyPrice": 180.0,
          "modelYear": 2023,
          "kilometer": 8000,
          "colorId": 2,
          "colorName": "Red",
          "brandId": 2,
          "brandName": "Honda"
        }
      ]
    }
    ```

### Get Cars By Daily Price

Retrieves all cars with a daily price less than or equal to the specified amount.

- **URL**: `/api/cars/getByDailyPrice`
- **Method**: `GET`
- **Authentication**: Required
- **Query Parameters**:
  - `dailyPrice`: Maximum daily price (required)
- **Success Response**:
  - **Code**: 200
  - **Content**:
    ```json
    {
      "success": true,
      "message": "Cars listed by daily price less than or equal to: 150.0",
      "data": [
        {
          "carId": 1,
          "dailyPrice": 150.0,
          "modelYear": 2022,
          "kilometer": 15000,
          "colorName": "Red",
          "brandName": "Toyota"
        },
        {
          "carId": 3,
          "dailyPrice": 130.0,
          "modelYear": 2021,
          "kilometer": 25000,
          "colorName": "Blue",
          "brandName": "Toyota"
        }
      ]
    }
    ```

### Get Paged Cars

Retrieves a paginated list of cars.

- **URL**: `/api/cars/getAllPaged`
- **Method**: `GET`
- **Authentication**: Required
- **Query Parameters**:
  - `pageNo`: Page number (required, starts at 1)
  - `pageSize`: Number of items per page (required)
- **Success Response**:
  - **Code**: 200
  - **Content**:
    ```json
    {
      "success": true,
      "message": "All cars paged",
      "data": [
        {
          "carId": 1,
          "dailyPrice": 150.0,
          "modelYear": 2022,
          "kilometer": 15000,
          "description": "Fuel efficient sedan",
          "colorName": "Red",
          "brandName": "Toyota"
        },
        {
          "carId": 2,
          "dailyPrice": 200.0,
          "modelYear": 2023,
          "kilometer": 5000,
          "description": "Luxury SUV",
          "colorName": "Black",
          "brandName": "BMW"
        }
      ]
    }
    ```

## Notes for Frontend Developers

1. **Car Availability**: The car listing does not automatically filter out cars that are currently rented. You should check the car's status using the rental service if you need to display only available cars.

2. **Image Support**: The current API does not include image URLs for cars. This feature is planned for a future update.

3. **Pricing**: All prices are in the local currency and are per day.

4. **Filtering**: When implementing filtering on the frontend, consider using a combination of the brand, color, and price filter endpoints rather than loading all cars and filtering client-side.

5. **Pagination**: Always use pagination for car listings to improve performance, especially as the database grows.

## TODOs

- Add support for car images
- Implement search by multiple parameters in a single request
- Add full-text search for car descriptions
- Add filtering by availability status
