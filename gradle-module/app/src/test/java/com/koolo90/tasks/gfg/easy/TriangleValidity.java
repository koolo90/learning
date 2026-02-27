package com.koolo90.tasks.gfg.easy;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * https://www.geeksforgeeks.org/dsa/check-whether-triangle-valid-not-sides-given/
 */
public class TriangleValidity {
    @Test
    void valid() {
        Assertions.assertTrue(canConstructTriangle(7,10,5));
    }

    @Test
    void invalid() {
        Assertions.assertFalse(canConstructTriangle(1,10,12));
    }

    private boolean canConstructTriangle(int... sides) {
        if(sides.length != 3) {
            return false;
        }
        int a = sides[0];
        int b = sides[1];
        int c = sides[2];

        return a+b>c && a+c>b && b+c>a;
    }
}
