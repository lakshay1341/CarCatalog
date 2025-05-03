# Payment Domain

## Overview

The Payment domain handles all operations related to payment processing in the rental system. This includes creating payments for rentals, storing payment information, and integrating with external payment gateways. The system currently supports credit card payments and has integrations with multiple bank payment processors.

## Entities

- **Payment**: Represents a payment transaction for a rental
- **CreditCard**: Represents a customer's credit card information (can be saved for future use)

## Endpoints

### Get All Payments

Retrieves a list of all payments in the system.

- **URL**: `/api/payments/getAll`
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
          "paymentId": 1,
          "totalAmount": 750.0,
          "paymentDate": "2023-05-01",
          "rentalCarId": 1
        },
        {
          "paymentId": 2,
          "totalAmount": 1000.0,
          "paymentDate": "2023-05-10",
          "rentalCarId": 2
        }
      ]
    }
    ```

### Get Payment By ID

Retrieves detailed information about a specific payment.

- **URL**: `/api/payments/getById`
- **Method**: `GET`
- **Authentication**: Required
- **Query Parameters**:
  - `id`: Payment ID (required)
- **Success Response**:
  - **Code**: 200
  - **Content**:
    ```json
    {
      "success": true,
      "message": "Data listed successfully",
      "data": {
        "paymentId": 1,
        "totalAmount": 750.0,
        "paymentDate": "2023-05-01",
        "rentalCarId": 1,
        "rentalStartDate": "2023-05-01",
        "rentalFinishDate": "2023-05-05",
        "carId": 1,
        "carDailyPrice": 150.0,
        "customerId": 2,
        "customerEmail": "john@example.com"
      }
    }
    ```
- **Error Response**:
  - **Code**: 404
  - **Content**:
    ```json
    {
      "success": false,
      "message": "Payment not found with id: 1",
      "data": null
    }
    ```

### Get Payments By Rental ID

Retrieves all payments for a specific rental.

- **URL**: `/api/payments/getByRentalCarId`
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
      "message": "Payments listed by rental car id: 1",
      "data": [
        {
          "paymentId": 1,
          "totalAmount": 750.0,
          "paymentDate": "2023-05-01"
        },
        {
          "paymentId": 3,
          "totalAmount": 100.0,
          "paymentDate": "2023-05-03"
        }
      ]
    }
    ```

### Make Payment for Individual Customer Rental

Processes a payment for an individual customer's rental.

- **URL**: `/api/payments/makePaymentForIndividualRent`
- **Method**: `POST`
- **Authentication**: Required
- **Request Body**:
  ```json
  {
    "createRentalCarRequest": {
      "startDate": "2023-08-01",
      "finishDate": "2023-08-05",
      "carId": 1,
      "customerId": 2,
      "rentedCityId": 1,
      "deliveredCityId": 1
    },
    "cardNumber": "4111111111111111",
    "cardHolder": "John Doe",
    "expirationDate": "12/25",
    "cvv": "123",
    "saveCard": true
  }
  ```
- **Success Response**:
  - **Code**: 200
  - **Content**:
    ```json
    {
      "success": true,
      "message": "Payment processed successfully",
      "data": {
        "rentalCarId": 5,
        "paymentId": 4,
        "totalAmount": 750.0
      }
    }
    ```
- **Error Response**:
  - **Code**: 400
  - **Content**:
    ```json
    {
      "success": false,
      "message": "Payment failed: Invalid card information",
      "data": null
    }
    ```

### Make Payment for Corporate Customer Rental

Processes a payment for a corporate customer's rental.

- **URL**: `/api/payments/makePaymentForCorporateRent`
- **Method**: `POST`
- **Authentication**: Required
- **Request Body**:
  ```json
  {
    "createRentalCarRequest": {
      "startDate": "2023-08-01",
      "finishDate": "2023-08-05",
      "carId": 1,
      "customerId": 3,
      "rentedCityId": 1,
      "deliveredCityId": 2
    },
    "cardNumber": "5555555555554444",
    "cardHolder": "Acme Corp",
    "expirationDate": "12/25",
    "cvv": "123",
    "saveCard": true
  }
  ```
