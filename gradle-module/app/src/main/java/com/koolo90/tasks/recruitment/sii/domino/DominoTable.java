package com.koolo90.tasks.recruitment.sii.domino;

import org.jspecify.annotations.NonNull;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

public class DominoTable {
    static @NonNull DominoChain longestChain(DominoBox box) {
        List<DominoChain> chains = new ArrayList<DominoChain>();
        createAllPossibleChains(box, chains);
        return chains.stream().max(Comparator.comparingInt(LinkedList::size)).get();
    }

    private static void createAllPossibleChains(DominoBox box, List<DominoChain> chains) {
        for (DominoBlock base : box) {
            DominoChain dc = new DominoChain(box);
            dc.placeAtFirst(base);
            dc.buildChain();
            chains.add(dc);
        }
    }
}