/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Item;

import Save.Load;
import Save.SaveObjectPackage;
import Save.StatManager.StatList;
import World.Cell;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 *
 * @author Allazham
 */
public class ItemList {
    private final ArrayList<Item> IList = new ArrayList();
    public void forceCell(Cell c){
        int i;
        for(i = 0; i < IList.size();i++){
            this.IList.get(i).forceCell(c);
        }
    }
    public boolean addItem(Item i){
        return IList.add(i);
    }
    public boolean removeItemByUUID(UUID uid){
        Item i = this.getItemByUUID(uid);
        if(i != null){
            return IList.remove(i);
        }
        return false;
    }
    public boolean fuseStack(Item it){
        List<Item> actual = this.getItemByName(it.getName());
        int i;
        for(i = 0;i < actual.size();i++){
            if(actual.get(i).MergeStack(it)){
                return true;
            }
        }
        return false;
    }
    public List<Item> getItemByName(String name){
        int i;
        ArrayList<Item> names = new ArrayList();
        for(i = 0; i < IList.size();i++){
            if(IList.get(i).getName().equals(name)){
                names.add(IList.get(i));
            }
        }
        return names;
    }
    public Item getItemByUUID(UUID uid){
        int i;
        for(i = 0; i < IList.size();i++){
            if(IList.get(i).getUUID().equals(uid)){
                return IList.get(i);
            }
        }
        return null;
    }
    public void checkList(StatList list){
        for(Item i:this.IList){
            StatList.CheckElements(list, i.getStatList());
        }
    }
    public SaveObjectPackage getSave(){
        SaveObjectPackage pack = new SaveObjectPackage(new String[]{""+IList.size()});
        pack.setName("ItemList");
        SaveObjectPackage o;
        return pack;
    }
    public static ItemList getItemList(Load l) throws IOException{
        String overview = l.getCurrentLocation();
        String data = l.readFromInputFile().get(0);
        int max = Integer.parseInt(data);
        ItemList IList = new ItemList();
        int i;
        for(i = 0; i < max;i++){
            l.getSubDirectoryFile(overview+"Item"+i+"/main");
            IList.IList.add(Item.loadItem(l));
        }
        return IList;
    }
}
