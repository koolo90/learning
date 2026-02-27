package com.koolo90.tasks.gfg.basic;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

/*
https://www.geeksforgeeks.org/dsa/find-number-closest-n-divisible-m/
 */
public class CloseiestDivisionShould {
    private final ClosestMultiplication closiestMultiplication = new ClosestMultiplication();

    public static Stream<Arguments> closestMultiplication() {
        return Stream.of(
            Arguments.of(7,5,5), //pos,pos,low
            Arguments.of(9,5,10), //pos,pos,high
            Arguments.of(7,-5,5), //pos,neg,low
            Arguments.of(9,-5,10), //pos,neg,high
            Arguments.of(-7,5,-5), //neg,pos,low
            Arguments.of(-9,5,-10), //neg,pos,high
            Arguments.of(-7,-5,-5), //neg,neg,low
            Arguments.of(-9,-5,-10) //neg,neg,high
        );
    }

    public static Stream<Arguments> closestMultiplicator() {
        return Stream.of(
                Arguments.of(7,5,1), //pos,pos,low
                Arguments.of(9,5,2), //pos,pos,high
                Arguments.of(7,-5,-1), //pos,neg,low
                Arguments.of(9,-5,-2), //pos,neg,high
                Arguments.of(-7,5,-1), //neg,pos,low
                Arguments.of(-9,5,-2), //neg,pos,high
                Arguments.of(-7,-5,1), //neg,neg,low
                Arguments.of(-9,-5,2) //neg,neg,high
        );
    }

    public static Stream<Arguments> incrementation() {
        return Stream.of(
                Arguments.of(7,5,1), //pos,pos,low
                Arguments.of(-9,-5,1), //neg,neg,high
                Arguments.of(7,-5,-1), //pos,neg,low
                Arguments.of(-7,5,-1) //neg,pos,low
        );
    }

    @ParameterizedTest
    @MethodSource("incrementation")
    void findSignum(int base, int divider, int expected) {
        Assertions.assertEquals(expected, closiestMultiplication.signum(base, divider));
    }

    @ParameterizedTest
    @MethodSource("closestMultiplication")
    void findClosestDivisibleNumber(int base, int divider, int expected) {
        Assertions.assertEquals(expected, closiestMultiplication.findNextNumberDivisibleByAndNeighbourOf(base, divider));
    }

    @ParameterizedTest
    @MethodSource("closestMultiplicator")
    void findDivider(int base, int divider, int expected) {
        Assertions.assertEquals(expected, closiestMultiplication.findDivider(base, divider));
    }

    private int signum(int base, int divider) {
        return closiestMultiplication.signum(base, divider);
    }

    private int findDivider(int base, int divider) {

        return closiestMultiplication.findDivider(base, divider);
    }

    private int findNextNumberDivisibleByAndNeighbourOf(int base, int divider) {
        return closiestMultiplication.findNextNumberDivisibleByAndNeighbourOf(base, divider);
    }

    public static class ClosestMultiplication {
        public ClosestMultiplication() {
        }

        int signum(int base, int divider) {
            if ((base < 0 && divider < 0) || (base >= 0 && divider >= 0)) {
                return 1;
            } else {
                return -1;
            }
        }

        int findDivider(int base, int divider) {
            int inc = signum(base, divider);
            int multiplication = base / divider;
            int multiplicationGreater = multiplication + inc;

            int lower = divider * multiplication;
            int greater = divider * multiplicationGreater;
            int distanceLower = Math.abs(lower - base);
            int distanceHigher = Math.abs(greater - base);

            return distanceLower < distanceHigher ? multiplication : multiplicationGreater;
        }

        int findNextNumberDivisibleByAndNeighbourOf(int base, int divider) {
            return findDivider(base, divider) * divider;
        }
    }
}
