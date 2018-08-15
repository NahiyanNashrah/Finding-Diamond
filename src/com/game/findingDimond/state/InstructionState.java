package com.game.findingDimond.state;

import com.game.findingDimond.Handler;
import com.game.findingDimond.graphics.Assets;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

/**
 *
 * @GAME
 */
public class InstructionState extends State{
    
    public Rectangle menuBackButton = new Rectangle(handler.getWidth()/5 + 350, 270, 120, 50);

    public InstructionState(Handler handler) {
        super(handler);
        
    }

    @Override
    public void tick() {
        int x, y;
        x = handler.getMouseManager().getMouseX();
        y = handler.getMouseManager().getMouseY();
        
        
//        if(x >= handler.getWidth()/5 + 100 && x <= handler.getWidth()/5+200){
//            
//            if(y >= 270 && y <= 330){
//               // State.setState(handler.getGame().menuState);
//                 //System.exit(1);
//            }
//        }
    }

    @Override
    public void render(Graphics g) {
        Graphics2D g2d = (Graphics2D) g ;
        
        g.drawImage(Assets.dirt, 0, 0, 640, 360, null);
        Font font = new Font("arial", Font.CENTER_BASELINE, 50);
        g.setFont(font);
        g.setColor(Color.GREEN);
        g.drawString("Instruction...!", handler.getWidth()/6, 60);
        
        
        Font font1 = new Font("arial", Font.CENTER_BASELINE, 20);
        g.setFont(font1);
        g.setColor(Color.BLUE);
        g.drawString("Use arrow keys to move player . ", handler.getWidth()/6, 170);
        g.drawString("Collect all the Diamond to complete the stage .", handler.getWidth()/6, 195);
        g.setColor(Color.RED);
        g.drawString("*Press space to go main menu. ", handler.getWidth()/6, 220);
        
        if(handler.getKeyManager().back)  State.setState(handler.getGame().menuState);
        
        //button
//        Font font1 = new Font("arial", Font.CENTER_BASELINE, 30);
//        g.setFont(font);
//        g.drawString("back", menuBackButton.x + 20, menuBackButton.y + 215);
//        g2d.draw(menuBackButton);
    }
    
}
