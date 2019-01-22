package com.company;

import javax.swing.*;

public class Queen extends Piece {

    // init
    public Queen(String color){
        // setting the objects color images
        this.blackPieceImg = new ImageIcon(getClass().getResource("/com/company/Sprites/blackQueen.png"));
        this.whitePieceImg = new ImageIcon(getClass().getResource("/com/company/Sprites/whiteQueen.png"));

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