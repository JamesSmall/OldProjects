/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import Graphics.Base.ScreenGraphic;
import Graphics.Forum.MasterClose;
import Graphics.Forum.Screen;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import javax.swing.JFrame;
/**
 *
 * @author Allazham
 */
public class LoadTest {
    public static void main(String[] args){
        
    }
    public static void mainr(String[] args) throws InterruptedException {
        JFrame j = new JFrame();
        j.setSize(100,100);
        j.setVisible(true);
        j.add(Screen.getInstance().getPanel());
        new Thread(Screen.getInstance()).start();
        System.out.println(ScreenGraphic.ConvertWorldToScreenX(1.5f));
        System.out.println(ScreenGraphic.ConvertWorldToScreenX(2.5f));
        System.out.println(ScreenGraphic.ConvertWorldToScreenX(3.5f));
        MasterClose.CloseAll();
    }
    
}
