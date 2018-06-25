/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Graphics.Forum;

import Graphics.Utils.ExternalClose;
import Utils.ErrorLog;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * @author james small
 * lastEdited:2/11/2013
 */
public final class MasterClose {
    private static boolean Closing = false;
    private static ExternalClose close = null;
    private MasterClose(){
        
    }
    private static final Thread Closer = new Thread(new Runnable(){
           @Override
           public void run() {
               try{
                Screen.getInstance().endGL();
                if(close == null){
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(MasterClose.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                if(close != null){
                    close.closeObject();
                }
                    while(Screen.isScreenCreated()){
                        Thread.sleep(100);
                    }
                } catch (Exception ex) {
                    ErrorLog.WriteErrorLog();
                    Screen.getInstance().endGL();
                    while(Screen.isScreenCreated()){
                        try {
                            Thread.sleep(100);
                        } catch (InterruptedException ex1) {
                            ErrorLog.error(ex);
                        }
                    }
                    System.exit(1);
                }
                ErrorLog.WriteErrorLog();
                System.exit(0);
            }
        });
    static{
        Closer.setName("Close Thread");
    }
    public static boolean isAttemptingClose(){
        return MasterClose.Closing;
    }
    public static void CloseAll(){
        if(!MasterClose.Closing){
            Closer.start();
        }
        MasterClose.Closing = true;
    }/*
    public static void CloseWithError(Throwable error){
        ErrorLog.error(error);
        new Thread(new Runnable(){
           @Override
           public void run() {
                if(close != null){
                    close.closeObject();
                }
                ErrorLog.WriteErrorLog();
                try {
                    Screen.getInstance().endGL();
                    while(Screen.isScreenCreated()){
                        Thread.sleep(100);
                    }
                    Screen.getInstance().getPanel().setVisible(false);
                } catch (InterruptedException ex) {
                    System.exit(1);
                }
                System.exit(1);
            }
        }).start();
    }*/
    public static void setCloseAll(ExternalClose ex){
        close = ex;
    }
    public static ExternalClose getExternalClose(){
        return close;
    }
    public static WindowAdapter getDestroyOnCloseListener(){
        return new DestroyGraphics();
    }
    private static class DestroyGraphics extends WindowAdapter{            
        @Override
        public void windowClosing(WindowEvent e) {
            if(!MasterClose.Closing)
                MasterClose.CloseAll();
        }
    }
}