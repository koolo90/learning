package com.koolo90.tasks.recruitment.sii.chain;

import java.util.*;
import java.util.stream.Collectors;

class DominoBox implements List<DominoBlock> {
    final LinkedList<DominoBlock> blocks;

    public DominoBox() {
        this.blocks = new LinkedList<>();
    }

    public boolean add(DominoBlock dominoBlock) {
        return blocks.add(dominoBlock);
    }

    @Override
    public boolean remove(Object o) {
        return this.blocks.remove(o);
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return this.blocks.containsAll(c);
    }

    @Override
    public boolean addAll(Collection<? extends DominoBlock> c) {
        return false;
    }

    @Override
    public boolean addAll(int index, Collection<? extends DominoBlock> c) {
        return false;
    }

    public void add(List<DominoBlock> list) {
        this.blocks.addAll(list);
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return this.blocks.removeAll(c);
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return this.blocks.retainAll(c);
    }

    @Override
    public void clear() {
        this.blocks.clear();
    }

    @Override
    public boolean equals(Object o) {
        return false;
    }

    @Override
    public int hashCode() {
        return 0;
    }

    @Override
    public DominoBlock get(int index) {
        return null;
    }

    @Override
    public DominoBlock set(int index, DominoBlock element) {
        return null;
    }

    @Override
    public void add(int index, DominoBlock element) {
        this.blocks.add(index, element);
    }

    @Override
    public DominoBlock remove(int index) {
        return this.blocks.remove(index);
    }

    @Override
    public int indexOf(Object o) {
        return this.blocks.indexOf(o);
    }

    @Override
    public int lastIndexOf(Object o) {
        return this.blocks.lastIndexOf(o);
    }

    @Override
    public ListIterator<DominoBlock> listIterator() {
        return this.listIterator();
    }

    @Override
    public ListIterator<DominoBlock> listIterator(int index) {
        return this.listIterator(0);
    }

    @Override
    public List<DominoBlock> subList(int fromIndex, int toIndex) {
        return this.subList(fromIndex, toIndex);
    }

    public void remove(DominoBlock dominoBlock) {
        this.blocks.remove(dominoBlock);
    }

    @Override
    public int size() {
        return this.blocks.size();
    }

    public boolean isEmpty() {
        return this.blocks.isEmpty();
    }

    @Override
    public boolean contains(Object o) {
        return this.blocks.contains(o);
    }

    @Override
    public Iterator<DominoBlock> iterator() {
        return this.blocks.iterator();
    }

    @Override
    public Object[] toArray() {
        return this.blocks.toArray();
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return this.blocks.toArray(a);
    }

    public Optional<DominoBlock> takeNextMatching(DominoBlock bb) {
        return this.blocks.stream().filter(tb -> tb.isMatching(bb)).findFirst();
    }

    public List<DominoBlock> peekAllMatching(DominoBlock baseBlock) {
        return this.blocks.stream().filter(tb -> tb.isMatching(baseBlock)).collect(Collectors.toList());
    }

    public DominoBlock peekFirst() {
        return this.blocks.getFirst();
    }
}
