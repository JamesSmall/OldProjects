/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Graphics;

/**
 *last Edited: 2/25/2013
 * @author james small
 */
public abstract class NonButtonUserAction{
    private boolean Active = true;
    private long lastAttempt = 0;
    private static final long sleep = 100;
    public void setActive(boolean active){
        this.Active = active;
    }
    public boolean isActive(){
        return this.Active;
    }
    protected void action(){
        if(this.Active && this.lastAttempt > (this.lastAttempt - System.currentTimeMillis())){
            this.reaction();
        }   this.lastAttempt = System.currentTimeMillis();
    }
    public abstract void reaction();
}
