/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Graphics.Base.Graphic;

import Holder.Texture;
import org.lwjgl.LWJGLException;
import org.lwjgl.Sys;

/**
 *
 * @author Allazham
 */
public abstract class Particle {
    int Updates;
    Particle(int update){
        this.Updates = update;
    }
    abstract void render(boolean update) throws LWJGLException;
    boolean check(){
        if(this.Updates > 0){
            this.Updates--;
        }
        if(this.Updates == 0){
            return true;
        }
        return false;
    }
    public int getRemainingTick(){
        if(this.Updates < 0){
            return Integer.MAX_VALUE;
        }
        return this.Updates;
    }
    public void Destroy(){
        this.Updates = 0;
    }
}
