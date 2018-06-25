/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package World.Entities.Bodies;

import Save.EntityInfo;
import World.Entities.AbstractGraphicEntry;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import org.lwjgl.LWJGLException;
import org.lwjgl.util.vector.Vector2f;

/**
 *
 * @author Allazham
 */
public abstract class Body extends AbstractGraphicEntry{
    private final ArrayList<BodyPart> parts = new ArrayList();
    private final ArrayList<Animation> animations = new ArrayList();
    private static final Order order = new Order();
    private long time = 0;
    private double angle = 0;
    private final Vector2f GraphicLocation;
    public Body(){
        this.GraphicLocation = super.getGraphicalLocation();
    }
    public Body(EntityInfo e){
        super(e);
        this.GraphicLocation = super.getGraphicalLocation();
    }
    
    public Body(EntityInfo e,List<BodyPart> parts){
        super(e);
        this.parts.addAll(parts);
        this.GraphicLocation = super.getGraphicalLocation();
    }
    public ArrayList<BodyPart> getBodyParts(){
        return new ArrayList(this.parts);
    }
    @Override
    protected void render() throws LWJGLException {
        if(time != 0){
            long currTime = System.currentTimeMillis();
            long left = currTime - this.time;
            for(BodyPart B: this.parts){
                B.render(GraphicLocation, super.getScale(),this.angle, left);
            }
            this.time = currTime;
        }
        else{
            this.time = System.currentTimeMillis();
        }
    }
    public void lookAt(Vector2f v){
        this.lookAt(v.x, v.y);
    }
    public void lookAt(float x, float y){
        Vector2f p = super.getLocationVector();
        this.angle = - Math.atan2(p.y-y, p.x-x)+Math.PI*3/2;
    }
    public void setMasterAngle(double angle){
        this.angle = angle;
    }
    public double getMasterAngle(){
        return this.angle;
    }
    public void addBodyPart(BodyPart Head) {
        this.parts.add(Head);
        Collections.sort(parts,order);
    }
    public void addAnimation(Animation a){
        if(this.getAnimationByName(a.getName())==null){
            this.animations.add(a);
        }
    }
    public boolean activateAnimation(String name){
        Animation a = this.getAnimationByName(name);
        if(a != null){
            a.AnimateBody(this);
            return true;
        }
        return false;
    }
    public Animation getAnimationByName(String name){
        for(Animation a:this.animations){
            if(a.getName().equals(name)){
                return a;
            }
        }
        return null;
    }
    public boolean isAnimating(){
        for(BodyPart p:this.parts){
            if(p.isAnimating()){
                return true;
            }
        }
        return false;
    }
    private static class Order implements Comparator<BodyPart>{
        @Override
        public int compare(BodyPart o1, BodyPart o2) {
            return(o1.renderOrder<o2.renderOrder ? -1 : (o1.renderOrder==o2.renderOrder ? 0 : 1));
        }
    }
}
