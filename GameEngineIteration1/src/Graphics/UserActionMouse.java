/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Graphics;

/*
 * @author james small
 * timeEdited:2/11/2013
 */
public abstract class UserActionMouse {
    private boolean Active = true,ignoreInputFunction = false;
    public void setIgnoreInput(boolean ignore){
        this.ignoreInputFunction = ignore;
    }
    public boolean isIgnoreInput(){
        return this.ignoreInputFunction;
    }
    public void setActive(boolean active){
        this.Active = active;
    }
    public boolean isActive(){
        return this.Active;
    }
    protected abstract void action(int key);
}