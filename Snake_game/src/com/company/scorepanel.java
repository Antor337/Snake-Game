package com.company;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Formatter;
import java.util.Scanner;
public class scorepanel extends JFrame {
        public void score(int z)
        {
        setVisible(true);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(700, 370);
        setLocationRelativeTo(null);
        ImageIcon obj=new ImageIcon(getClass().getResource("icon.png"));
        setIconImage(obj.getImage());
        setTitle("Snake's Score");
        JTextArea ta;
        ta=new JTextArea();
        ta.setBackground(Color.green);
        Font f;
        f=new Font("Arial",Font.ITALIC,50);
        ta.setFont(f);
        ta.setForeground(Color.BLUE);
        add(ta);
        String r = String.valueOf(z);
        String b= "WELLDONE!";
        String a = "LOOSER!";
        String c = "YOUR SCORE :";
        String d = "HIGHEST SCORE :";
        String ef = "Congratulations!";
        String gh = "WOW! Its the highest score.";
        if(z<5) {
            ta.append("          "+a+"\n");
            ta.append(c+" ");
            ta.append(r+"\n");
        }
        else
        {
            ta.append("        "+b+ "\n");
            ta.append(c+" ");
            ta.append(r+"\n");
        }
        //reading the file
        int score=0;
        try {
            File file=new File("C:/Users/User/Desktop/CSE 252/Snake_game/scorefile/high_score.txt");
            Scanner input=new Scanner(file);
            while(input.hasNext())
            {
                score=input.nextInt();
            }
        }
        catch(FileNotFoundException e)
        {
            System.out.println(e);
        }
        String x= String.valueOf(score);
        if(z>score)
        {
            //writting the file
            try {
                Formatter hs = new Formatter("C:/Users/User/Desktop/CSE 252/Snake_game/scorefile/high_score.txt");
                hs.format("%d\n", z);
                hs.close();
            }
            catch (FileNotFoundException e) {
                System.out.println(e);
            }
            ta.append(ef+"\n");
            ta.append(gh+"\n");
        }
        else {
            ta.append(d+" ");
            ta.append(x+"\n");
            ta.append("Keep playing,Dude.....");
        }
    }
}

