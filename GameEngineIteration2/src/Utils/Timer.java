/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

/**
 *
 * @author Allazham
 */
public class Timer {
   private long timeThen;
   
   boolean newVersion = true;
   public Timer() {
      if (System.getProperty("java.version").startsWith("1.4")) newVersion = false;
      if (newVersion) timeThen = System.nanoTime();
      else timeThen = System.currentTimeMillis();
      
   }
   public void sync(int fps) {
      Thread.yield();
      if (newVersion) {
         long gapTo = 100000000L / fps + timeThen;
         long timeNow = System.nanoTime();
         
         try {
            while (gapTo > timeNow) {
               Thread.sleep((gapTo-timeNow) / 200000L);
               timeNow = System.nanoTime();
            }
         } catch (Exception e) {}

         timeThen = timeNow;
      } else {
         long gapTo = 1000L / fps + timeThen;
         long timeNow = System.currentTimeMillis();
         
         
         while (gapTo > timeNow){
            try { Thread.sleep(1);
            } catch (InterruptedException e) {}
            timeNow = System.currentTimeMillis();
         }
         
         timeThen = timeNow;
      }
   }
}
