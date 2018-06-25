/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Holder.TextureTypes;

import Holder.Texture;
import java.awt.Color;
import java.util.ArrayList;

/**
 *
 * @author Allazham
 */
public class SingleColorTexture extends Texture{
    private static final ArrayList<SingleColorTexture> ConstructedTextures = new ArrayList();
    private Color c;
    private SingleColorTexture(Color c){
        int[] tex = new int[256];
        int i;
        for(i = 0; i < 256;i++){
            tex[i] = c.getRGB();
        }
        super.setRawPixels(16, 16, tex);
        super.CopyInstance = true;
        this.c = c;
    }
    private SingleColorTexture(SingleColorTexture tex){
        super(tex.getRawPixels(),16,16);
        super.setName(tex.getName());
        super.CopyInstance = true;
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
    public SingleColorTexture getNewInstance(){
        return new SingleColorTexture(this.getColor());
    }
    public Color getColor(){
        return c;
    }
    public static ArrayList<SingleColorTexture> getLoadedTextures(){
        return ConstructedTextures;
    }
    public static SingleColorTexture getColoredTexture(Color c){
        int i;
        SingleColorTexture t;
        for(i = 0; i < ConstructedTextures.size();i++){
            if(ConstructedTextures.get(i).c.equals(c)){
                return ConstructedTextures.get(i);
            }
        }
        t = new SingleColorTexture(c);
        ConstructedTextures.add(t);
        t.setName(c.toString());
        return t;
    }
}
