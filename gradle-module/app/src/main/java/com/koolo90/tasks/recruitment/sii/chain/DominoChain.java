package com.koolo90.tasks.recruitment.sii.chain;

import java.util.LinkedList;
import java.util.Optional;

public class DominoChain extends DominoBox {
    LinkedList<DominoBlock> chain = new LinkedList<>();

    public DominoChain(DominoBox dominoBox) {
        this.blocks.addAll(dominoBox.blocks);
    }

    public int getAvailableBlocksCount() {
        return this.blocks.size();
    }

    public boolean getAvailableBlocksContains(DominoBlock dominoBlock) {
        return this.blocks.contains(dominoBlock);
    }

    public boolean pickNextMatching() {
        Optional<DominoBlock> lastOptional = super.takeNextMatching(this.chain.getLast());
        if (lastOptional.isEmpty()) {
            return false;
        }
        DominoBlock dominoBlock = lastOptional.get();
        this.blocks.remove(dominoBlock);
        return this.chain.add(dominoBlock);
    }

    public int lengthOfChain() {
        return this.chain.size();
    }

    public DominoBlock takeFirst() {
        DominoBlock dominoBlock = peekFirst();
        this.blocks.remove(dominoBlock);
        this.chain.add(dominoBlock);
        return dominoBlock;
    }

    public DominoBlock takeFirst(DominoBlock dominoBlock) {
        this.blocks.remove(dominoBlock);
        this.chain.add(dominoBlock);
        return dominoBlock;
    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append(this.getClass().getSimpleName());
        sb.append("#");
        sb.append(this.lengthOfChain());
        sb.append("{");
        this.chain.stream().forEach(dominoBlock -> sb.append(dominoBlock.toString()));
        sb.append("}\n");
        sb.append("Remaining in box: {" + blocks + "}");
        return sb.toString();
    }
}
