package com.koolo90.tasks.recruitment.sii.chain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.text.MessageFormat;
import java.util.*;

public class CainCollectionShould {
    DominoBox dominoBox;

    DominoBlock BLOCK_7_10 = new DominoBlock(7, 10);
    DominoBlock BLOCK_1_1 = new DominoBlock(1, 1);
    DominoBlock BLOCK_1_2 = new DominoBlock(1, 2);
    DominoBlock BLOCK_5_6 = new DominoBlock(5, 6);
    DominoBlock BLOCK_7_12 = new DominoBlock(7, 12);
    DominoBlock BLOCK_4_5 = new DominoBlock(4, 5);
    DominoBlock BLOCK_7_6 = new DominoBlock(7, 6);
    DominoBlock BLOCK_3_4 = new DominoBlock(3, 4);

    @BeforeEach
    void initializeBox() {
        dominoBox = new DominoBox(){{
            add(BLOCK_1_1);
            add(BLOCK_1_2);
            add(BLOCK_5_6);
            add(BLOCK_7_10);
            add(BLOCK_7_12);
            add(BLOCK_4_5);
            add(BLOCK_7_6);
            add(BLOCK_3_4);
        }};
    }
    @Test
    void createAllPossibleCombinations() {
        List<DominoChain> possibleDominoChains = new ArrayList<>();
        for (DominoBlock block : dominoBox.blocks) {
            DominoChain dominoChain = new DominoChain(dominoBox);
            dominoChain.takeFirst(block);
            while (dominoChain.pickNextMatching()) {
                dominoChain.pickNextMatching();
            }
            possibleDominoChains.add(dominoChain);
        }

        Comparator<DominoChain> dominoChainLengthComparator = Comparator.comparingInt(DominoChain::lengthOfChain).reversed();
        DominoChain longestChain = possibleDominoChains.stream()
                .min(dominoChainLengthComparator).get();
        String pattern = "Length of longest chain: {0}\nThe chain: ";
        String format = MessageFormat.format(pattern, longestChain.lengthOfChain());
        System.out.println(format);
        System.out.println(longestChain);

        Assertions.assertEquals(6, longestChain.lengthOfChain());
        Assertions.assertTrue(longestChain.containsAll(
                Arrays.asList(BLOCK_7_10, BLOCK_7_12, BLOCK_7_6, BLOCK_5_6, BLOCK_4_5, BLOCK_3_4)
        ));
    }
}
