/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Holder;

import java.io.File;
import java.io.FileFilter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author Allazham
 */
public class MasterLoad {
    private static List<Sound> sounds = new ArrayList();
    private static List<Image> images = new ArrayList();
    private static final String IMAGELOADLOC = "/Images";
    private static final String SOUNDLOADLOC = "/Sounds";
    public static void load() throws IOException{
        MasterLoad.images.add(new Image("water","Images/Water",0,0,1,1,true));
        MasterLoad.images.add(new Image("lava","Images/Lava",0,0,1,1,true));
        MasterLoad.images.add(new Image("plain","Images/Plain",0,0,1,1,true));
        MasterLoad.images.add(new Image("obsidian","Images/Obsidian",0,0,1,1,true));
        MasterLoad.images.add(new Image("desert","Images/Desert",0,0,1,1,true));
    }
    public static Image getImage(String name){
        int i;
        for(i = 0; i < MasterLoad.images.size();i++){
            if(MasterLoad.images.get(i).getName().matches(name)){
                return MasterLoad.images.get(i);
            }
        }
        return null;
    }
    public static Sound getSound(String name) throws IOException{
        int i;
        for(i = 0; i < MasterLoad.sounds.size();i++){
            if(MasterLoad.sounds.get(i).getName().equalsIgnoreCase(name)){
                return MasterLoad.sounds.get(i);
            }
        }
        return null;
    }
}
