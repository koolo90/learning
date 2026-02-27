package com.koolo90.tasks.gfg.basic;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.stream.LongStream;

/**
 * https://www.geeksforgeeks.org/dsa/sum-of-squares-of-first-n-natural-numbers/
 */
public class NaturalSquareSummarizerShoudl {
    @Test
    void sumSquaresOfNaturalNumbers() {
        Assertions.assertEquals(14, squaresSum(3));
    }

    @Test
    void sumSquaresOfNaturalNumbers_CornerCase_Above() {
        Assertions.assertNotEquals(39, squaresSum(3));
    }

    @Test
    void sumSquaresOfNaturalNumbers_CornerCase_Bellow() {
        Assertions.assertNotEquals(5, squaresSum(3));
    }

    private static long squaresSum(long rangeEndInclusive) {
        return LongStream.rangeClosed(1, rangeEndInclusive).map(i -> i*i).sum();
    }
}
