/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Save;

import java.io.PrintWriter;

/**
 *
 * @author Allazham
 */
public final class Save {
    private PrintWriter pw;
    Save(){
        
    }
    Save(PrintWriter pw){
        this.pw = pw;
    }
    void setPrintWriter(PrintWriter pw){
        this.pw = pw;
    }
    public void println(String line){
        pw.println(line);
    }
    public void print(String print){
        pw.print(print);
    }
}
