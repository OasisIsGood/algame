package zelda.base;

import java.io.File;
import java.io.FileNotFoundException;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;

/*
 * From http://www.javafr.com/code.aspx?ID=27367
 */
public class Sound implements Runnable {
  
   private AudioInputStream audioPlayer;
   private AudioFormat format;
   private Clip clip;
   private Thread thread;
   private int tour;
   private boolean paused, closedToEnd;
 
   public Sound(File file) throws FileNotFoundException{
      if(!file.exists()){
         throw new FileNotFoundException();
      }
      this.initialize(file);
   }

   private void initialize(File fichier){
      try{
         this.audioPlayer = AudioSystem.getAudioInputStream(fichier);
         this.format = audioPlayer.getFormat();
         DataLine.Info info = new DataLine.Info(Clip.class, this.audioPlayer.getFormat(),
               ((int)this.audioPlayer.getFrameLength() * this.format.getFrameSize()));
         this.clip = (Clip)AudioSystem.getLine(info);
         reopen();
      }
      catch(Exception e)
      {
    	  System.err.println("Exception when initialize sound!");
      }
   }
   
   public void play(){
      if(this.thread == null){
         this.thread = new Thread(this);
         this.thread.start();
      }
      this.tour = 1;
   }
   
   public void boucle(int nbTimes){
      if(this.thread == null){
         this.thread = new Thread(this);
         this.thread.start();
      }
      this.tour = nbTimes;
   }
   
   public void run(){
      while(this.thread != null){
         try{Thread.sleep(123);}
         catch(Exception e){}
         
         if(this.tour > 0){
            this.clip.start();
            try{Thread.sleep(99);}
            catch(Exception e){}
            while((this.clip.isActive() || this.paused) && (this.thread != null)){
               try{Thread.sleep(99);}
               catch(Exception e){break;}
            }
            this.clip.stop();
            this.placeMicroseconde(0);
            this.tour--;
            if(this.tour < 1){
               if(this.closedToEnd){
                  this.close();
               }
            }
         }
      }
   }
   
   private void reopen() throws Exception{
      this.clip.open(this.audioPlayer);
   }
   
   public void pause(){
      if(!this.paused){
         this.clip.stop();
         this.paused = true;
      }
   }
  
   public void restart(){
      if(this.paused){
         paused = false;
         this.clip.start();
      }
   }
  
   public void stop(){
      this.clip.stop();
      this.placeMicroseconde(0);
      this.paused = false;
      this.tour = 0;
      this.thread = null;
   }
   
   public void close(){
      this.stop();
      this.clip.close();
      this.clip = null;
      this.format = null;
   }
  
   public boolean isClosedAtTheEnd() {
      return closedToEnd;
   }
  
   public void setCloseToTheEnd(boolean close) {
      this.closedToEnd = close;
   }
  
   public long lenghtSoundMicroseconde(){
      return this.clip.getMicrosecondLength();
   }
  
   public long getRenduMicroseconde(){
      return this.clip.getMicrosecondPosition();
   }

   public void placeMicroseconde(long microseconde){
      this.clip.setMicrosecondPosition(microseconde);
   }

   public void placeStart(){
      this.clip.setMicrosecondPosition(0);
   }
   
   public boolean isPaused(){
      return this.paused;
   }
   
   public boolean isPlaying(){
      return !this.paused && (this.tour > 0);
   }
}