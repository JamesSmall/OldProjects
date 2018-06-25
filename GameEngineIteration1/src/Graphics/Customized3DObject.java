/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Graphics;

/**
 *
 * @author Allazham
 */
public abstract class Customized3DObject extends Abstract3DObject {
    private float[][] points;
    public Customized3DObject(float x,float y,float z, float[][] points,boolean disabled){
        super(x,y,z,disabled);
        this.points = points;
    }
    public void setPoints(float[][] points){
        this.points = points;
    }
    public float[][] getPoints(){
        return points;
    }
}
