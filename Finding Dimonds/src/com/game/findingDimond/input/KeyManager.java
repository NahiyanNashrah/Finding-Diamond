package com.game.findingDimond.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 *
 * @GAME
 */
public class KeyManager implements KeyListener{
    
    private boolean[] keys;
    public boolean up, down, left, right;
    public boolean aUp, aDown, aLeft, aRight;
    public boolean back;
    
    public KeyManager(){
        keys = new boolean[256];
    }
    
    public void tick(){
        up = keys[KeyEvent.VK_W] || keys[KeyEvent.VK_UP];
        down = keys[KeyEvent.VK_S] || keys[KeyEvent.VK_DOWN];
        left = keys[KeyEvent.VK_A] || keys[KeyEvent.VK_LEFT];
        right = keys[KeyEvent.VK_D] || keys[KeyEvent.VK_RIGHT];
        
        aUp = keys[KeyEvent.VK_W] || keys[KeyEvent.VK_UP];
        aDown = keys[KeyEvent.VK_S] || keys[KeyEvent.VK_DOWN];
        aLeft = keys[KeyEvent.VK_A] || keys[KeyEvent.VK_LEFT];
        aRight = keys[KeyEvent.VK_D] || keys[KeyEvent.VK_RIGHT];
        
        
        back = keys[KeyEvent.VK_SPACE];
    } 

    @Override
    public void keyPressed(KeyEvent e) {
        keys[e.getKeyCode()] = true; 
        System.out.println("Pressed...!");
    }

    @Override
    public void keyReleased(KeyEvent e) {
        keys[e.getKeyCode()] = false;
    }
    
    @Override
    public void keyTyped(KeyEvent e) {
        
    }
    
}
