/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Forum.ToolBox.WorldControl;

import Graphics.Forum.ActionKeys;
import Graphics.UserActions.UserAction;
import Graphics.Utils.Camera2D;
import Forum.ToolBox.GrabTool;
import Forum.ToolBox.ToolGraphic;
import Graphics.UserActions.UserWorldAction;
import Utils.Config;
import World.Tile;
import World.Cell;
import World.Chunk;
import World.Region;
import World.WorldControl;
import World.WorldUtils;
import org.lwjgl.input.Mouse;
import org.lwjgl.util.vector.Vector2f;

/**
 *
 * @author Allazham
 */
public class TileRemover extends ToolGraphic implements GrabTool{
    private final Camera2D cam = Camera2D.getCamera();
    public TileRemover(){
        super(Config.PointerBasic);
    }
    @Override
    public UserAction getToolInstance() {
        return new Delete();
    }
    private class Delete extends UserWorldAction{
        @Override
        public void Action(ActionKeys k) {
            Vector2f loc = cam.getMousePointInWorld();
            Cell cc = WorldControl.getInstance().getPrimaryCell();
            Tile b;
            Region r;
            Chunk c;
            if(cc != null && Mouse.isButtonDown(1)){
                r = WorldUtils.getRegionFromPosition(cc, loc.x,loc.y);
                if(r != null){
                    if(!r.isEmpty()){
                        c = WorldUtils.getChunkFromPosition(cc, loc.x, loc.y);
                        if(c != null){
                            if(!c.isEmpty()){
                                b = WorldUtils.getTileFromPosition(cc, loc.x, loc.y);
                                if(b != null){
                                    WorldUtils.removeTile(b);
                                    if(c.isEmpty()){
                                        WorldUtils.removeChunk(c);
                                    }
                                }
                            }
                            else{
                                r.removeChunk(c.getChunkX(), c.getChunkY());
                            }
                        }
                    }
                }
            }
        }

        @Override
        public void create() {
            
        }

        @Override
        public void dispose() {
            
        }
    }
}
