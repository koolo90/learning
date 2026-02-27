package com.koolo90.tasks.gfg.basic;

import com.koolo90.framework.test.assertions.Assertions;
import org.junit.jupiter.api.Test;

import java.util.stream.LongStream;

/**
 * http://geeksforgeeks.org/dsa/program-find-sum-first-n-natural-numbers/
 */
public class SumOfNaturalsShould {
    @Test
    void sumUpToNumber() {
        Assertions.assertEquals(15, NaturalSummarizer.sumUpTo(5));
    }

    @Test
    void endOnLastElement_CornerCase_Above() {
        Assertions.assertNotEquals(21, NaturalSummarizer.sumUpTo(5));
    }

    @Test
    void endOnLastElement_CornerCase_Below() {
        Assertions.assertNotEquals(10, NaturalSummarizer.sumUpTo(5));
    }

    private class NaturalSummarizer {
        public static long sumUpTo(int lastElementIncl) {
            return LongStream.rangeClosed(1,lastElementIncl).sum();
        }
    }
}
