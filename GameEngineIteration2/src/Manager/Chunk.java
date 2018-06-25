/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Manager;

import Graphics.ErrorLog;
import Graphics.GraphicsObject;
import Graphics.MasterClose;
import Manager.Creatures.CreatureCreator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Allazham
 */
public class Chunk{
    private List<CreatureManager> creatures = new ArrayList();
    private boolean disabled = false;
    private boolean FinalSave = false;
    private int ChunkX = 0,ChunkY = 0;
    private Block[][] block = new Block[16][16];
    //for debug purposes only
    public Chunk(int ChunkX, int ChunkY){
        int x,y;
        this.ChunkX = ChunkX;
        this.ChunkY = ChunkY;
        for(x = 0; x < 16; x++){
            for(y = 0; y < 16; y++){
                block[x][y] = new Block(x+ChunkX*16,y+ChunkY*16,null,false);
            }
        }
    }
    public Chunk(int ChunkX, int ChunkY,int[][] terrian){
        int x,y;
        this.ChunkX = ChunkX;
        this.ChunkY = ChunkY;
        for(x = 0; x < 16; x++){
            for(y = 0; y < 16; y++){
                block[x][y] = new Block(x+ChunkX*16,y+ChunkY*16,terrian[x][y],false);
            }
        }
    }
    public Chunk(List<String> args){
        List<List<String>> blockStorage = new ArrayList(),creatureStorage = new ArrayList();
        List<Block> blocks = new ArrayList();
        List<String> storage;
        int i,ii,x, y = 0,terrian = 0;
        String s;
        String abs[];
        breakable:{
        for(x = 0; x < args.size();x++){
            s = args.get(x);
            if(s.contains("@")){
                s = s.substring(1);
                abs = s.split("::");
                s = abs[0];
            }
            else{
                s = s.substring(2);
                abs = s.split("=");
                try{
                    y = Integer.parseInt(abs[1]);
                }
                catch(NumberFormatException ex){
                    ErrorLog.error(ex);
                }
                s = abs[0];
            }
            s = s.toLowerCase();
            switch(s){
                case"start":
                    if(abs[1].toLowerCase().contains("block")){
                        storage = new ArrayList();
                        while(!args.get(x).toLowerCase().contains("end::block")&&args.size() != x){
                            storage.add(args.get(x));
                            x++;
                        }
                        blockStorage.add(storage);
                    }
                    else if(abs[1].equalsIgnoreCase("creature")){
                        storage = new ArrayList();
                        while(!args.get(x).toLowerCase().contains("end::creature") && args.size() != x){
                            storage.add(args.get(x));
                            x++;
                        }
                        creatureStorage.add(storage);
                    }
                    break;
                case"chunkx":
                    this.ChunkX = y;
                    break;
                case"chunky":
                    this.ChunkY = y;
                    break;
                case"end":
                    if(args.get(x).toLowerCase().contains("chunk")){
                        break breakable;
                    }
                    break;
            }
        }
        }
        for(i = 0; i < blockStorage.size();i++){
            storage = blockStorage.get(i);
            for(ii = 0; ii < storage.size();ii++){
                s = storage.get(ii);
                try{
                    if(s.toLowerCase().contains("::x")){
                        x = Integer.parseInt(s.split("=")[1]);
                    }
                    else if(s.toLowerCase().contains("::y")){
                        y = Integer.parseInt(s.split("=")[1]);
                    }
                    else if(s.toLowerCase().contains("groundtype")){
                        terrian = Integer.parseInt(s.split("=")[1]);
                    }
                    
                }
                catch(Exception ex){
                    ex.printStackTrace();
                    ErrorLog.error(ex);
                }
            }
            blocks.add(new Block(x,y,terrian,false));
        }
        Collections.sort(blocks,new easysort());
        i = 0;
        try{
            for(x = 0; x < 16;x++){
                for(y = 0; y < 16; y++){
                    this.block[x][y] =  blocks.get(i);
                    i++;
                }
            }
            for(i = 0; i < creatureStorage.size();i++){
                this.creatures.add(CreatureCreator.getCreatureFromSaveData(creatureStorage.get(i)));
            }
        }
        catch(Exception ex){
            MasterClose.CloseWithError(ex);
        }
    }
    public void update(){
        if(!this.disabled){
            int i;
            for(i = 0; i < this.creatures.size();i++){
                this.creatures.get(i).Update();
            }
        }
    }
    public void setDisabled(boolean disabled){
        this.disabled = disabled;
        int x, y;
        for(x = 0; x < 16; x++){
            for(y = 0; y < 16; y++){
                this.block[x][y].setDisabled(disabled);
            }
        }
        for(x = 0; x < this.creatures.size();x++){
            if(this.creatures.get(x) instanceof GraphicsObject){
                ((GraphicsObject)this.creatures.get(x)).setDisabled(disabled);
            }
        }
    }
    public boolean isDisabled(){
        return this.disabled;
    }
    public Block getBlock(float x, float y){
        int xx, yy;
        if(x%1 >= .5){
            xx = (int)x + 1;
        }
        else{
            xx = (int) x;
        }
        if(y%1 >= .4){
            yy = (int)y + 1;
        }
        else{
            yy = (int) y;
        }
        return block[xx][yy];
    }
    public int getChunkX(){
        return this.ChunkX;
    }
    public int getChunkY(){
        return this.ChunkY;
    }
    public void setChunkX(int ChunkX){
        int x, y;
        int temp = this.ChunkX - ChunkX;
        for(x = 0; x < 16;x++){
            for(y = 0; y < 16;y++){
                block[x][y].addY(temp);
            }
        }
        this.ChunkX = ChunkX;
    }
    
