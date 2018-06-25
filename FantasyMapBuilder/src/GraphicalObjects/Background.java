/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GraphicalObjects;

import java.awt.image.BufferedImage;

/**
 *
 * @author Allazham
 */
public abstract class Background extends GraphicsObject implements Mesh{
    @Override
    public abstract Background getNewInstance();
    public abstract void setImage(BufferedImage sample,BufferedImage full);
    public abstract BufferedImage getSampleImage();
}
