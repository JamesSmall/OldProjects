/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package World.Entities.Basic;

import Save.EntityInfo;
import Save.SaveObjectPackage;
import World.Entities.Bodies.PreBuilt.Npc;
import World.Entry;
import java.util.List;
import org.lwjgl.util.vector.Vector2f;

/**
 *
 * @author Allazham
 */
public class Follower extends Npc{
    private Entry Player = null;
    private final float speed =.005f;
    private final Vector2f Direction = new Vector2f();
    public Follower(){
    }
    public Follower(EntityInfo e){
        super(e);
    }
    @Override
    public void PeronalMovement(int delta) {
        Vector2f loc = new Vector2f();
        int i;
        this.Direction.x = (float)Math.sin(this.getMasterAngle())*speed;
        this.Direction.y = (float)Math.cos(this.getMasterAngle())*speed;
        for(i = 0; i < delta;i++){
            Vector2f.add(this.Direction, super.getLocationVector(), loc);
            if(super.checkLocation(loc)){
                super.setLocation(loc);
            }
            else{
                break;
            }
        }
    }
    
    @Override
    public void Update(int delta) {
        Entry e;
        int i;
        if(this.Player == null){
            List<Entry> p = super.getCell().getPlayers();
            if(!p.isEmpty()){
                this.Player = p.get(0);
            }
        }
        if(this.Player != null){
            Vector2f p = super.getLocationVector();
            Vector2f m = Player.getLocationVector();
            double angle = - Math.atan2(p.y-m.y, p.x-m.x)+Math.PI*3/2;
            super.setMasterAngle(angle);
        }
        List<Entry> ez = super.getCell().getEntriesInArea(super.getLocationX(),super.getLocationY(), super.getScale()*1.1f);
        for(i = 0; i < ez.size();i++){
            e = ez.get(i);
            if(!e.equals(this)){
                //if(){
                    Vector2f Directions = new Vector2f();
                    Vector2f p = super.getLocationVector();
                    Vector2f m = Player.getLocationVector();
                    double angle = - Math.atan2(p.y-m.y, p.x-m.x)+Math.PI*3/2;
                    Directions.x = (float)Math.sin(angle)*.01f;
                    Directions.y = (float)Math.cos(angle)*.01f;
                    e.addVelocity(Directions);
                //}
            }
        }
    }
    @Override
    public SaveObjectPackage getSave() {
        return this.getBasicPackage();
    }

    @Override
    public String getSaveIdentity() {
        return "Follower";
    }

    @Override
    public void disposeEntry() {
        
    }
    @Override
    public void disposeGraphics() {
        
    }
    @Override
    public boolean isPersistent() {
        return false;
    }
}
