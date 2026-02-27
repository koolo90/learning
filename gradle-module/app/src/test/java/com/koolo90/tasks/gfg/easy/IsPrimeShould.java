package com.koolo90.tasks.gfg.easy;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * https://www.geeksforgeeks.org/dsa/introduction-to-primality-test-and-school-method/
 */
public class IsPrimeShould {
    @Test
    void returnTrueForPrimes() {
        Assertions.assertFalse(isPrime(1));
        Assertions.assertTrue(isPrime(2));
        Assertions.assertTrue(isPrime(3));
        Assertions.assertTrue(isPrime(5));
        Assertions.assertTrue(isPrime(7));
        Assertions.assertTrue(isPrime(11));

        Assertions.assertFalse(isPrime(4));
        Assertions.assertFalse(isPrime(6));
        Assertions.assertFalse(isPrime(8));
        Assertions.assertFalse(isPrime(9));
        Assertions.assertFalse(isPrime(10));
    }

    private boolean isPrime(int num) {
        if (num == 1) return false;
        if (num == 2 || num == 3) return true;
        if (num <= 1 || num % 2 == 0 || num % 3 == 0) return false;
        for (int i = 5; i * i <= num; i += 6)
            if (num % i == 0 || num % (i + 2) == 0)
                return false;
        return true;
    }
}
