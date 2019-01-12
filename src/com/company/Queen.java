package com.company;

import javax.swing.*;

public class Queen extends Piece {

    public Queen(String color){

        this.blackPieceImg = new ImageIcon(getClass().getResource("/com/company/Sprites/blackQueen.png"));
        this.whitePieceImg = new ImageIcon(getClass().getResource("/com/company/Sprites/whiteQueen.png"));

        initColor(color);

    }

    @Override
    public void findValidMoves(Piece[][] board, int[][] possibleMoves, int currentX, int currentY) {
        super.findValidMoves(board, possibleMoves, currentX, currentY);

        checkPossiblePath(board, possibleMoves, currentX, currentY, 0,1);
        checkPossiblePath(board, possibleMoves, currentX, currentY, 1,0);
        checkPossiblePath(board, possibleMoves, currentX, currentY, 0,-1);
        checkPossiblePath(board, possibleMoves, currentX, currentY, -1,0);

        checkPossiblePath(board, possibleMoves, currentX, currentY, 1,1);
        checkPossiblePath(board, possibleMoves, currentX, currentY, -1,1);
        checkPossiblePath(board, possibleMoves, currentX, currentY, -1,-1);
        checkPossiblePath(board, possibleMoves, currentX, currentY, 1,-1);

    }
}