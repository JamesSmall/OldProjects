/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Forum;

import Graphics.UserActions.DefaultUserActions.TileOverlay;
import Graphics.UserActions.DefaultUserActions.FPSCalculator;
import Graphics.Forum.Screen;
import Graphics.Base.Graphic.ParticleEngine;
import Graphics.UserActions.DefaultUserActions.ScreenShot;
import Holder.MasterLoad;
import Save.SaveObjectPackage;
import Utils.Config;
import Utils.ErrorLog;
import World.Cell;
import World.Entities.Basic.PlayerPosition;
import World.WorldControl;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.SwingUtilities;

/**
 *
 * @author Allazham
 */
public class Main {
    public static void main(String[] args) throws InterruptedException{
        SwingUtilities.invokeLater(new Runner());
    }
    private static class Runner implements Runnable{
        @Override
        public void run() {
            try {
                ErrorLog.setDisabled(true);
                MasterLoad.load();
                WorldControl.getInstance().start();
                FrameMain f = new FrameMain();
                Screen.getInstance().addUserAction(new ScreenShot());
                f.setTitle("Back wolf Games Model Editor");
                //Thread.sleep(1000);
                f.setSize(500,500);
                f.setVisible(true);
                Config.setupToolBox(f);
                PlayerPosition c = new PlayerPosition();
                c.Manager();
                WorldControl.getInstance().setPrimaryCell(new Cell());
                WorldControl.getInstance().getPrimaryCell().addPlayer(c);
                Thread.sleep(100);
                f.startGL();
                Screen.getInstance().addUserAction(new FPSCalculator(true));
                Screen.getInstance().addGraphicObject(ParticleEngine.getInstance());
                Screen.getInstance().addUserAction(new TileOverlay());
                // prepare for delete
                //delete end her
            } catch (InterruptedException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }
}
