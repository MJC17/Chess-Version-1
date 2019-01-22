package com.company;

import javax.swing.*;

public class Bishop extends Piece {

    // init
    public Bishop(String color){

        // setting the objects color images
        this.blackPieceImg = new ImageIcon(getClass().getResource("/com/company/Sprites/blackBishop.png"));
        this.whitePieceImg = new ImageIcon(getClass().getResource("/com/company/Sprites/whiteBishop.png"));

        initColor(color);

    }

    @Override
    public void findValidMoves(Piece[][] board, int[][] possibleMoves, int currentX, int currentY, int num) {
        super.findValidMoves(board, possibleMoves, currentX, currentY, num);

        // path for up left
        checkPossiblePath(board, possibleMoves, currentX, currentY, 1,1, num);
        // path for up right
        checkPossiblePath(board, possibleMoves, currentX, currentY, -1,1, num);
        // path for bottom left
        checkPossiblePath(board, possibleMoves, currentX, currentY, -1,-1, num);
        // path for bottom right
        checkPossiblePath(board, possibleMoves, currentX, currentY, 1,-1, num);
    }
}