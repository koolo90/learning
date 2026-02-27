package com.koolo90.tasks.gfg.easy;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigInteger;

/**
 * https://www.geeksforgeeks.org/dsa/program-to-find-gcd-or-hcf-of-two-numbers/
 */
public class GreatestCommonDivisorFinderShould {
    /**
     * Input: a = 20, b = 28
     * Output: 4
     *
     * Explanation:
     *     The factors of 20 are 1, 2, 4, 5, 10 and 20.
     *     The factors of 28 are 1, 2, 4, 7, 14 and 28.
     *     Among these factors, 1, 2 and 4 are the common factors of both 20 and 28.
     *     The greatest among the common factors is 4.
     *
     * Input: a = 60, b = 36
     * Output: 12
     *
     * Explanation: GCD of 60 and 36 is 12.
     *
     * TODO: Paramethrize and use other methods outcomes
     */
    @Test
    void gcd() {
        Assertions.assertEquals(4, findGcdWithForLoop(20, 28));
        Assertions.assertEquals(4, findGcdWithForLoop(28, 20));
        Assertions.assertEquals(12,findGcdWithForLoop(60, 36));
        Assertions.assertEquals(1, findGcdWithForLoop(0, 0));
        Assertions.assertEquals(1, findGcdWithForLoop(3, 11));
    }

    private static int findGcdWithForLoop(int a, int b) {
        int limit = Math.min(a, b);
        int gcd = 1;
        for(int i = 1; i <= limit; i++) {
            if(a % i == 0 && b % i == 0) {
                if(i>gcd) {
                    gcd = i;
                }
            }
        }
        return gcd;
    }

    static int gcdWithWhileLoop(int a, int b) {
        int result = Math.min(a, b); // Find Minimum of a and b
        while (result > 0) {
            if (a % result == 0 && b % result == 0) {
                break;
            }
            result--;
        }


        return result; // Return gcd of a and b
    }

    static int gcdRecursive(int a, int b) {
        if (a == 0)
            return b;
        if (b == 0)
            return a;// Everything divides 0

        if (a == b) // Base case
            return a;


        if (a > b) // a is greater
            return gcdRecursive(a - b, b);
        return gcdRecursive(a, b - a);
    }

    static int gcdRecursiveAlternative(int a, int b) {
        // Everything divides 0
        if (a == 0)
            return b;
        if (b == 0)
            return a;

        // Base case
        if (a == b)
            return a;

        // a is greater
        if (a > b) {
            if (a % b == 0)
                return b;
            return gcdRecursiveAlternative(a - b, b);
        }

        // b is greater
        if (b % a == 0)
            return a;
        return gcdRecursiveAlternative(a, b - a);
    }

    static int gcdRecursiveMin(int a, int b) {
        return (b == 0) ? a : gcdRecursiveMin(b, a % b);
    }

    static int gcdBuiltIn(int a, int b) {
        return BigInteger.valueOf(a).gcd(BigInteger.valueOf(b)).intValue();
    }
}
