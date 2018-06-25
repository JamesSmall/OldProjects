/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package World;

import Graphics.Forum.GraphicsObject;
import Graphics.Forum.Screen;
import Save.SaveObjectPackage;
import java.util.ArrayList;
import java.util.UUID;

/**
 *
 * @author Allazham
 */
public final class Region {
    int RegionX,RegionY;
    Cell c;
    Chunk[][] chunks = new Chunk[16][16];
    public Region(Cell c,int x, int y){
        this.c = c;
        this.RegionX = x;
        this.RegionY = y;
    }
    public Chunk getChunkAt(int x, int y){
        return chunks[x][y];
    }
    public int getLocationX(){
        return this.RegionX;
    }
    public int getLocationY(){
        return this.RegionY;
    }
    public void setupChunk(Chunk[][] c){
        this.chunks = c;
    }
    public void setChunk(Chunk c){
        this.chunks[c.ChunkX][c.ChunkY] = c;
        Screen.getInstance().addGraphicObject(c);
    }
    public void setVisiable(boolean visable){
        int x,y;
        for(x = 0; x < 16;x++){
            for(y = 0; y < 16;y++){
                chunks[x][y].setVisiable(visable);
            }
        }
    }
    public boolean hasTileUpdated(){
        int x,y;
        for(x = 0; x < 16;x++){
            for(y = 0; y < 16; y++){
                if(this.chunks[x][y] != null){
                    if(this.chunks[x][y].hasBlockUpdated()){
                        return true;
                    }
                }
            }
        }
        return false;
    }
    public void removeChunk(int x, int y){
        Chunk chk = this.chunks[x][y];
        if(chk != null){
            Screen.getInstance().removeGraphicsObject(chk);
        }
        this.chunks[x][y] = null;
    }
    public Entry getEntry(UUID uid){
        int x,y;
        Entry e;
        for(x = 0; x < 16;x++){
            for(y = 0; y < 16;y++){
                if(chunks[x][y] != null){
                    e = chunks[x][y].getEntry(uid);
                    if(e != null){
                        return e;
                    }
                }
            }
        }
        return null;
    }
    public boolean removeEntry(UUID uid){
        int x,y;
        boolean e;
        for(x = 0; x < 16;x++){
            for(y = 0; y < 16;y++){
                e = chunks[x][y].removeEntry(uid);
                if(e){
                    return true;
                }
            }
        }
        return false;
    }
    public boolean isEmpty(){
        int x,y;
        for(x = 0; x < 16;x++){
            for(y = 0; y < 16; y++){
                if(this.chunks[x][y] != null){
                    if(!this.chunks[x][y].isEmpty()){
                        return false;
                    }
                }
            }
        }
        return true;
    }
    public ArrayList<GraphicsObject> getGraphics(){
        ArrayList<GraphicsObject> Graph = new ArrayList();
        int x,y;
        for(x = 0; x < 16; x++){
            for(y = 0; y < 16; y++){
                if(this.chunks[x][y] != null){
                    Graph.addAll(this.chunks[x][y].getGraphics());
                }
            }
        }
        return Graph;
    }
    public SaveObjectPackage getSavePackage(){
        int x,y;
        SaveObjectPackage obj;
        SaveObjectPackage pack = new SaveObjectPackage();
        for(x = 0; x < 16;x++){
            for(y = 0; y < 16;y++){
                if(this.chunks[x][y] != null){
                    obj = this.chunks[x][y].getSavePackage();
                    obj.setName("Chunk"+x+"_"+y);
                    pack.addPackage(obj);
                }
            }
        }
        pack.setMainData(getChunkData());
        //pack.setName("Region"+this.RegionX+"_"+this.RegionY);
        return pack;
    }
    public SaveObjectPackage getSaveTilePackage(){
        int x,y;
        SaveObjectPackage obj;
        SaveObjectPackage pack = new SaveObjectPackage();
        for(x = 0; x < 16;x++){
            for(y = 0; y < 16;y++){
                if(this.chunks[x][y] != null){
                    obj = this.chunks[x][y].getSaveTilePackage();
                        obj.setName("Chunk"+x+"_"+y);
                        pack.addPackage(obj);
                }
            }
        }
        pack.setMainData(getChunkData());
        //pack.setName("Region"+this.RegionX+"_"+this.RegionY);
        return pack;
    }
    public SaveObjectPackage getSaveEntriesPackage(){
        int x,y;
        SaveObjectPackage obj;
        boolean[] packOpen = new boolean[256];
        SaveObjectPackage pack = new SaveObjectPackage();
        for(x = 0; x < 16;x++){
            for(y = 0; y < 16;y++){
                if(this.chunks[x][y] != null){
                    obj = this.chunks[x][y].getSaveEntriesPackage();
                    if(!obj.isEmpty()){
                        obj.setName("Chunk"+x+"_"+y);
                        pack.addPackage(obj);
                        packOpen[x*16+y] = true;
                    }
                    else{
                        packOpen[x*16+y] = false;
                    }
                }
            }
        }
        pack.setMainData(generateEntityMain(packOpen));
        //pack.setName("Region"+this.RegionX+"_"+this.RegionY);
        return pack;
    }
    public void claimTilesSaved(){
        int x,y;
        for(x = 0; x < 16;x++){
            for(y = 0; y < 16;y++){
                this.chunks[x][y].claimTilesSaved();
            }
        }
    }
    private String[] getChunkData(){
        int x,y;
        String data = "";
        for(x = 0; x < 16;x++){
            for(y = 0; y <16;y++){
                if(this.chunks[x][y] != null){
                    data += "y";
                }
                else{
                    data += "n";
                }
                if(x != 16 && y != 16){
                    data += ",";
                }
            }
        }
        return new String[]{data};
    }
    private String[] generateEntityMain(boolean[] info){
        int x,y;
        String data = "";
        for(int i = 0; i < info.length;i++){
            if(info[i]){
                data += "y";
            }
            else{
                data+= "n";
            }
            if(i != info.length){
                data+=",";
            }
        }
        return new String[]{data};
    }
}
