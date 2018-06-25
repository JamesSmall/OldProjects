/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Menu;

import Graphics.Screen;
import Manager.Player;
import Manager.World;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
/*
 *
 * @author Allazham
 */
public class Main {
    private static int SLEEPINGLOADTIME = 1000;
    public static void main(String[] args) throws InterruptedException, FileNotFoundException, IOException{
       Screen scr = Screen.getInstance();
       new Thread(scr).start();
       scr.setTitle("Barbarians");
       while(!Screen.isScreenCreated()){
           Thread.sleep(100);
       }
       WorldMenu.getInstance();
       MainMenu mnu = MainMenu.getInstance();
       Thread.sleep(Main.SLEEPINGLOADTIME);
       //mnu.AddToScreen();
       new World("test",new Player(0,0,5,null,false,null)).StartThreads();
    }
}