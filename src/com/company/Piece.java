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

        // setting the piece color
        this.color = color.toLowerCase();

        // setting the piece proper image and direction due to the color
        if (this.color.equalsIgnoreCase("black")){
            img = blackPieceImg;
            direction = -1;
        } else if (this.color.equalsIgnoreCase("white")){
            img = whitePieceImg;
            direction = 1;
        }
    }

    // drawing the image of the piece
    public void drawPiece(Component c, Graphics g, int xPos, int yPos, int tileSize){
        img.paintIcon(c, g, xPos * tileSize, yPos * tileSize);
    }

    // function to find possible positions, made to be override
    public void findValidMoves(Piece[][] board, int[][] possibleMoves, int currentX, int currentY, int num) {

    }
    // function to find possible positions, made to be override

    public void findValidMoves(Piece[][] board, int[][] possibleMoves, int currentX, int currentY) {
        findValidMoves(board,possibleMoves, currentX, currentY, this.direction);
    }

    // function to set the position of a piece, made to be override
    public void movePiece(Piece[][] board, int x, int y){
        movePiece(x, y);

    }

    // function to set the position of a piece
    public void movePiece(int x, int y){
        x_coordinate = x;
        y_coordinate = y;
        numMoves ++;

    }

//    retruns the position
    public Point getCoordinate(){
        return new Point(x_coordinate, y_coordinate);
    }

//    returns the color
    public String getColor() {
        return color;
    }

    // finds movable path using recursion
    void checkPossiblePath(Piece[][] board, int[][] possibleMoves, int currentX, int currentY, int xOffset, int yOffset, int num){

        try {
            // checking if the spot in question is empty
            if (board[currentX + (direction * xOffset)][currentY + (direction * yOffset)] == null && possibleMoves[currentX + (direction * xOffset)][currentY + (direction * yOffset)] != direction){
                // setting the identifier to show that it a possible move
                possibleMoves[currentX + (direction * xOffset)][currentY + (direction * yOffset)] = num;
                // check the next in the path
                checkPossiblePath(board, possibleMoves, currentX + (direction * xOffset), currentY+ (direction * yOffset), xOffset, yOffset, num);

            }
            // checking if the spot in question has the opposing player piece
            else if (!board[currentX+ (direction * xOffset)][currentY+ (direction * yOffset)].getColor().equalsIgnoreCase(this.color) && possibleMoves[currentX + (direction * xOffset)][currentY + (direction * yOffset)] != direction){
                // setting the identifier to show that it a possible move
                possibleMoves[currentX + (direction * xOffset)][currentY+ (direction * yOffset)] = num;

            }

        }catch (ArrayIndexOutOfBoundsException e){}
    }

    // finds movable path using recursion
    void checkPossiblePath(Piece[][] board, int[][] possibleMoves, int currentX, int currentY, int xOffset, int yOffset){
        checkPossiblePath(board, possibleMoves, currentX, currentY, xOffset, yOffset, this.direction);
    }


    // checks possible moves for a single place in the array
    void checkSinglePossiblePos(Piece[][] board, int[][] possibleMoves, int currentX, int currentY, int xOffset, int yOffset, int num){

        try {
            // checking i0f the spot in question is empty
            if (board[currentX + (direction * xOffset)][currentY + (direction * yOffset)] == null && possibleMoves[currentX + (direction * xOffset)][currentY + (direction * yOffset)] != direction ){
                // setting the identifier to show that it a possible move
                possibleMoves[currentX + (direction * xOffset)][currentY + (direction * yOffset)] = num;

            }
            // checking if the spot in question has the opposing player piece
            else if (!board[currentX+ (direction * xOffset)][currentY+ (direction * yOffset)].getColor().equalsIgnoreCase(this.color) && possibleMoves[currentX + (direction * xOffset)][currentY + (direction * yOffset)] != direction){
                // setting the identifier to show that it a possible move
                possibleMoves[currentX + (direction * xOffset)][currentY+ (direction * yOffset)] = num;

            }

        }catch (ArrayIndexOutOfBoundsException e){}
    }

    // checks possible moves for a single place in the array
    void checkSinglePossiblePos(Piece[][] board, int[][] possibleMoves, int currentX, int currentY, int xOffset, int yOffset){
        checkSinglePossiblePos(board, possibleMoves, currentX, currentY, xOffset, yOffset, this.direction);
    }
}