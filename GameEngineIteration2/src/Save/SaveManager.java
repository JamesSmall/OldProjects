/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Save;

import World.Cell;
import World.Region;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;

/**
 *
 * @author Allazham
 */
public final class SaveManager {
    private static final UUID sessionid = UUID.randomUUID();
    private final List<File> ProblemFiles = new ArrayList();
    private Populater pop = new Populater();
    private final String saveDirectory = "Save/";
    private static final String lock = "Lock";
    private static final String Save = "Region/";
    private static final String EndSave = ".Test";
    public void doSave(SaveObject obj,String fileLoc) throws FileNotFoundException, IOException{
        int i;
        File f = new File(fileLoc);
        for(i = 0; i < this.ProblemFiles.size();i++){
            if(new File(fileLoc).equals(this.ProblemFiles.get(i))){
                this.ProblemFiles.remove(i);
                break;
            }
        }
        FileOutputStream fout = new FileOutputStream(f);
        ZipOutputStream zos = new ZipOutputStream(fout);
        PrintWriter pw;
        ZipEntry ze;
        ze = new ZipEntry(obj.getName());
        zos.putNextEntry(ze);
        pw = new PrintWriter(zos);
        obj.addToSave(new Save(pw));
        pw.flush();
        zos.finish();
        pw.close();
        zos.closeEntry();
    }
    public void doSave(SaveObjectPackage obj, String fileLoc) throws FileNotFoundException,IOException{
        int i;
        new File(fileLoc).delete();
        for(i = 0; i < this.ProblemFiles.size();i++){
            if(new File(fileLoc).equals(this.ProblemFiles.get(i))){
                this.ProblemFiles.remove(i);
                break;
            }
        }
        try (ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(fileLoc))) {
            
            obj.save("", zos,new Save());
            
            zos.finish();
            zos.close();
        }
        
    }
    public void saveRegion(Cell c,SaveObjectPackage s,int x, int y) throws IOException{
        //Thread.yield();
        new File(saveDirectory+c.getName()+"/"+Save).mkdirs();
        this.doSave(s,saveDirectory+c.getName()+"/"+Save+c.getName()+x+"_"+y+EndSave);
    }
    public void saveEntries(Cell c,SaveObjectPackage s,int x, int y) throws IOException{
        //Thread.yield();
        new File(saveDirectory+c.getName()+"/Entries/").mkdirs();
        this.doSave(s,saveDirectory+c.getName()+"/Entries/"+c.getName()+x+"_"+y+EndSave);
    }
    public void saveEntries(Cell c,SaveObjectPackage s) throws IOException{
        //Thread.yield();
        new File(saveDirectory+c.getName()+"/Entries/").mkdirs();
        this.doSave(s,saveDirectory+c.getName()+"/Entries/"+c.getName()+EndSave);
    }
    public Region loadRegion(Cell c,int x, int y){
        File Tilef = new File(saveDirectory+c.getName()+"/"+Save+c.getName()+x+"_"+y+EndSave);
        File Entryf = new File(saveDirectory+c.getName()+"/Entries/"+c.getName()+x+"_"+y+EndSave);
        int i;
        ZipFile zipfTile,zipfEntry;
        Load lT = null,lE = null;
        for(i = 0; i < this.ProblemFiles.size();i++){
            if(this.ProblemFiles.get(i).equals(Tilef)){
                return null;
            }
        }
        //Thread.yield();
        try{
            if(!Tilef.exists()&&Entryf.exists()){
                return pop.createBasicRegion(c, x, y);
            }
            zipfTile = new ZipFile(Tilef);
            zipfEntry = new ZipFile(Entryf);
            ZipEntry zeE = zipfEntry.getEntry("main");
            ZipEntry zeT = zipfTile.getEntry("main");
            if(zeT == null){
                throw new NullPointerException("Entry Does not contain main");
            }
            lT = new Load();
            lT.setupInputStream(zipfTile, zeT);
            lE = new Load();
            lE.setupInputStream(zipfEntry, zeE);
            return pop.GeneratePop(lT,lE,c,x,y);
        }
        catch(IOException | NullPointerException ex){
            //ErrorLog.error(ex);
            return pop.createBasicRegion(c, x, y);
        }
        finally{
            if(lE != null){
                try {
                    lE.destroy();
                } catch (IOException ex) {
                    
                }
            }
            if(lT != null){
                try {
                    lT.destroy();
                } catch (IOException ex) {
                    
                }
            }
        }
    }
    public boolean checkandlock(){
        try{
            if(isLocked()){
                return false;
            }
            File f = new File(this.saveDirectory+SaveManager.lock);
            this.lock();
        }
        catch(Exception ex){
            return false;
        }
        return true;
    }
    public void unlock(){
        FileWriter fw = null;
        try{
            new File(this.saveDirectory+SaveManager.lock).delete();
            fw = new FileWriter(new File(this.saveDirectory+SaveManager.lock));
            fw.append("0,"+SaveManager.sessionid);
        }
        catch(Exception ex){
            
        }
        finally{
            if(fw != null){
                try {
                    fw.close();
                } catch (IOException ex) {
                    
                }
            }
        }
    }
    public void lock(){
        FileWriter fw = null;
        try{
            new File(this.saveDirectory+SaveManager.lock).delete();
            fw = new FileWriter(new File(this.saveDirectory+SaveManager.lock));
            fw.append("1,"+SaveManager.sessionid);
        }
        catch(Exception ex){
            
        }
        finally{
            if(fw != null){
                try {
                    fw.close();
                } catch (IOException ex) {
                    
                }
            }
        }
    }
    public boolean isLocked(){
        BufferedReader r = null;
        try{
            File f = new File(this.saveDirectory+SaveManager.lock);
            if(f.exists()){
                r = new BufferedReader(new FileReader(f));
                String s = r.readLine().split(",")[0];
                if(s.equals("1")){
                    return true;
                }
            }
        }
        catch(Exception ex){
            
        }
        finally{
            if(r != null){
                try {
                    r.close();
                } catch (IOException ex) {
                    
                }
            }
        }
        return false;
    }
    
    public boolean checkLockFile(){
        BufferedReader r = null;
        try{
            File f = new File(this.saveDirectory+SaveManager.lock);
            if(f.exists()){
                r = new BufferedReader(new FileReader(f));
                String[] s = r.readLine().split(",");
                if(s[0].equals("1") ||!s[1].equals(SaveManager.sessionid.toString())){
                    return true;
                }
            }
        }
        catch(Exception ex){
            
        }
        finally{
            if(r != null){
                try {
                    r.close();
                } catch (IOException ex) {
                    
                }
            }
        }
        return false;
    }
    
    public boolean checkSessionID(){
        BufferedReader r = null;
        try{
            File f = new File(this.saveDirectory+SaveManager.lock);
            if(f.exists()){
                r = new BufferedReader(new FileReader(f));
                String s = r.readLine().split(",")[1];
                if(!s.equals(SaveManager.sessionid.toString())){
                    return true;
                }
            }
        }
        catch(Exception ex){
            
        }
        finally{
            if(r != null){
                try {
                    r.close();
                } catch (IOException ex) {
                    
                }
            }
        }
        return false;
    }
    public void RemoveRegion(Cell c,int x, int y){
        File f = new File(saveDirectory+c.getName()+"/"+Save+c.getName()+x+"_"+y+EndSave);
        if(f.exists()){
            f.setWritable(true);
            if(!f.delete()){
                this.ProblemFiles.add(f);
                f.deleteOnExit();
            }
        }
    }
    public void setPopulator(Populater p){
        this.pop = p;
    }
    public Populater getPopulator(){
        return pop;
    }
    public void saveGlobalData(SaveObjectPackage obj){
        
    }
}
