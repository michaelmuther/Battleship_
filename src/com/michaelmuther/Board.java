package com.michaelmuther;

import java.util.Arrays;

public class Board {

    final int rows = 10;
    final int columns = 10;

    final char FOG_OF_WAR = '~';

    char[][] boardArray = new char[rows][columns];

    public void boardInit() {
        for (int i = 0; i < boardArray.length; i++) {
            Arrays.fill(boardArray[i], FOG_OF_WAR);
        }
    }

    public void printShipBoard() {
        final char SPACE = ' ';
        final char FOG_OF_WAR = '~';
        final char SHIP_CELL = 'O';
        final String headerRow = "  1 2 3 4 5 6 7 8 9 10";
        final char[] headerCol = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J'};
        System.out.println(headerRow);
        for (int i = 0; i < rows; i++) {
            System.out.print(headerCol[i]);
            System.out.print(SPACE);
            for (int j = 0; j < columns; j++) {
                char temp = boardArray[i][j];
                temp = temp == 'A' || temp == 'B' || temp == 'C' || temp == 'D' || temp == 'S' ? SHIP_CELL : FOG_OF_WAR;
                System.out.print(temp);
                System.out.print(SPACE);
            }
            System.out.println();
        }
    }

}
