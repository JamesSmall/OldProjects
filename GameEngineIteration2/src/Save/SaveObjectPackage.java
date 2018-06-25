/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Save;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 *
 * @author Allazham
 */
public final class SaveObjectPackage{
    private String name = "";
    private List<String> saveInfo;
    private final List<SaveObject> obj = new ArrayList();
    private final List<SaveObjectPackage> packages = new ArrayList();
    public SaveObjectPackage(){
        saveInfo = new ArrayList();
    }
    public SaveObjectPackage(String[] mainData){
        saveInfo = new ArrayList();
        saveInfo.addAll(Arrays.asList(mainData));
    }
    public SaveObjectPackage(List<String> mainData){
        saveInfo = mainData;
    }
    public void setMainData(String[] mainData){
        saveInfo = new ArrayList();
        saveInfo.addAll(Arrays.asList(mainData));
    }
    public void setMainData(List<String> mainData){
        saveInfo = mainData;
    }
    public void addPackage(SaveObjectPackage pack){
        if(pack.equals(this)){
            throw new UnsupportedOperationException("Cannot add a pack to itself");
        }
        else if(this.packages.contains(pack)){
            throw new UnsupportedOperationException("Cannot add the same package twice");
        }
        this.packages.add(pack);
    }
    public void addSaveObject(SaveObject save){
        this.obj.add(save);
    }
    public void setName(String name){
        this.name = name;
    }
    public String getName(){
        return this.name;
    }
    public boolean isEmpty(){
        return this.obj.isEmpty() && this.packages.isEmpty();
    }
    void save(String currentLoc,ZipOutputStream zos,Save s) throws IOException{
        PrintWriter pw = new PrintWriter(zos);
        ZipEntry ze;
        int i;
        if(!currentLoc.isEmpty()&&!currentLoc.endsWith("/")){
            currentLoc = currentLoc.concat("/");
            ze = new ZipEntry(currentLoc);
            zos.putNextEntry(ze);
            pw.flush();
            zos.closeEntry();
        }
        currentLoc = currentLoc.concat(this.name);
        if(this.saveInfo != null){
            if(!this.saveInfo.isEmpty()){
                obj.add(new SaveMain());
            }
        }
        if(!currentLoc.isEmpty()&&!currentLoc.endsWith("/")){
            currentLoc = currentLoc.concat("/");
        }
        for(i = 0; i < this.packages.size();i++){
            this.packages.get(i).save(currentLoc,zos,s);
        }
        for(i = 0; i < obj.size();i++){
            ze = new ZipEntry(currentLoc.concat(obj.get(i).getName()));
            zos.putNextEntry(ze);
            
            s.setPrintWriter(pw);
            obj.get(i).addToSave(s);
            
            pw.flush();
            zos.closeEntry();
        }
    }
    private class SaveMain extends SaveObject{
        public SaveMain(){
            super.setName("main");
        }
        @Override
        public void addToSave(Save pw) throws IOException {
            int i;
            for(i = 0;i < saveInfo.size();i++){
                if(i!= saveInfo.size()-1){
                    pw.println(saveInfo.get(i));
                }
                else{
                    pw.print(saveInfo.get(i));
                }
            }
        }
    }
}
