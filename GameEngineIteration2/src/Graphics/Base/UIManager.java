/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Graphics.Base;

/**
 *
 * @author Allazham
 */
public interface UIManager {
    void setX(float x);
    void setY(float y);
    void setWidth(float width);
    void setHeight(float height);
    float getX();
    float getY();
    float getWidth();
    float getHeight();
    void setOrientation(int ort);
    int getOrientation();
}
