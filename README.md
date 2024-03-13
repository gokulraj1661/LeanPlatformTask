# Consultation Booking System(Lean Platform Technologies BE-Task)

## Introduction

This application provides a platform for users to register as clients or consultants. Clients can search for consultants based on their area of expertise and experience and view available slots for consultation. Consultants can create slots for the upcoming week to indicate their availability for consultation.

## Features

1. **Create User Account:**
   - Users can register themselves by providing details such as name, email, phone number, age, and role.
   - Roles include client or consultant.
   - If a user registers as a consultant, they will provide additional details such as experience and area of expertise.

2. **Slot Creation:**
   - Consultants can create slots for the upcoming week to indicate their availability for consultation.
   - Slots will contain information such as date, start time, end time, and whether the slot is booked or available.

3. **Search a Consultant:**
   - Clients can search for consultants based on their area of expertise and experience.
   - The application will display consultants matching the search criteria.
   - Clients can view available slots for consultation with the selected consultant.
   - Already booked slots will not be visible to clients, ensuring only available slots are shown.
     
## Tables Used
1.Client Table
2.Consultant Table
3.Slot Table

## Technologies Used

- Spring Boot
- Spring Data JPA
- RESTful APIs
- H2 Database (for development)

## EndPoints
### 1. Create User Account

- **URL:** `http://localhost:8087/register`
- **Method:** `POST`
- **Description:** Allows users to register themselves as clients or consultants.
- **Request Body:**
  ```json
  {
    "name": "John Doe",
    "email": "john@example.com",
    "phoneNo": "1234567890",
    "age": 30,
    "role": "client",          // consultant
    "experience": 5,          // Required for consultants
    "areaOfExpertise": "IT"  // Required for consultants
  }
  ```
### 2. Slot Creation

- **URL:** `http://localhost:8087/createslot`
- **Method:** `POST`
- **Description:** Allows consultants to create slots for the upcoming week.
- **Request Body:**
  ```json
  {
  "date": "2024-03-20",    //this formate should be followed
  "startTime": "09:00:00",
  "endTime": "10:00:00",
  "booked": false
  }
  ```
  ### 3.Search a Consultant

- **URL:** `http://localhost:8087/searchconsultant?areaOfExpertise=skin&experience=15`
- **Method:** `GET`
- **Description:** Allows clients to search for consultants based on area of expertise and experience.

  ## Working
  ### Consultant Register
  ![image](https://github.com/gokulraj1661/tanxAPI/assets/90254712/f1e8b938-8871-483c-8377-55ff21752437)

  ### Client Register
  ![image](https://github.com/gokulraj1661/tanxAPI/assets/90254712/d0a074f0-28e1-442e-9cc4-aa5a72dfc383)

  ### Slot Creation 
  ![image](https://github.com/gokulraj1661/tanxAPI/assets/90254712/ae9c6068-e6c1-40ef-b5e3-a301d96810ce)

  ### Get Slot by experiance and areaofexpertise
  ![image](https://github.com/gokulraj1661/tanxAPI/assets/90254712/d2007611-8293-4417-9f8a-83fc1129e8e6)

  In conclusion, the Consultation Booking System is designed to provide a convenient platform for users to connect with consultants for professional consultation services. The system offers features such as user registration, slot creation, and consultant search, allowing clients to find suitable consultants based on their area of expertise and experience and book available consultation slots.



