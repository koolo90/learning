package com.koolo90.tasks.recruitment.sii.glasses;

import com.koolo90.framework.test.assertions.Assertions;
import org.junit.jupiter.api.Test;

public class GlassFillerShould {
    @Test
    void fillGlassByFullUnit() {
        Assertions.assertEquals(1, howMuchIn(1, 1, 1, 1f));
        Assertions.assertEquals(0.5, howMuchIn(2, 1, 2, 1f));
        Assertions.assertEquals(1, howMuchIn(2, 1, 4, 1f));
        Assertions.assertEquals(0.333, howMuchIn(3, 1, 4, 1f), 0.001);
        Assertions.assertEquals(0, howMuchIn(4, 1, 4, 1f), 0.001);

        Assertions.assertEquals(0.5, howMuchIn(2, 1, 2, 0.5f));
    }

    private float howMuchIn(int row, int column, int waterUnits, float cupCap) {
        float inc = cupCap;

        int sum = 0;
        while ((sum + inc) <= waterUnits) {
            if(row == inc) {
                return 1f;
            }
            sum += inc;
            inc+=cupCap;
        }
        if(row > inc) {
            return 0f;
        }
        return (waterUnits - sum) / (inc * 1f);
    }
}
