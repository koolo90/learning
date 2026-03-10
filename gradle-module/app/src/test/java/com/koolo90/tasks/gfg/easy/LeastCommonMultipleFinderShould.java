package com.koolo90.tasks.gfg.easy;

import com.koolo90.framework.test.assertions.Assertions;
import org.junit.jupiter.api.Test;

/**
 * https://www.geeksforgeeks.org/dsa/program-to-find-lcm-of-two-numbers/
 */
public class LeastCommonMultipleFinderShould {
    @Test
    void find() {
        /**
         * Input: a = 10, b = 5; Output: 10
         * Explanation: 10 is the smallest number divisible by both 10 and 5
         * Input: a = 5, b = 11; Output: 55
         * Explanation: 55 is the smallest number divisible by both 5 and 11
         */
        Assertions.assertEquals(10, leastCommonMultiplierOf(10,5));
        Assertions.assertEquals(55, leastCommonMultiplierOf(5,11));
    }

    private static int leastCommonMultiplierOf(int a, int b) {
        if (a % b == 0) {
            return (a / b) * b;
        }
        if (b % a == 0) {
            return (b / a) * a;
        }
        return (a * b);
    }
}
