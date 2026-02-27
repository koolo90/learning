package com.koolo90.tasks.gfg.easy;

import com.koolo90.framework.test.assertions.Assertions;
import org.junit.jupiter.api.Test;

import java.util.stream.IntStream;

/**
 * https://www.geeksforgeeks.org/dsa/program-for-factorial-of-a-number/
 */
public class FactorialShould {
    @Test
    void factorial() {
        /**
         * Input: n = 5
         * Output: 120
         * Explanation: 5! = 5 * 4 * 3 * 2 * 1 = 120
         *
         * Input: n = 4
         * Output: 24
         * Explanation: 4! = 4 * 3 * 2 * 1 = 24
         */
        Assertions.assertEquals(120, getFactorial(5));
        Assertions.assertEquals(24, getFactorial(4));
    }

    private int getFactorial(int i) {
        return IntStream.range(1, i+1).reduce(1, (a, b) -> a*b);
    }
}
