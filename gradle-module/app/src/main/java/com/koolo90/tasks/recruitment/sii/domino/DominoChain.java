package com.koolo90.tasks.recruitment.sii.domino;

import org.jspecify.annotations.NonNull;

import java.util.LinkedList;
import java.util.Optional;

public class DominoChain extends LinkedList<DominoBlock> {
    public String CANNOT_FLIP_FIRST = "Cannot flip first element of a chain with more than one element.";
    private final DominoBox box;

    public DominoChain() {
        this.box = new DominoBox();
    }

    public DominoChain(DominoBox box) {
        this.box = new DominoBox();
        this.box.addAll(box);
    }

    public DominoBox getBox() {
        return this.box;
    }

    public void pickAsFirst(int i) {
        DominoBlock domino = this.box.get(i);
        this.box.remove(domino);
        this.add(domino);
    }

    public void placeAtFirst(DominoBlock base) {
        this.box.remove(base);
        this.addFirst(base);
    }

    public void flipFirst() {
        if(this.size() > 1) {
            throw new IllegalStateException(CANNOT_FLIP_FIRST);
        }
        DominoBlock first = this.getFirst();
        this.remove(first);
        this.add(first.flip());
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Chain: [");
        this.forEach(d -> sb.append("[").append(d).append("]").append("->"));
        sb.append("[#:"+this.size()+"]]").append("\n");
        sb.append("Box: #").append(this.box.size());
        return sb.toString();
    }

    public boolean addToBox(DominoBlock block) {
        return this.box.add(block);
    }

    public boolean pickNext() {
        Optional<DominoBlock> nextRegular = peekNext();
        if(nextRegular.isPresent()) {
            this.box.remove(nextRegular.get());
            return this.add(nextRegular.get());
        }

        Optional<DominoBlock> nextFlipped = peekNextFlipped();
        if(nextFlipped.isPresent()) {
            this.box.remove(nextFlipped.get());
            return this.add(nextFlipped.get().flip());
        }

        return false;
    }

    private @NonNull Optional<DominoBlock> peekNextFlipped() {
        int fieldB = this.getLast().getFieldB();
        Optional<DominoBlock> nextFlipped = this.box.stream()
                .filter(d -> fieldB == d.getFieldB()).findFirst();
        return nextFlipped;
    }

    private @NonNull Optional<DominoBlock> peekNext() {
        int fieldB = this.getFirst().getFieldB();
        Optional<DominoBlock> nextRegular = this.box.stream()
                .filter(d -> fieldB == d.getFieldA()).findFirst();
        return nextRegular;
    }

    public void buildChain() {
        while (this.pickNext()) {}
    }
}