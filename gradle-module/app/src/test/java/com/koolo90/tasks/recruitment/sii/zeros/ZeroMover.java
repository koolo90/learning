package com.koolo90.tasks.recruitment.sii.zeros;

import org.junit.jupiter.api.Test;

import java.sql.SQLOutput;
import java.text.MessageFormat;
import java.util.Arrays;

public class ZeroMover {
    @Test
    void moveZerosToEnd() {
        int[] input = new int[]{0, 1, 7, 0, 6, 9, 0, 6, 9};

        int zeroIndex = 0;
        for(int i= 0; i < input.length; i++) {
            if(input[i] == 0) {
                int zeroPlacement = input.length - ++zeroIndex;
                int tmp = input[zeroPlacement];
                System.out.println("input["+zeroPlacement+"]=" +tmp+"<=>input[" + i + "]=" + input[i] + "...");
                input[zeroPlacement] = input[i];
                input[i] = tmp;
            }
            System.out.println("Step #" + Arrays.toString(input));
        }

        System.out.println(Arrays.toString(input));
    }
}
