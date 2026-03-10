package com.koolo90.framework.test.assertions;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

public class Assertions extends org.junit.jupiter.api.Assertions {
    public static void assertEmpty(Collection collection) {
        assertTrue(collection.isEmpty());
    }

    public static void assertNotEmpty(Collection collection) {
        assertFalse(collection.isEmpty());
    }

    public static <T> void assertContains(Collection<? extends T> collection, T element) {
        assertTrue(collection.contains(element));
    }

    public static <T> void assertDoesNotContain(Collection<? extends T> collection, T element) {
        assertFalse(collection.contains(element));
    }

    public static void assertContainsAllInGivenOrder(Collection<?> collection, Object... elements) {
        for(int i = 0; i < elements.length; i++) {
            assertEquals(collection.toArray()[i], elements[i], "Element at index " + i + " is not as expected");
        }
    }

    public static void assertContains(String text, String expectedContent) {
        Assertions.assertTrue(text.contains(expectedContent));
    }

    public static void assertDoesNotContain(String text, String expectedContent) {
        Assertions.assertFalse(text.contains(expectedContent));
    }

    public static void assertContainsAll(int[] ints, int[] ints1) {
        for(int a : ints) {
            Assertions.assertTrue(Collections.singletonList(ints1).contains(a));
        }
    }
}
