/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package World.Entities.Deprecated;

import Graphics.Utils.Camera2D;
import Graphics.Base.ScreenGraphic;
import Holder.MasterLoad;
import Holder.Texture;
import World.Entities.AbstractGraphicEntry;
import java.util.LinkedList;
import org.lwjgl.LWJGLException;
import org.lwjgl.Sys;
import org.lwjgl.util.vector.Vector2f;

/**
 *
 * @author Allazham
 */
public abstract class Npc extends AbstractGraphicEntry{
//    private LinkedList<requestLocation> LeftHandAnimi = new LinkedList();
    
    private static final double TWOPI = Math.PI*2;
    private double DirectionAngle = 0;
    
    private float HandDistanceL = 3f,HandDistanceR = 3f;
    private double HandDegreeL = -.5d,HandDegreeR = .5d;
    
    private double CurrhandDegreeL = -.5d, CurrhandDegreeR = .5d;
    private float CurrhandDistanceL = 3f,CurrhandDistanceR = 3f;
    
    //convert distance
    private float distanceConvertL = .01f,distanceConvertR = .01f;
    private double angleConvertL = .01,angleConvertR = .01;

    //time based calculations
    private int delta;
    private long TimePast = -1,lastFrame = -1;
    
    //private boolean BodyAnimating = false;
    private final float Size = .25f;
    private final Vector2f GraphicLocation;
    private Texture handL,handR,Chest,Head;
    public Npc(){
        GraphicLocation = super.getGraphicalLocation();
        Head = MasterLoad.getImage("NakedHead");
        Chest =  MasterLoad.getImage("NakedChest");
        handL = MasterLoad.getImage("NakedHand");
        handR = MasterLoad.getImage("NakedHand");
    }
    @Override
    protected void render() throws LWJGLException {
        Vector2f cam = Camera2D.getCamera().getMousePointInWorld();
        TimePast = Sys.getTime() - TimePast;
        long time = Sys.getTime();
        delta = (int) (time - lastFrame);
        if(delta > 30){
            delta = 0;
        }
        lastFrame = time;
        
        this.DirectionAngle = Math.atan2(cam.y-this.GraphicLocation.y, -cam.x+this.GraphicLocation.x)+Math.PI*3/2;
        //make within resonable range
        int distConvert = (int) Math.floor(this.DirectionAngle/TWOPI);
        this.DirectionAngle = this.DirectionAngle - TWOPI*distConvert;
        
        //Chest
        this.Chest.bind();
        ScreenGraphic.DrawAngledTexturedSquare(this.GraphicLocation.x, this.GraphicLocation.y, Size*5, Size*5,Math.PI-this.DirectionAngle,this.Chest.getLesserX(),this.Chest.getLesserY(),this.Chest.getGreaterX(),this.Chest.getGreaterY());
        
        //head
        this.Head.bind();
        ScreenGraphic.DrawAngledTexturedSquare(this.GraphicLocation.x, this.GraphicLocation.y, Size*3, Size*3,Math.PI-this.DirectionAngle,this.Head.getLesserX(),this.Head.getLesserY(),this.Head.getGreaterX(),this.Head.getGreaterY());

        //render hands
        this.renderHandsL();
        this.renderHandsR();
    }
    public void renderHandsL() throws LWJGLException{
        this.handL.bind();
            if(this.HandDistanceL != this.CurrhandDistanceL){
                if(this.CurrhandDistanceL > this.HandDistanceL){
                    if(this.CurrhandDistanceL - this.distanceConvertL*this.delta < this.HandDistanceL){
                        this.CurrhandDistanceL = this.HandDistanceL;
                    }
                    else{
                        this.CurrhandDistanceL -= this.distanceConvertL*this.delta;
                    }
                }
                else{
                    if(this.CurrhandDistanceL + this.distanceConvertL*this.delta > this.HandDistanceL){
                        this.CurrhandDistanceL = this.HandDistanceL;
                    }
                    else{
                        this.CurrhandDistanceL += this.distanceConvertL*this.delta;
                    }
                }
            }
            //degree
            if(this.HandDegreeL != this.CurrhandDegreeL){
                if(this.CurrhandDegreeL > this.HandDegreeL){
                    if(this.CurrhandDegreeL - this.angleConvertL*this.delta < this.HandDegreeL){
                        this.CurrhandDegreeL = this.HandDegreeL;
                    }
                    else{
                        this.CurrhandDegreeL -= this.angleConvertL*this.delta;
                    }
                }
                else{
                    if(this.CurrhandDegreeL + this.angleConvertL*this.delta > this.HandDegreeL){
                        this.CurrhandDegreeL = this.HandDegreeL;
                    }
                    else{
                        this.CurrhandDegreeL += this.angleConvertL*this.delta;
                    }
                }
            }
        this.RenderHand(this.GraphicLocation.x, this.GraphicLocation.y, Size,this.CurrhandDistanceL*this.Size, this.CurrhandDegreeL+this.DirectionAngle,this.handL, true);
    }
    public void renderHandsR() throws LWJGLException{
        this.handR.bind();
            if(this.HandDistanceR != this.CurrhandDistanceR){
                if(this.CurrhandDistanceR > this.HandDistanceR){
                    if(this.CurrhandDistanceR - this.distanceConvertR*this.delta < this.HandDistanceR){
                        this.CurrhandDistanceR = this.HandDistanceR;
                    }
                    else{
                        this.CurrhandDistanceR -= this.distanceConvertR*this.delta;
                    }
                }
                else{
                    if(this.CurrhandDistanceR + this.distanceConvertR*this.delta > this.HandDistanceR){
                        this.CurrhandDistanceR = this.HandDistanceR;
                    }
                    else{
                        this.CurrhandDistanceR += this.distanceConvertR*this.delta;
                    }
                }
            }
            //degree
            if(this.HandDegreeR != this.CurrhandDegreeR){
                if(this.CurrhandDegreeR > this.HandDegreeR){
                    if(this.CurrhandDegreeR - this.angleConvertR*this.delta < this.HandDegreeR){
                        this.CurrhandDegreeR = this.HandDegreeR;
                    }
                    else{
                        this.CurrhandDegreeR -= this.angleConvertR*this.delta;
                    }
                }
                else{
                    if(this.CurrhandDegreeR + this.angleConvertR*this.delta > this.HandDegreeR){
                        this.CurrhandDegreeR = this.HandDegreeR;
                    }
                    else{
                        this.CurrhandDegreeR += this.angleConvertR*this.delta;
                    }
                }
        }
        this.RenderHand(this.GraphicLocation.x, this.GraphicLocation.y, Size,this.CurrhandDistanceR*this.Size, this.CurrhandDegreeR+this.DirectionAngle,this.handR, false);
    }
    public void RenderHand(float x, float y, float size,float distance,double angle,Texture hand,boolean textureFlipped)throws LWJGLException{
        float xx = ((float)Math.sin(angle))*distance,yy = ((float)Math.cos(angle))*distance;
        if(textureFlipped){
            ScreenGraphic.DrawAngledTexturedSquare(x+xx, y+yy, size, size,-angle,hand.getGreaterX(),hand.getGreaterY(),hand.getLesserX(),hand.getLesserY());
        }
        else{
            ScreenGraphic.DrawAngledTexturedSquare(x+xx, y+yy, size, size,-angle,hand.getLesserX(),hand.getGreaterY(),hand.getGreaterX(),hand.getLesserY());
        }
    }
    /*
     * 
     * angle distance
     * 
     */
    public void setRHandAngle(double angle){
        this.CurrhandDegreeL = angle;
        this.HandDegreeL = angle;
    }
    public void setLHandAngle(double angle){
        this.CurrhandDegreeR = angle;
        this.HandDegreeR = angle;
    }
    public void setRHandDistance(double angle){
        this.CurrhandDegreeL = angle;
        this.HandDegreeL = angle;
    }
    public void setLHandDistance(double angle){
        this.CurrhandDegreeR = angle;
        this.HandDegreeR = angle;
    }
    
    
    public void MoveRHandAngleTo(double angle){
        this.HandDegreeR = angle;
    }
    public void MoveLHandAngleTo(double angle){
        this.HandDegreeL = angle;
    }
    public void MoveRHandDistanceTo(float angle){
        this.HandDistanceR = angle;
    }
    public void MoveLHandDistanceTo(float angle){
        this.HandDistanceL = angle;
    }
    
    
    public void setAngleMoveSpeedL(double speed){
        this.angleConvertL = speed;
    }
    public void setAngleMoveSpeedR(double speed){
        this.angleConvertR = speed;
    }
    public void setDistanceMoveSpeedL(float speed){
        this.distanceConvertL = speed;
    }
    public void setDistanceMoveSpeedR(float speed){
        this.distanceConvertR = speed;
    }
    
    
    public boolean isAnimatingLHand(){
        return (this.CurrhandDegreeL != this.HandDegreeL || this.HandDistanceL != this.CurrhandDistanceL);
    }
    public boolean isAnimatingRHand(){
        return (this.CurrhandDegreeR != this.HandDegreeR || this.HandDistanceR != this.CurrhandDistanceR);
    }
    
    public float getHandDistanceLeft(){
        return this.CurrhandDistanceL;
    }
    public float getHandDistanceRight(){
        return this.CurrhandDistanceR;
    }
    public double getHandAngleLeft(){
        return this.CurrhandDegreeL;
    }
    public double getHandAngleRight(){
        return this.CurrhandDegreeR;
    }
    /*
     * texture management
     */
    
    public void setLHandTexture(Texture LH){
        this.handL = LH;
    }
    public void setRHandTexture(Texture RH){
        this.handR = RH;
    }
    public void setChestTexture(Texture Chest){
        this.Chest = Chest;
    }
    public void setHeadTexture(Texture head){
        this.Head = head;
    }
    public Texture getLHandTexture(){
        return this.handL;
    }
    public Texture getRHandTexture(){
        return this.handR;
    }
    public Texture getChestTexture(){
        return this.Chest;
    }
    public Texture getHeadTexture(){
        return this.Head;
    }
    public void handleRequestedAnimation(){
        
    }
}
