/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Graphics.UserActions;

import Graphics.Forum.ActionKeys;

/**
 *
 * @author Allazham
 */
public interface UserAction {
    public void Action(ActionKeys k);
    public boolean isDisabled();
    public void create();
    public void dispose();
}
