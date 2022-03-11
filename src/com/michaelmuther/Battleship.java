package com.michaelmuther;

public class Battleship extends Ship {

    String name = "Battleship";
    int length = 4;
    char label = 'B';

    public Battleship () {
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
