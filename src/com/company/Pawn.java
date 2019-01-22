package com.company;

import javax.swing.*;

public class Pawn extends Piece {

    int endPoint;

    // init
    public Pawn(String color){

        this.blackPieceImg = new ImageIcon(getClass().getResource("/com/company/Sprites/blackPawn.png"));
        this.whitePieceImg = new ImageIcon(getClass().getResource("/com/company/Sprites/whitePawn.png"));
        initColor(color);

        // setting the y endpoints to cha˜˜e the pawn into a new piece
        if (this.color.equalsIgnoreCase("black")){
            endPoint = 0;

        } else if (this.color.equalsIgnoreCase("white")){
            endPoint = 7;
        }
    }

    @Override
    public void findValidMoves(Piece[][] board, int[][] possibleMoves, int currentX, int currentY, int num) {
        super.findValidMoves(board, possibleMoves, currentX, currentY, num);

        try{
            // moving 1 up
            if (board[currentX][currentY + (direction * 1)] == null) {

                checkSinglePossiblePos(board, possibleMoves, currentX, currentY, 0, 1, num);

            }
        }catch (ArrayIndexOutOfBoundsException e){}

        try{
            // moving 2 up
            if (board[currentX][currentY + (direction * 1)] == null && board[currentX][currentY + (direction * 2)] == null && numMoves == 0) {

                checkSinglePossiblePos(board, possibleMoves, currentX, currentY,0, 2, num);
            }
        }catch (ArrayIndexOutOfBoundsException e){}


        // Up Left for a kill
        try{
            if (board[currentX + (direction * 1)][currentY + (direction * 1)] != null && !board[currentX+ (direction * 1)][currentY+ (direction * 1)].getColor().equalsIgnoreCase(this.color)) {
                checkSinglePossiblePos(board, possibleMoves, currentX, currentY,1,1, num);
            }
        }catch (ArrayIndexOutOfBoundsException e){}


        // Up Right for a kill
        try{
        if (board[currentX + (direction * -1)][currentY + (direction * 1)] != null && !board[currentX+ (direction * -1)][currentY+ (direction * 1)].getColor().equalsIgnoreCase(this.color)) {
            checkSinglePossiblePos(board, possibleMoves, currentX, currentY,-1,1, num);
        }
        }catch (ArrayIndexOutOfBoundsException e){}
    }

    @Override
    public void movePiece(Piece[][] board, int x, int y) {
        super.movePiece(board, x, y);

        if (y == endPoint){
//            was not able to get the pawn promotion to work
//            Piece newPiece = new PawnPromotionWindow().pawnPromotion(board[x][y].color);
//            board[x][y] = newPiece;

//            set the pawn promotion default to a Queen
            board[x][y] = new Queen(this.color);
        }
    }
}