    public void setChunkY(int ChunkY){
        int x, y;
        int temp = this.ChunkY - ChunkY;
        for(x = 0; x < 16;x++){
            for(y = 0; y < 16;y++){
                block[x][y].addY(temp);
            }
        }
        this.ChunkY = ChunkY;
    }
    public Block[][] getBlocks(){
        return this.block;
    }
    public Block getBlock(int x, int y){
        return block[x][y];
    }
    public List<GraphicsObject> getBlocksForScreen(){
        List<GraphicsObject> g = new ArrayList();
        int x,y;
        for(x = 0; x < 16;x++){
            for(y = 0; y < 16;y++){
                g.add(this.block[x][y]);
            }
        }
        return g;
    }
    public List<GraphicsObject> getCreaturesForScreen(){
        int i;
        List<GraphicsObject> ret = new ArrayList();
        for(i = 0; i < this.creatures.size();i++){
            if(this.creatures.get(i) instanceof GraphicsObject){
                ret.add((GraphicsObject)this.creatures.get(i));
            }
        }
        return ret;
    }
    public List<CreatureManager> getTargets(){
        return this.creatures;
    }
    public List<CreatureManager> getCreaturedInSearch(float x, float y, float s){
        List<CreatureManager> e = new ArrayList();
        int i;
        for(i = 0; i < this.creatures.size();i++){
            if(this.creatures.get(i).getX() > x - s && x  + s> this.creatures.get(i).getX() - s&&this.creatures.get(i).getY() > y - s && y + s > this.creatures.get(i).getY() ){
                e.add(this.creatures.get(i));
            }
        }
        return e;
    }
    public List<CreatureManager> getMisPlacedTargets(){
        int i;
        List<CreatureManager> ret = new ArrayList();
        for(i = 0; i < this.creatures.size();i++){
            if(((int)this.creatures.get(i).getX()/256) != this.ChunkX&&((int)this.creatures.get(i).getY()/256) != this.ChunkY){
                ret.add(this.creatures.remove(i));
                i--;
            }
        }
        return ret;
    }
    public void addCreature(CreatureManager e){
        this.creatures.add(e);
    }
    public void removeCreature(CreatureManager e){
        this.creatures.remove(e);
    }
    public boolean isInsideChunk(float x, float y){
        return false;
    }
    public List<String> getSaveData(){
        int x,y;
        List<String> save = new ArrayList();
        save.add("@Start::Chunk");
        save.add("::ChunkX="+this.ChunkX);
        save.add("::ChunkY="+this.ChunkY);
        for(x = 0; x < 16; x++){
            for(y = 0; y < 16; y++){
                save.addAll(block[x][y].getSaveData());
            }
        }
        for(x = 0; x < this.creatures.size();x++){
            save.addAll(this.creatures.get(x).getSaveData());
        }
        save.add("@End::Chunk");
        return save;
    }
    public void FinalSave(){
        this.FinalSave = true;
    }
    public boolean hasReachedFinalSave(){
        return this.FinalSave;
    }
    private static class easysort implements Comparator<Block>{
        @Override
        public int compare(Block o1, Block o2) {
            if(o1.getBlockX() == o2.getBlockX()){
                if(o1.getBlockY() > o2.getBlockY()){
                    return -1;
                }
                else if(o1.getBlockY() < o2.getBlockY()){
                    return 1;
                }
                else{
                    ErrorLog.error(new Exception("Strange occurance!!, block 1 == block 2"));
                    return 0;
                }
            }
            else if(o1.getBlockX() > o2.getBlockX()){
                return -1;
            }
            return 1;
        }
    }
}
