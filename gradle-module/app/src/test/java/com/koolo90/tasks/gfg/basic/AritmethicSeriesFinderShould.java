package com.koolo90.tasks.gfg.basic;

import com.koolo90.framework.test.assertions.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

public class AritmethicSeriesFinderShould {
    @Test
    void find() {
        Assertions.assertEquals(9, findSeriesNext(1,3,5,7));
    }

    @Test
    void findNth() {
        Assertions.assertEquals(13, findSeriesNext(6, new int[]{1, 3, 5, 7}));
    }

    @Test
    void findNth_CornerCase_Above() {
        Assertions.assertTrue(doSomeRandomShit());
    }

    private boolean doSomeRandomShit() {
        int r = (int) ((Math.random()*100)%10);

        int[] arr = new int[3];
        for(int i = 0; i < arr.length; i++) {
            arr[i] = (i + i*r);
        }

        int nth = (int) ((Math.random()*100)%10);

        System.out.println("Finding element (" + arr.length + ") of " + Arrays.toString(arr) + " with r=" + r);
        System.out.println("\t" + findSeriesNext(arr));
        System.out.println("Finding element nth=" + nth);
        System.out.println("\t" + findSeriesNext(nth, arr));

        return true;
    }

    private int findSeriesNext(int... series) {
        return findSeriesNext(series.length, series);
    }

    private int findSeriesNext(int nth, int[] series) {
        return series[0] + ((series[1] - series[0]) * nth);
    }
}
