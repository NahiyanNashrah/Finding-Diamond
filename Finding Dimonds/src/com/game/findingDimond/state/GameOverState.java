package com.game.findingDimond.state;

import com.game.findingDimond.Handler;
import com.game.findingDimond.entities.creatures.Player;
import com.game.findingDimond.graphics.Assets;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

/**
 *
 * @GAME
 */
public class GameOverState extends State{

    public GameOverState(Handler handler) {
        super(handler);
    }

    @Override
    public void tick() {
        
    }

    @Override
    public void render(Graphics g) {
        
        if(handler.getKeyManager().back)  State.setState(handler.getGame().menuState);
        
        int a = Player.Score;
        
        Graphics2D g2d = (Graphics2D) g ;
        
        if(a >= 338){
            g.drawImage(Assets.menuBackground, 0, 0, 640, 360, null);
            Font font = new Font("arial", Font.CENTER_BASELINE, 50);
            g.setFont(font);
            g.setColor(Color.RED);
            g.drawString("You Win...!", handler.getWidth()/6, 60);

            Font font1 = new Font("arial", Font.CENTER_BASELINE, 30);
            g.setFont(font1);
            g.setColor(Color.YELLOW);
            g.drawString("Your Score : "+ a ,handler.getWidth()/6, 120);
        }
        else{
            g.drawImage(Assets.menuBackground, 0, 0, 640, 360, null);
            Font font = new Font("arial", Font.CENTER_BASELINE, 50);
            g.setFont(font);
            g.setColor(Color.RED);
            g.drawString("You loss , Game Over ", handler.getWidth()/6, 60);

            Font font1 = new Font("arial", Font.CENTER_BASELINE, 30);
            g.setFont(font1);
            g.setColor(Color.YELLOW);
            g.drawString("Your Score : "+ a ,handler.getWidth()/6, 120);
        }
    }
    
}
