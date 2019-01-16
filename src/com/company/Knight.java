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
    public void findValidMoves(Piece[][] board, int[][] possibleMoves, int currentX, int currentY, int num) {
        super.findValidMoves(board, possibleMoves, currentX, currentY, num);

        //  Up Left
        checkSinglePossiblePos(board, possibleMoves, currentX, currentY,1,2, num);

        //  Up right
        checkSinglePossiblePos(board, possibleMoves, currentX, currentY,-1,2, num);

        //  Down Left
        checkSinglePossiblePos(board, possibleMoves, currentX, currentY,1,-2, num);

        //  Down Right
        checkSinglePossiblePos(board, possibleMoves, currentX, currentY,-1,-2, num);

        //   Left Up
        checkSinglePossiblePos(board, possibleMoves, currentX, currentY,2,1, num);

        //   Left Down
        checkSinglePossiblePos(board, possibleMoves, currentX, currentY,2,-1, num);

        //   Right Up
        checkSinglePossiblePos(board, possibleMoves, currentX, currentY,-2,1, num);

        //   Right Down
        checkSinglePossiblePos(board, possibleMoves, currentX, currentY,-2,-1, num);
    }
}

