package com.koolo90.tasks.recruitment.sii.median.set;

import java.util.Arrays;

public class Median {
    public static float of(int... array) {
        int[] sortedArray = Arrays.stream(array).sorted().toArray();
        int idx = (array.length) / 2; //take middle element
        int middleElement = sortedArray[idx];
        return isOfOddLength(sortedArray) ? middleElement : avg(middleElement, sortedArray[idx-1]);
    }

    private static float avg(int... elems) {
        return (float) Arrays.stream(elems).sum() / elems.length;
    }

    private static boolean isOfOddLength(int[] sortedArray) {
        return sortedArray.length % 2 == 1;
    }
}
