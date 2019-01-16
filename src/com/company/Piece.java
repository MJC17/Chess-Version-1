package com.company;

import javax.swing.*;
import java.awt.*;

public class Piece {

    String color;
    int direction;
    ImageIcon img;
    ImageIcon blackPieceImg;
    ImageIcon whitePieceImg;
    int numMoves = 0;

    protected int x_coordinate;  // piece x coordinate
    protected int y_coordinate;  // piece y coordinate

    public void initColor(String color){

        this.color = color.toLowerCase();

        if (this.color.equalsIgnoreCase("black")){
            img = blackPieceImg;
            direction = -1;
        } else if (this.color.equalsIgnoreCase("white")){
            img = whitePieceImg;
            direction = 1;
        }
    }

    public void drawPiece(Component c, Graphics g, int xPos, int yPos, int tileSize){
        img.paintIcon(c, g, xPos * tileSize, yPos * tileSize);
    }

    public void findValidMoves(Piece[][] board, int[][] possibleMoves, int currentX, int currentY, int num) {

    }

    public void findValidMoves(Piece[][] board, int[][] possibleMoves, int currentX, int currentY) {
        findValidMoves(board,possibleMoves, currentX, currentY, this.direction);
    }

    public void MovesPiece(int x, int y){
        x_coordinate = x;
        y_coordinate = y;
        numMoves ++;
    }

    public Point getCoordinate(){
        return new Point(x_coordinate, y_coordinate);
    }

    public String getColor() {
        return color;
    }

    void checkPossiblePath(Piece[][] board, int[][] possibleMoves, int currentX, int currentY, int xOffset, int yOffset, int num){

        try {

            if (board[currentX + (direction * xOffset)][currentY + (direction * yOffset)] == null && possibleMoves[currentX + (direction * xOffset)][currentY + (direction * yOffset)] != direction){
                possibleMoves[currentX + (direction * xOffset)][currentY + (direction * yOffset)] = num;
                checkPossiblePath(board, possibleMoves, currentX + (direction * xOffset), currentY+ (direction * yOffset), xOffset, yOffset, num);

            }else if (!board[currentX+ (direction * xOffset)][currentY+ (direction * yOffset)].getColor().equalsIgnoreCase(this.color) && possibleMoves[currentX + (direction * xOffset)][currentY + (direction * yOffset)] != direction){
                possibleMoves[currentX + (direction * xOffset)][currentY+ (direction * yOffset)] = num;

            }

        }catch (ArrayIndexOutOfBoundsException e){}
    }

    void checkPossiblePath(Piece[][] board, int[][] possibleMoves, int currentX, int currentY, int xOffset, int yOffset){
        checkPossiblePath(board, possibleMoves, currentX, currentY, xOffset, yOffset, this.direction);
    }



    void checkSinglePossiblePos(Piece[][] board, int[][] possibleMoves, int currentX, int currentY, int xOffset, int yOffset, int num){

        try {

            if (board[currentX + (direction * xOffset)][currentY + (direction * yOffset)] == null && possibleMoves[currentX + (direction * xOffset)][currentY + (direction * yOffset)] != direction ){
                possibleMoves[currentX + (direction * xOffset)][currentY + (direction * yOffset)] = num;

            }else if (!board[currentX+ (direction * xOffset)][currentY+ (direction * yOffset)].getColor().equalsIgnoreCase(this.color) && possibleMoves[currentX + (direction * xOffset)][currentY + (direction * yOffset)] != direction){
                possibleMoves[currentX + (direction * xOffset)][currentY+ (direction * yOffset)] = num;

            }

        }catch (ArrayIndexOutOfBoundsException e){}
    }

    void checkSinglePossiblePos(Piece[][] board, int[][] possibleMoves, int currentX, int currentY, int xOffset, int yOffset){
        checkSinglePossiblePos(board, possibleMoves, currentX, currentY, xOffset, yOffset, this.direction);
    }
}