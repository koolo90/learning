package com.koolo90.tasks.recruitment.sii;

import com.koolo90.framework.test.assertions.Assertions;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.Collectors;

public class SumToValueTest {
    @Test
    void shouldSumToValue() {
        int[] arr = {0,-1,2,-3,1};
        Assertions.assertContainsAll(new int[]{1, -3}, elementsThatSumUpTo(2, arr));
    }

    @Test
    void shouldSumToValueUsingSet() {
        int[] arr = {0,-1,2,-3,1};
        Assertions.assertContainsAll(new int[]{1, -3}, elementsThatSumUpToUsingSet(2, arr));
    }

    private int[] elementsThatSumUpToUsingSet(int i, int[] arr) {
        TreeSet<Integer> list = Arrays.stream(arr).boxed().collect(Collectors.toCollection(TreeSet::new));

        int[] result = new int[2];
        for(int elem : arr) {
            int tempSum = i - elem;
            if (list.contains(tempSum)) {
                return new int[]{elem, tempSum};
            }
        }
        return null;
    }

    private int[] elementsThatSumUpTo(int i, int[] arr) {
        for (int elem1 : arr) {
            for (int elem2 : arr) {
                if(elem1+elem2 == i) {
                    return new int[]{elem1, elem2};
                }
            }
        }
        return null;
    }
}
