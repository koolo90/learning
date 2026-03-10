package com.koolo90.tasks.gfg.easy;

import com.koolo90.framework.test.assertions.Assertions;
import org.junit.jupiter.api.Test;

/**
 * https://www.geeksforgeeks.org/dsa/perfect-number/
 *
 * A number is a perfect number if it is equal to the sum of its proper divisors, that is, the sum of its positive
 * divisors excluding the number itself. Find whether a given positive integer n is perfect or not.
 *
 * TODO: Paramethrize 
 */
public class PerfectNumberShould {
    @Test
    void perfectNumberShould() {
        /**
         * Input: n = 15
         * Output: false
         * Explanation: Divisors of 15 are 1, 3 and 5. Sum of divisors is 9 which is not equal to 15.
         *
         * Input: n = 6
         * Output: true
         * Explanation: Divisors of 6 are 1, 2 and 3. Sum of divisors is 6.
         */
        Assertions.assertFalse(isPerfectNumber(15));
        Assertions.assertTrue(isPerfectNumber(6));
        Assertions.assertFalse(isPerfectNumberOptimized(15));
        Assertions.assertTrue(isPerfectNumberOptimized(6));
    }

    private boolean isPerfectNumberOptimized(int num) {
        int sum = 1;
        for (int i = 2, sqi = (i*i); sqi <= num; i++, sqi = (i*i))
            if (num % i == 0) if (sqi != num) sum += (i + num / i);
            else sum += i;
        return sum == num && num != 1;
    }

    private boolean isPerfectNumber(int num) {
        int sum = 0;
        for (int i = 1; i < num; i++) {
            if (num % i == 0) {
                sum += i;
            }
        }

        return sum == num;
    }
}
