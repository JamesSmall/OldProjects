/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GraphicalObjects;

import Forum.GraphicConditions;
import Utils.SaveManager.SaveObjectPackage;
import java.awt.Color;
import java.awt.Graphics2D;

/**
 *
 * @author Allazham
 * a basic line in use for the object showing
 */
public class Line extends GraphicsObject{
    private double x1,y1,x2,y2;
    private Node front = null,back = null;
    private Color color = Color.GREEN;
    public Line(Node front, Node back){
        this.front = front;
        this.back = back;
        this.x1 = front.getImageX();
        this.y1 = front.getImageY();
        this.x2 = back.getImageX();
        this.y2 = back.getImageY();
        this.setLocation();
    }
    public void setX1(double x){
        this.x1 = x;
        this.setLocation();
    }
    public void setY1(double y){
        this.y1 = y;
        this.setLocation();
    }
    public double getX1(){
        return this.x1;
    }
    public double getY1(){
        return this.y1;
    }
    public void setx2(double x){
        this.x2 = x;
        this.setLocation();
    }
    public void setY2(double y){
        this.y2 = y;
        this.setLocation();
    }
    public double getx2(){
        return this.x2;
    }
    public double getY2(){
        return this.y2;
    }
    
    public void setFrontNode(Node n){
        this.front = n;
        x1 = n.getImageX();
        y1 = n.getImageY();
        this.setLocation();
    }
    public void setBackNode(Node n){
        this.back = n;
        x2 = n.getImageX();
        y2 = n.getImageY();
        this.setLocation();
    }
    public Node getFrontNode(){
        return this.front;
    }
    public Node getBackNode(){
        return this.back;
    }
    public void UpdateNode(Node n){
        if(this.front == n){
            x1 = n.getImageX();
            y1 = n.getImageY();
        }
        else if(this.back == n){
            x2 = n.getImageX();
            y2 = n.getImageY();
        }
        this.setLocation();
    }
    public void updateNodes(){
        if(this.front != null){
            x1 = front.getImageX();
            y1 = front.getImageY();
        }
        if(this.back != null){
            x2 = back.getImageX();
            y2 = back.getImageY();
        }
    }
    public void checkNodes(){
        if(front != null){
            x1 = front.getImageX();
            y1 = front.getImageY();
        }
        if(back != null){
            x2 = back.getImageX();
            y2 = back.getImageY();
        }
        this.setLocation();
    }
    private void setLocation(){
        super.setImageX((this.x1+this.y1)/2);
        super.setImageY((this.x2+this.y2)/2);
    }
    @Override
    public void Destroy(){
        this.setFrontNode(null);
        this.setBackNode(null);
    }

    @Override
    public void renderMap(Graphics2D g, GraphicConditions c) {
        int centerX = c.getScreenCenterX(),centerY = c.getScreenCenterY();
        double scale = c.getScale();
        g.setColor(color);
        g.drawLine((int)(-centerX+(x1/scale)), (int)(-centerY + (y1/scale)),(int)(-centerX + (x2/scale)), (int)(-centerY + (y2/scale)));
    }
    @Override
    public Line getNewInstance() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    public double DistancepointToLine(double Px,double Py) {
        if(((x1 < Px && Px < x2)||(x2 < Px && Px < x1))&&((y1 < Py && Py < y2)||(y2 < Py && Py < y1))){
            double normalLength = Math.sqrt((x2-x1)*(x2-x1)+(y2-y1)*(y2-y1));
            return Math.abs((Px-x1)*(y2-y1)-(Py-y1)*(x2-x1))/normalLength;
        }
        else if(((y1 < Py && Py < y2)||(y2 < Py && Py < y1))){
                return Math.abs(Px - x1);
            }
        else if(((x1 < Px && Px < x2)||(x2 < Px && Px < x1))){
                return Math.abs(Py - y1);
        }
        return Double.MAX_VALUE;
    }

    @Override
    public boolean isInsideHitBox(double x, double y) {
        return false;
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public double[][] getSurroundingPoints() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    @Override
    public SaveObjectPackage getSaveObjectPackage() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
