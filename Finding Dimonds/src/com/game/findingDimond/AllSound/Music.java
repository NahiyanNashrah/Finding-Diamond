package com.game.findingDimond.AllSound;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 *
 * @GAME
 */

public class Music {
    
    // to store current position
    Long currentFrame;
    Clip clip;
     
    // current status of clip
    String status;
     
    AudioInputStream audioInputStream;
    public static String filePath;
    public static void LoadSound(){
        
   filePath ="F:\\Java Game\\Finding Dimonds\\res\\Sounds\\OneMusic.wav";
    Music audioPlayer = new Music();
    
    audioPlayer.play();
   
    }
    
    
    
    public Music() {
           
            // create AudioInputStream object
    	
    	try{
    	
            audioInputStream = 
                    AudioSystem.getAudioInputStream(new File(filePath).getAbsoluteFile());
             
            // create clip reference
            clip = AudioSystem.getClip();
             
            // open audioInputStream to the clip
            clip.open(audioInputStream);
             
            clip.loop(Clip.LOOP_CONTINUOUSLY);
        }catch(Exception e) {
        	
        	
        	
        }
    	}

    // Method to play the audio
    public void play() 
    {
        //start the clip
        clip.start();
         
        status = "play";
    }
     
    // Method to pause the audio
    public void pause() 
    {
        if (status.equals("paused")) 
        {
            System.out.println("audio is already paused");
            return;
        }
        this.currentFrame = 
        this.clip.getMicrosecondPosition();
        clip.stop();
        status = "paused";
    }
     
    // Method to resume the audio
    public void resumeAudio() throws UnsupportedAudioFileException,
                                IOException, LineUnavailableException 
    {
        if (status.equals("play")) 
        {
            System.out.println("Audio is already "+
            "being played");
            return;
        }
        clip.close();
       resetAudioStream();
        clip.setMicrosecondPosition(currentFrame);
        this.play();
    }
     
     
    // Method to stop the audio
    public void stop() 
    {
        currentFrame = 0L;
        clip.stop();
        clip.close();
    }
     
     
    // Method to reset audio stream
    public void resetAudioStream() 
    {
        try {
			audioInputStream = AudioSystem.getAudioInputStream(
			new File(filePath).getAbsoluteFile());
		} catch (UnsupportedAudioFileException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        try {
			clip.open(audioInputStream);
		} catch (LineUnavailableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }
}