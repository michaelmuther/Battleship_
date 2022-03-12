package com.michaelmuther;

public class Player {

    // player has 2 boards: ShipBoard and PlayBoard.
    // PlayBoard is the one you make guesses and hits on
    // ShipBoard is the layout of your own ships

    private boolean isFirst;
    final String playerName;
    private ShipBoard shipBoard;
    private PlayBoard playBoard;

    public Player(boolean isFirst, String playerName) {
        this.isFirst = isFirst;
        this.playerName = playerName;
        this.shipBoard = new ShipBoard();
        this.playBoard = new PlayBoard(shipBoard.getBoard());
    }
}
