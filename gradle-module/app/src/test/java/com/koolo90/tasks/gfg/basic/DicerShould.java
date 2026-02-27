package com.koolo90.tasks.gfg.basic;

import com.koolo90.framework.test.assertions.Assertions;
import org.junit.jupiter.api.Test;

public class DicerShould {
    @Test
    void tellOppositeFace() {
        Assertions.assertEquals(1, oppositeFaceOf(6));
        Assertions.assertEquals(2, oppositeFaceOf(5));
        Assertions.assertEquals(3, oppositeFaceOf(4));
        Assertions.assertEquals(4, oppositeFaceOf(3));
        Assertions.assertEquals(5, oppositeFaceOf(2));
        Assertions.assertEquals(6, oppositeFaceOf(1));
    }

    private int oppositeFaceOf(int i) {
        return 7-i;
    }
}
