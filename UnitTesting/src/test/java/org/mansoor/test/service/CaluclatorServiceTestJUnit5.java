package org.mansoor.test.service;

import org.junit.jupiter.api.*;

public class CaluclatorServiceTestJUnit5 {
    @BeforeAll
    public static void init() {
        System.out.println("Thisis is Single Time Logic");
    }

    @BeforeEach
    public void middle() {
        System.out.println("This is BeforeEach");
    }

    @Test
    public void addTwoNumbers() {
        System.out.println("First Case");

        int actualResult = CaluclatorService.addTwoNumbers(2, 4);
        int expectedResult = 6;

        Assertions.assertEquals(expectedResult, actualResult, "Test Failed");
    }


    @Test
    @Disabled
    public void addAnyNumbers() {
        System.out.println("Second Case");

        int result = CaluclatorService.sumAnyNumbers(12, 3, 5);

        int expectedResult = 20;

        Assertions.assertEquals(expectedResult, result);
    }

    @AfterEach
    public void cleanUpAfterEach() {

        System.out.println("This is After Each");

    }

    @AfterAll
    public static void cleanUp() {

        System.out.println("This is After all");

    }
}
