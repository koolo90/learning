package com.koolo90.tasks.recruitment.sii.domino;

import org.junit.jupiter.api.Test;

import static com.koolo90.framework.test.assertions.Assertions.*;

public class DominoBlockShould {
    @Test
    void creationWithFailFast() {
        assertDoesNotThrow(() -> new DominoBlock(0, 9));
        assertDoesNotThrow(() -> new DominoBlock(9, 0));
        assertDoesNotThrow(() -> new DominoBlock(3, 7));

        System.out.println("Wololo");

        assertThrows(IllegalArgumentException.class, () -> new DominoBlock(-1, 0));
        assertThrows(IllegalArgumentException.class, () -> new DominoBlock(0, -1));
        assertThrows(IllegalArgumentException.class, () -> new DominoBlock(0, 10));
        assertThrows(IllegalArgumentException.class, () -> new DominoBlock(10, 0));
        assertThrows(IllegalArgumentException.class, () -> new DominoBlock(-1, 10));
    }

    @Test
    void matchField() {
        assertTrue(new DominoBlock(0, 9).isMatching(9));
    }

    @Test
    void doNotMatchField() {
        assertFalse(new DominoBlock(0, 9).isMatching(5));
    }

    @Test
    void matchFieldWhenFlipped() {
        assertTrue(new DominoBlock(0, 9).flip().isMatching(0));
    }

    @Test
    void matchOtherDomino() {
        assertTrue(new DominoBlock(0, 9).isMatching(new DominoBlock(9,8)));
        assertTrue(new DominoBlock(0, 9).isMatching(new DominoBlock(8,9).flip()));
        assertTrue(new DominoBlock(0, 9).flip().isMatching(new DominoBlock(0,7)));
        assertTrue(new DominoBlock(0, 9).flip().isMatching(new DominoBlock(7,0).flip()));
    }

    @Test
    void matchOtherDominoDisregardingOwnDirection() {
        assertTrue(new DominoBlock(0, 9).isMatchingAnyDirection(new DominoBlock(9,8)));
        assertTrue(new DominoBlock(9, 0).isMatchingAnyDirection(new DominoBlock(9,8)));
    }

    @Test
    void matchOtherDominoDisregardingOtherDirection() {
        assertTrue(new DominoBlock(0, 9).isMatchingAnyDirectionOf(new DominoBlock(8,9)));
        assertTrue(new DominoBlock(0, 9).isMatchingAnyDirectionOf(new DominoBlock(9,8)));
    }

    @Test
    void matchOtherDominoDisregardingDirectionOfBoth() {
        assertTrue(new DominoBlock(0, 9).isMatchingAnyField(new DominoBlock(0,9)));
        assertTrue(new DominoBlock(0, 9).isMatchingAnyField(new DominoBlock(9,0)));
        assertTrue(new DominoBlock(9, 0).isMatchingAnyField(new DominoBlock(0,9)));
        assertTrue(new DominoBlock(9, 0).isMatchingAnyField(new DominoBlock(9,0)));
    }

    @Test
    void doNotMatchOtherBlock() {
        assertFalse(new DominoBlock(0, 9).isMatchingAnyField(new DominoBlock(4,6)));
    }

    @Test
    void equalityContractFulfillment() {
        /**
         * reflexive: an object must equal itself
         * symmetric: x.equals(y) must return the same result as y.equals(x)
         * transitive: if x.equals(y) and y.equals(z), then also x.equals(z)
         * consistent: the value of .equals() should change only if a property that is contained in .equals() changes (no randomness allowed)
         */

        DominoBlock block_4_6 = new DominoBlock(4, 6);
        DominoBlock block_6_4 = new DominoBlock(4, 6);
        DominoBlock block_6_4_2 = new DominoBlock(4, 6);
        assertTrue(block_4_6.equals(block_4_6));
        assertTrue(block_4_6.equals(block_4_6));
        assertTrue(block_4_6.equals(block_6_4));
        assertTrue(block_4_6.equals(block_6_4) && block_6_4.equals(block_6_4_2) && block_4_6.equals(block_6_4_2));

        block_4_6.setFieldA(7);
        assertFalse(block_4_6.equals(block_6_4));
    }

}
