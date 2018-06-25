/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Manager;

import Graphics.ErrorLog;
import Graphics.ExternalClose;
import Graphics.MasterClose;
import Graphics.Screen;
import Manager.ChunkBuilder.BasicLandGeneratorAlpha;
import Manager.ChunkBuilder.ChunkBuilderBase;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author james small
 */
public class World{
    private String WorldName;
    private static World currentInstance = null;
    private int LoadedDistance = 5;
    private int DistanceTilDestory = 4;
    private static final int SLEEPERTHREAD = 72;
    private ChunkBuilderBase b = new BasicLandGeneratorAlpha();
    private Player p;
    private Thread EntityRunner,ChunkDeleter,ChunkBuilder,SaveThread;
    //private List<WorldGPS> g = new LinkedList();
    private Screen scr = Screen.getInstance();
    public List<Chunk> chunk = new ArrayList();
    private Runner run;
    private Deleter del;
    private Creator cre;
    private Saver sav;
    private boolean ready;
    @SuppressWarnings("LeakingThisInConstructor")
    public World(String name,Player p){
        World.currentInstance = this;
        this.p = p;
        this.run = new Runner();
        this.del = new Deleter();
        this.cre = new Creator();
        this.sav = new Saver();
        this.EntityRunner = new Thread(this.run);
        this.ChunkDeleter = new Thread(this.del);
        this.ChunkBuilder = new Thread(this.cre);
        this.SaveThread = new Thread(this.sav);
        MasterClose.setCloseAll(new Closer());
        this.WorldName = name;
        new File("Saves/").mkdir();
        new File("Saves/"+this.WorldName+"/").mkdir();
        new File("Saves/"+this.WorldName+"/Chunk/").mkdir();
    }
    @SuppressWarnings("LeakingThisInConstructor")
    public World(List<String> startInstructions){
        World.currentInstance = this;
        SetUptheWorld(startInstructions);
        this.run = new Runner();
        this.del = new Deleter();
        this.cre = new Creator();
        this.sav = new Saver();
        MasterClose.setCloseAll(new Closer());
        this.EntityRunner = new Thread(this.run);
        this.ChunkDeleter = new Thread(this.del);
        this.ChunkBuilder = new Thread(this.cre);
        this.SaveThread = new Thread(this.sav);
        new File("Saves/").mkdir();
        new File("Saves/"+this.WorldName+"/").mkdir();
        new File("Saves/"+this.WorldName+"/Chunk/").mkdir();
        
    }
    private void SetUptheWorld(List<String> args){
        
    }
    public void StartThreads() throws FileNotFoundException, InterruptedException, IOException{
        int xx,yy,x,y;
        Chunk c;
        for(xx = - DistanceTilDestory - LoadedDistance;xx< +DistanceTilDestory+LoadedDistance;xx++){
            for(yy = - DistanceTilDestory - LoadedDistance;yy< +DistanceTilDestory+LoadedDistance;yy++){
                x = (int) p.getX()/256;
                y = (int) p.getY()/256;
                    c = getChunk(x+xx,y+yy);
                    scr.addGraphicObject(c.getBlocksForScreen());
                    scr.addGraphicObject(c.getCreaturesForScreen());
                this.chunk.add(c);
            }
        }
        this.EntityRunner.start();
        this.ChunkDeleter.start();
        this.ChunkBuilder.start();
        this.SaveThread.start();
        this.ready = true;
    }
    public void SetUpWorld(List<String> instructions){
        this.SetUptheWorld(instructions);
    }
    public Chunk getChunkAt(int x, int y){
        int i;
        for(i = 0;i<chunk.size();i++){
            if(chunk.get(i).getChunkX() == x && chunk.get(i).getChunkY() == y){
                return chunk.get(i);
            }
        }
        return null;
    }
    public Chunk getChunkOnScreen(int x, int y){
        //((this.x - (this.width*this.scale) - WorldGPS.ScreenX)*WorldGPS.ScreenScale), ((this.y + (this.height*this.scale) - WorldGPS.ScreenY)*WorldGPS.ScreenScale)
        return this.getChunkAt((int)((x - WorldGPS.getWorldX()*WorldGPS.getWorldScale()/256)),(int)((x - WorldGPS.getWorldX()*WorldGPS.getWorldScale())/256));
    }
    public List<CreatureManager> getCreaturesOnScreen(int x, int y){
        Chunk c = this.getChunkOnScreen(x, y);
        List<CreatureManager> ret = new ArrayList();
        List<CreatureManager> cc = c.getTargets();
        int i;
        for(i = 0; i < cc.size();i++){
            if(cc.get(i).isInScreenHitBox(x, y)){
                ret.add(cc.get(i));
            }
        }
        return ret;
    }
    public void DestroyWorld(){
        int i;
        Chunk c;
        for(i = 0; i < this.chunk.size();i++){
            c = chunk.get(i);
            if(c != null){
                try {
                    this.SaveChunk(c);
                    scr.removeGraphicsObject(c.getBlocksForScreen());
                } catch (IOException ex) {
                    ErrorLog.error(ex);
                }
            }
        }
        this.chunk.clear();
        World.currentInstance = null;
        this.ready = false;
    }
    public Block getBlock(float x, float y){
        float ChunkX = x/256, ChunkY = y/256;
        Chunk c = this.getChunkAt((int)ChunkX,(int)ChunkY);
        if(c == null){
            return null;
        }
        return c.getBlock(x/16%16,y/16%16);
    }
    private void SaveChunk(Chunk c) throws IOException{
        List<String> data = c.getSaveData();
        File f = new File("Saves/"+this.WorldName+"/Chunk/ChunkX"+c.getChunkX()+"Y"+c.getChunkY()+".BARB");
        FileWriter fw;
        BufferedWriter w;
        int i;
        if(!f.exists()){
            f.getAbsoluteFile().createNewFile();
        }
        f = f.getAbsoluteFile();
        fw = new FileWriter(f);
        w = new BufferedWriter(fw);
        for(i = 0; i < data.size();i++){
            w.write(data.get(i)+System.getProperty("line.separator"));
        }
        w.flush();
        w.close();
    }
    private Chunk getChunk(int x, int y) throws FileNotFoundException, IOException{
        Chunk c;
        File f = new File("Saves/"+this.WorldName+"/Chunk/ChunkX"+x+"Y"+y+".BARB");
        List<String> args = new ArrayList();
        BufferedReader r;
        FileReader fr;
        String s;
        if(f.exists()){
            fr = new FileReader(f);
            r = new BufferedReader(fr);
            while((s = r.readLine())!= null){
                args.add(s);
            }
            c = new Chunk(args);
            scr.addGraphicObject(c.getCreaturesForScreen());
            scr.addGraphicObject(c.getBlocksForScreen());
            return c;
        }
        c = this.b.buildChunk(x,y);
        scr.addGraphicObject(c.getCreaturesForScreen());
        scr.addGraphicObject(c.getBlocksForScreen());
        return c;
    }
    public List<CreatureManager> getCreaturesInArea(float x, float y, float r){
        int i;
        List<CreatureManager> e = new ArrayList();
        for(i = 0; i < this.chunk.size();i++){
            if(this.chunk.get(i).getChunkX() > (x/256)-(r/256) && (x/256)+(r/256) > this.chunk.get(i).getChunkX()&&this.chunk.get(i).getChunkY() > (y/256)-(r/256) && (y/256)+(r/256) > this.chunk.get(i).getChunkY()){
                e.addAll(this.chunk.get(i).getTargets());
            }
        }
        return e;
    }
    private class Deleter implements Runnable{
        private boolean Running = true, Executing = true,currentlyRunning = false;
        @Override
        public void run() {
            int i;
            Chunk c;
            int x,y;
            try {
                this.currentlyRunning = true;
                while(this.Running){
                    if(this.Executing){
                        x = (int)(p.getX()/256);
                        y = (int)(p.getY()/256);
                        for(i = 0; i < chunk.size();i++){
                            c = chunk.get(i);
                            if(!c.hasReachedFinalSave()&&(c.getChunkX() > x+LoadedDistance+DistanceTilDestory||c.getChunkX() < x-LoadedDistance-DistanceTilDestory||c.getChunkY() > x +LoadedDistance+DistanceTilDestory||c.getChunkY() < y-LoadedDistance-DistanceTilDestory)){
                                chunk.get(i).FinalSave();
                            }
                        }
                        Thread.sleep(World.SLEEPERTHREAD);
                        if(!this.Running){
                            break;
                        }
                        x = (int)(p.getX()/256);
                        y = (int)(p.getY()/256);
                        for(i = 0; i < chunk.size();i++){
                            c = chunk.get(i);
                            if(c.isDisabled()&&!c.hasReachedFinalSave()&&(c.getChunkX() > x+LoadedDistance||c.getChunkX() < x-LoadedDistance||c.getChunkY() > y+LoadedDistance||c.getChunkY() < y-LoadedDistance)){
                                chunk.get(i).setDisabled(true);
                            }
                        }
                        Thread.sleep(World.SLEEPERTHREAD);
                        if(!this.Running){
                            break;
                        }
                        x = (int)(p.getX()/256);
                        y = (int)(p.getY()/256);
                        for(i = 0; i < chunk.size();i++){
                            c = chunk.get(i);
                            if(!c.isDisabled()&&!c.hasReachedFinalSave()&&(c.getChunkX() > x+LoadedDistance||c.getChunkX() < x-LoadedDistance||c.getChunkY() > y+LoadedDistance||c.getChunkY() < y-LoadedDistance)){
                                chunk.get(i).setDisabled(true);
                            }
                        }
                    }
                    Thread.sleep(World.SLEEPERTHREAD);
                }
                
            } catch (InterruptedException ex) {
                MasterClose.CloseAll();
            }
            this.currentlyRunning = false;
        }
        public void setRunning(boolean Running){
            this.Running = Running;
        }
        public void setExecuting(boolean execute){
            this.Executing = execute;
        }
        public boolean isRunning(){
            return this.Running;
        }
        public boolean isExecuting(){
            return this.Executing;
        }
        public boolean isCurrentlyingRunning(){
            return this.currentlyRunning;
        }
    }
    private class Creator implements Runnable{
    private boolean Running = true, Executing = true,CurrentlyRunning = false;;
        @Override
        public void run() {
            int xx, yy,x,y;
            Chunk c;
            this.CurrentlyRunning = true;
            while(this.Running){ 
               try {
                    if(this.Executing){
                       for(xx = - DistanceTilDestory - LoadedDistance;xx< +DistanceTilDestory+LoadedDistance;xx++){
                           for(yy = - DistanceTilDestory - LoadedDistance;yy< +DistanceTilDestory+LoadedDistance;yy++){
                               x = (int) (p.getX()/256);
                               y = (int) (p.getY()/256);
                               c = getChunkAt(x+xx,y+yy);
                               if(c == null){
                                   System.out.println(x+xx);
                                   System.out.println(y+yy);
                                   System.out.println();
                                   if(!this.Executing){
                                       break;
                                   }
                                   c = getChunk(x+xx,y+yy);
                                   scr.addGraphicObject(c.getBlocksForScreen());
                                   scr.addGraphicObject(c.getCreaturesForScreen());
                                   chunk.add(c);
                               }
                               else if((LoadedDistance > xx && xx > -LoadedDistance)&&(LoadedDistance > yy && yy > -LoadedDistance)&&c.isDisabled()){
                                   c.setDisabled(false);
                                   System.out.println("treesponk");
                               }
                               Thread.sleep(World.SLEEPERTHREAD/10);
                               if(!this.Executing||!this.Running){
                                    break;
                               }
                           }
                           
                       } 
                    }
                    
                    Thread.sleep(World.SLEEPERTHREAD);
                }catch (InterruptedException ex) {
                    MasterClose.CloseWithError(ex);
                } catch (FileNotFoundException ex) {
                    ErrorLog.error(ex);
                } catch (IOException ex) {
                    ErrorLog.error(ex);
                }
            }
            this.CurrentlyRunning = false;
        }
        public void setRunning(boolean Running){
            this.Running = Running;
        }
        public void setExecuting(boolean execute){
            this.Executing = execute;
        }
        public boolean isRunning(){
            return this.Running;
        }
        public boolean isExecuting(){
            return this.Executing;
        }
        public boolean isCurrentlyRunning(){
            return this.CurrentlyRunning;
        }
    }
    private class Runner implements Runnable{
    private boolean Running = true, Executing = true,CurrentlyRunning = false;
        @Override
        public void run() {
            int i,ii;
            List<CreatureManager> e;
            this.CurrentlyRunning = true;
            try {
                while(this.Running){
                    if(this.Executing){
                        for(i = 0; i < chunk.size();i++){
                            if(!chunk.get(i).isDisabled()){
                                chunk.get(i).update();
                                e = chunk.get(i).getMisPlacedTargets();
                                for(ii = 0; ii < e.size();ii++){
                                    getChunkAt((int)e.get(ii).getX()/256,(int)e.get(ii).getY()/256).addCreature(e.get(ii));
                                }
                            }
                        }
                        p.Update();
                    }
                    Thread.sleep(World.SLEEPERTHREAD);
                }
                
            } catch (InterruptedException ex) {
                MasterClose.CloseAll();
            }
            this.CurrentlyRunning = false;
        }
        public void setRunning(boolean Running){
            this.Running = Running;
        }
        public void setExecuting(boolean execute){
            this.Executing = execute;
        }
        public boolean isRunning(){
            return this.Running;
        }
        public boolean isExecuting(){
            return this.Executing;
        }
        public boolean isCurrentlyRunning(){
            return this.CurrentlyRunning;
        }
    }
    private class Saver implements Runnable{
    private boolean Running = true, Executing = true,currentlyRunning = false;;
        @Override
        public void run() {
            int i;
            Chunk c;
            this.currentlyRunning = true;
            try {
                while(this.Running){
                    if(this.Executing){
                        for(i = 0; i < chunk.size();i++){
                            c = chunk.get(i);
                            SaveChunk(c);
                            if(c.hasReachedFinalSave()){
                                chunk.remove(c);
                                i--;
                            }
                        }
                    }
                    Thread.sleep(World.SLEEPERTHREAD*1);
                }
                
            } catch (InterruptedException ex) {
                MasterClose.CloseAll();
            } catch (IOException ex) {
                ErrorLog.error(ex);
            }
            this.currentlyRunning = false;
        }
        public void setRunning(boolean Running){
            this.Running = Running;
        }
        public void setExecuting(boolean execute){
            this.Executing = execute;
        }
        public boolean isRunning(){
            return this.Running;
        }
        public boolean isExecuting(){
            return this.Executing;
        }
        public boolean isCurrentlyRunning(){
            return this.currentlyRunning;
        }
    }
    public class Closer extends ExternalClose{
        @Override
        protected void closeObject() {
            run.setRunning(false);
            del.setRunning(false);
            cre.setRunning(false);
            sav.setRunning(false);
            while(!isReady()||run.isCurrentlyRunning()||del.isCurrentlyingRunning()||cre.isCurrentlyRunning()||cre.isCurrentlyRunning()||sav.isCurrentlyRunning()){
                try {
                    Thread.sleep(100);
                } catch (InterruptedException ex) {
                    Logger.getLogger(World.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            DestroyWorld();
        }
    }
    public boolean isReady(){
        return this.ready;
    }
    public static World getCurrentInstance(){
        return World.currentInstance;
    }
}