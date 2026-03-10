package com.koolo90.tasks.recruitment.sii.median.arrays;

import com.koolo90.framework.test.assertions.Assertions;
import org.junit.jupiter.api.Test;

public class MedianShould {
    @Test
    void medianOfSetOfOddPower() {
        int[] arrayA = new int[]{-5, 3, 6, 12, 15};
        int[] arrayB = new int[]{-12, -10, -6, -3, 4, 10};

        Assertions.assertEquals(3, Median.of(Median.combine(arrayA, arrayB)));
    }

    @Test
    void medianOfSetOfEvenPower() {
        int[] arrayA = new int[]{1};
        int[] arrayB = new int[]{2, 4, 5, 6, 7};

        Assertions.assertEquals(4.5, Median.of(Median.combine(arrayA, arrayB)));
    }
}
