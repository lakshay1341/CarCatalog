# Brand Domain

## Overview

The Brand domain handles operations related to car manufacturers (e.g., Toyota, BMW, Honda). Brands are used to categorize cars and allow users to filter vehicles by manufacturer.

## Endpoints

### Get All Brands

Retrieves a list of all available car brands.

- **URL**: `/api/brands/getAll`
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
          "brandId": 1,
          "brandName": "Toyota"
        },
        {
          "brandId": 2,
          "brandName": "BMW"
        },
        {
          "brandId": 3,
          "brandName": "Honda"
        }
      ]
    }
    ```

### Get Brand By ID

Retrieves information about a specific brand.

- **URL**: `/api/brands/getById`
- **Method**: `GET`
- **Authentication**: Required
- **Query Parameters**:
  - `id`: Brand ID (required)
- **Success Response**:
  - **Code**: 200
  - **Content**:
    ```json
    {
      "success": true,
      "message": "Data listed successfully",
      "data": {
        "brandId": 1,
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
      "message": "Brand not found with id: 1",
      "data": null
    }
    ```

### Add Brand

Creates a new car brand in the system.

- **URL**: `/api/brands/add`
- **Method**: `POST`
- **Authentication**: Required (Admin only)
- **Request Body**:
  ```json
  {
    "brandName": "Mercedes"
  }
  ```
- **Success Response**:
  - **Code**: 200
  - **Content**:
    ```json
    {
      "success": true,
      "message": "Brand added successfully",
      "data": null
    }
    ```
- **Error Response**:
  - **Code**: 400
  - **Content**:
    ```json
    {
      "success": false,
      "message": "Brand already exists with name: Mercedes",
      "data": null
    }
    ```

### Update Brand

Updates an existing brand's information.

- **URL**: `/api/brands/update`
- **Method**: `PUT`
- **Authentication**: Required (Admin only)
- **Request Body**:
  ```json
  {
    "brandId": 1,
    "brandName": "Toyota Motors"
  }
  ```
- **Success Response**:
  - **Code**: 200
  - **Content**:
    ```json
    {
      "success": true,
      "message": "Brand updated successfully",
      "data": null
    }
    ```
- **Error Response**:
  - **Code**: 404
  - **Content**:
    ```json
    {
      "success": false,
      "message": "Brand not found with id: 1",
      "data": null
    }
    ```

### Delete Brand

Removes a brand from the system.

- **URL**: `/api/brands/delete`
- **Method**: `DELETE`
- **Authentication**: Required (Admin only)
- **Request Body**:
  ```json
  {
    "brandId": 1
  }
  ```
- **Success Response**:
  - **Code**: 200
  - **Content**:
    ```json
    {
      "success": true,
      "message": "Brand deleted successfully",
      "data": null
    }
    ```
- **Error Response**:
  - **Code**: 400
  - **Content**:
    ```json
    {
      "success": false,
      "message": "Brand cannot be deleted because it is used by existing cars",
      "data": null
    }
    ```

## Notes for Frontend Developers

1. **Brand Usage**: Brands are primarily used for categorizing and filtering cars. They should be presented in dropdown menus or filter options in the car search interface.

2. **Validation**: Brand names must be unique in the system. The frontend should validate that a brand name is not empty and meets the minimum length requirement (3 characters) before submitting.

3. **Admin Operations**: Only administrators should have access to add, update, or delete brands. Regular users should only be able to view brands.

4. **Relationship with Cars**: A brand can have multiple cars associated with it. When displaying brand details, you might want to show the number of available cars for that brand.

5. **Sorting**: When displaying brands in a dropdown, consider sorting them alphabetically by name for better user experience.

## TODOs

- Add support for brand logos/images
- Implement pagination for brands when the list grows large
- Add the ability to get cars by brand in a single request
- Consider adding brand categories (e.g., luxury, economy) for better filtering
