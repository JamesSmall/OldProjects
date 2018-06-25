/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package World;

import Graphics.Utils.ExternalClose;
import Graphics.Forum.MasterClose;
import Graphics.Forum.Screen;
import Save.Save;
import Save.SaveManager;
import Save.SaveObject;
import Save.SaveObjectPackage;
import Utils.ErrorLog;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Allazham
 */
public final class WorldControl {
    private static final Screen scr = Screen.getInstance();
    private static final WorldControl wld = WorldControl.getInstance();
    private Cell PrimaryCell;
    private SaveManager SM = new SaveManager();
    private final SaveInstanceControl control = new SaveInstanceControl();
    private final ArrayList<Cell> cells = new ArrayList();
    public Cell getPrimaryCell(){
        return this.PrimaryCell;
    }
    public ArrayList<Cell> getCells(){
        return cells;
    }
    public void start(){
        new Thread(control).start();
    }
    public void setPrimaryCell(Cell c){
        if(this.PrimaryCell != null){
            this.PrimaryCell.setScreenInvisiable();
            this.PrimaryCell.stop();
        }
        this.PrimaryCell = c;
        if(this.PrimaryCell != null){
            this.PrimaryCell.setScreenVisiable();
            this.PrimaryCell.start();
            if(!this.cells.contains(c)){
                this.cells.add(c);
            }
        }
    }
    private boolean isStillActive(){
        int i;
        for(i = 0; i < cells.size();i++){
            if(cells.get(i).isRunning()){
                return true;
            }
        }
        if(this.control.isAlive()){
            return true;
        }
        return this.PrimaryCell.isRunning();
    }
    private void CloseWorld(){
        int i;
            for(i = 0; i < cells.size();i++){
                cells.get(i).stop();
            }
            PrimaryCell.stop();
            this.control.setRunning(false);
    }
    private class SaveInstanceControl implements Runnable{
        private boolean Running,Active,Alive = false;
        private final LinkedList<Cell> closingCells = new LinkedList();
        @Override
        public void run() {
            Thread.currentThread().setName("Save Control");
            this.Running = true;
            this.Active = true;
            this.Alive = true;
            int count = 0;
            Crashable:{
                SM.lock();
                SM.unlock();
                while(this.Running){
                    if(this.Active){
                        if(SM.checkLockFile()){
                            MasterClose.CloseAll();
                            break Crashable;
                        }
                        //removes regions that are no longer ment to be active
                        SM.lock();
                            for(Cell c:getCells()){
                                LinkedList<Region> r = c.getClosingRegions();
                                while(r.size() > 0){
                                    try{
                                        Region reg = r.removeFirst();
                                        scr.removeGraphicsObject(reg.getGraphics());
                                        SM.saveRegion(c,reg.getSavePackage(),reg.RegionX,reg.RegionY);
                                    }
                                    catch(IOException ex){
                                        ErrorLog.error(ex);
                                    }
                                }
                                LinkedList<PositionRequest> requests = c.getPositionRequestList();
                                PositionRequest PR ;
                                while(requests.size() > 0){
                                     PR = requests.removeFirst();
                                     Thread.yield();
                                     Region reg = SM.loadRegion(c, PR.x, PR.y);
                                     if(reg != null){
                                        c.addRegion(reg);
                                     }
                                }
                            }
                        SM.unlock();
                        if(this.closingCells.size() > 0){
                            if(SM.checkLockFile()){
                                MasterClose.CloseAll();
                                break Crashable;
                            }
                            this.closingCells();
                        }
                        if(count == 500){
                            count = -1;
                            if(SM.checkLockFile()){
                                MasterClose.CloseAll();
                                break Crashable;
                            }
                            this.AllSaveOperation();
                        }
                        count++;
                    }
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException ex) {
                        ErrorLog.error(ex);
                    }
                }
                if(SM.checkLockFile()){
                    MasterClose.CloseAll();
                    break Crashable;
                }
                this.AllSaveOperation();
                this.Alive = false;
            }
        }
        private void AllSaveOperation(){
            LinkedList<SaveData> Entries = new LinkedList();
            LinkedList<SaveData> Tiles = new LinkedList();
            LinkedList<SaveData> PersistantEntries = new LinkedList();
            SM.lock();
            for(Cell c:getCells()){
                ArrayList<Region> rr = c.getAndRemoveClosingRegions();
                rr.addAll(c.getActiveRegions());
                PersistantEntries.add(new SaveData(c,c.getPeristantEntries(),0,0));
                for(Region r:rr){
                    Entries.add(new SaveData(c,r.getSaveEntriesPackage(),r.RegionX,r.RegionY));
                    if(r.hasTileUpdated()){
                        Tiles.add(new SaveData(c,r.getSaveTilePackage(),r.RegionX,r.RegionY));
                        r.claimTilesSaved();
                    }
                }
            }
            for(SaveData sd:Entries){
                Thread.yield();
                try {
                    SM.saveEntries(sd.c, sd.pack, sd.x,sd.y);
                } catch (IOException ex) {
                    ErrorLog.error(ex);
                }
            }
            
            for(SaveData sd:Tiles){
                Thread.yield();
                try {
                    SM.saveRegion(sd.c, sd.pack, sd.x,sd.y);
                } catch (IOException ex) {
                    ErrorLog.error(ex);
                }
            }
            
            for(SaveData sd:PersistantEntries){
                Thread.yield();
                try {
                    SM.saveEntries(sd.c, sd.pack);
                } catch (IOException ex) {
                    ErrorLog.error(ex);
                }
            }
        }
        private void closingCells(){
            LinkedList<SaveData> Entries = new LinkedList();
            LinkedList<SaveData> Tiles = new LinkedList();
            LinkedList<SaveData> PersistantEntries = new LinkedList();
            SM.lock();
            while(this.closingCells.size() > 0){
                Cell c = this.closingCells.removeFirst();
                LinkedList<Region> rr = c.getClosingRegions();
                rr.addAll(c.getActiveRegions());
                PersistantEntries.add(new SaveData(c,c.getPeristantEntries(),0,0));
                for(Region r:rr){
                    Entries.add(new SaveData(c,r.getSaveEntriesPackage(),r.RegionX,r.RegionY));
                    if(r.hasTileUpdated()){
                        Tiles.add(new SaveData(c,r.getSaveTilePackage(),r.RegionX,r.RegionY));
                    }
                }
                
            }
            for(SaveData sd:Tiles){
                Thread.yield();
                try {
                    SM.saveRegion(sd.c, sd.pack, sd.x,sd.y);
                } catch (IOException ex) {
                    ErrorLog.error(ex);
                }
            }
            for(SaveData sd:Entries){
                Thread.yield();
                try {
                    SM.saveEntries(sd.c, sd.pack, sd.x,sd.y);
                } catch (IOException ex) {
                    ErrorLog.error(ex);
                }
            }
            for(SaveData sd:PersistantEntries){
                Thread.yield();
                try {
                    SM.saveEntries(sd.c, sd.pack);
                } catch (IOException ex) {
                    ErrorLog.error(ex);
                }
            }
            SM.unlock();
        }
        public void setRunning(boolean running){
            this.Running = running;
        }
        public void setActive(boolean active){
            this.Active = active;
        }
        public boolean isRunning(){
            return this.Running;
        }
        public boolean isActive(){
            return this.Active;
        }
        public boolean isAlive(){
            return this.Alive;
        }
        public void closeCell(Cell c){
            this.closingCells.add(c);
        }
        private class SaveData{
            private SaveData(Cell c,SaveObjectPackage pack,int x, int y){
                this.c = c;
                this.x = x;
                this.y = y;
                this.pack = pack;
            }
            private Cell c;
            private int x, y;
            private SaveObjectPackage pack;
    }
    }
    private WorldControl(){
        MasterClose.setCloseAll(new Closer());
    }
    public static WorldControl getInstance(){
        return WorldHolder.world;
    }
    private static class WorldHolder{
        public static final WorldControl world = new WorldControl();
    }
    public SaveObject getSaveInstance(){
        return new SaveInstance();
    }
    private class Closer implements ExternalClose{
        @Override
        public void closeObject() {
            CloseWorld();
            while(isStillActive()){
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(WorldControl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
    public class SaveInstance extends SaveObject{
        @Override
        public void addToSave(Save pw) throws IOException {
            int i;
            pw.print(""+cells.size());
            for(i = 0; i < cells.size();i++){
                pw.print(""+cells.get(i).getName());
            }
        }
    }
}
