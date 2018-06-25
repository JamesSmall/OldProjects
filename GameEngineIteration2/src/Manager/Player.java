/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Manager;

import Graphics.MultipleKeyUserActionKeyboard;
import Graphics.MultipleKeyUserActionMouse;
import Graphics.Screen;
import Graphics.SingletonUserActionMouse;
import Holder.Image;
import java.util.ArrayList;
import java.util.List;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

/**
 *
 * @author Allazham
 */
public class Player extends WorldGPS implements CreatureManager{
    private World w;
    private boolean updated = true;
    private StatManager stat;
    public Player(float x,float y, float WidthAndHeight,Image img, boolean disabled,StatManager stat){
       super(x,y,WidthAndHeight,img,disabled); 
       this.stat = stat;
    }
    @Override
    public List<String> getSaveData(){
        List<String> save = new ArrayList();
        
        return save;
    }
    @Override
    public void Update() {
        //no action currently
        if(updated){
            w = World.getCurrentInstance();
            updated = false;
            ScreenMouse s = new ScreenMouse();
            s.setIgnoreInput(true);
            //Screen.getInstance().addMouseAction(s);
            Screen.getInstance().addKeyBoardAction(new GeneralControl());
        }
    }

    @Override
    public void Update(String args) {
        
    }

    @Override
    public StatManager getStatManager() {
        return this.stat;
    }

    @Override
    public Interaction getInteractionManager() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    private class ScreenMouse extends MultipleKeyUserActionMouse{
        @Override
        protected void Reaction(int key) {
            System.out.println(World.getCurrentInstance().getChunkOnScreen(Mouse.getX(),Mouse.getY()));
        }
    }
    private class GUIMouse extends SingletonUserActionMouse{
        public GUIMouse(){
            super.setKey(0);
        }
        @Override
        protected void Reaction() {
            
        }
    }
    private class GeneralControl extends MultipleKeyUserActionKeyboard{
        public GeneralControl(){
            super.setKeys(new int[]{Keyboard.KEY_W,Keyboard.KEY_S,Keyboard.KEY_A,Keyboard.KEY_D});
        }
        @Override
        protected void Reaction(int key) {
            switch(key){
                case Keyboard.KEY_W:
                    WorldGPS.addWorldY(10);
                    addY(10);
                    break;
                case Keyboard.KEY_S:
                    WorldGPS.addWorldY(-10);
                    addY(-10);
                    break;
               case Keyboard.KEY_A:
                    WorldGPS.addWorldX(-10);
                    addX(-10);
                    break;
                case Keyboard.KEY_D:
                    WorldGPS.addWorldX(10);
                    addX(10);
                    break;
            }
        }
    }
    private class GUIControls extends MultipleKeyUserActionKeyboard{
        @Override
        protected void Reaction(int key) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
    }
}
