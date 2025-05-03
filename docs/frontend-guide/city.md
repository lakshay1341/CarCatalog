# City Domain

## Overview

The City domain handles operations related to locations where cars can be rented from and returned to. Cities are used to track the pickup and drop-off locations for rentals and may affect pricing if a car is returned to a different city than where it was picked up.

## Entities

- **City**: Represents a city where rental operations can take place

## Endpoints

### Get All Cities

Retrieves a list of all available cities.

- **URL**: `/api/cities/getAll`
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
          "cityId": 1,
          "cityName": "Istanbul"
        },
        {
          "cityId": 2,
          "cityName": "Ankara"
        },
        {
          "cityId": 3,
          "cityName": "Izmir"
        }
      ]
    }
    ```

### Get City By ID

Retrieves information about a specific city.

- **URL**: `/api/cities/getById`
- **Method**: `GET`
- **Authentication**: Required
- **Query Parameters**:
  - `id`: City ID (required)
- **Success Response**:
  - **Code**: 200
  - **Content**:
    ```json
    {
      "success": true,
      "message": "Data listed successfully",
      "data": {
        "cityId": 1,
        "cityName": "Istanbul"
      }
    }
    ```
- **Error Response**:
  - **Code**: 404
  - **Content**:
    ```json
    {
      "success": false,
      "message": "City not found with id: 1",
      "data": null
    }
    ```

### Add City

Creates a new city in the system.

- **URL**: `/api/cities/add`
- **Method**: `POST`
- **Authentication**: Required (Admin only)
- **Request Body**:
  ```json
  {
    "cityName": "Antalya"
  }
  ```
- **Success Response**:
  - **Code**: 200
  - **Content**:
    ```json
    {
      "success": true,
      "message": "City added successfully",
      "data": null
    }
    ```
- **Error Response**:
  - **Code**: 400
  - **Content**:
    ```json
    {
      "success": false,
      "message": "City already exists with name: Antalya",
      "data": null
    }
    ```

### Update City

Updates an existing city's information.

- **URL**: `/api/cities/update`
- **Method**: `PUT`
- **Authentication**: Required (Admin only)
- **Request Body**:
  ```json
  {
    "cityId": 1,
    "cityName": "Istanbul Metropolitan"
  }
  ```
- **Success Response**:
  - **Code**: 200
  - **Content**:
    ```json
    {
      "success": true,
      "message": "City updated successfully",
      "data": null
    }
    ```
- **Error Response**:
  - **Code**: 404
  - **Content**:
    ```json
    {
      "success": false,
      "message": "City not found with id: 1",
      "data": null
    }
    ```

### Delete City

Removes a city from the system.

- **URL**: `/api/cities/delete`
- **Method**: `DELETE`
- **Authentication**: Required (Admin only)
- **Request Body**:
  ```json
  {
    "cityId": 1
  }
  ```
- **Success Response**:
  - **Code**: 200
  - **Content**:
    ```json
    {
      "success": true,
      "message": "City deleted successfully",
      "data": null
    }
    ```
- **Error Response**:
  - **Code**: 400
  - **Content**:
    ```json
    {
      "success": false,
      "message": "City cannot be deleted because it is used in active rentals",
      "data": null
    }
    ```

## Notes for Frontend Developers

1. **City Usage in Rentals**:
   - Cities are used to specify pickup and return locations for car rentals
   - The rental form should include dropdown selectors for both pickup and return cities
   - If the return city is different from the pickup city, an additional fee is applied

2. **Different City Fee**:
   - When a car is returned to a different city than where it was picked up, an additional fee is applied
   - This fee is currently fixed at 750 units of currency
   - The frontend should display this fee when a different return city is selected

3. **City Selection UI**:
   - Cities should be presented in dropdown menus in the rental creation form
   - Consider sorting cities alphabetically for better user experience
   - The most popular cities could be displayed at the top of the list

4. **Validation**:
   - City names must be unique in the system
   - The frontend should validate that a city name is not empty before submitting

5. **Admin Operations**:
   - Only administrators should have access to add, update, or delete cities
   - Regular users should only be able to view cities and select them for rentals

## TODOs

- Add support for city coordinates (latitude/longitude)
- Implement city search functionality
- Add support for branch locations within cities
- Implement city availability (some cities might not have cars available)
- Add support for city-specific pricing (some cities might have higher base prices)
