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
public abstract class UserWorldAction implements UserAction{
    private static final Camera2D cam = Camera2D.getCamera();
    private boolean disabled = false;
    public void setDisabled(boolean disabled){
        this.disabled = disabled;
    }
    @Override
    public boolean isDisabled(){
        if(disabled){
            return true;
        }
        return UIZonesManager.isPointInUI(cam.getMousePointOnGraphics());
    }
    
}
