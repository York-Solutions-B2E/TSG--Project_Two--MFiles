# README

## Project Status

- Project Status: **Incomplete**
- Outstanding Functionality:
  - Spring:
    - OAuth / OIDC Configuration
    - JWT (for OAuth)
  - React:
    - General:
      - Protected routes
      - Connect to API (it is currently fetching a locally-stored JSON file)
      - Displaying user name (the intent was to pull it from the HTTP Header as Spring is posting to a custom header)
    - Login Screen:
      - OAuth functionality
    - Claims List:
      - *Reactified* form-functionality
      - Proper pagination functionality (needs the Claim Details)
    - Claim Detail:
      - All (i.e., build-out, data, etc.)

## Setup / Run Instructions

Given that the project-functionality is incomplete, I did not manage to *Dockerize* the code. The following steps should provide a general process:

1. Clone the entire repo
2. PostgreSQL
  - Create the database (`membership_dashboard` in this situation)
  - Create a user (**or** use the default user credentials)
  - Update the *.env* file in Spring for the applicable credentials
3. Spring
  - Sync. the project with Maven (IDE-specific steps not included)
  - Compile and run the app (as normal)
  - Using Postman the end-points are as follows:

**Member Dashboard**
```
- GET - localhost:8080/api/dashboard
-- NOTES: Nothing needs to be passed as OAuth is not currently working.
```

**Claims List - 01**
```
- POST - localhost:8080/api/claims
-- EX: Specific Search
{
    "pageNumber": 0,
    "pageSize": 10,
    "claimNumber": "#C-10310",
    "serviceStartDate": "2025-08-22",
    "serviceEndDate": "2025-01-21",
    "name": "River Clinic",
    "statusList": ["PROCESSED"]
}
```

**Claims List - 02**
```
- POST - localhost:8080/api/claims
-- EX: Multiple-Results Search
{
    "pageNumber": 0,
    "pageSize": 10,
    "claimNumber": "",
    "serviceStartDate": "",
    "serviceEndDate": "",
    "name": "",
    "statusList": ["SUBMITTED", "IN_REVIEW", "PROCESSED", "PAID", "DENIED"]
}
```

**Claims List - 03**
```
- POST - localhost:8080/api/claims
-- EX: Default-Values Search
{
    "pageNumber": 0,
    "pageSize": null,
    "claimNumber": "",
    "serviceStartDate": "",
    "serviceEndDate": "",
    "name": "",
    "statusList": []
}
```

**Claim Details**
```
- GET - localhost:8080/api/claims/C-10300
-- NOTES: Nothing needs to be passed as OAuth is not currently working.
-- NOTES: This should get the Claim Details associated with #C-10300
```

4. React
  - Run `npm install` to pull in all dependencies
  - Run `npm run dev` to initialize the dev environment


**Note:**
- Make sure to start the Homebrew service with terminal **prior to** running the Java project
- Once the database is created, Spring will auto-seed the data using the *data.sql* file
- Spring is set-up to use `create-drop` meaning that all tables are created at boot then dropped at program-termination
- *.env* is structured as follows:

```.env
# Database Credentials
POSTGRES_HOST=localhost
POSTGRES_PORT=5432
POSTGRES_USER=...
POSTGRES_PASSWORD=...
POSTGRES_DB=membership_dashboard
POSTGRES_URL=jdbc:postgresql://localhost:5432/membership_dashboard

# Spring Security overrides
SSECURITY_USER=...
SSECURITY_PASS=...
```

- Spring Security is disabled in the *application.properties* file as I did not get that part completed
