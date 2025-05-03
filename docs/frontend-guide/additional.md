# Additional Services Domain

## Overview

The Additional Services domain handles operations related to extra services or features that can be added to car rentals. These include items like GPS navigation, child seats, Wi-Fi hotspots, and other optional add-ons that customers can select to enhance their rental experience.

## Entities

- **Additional**: Represents an additional service that can be added to rentals
- **OrderedAdditional**: Represents an additional service that has been added to a specific rental

## Endpoints

### Get All Additional Services

Retrieves a list of all available additional services.

- **URL**: `/api/additionals/getAll`
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
          "additionalId": 1,
          "additionalName": "GPS Navigation",
          "additionalDailyPrice": 10.0
        },
        {
          "additionalId": 2,
          "additionalName": "Child Seat",
          "additionalDailyPrice": 5.0
        },
        {
          "additionalId": 3,
          "additionalName": "Wi-Fi Hotspot",
          "additionalDailyPrice": 8.0
        }
      ]
    }
    ```

### Get Additional Service By ID

Retrieves information about a specific additional service.

- **URL**: `/api/additionals/getById`
- **Method**: `GET`
- **Authentication**: Required
- **Query Parameters**:
  - `id`: Additional ID (required)
- **Success Response**:
  - **Code**: 200
  - **Content**:
    ```json
    {
      "success": true,
      "message": "Data listed successfully",
      "data": {
        "additionalId": 1,
        "additionalName": "GPS Navigation",
        "additionalDailyPrice": 10.0,
        "maxUnitsPerRental": 1
      }
    }
    ```
- **Error Response**:
  - **Code**: 404
  - **Content**:
    ```json
    {
      "success": false,
      "message": "Additional service not found with id: 1",
      "data": null
    }
    ```

### Add Additional Service

Creates a new additional service in the system.

- **URL**: `/api/additionals/add`
- **Method**: `POST`
- **Authentication**: Required (Admin only)
- **Request Body**:
  ```json
  {
    "additionalName": "Premium Sound System",
    "additionalDailyPrice": 15.0,
    "maxUnitsPerRental": 1
  }
  ```
- **Success Response**:
  - **Code**: 200
  - **Content**:
    ```json
    {
      "success": true,
      "message": "Additional service added successfully",
      "data": null
    }
    ```
- **Error Response**:
  - **Code**: 400
  - **Content**:
    ```json
    {
      "success": false,
      "message": "Additional service already exists with name: Premium Sound System",
      "data": null
    }
    ```

### Update Additional Service

Updates an existing additional service's information.

- **URL**: `/api/additionals/update`
- **Method**: `PUT`
- **Authentication**: Required (Admin only)
- **Request Body**:
  ```json
  {
    "additionalId": 1,
    "additionalName": "GPS Navigation System",
    "additionalDailyPrice": 12.0,
    "maxUnitsPerRental": 1
  }
  ```
- **Success Response**:
  - **Code**: 200
  - **Content**:
    ```json
    {
      "success": true,
      "message": "Additional service updated successfully",
      "data": null
    }
    ```
- **Error Response**:
  - **Code**: 404
  - **Content**:
    ```json
    {
      "success": false,
      "message": "Additional service not found with id: 1",
      "data": null
    }
    ```

### Delete Additional Service

Removes an additional service from the system.

- **URL**: `/api/additionals/delete`
- **Method**: `DELETE`
- **Authentication**: Required (Admin only)
- **Request Body**:
  ```json
  {
    "additionalId": 1
  }
  ```
- **Success Response**:
  - **Code**: 200
  - **Content**:
    ```json
    {
      "success": true,
      "message": "Additional service deleted successfully",
      "data": null
    }
    ```
- **Error Response**:
  - **Code**: 400
  - **Content**:
    ```json
    {
      "success": false,
      "message": "Additional service cannot be deleted because it is used in active rentals",
      "data": null
    }
    ```

## Ordered Additional Endpoints

### Get All Ordered Additionals

Retrieves a list of all additional services that have been ordered with rentals.

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
          "additionalName": "GPS Navigation",
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

Retrieves all additional services ordered for a specific rental.

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
          "additionalName": "GPS Navigation",
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

### Get Ordered Additionals By Additional ID

Retrieves all orders for a specific additional service.

- **URL**: `/api/orderedAdditionals/getByAdditionalId`
- **Method**: `GET`
- **Authentication**: Required (Admin only)
- **Query Parameters**:
  - `additionalId`: Additional ID (required)
- **Success Response**:
  - **Code**: 200
  - **Content**:
    ```json
    {
      "success": true,
      "message": "Ordered additionals listed by additional id: 1",
      "data": [
        {
          "orderedAdditionalId": 1,
          "rentalCarId": 1,
          "amount": 1
        },
        {
          "orderedAdditionalId": 3,
          "rentalCarId": 3,
          "amount": 1
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

### Update Ordered Additional

Updates the quantity of an additional service in a rental.

- **URL**: `/api/orderedAdditionals/update`
- **Method**: `PUT`
- **Authentication**: Required
- **Request Body**:
  ```json
  {
    "orderedAdditionalId": 2,
    "rentalCarId": 1,
    "additionalId": 2,
    "amount": 3
  }
  ```
- **Success Response**:
  - **Code**: 200
  - **Content**:
    ```json
    {
      "success": true,
      "message": "Ordered additional updated successfully",
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

### Delete Ordered Additional

Removes an additional service from a rental.

- **URL**: `/api/orderedAdditionals/delete`
- **Method**: `DELETE`
- **Authentication**: Required
- **Request Body**:
  ```json
  {
    "orderedAdditionalId": 1
  }
  ```
- **Success Response**:
  - **Code**: 200
  - **Content**:
    ```json
    {
      "success": true,
      "message": "Ordered additional deleted successfully",
      "data": null
    }
    ```

## Notes for Frontend Developers

1. **Additional Services UI**:
   - Additional services should be presented as options during the rental checkout process
   - Each service should display its name, daily price, and maximum quantity
   - Implement quantity selectors for services that allow multiple units (e.g., child seats)

2. **Pricing Calculation**:
   - The total cost of additional services is calculated as:
     - Service daily price × number of rental days × quantity
   - This calculation is handled by the backend, but the frontend should display the breakdown

3. **Validation**:
   - Each additional service has a maximum number of units that can be added to a rental
   - The frontend should enforce these limits in the UI
   - For example, if a GPS has a max of 1, the quantity selector should be disabled after selecting 1

4. **Rental Flow Integration**:
   - Additional services can be added during initial rental creation
   - They can also be added or modified later (before the rental starts)
   - Consider implementing a separate step in the checkout flow for selecting additional services

5. **Admin Operations**:
   - Only administrators can add, update, or delete additional service types
   - Regular users can only add services to their rentals

## TODOs

- Add support for images for additional services
- Implement promotional bundles (combinations of services at a discount)
- Add support for time-limited promotions on additional services
- Implement availability tracking for services with limited inventory
- Add support for service categories (e.g., comfort, safety, entertainment)
