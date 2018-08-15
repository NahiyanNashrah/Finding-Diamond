package com.game.findingDimond.entities.creatures;

import com.game.findingDimond.Game;
import com.game.findingDimond.Handler;
import com.game.findingDimond.entities.Entity;
import com.game.findingDimond.graphics.Animation;
import com.game.findingDimond.graphics.Assets;
import com.game.findingDimond.state.State;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;

/**
 *
 * @GAME
 */
public class Player extends Creature{
    
    /*
    * Animations
    */
    private Animation animDown, animUp, animLeft, animRight;
    public int gametime = 3;
    public static int Score = 0;
    public int FinalScore = 0;
    public int HighScore = 0;
    private boolean doScore = false;
    private Font scoreFont;
    
    // Time 
    private long elapsedMS;
    private long fastestMS;
    private long startTime;
    private String formattedTime = "00:00:000";
            
    
    // Saving
    private String saveDataPath;
    private String fileName= "SaveData";
    

    public Player(Handler handler, float x, float y) {
        super(handler, x, y, Creature.DEFAULT_CREATURE_WIDTH, Creature.DEFAULT_CREATURE_HEIGHT);
        
        // Data Saving
        try {
            saveDataPath = Player.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        scoreFont = Game.main.deriveFont(24f);
        
        
        bounds.x = 10;
        bounds.y = 24;
        bounds.width = 38;
        bounds.height = 34;
        
        /*
        * Animations
        */
        animDown = new Animation(500, Assets.player_down);
        animUp = new Animation(500, Assets.player_up);
        animLeft = new Animation(500, Assets.player_left);
        animRight = new Animation(500, Assets.player_right);
        
        // Time
        startTime = System.nanoTime();
        
        loadHighScore();
    }
    
    private void createSaveData(){
        try {
            File file = new File(saveDataPath, fileName);
            
            FileWriter output = new FileWriter(file);
            BufferedWriter writer = new BufferedWriter(output);
            writer.write("" +0);
            
            // Time
            writer.newLine();
            writer.write("" + Integer.MAX_VALUE);
            
            writer.close();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private void loadHighScore(){
        try {
            File f= new File(saveDataPath, fileName);
            if(!f.isFile()){
                createSaveData();
            }
            
            BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(f)));
            HighScore = Integer.parseInt(reader.readLine());
            
            // Time
            fastestMS = Long.parseLong(reader.readLine());
            
            
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private void setHighScore(){
        FileWriter output = null;
        
        try {
            File f = new File(saveDataPath, fileName);
            
            output = new FileWriter(f);
            BufferedWriter writer = new BufferedWriter(output);
     
            writer.write("" + HighScore);
            
            // Time
            writer.newLine();
            if(elapsedMS <= fastestMS){
                writer.write(""+ elapsedMS);
            }
            else{
                writer.write("" + fastestMS);
            }
   
            writer.close();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    

    @Override
    public void tick() {
        
        // Time
        elapsedMS = ( System.nanoTime() - startTime ) / 1000000;
        formattedTime =formatTime(elapsedMS);
        
        if(Score> HighScore){
            HighScore = Score;
        }
        setHighScore();
        
        /*
        * Animations
        */
        animDown.tick();
        animUp.tick();
        animLeft.tick();
        animRight.tick();
        
        // Movement
        getInput();
        move();
        handler.getGameCamera().centerOnEntity(this);
        
        // Attack
        checkAttacks();
    }
    
    // Timer method
    private String formatTime(long millis){
        String formattedTime ;
        
        String hourFormat = "";
        int hours = (int) (millis / 3600000);
        if(hours >= 1){
            millis -= hours * 3600000;
            if(hours < 10){
                hourFormat = "0" + hours;
            }
            else{
                hourFormat = "" + hours;
            }
            hourFormat += ":";
        }
        
        String minuteFormat;
        int minutes = (int)(millis / 60000);
        if(minutes >= 1){
            millis -= minutes * 60000;
            if(minutes < 10){
                minuteFormat = "0" + minutes;
            }
            else{
                minuteFormat = "" + minutes;
            }
        }
        else{
            minuteFormat = "00:";
        }
        
        String secondFormat;
        int seconds = (int)(millis / 1000);
        if(seconds >= 1){
            millis -= seconds * 1000;
            if(seconds < 10){
                secondFormat = "0" + seconds;
            }
            else{
                secondFormat = "" + seconds;
            }
        }
        else{
            secondFormat = "00:";
        }
        
        String milliFormat;
        if(millis <= 99){
            milliFormat = "" + millis;
        }
        else if(millis > 9){
            milliFormat = "0" + millis;
        }
        else{
            milliFormat = "00" + millis;
        }
        
        formattedTime = hourFormat + minuteFormat + ":" + secondFormat + ":" + milliFormat;
        if(minutes >= gametime){
            //System.exit(1);
//                FinalScore = Score;
                State.setState(handler.getGame().gameOverState);
        }
        
        return formattedTime;
        
    }
 
    private void checkAttacks(){
        Rectangle cb = getCollisionBounds(0, 0);
        Rectangle ar = new Rectangle();
        int arSize = 20;
        ar.width = arSize;
        ar.height = arSize;
        
       if(handler.getKeyManager().aUp){
            ar.x = cb.x + cb.width / 2 - arSize / 2;
            ar.y = cb.y - arSize;
        }
       else if(handler.getKeyManager().aDown){
            ar.x = cb.x + cb.width / 2 - arSize / 2;
            ar.y = cb.y + cb.height;
        }
       else if(handler.getKeyManager().aLeft){
            ar.x = cb.x - arSize;
            ar.y = cb.y + cb.height / 2 - arSize / 2;
        }
       else if(handler.getKeyManager().aRight){
            ar.x = cb.x + cb.width;
            ar.y = cb.y + cb.height / 2 - arSize / 2;
        }
       else{
           return;
       }
       
       for(Entity e : handler.getWorld().getEntityManager().getEntity()){
           if(e.equals(this)) continue;
           if(e.getCollisionBounds(0, 0).intersects(ar)){
               e.hurt(1);
               doScore = true;
               return;
           }
       }
       
       if(doScore) {
           if(Score >10 && Score <=330){
               Score += 10 ;
           }
           else Score++ ;
           
               System.err.println("Score :" + Score);
               doScore = false;
               if(Score >= 10){
                   gametime = 10;
               }
               
               if(Score >= 338){
                   State.setState(handler.getGame().gameOverState);
               }
           }
    }
    
    @Override
    public void die(){
        System.out.println("You die...!");
    }
    
    private void getInput(){
        xMove = 0;
        yMove = 0;
        
        if(handler.getKeyManager().up) yMove = -speed;
        if(handler.getKeyManager().down) yMove = speed;
        if(handler.getKeyManager().left) xMove = -speed;
        if(handler.getKeyManager().right) xMove = speed;
        
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(getCurrentAnimationFrame(), (int)(x - handler.getGameCamera().getxOffset()), (int)(y - handler.getGameCamera().getyOffset()), width, height, null);
        
//        g.setColor(Color.GRAY);
//        g.fillRect((int) (x + bounds.x - handler.getGameCamera().getxOffset()),
//                (int) (y + bounds.y - handler.getGameCamera().getyOffset()),
//                bounds.width, bounds.height);

        g.setColor(Color.YELLOW);
        g.setFont(scoreFont);
        g.drawString("" + Score, 30, 40);
        FinalScore = Score;
        g.setColor(Color.RED);
        g.drawString("Best : " + HighScore, 500, 40);
        
        // Time
        g.setColor(Color.BLACK);
        g.drawString("Time : " + formattedTime, 30, 90);
        
    }
    
    private BufferedImage getCurrentAnimationFrame(){
        if(xMove < 0){
            return animLeft.getCurrentFrame();
        }
        else if(xMove > 0){
            return animRight.getCurrentFrame();
        }
        else if(yMove < 0){
            return animUp.getCurrentFrame();
        }
        else {
            return animDown.getCurrentFrame();
        }
    }
    
}
