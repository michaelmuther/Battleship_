package com.michaelmuther;

// takes in input and verifies input
public class ShipInput extends Input{

    // main function for entering coordinates for player's board
    // needs refactor for functional decomposition

    private char[][] shipBoardArray;
    private Ship[] ships;

    final char BUFFER_CELL = '#';
    final char SHIP_CELL = 'O';

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

    private String input;

    final String ERROR = "\nError! ";
    final String TRY_AGAIN = "Try again:\n";
    final String ERROR_LENGTH = "Wrong length of the "; // length of data entered does not match length of ship
    final String ERROR_LOCATION = "Wrong ship location! "; // coordinates are not horizontal or vertical
    final String ERROR_OVERLAP = "You placed it too close to another one. ";

    public ShipInput(char[][] boardArray, Ship[] ships) { // data from client object
        this.shipBoardArray = boardArray;
        this.ships = ships;
        enterShipCoordinates();
    }

    public void enterShipCoordinates() {
        final String MENU_INPUT_1 = "Enter the coordinates of the %s (%s cells):\n";
        for (Ship ship : ships) {
            boardPrintFinal();
            boolean validEntry = false;
            System.out.printf("\n" + MENU_INPUT_1, ship.getName(), ship.getLength()); // text for coordinate input
            do { // do loop for validEntry
                boolean horizontal = false;
                boolean vertical = false;
                int[] firstCoordinate = {0,0};
                int[] secondCoordinate = {0,0};
                input = scanner.nextLine(); // input line as String
                String[] inputArray = input.trim().split("\\s+"); // split by one or more spaces, beginning and ending spaces trimmed

                // check 1 to make sure there are exactly two inputs separated by a space.
                validEntry = inputStringArrayLengthIsValid(inputArray);

                // check 2 to make sure the two inputs are part of the board
                if(validEntry) {
                    validEntry = testCoordinate(inputArray[0]) && testCoordinate(inputArray[1]); // short circuits; the && is not complete if first && is false
                }

                // check 3 to make sure the two inputs are either horizontal or vertical.  First converts String inputs in two int arrays of indices.
                if(validEntry) {
                    firstCoordinate = convertCoordinatesToIndexArray(inputArray[0]);
                    secondCoordinate = convertCoordinatesToIndexArray(inputArray[1]);
                    horizontal = coordinatesHorizontallyAligned(firstCoordinate, secondCoordinate);
                    vertical = coordinatesVerticallyAligned(firstCoordinate, secondCoordinate);
                    if(!horizontal && !vertical) {
                        validEntry = false;
                        System.out.println(ERROR + ERROR_LOCATION + TRY_AGAIN);
                    }
                }

                // check 4 to make sure the length of the inputs is the same as for the ship
                if(validEntry) {
                    int length = lengthOfCoordinates(firstCoordinate, secondCoordinate);
                    if(length != ship.getLength()) {
                        System.out.println(ERROR + ERROR_LENGTH + ship.getName() + "! " + TRY_AGAIN);
                        validEntry = false;
                    }
                }

                // check 5 to make sure the ship input does not overlap another ship or the ship's buffer
                if(validEntry) {
                    if(!checkShipPlacementCoordinatesValid(firstCoordinate, secondCoordinate)) {
                        System.out.println(ERROR + ERROR_OVERLAP + TRY_AGAIN);
                        validEntry = false;
                    }
                }

                // Final step is to update the shipBoardArray with the input and buffer around ship
                if(validEntry) {
                    int[] indices = proposedCells(firstCoordinate, secondCoordinate);
                    if (horizontal) {
                        for (int index : indices) {
                            //update with ship label:
                            shipBoardArray[firstCoordinate[0]][index] = ship.getLabel();
                            // horizontal buffers:
                            if (firstCoordinate[0] <= 8) {
                                shipBoardArray[firstCoordinate[0] + 1][index] = BUFFER_CELL;
                            }
                            if (firstCoordinate[0] >= 1) {
                                shipBoardArray[firstCoordinate[0] - 1][index] = BUFFER_CELL;
                            }
                        }
                        // end caps:
                        if(indices[0] >= 1) {
                            shipBoardArray[firstCoordinate[0]][indices[0] - 1] = BUFFER_CELL;
                        }
                        if(indices[indices.length - 1] <= 8) {
                            shipBoardArray[firstCoordinate[0]][indices[indices.length - 1] + 1] = BUFFER_CELL;
                        }
                        // corners:
                        if(firstCoordinate[0] <= 8 ) {
                            // corner buffers below:
                            if(indices[0] >= 1) {
                                shipBoardArray[firstCoordinate[0] + 1][indices[0] - 1] = BUFFER_CELL;
                            }
                            if(indices[indices.length - 1] <= 8) {
                                shipBoardArray[firstCoordinate[0] + 1][indices[indices.length - 1] + 1] = BUFFER_CELL;
                            }
                        }
                        if(firstCoordinate[0] >= 1) {
                            // corner buffers above:
                            if(indices[0] >= 1) {
                                shipBoardArray[firstCoordinate[0] - 1][indices[0] - 1] = BUFFER_CELL;
                            }
                            if(indices[indices.length - 1] <= 8) {
                                shipBoardArray[firstCoordinate[0] - 1][indices[indices.length - 1] + 1] = BUFFER_CELL;
                            }
                        }

                    } else {
                        for (int index : indices) {
                            //update with ship label:
                            shipBoardArray[index][firstCoordinate[1]] = ship.getLabel();
                            //vertical buffers:
                            if (firstCoordinate[1] <= 8) {
                                shipBoardArray[index][firstCoordinate[1] + 1] = BUFFER_CELL;
                            }
                            if (firstCoordinate[1] >= 1) {
                                shipBoardArray[index][firstCoordinate[1] - 1] = BUFFER_CELL;
                            }
                        }
                        // end caps
                        if(indices[0] >= 1) {
//                                shipBoardArray[firstCoordinate[0]][indices[0] - 1] = BUFFER_CELL;// copy
                            shipBoardArray[indices[0] - 1][firstCoordinate[1]] = BUFFER_CELL;// final
                        }
                        if(indices[indices.length - 1] <= 8) {
//                                shipBoardArray[firstCoordinate[0]][indices[indices.length - 1] + 1] = BUFFER_CELL; // copy
                            shipBoardArray[indices[indices.length - 1] + 1][firstCoordinate[1]] = BUFFER_CELL; // final
                        }
                        // REFACTOR FOR VERTICAL SHIP:
                        if(firstCoordinate[0] <= 8 ) {
                            // corner buffers below:
                            if(indices[0] >= 1) {
//                                shipBoardArray[firstCoordinate[0] + 1][indices[0] - 1] = BUFFER_CELL; //copy
                                shipBoardArray[indices[0] - 1][firstCoordinate[1] + 1] = BUFFER_CELL; //final
                            }
                            if(indices[indices.length - 1] <= 8) {
//                                shipBoardArray[firstCoordinate[0] + 1][indices[indices.length - 1] + 1] = BUFFER_CELL; // copy
                                shipBoardArray[indices[indices.length - 1] + 1][firstCoordinate[1] + 1] = BUFFER_CELL; // final
                            }
                        }
                        if(firstCoordinate[0] >= 1) {
                            // corner buffers above:
                            if(indices[0] >= 1) {
//                                shipBoardArray[firstCoordinate[0] - 1][indices[0] - 1] = BUFFER_CELL; // copy
                                shipBoardArray[indices[0] - 1][firstCoordinate[1] - 1] = BUFFER_CELL;
                            }
                            if(indices[indices.length - 1] <= 8) {
//                                shipBoardArray[firstCoordinate[0] - 1][indices[indices.length - 1] + 1] = BUFFER_CELL; // copy
                                shipBoardArray[indices[indices.length - 1] + 1][firstCoordinate[1] - 1] = BUFFER_CELL;
                            }
                        }
                    }
                }
            } while (!validEntry); // end do while loop for entry of ships
//            boardPrintFinal();
        } // end enhanced for loop of ships array
        System.out.println();
    } // end enterShipCoordinates

