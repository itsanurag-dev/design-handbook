# ADR 0001: Standardize Testing Framework on JUnit 5 Jupiter

## Status
Accepted

## Context
The legacy repository contained only one test class written in JUnit 4 (`org.junit.Test`). Modern Java testing demands parameterization, dynamic tests, structured execution, and superior assertion engines (AssertJ).

## Decision
Upgrade the test suite to JUnit 5 (`junit-jupiter`), eliminate all JUnit 4 dependencies, and pair tests with AssertJ for readable assertions.

## Consequences
- **Positive**: Native support for `@DisplayName`, `@Nested` suites, `@Timeout` for concurrency safety, and `@ParameterizedTest`.
- **Negative**: Legacy tests require namespace updates (`org.junit.Test` to `org.junit.jupiter.api.Test`).
