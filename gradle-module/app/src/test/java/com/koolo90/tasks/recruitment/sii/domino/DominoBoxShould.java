package com.koolo90.tasks.recruitment.sii.domino;

import com.koolo90.framework.test.assertions.Assertions;
import org.junit.jupiter.api.Test;

public class DominoBoxShould {
    @Test
    void beEmpty() {
        new DominoBox();
        Assertions.assertTrue(new DominoBox().isEmpty());
    }

    @Test
    void addAnyBlock() {
        DominoBox dominos = new DominoBox();
        DominoBlock box_4_8 = new DominoBlock(4, 8);
        dominos.add(box_4_8);
        Assertions.assertFalse(dominos.isEmpty());
        Assertions.assertContains(dominos, box_4_8);
    }

    @Test
    void removeBlockDisregardingDirection() {
        DominoBox dominos = new DominoBox();
        DominoBlock box_4_8 = new DominoBlock(4, 8);
        DominoBlock box_8_4 = new DominoBlock(8, 4);

        dominos.add(box_4_8);
        dominos.removeAnyOccurence(box_8_4);
        dominos.removeAnyOccurence(box_8_4);

        Assertions.assertTrue(dominos.isEmpty());
    }

    @Test
    void retainBlockDisregardingDirection() {
        DominoBox dominos = new DominoBox();
        DominoBlock box_4_8 = new DominoBlock(4, 8);
        DominoBlock box_7_4 = new DominoBlock(7, 4);

        dominos.add(box_4_8);
        dominos.remove(box_7_4);

        Assertions.assertFalse(dominos.isEmpty());
    }

    @Test
    void dominoBoxShouldContaineDuplicates() {
        DominoBox dominos = new DominoBox();
        DominoBlock box_4_8 = new DominoBlock(4, 8);
        DominoBlock box_8_4 = new DominoBlock(8, 4);
        DominoBlock box_8_4_2 = new DominoBlock(8, 4);

        dominos.add(box_4_8);
        dominos.add(box_8_4);
        dominos.add(box_8_4_2);

        Assertions.assertFalse(dominos.isEmpty());
        Assertions.assertEquals(dominos.size(), 3);
    }

    @Test
    void dominoBoxShouldContaineDuplicatesEvenAfterRemoval() {
        DominoBox dominos = new DominoBox();
        DominoBlock box_4_8 = new DominoBlock(4, 8);
        DominoBlock box_8_4 = new DominoBlock(8, 4);
        DominoBlock box_8_4_2 = new DominoBlock(8, 4);
        DominoBlock box_8_4_3 = new DominoBlock(8, 4);

        dominos.add(box_4_8);
        dominos.add(box_8_4);
        dominos.add(box_8_4_2);
        dominos.remove(box_8_4_3);

        Assertions.assertFalse(dominos.isEmpty());
        Assertions.assertEquals(dominos.size(), 2);
        Assertions.assertContains(dominos, box_8_4_2);
    }
}
