package com.koolo90.tasks.recruitment.sii.hashmap;

import com.koolo90.framework.test.assertions.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.LinkedList;

public class HashMapImplementationShould {
    @Test
    void shouldAddOnSeparateSlots() {
        OwnHashMap hashMap = new OwnHashMap();
        hashMap.add(22);
        Assertions.assertTrue(hashMap.contains(22));
        int hashed = hashMap.hashOf(22);
        Assertions.assertEquals(2, hashed);
        LinkedList listUnderHash = hashMap.get(hashed);
        Assertions.assertTrue(listUnderHash.contains(22));
        Assertions.assertEquals(1, listUnderHash.size());
        HashMap hashMap2 = new HashMap();
        hashMap2.put(22, 22);
    }


    private class OwnHashMap {
        public static int DEFAULT_SIZE = 5;
        private final int initialSize;
        private LinkedList[] container;

        public OwnHashMap(int initialSize) {
            this.initialSize = initialSize;
            initalizeContainer(initialSize);
        }

        private void initalizeContainer(int initialSize) {
            this.container = new LinkedList[initialSize];
            for(int i = 0; i < initialSize; i++) {
                this.container[i] = new LinkedList<>();
            }
        }

        public OwnHashMap() {
            this(DEFAULT_SIZE);
        }

        public void add(int i) {
            int hash = hash(i);
            this.container[hash].add(i);
        }

        private int hash(int i) {
            return i % initialSize;
        }

        public int hashOf(int i) {
            int hash = hash(i);
            return this.container[hash].contains(i) ? hash : -1;
        }

        public boolean contains(int i) {
            return this.container[hash(i)].contains(i);
        }

        public LinkedList get(int hash) {
            return this.container[hash];
        }
    }
}
