/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package World.Entities.Unmoving;

import Graphics.Base.ScreenGraphic;
import Holder.MasterLoad;
import Holder.Texture;
import Save.SaveObjectPackage;
import World.Entities.AbstractGraphicEntry;
import Item.ItemList;
import org.lwjgl.LWJGLException;
import org.lwjgl.util.vector.Vector2f;

/**
 *
 * @author Allazham
 */
public class LootChest extends AbstractGraphicEntry{
    private Texture LChest;
    private Vector2f GraphicLocation;
    public LootChest(float x, float y, ItemList i){
        super.setLocation(x, y);
        LChest = MasterLoad.getImage("LootChest");
        super.setVelocity(0, 0);
        GraphicLocation = super.getGraphicalLocation();
    }
    @Override
    public void PeronalMovement(int delta) {
        
    }

    @Override
    protected void render() throws LWJGLException {
        this.LChest.bind();
        ScreenGraphic.DrawBasicTexturedSquare(GraphicLocation.x, GraphicLocation.y, super.getScale(),super.getScale(), this.LChest.getLesserX(),this.LChest.getLesserY(),this.LChest.getGreaterX(),this.LChest.getGreaterY());
    }

    @Override
    public void Update(int delta) {
        
    }
    
    @Override
    public SaveObjectPackage getSave() {
        return super.getBasicPackage();
    }

    @Override
    public String getSaveIdentity() {
        return "LootChest";
    }

    @Override
    public void disposeEntry() {
        
    }
    @Override
    public void disposeGraphics() {
        
    }

    @Override
    public boolean isPersistent() {
        return false;
    }
}
