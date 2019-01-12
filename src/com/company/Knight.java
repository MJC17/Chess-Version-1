package com.company;

import javax.swing.*;
import java.awt.*;

public class Knight extends Piece {

    public Knight(String color){

        this.blackPieceImg = new ImageIcon(getClass().getResource("/com/company/Sprites/blackKnight.png"));
        this.whitePieceImg = new ImageIcon(getClass().getResource("/com/company/Sprites/whiteKnight.png"));

        initColor(color);

    }

    @Override
    public void findValidMoves(Piece[][] board, int[][] possibleMoves, int currentX, int currentY) {
        super.findValidMoves(board, possibleMoves, currentX, currentY);


        //  Up Left
        checkSinglePossiblePos(board, possibleMoves, currentX, currentY,1,2);

        //  Up right
        checkSinglePossiblePos(board, possibleMoves, currentX, currentY,-1,2);

        //  Down Left
        checkSinglePossiblePos(board, possibleMoves, currentX, currentY,1,-2);

        //  Down Right
        checkSinglePossiblePos(board, possibleMoves, currentX, currentY,-1,-2);

        //   Left Up
        checkSinglePossiblePos(board, possibleMoves, currentX, currentY,2,1);

        //   Left Down
        checkSinglePossiblePos(board, possibleMoves, currentX, currentY,2,-1);

        //   Right Up
        checkSinglePossiblePos(board, possibleMoves, currentX, currentY,-2,1);

        //   Right Down
        checkSinglePossiblePos(board, possibleMoves, currentX, currentY,-2,-1);
    }
}