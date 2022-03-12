package com.michaelmuther;

public class ShipBoard extends Board{

    Ship aircraftCarrier = new AircraftCarrier();
    Ship battleship = new Battleship();
    Ship submarine = new Submarine();
    Ship cruiser = new Cruiser();
    Ship destroyer= new Destroyer();
    Ship[] ships = {aircraftCarrier, battleship, submarine, cruiser, destroyer};

    public ShipBoard() {
        boardInit();
//        printShipBoardRaw();
        ShipInput shipInput = new ShipInput(boardArray, ships);
        printShipBoard();
    }

    public char[][] getBoard() {
        return this.boardArray;
    }
}

//    public void printShipBoardRaw() {
//        final char SPACE = ' ';
//        final String headerRow = "  1 2 3 4 5 6 7 8 9 10";
//        final char[] headerCol = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J'};
//        System.out.println(headerRow);
//        for (int i = 0; i < rows; i++) {
//            System.out.print(headerCol[i]);
//            System.out.print(SPACE);
//            for (int j = 0; j < columns; j++) {
//                System.out.print(boardArray[i][j]);
//                System.out.print(SPACE);
//            }
//            System.out.println();
//        }
//    }

//    public void printShipBoard() {
//        final char SPACE = ' ';
//        final char FOG_OF_WAR = '~';
//        final char SHIP_CELL = 'O';
//        final String headerRow = "  1 2 3 4 5 6 7 8 9 10";
//        final char[] headerCol = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J'};
//        System.out.println(headerRow);
//        for (int i = 0; i < rows; i++) {
//            System.out.print(headerCol[i]);
//            System.out.print(SPACE);
//            for (int j = 0; j < columns; j++) {
//                char temp = boardArray[i][j];
//                temp = temp == 'A' || temp == 'B' || temp == 'C' || temp == 'D' || temp == 'S' ? SHIP_CELL : FOG_OF_WAR;
//                System.out.print(temp);
//                System.out.print(SPACE);
//            }
//            System.out.println();
//        }
//    }