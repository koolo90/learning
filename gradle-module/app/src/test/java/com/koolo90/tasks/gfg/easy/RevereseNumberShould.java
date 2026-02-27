package com.koolo90.tasks.gfg.easy;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class RevereseNumberShould {
    @Test
    void reverseNumber() {
        int number = 12345;
        int reveresd = 0;
        int log10 = (int) Math.ceil(Math.log10(number));
        for (int i = 0; i < log10; i++) {
            reveresd += (int) ((number % 10) * Math.pow(10, log10 - i - 1));
            number /= 10;
        }
        Assertions.assertEquals(54321, reveresd);
    }
}
