/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Forum.ToolBox;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

/**
 *
 * @author Allazham
 */
public abstract class ToolGraphic {
    private final BufferedImage img;
    private String ToolTip = "";
    public ToolGraphic(BufferedImage img){
        this.img = img;
    }
    public void Render(Graphics2D g){
        g.drawImage(this.img, 0,0, null);
        
    }
    public BufferedImage getImage(){
        return this.img;
    }
    public void setToolTip(String tt){
        this.ToolTip = tt;
    }
    public String getToolTip(){
        return this.ToolTip;
    }
}
