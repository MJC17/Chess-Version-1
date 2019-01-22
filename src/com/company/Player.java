package com.company;

import java.awt.*;

public class Player {

    int direction;
    String color;
    boolean checkStatus;


    // init
    Player(String color){

        // setting players color
        this.color = color;

        // setting players direction
        if (this.color.equalsIgnoreCase("black")){
            direction = -1;

        } else if (this.color.equalsIgnoreCase("white")){
            direction = 1;
        }
    }

    // finding the players possible moves for all chess piece
    public int[][] playerPossibleMoves(Piece[][] board) {

        // starting an empty board
        int[][] movesBored = new int[8][8];

        // looping through the whole array
        for (int x = 0; x < board.length; x++) {
            for (int y = 0; y < board[0].length; y++) {

                try {
                    // if its one of the player's chess piece
                    if (board[x][y].getColor().equalsIgnoreCase(this.color) && board[x][y] != null && !(board[x][y] instanceof King)) {

                        // find the piece possible moves
                        board[x][y].findValidMoves(board, movesBored, x, y, 2);

                    }
                } catch (ArrayIndexOutOfBoundsException | NullPointerException e) {

                }
            }
        }

        // return all the possible moves of the player
        return movesBored;
    }

    // finding the player's king
    public Point kingCoodinate(Piece[][] board){

        // looping through the whole array
        for(int x = 0; x < board.length; x++) {
            for (int y = 0; y < board[0].length; y++) {

                try{
                    // checking to see if it the Players king
                    if (board[x][y].getColor().equalsIgnoreCase(this.color) && board[x][y] instanceof King){

                        // giving the location
                        return new Point(x, y);

                    }
                }catch (NullPointerException e){}
            }
        }

        return null;
    }

    // setting if the player is in check
    public void setCheckStatus(boolean checkStatus) {
        this.checkStatus = checkStatus;
    }
    // seeing if the player is in check
    public boolean getCheckStatus(){
        return checkStatus;
    }
    // getting the color of the player
    public String getColor() {
        return color;
    }

}