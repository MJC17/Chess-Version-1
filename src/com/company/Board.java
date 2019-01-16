package com.company;

import java.awt.*;

class Board {

    private int boardHeight = 8;
    private int boardWidth = 8;
    private Piece[][] board = new Piece[boardWidth][boardHeight];
    private int[][] possibleMoves = new int[boardWidth][boardHeight];
    private int tileSize = 64;
    private Piece selectedPiece;
    private int lastSelectedX = -1, lastSelectedY = -1;

    private Player blackPlayer = new Player("black");
    private Player whitePlayer = new Player("white");
    private Player activePlayer = blackPlayer;
    private Player deactivePlayer = whitePlayer;

    public Board(){
        this.initialize();
    }

    private void initialize(){

        // White pawns
        for(int x = 0; x < 8; x++){
            board[x][1] = new Pawn("white");
        }

        // Black pawns
        for(int x = 0; x < 8; x++){
            board[x][6] = new Pawn("black");
        }


        //Rooks
        board[0][0] = new Rook("white");
        board[7][0] = new Rook("white");
        board[7][7] = new Rook("black");
        board[0][7] = new Rook("black");

        //Knights
        board[1][0] = new Knight("white");
        board[6][0] = new Knight("white");
        board[6][7] = new Knight("black");
        board[1][7] = new Knight("black");

        //Bishops
        board[2][0] = new Bishop("white");
        board[5][0] = new Bishop("white");
        board[2][7] = new Bishop("black");
        board[5][7] = new Bishop("black");

        //Queens
        board[3][0] = new Queen("white");
        board[3][7] = new Queen("black");

        //Kings
        board[4][0] = new King("white");
        board[4][7] = new King("black");

    }

    void drawChessboard(Component c, Graphics g){

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

                if(possibleMoves[x][y] == activePlayer.direction && board[x][y] instanceof King){
                    drawPoossiblePosition(g, Color.red, x, y);

                } else if( possibleMoves[x][y] == activePlayer.direction){
                    drawPoossiblePosition(g, Color.green, x, y);
                }
            }
        }

        g.setColor(Color.green);
        g.drawRect(lastSelectedX * tileSize, lastSelectedY * tileSize, tileSize, tileSize);

    }

    void drawPoossiblePosition(Graphics g, Color color, int x, int y){

        g.setColor(color);
        g.fillOval(x * tileSize + (int)(tileSize * 0.4), y * tileSize + (int)(tileSize * 0.4), (int)(tileSize * 0.2), (int)(tileSize * 0.2));

        g.setColor(Color.white);
        g.drawOval(x * tileSize + (int)(tileSize * 0.4), y * tileSize + (int)(tileSize * 0.4), (int)(tileSize * 0.2), (int)(tileSize * 0.2));

    }

    void checkPossibleMove(Point pos){

        Point clickedTilePos = new Point((pos.x - (pos.x % tileSize)) / tileSize,(pos.y - (pos.y % tileSize)) / tileSize);


        if(possibleMoves[clickedTilePos.x][clickedTilePos.y] == activePlayer.direction && !(board[clickedTilePos.x][clickedTilePos.y] instanceof King) && !activePlayer.getCheckStatus()){

            moveSelectedPiece(clickedTilePos.x, clickedTilePos.y);
            changePlayer();
            activePlayerInCheck();


        } else if( board[clickedTilePos.x][clickedTilePos.y] != null && board[clickedTilePos.x][clickedTilePos.y].getColor().equalsIgnoreCase(activePlayer.getColor()) && !activePlayer.getCheckStatus()){

            selectPiece(clickedTilePos.x, clickedTilePos.y);


        } else if (activePlayer.getCheckStatus()){

            selectActiveKing();

        }
    }

    void selectPiece(int x, int y){
        clearPossibleMoves();
        lastSelectedX = x;
        lastSelectedY = y;
        selectedPiece = board[x][y];
        selectedPiece.findValidMoves(board, possibleMoves, x, y);

    }

    void selectActiveKing(){
        Point activePlayerKingPosition = activePlayer.kingCoodinate(board);
        selectPiece(activePlayerKingPosition.x, activePlayerKingPosition.y);

    }

    void moveSelectedPiece(int x, int y){
        board[x][y] = selectedPiece;
        board[x][y].MovesPiece(x, y);
        board[lastSelectedX][lastSelectedY] = null;

        clearPossibleMoves();

        lastSelectedX = -1;
        lastSelectedY = -1;
    }

    void activePlayerInCheck(){

        int[][] movesboard = deactivePlayer.playerPossibleMoves(board);
        Point activePlayerKingPosition = activePlayer.kingCoodinate(board);
        if(movesboard[activePlayerKingPosition.x][activePlayerKingPosition.y] == 2){
            activePlayer.setCheckStatus(true);
            selectActiveKing();

            activePlayerInCheckmate();

        }else {
            activePlayer.setCheckStatus(false);

        }
        
    }

    void activePlayerInCheckmate(){



    }




    void clearPossibleMoves(){
        possibleMoves = new int[boardWidth][boardHeight];
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