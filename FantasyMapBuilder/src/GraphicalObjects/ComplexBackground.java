/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GraphicalObjects;

import Forum.GraphicConditions;
import Utils.ImageEditor;
import Utils.SaveManager.SaveObjectPackage;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

/**
 *
 * @author Allazham
 */
public class ComplexBackground extends Background{
    private double rollX,rollY;
    private double imageStrech = 1;
    private double scale = 1;
    private BufferedImage imgSample;
    private BufferedImage img;
    public ComplexBackground(){
        
    }
    private ComplexBackground(double imageStrech, BufferedImage img,BufferedImage sample){
        this.imageStrech = imageStrech;
        this.img = img;
        this.imgSample = sample;
    }
    
    @Override
    public void renderMap(Graphics2D g, GraphicConditions c) {
        scale = c.getScale();
        super.setImageX(c.getX());
        super.setImageY(c.getY());
        if(c.getWidthRegular() > img.getWidth() || c.getHeightRegular() > img.getHeight()){
            rescaleImage(c);
        }
        g.drawImage(img,0,0,null);
    }
    private void rescaleImage(GraphicConditions c){
        Graphics2D g;
        int holdValue;
        int i;
        while(this.img.getWidth() < c.getWidth()){
            holdValue = img.getWidth();
            img = ImageEditor.resizeImage(img, 0,0, this.imgSample.getWidth(), 0);
            g = img.createGraphics();
            i = 0;
            while(i < img.getHeight()){
                g.drawImage(this.imgSample, holdValue,i ,null);
                i += this.imgSample.getHeight();
            }
            
        }
        while(this.img.getHeight() < c.getHeight()){
            holdValue = img.getHeight();
            img = ImageEditor.resizeImage(img, 0,0,0, this.imgSample.getHeight());
            g = img.createGraphics();
            i = 0;
            while(i < img.getWidth()){
                g.drawImage(this.imgSample, i,holdValue ,null);
                i += this.imgSample.getWidth();
            }
        }
    }
    @Override
    public void setImage(BufferedImage img) {
        this.img = img;
        this.imgSample = img;
        repaint();
    }

    @Override
    public BufferedImage getImage() {
        return this.img;
    }

    @Override
    public void PaintCircle(double x, double y, double distance, int Color) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void PaintCircle(double x, double y, double distance, Color c) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void PaintCircle(double x, double y, double distance, int Color, boolean[][] guide) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void PaintCircle(double x, double y, double distance, Color c, boolean[][] guide) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void PaintCircle(double x, double y, double distance, int Color, int[][] guide) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void PaintCircle(double x, double y, double distance, Color c, int[][] guide) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void PaintSquare(double x, double y, double distance, int Color) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void PaintSquare(double x, double y, double distance, Color c) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void PaintSquare(double x, double y, double distance, int Color, boolean[][] guide) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void PaintSquare(double x, double y, double distance, Color c, boolean[][] guide) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void PaintSquare(double x, double y, double distance, int Color, int[][] guide) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void PaintSquare(double x, double y, double distance, Color c, int[][] guide) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int[] getDimension() {
        return new int[]{this.img.getWidth(),this.img.getHeight()};
    }

    @Override
    public int getImageWidth() {
        return this.img.getWidth();
    }

    @Override
    public int getImageHeight() {
        return this.img.getHeight();
    }

    @Override
    public ComplexBackground getNewInstance() {
        return new ComplexBackground(this.scale,img,imgSample);
    }
    @Override
    public boolean isInsideHitBox(double x, double y) {
        return true;
    }

    @Override
    public double[][] getSurroundingPoints() {
        return null;
    }

    @Override
    public void Destroy() {
        
    }

    @Override
    public void setImage(BufferedImage sample, BufferedImage full) {
        this.imgSample = sample;
        this.img = full;
        repaint();
    }

    @Override
    public BufferedImage getSampleImage() {
        return this.imgSample;
    }

    @Override
    public SaveObjectPackage getSaveObjectPackage() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
