# API Test Automation Framework for an E-Commerce Web Application

![Java](https://img.shields.io/badge/Java-007396?style=flat&logo=openjdk&logoColor=white)
![Maven](https://img.shields.io/badge/Maven-C71A36?style=flat&logo=apachemaven&logoColor=white)
![Rest Assured](https://img.shields.io/badge/Rest%20Assured-API%20Testing-brightgreen?style=flat)
![TestNG](https://img.shields.io/badge/TestNG-Test%20Runner-orange?style=flat)
![Allure](https://img.shields.io/badge/Allure-Reporting-purple?style=flat)

An API test automation framework built with **Java, Rest Assured and TestNG** to test the public REST APIs of the e-commerce website [automationexercise.com](https://automationexercise.com).
It covers all **14 documented APIs** (8 positive and 6 negative scenarios) and uses JSON test data, externalized configuration, serialization/deserialization of request and response bodies, and Allure reporting.

---

## Table of Contents

- [Project Resources](#project-resources)
- [Demo](#demo)
- [Screenshots](#screenshots)
- [Tech Stack](#tech-stack)
- [Key Design Decisions](#key-design-decisions)
- [APIs Covered](#apis-covered)
- [API Breakdown](#api-breakdown)
- [Project Structure](#project-structure)
- [Framework Layers](#framework-layers)
- [Configuration](#configuration)
- [Getting Started](#getting-started)
- [Reporting](#reporting)
- [Author](#author)

---

## Project Resources

| Resource | Link |
|---|---|
| API documentation | [automationexercise.com/api_list](https://www.automationexercise.com/api_list) |
| Manual API testing (Postman collection and documentation) | [API Manual Testing – GitHub repository](https://github.com/ahmedayman2001/API-Manual-Testing-E-Commerce-Web-Application-automationexercise.com-) |

---

## Demo

▶️ **[Watch the project demo video](https://drive.google.com/file/d/1U6XeQPSPQ-lStFCGCD6LmeZVsrEvA6Hp/view?usp=sharing)**: running the suite from the TestNG XML files and viewing the Allure report.

---

## Screenshots

[![A1.png](https://i.postimg.cc/zBSFgkhZ/A1.png)](https://postimg.cc/S2R90CVD)

[![A2.png](https://i.postimg.cc/vHSLfT4v/A2.png)](https://postimg.cc/4mV9TJbK)

---

## Tech Stack

| Area | Technology |
|---|---|
| Language | Java |
| Build tool | Maven |
| API testing | Rest Assured |
| Test runner | TestNG (suites defined in XML) |
| Assertions | Hamcrest matchers, TestNG `Assert` and `SoftAssert` |
| Test data | JSON files, read with Gson |
| Serialization / deserialization | POJOs for request and response bodies, Jackson `ObjectMapper` for request conversion |
| Configuration | `.properties` file (base domain and endpoint paths) |
| Reporting | Allure Report |
| Logging | Custom TestNG listener (`ITestListener`) |

---

## Key Design Decisions

Testing this API required handling a few behaviors specific to the site. The framework is built around them.

### 1. Request bodies are `application/x-www-form-urlencoded`
Create, login, delete, update and search requests are not sent as JSON. Request POJOs (for example `RegisterUser`, `ValidLogin`) are loaded from JSON files, converted to a map with Jackson, and sent with Rest Assured's `formParams(...)`.

```java
Map<String, Object> formData = new ObjectMapper()
        .convertValue(createdUser, new TypeReference<Map<String, Object>>() {});

given()
    .baseUri(Domain)
    .formParams(formData)
.when()
    .post(CreateAccount)
.then()
    .assertThat().body("responseCode", equalTo(201),
                       "message", containsString("User created!"));
```

### 2. Responses are `text/html` but the body is JSON
The server returns JSON content with a `Content-Type` of `text/html`, so Rest Assured would not parse it by default. The framework registers a JSON parser for that content type, which makes JSONPath assertions and POJO deserialization work:

```java
RestAssured.registerParser("text/html", Parser.JSON);
```

### 3. Every API returns HTTP `200 OK`
The website always answers with HTTP status `200`, and the real result is inside the response body as `responseCode`. For this reason every test validates **both** layers:

- the HTTP status code (`200`), and
- the `responseCode` and `message` fields in the response body (for example `201`, `400`, `404`, `405`).

This is how negative scenarios (missing parameter, unsupported method, wrong credentials) are verified.

### 4. Serialization and deserialization
- **Request side:** JSON test data is deserialized into request POJOs (`RequestBodyMessages` package), modified at runtime (for example with random data), then serialized into form parameters.
- **Response side:** responses are deserialized into response POJOs (`ResponseBodyMessages` package) such as `UserDetailsResponse` and `LoginWithoutEmail`, then asserted on typed fields.

---

## APIs Covered

All 14 APIs from the [official API list](https://www.automationexercise.com/api_list) are automated.

| # | Method | Endpoint | Scenario | Expected `responseCode` |
|---|---|---|---|---|
| 1 | GET | `/api/productsList` | Get all products list | 200 |
| 2 | POST | `/api/productsList` | Unsupported method | 405 |
| 3 | GET | `/api/brandsList` | Get all brands list | 200 |
| 4 | PUT | `/api/brandsList` | Unsupported method | 405 |
| 5 | POST | `/api/searchProduct` | Search product with `search_product` | 200 |
| 6 | POST | `/api/searchProduct` | Search without `search_product` | 400 |
| 7 | POST | `/api/createAccount` | Create/register a user account | 201 |
| 8 | POST | `/api/verifyLogin` | Login with valid details | 200 |
| 9 | POST | `/api/verifyLogin` | Login without email parameter | 400 |
| 10 | DELETE | `/api/verifyLogin` | Unsupported method | 405 |
| 11 | DELETE | `/api/deleteAccount` | Delete a newly created account | 200 |
| 12 | PUT | `/api/updateAccount` | Update user account | 200 |
| 13 | GET | `/api/getUserDetailByEmail` | Get user details by email | 200 |
| 14 | POST | `/api/verifyLogin` | Login with invalid details | 404 |

### What each test validates
- HTTP status code is `200`
- Response `Content-Type` and non-empty body
- `responseCode` and `message` in the body
- Response time under 3 seconds
- Data integrity for list endpoints (non-empty lists, mandatory fields, price format, unique IDs)
- Sensitive data is not exposed (for example, no password in the user details response)

---

## API Breakdown

The 14 APIs are organized into two test classes and grouped by feature, so each class owns one area of the application.

### By test class and feature

| Test class | Feature (Allure) | APIs | Positive | Negative |
|---|---|---|---|---|
| `Products` | Products List | 1, 2 | 1 | 1 |
| `Products` | Brands List | 3, 4 | 1 | 1 |
| `Products` | Search Product | 5, 6 | 1 | 1 |
| `UserAccount` | Create / Register Account | 7 | 1 | 0 |
| `UserAccount` | Login (verifyLogin) | 8, 9, 10, 14 | 1 | 3 |
| `UserAccount` | Delete Account | 11 | 1 | 0 |
| `UserAccount` | Update Account | 12 | 1 | 0 |
| `UserAccount` | Get User Details by Email | 13 | 1 | 0 |
| | **Total** | **14** | **8** | **6** |

- **`Products`** (APIs 1–6) covers the catalog: products, brands and search. These tests need no login or account setup.
- **`UserAccount`** (APIs 7–14) covers the user lifecycle: create, login, update, retrieve and delete an account.

### By HTTP method

| Method | APIs | Count |
|---|---|---|
| `GET` | 1, 3, 13 | 3 |
| `POST` | 2, 5, 6, 7, 8, 9, 14 | 7 |
| `PUT` | 4, 12 | 2 |
| `DELETE` | 10, 11 | 2 |

### By scenario type

| Type | APIs | What is verified |
|---|---|---|
| Positive | 1, 3, 5, 7, 8, 11, 12, 13 | Correct data and success message (`200` / `201`) |
| Negative: unsupported method | 2, 4, 10 | `responseCode` `405` with "This request method is not supported." |
| Negative: missing parameter | 6, 9 | `responseCode` `400` with a "parameter is missing" message |
| Negative: invalid data | 14 | `responseCode` `404` with "User not found!" |

---

## Project Structure

```text
api_project
├── src
│   ├── main/java
│   │   ├── MainPack                 # Scratch / entry point
│   │   ├── RequestBodyMessages      # Request POJOs (RegisterUser, ValidLogin, UpdateUserAccount, ...)
│   │   ├── ResponseBodyMessages     # Response POJOs (UserDetailsResponse, LoginWithoutEmail, ...)
│   │   ├── TestData                 # JSON test data + RandomDataGenerator
│   │   └── Utils                    # HelperClass (JSON reader), PropertiesReader
│   └── test/java
│       ├── MyListeners              # CustomListener (TestNG ITestListener)
│       ├── Properties               # environment.properties
│       └── Tests                    # Products, UserAccount
├── allure-results                   # Allure raw results
├── master_testng.xml                # Master suite
├── testng1.xml                      # Suite 1
├── testng2.xml                      # Suite 2
└── pom.xml
```

---

## Framework Layers

| Layer | Responsibility |
|---|---|
| **Tests** (`Tests`) | Test classes `Products` (APIs 1–6) and `UserAccount` (APIs 7–14), each test annotated with Allure metadata |
| **Request models** (`RequestBodyMessages`) | POJOs that describe what is sent to the API |
| **Response models** (`ResponseBodyMessages`) | POJOs that describe what the API returns |
| **Test data** (`TestData`) | JSON payloads for registration, login (valid and invalid), update, delete and user lookup; `RandomDataGenerator` produces unique names, emails, passwords and cities at runtime |
| **Utilities** (`Utils`) | `HelperClass.readJson(...)`, a generic Gson reader for JSON files; `PropertiesReader` for configuration values |
| **Listeners** (`MyListeners`) | `CustomListener` logs test start, success, failure and skip events |
| **Suites** | TestNG XML files group and run the test classes |

### Highlights
- **Data-driven login tests** with TestNG `@DataProvider` reading JSON arrays.
- **Group-scoped setup:** `@BeforeMethod(onlyForGroups = ...)` prepares a fresh random user only for the tests that need one.
- **Unique test users:** registration data is generated at runtime so repeated runs do not clash with "email already exists".
- **Soft assertions** for profile-field validation, so all mismatches are reported together.
- **Failure diagnostics:** `log().ifValidationFails()` prints the request and response only when a validation fails.

---

## Configuration

Base domain and endpoint paths are kept out of the test code in `src/test/java/Properties/environment.properties`:

```properties
Domain = https://automationexercise.com
ProductsList = /api/productsList
BrandList = /api/brandsList
SearchProduct = /api/searchProduct
CreateAccount = /api/createAccount
Login = /api/verifyLogin
DeleteAccount = /api/deleteAccount
UpdateAccount = /api/updateAccount
UserDetails = /api/getUserDetailByEmail
```

Values are read with `PropertiesReader.get(path, key)`, which fails fast with a clear message if the file or key is missing.

---

## Getting Started

### Prerequisites
- Java JDK
- Maven
- [Allure Commandline](https://allurereport.org/docs/install/) (to open the report locally)
- Internet access (the tests call the live website)

### Clone and run

```bash
git clone <repository-url>
cd api_project

# Run the tests
mvn clean test
```

Suites can also be run directly from the IDE by right-clicking `master_testng.xml`, `testng1.xml` or `testng2.xml` and choosing **Run**.

> Run commands from the project root, because test data and configuration files are read by relative path.

---

## Reporting

The framework uses **Allure**. Each test carries `@Epic`, `@Feature`, `@Story`, `@Severity`, `@Owner`, `@Description` and `@Link` annotations (links to the API documentation and the Postman collection), so the report is organized by feature and severity.

```bash
# Generate and open the report from the raw results
allure serve allure-results
```

---

## Author

**Ahmed Ayman**
Software QA Engineer | Manual, API & Automation Testing | Selenium & Rest Assured | ECPC Finalist | Competitive Programmer

GitHub: [ahmedayman2001](https://github.com/ahmedayman2001)
