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

    int xPos, yPos;

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

    public void findValidMoves(Piece[][] board, int[][] possibleMoves, int currentX, int currentY) {}

    public void MovesPiece(int x, int y){
        xPos = x;
        yPos = y;
        numMoves ++;
    }

    public String getColor() {
        return color;
    }

    void checkPossiblePath(Piece[][] board, int[][] possibleMoves, int currentX, int currentY, int xOffset, int yOffset){

        try {

            if (board[currentX + (direction * xOffset)][currentY + (direction * yOffset)] == null && possibleMoves[currentX + (direction * xOffset)][currentY + (direction * yOffset)] != (direction * -1)){
                possibleMoves[currentX + (direction * xOffset)][currentY + (direction * yOffset)] = this.direction;
                checkPossiblePath(board, possibleMoves, currentX + (direction * xOffset), currentY+ (direction * yOffset), xOffset, yOffset);

            }else if (!board[currentX+ (direction * xOffset)][currentY+ (direction * yOffset)].getColor().equalsIgnoreCase(this.color) && possibleMoves[currentX + (direction * xOffset)][currentY + (direction * yOffset)] != (direction * -1)){
                possibleMoves[currentX + (direction * xOffset)][currentY+ (direction * yOffset)] = this.direction;

            }

        }catch (ArrayIndexOutOfBoundsException e){}
    }


    void checkSinglePossiblePos(Piece[][] board, int[][] possibleMoves, int currentX, int currentY, int xOffset, int yOffset){

        try {

            if (board[currentX + (direction * xOffset)][currentY + (direction * yOffset)] == null && possibleMoves[currentX + (direction * xOffset)][currentY + (direction * yOffset)] != (direction * -1)){
                possibleMoves[currentX + (direction * xOffset)][currentY + (direction * yOffset)] = this.direction;

            }else if (!board[currentX+ (direction * xOffset)][currentY+ (direction * yOffset)].getColor().equalsIgnoreCase(this.color) && possibleMoves[currentX + (direction * xOffset)][currentY + (direction * yOffset)] != (direction * -1)){
                possibleMoves[currentX + (direction * xOffset)][currentY+ (direction * yOffset)] = this.direction;

            }

        }catch (ArrayIndexOutOfBoundsException e){}
    }
}