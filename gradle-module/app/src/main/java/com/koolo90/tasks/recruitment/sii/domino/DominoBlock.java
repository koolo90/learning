package com.koolo90.tasks.recruitment.sii.domino;

import java.text.MessageFormat;
import java.util.Objects;

public class DominoBlock {
    private static final String format = "Field %s must be in range [%d;%d], but was %d!";
    private static final int FIELD_MIN_INCL = 0;
    private static final int FIELD_MAX_ECL = 10;

    private int fieldA;
    private int fieldB;

    public DominoBlock(int fieldA, int fieldB) {
        this.setFieldA(fieldA);
        this.setFieldB(fieldB);
    }

    public int getFieldA() {
        return fieldA;
    }

    public void setFieldA(int fieldA) {
        validateValue(fieldA);
        this.fieldA = fieldA;
    }

    public int getFieldB() {
        return fieldB;
    }

    public void setFieldB(int fieldB) {
        validateValue(fieldB);
        this.fieldB = fieldB;
    }

    private static void validateValue(int fieldA) {
        if (fieldA < FIELD_MIN_INCL || fieldA >= FIELD_MAX_ECL) {
            throw new IllegalArgumentException(MessageFormat.format(format,
                    "A", FIELD_MIN_INCL, FIELD_MAX_ECL, fieldA));
        }
    }

    public boolean isMatching(int number) {
        this.validateValue(number);
        return this.fieldB == number;
    }

    public DominoBlock flip() {
        return new DominoBlock(this.fieldB, this.fieldA);
    }

    public boolean isMatching(DominoBlock otherDomino) {
        return isMatching(otherDomino.getFieldA());
    }

    public boolean isMatchingAnyDirection(DominoBlock otherDomino) {
        return this.isMatching(otherDomino.getFieldA()) || this.flip().isMatching(otherDomino.getFieldA());
    }

    public boolean isMatchingAnyDirectionOf(DominoBlock otherDomino) {
        return this.isMatching(otherDomino.getFieldA()) || this.isMatching(otherDomino.flip().getFieldA());
    }

    public boolean isMatchingAnyField(DominoBlock otherDomino) {
        return isMatchingAnyDirection(otherDomino) || isMatchingAnyDirectionOf(otherDomino);
    }

    @Override
    public boolean equals(Object other) {
        if (other == null || getClass() != other.getClass()) return false;
        DominoBlock o = (DominoBlock) other, t = this;
        return  this.fieldA == o.fieldA && t.fieldB == o.fieldB;

    }

    @Override
    public int hashCode() {
        return Objects.hash(fieldA, fieldB);
    }

    @Override
    public String toString() {
        return "["+fieldA+","+fieldB+"]";
    }

    public boolean isMatchingSecondField(DominoBlock domino) {
        return domino.isMatchingAny(fieldB);
    }

    private boolean isMatchingAny(int fieldB) {
        return false;
    }
}
