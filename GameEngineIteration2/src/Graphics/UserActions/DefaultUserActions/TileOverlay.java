/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Graphics.UserActions.DefaultUserActions;

import Graphics.Forum.ActionKeys;
import Graphics.UserActions.UserAction;
import Graphics.Utils.Camera2D;
import Graphics.Base.Graphic.ParticleEngine;
import Holder.MasterLoad;
import Holder.Texture;
import World.WorldUtils;
import org.lwjgl.util.vector.Vector2f;

/**
 *
 * @author Allazham
 */
public class TileOverlay implements UserAction{
    private boolean disabled = false;
    private final Camera2D d = Camera2D.getCamera();
    private final ParticleEngine p = ParticleEngine.getInstance();
    private final Texture tex;
    public TileOverlay(){
        tex = MasterLoad.getImage("Target");
    }
    @Override
    public void Action(ActionKeys k) {
        Vector2f v = d.getMousePointInWorld();
        int[] loc = WorldUtils.getTilePos(v.x, v.y);
        p.TileOverlay(tex, loc[0],loc[1], -2);
    }

    @Override
    public boolean isDisabled() {
        return disabled;
    }
    public void setDisabled(boolean disabled){
        this.disabled = disabled;
    }

    @Override
    public void create() {
        
    }

    @Override
    public void dispose() {
        
    }
}
