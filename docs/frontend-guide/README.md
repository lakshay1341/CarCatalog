# Frontend Integration Guide

## Purpose

This directory contains documentation specifically designed to help frontend developers integrate with our backend APIs. Each file provides detailed information about a specific domain or feature in our system.

## How to Use This Guide

1. Start by understanding the overall architecture in this README
2. Navigate to the specific domain documentation you're working with (e.g., `car.md`, `user.md`)
3. Each domain file contains:
   - Summary of what the domain handles
   - Available endpoints with methods, paths, and descriptions
   - Sample request and response bodies
   - Authentication requirements
   - Notes and assumptions for frontend implementation
   - TODOs if features are incomplete

## API Structure

Our backend follows a domain-driven design with RESTful APIs organized by business domain. Each domain has its own set of endpoints that follow these conventions:

- Base URL: `/api/{domain}`
- Standard HTTP methods:
  - GET: Retrieve resources
  - POST: Create resources
  - PUT: Update resources
  - DELETE: Remove resources
- Authentication: Most endpoints require authentication via JWT token in the Authorization header
- Response format: All responses follow a standard format:
  ```json
  {
    "success": true/false,
    "message": "Operation successful/error message",
    "data": { ... } // Optional, contains the actual response data
  }
  ```

## Domains

The system is divided into the following domains:

- **Car**: Vehicle management (cars, brands, colors)
- **User**: User account management
- **Customer**: Customer information (individual and corporate)
- **Rental**: Car rental operations
- **Payment**: Payment processing
- **Additional**: Additional services/features for rentals
- **City**: Location management

## Common Patterns

- All list endpoints support pagination with `page` and `size` query parameters
- Many endpoints support filtering with query parameters
- Error responses include detailed validation messages
- Dates should be formatted as ISO-8601 (YYYY-MM-DD)

## Getting Started

For new frontend developers, we recommend starting with:

1. Review the authentication process in `user.md`
2. Explore the basic car listing functionality in `car.md`
3. Understand the rental process in `rental.md`

## Need Help?

If you find missing or unclear information in these guides, please contact the backend team or submit an issue in our project management system.
