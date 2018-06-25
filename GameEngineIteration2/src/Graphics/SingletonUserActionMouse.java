/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Graphics;

/**
 *last Edited: 2/25/2013
 * @author james small
 */
public abstract class SingletonUserActionMouse extends UserActionMouse {
    private int key = -1;
    public void setKey(int key){
        this.key = key;
    }
    public int getKey(){
        return this.key;
    }
    @Override
    protected void action(int key){
        if((this.key == key||this.isIgnoreInput())&&this.isActive()){
            this.Reaction();
        }
    }
    protected abstract void Reaction();
}
