/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Graphics.Base.UI;

import static Graphics.Base.ScreenUI.LEFT;
import Graphics.Forum.ActionKeys;
import Graphics.Forum.Screen;
import Graphics.UserActions.UIZone;
import Graphics.UserActions.UserAction;
import Graphics.UserActions.UserInterfaceAction;
import Holder.ErrorCall;
import Holder.JavaScript.JavaScriptManager;
import Holder.JavaScript.JavaScriptFactory;
import Holder.Texture;
import java.util.Arrays;
import org.lwjgl.input.Keyboard;

/**
 *
 * @author Allazham
 */
public final class Console extends TextArea implements ErrorCall{
    private static JavaScriptFactory r = new JavaScriptFactory();
    private final JavaScriptManager e;
    private UserAction ua;
    public Console(float x, float y, float Width, float Height,boolean disabled){
        super(x,y,Width,Height,LEFT,disabled);
        e = r.getBasicEngine();
        e.runLineDirectly("function test(){World.getPrimaryCell().addEntry(new Packages.World.Entities.Basic.Follower())}");
        e.setErrorLog(this);
    }
    public Console(float x, float y, float width, float height, int orintation,boolean disabled){
        super(x,y,width,height,orintation,disabled);
        e = r.getBasicEngine();
        e.runLineDirectly("function test(){World.getPrimaryCell().addEntry(new Packages.World.Entities.Basic.Follower())}");
        e.setErrorLog(this);
    }
    public Console(float x, float y, float width, float height, int orintation,Texture t,boolean disabled){
        super(x,y,width,height,orintation,t,disabled);
        e = r.getBasicEngine();
        e.runLineDirectly("function test(){World.getPrimaryCell().addEntry(new Packages.World.Entities.Basic.Follower())}");
        e.setErrorLog(this);
    }
    @Override
    public UserAction getUserAction(){
        if(ua == null){
            ua = new KeyPressListener(super.getUIZone());
        }
        return ua;
    }
    @Override
    public void disposeGraphics(){
        if(ua != null){
            Screen.getInstance().removeUserAction(ua);
        }
        super.disposeGraphics();
    }
    @Override
    public void Error(Throwable e) {
        super.addToLastLine(e.getMessage());
        super.addNewLine();
    }
    private class KeyPressListener extends UserInterfaceAction{
        public KeyPressListener(UIZone z){
            super(z);
        }
        @Override
        public void Action(ActionKeys k) {
            char[] c = k.getCharKeys();
            if(k.ContainsKeyboardKey(Keyboard.KEY_BACK)){
                if(!getLastLineText().isEmpty()){
                    setText(getLastLineText().substring(0, getLastLineText().length()-1),data.length-1);
                    
                }
                else{
                    if(data.length != 0){
                        setText(Arrays.copyOf(data, data.length-1));
                    }
                }
            }
            else if(k.ContainsKeyboardKey(Keyboard.KEY_RETURN)){
                e.runLine(getLastLineText());
                addNewLine();
            }
            else{
                if(c.length == 0){
                    return;
                }
                if(Keyboard.isKeyDown(Keyboard.KEY_LSHIFT)){
                    setText(getLastLineText()+new StringBuilder().append(c, 0, c.length).toString().toUpperCase(),data.length-1);
                }
                else{
                    setText(getLastLineText()+new StringBuilder().append(c, 0, c.length).toString().toLowerCase(),data.length-1);
                }
            }
        }
    }
}
