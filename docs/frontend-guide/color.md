# Color Domain

## Overview

The Color domain handles operations related to car colors (e.g., Red, Blue, Black). Colors are used to categorize cars and allow users to filter vehicles by their exterior color.

## Endpoints

### Get All Colors

Retrieves a list of all available car colors.

- **URL**: `/api/colors/getAll`
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
          "colorId": 1,
          "colorName": "Red"
        },
        {
          "colorId": 2,
          "colorName": "Blue"
        },
        {
          "colorId": 3,
          "colorName": "Black"
        },
        {
          "colorId": 4,
          "colorName": "White"
        }
      ]
    }
    ```

### Get Color By ID

Retrieves information about a specific color.

- **URL**: `/api/colors/getById`
- **Method**: `GET`
- **Authentication**: Required
- **Query Parameters**:
  - `id`: Color ID (required)
- **Success Response**:
  - **Code**: 200
  - **Content**:
    ```json
    {
      "success": true,
      "message": "Data listed successfully",
      "data": {
        "colorId": 1,
        "colorName": "Red"
      }
    }
    ```
- **Error Response**:
  - **Code**: 404
  - **Content**:
    ```json
    {
      "success": false,
      "message": "Color not found with id: 1",
      "data": null
    }
    ```

### Add Color

Creates a new car color in the system.

- **URL**: `/api/colors/add`
- **Method**: `POST`
- **Authentication**: Required (Admin only)
- **Request Body**:
  ```json
  {
    "colorName": "Silver"
  }
  ```
- **Success Response**:
  - **Code**: 200
  - **Content**:
    ```json
    {
      "success": true,
      "message": "Color added successfully",
      "data": null
    }
    ```
- **Error Response**:
  - **Code**: 400
  - **Content**:
    ```json
    {
      "success": false,
      "message": "Color already exists with name: Silver",
      "data": null
    }
    ```

### Update Color

Updates an existing color's information.

- **URL**: `/api/colors/update`
- **Method**: `PUT`
- **Authentication**: Required (Admin only)
- **Request Body**:
  ```json
  {
    "colorId": 1,
    "colorName": "Bright Red"
  }
  ```
- **Success Response**:
  - **Code**: 200
  - **Content**:
    ```json
    {
      "success": true,
      "message": "Color updated successfully",
      "data": null
    }
    ```
- **Error Response**:
  - **Code**: 404
  - **Content**:
    ```json
    {
      "success": false,
      "message": "Color not found with id: 1",
      "data": null
    }
    ```

### Delete Color

Removes a color from the system.

- **URL**: `/api/colors/delete`
- **Method**: `DELETE`
- **Authentication**: Required (Admin only)
- **Request Body**:
  ```json
  {
    "colorId": 1
  }
  ```
- **Success Response**:
  - **Code**: 200
  - **Content**:
    ```json
    {
      "success": true,
      "message": "Color deleted successfully",
      "data": null
    }
    ```
- **Error Response**:
  - **Code**: 400
  - **Content**:
    ```json
    {
      "success": false,
      "message": "Color cannot be deleted because it is used by existing cars",
      "data": null
    }
    ```

## Notes for Frontend Developers

1. **Color Usage**: Colors are primarily used for categorizing and filtering cars. They should be presented in dropdown menus or filter options in the car search interface.

2. **Validation**: Color names must be unique in the system. The frontend should validate that a color name is not empty and meets the minimum length requirement (3 characters) before submitting.

3. **Admin Operations**: Only administrators should have access to add, update, or delete colors. Regular users should only be able to view colors.

4. **Visual Representation**: Consider using color swatches or indicators alongside the color names in the UI to provide visual cues to users.

5. **Sorting**: When displaying colors in a dropdown, consider sorting them alphabetically by name for better user experience.

## TODOs

- Add support for color hex codes to display actual color swatches in the UI
- Implement pagination for colors when the list grows large
- Add the ability to get cars by color in a single request
- Consider adding color categories (e.g., metallic, matte) for better filtering
