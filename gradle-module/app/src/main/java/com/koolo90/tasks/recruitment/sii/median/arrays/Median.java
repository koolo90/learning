package com.koolo90.tasks.recruitment.sii.median.arrays;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Median {
    public static int[] combine(int[] arrayA, int[] arrayB) {
        List<Integer> combinedArray = new ArrayList<>();
        Arrays.stream(arrayA).forEach(combinedArray::add);
        Arrays.stream(arrayB).forEach(combinedArray::add);
        return combinedArray.stream().mapToInt(Integer::intValue).toArray();
    }

    public static float of(int... array) {
        int[] sortedArray = Arrays.stream(array).sorted().toArray();
        if (array.length % 2 == 1) {
            int idx = (array.length) / 2;
            return sortedArray[idx];
        } else {
            int idx = array.length / 2;
            return (float) (sortedArray[idx] + sortedArray[idx - 1]) / 2;
        }
    }
}