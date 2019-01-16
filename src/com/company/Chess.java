package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Chess extends JPanel implements MouseListener {

    public static void main(String[] args) {
        new Chess();
    }

    JFrame window;
    Board gameBoard;

    Chess(){

        gameBoard = new Board();
        addMouseListener(this);
        setFocusable(true);

        // Set up window
        window = new JFrame();
        window.setContentPane(this);
        window.setTitle(" Chess By mc17 ");
        window.setSize(700,600);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setLocationRelativeTo(null);
        window.setVisible(true);

        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);


        gameBoard.drawChessboard(this, g);
    }

    void clearScreen(Graphics g){
        g.setColor(getBackground());
        g.fillRect(0, 0, 700, 600);
    }

    @Override
    public void mousePressed(MouseEvent e) {

        clearScreen(getGraphics());
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
