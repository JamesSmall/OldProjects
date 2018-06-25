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
public abstract class SaveObject{
    private String name = "UnnammedSaveObject";
    public abstract void addToSave(Save pw) throws IOException;
    
    public void setName(String name){
        this.name = name;
    }
    public String getName(){
        return this.name;
    }
}