- **Success Response**:
  - **Code**: 200
  - **Content**:
    ```json
    {
      "success": true,
      "message": "Payment processed successfully",
      "data": {
        "rentalCarId": 6,
        "paymentId": 5,
        "totalAmount": 1500.0
      }
    }
    ```

### Make Payment for Rental Update

Processes a payment for updating an existing rental (e.g., extending rental period).

- **URL**: `/api/payments/makePaymentForRentUpdate`
- **Method**: `POST`
- **Authentication**: Required
- **Request Body**:
  ```json
  {
    "updateRentalCarRequest": {
      "rentalCarId": 1,
      "startDate": "2023-05-01",
      "finishDate": "2023-05-07",
      "carId": 1,
      "customerId": 2,
      "rentedCityId": 1,
      "deliveredCityId": 1
    },
    "cardNumber": "4111111111111111",
    "cardHolder": "John Doe",
    "expirationDate": "12/25",
    "cvv": "123"
  }
  ```
- **Success Response**:
  - **Code**: 200
  - **Content**:
    ```json
    {
      "success": true,
      "message": "Payment processed and rental updated successfully",
      "data": {
        "paymentId": 6,
        "totalAmount": 300.0
      }
    }
    ```

## Credit Card Endpoints

### Get All Credit Cards

Retrieves a list of all saved credit cards for a customer.

- **URL**: `/api/creditCards/getByCustomerId`
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
      "message": "Credit cards listed by customer id: 2",
      "data": [
        {
          "creditCardId": 1,
          "cardNumber": "411111******1111",
          "cardHolder": "John Doe",
          "expirationDate": "12/25",
          "customerId": 2
        },
        {
          "creditCardId": 3,
          "cardNumber": "555555******4444",
          "cardHolder": "John Doe",
          "expirationDate": "10/24",
          "customerId": 2
        }
      ]
    }
    ```

### Add Credit Card

Saves a new credit card for a customer.

- **URL**: `/api/creditCards/add`
- **Method**: `POST`
- **Authentication**: Required
- **Request Body**:
  ```json
  {
    "cardNumber": "4111111111111111",
    "cardHolder": "John Doe",
    "expirationDate": "12/25",
    "cvv": "123",
    "customerId": 2
  }
  ```
- **Success Response**:
  - **Code**: 200
  - **Content**:
    ```json
    {
      "success": true,
      "message": "Credit card added successfully",
      "data": null
    }
    ```
- **Error Response**:
  - **Code**: 400
  - **Content**:
    ```json
    {
      "success": false,
      "message": "Card already exists for this customer",
      "data": null
    }
    ```

### Delete Credit Card

Removes a saved credit card.

- **URL**: `/api/creditCards/delete`
- **Method**: `DELETE`
- **Authentication**: Required
- **Request Body**:
  ```json
  {
    "creditCardId": 1
  }
  ```
- **Success Response**:
  - **Code**: 200
  - **Content**:
    ```json
    {
      "success": true,
      "message": "Credit card deleted successfully",
      "data": null
    }
    ```

## Notes for Frontend Developers

1. **Payment Process Flow**:
   - Create a rental using the Rental domain endpoints
   - Process payment using the appropriate payment endpoint
   - Both operations can be combined using the `makePaymentForIndividualRent` or `makePaymentForCorporateRent` endpoints

2. **Credit Card Security**:
   - Credit card numbers are masked when returned from the API (only first 6 and last 4 digits are visible)
   - CVV is never stored in the database
   - Consider implementing client-side encryption for additional security

3. **Saved Cards**:
   - Customers can save their credit cards for future use by setting `saveCard: true` in payment requests
   - Saved cards can be retrieved and used for future rentals
   - Implement a UI to allow customers to manage their saved cards

4. **Payment Calculation**:
   - The total payment amount is calculated automatically based on:
     - Car daily price × number of rental days
     - Additional services costs
     - Different city fee (if pickup and return cities are different)
   - The frontend does not need to calculate these amounts

5. **Payment Gateways**:
   - The system currently integrates with multiple payment processors (Ziraat Bank, Akbank)
   - The backend automatically selects the appropriate processor

## TODOs

- Implement support for different payment methods (PayPal, Apple Pay, etc.)
- Add support for partial payments and deposits
- Implement payment receipts and invoices
- Add support for refunds and cancellations
- Implement payment notifications (email, SMS)
