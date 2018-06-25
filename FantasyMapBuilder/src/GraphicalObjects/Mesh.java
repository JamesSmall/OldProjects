/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GraphicalObjects;

import java.awt.Color;
import java.awt.image.BufferedImage;

/**
 *
 * @author Allazham
 * the image of an object, this interface allows edits to images
 */
public interface Mesh {
    public void setImage(BufferedImage img);
    public BufferedImage getImage();
    
    public void PaintCircle(double x,double y, double distance, int Color);
    public void PaintCircle(double x, double y, double distance,Color c);
    
    public void PaintCircle(double x,double y, double distance, int Color,boolean[][] guide);
    public void PaintCircle(double x, double y, double distance,Color c,boolean[][] guide);

    public void PaintCircle(double x,double y, double distance, int Color,int[][] guide);
    public void PaintCircle(double x, double y, double distance,Color c,int[][] guide);
    
    public void PaintSquare(double x,double y, double distance, int Color);
    public void PaintSquare(double x, double y, double distance,Color c);
    
    public void PaintSquare(double x,double y, double distance, int Color,boolean[][] guide);
    public void PaintSquare(double x, double y, double distance,Color c, boolean[][] guide);
    
    public void PaintSquare(double x,double y, double distance, int Color,int[][] guide);
    public void PaintSquare(double x, double y, double distance,Color c, int[][] guide);
    
    public int[] getDimension();
    public int getImageWidth();
    public int getImageHeight();
}
