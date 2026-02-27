package com.koolo90.tasks.gfg.easy;

import com.koolo90.framework.test.assertions.Assertions;
import org.junit.jupiter.api.Test;

/**
 * https://www.geeksforgeeks.org/dsa/count-pairs-a-b-whose-sum-of-cubes-is-n-a3-b3-n/
 */
public class PairCubeCount {
    /**
     * Input: n = 9, Output: 2; Explanation: 1^3 + 2^3 = 9 and 2^3 + 1^3 = 9
     * Input: n = 28, Output: 2; Explanation: 1^3 + 3^3 = 28 and 3^3 + 1^3 = 28
     */

    @Test
    void test() {
        Assertions.assertEquals(2, cubePairsCount(9));
        Assertions.assertEquals(2, cubePairsCount(28));
    }

    private static int cubePairsCount(int num) {
        int count = 0;
        for (int i = 1; i <= Math.cbrt(num); i++) { // Check for each number 1 to cbrt(n)
            int cb = (int) Math.pow(i, 3); // Store cube of a number
            int diff = num - cb; // Subtract the cube from given n
            int cbrtDiff = (int) Math.cbrt(diff); // Check if the difference is also a perfect cube
            if (Math.pow(cbrtDiff, 3) == diff) // If yes, then increment count
                count++;
        }
        return count;
    }
}
