package com.koolo90.tasks.recruitment.sii.chain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

public class DominoChainShould {
    DominoBlock FIRST_BLOCK = new DominoBlock(1, 2);
    DominoBlock SECOND_BLOCK = new DominoBlock(2, 3);
    DominoBlock THIRD_BLOCK = new DominoBlock(4, 5);
    DominoBlock FOURTH_BLOCK = new DominoBlock(3, 1);
    DominoBlock FIFTH_BLOCK = new DominoBlock(2, 1);
    DominoBox dominoBox = new DominoBox() {{
        add(FIRST_BLOCK); //1
        add(SECOND_BLOCK); //2
        add(THIRD_BLOCK);
        add(FOURTH_BLOCK); //3 End
        add(FIFTH_BLOCK); //3 End
    }};

    DominoChain dominoChain;

    @BeforeEach
    void initializeChain() {
        dominoChain = new DominoChain(dominoBox);
    }


    @Test
    void haveAllElementsAvailable() {
        int originalBoxSize = dominoBox.size();
        int targetBoxSize = dominoChain.getAvailableBlocksCount();
        Assertions.assertEquals(originalBoxSize, targetBoxSize);
    }

    @Test
    void haveAllButOneElementsAvailable() {
        int originalBoxSize = dominoBox.size();
        int expectedBoxSizeAfterAction = originalBoxSize - 1;
        int actualBoxSize = dominoChain.getAvailableBlocksCount();
        Assertions.assertEquals(originalBoxSize, actualBoxSize, "Precondition check");

        DominoBlock dominoBlock = dominoChain.takeFirst();
        int actualBoxSizeAfterAction = dominoChain.getAvailableBlocksCount();
        Assertions.assertFalse(dominoChain.getAvailableBlocksContains(dominoBlock));
        Assertions.assertNotEquals(originalBoxSize, actualBoxSizeAfterAction);
        Assertions.assertEquals(expectedBoxSizeAfterAction, actualBoxSizeAfterAction);
    }

    @Test
    void selectAllMatchingToSelected() {
        DominoBlock matchingBlock1 = SECOND_BLOCK;
        DominoBlock matchingBlock2 = FIFTH_BLOCK;
        int originalBoxSize = dominoBox.size();
        int actualBoxSize = dominoChain.getAvailableBlocksCount();
        Assertions.assertEquals(originalBoxSize, actualBoxSize, "Precondition check");

        DominoBlock dominoBlock = dominoChain.peekFirst();
        List<DominoBlock> matchingBlocks = dominoChain.peekAllMatching(dominoBlock);
        Assertions.assertTrue(matchingBlocks.contains(matchingBlock1));
        Assertions.assertTrue(matchingBlocks.contains(matchingBlock2));
    }

    @Test
    void putTwoElementsToChain() {
        int originalBoxSize = dominoBox.size();
        int actualBoxSize = dominoChain.getAvailableBlocksCount();
        Assertions.assertEquals(originalBoxSize, actualBoxSize, "Precondition check");

        dominoChain.takeFirst();
        Assertions.assertTrue(dominoChain.pickNextMatching());
        Assertions.assertTrue(dominoChain.pickNextMatching());
        Assertions.assertTrue(dominoChain.pickNextMatching());
        Assertions.assertFalse(dominoChain.pickNextMatching());

        Assertions.assertEquals(1, dominoChain.getAvailableBlocksCount());
        Assertions.assertEquals(4, dominoChain.lengthOfChain());
    }
}