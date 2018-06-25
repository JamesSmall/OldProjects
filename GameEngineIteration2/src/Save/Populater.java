/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Save;

import World.Tile;
import World.Cell;
import World.Chunk;
import World.Entities.Basic.PlayerPosition;
import World.Entry;
import Item.ItemList;
import Save.StatManager.StatList;
import World.Entities.Basic.Follower;
import World.Region;
import java.io.IOException;
import java.util.List;
import java.util.UUID;
import org.lwjgl.util.vector.Vector2f;

/**
 *
 * @author Allazham
 */
public class Populater {
    public Region createBasicRegion(Cell c,int x, int y){
        Region r = new Region(c,x,y);
        Chunk[][] chunks = new Chunk[16][16];
        Tile[][] Tiles;
        Chunk ch;
        byte xx,yy,xxx,yyy;
        for(xx = 0; xx < chunks.length;xx++){
            for(yy = 0; yy < chunks[xx].length;yy++){
                ch = new Chunk(r,xx,yy);
                Tiles = new Tile[16][16];
                for(xxx=0;xxx<Tiles.length;xxx++){
                    for(yyy=0;yyy<Tiles[xxx].length;yyy++){
                        Tiles[xxx][yyy] = new Tile(ch,xxx,yyy, (int) (Math.random()*10));
                    }
                }
                ch.setupTiles(Tiles);
                chunks[xx][yy] = ch;
            }
        }
        r.setupChunk(chunks);
        return r; 
        //return null;
    }
    public Region GeneratePop(Load TileLoader,Load EntryLoader,Cell c,int x, int y) throws IOException{
        int i,count;
        boolean hasEntities;
        List<String> data;
        byte xx,yy, xxx,yyy;
        String Chunk,held[],CInfo[],EInfo[];
        Region r = new Region(c,x,y);
        Chunk cc;
        Entry e;
        //Tile b;
        Chunk[][] chunks = new Chunk[16][16];
        Tile[][] Tiles; 
        TileLoader.getSubDirectoryFile("main");
        EntryLoader.getSubDirectoryFile("main");
        CInfo = TileLoader.readFromInputFile().get(0).split(",");
        EInfo = EntryLoader.readFromInputFile().get(0).split(",");
        System.out.println("tree");
        for(xx=0;xx<chunks.length;xx++){
            for(yy=0;yy<chunks[xx].length;yy++){
                if("n".equals(CInfo[xx*16+yy])){
                    chunks[xx][yy] = null;
                    continue;
                }
                hasEntities = "y".equals(EInfo[xx*16+yy]);
                Tiles = new Tile[16][16];
                cc = new Chunk(r,xx,yy);
                Chunk = "Chunk"+xx+"_"+yy+"/";
                //chunk data goes here
                count = 0;
                TileLoader.getSubDirectoryFile(Chunk+"TileData");
                data = TileLoader.readFromInputFile();
                held = data.get(0).split(",");
                for(xxx=0;xxx<Tiles.length;xxx++){
                    for(yyy=0; yyy < Tiles[xxx].length;yyy++){
                        if(!"n".equals(held[count])){
                            Tiles[xxx][yyy] = new Tile(cc,xxx,yyy,Integer.parseInt(held[count]));
                        }
                        count++;
                    }
                }
                cc.setupTiles(Tiles);
                cc.claimTilesSaved();
                chunks[xx][yy] = cc;
                if(!hasEntities){
                    continue;
                }
                EntryLoader.getSubDirectoryFile(Chunk+"main");
                count = Integer.parseInt(EntryLoader.readFromInputFile().get(0));
                for(i = 0; i < count;i++){
                    EntryLoader.getSubDirectoryFile(Chunk+"Entry"+i+"/main");
                    e = GenerateEntry(EntryLoader);
                    if(e != null){
                        cc.addEntry(e);
                        e.setCell(c);
                    }
                }
            }
        }
        r.setupChunk(chunks);
        EntryLoader.destroy();
        TileLoader.destroy();
        return r;
    }
    
    
    
    protected static EntityInfo getEntityInfo(Load l) throws IOException{
        String tru = l.getCurrentLocation();
        String loc = l.getOverviewLocation();
        l.getSubDirectoryFile(loc+"BasicInfo");
        EntityInfo i = new EntityInfo();
        String[] right = l.readFromInputFile().get(0).split(",");
        i.loc = new Vector2f(Float.parseFloat(right[0]),Float.parseFloat(right[1]));
        i.Vel = new Vector2f(Float.parseFloat(right[2]),Float.parseFloat(right[3]));
        i.size = Float.parseFloat(right[4]);
        i.VelocityAdjust = Float.parseFloat(right[5]);
        i.UUID = UUID.fromString(right[6]);
        l.getSubDirectoryFile(loc+"StatList");
        i.aList = StatList.generateStatList(l);
        l.getSubDirectoryFile(loc+"ItemList/main");
        i.IList = ItemList.getItemList(l);
        l.getSubDirectoryFile(tru);
        i.IList.checkList(i.aList);
        return i;
    }
    public static Entry GenerateEntry(Load l) throws IOException{
        String s = l.readFromInputFile().get(0);
        return GenerateEntry(l,s);
    }
    public static Entry GenerateEntry(Load l, String mainData) throws IOException{
        switch (mainData) {
            case "PlayerPosition":
                return new PlayerPosition(getEntityInfo(l));
            case "Follower":
                return new Follower(getEntityInfo(l));
        }
        return null;
    }
    
}