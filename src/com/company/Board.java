package com.company;

import java.awt.*;

public class Board {

    int boredHeight = 8;
    int boredWidth = 8;
    Piece[][] board = new Piece[boredWidth][boredHeight];
    int[][] possibleMoves = new int[boredWidth][boredHeight];
    int tileSize = 64;
    Piece selectedPiece;
    int lastSelectedX = -1;
    int lastSelectedY = -1;

    Player blackPlayer = new Player("black");
    Player whitePlayer = new Player("white");
    Player activePlayer = blackPlayer;
    Player deactivePlayer = whitePlayer;

    Board(){
        this.initialize();
    }

    private void initialize(){



        // White pawns
        for(int x = 0; x < 4; x++){
            board[x][1] = new Pawn("white");
        }

        // Black pawns
        for(int x = 0; x < 4; x++){
            board[x][6] = new Pawn("black");
        }


//        //Rooks
//        board[0][0] = new Rook("white");
//        board[7][0] = new Rook("white");
//        board[7][7] = new Rook("black");
//        board[0][7] = new Rook("black");

        //Knights
        board[1][0] = new Knight("white");
        board[6][0] = new Knight("white");
        board[6][7] = new Knight("black");
        board[1][7] = new Knight("black");

//        //Bishops
//        board[2][0] = new Bishop("white");
//        board[5][0] = new Bishop("white");
//        board[2][7] = new Bishop("black");
//        board[5][7] = new Bishop("black");

        //Queens
        board[3][0] = new Queen("white");
        board[3][7] = new Queen("black");

        //Kings
        board[4][0] = new King("white");
        board[4][7] = new King("black");

    }

    public void drawBored(Component c, Graphics g){

        for(int x = 0; x < board.length; x++){
            for(int y = 0; y < board[0].length; y++){

                if ((x % 2 == 0 && y % 2 == 1) || (y % 2 == 0 && x % 2 == 1)){
                    g.setColor(Color.DARK_GRAY);
                    g.fillRect(x * tileSize, y  * tileSize, tileSize, tileSize);

                } else {
                    g.setColor(Color.LIGHT_GRAY);
                    g.fillRect(x  * tileSize, y  * tileSize, tileSize, tileSize);
                }

                if ( board[x][y] != null){

                     board[x][y].drawPiece(c, g, x, y, tileSize);

                }


                if( possibleMoves[x][y] != 0 && board[x][y] instanceof King){

                    g.setColor(Color.RED);
                    g.fillOval(x * tileSize + (int)(tileSize * 0.4), y * tileSize + (int)(tileSize * 0.4), (int)(tileSize * 0.2), (int)(tileSize * 0.2));

                    g.setColor(Color.white);
                    g.drawOval(x * tileSize + (int)(tileSize * 0.4), y * tileSize + (int)(tileSize * 0.4), (int)(tileSize * 0.2), (int)(tileSize * 0.2));

                } else if( possibleMoves[x][y] != 0){

                    g.setColor(Color.GREEN);
                    g.fillOval(x * tileSize + (int)(tileSize * 0.4), y * tileSize + (int)(tileSize * 0.4), (int)(tileSize * 0.2), (int)(tileSize * 0.2));

                    g.setColor(Color.white);
                    g.drawOval(x * tileSize + (int)(tileSize * 0.4), y * tileSize + (int)(tileSize * 0.4), (int)(tileSize * 0.2), (int)(tileSize * 0.2));
                }
            }
        }

        g.setColor(Color.green);
        g.drawRect(lastSelectedX * tileSize, lastSelectedY * tileSize, tileSize, tileSize);

    }

    public void checkPossibleMove(Point pos){

        int clickedX = (pos.x - (pos.x % tileSize)) / tileSize;
        int clickedY = (pos.y - (pos.y % tileSize)) / tileSize;

        if( possibleMoves[clickedX][clickedY] != 0 && !(board[clickedX][clickedY] instanceof King)){

            moveSelectedPiece(clickedX, clickedY);
            lastSelectedX = -1;
            lastSelectedY = -1;

            changePlayer();
            activePlayerInCheck();


        } else if( board[clickedX][clickedY] != null && board[clickedX][clickedY].getColor().equalsIgnoreCase(activePlayer.getColor())){

            clearPossibleMoves();

            lastSelectedX = clickedX;
            lastSelectedY = clickedY;
            selectedPiece = board[clickedX][clickedY];
            selectedPiece.findValidMoves(board, possibleMoves, clickedX, clickedY);
        }
    }

    void activePlayerInCheck(){

    }

    void moveSelectedPiece(int x, int y){

        board[x][y] = selectedPiece;
        board[x][y].MovesPiece(x, y);
        board[lastSelectedX][lastSelectedY] = null;

        clearPossibleMoves();
    }


    void clearPossibleMoves(){
        possibleMoves = new int[boredWidth][boredHeight];
    }



    void changePlayer(){
        if (activePlayer == blackPlayer){
            activePlayer = whitePlayer;
            deactivePlayer = blackPlayer;
        } else {
            activePlayer = blackPlayer;
            deactivePlayer = whitePlayer;
        }
    }


}