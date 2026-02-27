package com.koolo90.tasks.gfg.easy;

import com.koolo90.framework.test.assertions.Assertions;
import org.junit.jupiter.api.Test;

/**
 * https://www.geeksforgeeks.org/dsa/logic-building-problems/
 */
public class SumOfDigits {
    @Test
    void sumOfDigitsAsString() {
        int number = 12345;
        String numberAsString = String.valueOf(number);
        int reduce = numberAsString.chars().map(x -> x-48).reduce(0, Integer::sum);
        Assertions.assertEquals(15, reduce);
    }

    @Test
    void sumOfDigitsWithLog10() {
        int number = 12345, sum = 0, log10 = (int) Math.ceil(Math.log10(number));
        for(int i = 0; i < log10; i++) {
            sum += number % 10;
            number /= 10;
        }
        Assertions.assertEquals(15, sum);
    }
}
