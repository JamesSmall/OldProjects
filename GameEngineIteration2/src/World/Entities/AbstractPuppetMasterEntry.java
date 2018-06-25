/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package World.Entities;

import Graphics.Forum.GraphicsObject;
import World.Cell;
import World.Entry;
import Item.ItemList;
import Save.StatManager.StatList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import org.lwjgl.util.vector.Vector2f;

/**
 *
 * @author Allazham
 */
public abstract class AbstractPuppetMasterEntry implements Entry{
    private final Entry ent;
    private boolean markedforDelete = false;
    public AbstractPuppetMasterEntry(Entry e){
        if(e == null){
            throw new NullPointerException("Entity is null");
        }
        this.ent = e;
    }
    @Override
    public List<GraphicsObject> getGraphicObjects() {
        return ent.getGraphicObjects();
    }

    @Override
    public void setLocation(Vector2f v) {
        ent.setLocation(v);
    }

    @Override
    public void setLocation(float x, float y) {
        ent.setLocation(x,y);
    }
    @Override
    public UUID getUUID(){
        return ent.getUUID();
    }
    @Override
    public void setLocationX(float x) {
        ent.setLocationX(x);
    }

    @Override
    public void setLocationY(float y) {
        ent.setLocationY(y);
    }

    @Override
    public void setVelocityX(float x) {
        ent.setVelocityX(x);
    }

    @Override
    public void setVelocityY(float y) {
       ent.setVelocityY(y);
    }

    @Override
    public void setVelocity(float x, float y) {
        ent.setVelocity(x, y);
    }

    @Override
    public void setVelocity(Vector2f v) {
        ent.setVelocity(v);
    }
    
    @Override
    public void addVelocityX(float x){
        this.ent.addVelocityX(x);
    }
    @Override
    public void addVelocityY(float y){
        this.ent.addVelocityY(y);
    }
    @Override
    public void addVelocity(float x, float y){
        this.ent.addVelocity(x,y);
    }
    @Override
    public void addVelocity(Vector2f v){
        this.ent.addVelocity(v);
    }
    @Override
    public void setSize(float size) {
        ent.setSize(size);
    }

    @Override
    public float getLocationX() {
        return ent.getLocationX();
    }

    @Override
    public float getLocationY() {
        return ent.getLocationY();
    }

    @Override
    public float[] getLocation() {
        return ent.getLocation();
    }

    @Override
    public Vector2f getLocationVector() {
        return ent.getLocationVector();
    }

    @Override
    public Vector2f getGraphicalLocation() {
        return ent.getGraphicalLocation();
    }

    @Override
    public float getScale() {
        return ent.getScale();
    }

    @Override
    public float getVelocityX() {
        return ent.getVelocityX();
    }

    @Override
    public float getVelocityY() {
        return ent.getVelocityY();
    }

    @Override
    public float[] getVelocity() {
        return ent.getVelocity();
    }

    @Override
    public Vector2f getVelocityVector() {
        return ent.getVelocityVector();
    }

    @Override
    public boolean Intersect(Entry e) {
        return ent.Intersect(e);
    }

    @Override
    public boolean Intersect(float x, float y) {
        return ent.Intersect(x,y);
    }

    @Override
    public void setDisabled(boolean disable) {
        ent.setDisabled(disable);
    }

    @Override
    public boolean isDisabled() {
        return ent.isDisabled();
    }

    @Override
    public void UpdateGraphicLocation() {
        ent.UpdateGraphicLocation();
    }

    @Override
    public void UpdateLocation(int delta) {
        ent.UpdateLocation(delta);
    }

    @Override
    public Cell getCell() {
        return ent.getCell();
    }

    @Override
    public void setCell(Cell c) {
        ent.setCell(c);
    }
    
    @Override
    public boolean isInExistantSpace(){
        return this.ent.isInExistantSpace();
    }
    @Override
    public StatList getAttributeList() {
        return ent.getAttributeList();
    }

    @Override
    public ItemList getItemList() {
        return ent.getItemList();
    }
    public Entry getEntry(){
        return this.ent;
    }
    @Override
    public boolean isMarkedForDelete(){
        return this.markedforDelete;
    }
    public void markForDelete(){
        this.markedforDelete = true;
    }
    
    @Override
    public boolean equals(Object o){
        if(o instanceof Entry){
            Entry e = (Entry) o;
            return e.getUUID().equals(this.ent.getUUID());
        }
        return false;
    }

    @Override
    public int hashCode() {
        return 97 * 7 + Objects.hashCode(this.ent.getUUID());
    }
}
