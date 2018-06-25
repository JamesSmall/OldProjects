/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GraphicalObjects;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;

/**
 * basic  pointer image, this is for targeting purposes only.
 * @author Allazham
 */
public class PointerImage {
    private BufferedImage img;
    public PointerImage(BufferedImage img){
        this.img = img;
    }
    public void RenderMap(Graphics g, double x, double y){
        g.drawImage(img.getScaledInstance(8, 8,Image.SCALE_SMOOTH), (int)x, (int)y, 8,8, null);
        
    }
}
