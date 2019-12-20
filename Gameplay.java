package anishGame;

//this is every things that have to be imported to run the game//

import java.awt.event.KeyListener;
import javax.swing.Timer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.Rectangle;
import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.Font;

public class Gameplay extends JPanel implements KeyListener, ActionListener {
    private boolean play= false;
    private int score= 0;
    private int totalBricks= 21;

    // the time of ball to move around

    private Timer timer;
    private int delay=8;

    //position of player and the ball

    private int playerx=310;
    private int ballposx=120;
    private int ballposy=350;
    private int ballxdir=-1;
    private int ballydir=-2;

    private MapGenerator map;


    public Gameplay(){
        map= new MapGenerator(3,12);
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        timer = new Timer(delay,this);
         timer.start();
        }

        //This is the graphis of all ball peddle brick and background

        public void paint(Graphics g) {

            //background
            g.setColor(Color.black);
        g.fillRect(1, 1, 692, 592);


        //drawing map
        map.draw((Graphics2D)g);
        

        // borders
        g.setColor(Color.black);
        g.fillRect(0, 0, 3, 592);
        g.fillRect(0, 0, 692, 3);
        g.fillRect(691, 0, 3, 592);

        //scores
        g.setColor(Color.white);
        g.setFont(new Font("serif", Font.BOLD, 25));
        g.drawString(""+score,590, 30);


        //the paddle
        g.setColor(Color.blue);
        g.fillRect(playerx,550,100,8);

        //ball
        g.setColor(Color.yellow);
        g.fillOval(ballposx,ballposy,20,20);

        if(ballposy >570){
            play= false;
           int ballxdir = 0;
            int ballydir = 0;
            g.setColor(Color.RED);
            g.setFont(new Font("serif", Font.BOLD, 30));
            g.drawString("Game over,Scores: " ,190,300);

            g.setFont(new Font("serif", Font.BOLD, 20));
            g.drawString("press Enter to Restart", 230 ,350);

        }

            
        }

    @Override
    public void actionPerformed(ActionEvent e) {
        timer.start();

        if(play){

            //for ball intersection with paddle

                if(new Rectangle(ballposx, ballposy,20,20).intersects(new Rectangle(playerx,550 ,100 ,8))){
                    ballydir=-ballydir;
                }
                //For brick intersection with ball..

               A: for(int i= 0; i<map.map.length; i++){
                    for(int j=0; j<map.map[0].length; j++){
                        if(map.map[i][j]>0){
                            int brickx=j*map.brickwidth+80;
                            int bricky=i*map.brickwidth+50;
                            int brickwidth=map.brickwidth;
                            int brickheight=map.brickheight;
                            Rectangle rect=new Rectangle(brickx, bricky,brickwidth,brickheight);
                            Rectangle ballRect= new Rectangle(ballposx, ballposy,20,20);
                            Rectangle brickRect=rect;
                            
                            //to give the reasult after the ball intersect with the brick and 
                            // also to change the value of brick to 0 after it interect with the ball.
                            if(ballRect.intersects(brickRect)){
                                map.setBrickValue(0,i,j);
                                totalBricks--;
                                score+=5;
                                if(ballposx + 19 <= brickRect.x || ballposx + 1>=brickRect.x+brickRect.width){
                                    ballxdir = -ballxdir;
                                }else{
                                    ballydir = -ballydir;
                                }
                                //this break A is to take compiler out of the outer loop if only add break then 
                                // it will only stop inner loop
                                break A;

                            }
                        
                        }
                    }
                }


            // if the ball is touching all the right, left and top boader
            ballposx += ballxdir;
            ballposy += ballydir;
            if(ballposx <0 ){
                ballxdir= -ballxdir;
           
        }
        if(ballposy <0 ){
            ballydir= -ballydir;
        }
        if(ballposx >670 ){
            ballxdir= -ballxdir;
        }
    }
        

        repaint();

    }

    @Override
    public void keyTyped(KeyEvent e) {}
    
    @Override
    public void keyReleased(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {

        if(e.getKeyCode()==KeyEvent.VK_RIGHT){
            if(playerx>=600){
                playerx=600;
            }else{
                moveRight();
            }

        }
        if(e.getKeyCode()==KeyEvent.VK_LEFT){
            if(playerx < 10){
                playerx = 10;
            }else{
                moveLeft();
            }
            
        }
        if(e.getKeyCode()==KeyEvent.VK_ENTER){
            if(!play)
            play=true;
            ballposx=120;
            ballposy=350;
            ballxdir=-1;
            ballydir=-2;
            playerx=310;
            score=0;
            totalBricks=21;
            map= new MapGenerator(3,12);
            
            repaint();

        }
    }
    public void moveRight(){
        play=true;
        playerx+=20;
    }
    public void moveLeft(){
        play=true;
        playerx-=20;
    }


   
        
    
}
