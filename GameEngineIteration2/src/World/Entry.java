/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package World;

import Graphics.Forum.GraphicsObject;
import Save.SaveObjectPackage;
import Item.ItemList;
import Save.StatManager.StatList;
import java.util.List;
import java.util.UUID;
import org.lwjgl.util.vector.Vector2f;

/**
 *
 * @author Allazham
 * @see  World.Entities.AbstractEntry
 * @see World.Entities.AbstractGraphicEntry
 * @see World.entities.AbstractPuppetMasterEntry
 */
public interface Entry {
    //client only
    public List<GraphicsObject> getGraphicObjects();
    
    //location setting
    //
    public void setLocation(Vector2f v);
    public void setLocation(float x, float y);
    public void setLocationX(float x);
    public void setLocationY(float y);
    
    public void setVelocityX(float x);
    public void setVelocityY(float y);
    public void setVelocity(float x, float y);
    public void setVelocity(Vector2f v);
    public void addVelocityX(float x);
    public void addVelocityY(float y);
    public void addVelocity(float x, float y);
    public void addVelocity(Vector2f v);
    public void setSize(float size);
    
    //location detection
    public float getLocationX();
    public float getLocationY();
    public float[] getLocation();
    public Vector2f getLocationVector();
    
    public Vector2f getGraphicalLocation();
    
    public float getScale();
    
    public float getVelocityX();
    public float getVelocityY();
    public float[] getVelocity();
    public Vector2f getVelocityVector();
    
    //intersection detection
    public boolean Intersect(Entry e);
    public boolean Intersect(float x, float y);
    
    public void setDisabled(boolean disable);
    public boolean isDisabled();
    public boolean isMarkedForDelete();
    
    public void UpdateGraphicLocation();
    public void UpdateLocation(int delta);
    public void Update(int delta);
    //entry interaction
    public Cell getCell();
    public void setCell(Cell c);
    public UUID getUUID();
    
    public StatList getAttributeList();
    public ItemList getItemList();
    public SaveObjectPackage getSave();
    
    public boolean isPersistent();
    public boolean isInExistantSpace();
    //public void backgroupUpdate(long time);
    
    
    public void disposeEntry();
    
    public String getSaveIdentity();
}
