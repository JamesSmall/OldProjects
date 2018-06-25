/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils.SaveManager;

import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 *
 * @author Allazham
 */
public final class SaveObjectPackage{
    private String name = "";
    private List<String> saveInfo = new ArrayList();
    private List<SaveObject> obj = new ArrayList();
    private List<SaveObjectPackage> packages = new ArrayList();
    public SaveObjectPackage(List<String> mainData){
        saveInfo = mainData;
    }
    public void setMainData(List<String> mainData){
        saveInfo = mainData;
    }
    public void addPackage(SaveObjectPackage pack){
        this.packages.add(pack);
    }
    public void addSaveObject(SaveObject save){
        this.obj.add(save);
    }
    public void setName(String name){
        this.name = name + "//";
    }
    public String getName(){
        return this.name;
    }
    protected void save(String currentLoc,ZipOutputStream zos) throws IOException{
        PrintWriter pw;
        Save s = new Save();
        ZipEntry ze;
        int i;
        if(this.saveInfo != null){
            if(!this.saveInfo.isEmpty()){
                obj.add(new SaveMain());
            }
        }
        for(i = 0; i < obj.size();i++){
            if(!currentLoc.isEmpty()){
                currentLoc += "//";
            }
            ze = new ZipEntry(currentLoc+this.name+obj.get(i).getName());
            System.out.println(currentLoc+this.name+obj.get(i).getName());
            zos.putNextEntry(ze);
            pw = new PrintWriter(new BufferedOutputStream(zos));
            s.setPrintWriter(pw);
            obj.get(i).addToSave(s);
            pw.flush();
            zos.closeEntry();
        }
        for(i = 0; i < this.packages.size();i++){
            this.packages.get(i).save(currentLoc+"\\"+this.name, zos);
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
                pw.println(saveInfo.get(i));
            }
        }
    }
}
