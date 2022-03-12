package com.michaelmuther;

public class ShotInput extends Input{

    char[][] shipBoardArray;

    final char HIT = 'X';
    final char MISS = 'M';

    final private String START_MESSAGE_STR = "\nThe game starts!";
    final private String TAKE_SHOT_STR = "\nTake a shot!";
    final private String ERROR = "\nError! You entered the wrong coordinates! Try again:\n";
    final private String HIT_STR = "\nYou hit a ship!";
    final private String MISS_STR = "\nYou missed!";

    private String input;

    final String[][] board = {
            {"A1", "A2", "A3", "A4", "A5", "A6", "A7", "A8", "A9", "A10"},
            {"B1", "B2", "B3", "B4", "B5", "B6", "B7", "B8", "B9", "B10"},
            {"C1", "C2", "C3", "C4", "C5", "C6", "C7", "C8", "C9", "C10"},
            {"D1", "D2", "D3", "D4", "D5", "D6", "D7", "D8", "D9", "D10"},
            {"E1", "E2", "E3", "E4", "E5", "E6", "E7", "E8", "E9", "E10"},
            {"F1", "F2", "F3", "F4", "F5", "F6", "F7", "F8", "F9", "F10"},
            {"G1", "G2", "G3", "G4", "G5", "G6", "G7", "G8", "G9", "G10"},
            {"H1", "H2", "H3", "H4", "H5", "H6", "H7", "H8", "H9", "H10"},
            {"I1", "I2", "I3", "I4", "I5", "I6", "I7", "I8", "I9", "I10"},
            {"J1", "J2", "J3", "J4", "J5", "J6", "J7", "J8", "J9", "J10"}
    };


    public ShotInput (char[][] boardArray) {
        this.shipBoardArray = boardArray;
        enterShotCoordinate();
    }

    public void enterShotCoordinate() {
        System.out.println(START_MESSAGE_STR);
        playerBoardPrintFinal();
        System.out.println(TAKE_SHOT_STR);
        boolean validShot = false;
        boolean hit = false;
        char current;
        do {
            input = scanner.nextLine();
            validShot = testShotCoordinate(input.trim());
        } while (!validShot);

        int[] indices = convertCoordinatesToIndexArray(input);
        current = shipBoardArray[indices[0]][indices[1]];
        if (current == '#' || current == '~') {
            shipBoardArray[indices[0]][indices[1]] = MISS;
            playerBoardPrintFinal();
            System.out.println(MISS_STR);
        } else {
            shipBoardArray[indices[0]][indices[1]] = HIT;
            playerBoardPrintFinal();
            System.out.println(HIT_STR);
        }
    }

    public boolean testShotCoordinate(String coordinate) {
        for (String[] strings : board) {
            for (String string : strings) {
//                if (string.equals(coordinate)) { // allows only upper case entries
                if (string.equals(coordinate.toUpperCase())) { // allows lower and upper case entries
                    return true;
                }
            }
        }
        System.out.println(ERROR);
        return false;
    }

    public int[] convertCoordinatesToIndexArray(String coordinate) {
        int[] indices = new int[2];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
//                if (board[i][j].equals(coordinate)) { // allow only upper case entries
                if (board[i][j].equals(coordinate.toUpperCase())) { // allows lower and upper case entries
                    indices[0] = i;
                    indices[1] = j;
                }
            }
        }
        return indices;
    }

    public void playerBoardPrintFinal() {
        final char space = ' ';
        final String headerRow = "  1 2 3 4 5 6 7 8 9 10";
        final char[] headerCol = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J'};
        System.out.println("\n" + headerRow);
        for (int i = 0; i < shipBoardArray.length; i++) {
            System.out.print(headerCol[i]);
            System.out.print(space);
            for (int j = 0; j < shipBoardArray[i].length; j++) {
                if(shipBoardArray[i][j] == '#') {
                    System.out.print('~');
                } else if (shipBoardArray[i][j] == 'M' || shipBoardArray[i][j] == 'X') {
                    System.out.print(shipBoardArray[i][j]);
                } else if (shipBoardArray[i][j] != '~') {
                    System.out.print('O');
                } else {
                    System.out.print('~');
                }
                System.out.print(space);
            }
            System.out.println();
        }
    }

}
