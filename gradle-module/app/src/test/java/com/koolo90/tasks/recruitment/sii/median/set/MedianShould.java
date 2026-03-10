package com.koolo90.tasks.recruitment.sii.median.set;

import com.koolo90.framework.test.assertions.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

public class MedianShould {
    @Test
    void medianOfSetOfOddPower() {
        Assertions.assertEquals(171, Median.of(179, 169, 171, 174, 167));
        Assertions.assertEquals(82, Median.of(74, 82, 75, 96, 88));
    }

    @Test
    void medianOfSetOfEvenPower() {
        Assertions.assertEquals(2.5, Median.of(1,2,3,4));
    }
}
