package com.koolo90.tasks.recruitment.sii.reverse;

import com.koolo90.framework.test.assertions.Assertions;
import org.jspecify.annotations.NonNull;
import org.junit.jupiter.api.Test;

public class StringReverseShould {
    @Test
    void reverseStringAsCharArray() {
        String toReverse = "kapusta";
        char[] reversedKapusta = invert(toReverse);
        Assertions.assertEquals("atsupak", new String(reversedKapusta));
    }

    @Test
    void reverseStringAsString() {
        String toReverse = "kapusta";
        String reversed = "";
        for (int i = 0; i < toReverse.length(); i++) {
            reversed = toReverse.charAt(i) + reversed;
        }
        Assertions.assertEquals("atsupak", reversed);
    }

    @Test
    void reverseStringAsStringWithStringBuilder() {
        String toReverse = "kapusta";
        StringBuilder reversed = new StringBuilder();
        for (int i = 0; i < toReverse.length(); i++) {
            reversed.insert(0, toReverse.charAt(i));
        }
        Assertions.assertEquals("atsupak", reversed.toString());
    }

    private char @NonNull [] invert(String toReverse) {
        char[] kapusta = toReverse.toCharArray();
        char[] reversedKapusta = new char[kapusta.length];
        for (int i = 0; i < kapusta.length; i++) {
            reversedKapusta[i] = kapusta[kapusta.length-1-i];
        }
        return reversedKapusta;
    }
}
