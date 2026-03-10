package com.koolo90.tasks.gfg.easy;

import com.koolo90.framework.test.assertions.Assertions;
import org.junit.jupiter.api.Test;

/**
 * https://www.geeksforgeeks.org/dsa/program-to-add-two-fractions/
 */
public class FractionsAddedShould {
    final FractionsSummator fractionsSummator = new FractionsSummator();

    @Test
    void addFractionsFlat() {
        Assertions.assertArrayEquals(new int[]{12, 16}, fractionsSummator.sumFractionsFlat(new int[]{1, 2}, new int[]{2, 8}));
    }

    @Test
    void findCommonDividers() {
        Assertions.assertArrayEquals(new int[]{1, 2, 4}, fractionsSummator.findCommonDividers(12, 16));
    }

    @Test
    void findCommonDividersInversedOrder() {
        Assertions.assertArrayEquals(new int[]{1, 2, 4}, fractionsSummator.findCommonDividers(16, 12));
    }

    @Test
    void findGreatestCommonDivisor() {
        Assertions.assertEquals(4, fractionsSummator.greatestCommonDivisor(16, 12));
    }

    @Test
    void addFractions() {
        Assertions.assertArrayEquals(new int[]{3, 4}, fractionsSummator.sumFractions(new int[]{1, 2}, new int[]{2, 8}));
    }

    private int greatestCommonDivisor(int i, int i1) {
        return fractionsSummator.greatestCommonDivisor(i, i1);
    }

    private int[] findCommonDividers(int a, int b) {
        return fractionsSummator.findCommonDividers(a, b);
    }

    private int[] sumFractionsFlat(int[] fractionA, int[] fractionB) {
        return fractionsSummator.sumFractionsFlat(fractionA, fractionB);
    }

    private int[] sumFractions(int[] fractionA, int[] fractionB) {
        //A common, vulgar,[n 1] or simple fraction (consists of an integer numerator, displayed above a line
        // (or before a slash like 1⁄2), and a non-zero integer denominator, displayed below (or after) that line. If
        // these integers are positive, then the numerator represents a number of equal parts, and the denominator
        // indicates how many of those parts make up a unit or a whole.
        // find the highest common divider between numerator and denominator
        // divide boths by the highest common divider and place numbers accordingly.

        // 1/2 + 2/8 = 4/8 + 2/8 = 6/8 = 3/4 (HCB = 2)

        return fractionsSummator.sumFractions(fractionA, fractionB);
    }

    private int findMinimum(int numberA, int numberB) {
        return fractionsSummator.findMinimum(numberA, numberB);
    }
}
