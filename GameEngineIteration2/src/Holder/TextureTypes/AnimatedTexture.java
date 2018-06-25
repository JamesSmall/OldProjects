/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Holder.TextureTypes;

import Holder.Texture;
import java.awt.image.BufferedImage;
import org.lwjgl.LWJGLException;
import org.lwjgl.Sys;

/**
 *
 * @author Allazham
 */
public class AnimatedTexture extends Texture {
    //varriables
    private int x = 0, y = 0;
    private final int Width,Height;
    private final long TimeTilUpdate;
    private long lastUpdate = 0;
    // constructors
    public AnimatedTexture(int[] data, int width,int height,int AWidth,int AHeight){
        super(data,width,height);
        Width = AWidth;
        Height = AHeight;
        this.TimeTilUpdate = 100;
    }
    public AnimatedTexture(BufferedImage img,int AWidth,int AHeight){
       super(img);
       Width = AWidth;
       Height = AHeight;
       this.TimeTilUpdate = 100;
    }
    public AnimatedTexture(int TextureID,int[] Pixels,int Width,int Height,String name,int AHeight,int AWidth){
        super(TextureID,Pixels,Width,Height,name);
        this.Width = AWidth;
        this.Height = AHeight;
        this.TimeTilUpdate = 100;
        super.CopyInstance = true;
    }
    public AnimatedTexture(int[] data, int width,int height,int AWidth,int AHeight,long TimeTilUpdate){
        super(data,width,height);
        Width = AWidth;
        Height = AHeight;
        this.TimeTilUpdate = TimeTilUpdate;
    }
    public AnimatedTexture(BufferedImage img,int AWidth,int AHeight,long TimeTilUpdate){
       super(img);
       Width = AWidth;
       Height = AHeight;
       
       this.TimeTilUpdate = TimeTilUpdate;
    }
    public AnimatedTexture(int TextureID,int[] Pixels,int Width,int Height,String name,int AHeight,int AWidth,long TimeTilUpdate){
        super(TextureID,Pixels,Width,Height,name);
        this.Width = AWidth;
        this.Height = AHeight;
        super.CopyInstance = true;
        this.TimeTilUpdate = TimeTilUpdate;
    }
    
    public AnimatedTexture(String name,int[] data, int width,int height,int AWidth,int AHeight){
        super(data,width,height);
        super.setName(name);
        Width = AWidth;
        Height = AHeight;
        this.TimeTilUpdate = 100;
    }
    public AnimatedTexture(String name,BufferedImage img,int AWidth,int AHeight){
       super(img);
       super.setName(name);
       Width = AWidth;
       Height = AHeight;
       
        this.TimeTilUpdate = 100;
    }
    public AnimatedTexture(String name,int[] data, int width,int height,int AWidth,int AHeight,long TimeTilUpdate){
        super(data,width,height);
        super.setName(name);
        Width = AWidth;
        Height = AHeight;
        this.TimeTilUpdate = TimeTilUpdate;
    }
    public AnimatedTexture(String name,BufferedImage img,int AWidth,int AHeight,long TimeTilUpdate){
       super(img);
       super.setName(name);
       Width = AWidth;
       Height = AHeight;
       
       this.TimeTilUpdate = TimeTilUpdate;
    }
    //custom bind, for animation done on regular texture appeareance
    @Override
    public void bind() throws LWJGLException{
        super.bind();
        if(Sys.getTime() - this.lastUpdate > this.TimeTilUpdate){
            this.lastUpdate = Sys.getTime();
            if(this.x == this.Width-1){
                if(this.y == this.Height-1){
                   this.y = 0;
                }
                else{
                    this.y++;
                }
                this.x = 0;
            }
            else{
                this.x++;
            }
        }
    }
    //animation location
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
    //for inheretiance
    public int getSpritenWidthClicks(){
        return this.Width;
    }
    public int getSpriteHeightClicks(){
        return this.Height;
    }
    //new instance
    @Override
    public AnimatedTexture getNewInstance(){
        return new AnimatedTexture(super.getTextureID(),super.getRawPixels(),super.getWidth(),super.getHeight(),super.getName(),this.Width,this.Height,this.TimeTilUpdate);
    }
}
