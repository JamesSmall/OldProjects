/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package World;

import Graphics.Base.ScreenGraphic;
import Graphics.Utils.DirectUpdateThread;
import Graphics.Forum.Screen;
import Graphics.Utils.Camera2D;
import Save.SaveObjectPackage;
import Save.StatManager.StatList;
import Utils.ErrorLog;
import Utils.Timer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;
import org.lwjgl.util.vector.Vector2f;

/**
 *
 * @author Allazham
 */
public final class Cell{
    private static final Camera2D cam = Camera2D.getCamera();
    private static final Screen scr = Screen.getInstance();
    private static final long ThreadSleepyTime = 500;
    private static final int LoadedDistance = 1;//SeeableDistance = 1;
    
    private boolean screenSeeable = false;
    private boolean isReady = false;
    private final ArrayList<Entry> Player = new ArrayList();
    private final ArrayList<Entry> PersistantEntries = new ArrayList();
    private final ArrayList<Region> regions = new ArrayList();
    private final ChunkControl ChunkControl = new ChunkControl();
    //private final SaveControl SaveManager = new SaveControl();
    private final Updater Updater = new Updater();
    private final ChunkLoader ChunkLoader = new ChunkLoader();
    private final Visiability sight = new Visiability();
    private final GraphicsUpdate GU = new GraphicsUpdate();
    private final LinkedList<PositionRequest> requests = new LinkedList();
    private final LinkedList<Region> closingRegions = new LinkedList();
    private final StatList StatList;
    private String name = "WorldCell";
    public Cell(){
        this.StatList = new StatList();
    }
    public Cell(StatList sl){
        this.StatList = sl;
    }
    public void start() {
        new Thread(Updater).start();
        //new Thread(SaveManager).start();
        //new Thread(ChunkControl).start();
        new Thread(ChunkLoader).start();
        new Thread(sight).start();
    }
    boolean containsRegionOrRequest(int x, int y){
        for(PositionRequest r: this.requests){
            if(r.x == x && r.y == y){
                return true;
            }
        }
        for(Region r: this.regions){
            if(r.RegionX == x && r.RegionY == y){
                return true;
            }
        }
        
        return false;
    }
    void clear(){
        this.closingRegions.clear();
        this.regions.clear();
    }
    void RequestRegion(int x, int y){
        if(!this.containsRegionOrRequest(x, y)){
            this.requests.add(new PositionRequest(x,y));
        }
    }
    void RemoveRegion(Region r){
        this.regions.remove(r);
        this.closingRegions.add(r);
    }
    LinkedList<PositionRequest> getPositionRequestList(){
        return this.requests;
    }
    LinkedList<Region> getClosingRegions(){
        return this.closingRegions;
    }
    ArrayList<Region> getAndRemoveClosingRegions(){
        ArrayList l = new ArrayList(this.closingRegions);
        this.closingRegions.clear();
        return l;
    }
    public boolean hasTilesUpdated(){
        for(Region r:this.regions){
            if(r.hasTileUpdated()){
                return true;
            }
        }
        return false;
    }
    boolean containsRegion(int x, int y){
        if(this.getRegionAt(x,y) != null){
            return true;
        }
        for(Region r: this.closingRegions){
            if(r.RegionX == x && r.RegionY == y){
                return true;
            }
        }
        return false;
    }
    public ArrayList<Region> getActiveRegions(){
        return new ArrayList(this.regions);
    }
    public void setScreenVisiable(){
        scr.addDirectUpdateThread(GU);
        this.screenSeeable = true;
    }
    public void setScreenInvisiable(){
        this.screenSeeable = false;
        scr.removeDirectUpdateThread(GU);
    }
    public void setName(String name){
        this.name = name;
    }
    public String getName() {
        return this.name;
    }
    public void addPlayer(Entry p){
        this.Player.add(p);
        p.setCell(this);
        scr.addGraphicObject(p.getGraphicObjects());
    }
    public SaveObjectPackage getPeristantEntries(){
        SaveObjectPackage pack = new SaveObjectPackage(new String[]{""+this.PersistantEntries.size()});
        SaveObjectPackage o;
        pack.addSaveObject(this.StatList.getSave());
        for(int i = 0; i < this.PersistantEntries.size();i++){
            o = this.PersistantEntries.get(i).getSave();
            o.setName("Entry"+i);
            pack.addPackage(o);
        }
        return pack;
    }
    public void removePlayer(Entry p){
        this.Player.remove(p);
        scr.removeGraphicsObject(p.getGraphicObjects());
    }
    public List<Entry> getPlayers(){
        return this.Player;
    }
    public Region getRegionAt(int x, int y){
        int i;
        for(i = 0; i < this.regions.size();i++){
            if(this.regions.get(i).RegionX == x && this.regions.get(i).RegionY == y){
                return this.regions.get(i);
            }
        }
        return null;
    }
    public Chunk getChunkAt(int x, int y){
        Region r;
        if(x < 0 && y < 0){
            r = this.getRegionAt((x-15)/16, (y-15)/16);
        }
        else if(x < 0){
            r = this.getRegionAt((x-15)/16, y/16);
        }
        else if(y < 0){
            r = this.getRegionAt(x/16, (y-15)/16);
        }
        else{
            r = this.getRegionAt(x/16, y/16);
        }
        int xx, yy;
        if(r != null){
            xx = x%16;
            yy = y%16;
            if(xx < 0){
                xx+=16;
            }
            if(yy < 0){
                yy+=16;
            }
            return r.getChunkAt(xx, yy);
        }
        return null;
    }
    public Tile getTileAt(int x, int y){
        Chunk c;
        if(x < 0 && y < 0){
            c = this.getChunkAt((x-15)/16,(y-15)/16);
        }
        else if(x < 0){
            c = this.getChunkAt((x-15)/16,y/16);
        }
        else if(y < 0){
            c = this.getChunkAt(x/16,(y-15)/16);
        }
        else{
            c = this.getChunkAt(x/16,y/16);
        }
        int xx = x%256,yy = y %256;
        if(c  != null){
            if(xx < 0){
                xx+=256;
            }
            if(yy < 0){
                yy+=256;
            }
            xx %= 16;
            yy %= 16;
            if(xx < 0){
                xx+=16;
            }
            if(yy < 0){
                yy+=16;
            }
            return c.getTileAt(xx, yy);
        }
        return null;
    }
    public void addEntry(Entry e){
        int[] chk = WorldUtils.getChunkPos(e.getLocationX(), e.getLocationY());
        Chunk c = this.getChunkAt(chk[0],chk[1]);
        if(e.isPersistent()){
            e.setCell(this);
            this.PersistantEntries.add(e);
        }
        else{
            if(c != null){
                e.setCell(this);
                Cell.scr.addGraphicObject(e.getGraphicObjects());
                c.addEntry(e);
            }
        }
    }
    public void addEntries(List<Entry> e){
        int i;
        for(i = 0; i < e.size();i++){
            this.addEntry(e.get(i));
        }
    }
    private void addEntriesAlreadyExistant(List<Entry> e){
        int i;
        for(i = 0; i < e.size();i++){
            this.addEntryAlreadyExistant(e.get(i));
        }
    }
    private void addEntryAlreadyExistant(Entry e){
        int[] chk = WorldUtils.getChunkPos(e.getLocationX(), e.getLocationY());
        Chunk c = this.getChunkAt(chk[0],chk[1]);
        if(c != null){
            c.addEntry(e);
        }
    }
    public Entry getEntryByUUID(UUID uid){
        int i;
        Entry e;
        for(i = 0; i < this.regions.size();i++){
            e = this.regions.get(i).getEntry(uid);
            if(e != null){
                return e;
            }
        }
        return null;
    }
    public boolean removeEntry(UUID uid){
        int i;
        boolean e;
        for(Entry ee: this.PersistantEntries){
            if(ee.getUUID().equals(uid)){
                this.PersistantEntries.remove(ee);
                return true;
            }
        }
        for(i = 0; i < this.regions.size();i++){
            e = this.regions.get(i).removeEntry(uid);
            if(e){
                return true;
            }
        }
        return false;
    }
    public void removeEntry(Entry e){
        this.removeEntry(e.getUUID());
    }
    public LinkedList<Entry> getEntriesInPoint(float x, float y){
        int[] locLesser = WorldUtils.getChunkPos(x-1, y-1);
        int[] locGreater = WorldUtils.getChunkPos(x+1, y+1);
        LinkedList<Entry> entries = new LinkedList();
        int i;
        if(Arrays.equals(locLesser, locGreater)){
            Chunk c;
            c = this.getChunkAt(locLesser[0], locLesser[1]);
            if(c != null){
                entries.addAll(c.getEntriesInPoint(x, y));
            }
        }
        else if(locLesser[0] != locGreater[0] || locLesser[1] != locGreater[1]){
            Chunk c,c2;
            c = this.getChunkAt(locLesser[0], locLesser[1]);
            c2 = this.getChunkAt(locGreater[0], locGreater[1]);
            if(c != null){
                entries.addAll(c.getEntriesInPoint(x, y));
            }
            if(c2 != null){
                entries.addAll(c2.getEntriesInPoint(x, y));
            }
        }
        else{
            Chunk c,cx,cy,cxy;
            c = this.getChunkAt(locLesser[0],locLesser[1]);
            cx = this.getChunkAt(locGreater[0],locLesser[1]);
            cy = this.getChunkAt(locLesser[0],locGreater[1]);
            cxy = this.getChunkAt(locGreater[0],locGreater[1]);
            if(c != null){
                entries.addAll(c.getEntriesInPoint(x, y));
            }
            if(cx != null){
                entries.addAll(cx.getEntriesInPoint(x, y));
            }
            if(cy != null){
                entries.addAll(cy.getEntriesInPoint(x, y));
            }
            if(cxy != null){
                entries.addAll(cxy.getEntriesInPoint(x, y));
            }
        }
        for(i = 0; i < this.Player.size();i++){
            if(this.Player.get(i).Intersect(x, y)){
                entries.add(Player.get(i));
            }
        }
        return entries;
    }
    public LinkedList<Entry> getEntriesInArea(float x, float y,float dist){
        LinkedList<Entry> entries = new LinkedList();
        int[] locLesser = WorldUtils.getChunkPos(x-1-dist, y-1-dist);
        int[] locGreater = WorldUtils.getChunkPos(x+1+dist, y+1+dist);
        Chunk c;
        int xx,yy;
        for(xx = locLesser[0];xx <= locGreater[0];xx++){
            for(yy = locLesser[1];yy <= locGreater[1];yy++){
                c = this.getChunkAt(xx, yy);
                if(c != null){
                    entries.addAll(c.getEntriesInArea(x, y, dist));
                }
            }
        }
        for(xx = 0; xx < this.Player.size();xx++){
            if(dist > Math.sqrt(Math.pow(x-this.Player.get(xx).getLocationX(), 2)+Math.pow(y-this.Player.get(xx).getLocationY(), 2))&&this.Player.get(xx).getScale() >= 0){
                entries.add(this.Player.get(xx));
            }
        }
        return entries;
    }
    public void stop() {
        this.isReady = false;
        Updater.setRunning(false);
//        SaveManager.setRunning(false);
        ChunkControl.setRunning(false);
        ChunkLoader.setRunning(false);
        sight.setRunning(false);
    }
    public boolean isReady(){
        return this.isReady;
    }
    public boolean isRunning() {
        return this.Updater.isAlive()|/*SaveManager.isAlive()*/ChunkControl.isAlive()|ChunkLoader.isAlive()|sight.isAlive();
    }

