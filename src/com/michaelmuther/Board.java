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

    public void boardPrint() {
        final char space = ' ';
        final String headerRow = "  1 2 3 4 5 6 7 8 9 10";
        final char[] headerCol = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J'};
        System.out.println(headerRow);
        for (int i = 0; i < rows; i++) {
            System.out.print(headerCol[i]);
            System.out.print(space);
            for (int j = 0; j < columns; j++) {
                System.out.print(boardArray[i][j]);
                System.out.print(space);
            }
            System.out.println();
        }
    }

}
