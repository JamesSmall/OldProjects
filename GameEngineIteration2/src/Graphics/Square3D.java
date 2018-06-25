/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Graphics;

import org.lwjgl.opengl.GL11;
import org.newdawn.slick.Color;

/**
 * LastEdited 3/1/2013
 *holds any type of 3d square
 * @author james small
 */
public class Square3D extends Customized3DObject {
    public Square3D(float x,float y, float z,float[][] points,boolean disabled){
        //texture without sprite;
        super(x,y,z,points, disabled);
    }
    @Override
    protected void render() {
        float[] loc = this.getLocation();
        float[][] points = this.getPoints();
        
        if(null != null){
            
        }
        else{
            Color.red.bind();
        }
        GL11.glBegin(GL11.GL_QUADS);
            //GL11.glTexCoord2f(texCords[0][0], texCords[0][1]);
            GL11.glVertex3f(loc[0] + points[0][0],loc[1] + points[0][1],loc[2] + points[0][2]);
            //GL11.glTexCoord2f(texCords[1][0], texCords[1][1]);
            GL11.glVertex3f(loc[0] + points[1][0],loc[1] + points[1][1],loc[2] + points[1][2]);
            //GL11.glTexCoord2f(texCords[2][0], texCords[2][1]);
            GL11.glVertex3f(loc[0] + points[2][0],loc[1] + points[2][1],loc[2] + points[2][2]);
            //GL11.glTexCoord2f(texCords[3][0], texCords[3][1]);
            GL11.glVertex3f(loc[0] + points[3][0],loc[1] + points[3][1],loc[2] + points[3][2]);
        GL11.glEnd();
    }
    
}