package com.company;

import javax.swing.*;

public class Bishop extends Piece {

    public Bishop(String color){

        this.blackPieceImg = new ImageIcon(getClass().getResource("/com/company/Sprites/blackBishop.png"));
        this.whitePieceImg = new ImageIcon(getClass().getResource("/com/company/Sprites/whiteBishop.png"));

        initColor(color);

    }

    @Override
    public void findValidMoves(Piece[][] board, int[][] possibleMoves, int currentX, int currentY, int num) {
        super.findValidMoves(board, possibleMoves, currentX, currentY, num);
        checkPossiblePath(board, possibleMoves, currentX, currentY, 1,1, num);
        checkPossiblePath(board, possibleMoves, currentX, currentY, -1,1, num);
        checkPossiblePath(board, possibleMoves, currentX, currentY, -1,-1, num);
        checkPossiblePath(board, possibleMoves, currentX, currentY, 1,-1, num);
    }
}