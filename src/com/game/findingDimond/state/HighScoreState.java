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
public class HighScoreState extends State{

    public HighScoreState(Handler handler) {
        super(handler);
    }

    @Override
    public void tick() {
        
    }

    @Override
    public void render(Graphics g) {
        if(handler.getKeyManager().back)  State.setState(handler.getGame().menuState);
        
        Player player = new Player(handler, 0, 0);
        int hs = player.HighScore;
        
        Graphics2D g2d = (Graphics2D) g ;
        
        g.drawImage(Assets.menuBackground, 0, 0, 640, 360, null);
        Font font = new Font("arial", Font.CENTER_BASELINE, 50);
        g.setFont(font);
        g.setColor(Color.RED);
        g.drawString("High Score : " +hs, handler.getWidth()/6, 60);
    }
    
}
