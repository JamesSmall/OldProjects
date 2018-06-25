/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils.SaveManager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

/**
 *
 * @author Allazham
 */
public final class Load {
    private ZipFile f;
    private String currLoc = "";
    private String Directory = "";
    private BufferedReader read;
    void setupInputStream(ZipFile f,ZipEntry ze) throws IOException{
       String strings[] = ze.getName().split(System.getProperty("line.separator"));
       int num = strings.length - 1;
       int i;
       for(i = 0; i < num; i++){
           Directory += strings[i]+"/";
       }
       this.f = f;
       read = new BufferedReader(new InputStreamReader(f.getInputStream(ze)));
    }
    void destroy() throws IOException{
        read.close();
    }
    public void getSubDirectoryFile(String loc) throws IOException{
        ZipEntry ze = this.f.getEntry(Directory+=loc);
        BufferedReader reader = new BufferedReader(new InputStreamReader(f.getInputStream(ze)));
        this.read.close();
        this.read = reader;
        currLoc = loc;
    }
    public List<String> readFromInputFile(){
        List<String> data = new ArrayList();
        int i;
        String dat;
        try {
            while((dat = this.read.readLine())!= null){
                data.add(dat);
            }
        } catch (IOException ex) {
            Logger.getLogger(Load.class.getName()).log(Level.SEVERE, null, ex);
        }
        return data;
    }
}