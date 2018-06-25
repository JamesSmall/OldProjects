/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import Graphics.Base.UI.Panel;
import Graphics.Forum.MasterClose;
import Graphics.Forum.Screen;
import Graphics.Base.UI.ScrollBar;
import Holder.MasterLoad;
import Holder.TextureTypes.SingleColorTexture;
import Utils.Config;
import Utils.ErrorLog;
import java.awt.Color;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;
/**
 *
 * @author Allazham
 */
public class TestMain {
    public static void main(String[] args){
        System.out.println(Config.JavaVersion);
    }
    public static void maifn(String[] args) throws InterruptedException{
        final JFrame j = new JFrame();
        j.setSize(100, 100);
        Screen scr = Screen.getInstance();
        j.add(scr.getPanel());
        j.setVisible(true);
        MasterLoad.load();
        j.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                MasterClose.CloseAll();
                while(Screen.isScreenCreated()){
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException ex) {
                        ErrorLog.error(ex);
                    }
                }
                j.dispose();
            }
            
        });
        new Thread(scr).start();
        Panel p = new Panel(.25f,.25f,.5f,.5f,ScrollBar.DOWNLEFT,SingleColorTexture.getColoredTexture(Color.red),false);
        ScrollBar b = new ScrollBar(0,0,1f,.2f,ScrollBar.CENTER,SingleColorTexture.getColoredTexture(Color.BLUE),false,true);
        Thread.sleep(1000);
        
        scr.addUserAction(b.getScrollAction());
        p.addRenderObject(b);
        scr.addGraphicObject(p);
        b.setup();
    }
}
