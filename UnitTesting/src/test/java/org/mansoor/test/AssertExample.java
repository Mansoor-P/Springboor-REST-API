package org.mansoor.test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class AssertExample {
    // Assertion = validating actual result with expected result

    @Test
    public void test1() {
        System.out.println("Some Assertions operations");
//        int  actual= 12;
//        int  expected=12;
//
//        Assertions.assertEquals(expected,actual);

        String a = "mansoor";
        String b = "mansoor";

        Assertions.assertEquals(a, b);
    }

}
