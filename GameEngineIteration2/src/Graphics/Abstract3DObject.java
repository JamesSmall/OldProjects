/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Graphics;

import java.util.ArrayList;
import java.util.List;
import org.newdawn.slick.opengl.Texture;

/**
 *
 * @author james small
 * Last Edited 3/1/2013
 */
public abstract class Abstract3DObject extends GraphicsObject implements Location{
    private float x, y, z;
    float[][] points;
    private static GraphicConditions g = GraphicConditions.getInstance();
    public Abstract3DObject(float x, float y, float z, boolean disabled){
        super(disabled);
        this.x = x;
        this.y = y;
        this.z = z;
        super.setPriority(100);
    }
    @Override
    protected void checkCondations(GraphicConditions c){
        if(!c.is3D()){
            c.set3D();
        }
    }
    @Override
    public void setLocationX(float x){
        this.x = x;
    }
    @Override
    public void setLocationY(float y){
        this.y = y;
    }
    @Override
    public void setLocationZ(float z){
        this.z = z;
    }
    @Override
    public void setLocation(float x,float y,float z){
        this.x = x;
        this.y = y;
        this.z = z;
    }
    @Override
    public float getLocationX(){
        return this.x - g.getCameraX();
    }
    @Override
    public float getLocationY(){
        return this.y - g.getCameraY();
    }
    @Override
    public float getLocationZ(){
        return this.z - g.getCameraZ();
    }
    @Override
    public float[] getLocation(){
        float[] ret = {this.x - g.getCameraX(), this.y - g.getCameraY(),this.z - g.getCameraZ()};
        return ret;
    }
}