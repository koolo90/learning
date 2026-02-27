package com.koolo90.tasks.gfg.basic;

import com.koolo90.framework.test.assertions.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Basing on: https://www.geeksforgeeks.org/dsa/check-whether-given-number-even-odd/
 */
public class EvenOddShould {
    @Test
    void returnTrueForEvenBitwise() {
        Assertions.assertTrue(OddChecker.isEvenBitwise(4));
    }

    @Test
    void returnTrueForEvenMathematically() {
        Assertions.assertTrue(OddChecker.isEvenMathematically(4));
    }

    @Test
    void returnFalseForEvenBitwise() {
        Assertions.assertFalse(OddChecker.isEvenBitwise(51));
    }

    @Test
    void returnFalseForOddBitwise() {
        Assertions.assertFalse(OddChecker.isEvenMathematically(51));
    }

    private static class OddChecker {
        static boolean isEvenMathematically(int number) {
            return (number % 2) == 0;
        }

        public static boolean isEvenBitwise(int number) {
            return (number & 1) == 0;
        }
    }
}
