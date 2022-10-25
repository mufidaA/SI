package spaceInvaders;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

import spaceInvaders.Stage.*;


public class spaceInvaders extends Canvas {
    private BufferedImage playerGrafic;
    private BufferedImage aliensGrafic;
    private BufferedImage bulletGrafic;
    private Stage gameStage;
    Boolean gameRunning = false;
    //static private Timer T;

    public spaceInvaders() {
        loadGrafics();
        gameStage = new Stage(400, 400);
      
        /*  addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                int keyCode = e.getKeyCode();
                if (keyCode == KeyEvent.VK_LEFT) {
                    gameStage.movePlayer(-1);
                } else if (keyCode == KeyEvent.VK_RIGHT) {
                    gameStage.movePlayer(1);
                } 
            }
        });
        launchTimer();*/
    }

    public void updateGame(){
        gameStage.AnimateAliens(0);
        gameStage.UpdateBullet(0);
    }

    public void moveLeft() {
        gameStage.movePlayer(-1);
        updateGame();
    }
    public void moveRight() {
        gameStage.movePlayer(1);
        updateGame();
    }
    public void shootButton() {
        if (gameRunning && !gameStage.isGameOver()){
            gameStage.shootBullet();
        }
        else {
            gameRunning = true;
            gameStage.setUp();
            
        }
        updateGame();
    };
/* 
    public void launchTimer() {
        int delay = 1000; // delay for 1 sec.
        int period = 40; // repeat every sec.
        spaceInvaders.T = new Timer();
        spaceInvaders.T.scheduleAtFixedRate(new TimerTask() {
            public void run() {
                if (gameStage.isGameOver()) {
                    gameRunning = false;
                }else if(gameRunning){
                    gameStage.AnimateAliens(period/100);
                    gameStage.UpdateBullet(period/100);
                }
                paint(getGraphics());
            }
        }, delay, period);
    }
*/
 /*    public static void start() {
        JFrame frame = new JFrame("Space Invaders");
        Canvas canvas = new spaceInvaders();
        canvas.setSize(400, 430);
        frame.add(canvas);
        frame.pack();
        frame.setVisible(true);

        ((spaceInvaders) canvas).loadGrafics();
    }
*/
    public void loadGrafics() {
        try {
            playerGrafic = ImageIO.read(new File("src/main/java/spaceInvaders/images/player1.png"));
            aliensGrafic = ImageIO.read(new File("src/main/java/spaceInvaders/images/alien.png"));
            bulletGrafic = ImageIO.read(new File("src/main/java/spaceInvaders/images/shot.png"));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void paint(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, gameStage.Width(), gameStage.Height());
        
        
        if(!gameRunning){
            g.setColor(Color.WHITE);
            g.drawString("Click to Start Space Invaders", gameStage.Width()/2-100, gameStage.Height()/2);
            if(gameStage.isGameOver())
                g.drawString("Game Over. Your score: " + gameStage.getScore(), 
                            gameStage.Width()/2-50, gameStage.Height()/2-50);
        }else{
            g.drawImage(playerGrafic, 
                        gameStage.getPlayer().getTopLeftX(), 
                        gameStage.getPlayer().getTopLeftY(), this);
            for (int i = 0; i < gameStage.getAlienCount(); i++) {
                g.drawImage(aliensGrafic, gameStage.getAlien(i).getTopLeftX(), 
                                          gameStage.getAlien(i).getTopLeftY(), this);
            }
            List<Bullet> bl = gameStage.getBullet();
            for (int i = 0; i < bl.size(); i++){
                Bullet bul = bl.get(i);
                g.drawImage(bulletGrafic, bul.getTopLeftX(),  bul.getTopLeftY(), this);
            }
            g.setColor(Color.WHITE);
            g.drawString("SCORE: "+ gameStage.getScore(), 10, 10);
            if (gameStage.getAlien().size() == 0) {
                g.drawString("You win!"+ "\r\n" + " Your score: " + gameStage.getScore(), 
                            gameStage.Width()/2-50, gameStage.Height()/2-50);
            }
        }
       
    }
}
