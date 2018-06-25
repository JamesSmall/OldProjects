/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Graphics.Base.UI;

import Graphics.Base.ScreenUI;
import Graphics.Forum.ActionKeys;
import Graphics.UserActions.UIZone;
import Graphics.UserActions.UserAction;
import Graphics.UserActions.UserInterfaceAction;
import Holder.MasterLoad;
import Holder.TextureTypes.GlyphTexture;
import Holder.Texture;
import Holder.TextureTypes.TextPacket;
import java.awt.Color;
import org.lwjgl.LWJGLException;
import java.util.ArrayList;
import org.lwjgl.input.Keyboard;

/**
 *
 * @author Allazham
 */
public class TextBox extends ScreenUI{
    private final GlyphTexture glyph = MasterLoad.GlyphTexture();
    private UserInterfaceAction UIA;
    private UserAction KListener;
    private static float TextScale = 1;
    private boolean targeted = false;
    private String text = "";
    private double roll = 0;
    public TextBox(float x, float y, float Width, float Height,boolean disabled){
        super(x,y,Width,Height,LEFT,disabled);
    }
    public TextBox(float x, float y, float width, float height, int orintation,boolean disabled){
        super(x,y,width,height,orintation,disabled);
    }
    public TextBox(float x, float y, float width, float height, int orintation,Texture t,boolean disabled){
        super(x,y,width,height,orintation,t,disabled);
    }
    public void setRoll(double r){
        this.roll = r;
    }
    public double getRoll(){
        return this.roll;
    }
    public void setText(String text){
        this.setText(text,Color.BLACK);
    }
    public void setText(String text, Color c){
       this.text = text;
    }
    public String getText(){
        return this.text;
    }
    @Override
    public void disposeGraphics(){
        
    }
    @Override
    public void render() throws LWJGLException {
        float width = super.getActualWidth(),height = super.getActualHeight();
        float x = super.getActualX(),y = super.getActualY();
        this.glyph.bind();
        int ort = super.getActaulOrientation();
        if(ort == ScreenUI.CENTER || ort == ScreenUI.DOWN || ort == ScreenUI.UP){
            this.renderloopCenter(0, y, width, height*TextBox.TextScale);
        }
        else if(ort == ScreenUI.RIGHT || ort == ScreenUI.DOWNRIGHT || ort == ScreenUI.UPRIGHT){
            this.renderloopRight(0, y, width, height*TextBox.TextScale);
        }
        else{
            this.renderloopLeft(-.1f, y, width, height*TextBox.TextScale);
        }
    }
    private void renderloopLeft(float xStart,float y, float width,float height) throws LWJGLException{
        float xx = xStart;
            for(char c:this.text.toCharArray()){
                this.glyph.setChar(c);
                //xx -= height*this.glyph.getDXGapPercentage();
                xx -= height*this.glyph.getDXGapPercentage();
                System.out.println(xx);
                if(xx+height*this.glyph.getDXGapPercentage() < 0 && xx > 0){
                    
                    float percent = -(xx+height*(float)this.glyph.getDXGapPercentage())/height;
                    ScreenUI.DrawBasicTexturedSquare(xx+super.getActualX()+(height*(float)this.glyph.getDXGapPercentage()*percent), y, height*percent, height,this.glyph.getLesserX()+(this.glyph.getGreaterX()-this.glyph.getLesserX())*percent,this.glyph.getLesserY(),this.glyph.getGreaterX(),this.glyph.getGreaterY(), super.getActaulOrientation());
                }
                else if(xx + height*this.glyph.getDXGapPercentage() < 0 &&xx+height*(1-this.glyph.getDXGapPercentage()-this.glyph.getDWidthPercentage())<width){
                    ScreenUI.DrawBasicTexturedSquare(xx+super.getActualX(), y, height, height,this.glyph.getLesserX(),this.glyph.getLesserY(),this.glyph.getGreaterX(),this.glyph.getGreaterY(), super.getActaulOrientation());
                }
                else if(width > xx){
                    float percent = (width-xx)/height;
                    ScreenUI.DrawBasicTexturedSquare(xx+super.getActualX()-(height*(float)this.glyph.getDXGapPercentage()*percent), y, height*percent, height,this.glyph.getLesserX(),this.glyph.getLesserY(),this.glyph.getLesserX()+(this.glyph.getGreaterX()-this.glyph.getLesserX())*percent,this.glyph.getGreaterY(), super.getActaulOrientation());
                }
                xx += height*1.1 - height*this.glyph.getDWidthPercentage();
            }
    }
    private void renderloopCenter(float xStart,float y, float width,float height) throws LWJGLException{
        float xx = xStart+super.getActualX();
            for(char c:this.text.toCharArray()){
                this.glyph.setChar(c);
                //xx -= height*this.glyph.getDXGapPercentage();
                xx -= height*this.glyph.getDXGapPercentage();
                if(xx+height*(1-this.glyph.getDXGapPercentage()-this.glyph.getDWidthPercentage())<width){
                    ScreenUI.DrawBasicTexturedSquare(xx+super.getActualX()-width/2+height/2, y, height, height,this.glyph.getLesserX(),this.glyph.getLesserY(),this.glyph.getGreaterX(),this.glyph.getGreaterY(), super.getActaulOrientation());
                }
                else if(width > xx){
                    float percent = (width-xx)/height;
                    ScreenUI.DrawBasicTexturedSquare((xx+super.getActualX()-(height*(float)this.glyph.getDXGapPercentage()*percent)-width/2+height/2*percent), y, height*percent, height,this.glyph.getLesserX(),this.glyph.getLesserY(),this.glyph.getLesserX()+(this.glyph.getGreaterX()-this.glyph.getLesserX())*percent,this.glyph.getGreaterY(), super.getActaulOrientation());
                }
                xx += height*1.1 - height*this.glyph.getDWidthPercentage();
            }
    }
    private void renderloopRight(float xStart,float y, float width,float height) throws LWJGLException{
        float xx = xStart;
            for(char c:this.text.toCharArray()){
                this.glyph.setChar(c);
                //xx -= height*this.glyph.getDXGapPercentage();
                xx -= height*this.glyph.getDXGapPercentage();
                if(xx+height*(1-this.glyph.getDXGapPercentage()-this.glyph.getDWidthPercentage())<width){
                    ScreenUI.DrawBasicTexturedSquare(-xx-super.getActualX()+width-height, y, height, height,this.glyph.getLesserX(),this.glyph.getLesserY(),this.glyph.getGreaterX(),this.glyph.getGreaterY(), super.getActaulOrientation());
                }
                else if(width > -xx){
                    float percent = (width-xx)/height;
                    ScreenUI.DrawBasicTexturedSquare(width-height*percent-(xx+super.getActualX()-(height*(float)this.glyph.getDXGapPercentage()*percent)), y, height*percent, height,this.glyph.getLesserX(),this.glyph.getLesserY(),this.glyph.getLesserX()+(this.glyph.getGreaterX()-this.glyph.getLesserX())*percent,this.glyph.getGreaterY(), super.getActaulOrientation());
                }
                xx += height*1.1 - height*this.glyph.getDWidthPercentage();
            }
    }
    private void renderloopRightDE(float xStart,float y, float width,float height) throws LWJGLException{
        float xx = xStart;
            for(char c:this.text.toCharArray()){
                this.glyph.setChar(c);
                //xx -= height*this.glyph.getDXGapPercentage();
                xx -= height*this.glyph.getDXGapPercentage();
                if(xx+height*(1-this.glyph.getDXGapPercentage()-this.glyph.getDWidthPercentage())<width){
                    ScreenUI.DrawBasicTexturedSquare(-xx+width-height, y, height, height,this.glyph.getLesserX(),this.glyph.getLesserY(),this.glyph.getGreaterX(),this.glyph.getGreaterY(), super.getActaulOrientation());
                }
                else if(xx > width){
                    
                }
                xx += height*1.1 - height*this.glyph.getDWidthPercentage();
            }
    }
    public void setTargeted(boolean target){
        this.targeted = target;
    }
    public boolean isTargeted(){
        return this.targeted;
    }
    public UserAction getKeyListener(){
        if(this.KListener == null){
            this.KListener = new KeyPressListener();
        }
        return this.KListener;
    }
    public UserInterfaceAction getMouseWheelAction(){
        if(this.UIA == null){
            this.UIA = new MouseWheelListener(this.getUIZone());
        }
        return this.UIA;
    }
    private class KeyPressListener implements UserAction{
        @Override
        public void Action(ActionKeys k) {
            char[] c = k.getCharKeys();
            if(k.ContainsKeyboardKey(Keyboard.KEY_BACK)){
                if(getText().length() != 0){
                    setText(getText().substring(0, getText().length()-1));
                }
            }
            else{
                if(c.length == 0){
                    return;
                }
                if(Keyboard.isKeyDown(Keyboard.KEY_LSHIFT)){
                    setText(getText()+new StringBuilder().append(c, 0, c.length).toString().toUpperCase());
                }
                else{
                    setText(getText()+new StringBuilder().append(c, 0, c.length).toString().toLowerCase());
                }
            }
        }

        @Override
        public boolean isDisabled(){
            return targeted;
        }
        @Override
        public void create() {
            
        }
        @Override
        public void dispose() {
            
        }
    }
    private class MouseWheelListener extends UserInterfaceAction{
        public MouseWheelListener(UIZone uiz){
            super(uiz);
        }
        @Override
        public void Action(ActionKeys k) {
            
        }
    }
}
