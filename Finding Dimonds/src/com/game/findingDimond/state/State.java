package com.game.findingDimond.state;

import com.game.findingDimond.Game;
import com.game.findingDimond.Handler;
import java.awt.Graphics;

/**
 *
 * @GAME
 */
public abstract class State {
    
    private static State currentState = null;
    
    public static void setState(State state){
        currentState = state;
    }
    
    public static State getState(){
        return currentState;
    }
    
    /*
    * Class
    */
    protected Handler handler;
    
    public State(Handler handler){
        this.handler = handler;
        
    }
    
    public abstract void tick();
    public abstract void render(Graphics g);
    
    
}
