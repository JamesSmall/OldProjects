/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package test;

import Graphics.Base.ScreenUI;
import Graphics.Base.UI.Console;
import Graphics.Forum.Screen;
import Holder.MasterLoad;
import javax.swing.JFrame;

/**
 *
 * @author Allazham
 */
public class ScreenUITest {
    public static void main(String[] args) throws InterruptedException{
        MasterLoad.load();
        Screen scr = Screen.getInstance();
        JFrame test = new JFrame();
        test.setSize(100, 100);
        test.setVisible(true);
        test.add(scr.getPanel());
        new Thread(scr).start();
        Console b = new Console(0,0,1,1,ScreenUI.UP,false);
        scr.addGraphicObject(b);
        scr.addUserAction(b.getUserAction());
        scr.addUserAction(b.getLeftRightMouseWheelListener());
    }
}
