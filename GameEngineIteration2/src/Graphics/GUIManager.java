/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Graphics;
import java.awt.Component;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author james small
 * lastEdited:2/9/2013
 */
@SuppressWarnings("unchecked")
public class GUIManager{
    private int Frame = 0,x=0,z=0,Width=0,Height=0;
    private double[][] points;
    
    protected List<double[][]> GUIPoints = new ArrayList();
    protected List<Component> GUIComp = new ArrayList();
    public GUIManager(int i,double[][] points){
        this.Frame = i;
        this.points = points;
    }
    public void setFrame(int Frame){
        this.Frame = Frame;
    }
    public int getFrameNumber(){
        return this.Frame;
    }
    public void setGUIPoints(double[][] points){
        this.points = points;
    }
    public double[][] getGUIPoints(){
        return this.points;
    }
    public void addGUIComponenet(Component comp, double[][] points){
        this.GUIComp.add(comp);
        this.GUIPoints.add(points);
    }
    public void removeGUIComponenet(Component comp){
        int i = this.GUIComp.indexOf(comp);
        if(i == -1){
            return;
        }
        this.GUIPoints.remove(i);
        this.GUIComp.remove(comp);
    }
    public void reset(){
        int i;
        double[][] item;
        for(i=0;i<this.GUIComp.size();i++){
            item = this.GUIPoints.get(i);
            this.GUIComp.get(i).setLocation(this.x+(int)(this.Width*item[0][0]), this.z+(int)(this.Height*item[1][0]));
            this.GUIComp.get(i).setSize((int)(this.Width*(item[0][1]-item[0][0])),(int)(this.Height*(item[1][1]-item[1][0])));
            this.GUIComp.get(i).validate();
        }
    }
    protected void SetFramePoints(int Width, int Height){
        int i = 0;
        double[][] item;
        this.x = (int) (Width*points[0][0]);
        this.z = (int) (Height*points[1][0]);
        this.Width = (int) (Width*(points[0][1]-points[0][0]));
        this.Height = (int) (Height*(points[1][1] - points[1][0]));
        for(i=0;i<this.GUIComp.size();i++){
            item = this.GUIPoints.get(i);
            this.GUIComp.get(i).setLocation(this.x+(int)(this.Width*item[0][0]), this.z+(int)(this.Height*item[1][0]));
            this.GUIComp.get(i).setSize((int)(this.Width*(item[0][1]-item[0][0])),(int)(this.Height*(item[1][1]-item[1][0])));
            this.GUIComp.get(i).validate();
        }
    }
}
