package com.game.findingDimond.entities.statics;

import com.game.findingDimond.Handler;
import com.game.findingDimond.graphics.Assets;
import com.game.findingDimond.tiles.Tile;
import java.awt.Color;
import java.awt.Graphics;

/**
 *
 * @GAME
 */
public class Dimond extends StaticEntity{

    public Dimond(Handler handler, float x, float y) {
        super(handler, x, y, Tile.TILEWIDTH, Tile.TILEHEIGHT);
        
        bounds.x = 16;
        bounds.y = (int) (height / 3.5f);
        bounds.width = width - 31;
        bounds.height = (int) (height - height + 24.0f);
    }

    @Override
    public void tick() {
       
    }
    
    @Override
    public void die(){
        
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(Assets.dimond, (int)(x - handler.getGameCamera().getxOffset()) , (int)(y - handler.getGameCamera().getyOffset()), width, height, null);
        
//        g.setColor(Color.GRAY);
//        g.fillRect((int) (x + bounds.x - handler.getGameCamera().getxOffset()),
//                (int) (y + bounds.y - handler.getGameCamera().getyOffset()),
//                bounds.width, bounds.height);
    }
    
}
