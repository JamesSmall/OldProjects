/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package World.Entities.Bodies;

import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Allazham
 */
public class AnimationSequence {
    private final LinkedList<Location> locations = new LinkedList();
    AnimationSequence(List<Location> loc){
        this.locations.addAll(loc);
    }
    boolean updateBodyPart(BodyPart BP){
        if(!this.locations.isEmpty()){
            Location l = this.locations.removeFirst();
            BP.setNextAngle(l.angle);
            BP.setNextDistnace(l.distance);
            return true;
        }
        return false;
    }
}
