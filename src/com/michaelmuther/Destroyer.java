package com.michaelmuther;

public class Destroyer extends Ship {

    String name = "Destroyer";
    int length = 2;
    char label = 'D';

    public Destroyer () {
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
