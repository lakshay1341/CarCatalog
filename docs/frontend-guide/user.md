# User Domain

## Overview

The User domain handles all operations related to user management in the rental system. This includes user authentication, registration, profile management, and authorization. Users are the base entity for customers (both individual and corporate) and administrators.

## Entities

- **User**: Base entity for all users in the system

## Endpoints

### Get All Users

Retrieves a list of all users in the system.

- **URL**: `/api/users/getAll`
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
          "email": "john@example.com"
        },
        {
          "userId": 2,
          "email": "acme@example.com"
        },
        {
          "userId": 3,
          "email": "admin@example.com"
        }
      ]
    }
    ```

### Get User By ID

Retrieves detailed information about a specific user.

- **URL**: `/api/users/getById`
- **Method**: `GET`
- **Authentication**: Required
- **Query Parameters**:
  - `id`: User ID (required)
- **Success Response**:
  - **Code**: 200
  - **Content**:
    ```json
    {
      "success": true,
      "message": "Data listed successfully",
      "data": {
        "userId": 1,
        "email": "john@example.com"
      }
    }
    ```
- **Error Response**:
  - **Code**: 404
  - **Content**:
    ```json
    {
      "success": false,
      "message": "User not found with id: 1",
      "data": null
    }
    ```

### Get User By Email

Retrieves a user by their email address.

- **URL**: `/api/users/getByEmail`
- **Method**: `GET`
- **Authentication**: Required
- **Query Parameters**:
  - `email`: Email address (required)
- **Success Response**:
  - **Code**: 200
  - **Content**:
    ```json
    {
      "success": true,
      "message": "Data listed successfully",
      "data": {
        "userId": 1,
        "email": "john@example.com"
      }
    }
    ```
- **Error Response**:
  - **Code**: 404
  - **Content**:
    ```json
    {
      "success": false,
      "message": "User not found with email: john@example.com",
      "data": null
    }
    ```

### Add User

Creates a new user in the system.

- **URL**: `/api/users/add`
- **Method**: `POST`
- **Authentication**: Not required (public registration)
- **Request Body**:
  ```json
  {
    "email": "newuser@example.com",
    "password": "password123"
  }
  ```
- **Success Response**:
  - **Code**: 200
  - **Content**:
    ```json
    {
      "success": true,
      "message": "User added successfully",
      "data": {
        "userId": 4
      }
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

### Update User

Updates an existing user's information.

- **URL**: `/api/users/update`
- **Method**: `PUT`
- **Authentication**: Required
- **Request Body**:
  ```json
  {
    "userId": 1,
    "email": "john.doe@example.com",
    "password": "newpassword123"
  }
  ```
- **Success Response**:
  - **Code**: 200
  - **Content**:
    ```json
    {
      "success": true,
      "message": "User updated successfully",
      "data": null
    }
    ```
- **Error Response**:
  - **Code**: 404
  - **Content**:
    ```json
    {
      "success": false,
      "message": "User not found with id: 1",
      "data": null
    }
    ```

### Delete User

Removes a user from the system.

- **URL**: `/api/users/delete`
- **Method**: `DELETE`
- **Authentication**: Required
- **Request Body**:
  ```json
  {
    "userId": 1
  }
  ```
- **Success Response**:
  - **Code**: 200
  - **Content**:
    ```json
    {
      "success": true,
      "message": "User deleted successfully",
      "data": null
    }
    ```
- **Error Response**:
  - **Code**: 400
  - **Content**:
    ```json
    {
      "success": false,
      "message": "User cannot be deleted because they have associated data",
      "data": null
    }
    ```

### Login

Authenticates a user and returns a JWT token.

- **URL**: `/api/auth/login`
- **Method**: `POST`
- **Authentication**: Not required
- **Request Body**:
  ```json
  {
    "email": "john@example.com",
    "password": "password123"
  }
  ```
- **Success Response**:
  - **Code**: 200
  - **Content**:
    ```json
    {
      "success": true,
      "message": "Login successful",
      "data": {
        "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...",
        "userId": 1,
        "email": "john@example.com",
        "roles": ["CUSTOMER"]
      }
    }
    ```
- **Error Response**:
  - **Code**: 401
  - **Content**:
    ```json
    {
      "success": false,
      "message": "Invalid email or password",
      "data": null
    }
    ```

### Register

Registers a new user in the system.

- **URL**: `/api/auth/register`
- **Method**: `POST`
- **Authentication**: Not required
- **Request Body**:
  ```json
  {
    "email": "newuser@example.com",
    "password": "password123",
    "confirmPassword": "password123"
  }
  ```
- **Success Response**:
  - **Code**: 200
  - **Content**:
    ```json
    {
      "success": true,
      "message": "Registration successful",
      "data": {
        "userId": 4,
        "email": "newuser@example.com"
      }
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
      "message": "Passwords do not match",
      "data": null
    }
    ```

## Notes for Frontend Developers

1. **Authentication Flow**:
   - Users register using the `/api/auth/register` endpoint
   - Users login using the `/api/auth/login` endpoint
   - The login endpoint returns a JWT token that should be included in the `Authorization` header for all authenticated requests
   - Format: `Authorization: Bearer {token}`

2. **User Types**:
   - Regular users (customers) can register through the public registration endpoints
   - Admin users are typically created by other admins or directly in the database
   - The system distinguishes between individual customers, corporate customers, and administrators

3. **Password Security**:
   - Passwords are stored securely using bcrypt hashing
   - The frontend should enforce password strength requirements (minimum length, complexity)
   - Consider implementing password confirmation fields in registration forms

4. **Email Validation**:
   - Emails must be unique in the system
   - The backend validates email format, but the frontend should also implement basic validation

5. **Authorization**:
   - Different endpoints require different authorization levels
   - Admin-only endpoints will return a 403 Forbidden error if accessed by regular users
   - Users can only access their own data (except admins who can access all data)

## TODOs

- Implement email verification for new registrations
- Add support for password reset functionality
- Implement multi-factor authentication
- Add support for social login (Google, Facebook, etc.)
- Implement user roles and permissions system
