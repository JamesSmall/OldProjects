/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Manager;

import Graphics.ScreenGraphic;
import Holder.Image;
import org.lwjgl.opengl.GL11;
import org.newdawn.slick.Color;
import org.newdawn.slick.opengl.TextureImpl;

/**
 *
 * @author Allazham
 */
public abstract class WorldGPS extends ScreenGraphic{
    //private methods
    private static float ScreenX = 0, ScreenY = 0,ScreenScale = 1;
    private float x, y, width, height, scale;
    private Image img;
    
    public WorldGPS(float x, float y, float width,float height,float scale,Image img,boolean disabled){
        super(disabled);
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.scale = scale;
        this.img = img;
    }
    public WorldGPS(float x, float y,float WidthAndHeight,Image img, boolean disabled){
        super(disabled);
        this.x = x;
        this.y = y;
        this.width = WidthAndHeight;
        this.height = WidthAndHeight;
        this.scale = 1;
        this.img = img;
    }
    /*
     * Inhereted Objects
     * 
     */
    @Override
    protected void render() {
        
        if(this.img == null){
            this.renderWithoutImage();
        }
        else{
            this.renderWithImage();
        }
    }
    /*
     * 
     * private methods to reduce unreadability
     */
    //method is fun
    private void renderWithImage(){
        float minorX = img.getMinorX(), minorY = img.getMinorY(),greaterX = img.getXGreaterX(),greaterY = img.getXGreaterY();
        this.img.bind();
        GL11.glBegin(GL11.GL_QUADS);
        GL11.glTexCoord2f(minorX, minorY);
        GL11.glVertex2f(((this.x - (this.width*this.scale) - WorldGPS.ScreenX)*WorldGPS.ScreenScale), ((this.y - (this.height*this.scale) - WorldGPS.ScreenY)*WorldGPS.ScreenScale));
        GL11.glTexCoord2f(greaterX, minorY);
        GL11.glVertex2f(((this.x + (this.width*this.scale) - WorldGPS.ScreenX)*WorldGPS.ScreenScale), ((this.y - (this.height*this.scale) - WorldGPS.ScreenY)*WorldGPS.ScreenScale));
        GL11.glTexCoord2f(greaterX, greaterY);
        GL11.glVertex2f(((this.x + (this.width*this.scale) - WorldGPS.ScreenX)*WorldGPS.ScreenScale), ((this.y + (this.height*this.scale) - WorldGPS.ScreenY)*WorldGPS.ScreenScale));
        GL11.glTexCoord2f(minorX, greaterY);
        GL11.glVertex2f(((this.x - (this.width*this.scale) - WorldGPS.ScreenX)*WorldGPS.ScreenScale), ((this.y + (this.height*this.scale) - WorldGPS.ScreenY)*WorldGPS.ScreenScale));
        GL11.glEnd();
    }
    //in reality only for debugging purposes
    private void renderWithoutImage(){
        GL11.glBegin(GL11.GL_QUADS);
        //Color.black.bind();
        GL11.glVertex2f(((this.x - (this.width*this.scale) - WorldGPS.ScreenX)*WorldGPS.ScreenScale), ((this.y - (this.height*this.scale) - WorldGPS.ScreenY)*WorldGPS.ScreenScale));
        GL11.glVertex2f(((this.x + (this.width*this.scale) - WorldGPS.ScreenX)*WorldGPS.ScreenScale), ((this.y - (this.height*this.scale) - WorldGPS.ScreenY)*WorldGPS.ScreenScale));
        GL11.glVertex2f(((this.x + (this.width*this.scale) - WorldGPS.ScreenX)*WorldGPS.ScreenScale), ((this.y + (this.height*this.scale) - WorldGPS.ScreenY)*WorldGPS.ScreenScale));
        GL11.glVertex2f(((this.x - (this.width*this.scale) - WorldGPS.ScreenX)*WorldGPS.ScreenScale), ((this.y + (this.height*this.scale) - WorldGPS.ScreenY)*WorldGPS.ScreenScale));
        GL11.glEnd();
        //Color.white.bind();
    }
    /*
     * static methods, World Position Management(the main purpose of this class)
     * 
     */
    public static void setWorldX(float x){
        WorldGPS.ScreenX = x;
    }
    public static void setWorldY(float y){
        WorldGPS.ScreenY = y;
    }
    public static void setWorldScale(float scale){
        WorldGPS.ScreenScale = scale;
    }
    public static void addWorldX(float x){
        WorldGPS.ScreenX += x;
    }
    public static void addWorldY(float y){
        WorldGPS.ScreenY += y;
    }
    public static void addWorldScale(float scale){
        WorldGPS.ScreenScale += scale;
    }
    public static float getWorldX(){
        return WorldGPS.ScreenX;
    }
    public static float getWorldY(){
        return WorldGPS.ScreenY;
    }
    public static float getWorldScale(){
        return WorldGPS.ScreenScale;
    }
    /*
     * Screen Calculations, for the purposes of targeting and catching
     * 
     */
    public float getPosOnScreenY() {
        return ((this.y - WorldGPS.ScreenY)*WorldGPS.ScreenScale);
    }
    public float getPosOnScreenX() {
        return ((this.x - WorldGPS.ScreenX)*WorldGPS.ScreenScale);
    }
    public float getPointByPercentX(float percent){
        return((this.x + (this.width*this.scale*percent) - WorldGPS.ScreenX)*WorldGPS.ScreenScale);
    }
    public float getPointByPercentY(float percent){
        return((this.y + (this.width*this.scale*percent) - WorldGPS.ScreenY)*WorldGPS.ScreenScale);
    }
    public boolean isInScreenPersonalBox(float x, float y){
        if((this.x - (this.width*this.scale) - WorldGPS.ScreenX)*WorldGPS.ScreenScale < x && (this.x + (this.width*this.scale) - WorldGPS.ScreenX)*WorldGPS.ScreenScale > x && (((this.y - (this.height*this.scale) - WorldGPS.ScreenY)*WorldGPS.ScreenScale))*WorldGPS.ScreenScale < y && (((this.y + (this.height*this.scale) - WorldGPS.ScreenY)*WorldGPS.ScreenScale))*WorldGPS.ScreenScale > y){
            return true;
        }
        return false;
    }
    public boolean isInScreenHitBox(float x, float y){
        if((this.x - (this.width*this.scale*2) - WorldGPS.ScreenX)*WorldGPS.ScreenScale < x && (this.x + (this.width*this.scale*2) - WorldGPS.ScreenX)*WorldGPS.ScreenScale > x && (((this.y - (this.height*this.scale*2) - WorldGPS.ScreenY)*WorldGPS.ScreenScale))*WorldGPS.ScreenScale < y && (((this.y + (this.height*this.scale*2) - WorldGPS.ScreenY)*WorldGPS.ScreenScale))*WorldGPS.ScreenScale > y){
            return true;
        }
        return false;
    }
    /*
     * personal entity Position
     * 
     */
    public void setX(float x){
        this.x = x;
    }
    public void setY(float y){
        this.y = y;
    }
    public void addX(float x){
        this.x += x;
    }
    public void addY(float y){
        this.y +=y;
    }
    public float getX(){
        return this.x;
    }
    public float getY(){
        return this.y;
    }
    /*
     * Objects own scale
     * 
     */
    public void setScale(float scale){
        this.scale = scale;
    }
    public void addScale(float scale){
        this.scale += scale;
    }
    public void multipleScale(float scale){
        this.scale *= scale;
    }
    public float getScale(){
        return this.scale;
    }
    /*
     * minor image management
     * 
     */
    public void setImage(Image img){
        this.img = img;
    }
    public void setNewImage(Image img){
        this.img = img.getNewInstance();
    }
    public void resetImageAsNewImage(){
        this.img = this.img.getNewInstance();
    }
    public Image getImage(){
        return this.img;
    }
    //height && width manager
    public void setHeight(float height){
        this.height = height;
    }
    public void setWidth(float width){
        this.width = width;
    }
    public void multiplyHeight(float height){
        this.height *= height;
    }
    public void multiplyWidth(float width){
        this.width *= width;
    }
    public void addWidth(float width){
        this.width += width;
    }
    public void addHeight(float height){
        this.height += height;
    }
    public float getWidth(){
        return this.width;
    }
    public float getHeight(){
        return this.height;
    }
}