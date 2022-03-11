package com.michaelmuther;

public class Submarine extends Ship {

    String name = "Submarine";
    int length = 3;
    char label = 'S';

    public Submarine () {
    }

    @Override
    public int getLength() {
        return length;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public char getLabel() {
        return label;
    }

}
