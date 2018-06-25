/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author Allazham
 */
public class ErrorLog {
    private static final List<Throwable> thrower = new ArrayList();
    private static boolean disabled = false;
    private ErrorLog(){
        
    }
    public static void error(Throwable b){
        if(ErrorLog.disabled){
            System.out.println(ErrorLog.getStackTrace(b));
        }
        ErrorLog.thrower.add(b);
    }
    public static void setDisabled(boolean disabled){
        ErrorLog.disabled = disabled;
    }
    public static void WriteErrorLog(){
        if(disabled){
            return;
        }
        File f;
        PrintWriter p;
        int i;
        for(i = 0; i < thrower.size();i++){
            thrower.get(i).printStackTrace();
        }
        if(ErrorLog.thrower.isEmpty()){
            return;
        }
        try {
            f = new File("ErrorLog"+System.nanoTime()+".txt");
            if(f.createNewFile()){
                p = new PrintWriter(f);
                //p.write("Please send these errors to Allazham@hotmail.com");
                //p.println();
                for(i = 0; i < ErrorLog.thrower.size();i++){
                    p.write(ErrorLog.getStackTrace(ErrorLog.thrower.get(i)));
                }
                p.flush();
                p.close();
            }
            else{
                throw new IOException("Could not Create Error Log File");
            }
        } catch (IOException ex) {
            String s = "";
            i = JOptionPane.showConfirmDialog(null,"unable to write errors to file,"+(ErrorLog.thrower.size()+1)+" Errors Detected, press contunie to see errors in window, click cancel to ignore errors","an error has occured",JOptionPane.OK_CANCEL_OPTION);
            if(i == JOptionPane.OK_OPTION){
                for(i = 0;i < ErrorLog.thrower.size();i++){
                    s += ErrorLog.getStackTrace(ErrorLog.thrower.get(i));
                }
            }
            JOptionPane.showMessageDialog(null,s + ErrorLog.getStackTrace(ex));
        }
    }
    public static String getStackTrace(Throwable aThrowable) {
        final Writer result = new StringWriter();
        final PrintWriter printWriter = new PrintWriter(result);
        printWriter.println("["+aThrowable.getMessage()+"]");
        aThrowable.printStackTrace(printWriter);
    return result.toString();
  }
}
