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

//    init the object
    public Board(){
        // setting up the chess board
        this.initialize();
    }

//    placing all the piece inside the chess board for both colors
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


    // drawing the object to the screen
    void drawChessboard(Component c, Graphics g){

        // loop that goes through the full array
        for(int x = 0; x < board.length; x++){
            for(int y = 0; y < board[0].length; y++){

                // drawing the chess board tile background
                if ((x % 2 == 0 && y % 2 == 1) || (y % 2 == 0 && x % 2 == 1)){
                    g.setColor(Color.DARK_GRAY);
                    g.fillRect(x * tileSize, y  * tileSize, tileSize, tileSize);

                } else {
                    g.setColor(Color.LIGHT_GRAY);
                    g.fillRect(x  * tileSize, y  * tileSize, tileSize, tileSize);
                }

                // indicator showing that the king is in check
                if(activePlayer.getCheckStatus() &&  board[x][y] != null &&  board[x][y] instanceof King &&  board[x][y].getColor().equalsIgnoreCase(activePlayer.color)){
                    g.setColor(Color.red);
                    g.fillRect(x * tileSize, y * tileSize, tileSize - 1, tileSize - 1);
                }

                // drawing the chess piece in the their position
                if ( board[x][y] != null){
                     board[x][y].drawPiece(c, g, x, y, tileSize);
                }

                // indicator for king is in check but not check mate
                if(possibleMoves[x][y] == activePlayer.direction && board[x][y] instanceof King){
                    drawPoossiblePosition(g, Color.red, x, y);

                }
                // indicator for possible moves
                else if( possibleMoves[x][y] == activePlayer.direction){
                    drawPoossiblePosition(g, Color.green, x, y);
                }
            }
        }



        // indicator showing the selected the bored piece
        g.setColor(Color.green);
        g.drawRect(lastSelectedX * tileSize, lastSelectedY * tileSize, tileSize, tileSize);
    }

    // template for indicator that shows data relating to possible moves
    void drawPoossiblePosition(Graphics g, Color color, int x, int y){
        // main color
        g.setColor(color);
        g.fillOval(x * tileSize + (int)(tileSize * 0.4), y * tileSize + (int)(tileSize * 0.4), (int)(tileSize * 0.2), (int)(tileSize * 0.2));
        // white outline
        g.setColor(Color.white);
        g.drawOval(x * tileSize + (int)(tileSize * 0.4), y * tileSize + (int)(tileSize * 0.4), (int)(tileSize * 0.2), (int)(tileSize * 0.2));

    }

    // receiving data relating to the mouse input
    void checkPossibleMove(Point pos){

        // calculating the tile that was clicked
        Point clickedTilePos = new Point((pos.x - (pos.x % tileSize)) / tileSize,(pos.y - (pos.y % tileSize)) / tileSize);

        // for when the active player is moving the selected chess piece
        if(possibleMoves[clickedTilePos.x][clickedTilePos.y] == activePlayer.direction && !(board[clickedTilePos.x][clickedTilePos.y] instanceof King) ){
            // moving the selected piece
            moveSelectedPiece(clickedTilePos.x, clickedTilePos.y);
            clearPossibleMoves();

            // switching the player
            changePlayer();
        }

        // for when the active player is selecting a chess piece
        else if( board[clickedTilePos.x][clickedTilePos.y] != null && board[clickedTilePos.x][clickedTilePos.y].getColor().equalsIgnoreCase(activePlayer.getColor())){
            selectPiece(clickedTilePos.x, clickedTilePos.y);
        }
    }

    // selecting a chess piece
    void selectPiece(int x, int y){

        // getting the object
        lastSelectedX = x;
        lastSelectedY = y;
        selectedPiece = board[x][y];

        // finding the new possible moves for the selected chess piece
        clearPossibleMoves();
        selectedPiece.findValidMoves(board, possibleMoves, x, y);
    }

    // for when selecting a new chess piece
    void moveSelectedPiece(int x, int y){

        // placing the object in its new position
        board[x][y] = selectedPiece;
        board[x][y].movePiece(board, x, y);
        board[lastSelectedX][lastSelectedY] = null;

        // disabling the selection box on the screen... putting it out of view
        lastSelectedX = -1;
        lastSelectedY = -1;
    }

    // seeing if the active player is in check
    boolean activePlayerInCheck(){

        // collecting the needed data
        int[][] movesboard = deactivePlayer.playerPossibleMoves(board);
        Point activePlayerKingPosition = activePlayer.kingCoodinate(board);

        // checking if the king is in the path of opposing player pieces path
        activePlayer.setCheckStatus(movesboard[activePlayerKingPosition.x][activePlayerKingPosition.y] == 2);
        return (movesboard[activePlayerKingPosition.x][activePlayerKingPosition.y] == 2);
    }


    // clearing the array that holds the possible moves to the active player
    void clearPossibleMoves(){
        possibleMoves = new int[boardWidth][boardHeight];
    }

    // switching the active player
    void changePlayer(){
        if (activePlayer == blackPlayer){
            activePlayer = whitePlayer;
            deactivePlayer = blackPlayer;
        } else {
            activePlayer = blackPlayer;
            deactivePlayer = whitePlayer;
        }

        // checking if the player is now in check
        activePlayerInCheck();
    }


}