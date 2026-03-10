package com.koolo90.tasks.recruitment.sii.separate.chaining;

import com.koolo90.framework.test.assertions.Assertions;
import org.junit.jupiter.api.Test;

public class SeparateChainingShould {
    @Test
    void separateChainingShouldStore26on0index() {
        SeparateChaining separateChaining = new SeparateChaining(5);

        Assertions.assertTrue(separateChaining.add(26));
        Assertions.assertTrue(separateChaining.add(6));
        Assertions.assertTrue(separateChaining.add(7));
        Assertions.assertContains(separateChaining.get(1), 26);
        Assertions.assertContains(separateChaining.get(2), 7);
        Assertions.assertEquals(1, separateChaining.getIndex(26));
        Assertions.assertContainsAllInGivenOrder(separateChaining.get(1), 26, 6);
        Assertions.assertEquals(3, separateChaining.getSize());
        Assertions.assertEquals(3, separateChaining.getEmptyIndices());
    }

}
