/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GraphicalObjects;

import Forum.GraphicConditions;
import Utils.Config;
import Utils.SaveManager.SaveObjectPackage;
import java.awt.Color;
import java.awt.Graphics2D;
import java.util.UUID;

/*
 * @author Allazham
 * a basic point on screen that isnt a point.
 */
public class Node extends GraphicsObject{
    private Color c = Config.defaultColor;
    public Node(double x, double y){
        super.setImageX(x);
        super.setImageY(y);
    }
    public Node(UUID uid,double x, double y){
        super(uid);
        super.setImageX(x);
        super.setImageY(y);
    }
    @Override
    public void renderMap(Graphics2D g, GraphicConditions c) {
        g.setColor(this.c);
        int centerX = c.getScreenCenterX(),CenterY = c.getScreenCenterY();
        double scale = c.getScale();
        g.fillOval(-centerX+(int)(super.getImageX()/scale)-3, -CenterY+(int)(super.getImageY()/scale)-3, 5,5);
    }
    @Override
    public Node getNewInstance() {
        return new Node(this.getUID(),this.getImageX(),this.getImageY());
    }

    @Override
    public boolean isInsideHitBox(double x, double y) {
        if(x - 5 < super.getImageX() && super.getImageX() < x + 5 && y - 5 < super.getImageY() && super.getImageY() < y + 5){
            return true;
        }
        return false;
    }
    @Override
    public double[][] getSurroundingPoints() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    public void setColor(Color c){
        this.c = c;
    }
    public Color getColor(){
        return this.c;
    }
    @Override
    public void Destroy() {
        
    }
    @Override
    public SaveObjectPackage getSaveObjectPackage() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
