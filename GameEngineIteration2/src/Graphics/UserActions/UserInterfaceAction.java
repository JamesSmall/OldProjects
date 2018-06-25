/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Graphics.UserActions;

import Graphics.Utils.Camera2D;

/**
 *
 * @author Allazham
 */
public abstract class UserInterfaceAction implements UserAction{
    private UIZone uiz;
    private boolean created = false;
    private boolean disabled = false;
    public UserInterfaceAction(UIZone uiz){
        this.uiz = uiz;
    }
    @Override
    public boolean isDisabled() {
        if(this.disabled == true){
            return true;
        }
        return !uiz.isInside(Camera2D.getCamera().getMousePointOnGraphics());
    }
    public void setDisabeld(boolean disabled){
        this.disabled = disabled;
    }
    public synchronized void setUIZone(UIZone z){
        if(this.uiz != null && this.created){
            UIZonesManager.removeUIZone(uiz);
        }
        this.uiz = z;
        if(this.uiz != null && this.created){
            UIZonesManager.addUIZone(uiz);
        }
    }
    public UIZone getUIZone(){
        return this.uiz;
    }
    @Override
    public synchronized void create(){
        this.created = true;
        UIZonesManager.addUIZone(uiz);
    }
    @Override
    public synchronized void dispose(){
        this.created = false;
        UIZonesManager.removeUIZone(uiz);
    }
}
