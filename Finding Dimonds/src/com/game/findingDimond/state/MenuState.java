package com.game.findingDimond.state;

import com.game.findingDimond.Game;
import com.game.findingDimond.Handler;
import com.game.findingDimond.graphics.Assets;
import com.game.findingDimond.ui.ClickListener;
import com.game.findingDimond.ui.UIImageButton;
import com.game.findingDimond.ui.UIManager;
import java.awt.Button;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.io.File;

/**
 *
 * @GAME
 */
public class MenuState extends State{
    
    /*
    * Menu
    */
    public Rectangle playButton = new Rectangle(handler.getWidth()/5 + 100, 90, 150, 50);
    public Rectangle helpButton  = new Rectangle(handler.getWidth()/5 + 100, 180, 150, 50);
    public Rectangle quitButton = new Rectangle(handler.getWidth()/5 + 100, 270, 150, 50);
    
    
    public MenuState(Handler handler) {
        super(handler);
    }

    @Override
    public void tick() {
        int x, y;
        x = handler.getMouseManager().getMouseX();
        y = handler.getMouseManager().getMouseY();
        
        if(x >= handler.getWidth()/5 + 100 && x <= handler.getWidth()/5+200){
            
            if(y >= 90 && y <= 140) State.setState(handler.getGame().gameState);
        }
        
        if(x >= handler.getWidth()/5 + 100 && x <= handler.getWidth()/5+200){
            
            if(y >= 180 && y <= 235) State.setState(handler.getGame().highScoreState);
        }
        
        if(x >= handler.getWidth()/5 + 100 && x <= handler.getWidth()/5+200){
            
            if(y >= 270 && y <= 330){
                State.setState(handler.getGame().instructionState);
                 //System.exit(1);
            }
        }
    }

    @Override
    public void render(Graphics g) {
        
        Graphics2D g2d = (Graphics2D) g ;
        
        g.drawImage(Assets.menuBackground, 0, 0, 640, 360, null);
        Font font = new Font("arial", Font.CENTER_BASELINE, 50);
        g.setFont(font);
        g.setColor(Color.BLACK);
        g.drawString("FINDING DIAMOND", handler.getWidth()/6, 60);
        
        g.setColor(Color.RED);
        Font font1 = new Font("arial", Font.CENTER_BASELINE, 30);
        g.setFont(font);
        g.drawString("Play", playButton.x + 20, playButton.y + 40);
        g2d.draw(playButton);
        g.drawString("Score", playButton.x + 8, playButton.y + 132);
        g2d.draw(helpButton);
        g.drawString("Help", playButton.x + 20, playButton.y + 220);
        g2d.draw(quitButton);
        
    }
    
    
}
