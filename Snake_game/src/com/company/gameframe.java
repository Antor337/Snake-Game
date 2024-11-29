package com.company;

import javax.swing.*;
public class gameframe extends JFrame {
    gameframe()
    {
        this.add(new gamepanel());
        this.setVisible(true);
        this.setTitle("Snake Game");
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setLocationRelativeTo(null);
        icon();
    }
    public void icon()
    {
        ImageIcon obj=new ImageIcon(getClass().getResource("icon.png"));
        this.setIconImage(obj.getImage());
    }
}