    public boolean inputStringArrayLengthIsValid (String[] inputArray) {
        if (inputArray.length != 2) {
            System.out.println(ERROR + TRY_AGAIN);
            return false;
        }
        return true;
    }

    public boolean checkShipPlacementCoordinatesValid(int[] firstCoordinates, int[] secondCoordinates) {
        int[] indices = proposedCells(firstCoordinates, secondCoordinates);
        // checks to see if the coordinates are horizontally aligned, if not, then different for loop
        if (coordinatesHorizontallyAligned(firstCoordinates, secondCoordinates)) {
            for (int index : indices) {
                if (shipBoardArray[firstCoordinates[0]][index] != '~') {
                    return false;
                }
            }
        } else {
            for (int index : indices) {
                if (shipBoardArray[index][firstCoordinates[1]] != '~') {
                    return false;
                }
            }
        }
        return true;
    }

    // helper functions for placement check
    // final
    public boolean coordinatesHorizontallyAligned(int[] firstCoordinates, int[] secondCoordinates) {
        return firstCoordinates[0] == secondCoordinates[0];
    }
    public boolean coordinatesVerticallyAligned(int[] firstCoordinates, int[] secondCoordinates) {
        return firstCoordinates[1] == secondCoordinates[1];
    }

    // returns negative one is length is diagonal
    // also can check if not horizontal or vertical
    //final
    public int lengthOfCoordinates(int[] firstCoordinates, int[] secondCoordinates) {
        if (coordinatesVerticallyAligned(firstCoordinates, secondCoordinates)) {
            return Math.abs(firstCoordinates[0] - secondCoordinates[0]) + 1;
        } else if (coordinatesHorizontallyAligned(firstCoordinates, secondCoordinates)){
            return Math.abs(firstCoordinates[1] - secondCoordinates[1]) + 1;
        } else {
            return -1;
        }
    }

