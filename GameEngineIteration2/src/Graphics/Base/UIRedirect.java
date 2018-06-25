package Graphics.Base;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author Allazham
 */
public interface UIRedirect {
    void setX(float x);
    void setY(float y);
    void setWidth(float width);
    void setHeight(float height);
    void setUIManager(UIManager ui);
    UIManager getUIManager();
    float getX();
    float getY();
    float getWidth();
    float getHeight();
    void ProcessChanges(UIManager uim,float x, float y, float width, float height,int Orientation);
}
