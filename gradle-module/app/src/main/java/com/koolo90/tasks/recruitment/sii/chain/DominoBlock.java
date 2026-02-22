package com.koolo90.tasks.recruitment.sii.chain;

public class DominoBlock {
    private static String valueErrorMessage = "DominoBlock cannot have a value less than 0 or greater than 9";
    private static IllegalArgumentException illegalArgumentException = new IllegalArgumentException(valueErrorMessage);

    private int a;
    private int b;

    //Getters
    public int getA() {
        return a;
    }

    public int getB() {
        return b;
    }

    //Setters
    public void setA(int a) {
        if(isIllegalValue(a)) {
            throw illegalArgumentException;
        }
        this.a = a;
    }

    private boolean isIllegalValue(int a) {
        return a > 9 || a < 0;
    }

    public void setB(int b) {
        if(isIllegalValue(a)) {
            throw illegalArgumentException;
        }
        this.b = b;
    }
    //Constructor
    public DominoBlock(int a, int b) {
        this.setA(a);
        this.setB(b);
    }

    //Public methods
    public boolean isMatching(int c){
        return b == c;
    }

    public boolean isMatching(DominoBlock otherBlock) {
        //Take this.B and compare it with otherBlock.A
        //If there is no match, flip the other block and compare this.B to otherBlock.A
        boolean matching_1 = this.isMatching(otherBlock.a);
        otherBlock.flip();
        boolean matching_2 = this.isMatching(otherBlock.a);
        return matching_1 || matching_2;
    }

    public void flip() {
        int tmp = a;
        this.a = b;
        this.b = tmp;
    }

    public String toString(){
        return "[" + a + "," + b + "]";
    }

    public int hashCode() {
        return a * 1 + b * 10;
    }

    //Protected methods
    //Private methods
}
