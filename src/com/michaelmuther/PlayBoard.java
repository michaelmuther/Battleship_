package com.michaelmuther;

public class PlayBoard extends Board{



    char[][] boardArray;

    public PlayBoard (char[][] boardArray) {
        this.boardArray = boardArray;
        ShotInput shotInput = new ShotInput(boardArray);

    }


}
