package com.koolo90.tasks.recruitment.sii.snow;

import com.koolo90.framework.test.assertions.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

/**
 * https://www.geeksforgeeks.org/trapping-rain-water/
 */
public class SnowRetainerShould {

    static Stream<Arguments> getWaterRagesWithNoCapacity() {
        return Stream.of(
                Arguments.of(new int[]{0,0}, 0),
                Arguments.of(new int[]{0,0}, 0),
                Arguments.of(new int[]{0,1}, 0),
                Arguments.of(new int[]{1,0}, 0),
                Arguments.of(new int[]{1,1}, 0),

                Arguments.of(new int[]{0,0,0}, 0),
                Arguments.of(new int[]{0,0,1}, 0),
                Arguments.of(new int[]{0,1,0}, 0),
                Arguments.of(new int[]{0,1,1}, 0),
                Arguments.of(new int[]{1,0,0}, 0),
                Arguments.of(new int[]{1,1,0}, 0),
                Arguments.of(new int[]{1,1,1}, 0));
    }

    static Stream<Arguments> getWaterRagesWithCapacityOne() {
        return Stream.of(
                Arguments.of(new int[]{1,0,1}, 1),
                Arguments.of(new int[]{2,1,2}, 1));
    }

    static Stream<Arguments> getWaterRagesWithCapacityTwo() {
        return Stream.of(
                Arguments.of(new int[]{2,0,2}, 2),
                Arguments.of(new int[]{1,0,0,1}, 2),
                Arguments.of(new int[]{1,0,0,1}, 2),
                Arguments.of(new int[]{1,0,1,0,1}, 2));
    }

    @Test
    void selectMinimum() {
        int leftHeight = 1;
        int rightHeight = 2;
        int expectedMin = 1;
        int actualMin = min(leftHeight, rightHeight);
        Assertions.assertEquals(expectedMin, actualMin);
    }

    @ParameterizedTest
    @MethodSource("getWaterRagesWithNoCapacity")
    void trappedSnowShouldBeZero(int[] mountainRange, int expectedTrappedWater) {
        int actualTrappedWater = calculateTrappedWater(mountainRange);
        Assertions.assertEquals(expectedTrappedWater, actualTrappedWater);
    }

    @ParameterizedTest
    @MethodSource("getWaterRagesWithCapacityOne")
    void trappedSnowShouldBeOneUnit(int[] mountainRange, int expectedTrappedWater) {
        int actualTrappedWater = calculateTrappedWater(mountainRange);
        Assertions.assertEquals(expectedTrappedWater, actualTrappedWater);
    }

    @ParameterizedTest
    @MethodSource("getWaterRagesWithCapacityTwo")
    void trappedSnowShouldBeTwoUnits(int[] mountainRange, int expectedTrappedWater) {
        int actualTrappedWater = calculateTrappedWater(mountainRange);
        Assertions.assertEquals(expectedTrappedWater, actualTrappedWater);
    }

    @Test
    void trappedSnowShouldBeThreeUnits() {
        int trappedWater = calculateTrappedWater(new int[]{2,1,0,1});
        Assertions.assertEquals(3, trappedWater);
    }

    private int calculateTrappedWater(int[] mountainRange) {
        if (mountainRange.length < 3) {
            return 0; //there is no possibility to catch any water in mountain range of length 2
        }
        int trappedWater = 0;

        int rightIdx = 0, middleIdx = 1, leftIdx = 2;
        

        return trappedWater;
    }

    private int min(int numberA, int numberB) {
        return numberA < numberB ? numberA : numberB;
    }

    private int taken(int idxLeft, int idxRight, int[] range) {
        return 0;
    }
}
