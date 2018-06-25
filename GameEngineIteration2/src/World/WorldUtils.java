/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package World;


/**
 *
 * @author Allazham
 */
public final class WorldUtils {
    private WorldUtils(){
        
    }
    public static Tile getTileFromPosition(Cell cc,float x, float y){
       //Chunk c = getChunkFromPosition(cc,x, y);
        int xx = (int) Math.floor(x), yy = (int) Math.floor(y);
        return cc.getTileAt(xx, yy);
    }
    public static Chunk getChunkFromPosition(Cell c,float x,float y){
        int xx = (int) Math.floor(x/16), yy = (int) Math.floor(y/16);
        return c.getChunkAt(xx, yy);
    }
    public static Region getRegionFromPosition(Cell c,float x, float y){
         int xx = (int) Math.floor(x/256), yy = (int) Math.floor(y/256);
         return c.getRegionAt(xx, yy);
    }
    public static Tile getTileFromPosition(Chunk c,float x, float y){
        byte[] pos = getInternalTilePos(x,y);
        return c.getTileAt(pos[0],pos[1]);
    }
    public static Chunk getChunkFromPosition(Region r,float x, float y){
        int xx = (int) Math.floor(x/16%16), yy = (int) Math.floor(y/16%16);
        if(xx < 0){
            xx+=16;
        }
        if(yy < 0){
            yy+=16;
        }
        return r.getChunkAt(xx, yy);
    }
        public static byte[] getInternalTilePos(float x, float y){
            byte xx = (byte) Math.floor(x%16), yy = (byte) Math.floor(y%16);
            if(xx < 0){
                xx+=16;
            }
            if(yy < 0){
                yy+=16;
            }
           return new byte[]{xx, yy};
        }
        public static byte[] getInternalChunkPos(float x,float y){
            byte xx = (byte) Math.floor(x/16%16);
            byte yy = (byte) Math.floor(y/16%16);
            if(xx < 0){
                xx+=16;
            }
            if(yy < 0){
                yy+=16;
            }
            return new byte[]{xx, yy};
        }
        public static Tile[][] getTiles(Cell c,float x, float y,float distance){
            int closeX,closeY,farX,farY,xx,yy,distX,distY;
            Tile[][] Tiles;
            distance /=2;
            //x +=.25f;
            //y +=.25f;
            closeX = (int)Math.floor(x-distance);
            closeY = (int)Math.floor(y-distance);
            farX = (int)Math.floor(x+distance+1f);
            farY = (int)Math.floor(y+distance+1f);
            distX = farX-closeX;
            distY = farY-closeY;
            Tiles = new Tile[distX][distY];
            for(xx = 0; xx < distX;xx++){
                for(yy = 0; yy < distY;yy++){
                    Tiles[xx][yy] = c.getTileAt(closeX+xx, closeY+yy);
                }
            }
            return Tiles;
        }
        public static int[] getTilePos(float x, float y){
            return new int[]{(int)Math.floor(x),(int)Math.floor(y)};
        }
        public static int[] getChunkPos(float x, float y){
            return new int[]{(int)Math.floor(x/16),(int)Math.floor(y/16)};
        }
        public static int[] getRegionPos(float x, float y){
            return new int[]{(int) Math.floor(x/256), (int) Math.floor(y/256)};
        }
        public float[][] getRegionEdges(Region r){
            return getRegionEdges(r.RegionX,r.RegionY);
        }
        public float[][] getChunkEdges(Chunk c){
            return getChunkEdges(c.region.RegionX*16+c.ChunkX,c.region.RegionY*16+c.ChunkY);
        }
        public float[][] getTileEdges(Tile b){
            return getTileEdges(b.c.region.RegionX*256+b.c.ChunkX*16+b.x,b.c.region.RegionY*256+b.c.ChunkY*16+b.y);
        }
        public float[][] getRegionEdges(int x, int y){
            return new float[][]{{x*256,y*256},{(x+1)*256,(y+1)*256}};
        }
        public float[][] getChunkEdges(int x, int y){
            return new float[][]{{x*16,y*16},{(x+1)*16,(y+1)*16}};
        }
        public float[][] getTileEdges(int x, int y){
            return new float[][]{{x,y},{x+1,y+1}};
        }
        public static boolean removeTile(Cell c, float x, float y){
            Tile b = WorldUtils.getTileFromPosition(c, x, y);
            if(b != null){
                b.c.removeTile(b.x, b.y);
                return true;
            }
            return false;
        }
        public static boolean removeTile(Tile b){
            if(b != null){
                b.c.removeTile(b.x, b.y);
            }
            return false;
        }
        public static boolean removeChunk(Cell c, float x, float y){
            Chunk cc = WorldUtils.getChunkFromPosition(c, x, y);
            if(cc != null){
                cc.region.removeChunk(cc.ChunkX, cc.ChunkY);
                return true;
            }
            return false;
        }
        public static boolean removeChunk(Chunk c){
            if(c != null){
                c.region.removeChunk(c.ChunkX, c.ChunkY);
            }
            return false;
        }
}
