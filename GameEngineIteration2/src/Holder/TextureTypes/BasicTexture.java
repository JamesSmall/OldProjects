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
public class BasicTexture extends Texture{
    public BasicTexture(int[] data, int width,int height){
        super(data,width,height);
    }
    public BasicTexture(BufferedImage img){
       super(img);
    }
    public BasicTexture(Texture t){
        super(t.getTextureID(),t.getRawPixels(),t.getWidth(),t.getHeight(),t.getName());
        super.CopyInstance = true;
    }
    public BasicTexture(String name,BufferedImage img){
       super(img);
       super.setName(name);
    }
    public BasicTexture(String name,int TextureID,int[] Pixels,int Width,int Height){
        super(TextureID,Pixels,Width,Height,name);
        super.setName(name);
    }
    @Override
    public double getGreaterX() {
        return 1;
    }

    @Override
    public double getGreaterY() {
        return 1;
    }

    @Override
    public double getLesserX() {
        return 0;
    }

    @Override
    public double getLesserY() {
        return 0;
    }
    @Override
    public BasicTexture getNewInstance(){
        return new BasicTexture(this);
    }
}
