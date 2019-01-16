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

        checkKingPossibleMove(board, possibleMoves, currentX, currentY, 0,1);
        checkKingPossibleMove(board, possibleMoves, currentX, currentY, 1,0);
        checkKingPossibleMove(board, possibleMoves, currentX, currentY, 0,-1);
        checkKingPossibleMove(board, possibleMoves, currentX, currentY, -1,0);
        checkKingPossibleMove(board, possibleMoves, currentX, currentY, 1,1);
        checkKingPossibleMove(board, possibleMoves, currentX, currentY, -1,1);
        checkKingPossibleMove(board, possibleMoves, currentX, currentY, -1,-1);
        checkKingPossibleMove(board, possibleMoves, currentX, currentY, 1,-1);


    }
    
    void checkKingPossibleMove(Piece[][] board, int[][] possibleMoves, int currentX, int currentY, int xOffset, int yOffset){

        board[currentX][currentY] = null;

        for(int x = 0; x < board.length; x++){
            for(int y = 0; y < board[0].length; y++) {

                try{
                    if (!board[x][y].getColor().equalsIgnoreCase(this.color) && board[x][y] != null && !(board[x][y] instanceof King)){
                        board[x][y].findValidMoves(board, possibleMoves, x, y, 2);
                    }

                }catch (ArrayIndexOutOfBoundsException | NullPointerException e){}
            }
        }

        try {

            if (board[currentX + (direction * xOffset)][currentY + (direction * yOffset)] == null && possibleMoves[currentX + (direction * xOffset)][currentY + (direction * yOffset)] != direction ){
                possibleMoves[currentX + (direction * xOffset)][currentY + (direction * yOffset)] = this.direction;

            }else if (!board[currentX+ (direction * xOffset)][currentY+ (direction * yOffset)].getColor().equalsIgnoreCase(this.color) && possibleMoves[currentX + (direction * xOffset)][currentY + (direction * yOffset)] != direction){
                possibleMoves[currentX + (direction * xOffset)][currentY+ (direction * yOffset)] = this.direction;

            }

        }catch (ArrayIndexOutOfBoundsException e){}

        board[currentX][currentY] = this;
    }

}

