package com.koolo90.tasks.recruitment.sii.separate.chaining;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public final class SeparateChaining {
    private final int baseForHashFucntion;
    List<List<Integer>> integer;

    public SeparateChaining(int i) {
        this.baseForHashFucntion = i;
        integer = new ArrayList<>(baseForHashFucntion);
        IntStream.range(0, 5).forEach(
                index -> integer.add(index, new ArrayList<>())
        );
    }

    public boolean add(int i) {
        int index = this.hash(i);
        return this.integer.get(index).add(i);
    }

    private int hash(int i) {
        return i % baseForHashFucntion;
    }

    private void initializeIfNeeded(int index) {
        if (this.integer.isEmpty()) {
            this.integer.add(index, new ArrayList<>());
        }
    }

    public List<Integer> get(int i) {
        return this.integer.get(i);
    }

    public int getIndex(int i) {
        return this.hash(i);
    }

    public int getEmptyIndices() {
        return this.integer.stream().map(List::size).reduce(0, Integer::sum);
    }

    public int getSize() {
        return this.integer.stream().mapToInt(List::size).sum();
    }
}
