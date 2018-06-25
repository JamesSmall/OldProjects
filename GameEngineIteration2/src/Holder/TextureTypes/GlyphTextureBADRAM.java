/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Holder.TextureTypes;

import Holder.Texture;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import org.lwjgl.opengl.GL11;

/**
 *
 * @author Allazham
 */
public class GlyphTextureBADRAM extends Texture{
    private static final int drawingSize = 16;
    private static final String testString = "Fgy";
    private double sizeDiff = 0;
    private int size = 0;
    private String currentString;
    public GlyphTextureBADRAM(){
        super.TextureBindType = GL11.GL_LINEAR;
        super.setRawPixels(0, 0, new int[]{0});
    }
    public GlyphTextureBADRAM(String args){
        super.TextureBindType = GL11.GL_LINEAR;
        this.GenerateString(args, 16);
    }
    public GlyphTextureBADRAM(String args,int size){
        super.TextureBindType = GL11.GL_LINEAR;
        this.GenerateString(args, size);
    }
    public final void GenerateString(String str, int drawingSize,String strz,Color c){
        BufferedImage img = this.createFullImage(str, drawingSize,strz,c);
        BufferedImage img2 = this.createImage(GlyphTextureBADRAM.testString, drawingSize);
        this.doFinalDrawing(img, this.getActualWidth(img), this.getActaullHeight(img2),this.getGapHeight(img2));
    }
    public final void GenerateString(String str, int drawingSize,String stz){
        BufferedImage img = this.createFontImage(str, drawingSize,stz);
        BufferedImage img2 = this.createImage(GlyphTextureBADRAM.testString, drawingSize);
        this.doFinalDrawing(img, this.getActualWidth(img), this.getActaullHeight(img2),this.getGapHeight(img2));
    }
    public final void GenerateString(String str, int drawingSize,Color c){
        BufferedImage img = this.createColoredImage(str, drawingSize,c);
        BufferedImage img2 = this.createImage(GlyphTextureBADRAM.testString, drawingSize);
        this.doFinalDrawing(img, this.getActualWidth(img), this.getActaullHeight(img2),this.getGapHeight(img2));
    }
    public final void GenerateString(String str){
        BufferedImage img = this.createDefaultImage(str);
        BufferedImage img2 = this.createDefaultImage(GlyphTextureBADRAM.testString);
        this.doFinalDrawing(img, this.getActualWidth(img), this.getActaullHeight(img2),this.getGapHeight(img2));
    }
    public final void GenerateString(String str, int drawingSize){
        BufferedImage img = this.createImage(str, drawingSize);
        BufferedImage img2 = this.createImage(GlyphTextureBADRAM.testString, drawingSize);
        
        this.doFinalDrawing(img, this.getActualWidth(img), this.getActaullHeight(img2),this.getGapHeight(img2));
    }
    private BufferedImage createFullImage(String str,int drawingSize,String font,Color c){
        BufferedImage img = new BufferedImage(drawingSize*str.length(),drawingSize*2,BufferedImage.TYPE_4BYTE_ABGR);
        Graphics2D g = img.createGraphics();
        str = str.replace(" ", "  ");
        this.size = drawingSize;
        g.setColor(Color.BLACK);
        
        RenderingHints rh = new RenderingHints(
             RenderingHints.KEY_ANTIALIASING,
             RenderingHints.VALUE_ANTIALIAS_ON);
        g.setRenderingHints(rh);
        g.setFont(new Font(font, Font.BOLD, size));
        g.drawString(str,0,drawingSize);
        g.dispose();
        return img;
    }
    private BufferedImage createColoredImage(String str,int drawingSize,Color c){
        BufferedImage img = new BufferedImage(drawingSize*str.length(),drawingSize*2,BufferedImage.TYPE_4BYTE_ABGR);
        Graphics2D g = img.createGraphics();
        
        str = str.replace(" ", "  ");
        this.size = drawingSize;
        g.setColor(c);
        
        RenderingHints rh = new RenderingHints(
             RenderingHints.KEY_ANTIALIASING,
             RenderingHints.VALUE_ANTIALIAS_ON);
        g.setRenderingHints(rh);
        g.setFont(new Font(g.getFont().toString(), Font.BOLD, size));
        g.drawString(str,0,drawingSize);
        g.dispose();
        return img;
    }
    private BufferedImage createFontImage(String str,int drawingSize,String font){
        BufferedImage img = new BufferedImage(drawingSize*str.length(),drawingSize*2,BufferedImage.TYPE_4BYTE_ABGR);
        Graphics2D g = img.createGraphics();
        str = str.replace(" ", "  ");
        this.size = drawingSize;
        g.setColor(Color.BLACK);
        
        RenderingHints rh = new RenderingHints(
             RenderingHints.KEY_ANTIALIASING,
             RenderingHints.VALUE_ANTIALIAS_ON);
        g.setRenderingHints(rh);
        g.setFont(new Font(font, Font.BOLD, size));
        g.drawString(str,0,drawingSize);
        g.dispose();
        return img;
    }
    private BufferedImage createImage(String str,int drawingSize){
        BufferedImage img = new BufferedImage(drawingSize*str.length(),drawingSize*2,BufferedImage.TYPE_4BYTE_ABGR);
        Graphics2D g = img.createGraphics();
        str = str.replace(" ", "  ");
        this.size = drawingSize;
        g.setColor(Color.BLACK);
        
        RenderingHints rh = new RenderingHints(
             RenderingHints.KEY_ANTIALIASING,
             RenderingHints.VALUE_ANTIALIAS_ON);
        g.setRenderingHints(rh);
        g.setFont(new Font(g.getFont().toString(), Font.BOLD, size));
        g.drawString(str,0,drawingSize);
        g.dispose();
        return img;
    }
    private BufferedImage createDefaultImage(String str){
        BufferedImage img = new BufferedImage(16*str.length(),16,BufferedImage.TYPE_4BYTE_ABGR);
        Graphics2D g = img.createGraphics();
        str = str.replace(" ", "  ");
        this.size = 16;
        g.setColor(Color.BLACK);
        
        RenderingHints rh = new RenderingHints(
             RenderingHints.KEY_ANTIALIASING,
             RenderingHints.VALUE_ANTIALIAS_ON);
        g.setRenderingHints(rh);
        g.setFont(new Font(g.getFont().toString(), Font.BOLD, size));
        g.drawString(str,0,16);
        g.dispose();
        return img;
    }
    private int getActualWidth(BufferedImage img){
        int x,y,i = 0;
        for(y = 0; y < img.getHeight();y++){
            for(x = i; x < img.getWidth();x++){
                if(img.getRGB(x, y) != 0){
                    i = x+2;
                }
            }
        }
        return i;
    }
    private int getGapHeight(BufferedImage img){
        int x,y,i = img.getHeight()-1;
        for(x = 0; x < img.getWidth();x++){
            for(y = 0; y < i ;y++){
                if(img.getRGB(x, y) != 0){
                    i = y-1;
                    break;
                }
            }
        }
        return i;
    }
    private int getActaullHeight(BufferedImage img){
        int x,y,i = 0;
        for(x = 0; x < img.getWidth();x++){
            for(y = img.getHeight()-1; y > i ;y--){
                if(img.getRGB(x, y) != 0){
                    i = y+1;
                    break;
                }
            }
        }
        
        return img.getHeight()-(img.getHeight()-i);
    }
    private void doFinalDrawing(BufferedImage img,int width,int height,int gap){
        if(width <= 0||height-gap <= 0){
            super.setImage(new BufferedImage(1,1,BufferedImage.TYPE_4BYTE_ABGR));
            return;
        }
        BufferedImage ret = new BufferedImage(width,height-gap,BufferedImage.TYPE_4BYTE_ABGR);
        Graphics2D g = ret.createGraphics();
        RenderingHints rh = new RenderingHints(
             RenderingHints.KEY_ANTIALIASING,
             RenderingHints.VALUE_ANTIALIAS_ON);
        g.setRenderingHints(rh);
        g.drawImage(img, 0, -gap, null);
        g.dispose();
        
        this.sizeDiff = (float)height/(float)width;
        super.setImage(ret);
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
    private double getPredictedUsageLength(double currWidth,double currHeight){
        return this.sizeDiff/(currHeight/currWidth);
    }
    public double getTextureUsageWidth(double width,double height){
        double test = this.getPredictedUsageLength(width, height);
        return (test < 1d ? test: 1d);
    }
    public float CheckReductionWidth(float width,float height){
        float f = (float)this.getPredictedUsageLength(width, height);
        if(f < 1){
            return width;
        }
        return width/f;
    }
    @Override
    public double getLesserY() {
        return 0;
    }
    public double getApprorateScale(){
        return this.sizeDiff;
    }
    @Override
    public GlyphTextureBADRAM getNewInstance() {
        return new GlyphTextureBADRAM(this.currentString,this.size);
    }
}
