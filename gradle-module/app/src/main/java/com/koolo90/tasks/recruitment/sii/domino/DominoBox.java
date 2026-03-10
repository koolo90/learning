package com.koolo90.tasks.recruitment.sii.domino;

import java.util.ArrayList;
import java.util.Arrays;

public class DominoBox extends ArrayList<DominoBlock> {
    public DominoBox(DominoBlock... dominoBlock) {
        this.addAll(Arrays.asList(dominoBlock));
    }

    public void removeAnyOccurence(DominoBlock toBeDeleted) {
        this.removeIf(box -> box.isMatching(toBeDeleted));
        this.removeIf(box -> box.isMatching(toBeDeleted.flip()));
    }
}
