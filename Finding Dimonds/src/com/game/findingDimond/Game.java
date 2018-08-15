package com.game.findingDimond;

import com.game.findingDimond.display.Display;
import com.game.findingDimond.graphics.Assets;
import com.game.findingDimond.graphics.GameCamera;
import com.game.findingDimond.graphics.ImageLoader;
import com.game.findingDimond.graphics.SpriteSheet;
import com.game.findingDimond.input.KeyManager;
import com.game.findingDimond.input.MouseManager;
import com.game.findingDimond.state.GameOverState;
import com.game.findingDimond.state.GameState;
import com.game.findingDimond.state.HighScoreState;
import com.game.findingDimond.state.InstructionState;
import com.game.findingDimond.state.MenuState;
import com.game.findingDimond.state.State;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @Game
 */
public class Game implements Runnable{
    
    public static final Font main = new Font("Bebas Neue Regular", Font.PLAIN, 28);
    
    private Display display;
    private int width, height;
    public String title;
    
    private boolean running = false;
    private Thread thread;
    
    private BufferStrategy bs;
    private Graphics g;
    
    /*
    * States
    */
    public State gameState;
    public State menuState;
    public State gameOverState;
    public State instructionState;
    public State highScoreState;
    
    /*
    * Input
    */
    private KeyManager keyManager;
    private MouseManager mouseManager;
    
    /*
    * Camera
    */
    private GameCamera gameCamera;
    
    /*
    * Handler
    */
    private Handler handler;
    
    public Game(String title, int width, int height){
        this.width = width;
        this.height = height;
        this.title = title;
        keyManager = new KeyManager();
        mouseManager = new MouseManager();
        
        //menuSound = new MenuSound("/res/Sounds/OneMusic.wav");
    }
    
    private void init(){
         display = new Display(title, width, height);
         display.getFrame().addKeyListener(keyManager);
         display.getFrame().addMouseListener(mouseManager);
         display.getFrame().addMouseMotionListener(mouseManager);
         display.getCanvas().addMouseListener(mouseManager);
         display.getCanvas().addMouseMotionListener(mouseManager);
         Assets.init();
         
         handler = new Handler(this);
         gameCamera = new GameCamera(handler, 0, 0);
         
         gameState = new GameState(handler);
         menuState = new MenuState(handler);
         gameOverState = new GameOverState(handler);
         instructionState = new InstructionState(handler);
         highScoreState = new HighScoreState(handler);
         
         //State.setState(gameState);
         State.setState(menuState);
    }
    
    
    private void tick(){
        keyManager.tick();
        
        if(State.getState() != null){
            State.getState().tick();
        }
        
    }
    
    private void render(){
//        if(handler.getKeyManager().back)  State.setState(handler.getGame().menuState);
        
        bs = display.getCanvas().getBufferStrategy();
        if(bs == null){
            display.getCanvas().createBufferStrategy(3);
            return;
        }
        g = bs.getDrawGraphics();
        g.clearRect(0, 0, width, height);   // Clear the frame
        
        if(State.getState() != null){
            State.getState().render(g);
        }
        
        bs.show();
        g.dispose();
    }

    @Override
    public void run() {
        
        init();
        
        int fps = 60;
        double timePerTick = 1000000000 / fps;
        double delta = 0;
        long now;
        long lastTime = System.nanoTime();
        long timer = 0;
        int ticks = 0;
        
        while(running){
            now = System.nanoTime();
            delta += (now - lastTime) / timePerTick;
            timer += now - lastTime;
            lastTime = now;
            
            if(delta >= 1){
                tick();
                render();
                ticks++;
                delta--;
            }
            
            if(timer >= 1000000000){
                System.out.println("Ticks and Frames : " +ticks);
                ticks = 0;
                timer = 0;
            }
        }
        
        stop();
    }
    
    public KeyManager getKeyManager(){
        return keyManager;  
    }
    
    public MouseManager getMouseManager(){
        return mouseManager;
    }
    
    public GameCamera getGameCamera(){
        return gameCamera;
    }
    
    public int getWidth(){
        return width;
    }
    
     public int getHeight(){
        return height;
    }
    
    public synchronized void start(){
        if(running) return;
        running = true;
        thread =  new Thread(this);
        thread.start();
    }
    
    public synchronized void stop(){
        if(!running)return;
        running = false;
        try {
            thread.join();
        } catch (InterruptedException ex) {
            Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
