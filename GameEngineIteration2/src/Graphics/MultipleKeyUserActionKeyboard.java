/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Graphics;

/**
 *last Edited: 2/25/2013
 * @author james small
 */
public abstract class MultipleKeyUserActionKeyboard extends UserActionKeyboard {
    private int[] keys = new int[0];
    public void setKeys(int[] keys){
        this.keys = keys;
    }
    public void addKey(int key){
        int i;
        int[] keys = this.keys;
        this.keys = new int[keys.length+1];
        for(i = 0; i < keys.length;i++){
            this.keys[i] = keys[i];
        }
        this.keys[this.keys.length-1] = key;
    }
    public void addKeys(int[] key){
        int[] keys = new int[this.keys.length+key.length];
        int i;
        for(i = 0; i < this.keys.length;i++){
            keys[i] = this.keys[i];
        }
        
    }
    public int[] getKeys(){
        return this.keys;
    }
    @Override
    protected void action(int key) {
        int i;
        if(this.isActive() && !this.isIgnoreInput()){
            for(i = 0;i < keys.length;i++){
                if(keys[i] == key){
                    this.Reaction(key);
                    return;
                }
            }
        }
        else if(this.isActive()&& this.isIgnoreInput()){
            this.Reaction(key);
        }
    }
    protected abstract void Reaction(int key);
}
