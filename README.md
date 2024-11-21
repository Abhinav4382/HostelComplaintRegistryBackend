# Hostel Complaint Registry Backend

## Overview

The Hostel Complaint Registry Backend is a Spring Boot-based project that manages user and complaint-related operations. It provides RESTful endpoints for user management, authentication, and complaint handling.

## Technologies Used

- Java
- Spring Boot
- Spring Security
- MongoDB
- Maven

## Project Structure

- `controllers`: Contains the REST controllers for handling HTTP requests.
- `entity`: Contains the entity classes representing the data model.
- `repository`: Contains the repository interfaces for data access.
- `service`: Contains the service classes for business logic.
- `utilis`: Contains utility classes, such as JWT utility.

## Endpoints

### User Endpoints

- **Get All Users**
  - `GET /user/all-users`
  - Returns a list of all users.

- **Get User Role**
  - `GET /user/role`
  - Returns the role of the user based on the JWT token.

- **Get Hostel ID**
  - `GET /user/hostel-id`
  - Returns the user details based on the JWT token.

- **Update User**
  - `PUT /user/update`
  - Updates the details of the currently authenticated user.

- **Delete User**
  - `DELETE /user/delete`
  - Deletes the currently authenticated user.

### Complaint Endpoints

- **Add Complaint**
  - `POST /complaint`
  - Adds a new complaint.

- **Get Complaint by ID**
  - `GET /complaint/{ComplaintId}`
  - Returns a complaint by its ID.

- **Get Complaints by JWT**
  - `GET /complaint`
  - Returns complaints for the user based on the JWT token.

- **Update Complaint Status**
  - `PUT /complaint/update/{id}`
  - Updates the status of a complaint.

- **Delete Complaint**
  - `DELETE /complaint/{ComplaintId}`
  - Deletes a complaint by its ID.

## Setup and Installation

1. Clone the repository:
   ```sh
   git clone https://github.com/Abhinav4382/HostelApplication.git
