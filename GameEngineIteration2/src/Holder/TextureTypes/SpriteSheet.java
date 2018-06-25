/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Holder.TextureTypes;

import Holder.Texture;
import java.awt.image.BufferedImage;

/**
 *
 * @author Allazham
 */
public class SpriteSheet extends Texture{
    private int x = 0, y = 0;
    private final int Width,Height;
    private final int invWidth,invHeight;
    public SpriteSheet(int[] data, int width,int height,int AWidth,int AHeight){
        super(data,width,height);
        Width = AWidth;
        Height = AHeight;
        invWidth = width/AWidth;
        invHeight = height/AHeight;
    }
    public SpriteSheet(String name,BufferedImage img,int AWidth,int AHeight){
       super(img);
       super.setName(name);
       Width = AWidth;
       Height = AHeight;
       invWidth = super.getWidth()/AWidth;
       invHeight = super.getHeight()/AHeight;
    }
    public SpriteSheet(String name,int[] data, int width,int height,int AWidth,int AHeight){
        super(data,width,height);
        super.setName(name);
        Width = AWidth;
        Height = AHeight;
        invWidth = width/AWidth;
        invHeight = height/AHeight;
    }
    public SpriteSheet(BufferedImage img,int AWidth,int AHeight){
       super(img);
       Width = AWidth;
       Height = AHeight;
       invWidth = super.getWidth()/AWidth;
       invHeight = super.getHeight()/AHeight;
    
    }
    public SpriteSheet(int TextureID,int[] Pixels,int Width,int Height,String name,int AWidth,int AHeight){
        super(TextureID,Pixels,Width,Height,name);
        this.Width = AWidth;
        this.Height = AHeight;
        super.CopyInstance = true;
        invWidth = Width/AWidth;
        invHeight = Height/AHeight;
    }
    
    public int getSubImageWidth(){
        return this.invWidth;
    }
    public int getSubImageHeight(){
        return this.invHeight;
    }
    @Override
    public double getGreaterX() {
        return ((double)this.x+1)/((double)this.Width);
    }

    @Override
    public double getGreaterY() {
        return ((double)this.y+1)/((double)this.Height);
    }

    @Override
    public double getLesserX() {
        return ((double)this.x)/((double)this.Width);
    }

    @Override
    public double getLesserY() {
        return ((double)this.y)/((double)this.Height);
    }
    public void setX(int x){
        if(0 > x && x >= Width){
            throw new IndexOutOfBoundsException();
        }
        this.x = x;
    }
    public void setY(int y){
        if(0 > y && y >= Height){
            throw new IndexOutOfBoundsException();
        }
        this.y = y;
    }
    public int getSpritenWidthClicks(){
        return this.Width;
    }
    public int getSpriteHeightClicks(){
        return this.Height;
    }
    @Override
    public SpriteSheet getNewInstance(){
        return new SpriteSheet(super.getTextureID(),super.getRawPixels(),super.getWidth(),super.getHeight(),super.getName(),this.Width,this.Height);
    }
}
