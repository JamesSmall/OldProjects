/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

import javax.swing.JOptionPane;

/**
 *
 * @author Allazham
 */
public class ErrorMessage {
    private static final String NullErrorMessage = "A target is required To run this tool";
    private static boolean showUserError = true;
    public static void setUserError(boolean show){
        ErrorMessage.showUserError = show;
    }
    public static boolean getuserError(){
        return ErrorMessage.showUserError;
    }
    
    public static void ErrorNullTarget(){
        JOptionPane.showMessageDialog(null, "User Error", NullErrorMessage, JOptionPane.ERROR_MESSAGE);
    }
    public static void ErrorIncorrectTargetType(String type){
        JOptionPane.showMessageDialog(null, "User Error",  "Incorrect Target type, the target must be "+type, JOptionPane.ERROR_MESSAGE);
    }
    
}
