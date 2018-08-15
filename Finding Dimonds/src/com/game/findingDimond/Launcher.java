package com.game.findingDimond;

import com.game.findingDimond.AllSound.Music;

/**
 *
 * @GAME
 */
public class Launcher {
    
    public static void main(String[] args) {
            
        Music music = new Music();
        music.LoadSound();
        
        Game game = new Game("Finding Dimond ...!", 640, 360);
        game.start();
                
    }
}
