package com.koolo90.tasks.gfg.easy;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class FractionsSummator {
    int NUMERATOR_IDX = 0;
    int DENOMINATOR_IDX = 1;

    public FractionsSummator() {
    }

    int greatestCommonDivisor(int i, int i1) {
        return Arrays.stream(findCommonDividers(i, i1)).max().orElse(1);
    }

    int[] findCommonDividers(int a, int b) {
        List<Integer> commonDividers = new LinkedList<Integer>();
        int lowest = findMinimum(a, b);
        for (int i = 1; i < lowest; i++) {
            if (a % i == 0 && b % i == 0) {
                commonDividers.add(i);
            }
        } //complexity is fucked, performance could be improved
        return commonDividers.stream().mapToInt(Integer::intValue).toArray();
    }

    int[] sumFractionsFlat(int[] fractionA, int[] fractionB) {
        int[] result = new int[2];
        result[NUMERATOR_IDX] = fractionA[NUMERATOR_IDX] * fractionB[DENOMINATOR_IDX] + fractionB[NUMERATOR_IDX] * fractionA[DENOMINATOR_IDX];
        result[DENOMINATOR_IDX] = fractionA[DENOMINATOR_IDX] * fractionB[DENOMINATOR_IDX];
        return result;
    }

    int[] sumFractions(int[] fractionA, int[] fractionB) {
        //A common, vulgar,[n 1] or simple fraction (consists of an integer numerator, displayed above a line
        // (or before a slash like 1⁄2), and a non-zero integer denominator, displayed below (or after) that line. If
        // these integers are positive, then the numerator represents a number of equal parts, and the denominator
        // indicates how many of those parts make up a unit or a whole.
        // find the highest common divider between numerator and denominator
        // divide boths by the highest common divider and place numbers accordingly.

        // 1/2 + 2/8 = 4/8 + 2/8 = 6/8 = 3/4 (HCB = 2)

        int[] flat = sumFractionsFlat(fractionA, fractionB);
        int numerator = flat[NUMERATOR_IDX];
        int denominator = flat[DENOMINATOR_IDX];
        int gcd = greatestCommonDivisor(numerator, denominator);

        return new int[]{numerator / gcd, denominator / gcd};
    }

    int findMinimum(int numberA, int numberB) {
        if (numberA < numberB) {
            return numberA;
        } else if (numberB < numberA) { //The call to math.min is not here for a purpose
            return numberB;
        } else {
            return numberA;
        }
    }
}