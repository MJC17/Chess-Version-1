package com.company;

import java.awt.*;

public class Player {

    int direction;
    String color;
    boolean checkStatus;



    Player(String color){

        this.color = color;

        if (this.color.equalsIgnoreCase("black")){
            direction = -1;

        } else if (this.color.equalsIgnoreCase("white")){
            direction = 1;
        }
    }

    public int[][] playerPossibleMoves(Piece[][] board) {

        int[][] movesBored = new int[8][8];

        for (int x = 0; x < board.length; x++) {
            for (int y = 0; y < board[0].length; y++) {

                try {
                    if (board[x][y].getColor().equalsIgnoreCase(this.color) && board[x][y] != null && !(board[x][y] instanceof King)) {

                        board[x][y].findValidMoves(board, movesBored, x, y, 2);

                    }
                } catch (ArrayIndexOutOfBoundsException | NullPointerException e) {

                }
            }
        }

        return movesBored;
    }

    public Point kingCoodinate(Piece[][] board){

        for(int x = 0; x < board.length; x++) {
            for (int y = 0; y < board[0].length; y++) {

                try{
                    if (board[x][y].getColor().equalsIgnoreCase(this.color) && board[x][y] instanceof King){

                        return board[x][y].getCoordinate();

                    }
                }catch (NullPointerException e){}
            }
        }

        return null;
    }

    public void setCheckStatus(boolean checkStatus) {
        checkStatus = checkStatus;
    }

    public boolean getCheckStatus(){
        return checkStatus;
    }

    public String getColor() {
        return color;
    }

}