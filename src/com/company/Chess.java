package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Chess extends JPanel implements MouseListener {

    public static void main(String[] args) {
        // stating the chess game
        new Chess();
    }

//              Name: Marcus J Camerpn (mc17)
//              Date: Dec 12
//              Project Name: Chess
//              Course: ISC3U1
//              About: chess game


    JFrame window;
    Board gameBoard;

    Chess(){

        // init
        gameBoard = new Board();
        addMouseListener(this);
        setFocusable(true);

        // Set up window
        window = new JFrame();
        window.setContentPane(this);
        window.setTitle(" Chess By mc17 ");
        window.setSize(512,512 + 20);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setLocationRelativeTo(null);
        window.setVisible(true);

        // painting the chess bored
        repaint();
    }
// function for drawing to the window
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // drawing the board in the window
        gameBoard.drawChessboard(this, g);
    }

    // clearing the screen to a new canvas
    void clearScreen(Graphics g){
        g.setColor(getBackground());
        g.fillRect(0, 0, 700, 600);
    }

    // getting data from the mouse
    @Override
    public void mousePressed(MouseEvent e) {

        clearScreen(getGraphics());
        // passing the data to the board
        gameBoard.checkPossibleMove(e.getPoint());
        gameBoard.drawChessboard(this, getGraphics());


    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }


}
