/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Item;

import Save.SaveObjectPackage;
import World.Entities.AbstractGraphicEntry;

/**
 *
 * @author Allazham
 */
public class GroundItem extends AbstractGraphicEntry{
    @Override
    protected void render() {
        
    }

    @Override
    public void PeronalMovement(int delta) {
        
    }

    @Override
    public void Update(int delta) {
        
    }

    @Override
    public SaveObjectPackage getSave() {
        return null;
    }

    @Override
    public String getSaveIdentity() {
        return "Item";
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
