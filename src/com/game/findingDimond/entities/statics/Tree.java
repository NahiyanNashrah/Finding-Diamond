package com.game.findingDimond.entities.statics;

import com.game.findingDimond.Handler;
import com.game.findingDimond.entities.creatures.Player;
import com.game.findingDimond.graphics.Assets;
import com.game.findingDimond.tiles.Tile;
import java.awt.Color;
import java.awt.Graphics;

/**
 *
 * @GAME
 */
public class Tree extends StaticEntity{
    
    //public static boolean tactive = true;

    public Tree(Handler handler, float x, float y) {
        super(handler, x, y, Tile.TILEWIDTH, Tile.TILEHEIGHT);
        
        bounds.x = 25;
        bounds.y = (int) (height / 2.5f);
        bounds.width = width - 48;
        bounds.height = (int) (height - height + 40.0f);
        
//        if(Player.Score >= 3){
//            tactive = false;
//        }
    }

    @Override
    public void tick() {
   
    }
    @Override
    public void die(){
        
    }
    
    
//    public boolean isActive(){
//        return tactive;
//    }

    @Override
    public void render(Graphics g) {
        g.drawImage(Assets.tree, (int)(x - handler.getGameCamera().getxOffset()) , (int)(y - handler.getGameCamera().getyOffset()), width, height, null);
        
//        g.setColor(Color.GRAY);
//        g.fillRect((int) (x + bounds.x - handler.getGameCamera().getxOffset()),
//                (int) (y + bounds.y - handler.getGameCamera().getyOffset()),
//                bounds.width, bounds.height);
    }
    
}
