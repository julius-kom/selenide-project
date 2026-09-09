# SauceDemo UI Test Automation

UI test automation project for SauceDemo built with Java, Selenide and JUnit.

## Tech Stack

- Java 21
- Selenide
- JUnit 6
- Maven
- AssertJ
- Allure Report
- GitHub Actions

## Test Coverage

The project currently covers:

- Successful login
- Negative login
- Product list validation
- Product sorting by name
- Product sorting by price
- Adding a product to the cart
- Successful checkout flow
- Checkout validation errors
- Logout

## Test Environment

Tests run against the public SauceDemo test website.

The credentials used in the tests are public test credentials provided by SauceDemo and do not contain any sensitive data.

## Project Structure

- `pages` — Page Object classes
- `tests` — UI tests and base test configuration
- `models` — data models
- `data` — test data
- `config` — project configuration
- `extensions` — JUnit extensions

## Running Tests

Run all tests:

```bash
mvn test
```

Run tests in headless mode:

```bash
mvn test -Dselenide.headless=true
```

## CI

GitHub Actions runs the UI test suite automatically on every push.

The workflow can also be started manually.

Tests are executed in headless mode, and Allure results are uploaded as an artifact after every run, including failed runs.

Screenshots are automatically attached to Allure results when a test fails.