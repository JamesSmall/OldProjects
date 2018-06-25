/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Graphics.UserActions;

/**
 *
 * @author Allazham
 */
public abstract class AbstractUserAction implements UserAction{
    private boolean disabled;
    public AbstractUserAction(boolean disabled){
        this.disabled = disabled;
    }

    @Override
    public boolean isDisabled() {
        return this.disabled;
    }
    public void setDisabled(boolean disabled){
        this.disabled = disabled;
    }
}
