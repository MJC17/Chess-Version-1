package com.company;

import javax.swing.*;
import java.awt.*;

public class Pawn extends Piece {

    int endPoint;

    public Pawn(String color){

        this.blackPieceImg = new ImageIcon(getClass().getResource("/com/company/Sprites/blackPawn.png"));
        this.whitePieceImg = new ImageIcon(getClass().getResource("/com/company/Sprites/whitePawn.png"));
        initColor(color);

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
        if (board[currentX][currentY + (direction * 1)] == null) {

            checkSinglePossiblePos(board, possibleMoves, currentX, currentY, 0, 1, num);

        }   }catch (ArrayIndexOutOfBoundsException e){}

        try{
        if (board[currentX][currentY + (direction * 1)] == null && board[currentX][currentY + (direction * 2)] == null && numMoves == 0) {

            checkSinglePossiblePos(board, possibleMoves, currentX, currentY,0, 2, num);
        }   }catch (ArrayIndexOutOfBoundsException e){}


        // Up Left
        try{
        if (board[currentX + (direction * 1)][currentY + (direction * 1)] != null && !board[currentX+ (direction * 1)][currentY+ (direction * 1)].getColor().equalsIgnoreCase(this.color)) {
            checkSinglePossiblePos(board, possibleMoves, currentX, currentY,1,1, num);
        }
        }catch (ArrayIndexOutOfBoundsException e){}
        // Up Right
        try{
        if (board[currentX + (direction * -1)][currentY + (direction * 1)] != null && !board[currentX+ (direction * -1)][currentY+ (direction * 1)].getColor().equalsIgnoreCase(this.color)) {
            checkSinglePossiblePos(board, possibleMoves, currentX, currentY,-1,1, num);
        }
        }catch (ArrayIndexOutOfBoundsException e){}
    }

    @Override
    public void MovesPiece(int x, int y) {
        super.MovesPiece(x, y);

        if (y == endPoint){
            System.out.println("Change Player");
        }
    }
}