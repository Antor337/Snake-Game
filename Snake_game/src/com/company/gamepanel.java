package com.company;
//importing all classes of java swing.
import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyAdapter;
import java.util.Random;
public class gamepanel extends JPanel implements ActionListener {
        //declaring all goods.
        int screen_width = 1460;
        int screen_hight = 820;
        int per_unit_size = 25;
        int game_units = (screen_width * screen_hight) / per_unit_size;
        int delay = 60;
        int x[] = new int[game_units];
        int y[] = new int[game_units];
        int bodyparts = 6;
        int appleeaten = 0;
        int applex;
        int appley;
        char direction = 'R'; //default direction.
        boolean running; // this is the variable which compute if the snake run or not.
        Timer timer; //game time class
    gamepanel()
    {
        this.setPreferredSize(new Dimension(screen_width,screen_hight));
        this.setBackground(Color.black);
        this.setFocusable(true);
        this.addKeyListener(new MyKeyAdaptor());
        startgame();
    }
        public void startgame ()
        {
            //starting game
            newapple();
            running = true;
            timer = new Timer(delay, this);
            timer.start();
        }
        public void paintComponent (Graphics g)
        {
            super.paintComponent(g);
            draw(g);
        }
        public void draw (Graphics g)
        {
            //check if the game is run or not
            if (running) {
                for (int i = 0; i < (int) (screen_width / 24); i++) {
                    g.drawLine(i * per_unit_size, 0, i * per_unit_size, screen_hight);
                }
                for (int i = 0; i < (int) (screen_hight / 24); i++) {
                    g.drawLine(0, i * per_unit_size, screen_width, i * per_unit_size);
                }
                g.setColor(Color.green); //apples color.
                g.fillOval(applex, appley, per_unit_size, per_unit_size); //creating apple
                for (int i = 0; i < bodyparts; i++) {
                    if (i == 0) {
                        g.setColor(Color.red); // the colour of snake head
                        g.fillRect(x[i], y[i], per_unit_size, per_unit_size); //the size of snake head
                    }
                    else
                    {
                        g.setColor(Color.yellow);   // the colour of snake body
                        g.fillRect(x[i], y[i], per_unit_size, per_unit_size);  //the size of snake body
                    }
                }
            }
            else
            {
                gameover(g);// calling game over function for termination.
                scorepanel b;
                b = new scorepanel();
                b.score(appleeaten);
            }
        }
        //creating a new apple for x axis & y axis
        public void newapple ()
        {
            Random random = new Random();
            applex = random.nextInt(58) * per_unit_size;
            appley = random.nextInt(32) * per_unit_size;
        }
        public void move ()
        {
            //calculation for moving snake
            for (int i = bodyparts; i > 0; i--) {
                x[i] = x[i - 1];
                y[i] = y[i - 1];
            }
            //function declared for which the snake move along the input keyword direction
            switch (direction) {
                case 'U'://for up direction
                    y[0] = y[0] - per_unit_size;
                    break;
                case 'D'://for down direction
                    y[0] = y[0] + per_unit_size;
                    break;
                case 'L'://for left direction
                    x[0] = x[0] - per_unit_size;
                    break;
                case 'R'://for right direction
                    x[0] = x[0] + per_unit_size;
                    break;
            }
        }
        public void checkapple ()
        {
            //when snake head location == apple location
            if (x[0] == applex && y[0] == appley) {
                bodyparts++;
                appleeaten++;
                newapple();  //when snake eat an apple the newapple function called for creating new random apple.
            }
        }
        public void checkcollision ()
        {
            for (int i = bodyparts; i > 0; i--) {   //if the head of the snack touches its body.
                if (x[0] == x[i] && y[0] == y[i]) {
                    running = false;
                }
            }
            //checking the head location doesn't touch left border
            if (x[0] < 0) {
                running = false;
            }
            //checking the head location doesn't touch right border
            if (x[0] > 1470) {
                running = false;
            }
            //checking the head location doesn't touch up border
            if (y[0] < 0) {
                running = false;
            }
            //checking the head location doesn't touch bottom border
            if (y[0] > 800) {
                running = false;
            }
            //when the snack get collision then the game time will be finished
            if (!running) {
                timer.stop();
            }
        }
        public void gameover (Graphics g)
        {
            //setting up for GAME OVER operation.
            g.setColor(Color.BLUE);
            g.setFont(new Font("Ink Free", Font.ITALIC, 100));
            g.drawString("GAME OVER", 450, 150);

        }
        @Override
        public void actionPerformed (ActionEvent p){
            // this is the function which control the all game actions.
            if (running) {
                move();
                checkapple();
                checkcollision();
            }
            repaint();
    }
        public class MyKeyAdaptor extends KeyAdapter {
            @Override
            public void keyPressed(KeyEvent e) {
                //for taking direction from keyboard.
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_LEFT:
                        if (direction != 'R') {
                            direction = 'L';
                        }
                        break;
                    case KeyEvent.VK_RIGHT:
                        if (direction != 'L') {
                            direction = 'R';
                        }
                        break;
                    case KeyEvent.VK_UP:
                        if (direction != 'D') {
                            direction = 'U';
                        }
                        break;
                    case KeyEvent.VK_DOWN:
                        if (direction != 'U') {
                            direction = 'D';
                        }
                        break;
                }
            }
        }
    }


