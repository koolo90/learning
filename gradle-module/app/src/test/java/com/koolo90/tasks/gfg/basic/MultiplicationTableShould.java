package com.koolo90.tasks.gfg.basic;

import com.koolo90.framework.test.assertions.Assertions;
import org.junit.jupiter.api.Test;

import java.util.stream.IntStream;

/**
 * https://www.geeksforgeeks.org/dsa/program-to-print-multiplication-table-of-a-number/
 */
public class MultiplicationTableShould {
    @Test
    public void createMultiplicationTable() {
        Assertions.assertContains(MultiplcationTable.createMultiplicationTableFor(5), "5*0=0");
        Assertions.assertContains(MultiplcationTable.createMultiplicationTableFor(5), "5*1=5");
        Assertions.assertContains(MultiplcationTable.createMultiplicationTableFor(5), "5*7=35");
        Assertions.assertContains(MultiplcationTable.createMultiplicationTableFor(5), "5*10=50");
    }

    @Test
    public void prepareMultiplicationTableWithExpandedRange() {
        String multiplicationTable5_0_3 = MultiplcationTable.forNumber(5).otherMultiplierRange(0,3).create();

        Assertions.assertContains(multiplicationTable5_0_3, "5*0=0");
        Assertions.assertContains(multiplicationTable5_0_3, "5*1=5");
        Assertions.assertContains(multiplicationTable5_0_3, "5*2=10");
        Assertions.assertContains(multiplicationTable5_0_3, "5*3=15");
        Assertions.assertDoesNotContain(multiplicationTable5_0_3, "5*4=20");
    }

    private static class MultiplcationTable {
        private final int number;
        private static int rangeStart = 0;
        private static int rangeEndIncl = 10;

        public MultiplcationTable(int number) {
            this.number = number;
        }

        public static String createMultiplicationTableFor(int i) {
            StringBuilder sb = new StringBuilder();
            IntStream.rangeClosed(rangeStart, rangeEndIncl)
                    .forEach(j -> sb.append(i).append("*").append(j).append("=").append(i * j).append("\n"));
            return sb.toString();
        }

        public static MultiplcationTable forNumber(int i) {
            return new MultiplcationTable(5);
        }

        public MultiplcationTable otherMultiplierRange(int rangeStart, int rangeStop) {
            this.rangeStart = rangeStart;
            this.rangeEndIncl = rangeStop;
            return this;
        }

        public String create() {
            return createMultiplicationTableFor(number);
        }
    }
}
