package com.koolo90.tasks.recruitment.sii.chain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

public class DominoBoxShould {
    @Test
    void addDominoBlocks() {
        DominoBox dominoBox = new DominoBox();
        dominoBox.add(new DominoBlock(1,2));
        Assertions.assertFalse(dominoBox.isEmpty());
        Assertions.assertEquals(1, dominoBox.size());
    }

    @Test
    void removeDominoBlocks() {
        DominoBox dominoBox = new DominoBox();
        DominoBlock dominoBlock = new DominoBlock(1, 2);

        dominoBox.add(dominoBlock);
        Assertions.assertEquals(1, dominoBox.size(),  "Precondition check");

        dominoBox.remove(dominoBlock);
        Assertions.assertTrue(dominoBox.isEmpty());
    }

    @Test
    void addAllDominoBlocks() {
        DominoBox dominoBox = new DominoBox();
        DominoBlock dominoBlock1 = new DominoBlock(1, 2);
        DominoBlock dominoBlock2 = new DominoBlock(1, 2);
        DominoBlock dominoBlock3 = new DominoBlock(1, 2);
        List<DominoBlock> list = Arrays.asList(dominoBlock1, dominoBlock2, dominoBlock3);

        dominoBox.add(list);
        Assertions.assertEquals(3, dominoBox.size(),  "Precondition check");
        Assertions.assertFalse(dominoBox.isEmpty());
    }

}
