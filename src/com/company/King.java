package com.company;

import javax.swing.*;
import java.awt.*;

public class King extends Piece {

    public King(String color){

        this.blackPieceImg = new ImageIcon(getClass().getResource("/com/company/Sprites/blackKing.png"));
        this.whitePieceImg = new ImageIcon(getClass().getResource("/com/company/Sprites/whiteKing.png"));

        initColor(color);

    }

    @Override
    public void findValidMoves(Piece[][] board, int[][] possibleMoves, int currentX, int currentY) {
        super.findValidMoves(board, possibleMoves, currentX, currentY);


    }
}