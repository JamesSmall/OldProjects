/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Holder.TextureTypes;

import Holder.Texture;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

/**
 *
 * @author Allazham
 */
public class RollingHorizonalTexture extends Texture{
    private double angle = 0,Change;
    private static final double TWOPI = Math.PI*2,HALFPI = Math.PI/2,THREEHALVESPI = Math.PI*3/2;
    private final int normalWidth;
    double addDistance;
    public RollingHorizonalTexture(int[] data, int width,int height){
        //super(data,width,height);
        this.normalWidth = height;
        BufferedImage img1 = new BufferedImage(width,height,BufferedImage.TYPE_4BYTE_ABGR);
        BufferedImage img2 = new BufferedImage(width,height,BufferedImage.TYPE_4BYTE_ABGR);
        BufferedImage offical = new BufferedImage(width*2,height,BufferedImage.TYPE_4BYTE_ABGR);
        int x,y;
        for(y = 0;y<height;y++){
            for(x = 0; x < width;x++){
                 img1.setRGB(x, y,data[y*width+x]);
            }
        }
        //fuses two images togeatehr
        Graphics2D g = img2.createGraphics();
        g.drawImage(img2, img1.getWidth()/2, 0, null);
        g.drawImage(img2, -img1.getWidth()/2, 0, null);
       
        g = offical.createGraphics();
        g.drawImage(img1, 0, 0, null);
        g.drawImage(img2, offical.getWidth()/2,0, null);
        
        super.setImage(offical);
        if(this.normalWidth > this.getWidth()/4){
            throw new NullPointerException("Image needs to be 4 times bigger than normal width");
        }
        this.Change = (double)this.normalWidth/(double)this.getWidth();
    }
    public RollingHorizonalTexture(BufferedImage img){
        this.normalWidth = img.getHeight();
        BufferedImage img2 = new BufferedImage(img.getWidth(),img.getHeight(),BufferedImage.TYPE_4BYTE_ABGR);
        BufferedImage offical = new BufferedImage(img.getWidth()*2,img.getHeight(),BufferedImage.TYPE_4BYTE_ABGR);
        
        //fuses two iamges togeather
        Graphics2D g = img2.createGraphics();
        g.drawImage(img, img.getWidth()/2, 0, null);
        g.drawImage(img, -img.getWidth()/2, 0, null);
        
        g = offical.createGraphics();
        g.drawImage(img, 0, 0, null);
        g.drawImage(img2, offical.getWidth()/2,0, null);
        g.dispose();
        
        super.setImage(offical);
        if(this.normalWidth > this.getWidth()/4){
            throw new NullPointerException("Image needs to be 4 times bigger than normal width");
        }
        this.Change = (double)this.normalWidth/(double)this.getWidth();
    }
    public RollingHorizonalTexture(String name,int[] data, int width,int height){
        //super(data,width,height);
        super.setName(name);
        this.normalWidth = height;
        BufferedImage img1 = new BufferedImage(width,height,BufferedImage.TYPE_4BYTE_ABGR);
        BufferedImage img2 = new BufferedImage(width,height,BufferedImage.TYPE_4BYTE_ABGR);
        BufferedImage offical = new BufferedImage(width*2,height,BufferedImage.TYPE_4BYTE_ABGR);
        int x,y;
        for(y = 0;y<height;y++){
            for(x = 0; x < width;x++){
                 img1.setRGB(x, y,data[y*width+x]);
            }
        }
        //fuse two images togeather
        Graphics2D g = img2.createGraphics();
        g.drawImage(img2, img1.getWidth()/2, 0, null);
        g.drawImage(img2, -img1.getWidth()/2, 0, null);
       
        g = offical.createGraphics();
        g.drawImage(img1, 0, 0, null);
        g.drawImage(img2, offical.getWidth()/2,0, null);
        
        super.setImage(offical);
        if(this.normalWidth > this.getWidth()/4){
            throw new NullPointerException("Image needs to be 4 times bigger than normal width");
        }
        this.Change = (double)this.normalWidth/(double)this.getWidth();
    }
    public RollingHorizonalTexture(String name,BufferedImage img){
        super.setName(name);
        this.normalWidth = img.getHeight();
        BufferedImage img2 = new BufferedImage(img.getWidth(),img.getHeight(),BufferedImage.TYPE_4BYTE_ABGR);
        BufferedImage offical = new BufferedImage(img.getWidth()*2,img.getHeight(),BufferedImage.TYPE_4BYTE_ABGR);
        
        Graphics2D g = img2.createGraphics();
        g.drawImage(img, img.getWidth()/2, 0, null);
        g.drawImage(img, -img.getWidth()/2, 0, null);
        
        g = offical.createGraphics();
        g.drawImage(img, 0, 0, null);
        g.drawImage(img2, offical.getWidth()/2,0, null);
        g.dispose();
        
        super.setImage(offical);
        if(this.normalWidth > this.getWidth()/4){
            throw new NullPointerException("Image needs to be 4 times bigger than normal width");
        }
        this.Change = (double)this.normalWidth/(double)this.getWidth();
    }
    public RollingHorizonalTexture(int TextureID,int[] Pixels,int Width,int Height,String name){
        super(TextureID,Pixels,Width,Height,name);
        this.normalWidth = Height;
        if(this.normalWidth > this.getWidth()/4){
            throw new NullPointerException("Image needs to be 4 times bigger than normal width");
        }
        this.Change = (double)this.normalWidth/(double)this.getWidth();
        super.CopyInstance = true;
    }
    
    @Override
    public double getGreaterX() {
        return this.getLesserX()+this.Change;
    }

    @Override
    public double getGreaterY() {
        return 1;
    }

    @Override
    public double getLesserX() {
       double point;
       if(HALFPI <= this.angle && this.angle < THREEHALVESPI){
           return (this.angle/TWOPI/2d);
       }
       else{
            point = this.angle/TWOPI/2d;
            if(point <= .125){
                return point + .75;
            }
            else{
                return point + .25;
            }
        }
    }
    @Override
    public double getLesserY() {
        return 0;
    }
    public int getNormalWidth(){
        return this.normalWidth;
    }
    public void setAngleRadians(double angle){
        int distConvert = (int) Math.floor(angle/TWOPI);
        this.angle = angle - TWOPI*distConvert;
            //this.angle =+ TWOPI;
    }
    @Deprecated
    /**used for testing purposes only
     * @Deprecated was used for testing purposes only
     */
    public void setAngleDegree(double angle){
        this.setAngleRadians(Math.toRadians(angle));
    }
    //in radians
    public double getAngle(){
        return this.angle;
    }
    @Override
    public RollingHorizonalTexture getNewInstance(){
        return new RollingHorizonalTexture(super.getTextureID(),super.getRawPixels(),super.getWidth(),super.getHeight(),super.getName());
    }
}
