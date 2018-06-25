/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package World;

import Graphics.Base.ScreenGraphic;
import static Graphics.Base.ScreenGraphic.ConvertWorldToScreenX;
import static Graphics.Base.ScreenGraphic.ConvertWorldToScreenY;
import Holder.Texture;
import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.GL11;

/**
 *
 * @author Allazham
 */
public class Tile{
    private final Texture image;
    protected byte x, y;
    protected int type;
    protected Chunk c;
    protected Tile(){
        image = null;
    }
    protected Tile(Texture image){
        this.image = image;
    }
    public Tile(Chunk c,byte x, byte y,int type){
        this.x = x;
        this.y = y;
        this.type = type;
        this.image = ImageControl.getImageByTileType(type);
        this.c = c;
    }
    protected void render() throws LWJGLException{
        this.image.bind();
        ScreenGraphic.DrawBasicTexturedSquare(x+(c.ChunkX*16)+(c.region.RegionX*256)+.5f, y+(c.ChunkY*16)+(c.region.RegionY*256)+.5f, 1.0f,1.0f,image.getLesserX(),image.getLesserY(),image.getGreaterX(),image.getGreaterY());
    }
    @Deprecated
    void render2() throws LWJGLException {
        
        float localXLesser = ConvertWorldToScreenX(x+(c.ChunkX*16)+(c.region.RegionX*256)),localXGreater = ConvertWorldToScreenX(x+(c.ChunkX*16)+(c.region.RegionX*256)+1),localYLesser = ConvertWorldToScreenY(y+(c.ChunkY*16)+(c.region.RegionY*256)),localYGreater = ConvertWorldToScreenY(y+(c.ChunkY*16)+(c.region.RegionY*256)+1);
        this.image.bind();
        GL11.glBegin(GL11.GL_QUADS);
        
        GL11.glTexCoord2d(image.getLesserX(),image.getLesserY());
        GL11.glVertex2f(localXLesser,localYLesser);
        
        GL11.glTexCoord2d(image.getGreaterX(),image.getLesserY());
        GL11.glVertex2f(localXGreater,localYLesser);
        
        GL11.glTexCoord2d(image.getGreaterX(),image.getGreaterY());
        GL11.glVertex2f(localXGreater,localYGreater);
        
        GL11.glTexCoord2d(image.getLesserX(),image.getGreaterY());
        GL11.glVertex2f(localXLesser,localYGreater);
        GL11.glEnd();
    }
    public int getGroundType(){
        return this.type;
    }
}
