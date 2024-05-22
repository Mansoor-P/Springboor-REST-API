# Major Differences Between JUnit 4 and JUnit 5

## Introduction

This README outlines the key differences between JUnit 4 and JUnit 5. JUnit is a popular testing framework for Java
applications. JUnit 5 is a complete rewrite of JUnit 4 and introduces several new features and improvements.

## Key Differences

### 1. Architecture

| Aspect     | JUnit 4              | JUnit 5                                                                                                                                                                                          |
|------------|----------------------|--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| Structure  | Monolithic framework | Modular architecture                                                                                                                                                                             |
| Components | Single JAR file      | - **JUnit Platform:** Foundation for launching testing frameworks<br> - **JUnit Jupiter:** New programming and extension model<br> - **JUnit Vintage:** Backward compatibility for JUnit 3 and 4 |

### 2. Annotations

| Annotation Type  | JUnit 4        | JUnit 5       |
|------------------|----------------|---------------|
| Before each test | `@Before`      | `@BeforeEach` |
| After each test  | `@After`       | `@AfterEach`  |
| Before all tests | `@BeforeClass` | `@BeforeAll`  |
| After all tests  | `@AfterClass`  | `@AfterAll`   |
| Ignoring tests   | `@Ignore`      | `@Disabled`   |

### 3. Test Method Declaration

| Aspect            | JUnit 4       | JUnit 5                                           |
|-------------------|---------------|---------------------------------------------------|
| Method visibility | `public void` | Can be package-private, return type not mandatory |
| Return type       | `void`        | Can return values                                 |

### 4. Assertions

| Feature | JUnit 4                                  | JUnit 5                                      |
|---------|------------------------------------------|----------------------------------------------|
| Class   | `Assert`                                 | `Assertions`                                 |
| Example | `Assert.assertEquals(expected, actual);` | `Assertions.assertEquals(expected, actual);` |

### 5. Assumptions

| Feature | JUnit 4                         | JUnit 5                              |
|---------|---------------------------------|--------------------------------------|
| Class   | `Assume`                        | `Assumptions`                        |
| Example | `Assume.assumeTrue(condition);` | `Assumptions.assumeTrue(condition);` |

### 6. Dynamic Tests

| Feature | JUnit 4 | JUnit 5                                                                                                                                                                                                                                                                 |
|---------|---------|-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| Support | No      | Yes, using `@TestFactory`                                                                                                                                                                                                                                               |
| Example | N/A     | ```java<br>@TestFactory<br>Collection<DynamicTest> dynamicTests() {<br>    return Arrays.asList(<br>        DynamicTest.dynamicTest("Test 1", () -> assertTrue(true)),<br>        DynamicTest.dynamicTest("Test 2", () -> assertEquals(4, 2 * 2))<br>    );<br>}<br>``` |

- **JUnit 4:** No support for dynamic tests.
- **JUnit 5:** Introduces dynamic tests using `@TestFactory`.
    - Example:
      ```java
      @TestFactory 
      Collection<DynamicTest> dynamicTests() {
          return Arrays.asList(
              DynamicTest.dynamicTest("Test 1", () -> assertTrue(true)),
              DynamicTest.dynamicTest("Test 2", () -> assertEquals(4, 2 * 2))
          );
      }
      ```

### 7. Nested Tests

| Feature | JUnit 4 | JUnit 5                                                                                                                               |
|---------|---------|---------------------------------------------------------------------------------------------------------------------------------------|
| Support | No      | Yes, using `@Nested`                                                                                                                  |
| Example | N/A     | ```java<br>@Nested<br>class InnerClassTest {<br>    @Test<br>    void innerTest() {<br>        assertTrue(true);<br>    }<br>}<br>``` |

- **JUnit 4:** Does not support nested test classes.
- **JUnit 5:** Supports nested test classes using `@Nested` annotation for better organization of tests.
    - Example:
      ```java
      @Nested
      class InnerClassTest {
          @Test
          void innerTest() {
              assertTrue(true);
          }
      }
      ```

### 8. Parameterized Tests

| Feature | JUnit 4                                                                                                                                                                                                                 | JUnit 5                                                                                                                                                                      |
|---------|-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| Support | `@RunWith(Parameterized.class)`                                                                                                                                                                                         | `@ParameterizedTest`, `@ValueSource`, `@CsvSource`, etc.                                                                                                                     |
| Example | ```java<br>@RunWith(Parameterized.class)<br>public class ParameterizedTest {<br>    @Parameter<br>    public int input;<br>    @Test<br>    public void test() {<br>        assertTrue(input > 0);<br>    }<br>}<br>``` | ```java<br>@ParameterizedTest<br>@ValueSource(strings = { "Hello", "JUnit" })<br>void testWithStringParameter(String argument) {<br>    assertNotNull(argument);<br>}<br>``` |

- **JUnit 4:** Supported via `@RunWith(Parameterized.class)`.
- **JUnit 5:** More powerful and flexible parameterized tests using `@ParameterizedTest`, `@ValueSource`, `@CsvSource`,
  etc.
    - Example:
      ```java
      @ParameterizedTest
      @ValueSource(strings = { "Hello", "JUnit" })
      void testWithStringParameter(String argument) {
          assertNotNull(argument);
      }
      ```
