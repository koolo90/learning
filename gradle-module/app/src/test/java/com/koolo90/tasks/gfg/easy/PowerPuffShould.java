package com.koolo90.tasks.gfg.easy;

import com.koolo90.framework.test.assertions.Assertions;
import org.junit.jupiter.api.Test;

/**
 * https://www.geeksforgeeks.org/dsa/check-if-a-number-is-power-of-another-number/
 */
public class PowerPuffShould {
    @Test
    void powerPuff() {
        Assertions.assertTrue(isPower(2, 8));
        Assertions.assertTrue(isPower(3,27));
        Assertions.assertTrue(isPower(3,27));
        Assertions.assertFalse(isPower(4,27));
    }

    private boolean isPower(int base, int powered) {
        double logsProportion = Math.log(powered) / Math.log(base);
        return logsProportion == Math.floor(logsProportion);
    }
}
