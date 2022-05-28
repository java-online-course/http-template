package com.epam.izh.rd.online.view;

import javax.swing.*;
import java.awt.*;

public class GameField extends JPanel {
    private ImageIcon winnerIcon;
    private Image winner;
    private final String path = "C:\\Users\\shiba\\Desktop\\fork_epam\\http-template\\src\\main\\java\\resources\\winner.jpg";

    public GameField(){
        setBackground(Color.black);
        winnerIcon = new ImageIcon(path);
        winner = winnerIcon.getImage();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(winner,90,90,this);

    }

}
