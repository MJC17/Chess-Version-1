package com.company;

public class Player {

    int[][] pleyerPossibleMoves;
    int direction;
    String color;
    boolean isInCheck;
    boolean isInCheckMate;

    int xPosKing;
    int yPosKing;

    Player(String color){

        this.color = color;

        if (this.color.equalsIgnoreCase("black")){

            direction = -1;
        } else if (this.color.equalsIgnoreCase("white")){

            direction = 1;
        }

    }

    public void playerPossableMoves(Piece[][] gameBored, int[][] possibleMoves){

        for(int x = 0; x < gameBored.length; x++){
            for(int y = 0; y < gameBored[0].length; y++) {

                try{
                    if (gameBored[x][y].getColor().equalsIgnoreCase(this.color) && gameBored[x][y] != null){
                        gameBored[x][y].findValidMoves(gameBored, possibleMoves, x, y);

                        if (gameBored[x][y] instanceof King){
                            xPosKing = x;
                            yPosKing = y;
                        }

                    }
                }catch (ArrayIndexOutOfBoundsException | NullPointerException e){

                }
            }
        }
    }

    public String getColor() {
        return color;
    }
}