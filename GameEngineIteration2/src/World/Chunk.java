/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package World;

import Graphics.Base.ScreenGraphic;
import Graphics.Forum.GraphicsObject;
import Graphics.Forum.Screen;
import Save.Save;
import Save.SaveObject;
import Save.SaveObjectPackage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;
import org.lwjgl.LWJGLException;
import org.lwjgl.util.vector.Vector2f;

/**
 *
 * @author Allazham
 */
public final class Chunk extends ScreenGraphic{
    private static final Screen scr = Screen.getInstance();
    private boolean Visiable,tileUpdated = true;
    byte ChunkX, ChunkY;
    Region region;  
    List<Entry> entry = new ArrayList();
    Tile[][] Tiles = new Tile[16][16];
    public Chunk(Region region,byte ChunkX,byte ChunkY){
        super(true);
        super.setPriority(50);
        this.region = region;
        this.ChunkX = ChunkX;
        this.ChunkY = ChunkY;
    }
    public void setVisiable(boolean visiable){
        this.Visiable = visiable;
        int x,y;
        for(x = 0; x < this.entry.size();x++){
            this.entry.get(x).setDisabled(!visiable);
        }
    }
    public boolean isVisiable(){
        return this.Visiable;
    }
    public boolean hasBlockUpdated(){
        return this.tileUpdated;
    }
    public void claimTilesSaved(){
        this.tileUpdated = false;
    }
    public Tile getTileAt(int x,int y){
        return Tiles[x][y];
    }
    public Tile[][] getTileArea(int x, int y,int Width,int Height){
        if(x < 0 || y < 0 || Width < 0 || Height < 0||x + Width > 15 || y + Height > 15){
            throw new IndexOutOfBoundsException("values are outside norms");
        }
        Tile[][] Tile = new Tile[Width][Height];
        int xx,yy;
        for(xx = x;xx<Width+x;xx++){
            for(yy = y;yy<Height+y;yy++){
                Tile[xx-x][yy-y] = this.Tiles[xx][yy];
            }
        }
        return Tile;
    }
    public boolean isEmpty(){
        int x,y;
        for(x = 0; x < 16;x++){
            for(y = 0; y < 16; y++){
                if(this.Tiles[x][y] != null){
                    return false;
                }
            }
        }
        return true;
    }
    public Tile[][] getAllTile(){
        return this.Tiles;
    }
    public void setTileAt(Tile b){
        this.Tiles[b.x][b.y] = b;
        this.tileUpdated = true;
    }
    public void removeTile(int x, int y){
        this.Tiles[x][y] = null;
        this.tileUpdated = true;
    }
    public int getChunkX(){
        return this.ChunkX;
    }
    public int getChunkY(){
        return this.ChunkY;
    }
    public Entry getEntry(UUID uid){
        int i;
        for(i = 0; i < this.entry.size();i++){
            if(this.entry.get(i).getUUID().equals(uid)){
                return this.entry.get(i);
            }
        }
        return null;
    }
    public boolean removeEntry(UUID uid){
        int i;
        for(i = 0; i < this.entry.size();i++){
            if(this.entry.get(i).getUUID().equals(uid)){
                Entry e = this.entry.remove(i);
                scr.removeGraphicsObject(e.getGraphicObjects());
                return true;
            }
        }
        return false;
    }
    LinkedList<Entry> getOutOfBoundsEntries(){
        LinkedList<Entry> OFBE = new LinkedList();
        int i;
        Entry e;
        for(i = 0; i < this.entry.size();i++){
            e = this.entry.get(i);
            if(!((int)Math.floor(e.getLocationX()/16) == this.ChunkX+this.region.RegionX*16&&((int)Math.floor(e.getLocationY()/16)) == this.ChunkY+this.region.RegionY*16)){
                OFBE.add(this.entry.remove(i));
            }
        }
        return OFBE;
    }
    public LinkedList<Entry> getEntriesInPoint(float x, float y){
        LinkedList<Entry> LL = new LinkedList();
        int i;
        for(i = 0; i < this.entry.size();i++){
            if(entry.get(i).Intersect(x,y)){
                LL.add(entry.get(i));
            }
        }
        return LL;
    }
    public LinkedList<Entry> getEntriesInArea(float x, float y, float scale){
        LinkedList<Entry> LL = new LinkedList();
        int i;
        for(i = 0; i < this.entry.size();i++){
            if(scale > Math.sqrt(Math.pow(x-this.entry.get(i).getLocationX(), 2)+Math.pow(y-this.entry.get(i).getLocationY(), 2))&&this.entry.get(i).getScale() >= 0){
                LL.add(this.entry.get(i));
            }
        }
        return LL;
    }
    public LinkedList<Entry> getEntriesIntersection(Entry e){
        LinkedList<Entry> LL = new LinkedList();
        int i;
        for(i = 0; i < this.entry.size();i++){
            if(e.Intersect(this.entry.get(i))){
                LL.add(this.entry.get(i));
            }
        }
        return LL;
    }
    public ArrayList<GraphicsObject> getGraphics(){
        ArrayList<GraphicsObject> graph = new ArrayList();
        int i;
        graph.add(this);
        for(i = 0; i < this.entry.size();i++){
            graph.addAll(this.entry.get(i).getGraphicObjects());
        }
        return graph;
        
    }
    public void addEntry(Entry e){
        this.entry.add(e);
    }
    public void setupTiles(Tile[][] b){
        this.Tiles = b;
    }
    public Vector2f getLocationVector(){
        return new Vector2f((ChunkX*16)+(region.RegionX*256),(ChunkY*16)+(region.RegionY*256));
    }
    @Override
    public void setDisabled(boolean disabled){
        super.setDisabled(disabled);
        int i;
        for(i = 0; i < this.entry.size();i++){
            this.entry.get(i).setDisabled(disabled);
        }
    }
    public SaveObjectPackage getSavePackage(){
        SaveObjectPackage pack = new SaveObjectPackage(new String[]{""+this.entry.size()});
        SaveObjectPackage o;
        int i;
        pack.addSaveObject(new TileData(this.Tiles));
        for(i = 0; i < this.entry.size();i++){
            o = entry.get(i).getSave();
            o.setName("Entry"+i);
            pack.addPackage(o);
        }
        this.tileUpdated = false;
        return pack;
    }
    public SaveObjectPackage getSaveTilePackage(){
        SaveObjectPackage pack = new SaveObjectPackage();
        pack.addSaveObject(new TileData(this.Tiles));
        this.tileUpdated = false;
        return pack;
    }
    public SaveObjectPackage getSaveEntriesPackage(){
        SaveObjectPackage pack = new SaveObjectPackage(new String[]{""+this.entry.size()});
        SaveObjectPackage o;
        int i;
        for(i = 0; i < this.entry.size();i++){
            o = entry.get(i).getSave();
            o.setName("Entry"+i);
            pack.addPackage(o);
        }
        
        return pack;
    }
    @Override
    protected void render() throws LWJGLException {
        int x, y;
        for(x = 0; x < 16; x++){
            for (y = 0; y < 16; y ++){
                if(Tiles[x][y] != null){
                    this.Tiles[x][y].render();
                }
            }
        }
    }

    @Override
    public void disposeGraphics() {
        
    }
    public static class TileData extends SaveObject{
        private final String data;
        public TileData(Tile[][] Tiles){
            StringBuilder b = new StringBuilder();
            super.setName("TileData");
            int x,y;
            //int count = 0;
            for(x = 0; x < 16;x++){
                for(y = 0; y < 16;y++){
                    if(Tiles[x][y] != null){
                        b.append(Tiles[x][y].type);
                    }
                    else{
                        b.append("n");
                    }
                    if(!(15 == x && 15== y)){
                       b.append(",");
                    }
                }
            }
            data = b.toString();
        }

        @Override
        public void addToSave(Save pw) throws IOException {
            pw.print(data);
        }
    }
}
