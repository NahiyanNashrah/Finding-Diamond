package com.game.findingDimond.tiles;

import com.game.findingDimond.graphics.Assets;
import java.awt.image.BufferedImage;

/**
 *
 * @GAME
 */
public class RockTile extends Tile{
    
    public RockTile(int id) {
        super(Assets.stone, id);
    }
    
    @Override
    public boolean isSolid(){
        return true;
    }
    
}
