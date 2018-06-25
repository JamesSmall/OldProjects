/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Save;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
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
    private InputStreamReader IFS; 
    private InputStream IS;
    void setupInputStream(ZipFile f,ZipEntry ze) throws IOException{
       String[] strings;
       if(ze.getName().replace("\\", "/").contains("/")){
           strings = ze.getName().replace("\\", "/").split("/");
       }
       else{
            strings = new String[]{ze.getName()};
       }
       int num = strings.length - 1;
       int i;
       StringBuilder b = new StringBuilder();
       for(i = 0; i < num; i++){
          b.append(strings[i]).append("/");
       }
       this.Directory = b.toString();
       this.f = f;
       IS = f.getInputStream(ze);
       IFS = new InputStreamReader(IS);
       read = new BufferedReader(IFS);
    }
    void destroy() throws IOException{
        IS.close();
        IFS.close();
        read.close();
        f.close();
    }
    public String getCurrentLocation(){
        return currLoc;
    }
    public String getOverviewLocation(){
        if(this.currLoc.isEmpty()){
            return "";
        }
        String[] example = currLoc.split("/");
        String ret = "";
        int i;
        for(i = 0; i < example.length-1;i++){
            ret += example[i]+"/";
        }
        return ret;
    }
    public void getSubDirectoryFile(String loc) throws IOException{
        ZipEntry ze = this.f.getEntry(Directory+loc);
        BufferedReader reader = new BufferedReader(new InputStreamReader(f.getInputStream(ze)));
        this.read.close();
        this.read = reader;
        currLoc = loc;
    }
    public List<String> readFromInputFile(){
        List<String> data = new ArrayList();
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