/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GraphicalObjects;

import Forum.GraphicConditions;
import Utils.SaveManager.SaveObjectPackage;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.UUID;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 *
 * @author Allazham
 */
public abstract class GraphicsObject extends JLabel implements Renewable{
    private GraphicConditions c;
    private boolean Disabled,Locked,requestRepaint;
    private double x, y;
    private UUID uid;
    public GraphicsObject(){
        super.setOpaque(false);
        this.uid = UUID.randomUUID();
    }
    public GraphicsObject(String uid){
        super.setOpaque(false);
        this.uid = UUID.fromString(uid);
    }
    public GraphicsObject(UUID uid){
        this.uid = uid;
    }
    public GraphicsObject(UUID uid,GraphicConditions gc){
        this.uid = uid;
        this.c = gc;
        super.setSize(10,10);
    }
    @Override
    public abstract GraphicsObject getNewInstance();
    public abstract void renderMap(Graphics2D g,GraphicConditions c);
    public abstract boolean isInsideHitBox(double x, double y);
    public abstract double[][] getSurroundingPoints();
    public abstract void Destroy();
    @Override
    public void paint(Graphics g){
        try{
        //g.clearRect(0,0,getWidth(),getHeight());
        super.setLocation(0, 0);
        super.setSize(c.getWidthRegular(),c.getHeightRegular());
        if(this.getWidth() > 0 && this.getHeight() > 0){
            super.paint(g);
            renderMap((Graphics2D) g,c);
        }
        }catch(Exception ex){
            
        }
    }
    public void setGraphicCondition(GraphicConditions gc){
        this.c = gc;
        super.setSize(10,10);
    }
    public GraphicConditions getGraphicConditions(){
        return this.c;
    }
    public void setImageX(double x){
        this.x = x;
    }
    public void setImageY(double y){
        this.y = y;
    }
    public double getImageX(){
        return this.x;
    }
    public double getImageY(){
        return this.y;
    } 
    
    public void addImageX(double x){
        this.x += x;
    }
    public void addImageY(double y){
        this.y += y;
    }
    
    public void setUID(String uid){
        this.uid = UUID.fromString(uid);
    }
    public void setUID(UUID uid){
        this.uid = uid;
    }
    public UUID getUID(){
        return this.uid;
    }
    public void setDisabled(boolean disabled){
        this.Disabled = disabled;
    }
    public void setLocked(boolean lock){
        this.Locked = lock;
    }
    public boolean isDisabled(){
        return this.Disabled;
    }
    public boolean isLocked(){
        return this.Locked;
    }
    public abstract SaveObjectPackage getSaveObjectPackage();
}
