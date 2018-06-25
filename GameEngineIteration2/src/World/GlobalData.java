/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package World;

import Save.BasicSaveObject;
import Save.Load;
import Save.Populater;
import Save.SaveObjectPackage;
import Save.StatManager.StatList;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author Allazham
 */
public class GlobalData {
    private StatList statlist;
    private Entry mainPlayer;
    private ArrayList<String> CellNames;
    public GlobalData(Entry Player){
        statlist = new StatList();
        mainPlayer = Player;
        CellNames = new ArrayList();
    }
    public GlobalData(Load l) throws IOException{
        l.getSubDirectoryFile("StatList");
        statlist = StatList.generateStatList(l);
        l.getSubDirectoryFile("Player/main");
        Populater.GenerateEntry(l);
    }
    public ArrayList<String> getNamesList(){
        return this.CellNames;
    }
    public void addCellName(String name){
        this.CellNames.add(name);
    }
    public Entry getPlayer(){
        return this.mainPlayer;
    }
    public StatList getGlobalStatList(){
        return this.statlist;
    }
    public SaveObjectPackage getSave(){
        SaveObjectPackage pack = new SaveObjectPackage();
        pack.addPackage(this.mainPlayer.getSave());
        pack.addSaveObject(statlist.getSave());
        StringBuilder b = new StringBuilder(this.CellNames.get(0));
        for(int i = 1; i < this.CellNames.size();i++){
            b.append(",").append(this.CellNames.get(i));
        }
        pack.addSaveObject(new BasicSaveObject(new String[]{b.toString()},"CellNames"));
        return pack;
    }
}
