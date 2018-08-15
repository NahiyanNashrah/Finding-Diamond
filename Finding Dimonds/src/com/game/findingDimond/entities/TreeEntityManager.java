package com.game.findingDimond.entities;

import com.game.findingDimond.Handler;
import com.game.findingDimond.entities.creatures.Player;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Comparator;

/**
 *
 * @GAME
 */
public class TreeEntityManager {
    private Handler handler;
    private Player player;
    private ArrayList<TreeEntity> TEntitys;
    //private Entity tEntity;
    
    private Comparator<Entity> renderSorter = new Comparator<Entity>(){
        @Override
        public int compare(Entity a, Entity b) {
            if(a.getY() + a.getHeight() < b.getY() + b.getHeight()) return -1;
            return 1;
        }
        
    };
    
    public TreeEntityManager(Handler handler, Player player){
        this.handler = handler;
        this.player = player;
        TEntitys = new ArrayList<TreeEntity>();
        //addEntity(player);
    }
    
    public void tick(){
        for(int i=0;i<TEntitys.size();i++){
            TreeEntity e = TEntitys.get(i);
            e.tick();
            if(!e.isActive()){
                //entities.remove(e);
                TEntitys.remove(e);
            }
        }
        //TEntitys.sort(renderSorter);

        if(Player.Score >= 5){
            TEntitys = null;
        }
    }
    
    public void render(Graphics g){
        for(TreeEntity e : TEntitys){
            //e.render(g);
            e.render(g);
        }
        
    }
    
    public void addEntity(TreeEntity e){
        TEntitys.add(e);
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
    
    public ArrayList<TreeEntity> getEntity(){
        return TEntitys;
    }
    
    public void setEntity(ArrayList<TreeEntity> entitys){
        this.TEntitys =  entitys;
    }
}
