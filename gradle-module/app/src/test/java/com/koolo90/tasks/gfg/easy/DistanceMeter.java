package com.koolo90.tasks.gfg.easy;

import com.koolo90.framework.test.assertions.Assertions;
import org.junit.jupiter.api.Test;

/**
 * https://www.geeksforgeeks.org/dsa/program-calculate-distance-two-points/
 */
public class DistanceMeter {
    @Test
    void distance() {
        int[] pointA = {3, 4}, pointB = {7, 7};
        Assertions.assertEquals(5, distanceBetween(pointA, pointB));
        Assertions.assertEquals(1.41421, distanceBetween(pointA, pointB));
    }

    private Object distanceBetween(int[] pointA, int[] pointB) {
        int lengthA = Math.abs(pointA[0] - pointB[0]), lengthB = Math.abs(pointA[1] - pointB[1]);
        return Math.sqrt(lengthA*lengthA + lengthB*lengthB);
    }
}
