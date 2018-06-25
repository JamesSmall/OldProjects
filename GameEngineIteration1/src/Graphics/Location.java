/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Graphics;

/**
 *
 * @author Allazham
 */
public interface Location {
    public void setLocationX(float x);
    public void setLocationY(float y);
    public void setLocationZ(float z);
    public void setLocation(float x,float y,float z);
    public float getLocationX();
    public float getLocationY();
    public float getLocationZ();
    public float[] getLocation();
}
