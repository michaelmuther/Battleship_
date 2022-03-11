package com.michaelmuther;

public class Ship {

    String name;
    String firstCoordinate;
    String secondCoordinate;
    int length;
    char label;
    boolean isActive = true;

    public Ship() {
    }

    public int getLength() {
        return length;
    }

    public String getName() {
        return name;
    }

    public char getLabel() {
        return label;
    }

}
