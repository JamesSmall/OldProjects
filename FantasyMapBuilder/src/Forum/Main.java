/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Forum;

import Utils.Config;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.SwingUtilities;

/**
 *
 * @author Allazham
 */
public class Main {
    public static void main(String[] args){
        SwingUtilities.invokeLater(new Runner());
    }
    private static class Runner implements Runnable{
        @Override
        public void run() {
            try {
                
                FrameMain f = new FrameMain();
                //Thread.sleep(1000);
                Config.Intilize();
                Config.setUp();
                Config.checkFileDirectory();
                f.setSize(500,500);
                f.setVisible(true);
                f.setDrawingPanel(new DrawingPanel());
                Config.setupToolBox(f);
                // prepare for delete\
                //delete end her
            } catch (IOException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }
}
