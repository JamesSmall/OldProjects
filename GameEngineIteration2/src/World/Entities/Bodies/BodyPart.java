/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package World.Entities.Bodies;

import Graphics.Base.ScreenGraphic;
import Holder.Texture;
import org.lwjgl.LWJGLException;
import org.lwjgl.util.vector.Vector2f;

/**
 *
 * @author Allazham
 */
public final class BodyPart {
    private boolean flipTexture = false;
    private String name;
    private Texture t;
    int renderOrder;
    private double angle,currAngle,angleVsSeconds;
    private float distance,currDistance,distnaceVsSeconds,size;
    private AnimationSequence animi;
    public BodyPart(Texture t,int RenderOrder,String name,float size,float distance,double angle){
        this.t = t;
        this.renderOrder = RenderOrder;
        this.angle = angle;
        this.currAngle = angle;
        this.currDistance = distance;
        this.distance = distance;
        this.setAnglePerSecond(1);
        this.setDistancePerSecond(1f);
        this.size = size;
        this.name = name;
    }
    public BodyPart(Texture t,int RenderOrder,String name,float size,float distance,double angle,double anglePerSeconds,float distancePerSeconds){
        this.t = t;
        this.renderOrder = RenderOrder;
        this.angle = angle;
        this.currAngle = angle;
        this.currDistance = distance;
        this.distance = distance;
        this.setAnglePerSecond(anglePerSeconds);
        this.setDistancePerSecond(distancePerSeconds);
        this.size = size;
        this.name = name;
    }
    //0.001 miliseconds to seconds
    void render(Vector2f GraphicLocation, float Size,double angle,long timePast)throws LWJGLException{
        //if(lastTime != 0){
            float sizeValue = this.size * Size;
            if(this.distance != this.currDistance){
                if(distance < currDistance){
                    currDistance -= timePast*this.distnaceVsSeconds;
                    if(distance > currDistance){
                        currDistance = distance;
                    }
                }
                else{
                    currDistance += timePast*this.distnaceVsSeconds;
                    if(distance < currDistance){
                        currDistance = distance;
                    }
                }
            }
            if(this.angle != this.currAngle){
                if(this.angle < currAngle){
                    currAngle -= timePast*this.angleVsSeconds;
                    if(this.angle > currAngle){
                        currAngle = this.angle;
                    }
                }
                else{
                    currAngle += timePast*this.angleVsSeconds;
                    if(this.angle < currAngle){
                        currAngle = this.angle;
                    }
                }
            }
            if(this.angle == this.currAngle && this.distance == this.currDistance){
                if(this.animi != null){
                    if(!animi.updateBodyPart(this)){
                        this.animi = null;
                    }
                }
            }
        //}
        this.Render(GraphicLocation.x, GraphicLocation.y, sizeValue,this.currDistance*sizeValue, this.currAngle+angle);
    }
    private void Render(float x, float y, float size,float distance,double angle)throws LWJGLException{
        float xx = ((float)Math.sin(angle))*distance,yy = ((float)Math.cos(angle))*distance;
        this.t.bind();
        if(!this.flipTexture){
            ScreenGraphic.DrawAngledTexturedSquare(x+xx, y+yy, size, size,-angle,t.getLesserX(),t.getGreaterY(),t.getGreaterX(),t.getLesserY());
        }
        else{
            ScreenGraphic.DrawAngledTexturedSquare(x+xx, y+yy, size, size,-angle,t.getGreaterX(),t.getGreaterY(),t.getLesserX(),t.getLesserY());
        }
    }
    public void setDistancePerSecond(float distance){
        this.distnaceVsSeconds = distance*.001f;
    }
    public void setAnglePerSecond(double angle){
        this.angleVsSeconds = angle*.001d;
    }
    public void setNextAngle(double angle){
        this.angle = angle;
    }
    public void setDistance(float distance){
        this.distance = distance;
        this.currDistance = distance;
    }
    public void setAngle(double angle){
        this.angle = angle;
        this.currAngle = angle;
    }
    public void setNextDistnace(float distance){
        this.distance = distance;
    }
    public void setAnimation(AnimationSequence a){
        this.animi = a;
    }
    public boolean isAnimating(){
        return this.currAngle != this.angle || this.distance != this.currDistance;
    }
    public String getName(){
        return this.name;
    }
    public void setName(String name){
        this.name = name;
    }
    public void setTextureFlipped(boolean flipped){
        this.flipTexture = flipped;
    }
    public int getRenderOrder(){
        return this.renderOrder;
    }
    public void setRenderOrder(int renderOrder){
        this.renderOrder = renderOrder;
    }
}
