package com.game.findingDimond.state;

import com.game.findingDimond.Game;
import com.game.findingDimond.Handler;
import com.game.findingDimond.entities.creatures.Player;
import com.game.findingDimond.entities.statics.Dimond;
import com.game.findingDimond.entities.statics.Tree;
import com.game.findingDimond.graphics.Assets;
import com.game.findingDimond.tiles.Tile;
import com.game.findingDimond.worlds.World;
import java.awt.Graphics;

/**
 *
 * @GAME
 */
public class GameState extends State{
    
    private World world;
    
    public GameState(Handler handler){
        super(handler);
        world = new World(handler, "res/worlds/world1.txt");
        handler.setWorld(world);
    }

    @Override
    public void tick() {
        world.tick();
    }

    @Override
    public void render(Graphics g) {
        world.render(g);
    }
    
}
