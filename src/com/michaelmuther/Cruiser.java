package com.michaelmuther;

public class Cruiser extends Ship {

    String name = "Cruiser";
    int length = 3;
    char label = 'C';

    public Cruiser () {
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
