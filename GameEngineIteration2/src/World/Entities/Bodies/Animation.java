/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package World.Entities.Bodies;

import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author Allazham
 */
public class Animation {
    private ArrayList<Package> location = new ArrayList();
    private String name;
    public Animation(String name){
        this.name = name;
    }
    public void AnimateBody(Body b){
        for(Package p:this.location){
            for(BodyPart bb:b.getBodyParts()){
                if(p.bodyPart.equals(bb.getName())){
                    bb.setAnimation(new AnimationSequence(p.loc));
                }
            }
        }
    }
    public void addAnimationSequence(List<Location> bodypart,String name){
        this.location.add(new Package(bodypart,name));
    }
    public void addAnimationSequence(double[] angle, float[] distance,String name){
        if(angle.length != distance.length){
            throw new UnsupportedOperationException("angle and distance must have equal lengths");
        }
        ArrayList<Location> loc = new ArrayList();
        int i;
        for(i = 0; i < angle.length;i++){
           loc.add(new Location(angle[i],distance[i]));
        }
        this.location.add(new Package(loc,name));
    }
    public String getName(){
        return this.name;
    }
    private class Package{
        Package(List<Location> loc,String name){
            this.bodyPart = name;
            this.loc.addAll(loc);
        }
        private String bodyPart;
        private ArrayList<Location> loc = new ArrayList();
    }
}
