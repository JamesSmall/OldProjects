/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Forum;

/**
 *
 * @author Allazham
 */
public enum GraphicConditions {
    Instance;
    private int Width = 0, Height = 0,x = 0,y = 0;
    private double scale = 0;
    private boolean showNodes = true;
    public void setShowNodes(boolean node){
        this.showNodes = node;
    }
    public boolean isShowingNodes(){
        return this.showNodes;
    }
    public void setWidth(int Width){
        this.Width = Width;
    }
    public void setHeight(int Height){
        this.Height = Height;
    }
    public void setX(int x){
        this.x = x;
    }
    public void setY(int y){
        this.y = y;
    }
    public void setScale(double scale){
        this.scale = scale;
    }
    public int getWidthRegular(){
        return this.Width;
    }
    public int getHeightRegular(){
        return this.Height;
    }
    public int getWidth(){
        return (int) (this.Width*this.scale);
    }
    public int getHeight(){
        return (int) (this.Height*this.scale);
    }
    public int getX(){
        return this.x;
    }
    public int getY(){
        return this.y;
    }
    public double getScale(){
        return this.scale;
    }
    public void setConditions(int x, int y, int Width,int Height){
        this.x = x;
        this.y = y;
        this.Width = Width;
        this.Height = Height;
    }
    public void setConditions(int x, int y, int Width,int Height,double scale){
        this.x = x;
        this.y = y;
        this.Width = Width;
        this.Height = Height;
        this.scale = scale;
    }
    public int getScreenCenterX(){
        return (int) ((this.x-(this.getWidth()/2))/this.scale);
    }
    public int getScreenCenterY(){
        return (int) ((this.y-(this.getHeight()/2))/this.scale);
    }
    public int getStartingPointX(double x,double Width){
        return (int) (-this.getScreenCenterX()+((x-Width/2)/scale));
    }
    public int getStartingPointY(double y,double Height){
        return (int) (-this.getScreenCenterY()+((y-Height/2)/scale));
    }
    public int getStartingPointXUnscaled(double x,double Width){
        return (int) (-this.getScreenCenterX()+((x/scale-Width/2)));
    }
    public int getStartingPointYUnscaled(double y,double Height){
        return (int) (-this.getScreenCenterY()+((y/scale-Height/2)));
    }
    public int MouseToScreenCordsX(double x){
        return (int) ((x+this.getScreenCenterX())*scale);
    }
    public int MouseToScreenCordsY(double y){
        return (int) ((y+this.getScreenCenterY())*scale);
    }
}
