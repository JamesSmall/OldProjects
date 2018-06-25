/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package World.Entities.Deprecated;

import Graphics.Base.ScreenGraphic;
import Holder.Texture;
import Holder.TextureTypes.SingleColorTexture;
import World.Entities.AbstractGraphicEntry;
import java.awt.Color;
import org.lwjgl.LWJGLException;
import org.lwjgl.util.vector.Vector2f;

/**
 *
 * @author Allazham
 */
public abstract class Animal extends AbstractGraphicEntry{
    private Vector2f Graphicocation;
    private Texture Chest,Head;
    private double DirectionAngle = 0;
    private double CurrHeadDegree = 0,HeadDegree = 0;
    private double angleConvert = 0;
    private float Size = .25f;
    private float CurrHeadDistance = 5,HeadDistance = 5;
    private float distanceConvert = .01f;
    private int delta;
    public Animal(){
        this.Graphicocation = super.getGraphicalLocation();
        this.Chest = SingleColorTexture.getColoredTexture(Color.red);
        this.Head = SingleColorTexture.getColoredTexture(Color.blue);
    }
    @Override
    protected void render() throws LWJGLException {
        //Chest
        this.Chest.bind();
        ScreenGraphic.DrawAngledTexturedSquare(this.Graphicocation.x, this.Graphicocation.y, Size*5, Size*5,Math.PI-this.DirectionAngle,this.Chest.getLesserX(),this.Chest.getLesserY(),this.Chest.getGreaterX(),this.Chest.getGreaterY());
        this.renderHead();
    }
     public void renderHead() throws LWJGLException{
        this.Head.bind();
            if(this.HeadDistance != this.CurrHeadDistance){
                if(this.CurrHeadDistance > this.HeadDistance){
                    if(this.CurrHeadDistance - this.distanceConvert*this.delta < this.HeadDistance){
                        this.CurrHeadDistance = this.HeadDistance;
                    }
                    else{
                        this.CurrHeadDistance -= this.distanceConvert*this.delta;
                    }
                }
                else{
                    if(this.CurrHeadDistance + this.distanceConvert*this.delta > this.HeadDistance){
                        this.CurrHeadDistance = this.HeadDistance;
                    }
                    else{
                        this.CurrHeadDistance += this.distanceConvert*this.delta;
                    }
                }
            }
            //degree
            if(this.HeadDegree != this.CurrHeadDegree){
                if(this.CurrHeadDegree > this.HeadDegree){
                    if(this.CurrHeadDegree - this.angleConvert*this.delta < this.HeadDegree){
                        this.CurrHeadDegree = this.HeadDegree;
                    }
                    else{
                        this.CurrHeadDegree -= this.angleConvert*this.delta;
                    }
                }
                else{
                    if(this.CurrHeadDegree + this.angleConvert*this.delta > this.HeadDegree){
                        this.CurrHeadDegree = this.HeadDegree;
                    }
                    else{
                        this.CurrHeadDegree += this.angleConvert*this.delta;
                    }
                }
            }
        this.RenderHead(this.Graphicocation.x, this.Graphicocation.y, Size,this.CurrHeadDistance*this.Size, this.CurrHeadDegree+this.DirectionAngle,this.Head);
    }
    
    public void RenderHead(float x, float y, float size,float distance,double angle,Texture hand)throws LWJGLException{
        float xx = ((float)Math.sin(angle))*distance,yy = ((float)Math.cos(angle))*distance;
        ScreenGraphic.DrawAngledTexturedSquare(x+xx, y+yy, size, size,-angle,hand.getGreaterX(),hand.getGreaterY(),hand.getLesserX(),hand.getLesserY());
    }
}
