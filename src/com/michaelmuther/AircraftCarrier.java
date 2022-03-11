package com.michaelmuther;

public class AircraftCarrier extends Ship {

    String name = "Aircraft Carrier";
    int length = 5;
    char label = 'A';

    public AircraftCarrier () {
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
