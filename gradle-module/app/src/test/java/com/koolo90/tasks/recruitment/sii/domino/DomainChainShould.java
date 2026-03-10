package com.koolo90.tasks.recruitment.sii.domino;

import com.koolo90.framework.test.assertions.Assertions;
import org.junit.jupiter.api.Test;

/**
 * The class will find the longest possible chain of domino blocks.
 * The algorithm is not optimized memory- and complexity-wise
 */
public class DomainChainShould {
    @Test
    void initializeEmptyBoxAndChain() {
        DominoChain chain = new DominoChain();

        Assertions.assertEmpty(chain);
        Assertions.assertEmpty(chain.getBox());
    }

    @Test
    void initializeEmptyChain() {
        DominoChain chain = new DominoChain(new DominoBox(new DominoBlock(0, 9)));

        Assertions.assertEmpty(chain);
        Assertions.assertNotEmpty(chain.getBox());
    }

    @Test
    void pickFirstElement() {
        DominoBlock first = new DominoBlock(0, 9);
        DominoChain chain = new DominoChain(new DominoBox(first));

        chain.pickAsFirst(0);

        Assertions.assertContains(chain, first);
    }

    @Test
    void takeOutOfTheBox() {
        DominoBlock first = new DominoBlock(0, 9);
        DominoChain chain = new DominoChain(new DominoBox(first));

        chain.pickAsFirst(0);

        Assertions.assertDoesNotContain(chain.getBox(), first);
    }

    @Test
    void flipFirstElement() {
        DominoBlock first = new DominoBlock(0, 9);
        DominoChain chain = new DominoChain(new DominoBox(first));
        chain.pickAsFirst(0);

        DominoBlock flipped = first.flip();

        chain.flipFirst();

        Assertions.assertDoesNotContain(chain.getBox(), first);
        Assertions.assertDoesNotContain(chain, first); //this is wrong due to the fact that block equals is wrong.
        Assertions.assertContains(chain, flipped);
    }

    @Test
    void notFlipIfMoreThanOneElement() {
        DominoBlock first = new DominoBlock(0, 9);
        DominoBlock second = new DominoBlock(0, 9);
        DominoChain chain = new DominoChain();
        chain.add(first);
        chain.add(second);
        Assertions.assertThrows(IllegalStateException.class, chain::flipFirst);
    }

    @Test
    void placeSecondElement() {
        DominoBlock first = new DominoBlock(0, 9);
        DominoBlock second = new DominoBlock(0, 9);
        DominoChain chain = new DominoChain(){{
            addToBox(first);
            addToBox(second);
        }};

        chain.pickAsFirst(0);
        chain.pickNext();

        Assertions.assertEquals(chain.getLast(), second.flip());
    }

    @Test
    void cannotPlaceSecondElement() {
        DominoBlock first = new DominoBlock(0, 9);
        DominoBlock second = new DominoBlock(4, 5);
        DominoChain chain = new DominoChain(){{
            addToBox(first);
            addToBox(second);
        }};

        chain.pickAsFirst(0);
        Assertions.assertFalse(chain.pickNext());

        Assertions.assertEquals(chain.getLast(), first);
        Assertions.assertDoesNotContain(chain, second);
    }

    @Test
    void createChainOf2Elements() {
        DominoBlock first = new DominoBlock(0, 9);
        DominoBlock second = new DominoBlock(4, 5);
        DominoBlock third = new DominoBlock(7, 9);

        DominoChain chain = new DominoChain(){{
            addToBox(first);
            addToBox(second);
            addToBox(third);
        }};

        chain.pickAsFirst(0);
        Assertions.assertTrue(chain.pickNext());
        Assertions.assertFalse(chain.pickNext());

        Assertions.assertEquals(chain.getLast(), third.flip());
        Assertions.assertDoesNotContain(chain, second);
    }

    @Test
    void createChainOf3Elements() {
        DominoBlock first = new DominoBlock(0, 9);
        DominoBlock second = new DominoBlock(4, 5);
        DominoBlock third = new DominoBlock(5, 9);
        DominoBlock fourth = new DominoBlock(7, 9);

        DominoChain chain = new DominoChain(){{
            addToBox(first);
            addToBox(second);
            addToBox(third);
            addToBox(fourth);
        }};

        chain.pickAsFirst(0);
        chain.pickNext();
        chain.pickNext();
        boolean expectedNoAddition = chain.pickNext();

        Assertions.assertFalse(expectedNoAddition);
        Assertions.assertEquals(3, chain.size());
        Assertions.assertEquals(second.flip(), chain.getLast());
        Assertions.assertDoesNotContain(chain, second);
        Assertions.assertNotEmpty(chain.getBox());
    }

    @Test
    void findLongestChain() {
        DominoBlock expected_1 = new DominoBlock(3, 5);
        DominoBlock expected_2 = new DominoBlock(5, 6);
        DominoBlock expected_3 = new DominoBlock(4, 6);
        DominoBlock expected_4 = new DominoBlock(3, 4);
        DominoBlock expected_5 = new DominoBlock(2, 3);
        DominoBlock not_expected = new DominoBlock(0,1);
        DominoBox box = new DominoBox() {{
            add(not_expected);
            add(expected_3);
            add(expected_2);
            add(expected_1);
            add(expected_5);
            add(expected_4);
        }};

        DominoChain chain = DominoTable.longestChain(box);

        Assertions.assertEquals(5, chain.size());
        Assertions.assertDoesNotContain(chain, not_expected);
        Assertions.assertContains(chain.getBox(), not_expected);
        Assertions.assertContainsAllInGivenOrder(chain, expected_1, expected_2, expected_3.flip(), expected_4.flip(), expected_5.flip());
    }

}
