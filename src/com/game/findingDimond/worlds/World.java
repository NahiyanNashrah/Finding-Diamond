package com.game.findingDimond.worlds;

import com.game.findingDimond.Game;
import com.game.findingDimond.Handler;
import com.game.findingDimond.entities.EntityManager;
import com.game.findingDimond.entities.creatures.Player;
import com.game.findingDimond.entities.statics.Dimond;
import com.game.findingDimond.entities.statics.Tree;
import com.game.findingDimond.graphics.Assets;
import com.game.findingDimond.tiles.Tile;
import com.game.findingDimond.utils.Utils;
import java.awt.Graphics;

/**
 *
 * @GAME
 */
public class World {
    
    private Handler handler;
    private int width, height;
    private int spawnX, spawnY;
    private int [][] tiles;
    Player player;
    
    /*
    * Entities
    */
    private EntityManager entityManager;
    
    public World(Handler handler, String path){
        this.handler = handler;
        entityManager = new EntityManager(handler, new Player(handler, 100, 100));

       entityManager.addEntity(new Tree(handler, 1300, 550));
       
       /*
       * All Dimonds
       */
       
       // Level - 1
        entityManager.addEntity(new Dimond(handler, 100, 180));
        entityManager.addEntity(new Dimond(handler, 380, 360));
        entityManager.addEntity(new Dimond(handler, 1000, 200));
        entityManager.addEntity(new Dimond(handler, 950, 200));
        entityManager.addEntity(new Dimond(handler, 1150, 700));
        entityManager.addEntity(new Dimond(handler, 500, 1000));
        entityManager.addEntity(new Dimond(handler, 200, 650));
        entityManager.addEntity(new Dimond(handler, 1216, 823));
        entityManager.addEntity(new Dimond(handler, 320, 650));
        entityManager.addEntity(new Dimond(handler, 120, 1100)); // 10
        
        // Interface
        entityManager.addEntity(new Dimond(handler, 1344, 70));
        entityManager.addEntity(new Dimond(handler, 1408, 70));
        entityManager.addEntity(new Dimond(handler, 1472, 70));
        entityManager.addEntity(new Dimond(handler, 1344, 140));
        entityManager.addEntity(new Dimond(handler, 1408, 140));
        entityManager.addEntity(new Dimond(handler, 1472, 140));
        entityManager.addEntity(new Dimond(handler, 1344, 210));
        entityManager.addEntity(new Dimond(handler, 1408, 210));
        entityManager.addEntity(new Dimond(handler, 1472, 210));
        entityManager.addEntity(new Dimond(handler, 1344, 280));
        entityManager.addEntity(new Dimond(handler, 1408, 280));
        entityManager.addEntity(new Dimond(handler, 1472, 280));
        entityManager.addEntity(new Dimond(handler, 1344, 350));
        entityManager.addEntity(new Dimond(handler, 1408, 350));
        entityManager.addEntity(new Dimond(handler, 1472, 350));
        entityManager.addEntity(new Dimond(handler, 1344, 420));
        entityManager.addEntity(new Dimond(handler, 1408, 420));
        entityManager.addEntity(new Dimond(handler, 1472, 420)); // 18  , Score = 10 + 180 = 190
        
        entityManager.addEntity(new Dimond(handler, 1344, 700));
        entityManager.addEntity(new Dimond(handler, 1408, 700));
        entityManager.addEntity(new Dimond(handler, 1472, 700));
        entityManager.addEntity(new Dimond(handler, 1344, 770));
        entityManager.addEntity(new Dimond(handler, 1408, 770));
        entityManager.addEntity(new Dimond(handler, 1472, 770));
        entityManager.addEntity(new Dimond(handler, 1344, 840));
        entityManager.addEntity(new Dimond(handler, 1408, 840));
        entityManager.addEntity(new Dimond(handler, 1472, 840));
        entityManager.addEntity(new Dimond(handler, 1344, 910));
        entityManager.addEntity(new Dimond(handler, 1408, 910));
        entityManager.addEntity(new Dimond(handler, 1472, 910));
        entityManager.addEntity(new Dimond(handler, 1344, 1150));
        entityManager.addEntity(new Dimond(handler, 1408, 1150));
        entityManager.addEntity(new Dimond(handler, 1472, 1150)); // 15 , Score = 190 + 150 = 340
        
        // Level - 2
        entityManager.addEntity(new Dimond(handler, 1600, 180));
        entityManager.addEntity(new Dimond(handler, 3400, 1030));
        entityManager.addEntity(new Dimond(handler, 3200, 1030));
        entityManager.addEntity(new Dimond(handler, 2800, 1160));
        entityManager.addEntity(new Dimond(handler, 2870, 1160));
        entityManager.addEntity(new Dimond(handler, 2940, 1160));
        entityManager.addEntity(new Dimond(handler, 3010, 1160));
        entityManager.addEntity(new Dimond(handler, 3060, 1180)); // 8 , Score = 340 + 8 = 348
//        entityManager.addEntity(new Dimond(handler, 2800, 180));
//        entityManager.addEntity(new Dimond(handler, 100, 180));
//        entityManager.addEntity(new Dimond(handler, 100, 180));
//        entityManager.addEntity(new Dimond(handler, 100, 180));
        
        
       
        
        
        
        
        loadWorld(path);
        
        entityManager.getPlayer().setX(spawnX);
        entityManager.getPlayer().setY(spawnY);
        
    }
    
    public void tick(){
        entityManager.tick();
    }
    
    public void render(Graphics g){
        int xStart = (int) Math.max(0, handler.getGameCamera().getxOffset() / Tile.TILEWIDTH);
        int xEnd = (int) Math.min(width, (handler.getGameCamera().getxOffset() + handler.getWidth()) / Tile.TILEWIDTH +1);
        int yStart = (int) Math.max(0, handler.getGameCamera().getyOffset() / Tile.TILEHEIGHT);;
        int yEnd = (int) Math.min(height, (handler.getGameCamera().getyOffset() + handler.getHeight()) / Tile.TILEHEIGHT +1);
        
        for(int y=yStart;y<yEnd;y++){
            for(int x=xStart;x<xEnd;x++){
                getTile(x, y).render(g, (int)(x * Tile.TILEWIDTH - handler.getGameCamera().getxOffset()), 
                        (int)(y * Tile.TILEHEIGHT - handler.getGameCamera().getyOffset()));
                
            }
        }
        /*
        * Entities
        */
        entityManager.render(g);
        
    }
    
    public Tile getTile(int x, int y){
        if(x<0 ||y<0 || x>=width || y>=height ) return Tile.grassTile;
        
        Tile t = Tile.tiles[tiles[x][y]];
        if(t == null) return  Tile.DirtTile;
        return t;
    }
    
    private void loadWorld(String path){
        String file = Utils.loadFileAsString(path);
        String[] tokens = file.split("\\s+");
        width = Utils.parseInt(tokens[0]);
        height = Utils.parseInt(tokens[1]);
        spawnX = Utils.parseInt(tokens[2]);
        spawnY = Utils.parseInt(tokens[3]);
        
        tiles = new int[width][height];
        for(int y=0;y<height;y++){
            for(int x=0;x<width;x++){
                tiles[x][y] = Utils.parseInt(tokens[(x + y * width) + 4]);
            }
        }
    }
    
    public int getWidth(){
        return width;
    }
    
    public int getHeight(){
        return height;
    }
    
    public EntityManager getEntityManager(){
        return entityManager;
    }
    
}