    // converts the inputs into int[] of indices for the shipBoardArray
    // final
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

    // checks string input of coordinates to see if it is a valid input
    // final
    public boolean testCoordinate(String coordinate) {
        for (String[] strings : board) {
            for (String string : strings) {
//                if (string.equals(coordinate)) { // allows only upper case entries
                if (string.equals(coordinate.toUpperCase())) { // allows lower and upper case entries
                    return true;
                }
            }
        }
        System.out.println(ERROR + " \"" + coordinate + "\" is not a valid input.");
        return false;
    }

    // helper function to convert entered coordinates (first converted from string to length two array of ints)
    // to the array for the cells that are not changing.  Either the first index or second index will
    // not change, so only a single array of ints needs to be returned.  Change name of this function?
    public int[] proposedCells(int[] firstCoordinates, int[] secondCoordinates) {
        int length = lengthOfCoordinates(firstCoordinates, secondCoordinates);
        int[] results = new int[length];
        if(coordinatesHorizontallyAligned(firstCoordinates, secondCoordinates)) {
            if (firstCoordinates[1] < secondCoordinates[1]) { // the coordinates can be either way: first greater or second greater
                for (int i = 0; i < length; i++) {
                    results[i] = firstCoordinates[1] + i;
                }
            } else {
                for (int i = 0; i < length; i++) {
                    results[i] = secondCoordinates[1] + i;
                }
            }
        } else {
            if (firstCoordinates[0] < secondCoordinates[0]) {
                for (int i = 0; i < length; i++) {
                    results[i] = firstCoordinates[0] + i;
                }
            } else {
                for (int i = 0; i < length; i++) {
                    results[i] = secondCoordinates[0] + i;
                }
            }
        }
        return results;
    }

    public void boardPrintRaw() {
        final char space = ' ';
        final String headerRow = "  1 2 3 4 5 6 7 8 9 10";
        final char[] headerCol = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J'};
        System.out.println("\n" + headerRow);
        for (int i = 0; i < shipBoardArray.length; i++) {
            System.out.print(headerCol[i]);
            System.out.print(space);
            for (int j = 0; j < shipBoardArray[i].length; j++) {
                System.out.print(shipBoardArray[i][j]);
                System.out.print(space);
            }
            System.out.println();
        }
    }

    public void boardPrintFinal() {
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
