package com.company;

import javax.swing.*;
import java.awt.*;

public class King extends Piece {

    // init
    public King(String color){

        // setting the objects color images
        this.blackPieceImg = new ImageIcon(getClass().getResource("/com/company/Sprites/blackKing.png"));
        this.whitePieceImg = new ImageIcon(getClass().getResource("/com/company/Sprites/whiteKing.png"));

        initColor(color);

    }

    @Override
    public void findValidMoves(Piece[][] board, int[][] possibleMoves, int currentX, int currentY) {
        super.findValidMoves(board, possibleMoves, currentX, currentY);

        // up
        checkKingPossibleMove(board, possibleMoves, currentX, currentY, 0,1);
        // right
        checkKingPossibleMove(board, possibleMoves, currentX, currentY, 1,0);
        // down
        checkKingPossibleMove(board, possibleMoves, currentX, currentY, 0,-1);
        // left
        checkKingPossibleMove(board, possibleMoves, currentX, currentY, -1,0);
        // up right
        checkKingPossibleMove(board, possibleMoves, currentX, currentY, 1,1);
        // up left
        checkKingPossibleMove(board, possibleMoves, currentX, currentY, -1,1);
        // down right
        checkKingPossibleMove(board, possibleMoves, currentX, currentY, -1,-1);
        // down left
        checkKingPossibleMove(board, possibleMoves, currentX, currentY, 1,-1);


    }
    
    void checkKingPossibleMove(Piece[][] board, int[][] possibleMoves, int currentX, int currentY, int xOffset, int yOffset){

        // emptying the kings position so it doesn't interferer with calculations
        board[currentX][currentY] = null;

        // looping through the hole array
        for(int x = 0; x < board.length; x++){
            for(int y = 0; y < board[0].length; y++) {

                // sure the king can't get close to another king
                try{
                    if (!board[x][y].getColor().equalsIgnoreCase(this.color) && board[x][y] != null && !(board[x][y] instanceof King)){
                        board[x][y].findValidMoves(board, possibleMoves, x, y, 2);
                    }

                }catch (ArrayIndexOutOfBoundsException | NullPointerException e){}
            }
        }

        try {
            // checking if the spot is empty
            if (board[currentX + (direction * xOffset)][currentY + (direction * yOffset)] == null && possibleMoves[currentX + (direction * xOffset)][currentY + (direction * yOffset)] != direction ){
                possibleMoves[currentX + (direction * xOffset)][currentY + (direction * yOffset)] = this.direction;

            }
            // checking if the spot has the opposing player in it
            else if (!board[currentX+ (direction * xOffset)][currentY+ (direction * yOffset)].getColor().equalsIgnoreCase(this.color) && possibleMoves[currentX + (direction * xOffset)][currentY + (direction * yOffset)] != direction){
                possibleMoves[currentX + (direction * xOffset)][currentY+ (direction * yOffset)] = this.direction;

            }

        }catch (ArrayIndexOutOfBoundsException e){}

        // placing the king back in his position
        board[currentX][currentY] = this;
    }

}

