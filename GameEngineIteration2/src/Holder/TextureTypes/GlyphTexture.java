/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Holder.TextureTypes;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import org.lwjgl.opengl.GL11;

/**
 *
 * @author Allazham
 */
public class GlyphTexture extends SpriteSheet{
    public final ArrayList<TextPacket> packet;
    public TextPacket pack = null;
    private BufferedImage img;
    public GlyphTexture(int[] data, int width,int height,int AWidth,int AHeight,List<TextPacket> packet){
        super(data,width,height,AWidth,AHeight);
        this.img = super.getBufferedImage();
        this.packet = new ArrayList(packet);
        for(TextPacket pack: this.packet){
           this.editPacket(pack);
       }
        super.TextureBindType = GL11.GL_LINEAR;
    }
    public GlyphTexture(String name,BufferedImage img,int AWidth,int AHeight,List<TextPacket> packet){
       super(name,img,AWidth,AHeight);
       this.img = img;
       this.packet = new ArrayList(packet);
       for(TextPacket pack: this.packet){
           this.editPacket(pack);
       }
       
        super.TextureBindType = GL11.GL_LINEAR;
    }
    
    private void editPacket(TextPacket packet){
        BufferedImage image = new BufferedImage(super.getSubImageWidth(),super.getSubImageHeight(),BufferedImage.TYPE_4BYTE_ABGR_PRE);
        Graphics2D g = image.createGraphics();
        g.drawImage(img,-(packet.x*super.getSubImageWidth()),-(packet.y*super.getSubImageHeight()),null);
        g.dispose();
        int width = super.getSubImageWidth(),height = super.getSubImageHeight();
        int maxX = 0,minX = height;
        boolean isEmpty = true;
        for(int y = 0; y < height;y++){
            for(int x = 0; x < width;x++){
                int b = (image.getRGB(x, y) >> 24) & 0xFF;
                if(b != 0){
                    minX = Math.min(minX, x);
                    isEmpty = false;
                    break;
                }
            }
        }
        if(isEmpty){
            packet.width = width/2;
            return;
        }
        for(int y = 0; y < height;y++){
            for(int x = width-1; x > -1;x--){
                int b = (image.getRGB(x, y) >> 24) & 0xFF;
                if(b != 0){
                    maxX = Math.max(maxX, x);
                    break;
                }
            }
        }
        packet.dx = minX;
        packet.width = width-maxX;
        System.out.println(packet.c+"_"+packet.dx+"_"+packet.width);
    }
    public void setChar(char c){
        for(TextPacket p:packet){
            if(p.equals(c)){
                this.setup(p);
                return;
            }
        }
    }
    public TextPacket obtainCurrentLink(char c){
        return this.pack;
    }
    public TextPacket obtainLinkToChar(char c){
        for(TextPacket p:packet){
            if(p.equals(c)){
                return p;
            }
        }
        return null;
    }
    public void activateLink(TextPacket c){
        this.setup(c);
    }
    private void setup(TextPacket p){
        this.pack = p;
        if(p == null){
            return;
        }
        super.setX(p.x);
        super.setY(p.y);
    }
    public double getDXGapPercentage(){
        if(pack == null){
            return 0;
        }
        return ((double)pack.dx)/((double)super.getSubImageWidth());
    }
    public double getDYGapPercentage(){
        if(pack == null){
            return 0;
        }
        
        return ((double)pack.dy)/((double)super.getSubImageHeight());
    }
    public double getDWidthPercentage(){
        if(pack == null){
            return 0;
        }
        return ((double)pack.width)/((double)super.getSubImageWidth());
    }
    public double getDHeightPercentage(){
        if(pack == null){
            return 0;
        }
        return ((double)pack.height)/((double)super.getSubImageHeight());
    }
}
