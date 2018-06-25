/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Graphics.UserActions.DefaultUserActions;

import Graphics.Forum.ActionKeys;
import Graphics.UserActions.UserAction;
import org.lwjgl.Sys;

/**
 *
 * @author Allazham
 */
public class FPSCalculator implements UserAction{
    private int fps;
    private float lastFPS;
    private boolean Disabled,firstRun = true;
    public FPSCalculator(boolean disabled){
        this.Disabled = disabled;
    }
    @Override
    public void Action(ActionKeys k) {
        if(firstRun){
            this.lastFPS = Sys.getTime();
            this.firstRun = false;
        }
        updateFPS();
    }
    public void updateFPS() {
		if (Sys.getTime() - lastFPS > 1000) {
			System.out.println("FPS: " + fps);
			if(this.fps == 1){
                            lastFPS = Sys.getTime();
                        }
                        else{
                            lastFPS += 1000;
                        }
                        fps = 0;
		}
		fps++;
	}
    @Override
    public boolean isDisabled() {
        return this.Disabled;
    }
    public void setDisabled(boolean disabled){
        this.Disabled = disabled;
        if(this.Disabled = true){
            this.firstRun = true;
        }
    }

    @Override
    public void create() {
        
    }

    @Override
    public void dispose() {
        
    }
}
