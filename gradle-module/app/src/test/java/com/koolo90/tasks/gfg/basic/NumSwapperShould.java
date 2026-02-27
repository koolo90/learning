package com.koolo90.tasks.gfg.basic;

import com.koolo90.framework.test.assertions.Assertions;
import org.junit.jupiter.api.Test;

/**
 * https://www.geeksforgeeks.org/dsa/swap-two-numbers/
 */
public class NumSwapperShould {
    @Test
    void swapNumbers() {
        int first = 1;
        int second = 2;

        NumberPair numberPair = swapNumbers(new NumberPair(first, second));

        Assertions.assertEquals(second, numberPair.first);
        Assertions.assertEquals(first, numberPair.second);
    }

    private NumberPair swapNumbers(NumberPair numberPair) {
        return new NumberPair(numberPair.second, numberPair.first);
    }

    private record NumberPair(int first, int second){

    }
}
