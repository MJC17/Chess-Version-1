package com.company;

import javax.swing.*;

public class Rook extends Piece {

    public Rook(String color){

        this.blackPieceImg = new ImageIcon(getClass().getResource("/com/company/Sprites/blackRook.png"));
        this.whitePieceImg = new ImageIcon(getClass().getResource("/com/company/Sprites/whiteRook.png"));

        initColor(color);

    }

    @Override
    public void findValidMoves(Piece[][] board, int[][] possibleMoves, int currentX, int currentY) {
        super.findValidMoves(board, possibleMoves, currentX, currentY);

        checkPossiblePath(board, possibleMoves, currentX, currentY, 0,1);
        checkPossiblePath(board, possibleMoves, currentX, currentY, 1,0);
        checkPossiblePath(board, possibleMoves, currentX, currentY, 0,-1);
        checkPossiblePath(board, possibleMoves, currentX, currentY, -1,0);

    }

}