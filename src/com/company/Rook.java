package com.company;

import javax.swing.*;

public class Rook extends Piece {

    //  init
    public Rook(String color){
        // setting the objects color images
        this.blackPieceImg = new ImageIcon(getClass().getResource("/com/company/Sprites/blackRook.png"));
        this.whitePieceImg = new ImageIcon(getClass().getResource("/com/company/Sprites/whiteRook.png"));

        initColor(color);

    }

    @Override
    public void findValidMoves(Piece[][] board, int[][] possibleMoves, int currentX, int currentY, int num) {
        super.findValidMoves(board, possibleMoves, currentX, currentY, num);

        // up
        checkPossiblePath(board, possibleMoves, currentX, currentY, 0,1, num);
        // left
        checkPossiblePath(board, possibleMoves, currentX, currentY, 1,0, num);
        // down
        checkPossiblePath(board, possibleMoves, currentX, currentY, 0,-1, num);
        // right
        checkPossiblePath(board, possibleMoves, currentX, currentY, -1,0, num);

    }

}