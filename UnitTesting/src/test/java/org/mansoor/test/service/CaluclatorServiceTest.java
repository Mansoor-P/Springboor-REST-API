//package org.mansoor.test.service;
//
//import org.junit.*;
//
//import java.util.Date;
//
//
//public class CaluclatorServiceTest {
//
//
//    @BeforeClass
//    public static void init() {
//        System.out.println("Testing Initialization Started");
//        System.out.println("Started Test : " + new Date());
//    }
//
//    @Test(timeout = 3000)
//    public void addTwoNumbers() throws InterruptedException {
//        System.out.println("Test for addTwoNumbers");
//        // actual result
//        int result = CaluclatorService.addTwoNumbers(12, 32);
//
//        // expected result
//        int expected = 44;
//
//        Assert.assertEquals(expected, result);
//    }
//
//    @Before
//    public void beforeEach() {
//        System.out.println("Before Each Test");
//    }
//
//    @Test
//    public void sumAnyNumbersTest() {
//        System.out.println("Test for sumAnyNumbers");
//
//        int result = CaluclatorService.sumAnyNumbers(2, 3, 7, 9, 6);
//
//        int expectedResult = 27;
//
//        Assert.assertEquals(expectedResult, result);
//    }
//
//    @AfterClass
//    public static void cleanup() {
//        System.out.println("After All Test cases");
//        System.out.println("End Test : " + new Date());
//    }
//
//}
