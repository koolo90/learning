package com.koolo90.tasks.recruitment.sii.chain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

class DominoBlockShould {
    @Test
    void createBlock() {
        Assertions.assertDoesNotThrow(() -> new DominoBlock(0,9));
        Assertions.assertDoesNotThrow(() -> new DominoBlock(9,0));

        Assertions.assertDoesNotThrow(() -> new DominoBlock(2,4));
    }

    @Test
    void throwExceptionOnInvalidInput() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> new DominoBlock(-1,0));
        Assertions.assertThrows(IllegalArgumentException.class, () -> new DominoBlock(10,0));

        Assertions.assertThrows(IllegalArgumentException.class, () -> new DominoBlock(0,-1));
        Assertions.assertThrows(IllegalArgumentException.class, () -> new DominoBlock(0,10));

        Assertions.assertThrows(IllegalArgumentException.class, () -> new DominoBlock(-1,10));
    }

    @Test
    void matchOtherBlock() {
        DominoBlock block_1_2 = new DominoBlock(1,2);
        DominoBlock block_2_1 = new DominoBlock(2,3);

        Assertions.assertTrue(block_1_2.isMatching(block_2_1));
    }

    @Test
    void doNotMatchOtherBlock() {
        DominoBlock block_1_2 = new DominoBlock(1,2);
        DominoBlock block_2_1 = new DominoBlock(3,4);

        Assertions.assertTrue(block_1_2.isMatching(block_2_1));
    }

    @Test @Disabled
    void matchOtherBlockNumber() {
        DominoBlock block_1_1 = new DominoBlock(1,1);

        Assertions.assertTrue(block_1_1.isMatching(1));
    }

    @Test @Disabled
    void doNotMatchToNumber() {
        DominoBlock block_1_1 = new DominoBlock(1,1);

        Assertions.assertFalse(block_1_1.isMatching(2));
    }

    @Test @Disabled
    void matchToAnotherBlock() {
        DominoBlock block_1_1 = new DominoBlock(1,1);
        DominoBlock block_1_2 = new DominoBlock(1,2);

        Assertions.assertTrue(block_1_1.isMatching(block_1_2));
    }

    @Test @Disabled
    void matchToAnotherBlockReversed() {
        DominoBlock block_1_1 = new DominoBlock(1,1);
        DominoBlock block_1_2 = new DominoBlock(2,1);

        Assertions.assertTrue(block_1_1.isMatching(block_1_2));
    }

    @Test @Disabled
    void doNotMatchToAnotherBlock() {
        DominoBlock block_1_1 = new DominoBlock(1,1);
        DominoBlock block_2_3 = new DominoBlock(2,3);

        Assertions.assertFalse(block_1_1.isMatching(block_2_3));
    }

    @Test @Disabled
    void flipBlock() {
        DominoBlock block_2_3 = new DominoBlock(2,3);

        int field_A = block_2_3.getA();
        int field_B = block_2_3.getB();
        Assertions.assertEquals(field_A, field_A, "Precondition check");
        Assertions.assertEquals(field_B, field_B, "Precondition check");

        block_2_3.flip();

        Assertions.assertEquals(field_B, block_2_3.getA());
        Assertions.assertEquals(field_A, block_2_3.getB());
    }
}


