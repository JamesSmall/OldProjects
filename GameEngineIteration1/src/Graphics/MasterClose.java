/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Graphics;

import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * @author james small
 * lastEdited:2/11/2013
 */
public class MasterClose {
    private static ExternalClose close;
    public static void CloseAll(){
       new Thread(new Runnable(){
           @Override
           public void run() {
                Screen.getInstance().endGL();
                Screen.getInstance().setVisible(false);
                while(close == null){
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(MasterClose.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                close.closeObject();
                ErrorLog.WriteErrorLog();
                try {
                    while(Screen.isScreenCreated()){
                        Thread.sleep(100);
                    }
                } catch (InterruptedException ex) {
                    System.exit(1);
                }
                System.exit(0);
            }
        }).start();
    }
    public static void CloseWithError(Throwable error){
        ErrorLog.error(error);
        new Thread(new Runnable(){
           @Override
           public void run() {
                Screen.getInstance().endGL();
                Screen.getInstance().setVisible(false);
                if(close != null){
                    close.closeObject();
                }
                ErrorLog.WriteErrorLog();
                try {
                    while(Screen.isScreenCreated()){
                        Thread.sleep(100);
                    }
                } catch (InterruptedException ex) {
                    System.exit(1);
                }
                System.exit(1);
            }
        }).start();
    }
    public static void setCloseAll(ExternalClose ex){
        close = ex;
    }
    public static ExternalClose getExternalClose(){
        return close;
    }
}