    public void addRegion(Region r){
        this.isReady = true;
        if(!this.containsRegion(r.RegionX, r.RegionY)){
            this.regions.add(r);
            if(this.screenSeeable){
                scr.addGraphicObject(r.getGraphics());
            }
        }
    }
    public void removeRegion(int x, int y){
//        this.SaveManager.CloseRegion(this.getRegionAt(x, y));
    }
    public boolean isEmpty(){
        return this.regions.isEmpty();
    }
    public List<Region> getRegions(){
        return this.regions;
    }
    /*
    private class SaveControl implements Runnable{
        private final LinkedList<Region> toSave = new LinkedList();
        private final LinkedList<PositionRequest> toLoad = new LinkedList();
        private boolean Running,Active,Alive = false,SaveManagement = true;
        //private long lastUpdateTime;
        private int count;
        private final SaveManager sm = new SaveManager();
        @Override
        public void run() {
            Thread.currentThread().setName("Save Control");
            LinkedList<SaveData> SD = new LinkedList();
            int i;
            Region reg;
            SaveData s;
            PositionRequest r;
            this.Running = true;
            this.Active = true;
            count = 0;
            Alive = true;
            Crashable:{
            try {
                Thread.sleep(100);
            } catch (InterruptedException ex) {
             //   Logger.getLogger(Cell.class.getName()).log(Level.SEVERE, null, ex);
            }
            sm.lock();
            sm.unlock();
            while(this.Running){
                //lastUpdateTime = Sys.getTime();
                if(this.Active){
                    //saves regions removed from memory
                    synchronized(toSave){
                    if(sm.checkLockFile()){
                        MasterClose.CloseAll();
                        break Crashable;
                    }
                    sm.lock();
                    while(toSave.size() > 0){
                        try {
                            reg = toSave.removeFirst();
                            scr.removeGraphicsObject(reg.getGraphics());
                            
                            sm.saveRegion(getCell(), reg.getSavePackage(),reg.RegionX,reg.RegionY);
                        } catch (IOException ex) {
                            ErrorLog.error(ex);
                        }
                    }
                    sm.unlock();
                    }
                    //loads regions into game
                    synchronized(toLoad){
                        while(toLoad.size()>0){
                            //try{
                                r = toLoad.removeFirst();
                                if(getRegionAt(r.x,r.y) == null){
                                    //toLoad.removeFirst();
                                    Thread.yield();
                                    reg = sm.loadRegion(getCell(), r.x, r.y);
                                    if(reg!= null){
                                        regions.add(reg);
                                        scr.addGraphicObject(reg.getGraphics());
                                    }
                                }
                            //}
                            //catch(Exception ex){
                                //ErrorLog.error(ex);
                            //}
                        }
                    }
                    if(count == 500 && SaveManagement){
                        count = -1;
                        //synchronized(regions){
                            if(sm.checkLockFile()){
                                MasterClose.CloseAll();
                                break Crashable;
                            }
                            sm.lock();
                            for(i = 0; i < regions.size();i++){
                                reg = regions.get(i);
                                SD.add(new SaveData(reg.getSavePackage(),reg.RegionX,reg.RegionY));
                            }
                            while(SD.size() > 0){
                                s = SD.removeFirst();
                                try {
                                    sm.saveRegion(getCell(),s.pack,s.x,s.y);
                                    Thread.sleep(5);
                                } catch (IOException | InterruptedException ex) {
                                    ErrorLog.error(ex);
                                }
                            }
                            while(SD.size() > 0){
                                if(!this.Active){
                                    break;
                                }
                                s = SD.removeFirst();
                                try {
                                    Thread.yield();
                                    sm.saveRegion(getCell(), s.pack, s.x, s.y);
                                } catch (IOException ex) {
                                //Logger.getLogger(Cell.class.getName()).log(Level.SEVERE, null, ex);
                                    ErrorLog.error(ex);
                                }
                            }
                            sm.unlock();
                        //}
                    }
                    if(count == 2){
                        isReady = true;
                    }
                    count++;
                }
                if(this.Running){
                try {
                    Thread.sleep(100);
                } catch (IllegalArgumentException|InterruptedException ex) {
                    //ErrorLog.error(ex);
                }
                }
            }
            Thread.currentThread().setPriority(5);
            //closing save
            //synchronized(regions){
                if(sm.checkLockFile()){
                    MasterClose.CloseAll();
                    break Crashable;
                }
                sm.lock();
                            for(i = 0; i < regions.size();i++){
                                try {
                                    reg = regions.get(i);
                                    if(!MasterClose.isAttemptingClose()){
                                        scr.removeGraphicsObject(reg.getGraphics());
                                    }
                                    if(reg.isEmpty()){
                                        sm.RemoveRegion(getCell(), reg.RegionX,reg.RegionY);
                                    }
                                    else{
                                        sm.saveRegion(getCell(),reg.getSavePackage(),reg.RegionX,reg.RegionY);
                                    }
                                } catch (IOException ex) {
                                    ErrorLog.error(ex);
                                }
                            }
                        //}
                        sm.unlock();
            
            }
            this.Alive = false;
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
        public void CloseRegion(Region r){
            this.toSave.add(r);
        }
        public void RequestLoadRegion(PositionRequest r){
            synchronized(toLoad){
                this.toLoad.add(r);
            }
        }
    }
    */
    private class Updater implements Runnable{
        private volatile boolean Running,Active,Alive;
        private final Timer t = new Timer();
        private long lastFrame;
        private long getTime(){
            return System.nanoTime() / 1000000;
        }
        public int getDelta() {
            long time = getTime();
            int delta = (int) (time - lastFrame);
            lastFrame = time;
    	
            return delta;
        }
        @Override
        public void run() {
            int delta;
            Thread.currentThread().setName("Updater");
            int i,ii,x,y;
            Region r;
            ArrayList<Entry> remove = new ArrayList();
            this.Running = true;
            this.Active = true;
            this.Alive = true;
            this.lastFrame = this.getTime();
            while(this.Running){
                delta = this.getDelta();
                if(delta > 20){
                    delta = 0;
                }
                if(this.Active){
                    for(i = 0; i < regions.size();i++){
                        r = regions.get(i);
                        //if(!r.isUpdatesDisabled()){
                            for(x = 0; x < 16; x++){
                                for(y = 0; y < 16; y++){
                                    Chunk c = r.chunks[x][y];
                                    if(c != null){
                                        for(ii = 0; ii < c.entry.size();ii++){
                                            if(c.entry.get(ii).isMarkedForDelete()){
                                                remove.add(c.entry.get(ii));
                                            }
                                            else{
                                                c.entry.get(ii).Update(delta);
                                            }
                                        }
                                    }
                                }
                           // }
                        }
                    }
                    for(Entry e: Player){
                        e.Update(delta);
                    }
                    for(Entry e: PersistantEntries){
                        if(e.isMarkedForDelete()){
                            remove.add(e);
                        }
                        else{
                            e.Update(delta);
                        }
                    }
                    for(i = 0; i < regions.size();i++){
                        r = regions.get(i);
                        for(x = 0; x < 16; x++){
                            for(y = 0; y < 16; y++){
                                Chunk c = r.chunks[x][y];
                                if(c != null){
                                    for(ii = 0; ii < c.entry.size();ii++){
                                        if(!c.entry.get(ii).isMarkedForDelete()){
                                            c.entry.get(ii).UpdateLocation(delta);
                                            addEntriesAlreadyExistant(c.getOutOfBoundsEntries());
                                        }
                                    }
                                }
                            }
                        }
                    }
                    for(i = 0; i < Player.size();i++){
                        Player.get(i).UpdateLocation(delta);
                    }
                    for(Entry e: PersistantEntries){
                        e.UpdateLocation(delta);
                    }
                    for(Entry ez: remove){
                        removeEntry(ez);
                    }
                    remove.clear();
                }
                t.sync(30);
            }
            this.Alive = false;
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
    }
    private class ChunkControl implements Runnable{
        private boolean Running,Active,Alive;
        @Override
        public void run() {
            Thread.currentThread().setName("Chunk Control");
            int i,ii;
            Region r;
            Entry p;
            this.Running = true;
            this.Active = true;
            this.Alive = true;
            while(this.Running){
                if(this.Active){
                    for(i = 0; i < regions.size();i++){
                        r = regions.get(i);
                        Breakable:{
                            for(ii = 0; ii < Player.size();ii++){
                                p = Player.get(ii);
                                Vector2f loc = this.ConvertToGlobal(p.getLocationVector());
                                if(r.RegionX - LoadedDistance -1 < loc.x && loc.x < r.RegionX + LoadedDistance + 1&& r.RegionY - LoadedDistance -1< loc.y && loc.y < r.RegionY + LoadedDistance +1){
                                    break Breakable;
                                }
                            }
                            RemoveRegion(r);
                        }
                        try {
                            Thread.sleep(10);
                        } catch (InterruptedException ex) {
                            ErrorLog.error(ex);
                        }
                    }
                }
                try {
                    Thread.sleep(ThreadSleepyTime);
                } catch (InterruptedException ex) {
                    ErrorLog.error(ex);
                }
            }
            this.Alive = false;
        }
        private Vector2f ConvertToGlobal(Vector2f loc){
            Vector2f ret = new Vector2f(loc.x/256,loc.y/256);
            return ret;
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
    }
    private class ChunkLoader implements Runnable{
        private volatile boolean Running,Active,Alive;
        @Override
        public void run() {
            Thread.currentThread().setName("Chunk Loader");
            int i;
            int x,y;
            int minX,minY,maxX,maxY;
            Region r;
            Vector2f p;
            this.Alive = true;
            this.Running = true;
            this.Active = true;
            while(this.Running){
                if(this.Active){
                    for(i = 0; i < Player.size();i++){
                        p = this.ConvertToGlobal(Player.get(i).getLocationVector());
                        minX = ((int)p.x) - LoadedDistance;
                        minY = ((int)p.y) - LoadedDistance;
                        maxX = ((int)p.x) + LoadedDistance;
                        maxY = ((int)p.y) + LoadedDistance;
                        for(x = minX;x<=maxX;x++){
                            for(y = minY;y<=maxY;y++){
                                r = getRegionAt(x,y);
                                if(r == null){
                                    RequestRegion(x,y);
                                }
                            }
                        }
                    }
                    
                }
                try {
                    Thread.sleep(ThreadSleepyTime*10);
                } catch (IllegalArgumentException ex) {
                    ErrorLog.error(new OverTimeError("Cannot Keep up in"+this.getClass().getCanonicalName()+", did System time change or is the thread overEncombered"));
                } catch (InterruptedException ex) {
                    ErrorLog.error(ex);
                }
                
            }
            this.Alive = false;
        }
        private Vector2f ConvertToGlobal(Vector2f loc){
            Vector2f ret = new Vector2f(loc.x/256,loc.y/256);
            return ret;
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
    }
    private class Visiability implements Runnable{
        private volatile boolean Running,Active,Alive;
        @Override
        public void run() {
            Thread.currentThread().setPriority(3);
            Thread.currentThread().setName("Sight Thread");
            Region r;
            Chunk c;
            int i,x,y;
            this.Running = true;
            this.Active = true;
            this.Alive = true;
            while(this.Running){
                if(this.Active){
                    for(i = 0;i<regions.size();i++){
                        r = regions.get(i);
                        for(x=0;x<16;x++){
                            for(y = 0; y < 16; y++){
                                c = r.chunks[x][y];
                                if(c != null){
                                    c.setDisabled(this.ShouldBeDisabled(c.getLocationVector()));
                                }
                            }
                        }
                    }
                }
                try {
                    Thread.sleep(ThreadSleepyTime*5);
                } catch (IllegalArgumentException ex) {
                    ErrorLog.error(new OverTimeError("Cannot Keep up in"+this.getClass().getCanonicalName()+", did  time change or is the thread overEncombered"));
                } catch (InterruptedException ex) {
                    ErrorLog.error(ex);
                }
                
            }
            this.Alive = false;
        }
        private boolean ShouldBeDisabled(Vector2f loc){
            Vector2f ret = ScreenGraphic.ConvertWorldToScreen(loc);
            return (!(ret.x < cam.getInternalScreenX()*2 && ret.x > cam.getInternalScreenX()*-2 && ret.y < cam.getInternalScreenY()*2 && cam.getInternalScreenY()*-2 < ret.y));  
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
    }
    private class GraphicsUpdate implements DirectUpdateThread{
        @Override
        public void Update() {
            int i,x,y,ii;
            Region r;
                //synchronized(regions){
                    for(i = 0; i < regions.size();i++){
                        r = regions.get(i);
                        for(x = 0; x < 16; x++){
                            for(y = 0; y < 16; y++){
                                Chunk c = r.chunks[x][y];
                                if(c != null){
                                    for(ii = 0; ii < c.entry.size();ii++){
                                        c.entry.get(ii).UpdateGraphicLocation();
                                    }
                                }
                            }
                        }
                    }
                    for(i = 0; i < Player.size();i++){
                        Player.get(i).UpdateGraphicLocation();
                    } 
            //}
        }
    }
}
