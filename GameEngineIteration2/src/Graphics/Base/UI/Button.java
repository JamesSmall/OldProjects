/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Graphics.Base.UI;

import Graphics.Base.ScreenUI;
import static Graphics.Base.ScreenUI.LEFT;
import Graphics.Forum.ActionKeys;
import Graphics.Forum.Screen;
import Graphics.UserActions.UIZone;
import Graphics.UserActions.UserInterfaceAction;
import Graphics.Utils.Camera2D;
import Holder.Texture;
import org.lwjgl.input.Mouse;

/**
 *
 * @author Allazham
 */
public class Button extends ScreenUI{
    private UserInterfaceAction act;
    private Runnable run;
    private int ButtonPress = 0;
    private static Camera2D cam = Camera2D.getCamera();
    private final UIZone z;
    public Button(float x, float y, float Width, float Height,boolean disabled){
        super(x,y,Width,Height,LEFT,disabled);
        z = super.getUIZone();
    }
    public Button(float x, float y, float width, float height, int orintation,boolean disabled){
        super(x,y,width,height,orintation,disabled);
        z = super.getUIZone();
    }
    public Button(float x, float y, float width, float height, int orintation,Texture t,boolean disabled){
        super(x,y,width,height,orintation,t,disabled);
        z = super.getUIZone();
    }
    public UserInterfaceAction getAction(){
        if(this.act != null){
            this.act = new ButtonPress(super.getUIZone());
        }
        return act;
    }
    public void setRunnable(Runnable r){
        this.run = r;
    }
    public void setMouseButtonPress(int press){
        this.ButtonPress = press;
    }
    @Override
    public void disposeGraphics(){
        if(act != null){
            Screen.getInstance().removeUserAction(act);
        }
        super.disposeGraphics();
    }
    private class ButtonPress extends UserInterfaceAction{
        public ButtonPress(UIZone uiz){
            super(uiz);
        }
        @Override
        public void Action(ActionKeys k) {
            Update(z);
            if(z.isInside(cam.getMousePointOnGraphics())){
                if(Mouse.isButtonDown(ButtonPress)){
                    if(run != null){
                        run.run();
                    }
                }
            }
        }
    }
}
