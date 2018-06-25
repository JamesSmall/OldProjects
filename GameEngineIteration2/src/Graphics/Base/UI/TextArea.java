/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Graphics.Base.UI;

import Graphics.Base.ScreenUI;
import Graphics.Forum.ActionKeys;
import Graphics.Forum.Screen;
import Graphics.UserActions.UIZone;
import Graphics.UserActions.UserAction;
import Graphics.UserActions.UserInterfaceAction;
import Holder.Texture;
import Holder.TextureTypes.SingleColorTexture;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Arrays;
import org.lwjgl.input.Keyboard;

/**
 *
 * @author Allazham
 */
public class TextArea extends Panel{
    private UserAction Key,Wheel;
    private float textSize = .1f;
    private final ArrayList<Table> boxes = new ArrayList();
    String[] data = new String[0];
    public TextArea(boolean disabled){
        super(disabled);
    }
    public TextArea(float x, float y, float width, float height, int orintation,boolean disabled){
        super(x,y,width,height,orintation,disabled);
    }
    public TextArea(float x, float y, float width, float height, int orintation,Texture t,boolean disabled){
        super(x,y,width,height,orintation,t,disabled);
    }
    public void setTextHeight(float size){
        this.textSize = size;
        this.updateWorks();
    }
    public float getTextHeight(){
        return this.textSize;
    }
    public void setText(String[] text){
        this.data = text;
        this.updateWorks();
    }
    public void addNewLine(){
        this.addNewLine("");
        this.updateWorks();
    }
    public void setText(String text,int i){
        if(data.length == 0){
            this.addNewLine(text);
        }
        else{
            data[i] = text;
            this.updateWorks();
        }
    }
    public String getLastLineText(){
        if(data.length == 0){
            return "";
        }
        return data[data.length-1];
    }
    public void addNewLine(String line){
        String[] temp = Arrays.copyOf(data, data.length+1);
        temp[data.length] = line;
        data = temp;
        this.updateWorks();
    }
    public void addToLastLine(String text){
        if(data.length != 0){
            data[data.length-1] += text;
            this.updateWorks();
        }
    }
    public String getTextLine(int i){
        return data[i];
    }
    public String[] getAllText(){
        return data;
    }
    private void SetProperSizeTextBox(){
        if(data.length > boxes.size()){
            while(data.length > boxes.size()){
                boxes.add(new Table(new TextBox(0,0,1,this.textSize,ScreenUI.UPLEFT,SingleColorTexture.getColoredTexture(new Color(0,0,0,0)),false)));
            }
        }
        else if(data.length < boxes.size()){
            while(data.length < boxes.size()){
                Table b = boxes.remove(0);
                b.b.disposeGraphics();
                super.removeRenderObject(b.b);
            }
        }
    }
    private void updateWorks(){
        int i;
        float dist = this.textSize;
        this.SetProperSizeTextBox();
        for(i = data.length-1; i > -1;i--){
            dist += this.textSize;
            if(dist <= 1){
                if(!boxes.get(i).active){
                    super.addRenderObject(boxes.get(i).b);
                    boxes.get(i).active = true;
                }
            }
            else{
                if(boxes.get(i).active){
                    super.removeRenderObject(boxes.get(i).b);
                    boxes.get(i).active = false;
                }
            }
            boxes.get(i).b.setY(1-(dist-this.textSize));
            boxes.get(i).b.setText(data[i]);
        }
        super.update();
    }
    private class Table{
        Table(TextBox t){
            b = t;
        }
        TextBox b;
        boolean active = false;
    }
    public UserAction getUserAction(){
        if(this.Key == null){
            this.Key =  new KeyPressListener(super.getUIZone());
        }
        return this.Key;
    }
    public UserAction getLeftRightMouseWheelListener(){
        if(Wheel == null){
            Wheel = new UserActionPackage(super.getUIZone());
        }
        return Wheel;
    }
    @Override
    public void disposeGraphics(){
        this.setText(new String[0]);
        if(Key != null){
            Screen.getInstance().removeUserAction(Key);
        }
        if(Wheel != null){
            Screen.getInstance().removeUserAction(this.Wheel);
        }
        this.updateWorks();
    }
    private class UserActionPackage extends UserInterfaceAction{
        public UserActionPackage(UIZone uiz){
            super(uiz);
        }
        @Override
        public void Action(ActionKeys k) {
            int i;
            for(i = 0; i < boxes.size();i++){
                UserAction a = boxes.get(i).b.getMouseWheelAction();
                if(!a.isDisabled()){
                    a.Action(k);
                }
            }
        }
        
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
                    setText(Arrays.copyOf(data, data.length-2));
                }
            }
            else if(k.ContainsKeyboardKey(Keyboard.KEY_RETURN)){
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
