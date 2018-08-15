package com.game.findingDimond.entities;

import com.game.findingDimond.Handler;
import com.game.findingDimond.entities.creatures.Player;
import com.game.findingDimond.entities.statics.Tree;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Comparator;

/**
 *
 * @GAME
 */
public class EntityManager {
    
    private Handler handler;
    private Player player;
    private ArrayList<Entity> entities;
    
    private Comparator<Entity> renderSorter = new Comparator<Entity>(){
        @Override
        public int compare(Entity a, Entity b) {
            if(a.getY() + a.getHeight() < b.getY() + b.getHeight()) return -1;
            return 1;
        }
        
    };
    
    public EntityManager(Handler handler, Player player){
        this.handler = handler;
        this.player = player;
        entities = new ArrayList<Entity>();
        addEntity(player);
    }
    
    public void tick(){
        Entity[] tmp = new Entity[entities.size()];
        entities.toArray(tmp);
//        for(int i=0;i<entities.size();i++){
//            Entity e = entities.get(i);
//            e.tick();
//            
//            if(!e.isActive()){
//                entities.remove(e);
//            }
////             if(i == 6){
////                 entities.remove(e);
////             }
//            
//        }
    for(int i=0;i<tmp.length;i++)
    {
        tmp[i].tick();
        if(!tmp[i].isActive() && !(tmp[i] instanceof Tree)){
            entities.remove(tmp[i]);
        }
        
        if(tmp[i] instanceof Tree)
        {
            if(Player.Score >= 10){
                entities.remove(tmp[i]);
            }
        }
    }
        entities.sort(renderSorter);
    }
    
    public void render(Graphics g){
        for(Entity e : entities){
            e.render(g);
        }
    }
    
    public void addEntity(Entity e){
        entities.add(e);
    }
        
    
    /*
    * Getters and Setters
    */
    public Handler getHandler(){
        return handler;
    }
    
    public void setHandler(Handler handler){
        this.handler = handler;
    }
    
    public Player getPlayer(){
        return player;
    }
    
    public void setPlayer(Player player){
        this.player = player;
    }
    
    public ArrayList<Entity> getEntity(){
        return entities;
    }
    
    public void setEntity(ArrayList<Entity> entitys){
        this.entities = entitys;
    }
}
