/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Forum.ToolBox;

import javax.swing.JFrame;


/**
 *
 * @author Allazham
 */
public interface Changeable {
    public void setDisable(boolean disable);
    public boolean isDisaled();
    
    public void showInstance();
    public JFrame getCustomInstance();
}
