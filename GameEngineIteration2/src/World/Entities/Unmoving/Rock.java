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
import org.lwjgl.LWJGLException;
import org.lwjgl.util.vector.Vector2f;

/**
 *
 * @author Allazham
 */
public class Rock extends AbstractGraphicEntry{
    private final Texture rock;
    private final Vector2f GraphicLocation;
    public Rock(){
        rock = MasterLoad.getImage("rock");
        GraphicLocation = super.getGraphicalLocation();
    }
    @Override
    public void PeronalMovement(int delta) {
        
    }

    @Override
    public void Update(int delta) {
        
    }
    
    @Override
    protected void render() throws LWJGLException {
        this.rock.bind();
        ScreenGraphic.DrawBasicTexturedSquare(this.GraphicLocation.x,this.GraphicLocation.y, super.getScale(),super.getScale(), this.rock.getLesserX(),this.rock.getLesserY(),this.rock.getGreaterX(),this.rock.getGreaterY());
    }
    
    @Override
    public SaveObjectPackage getSave() {
        return super.getBasicPackage();
    }

    @Override
    public String getSaveIdentity() {
        return "Rock";
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
