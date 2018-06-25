/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Save;

import java.io.IOException;

/**
 *
 * @author Allazham
 */
public class BasicSaveObject extends SaveObject{
    private final String[] data;
    public BasicSaveObject(String[] data,String name){
        this.data = data;
        super.setName(name);
    }
    public BasicSaveObject(String data,String name){
        this.data = new String[]{data};
        super.setName(name);
    }
    @Override
    public void addToSave(Save pw) throws IOException {
        int i;
        for(i = 0; i < data.length;i++){
            if(i != data.length-1){
                pw.println(data[i]);
            }
            else{
                pw.print(data[i]);
            }
        }
    }
}
