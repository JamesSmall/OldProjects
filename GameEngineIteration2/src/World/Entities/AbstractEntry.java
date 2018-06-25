/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package World.Entities;

import Save.Save;
import Save.SaveObject;
import Save.SaveObjectPackage;
import Save.EntityInfo;
import World.Tile;
import World.Cell;
import World.Entry;
import Item.ItemList;
import Save.StatManager.StatList;
import World.WorldUtils;
import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import org.lwjgl.util.vector.Vector2f;

/**
 *
 * @author Allazham
 * @see World.Entities.AbstractGraphicEntry
 * @see World.Entities.AbstractPuppetMasterEntry
 * @version 1.0
 * @since 1.0
 */

public abstract class AbstractEntry implements Entry{
    private Cell c;
    private final StatList AList;
    private final ItemList IList;
    private final Vector2f Location;
    private final Vector2f Velocity;
    private float Size = 1;
    private float VelocityAdjust = .005f;
    private float Friction = .0005f;
    private int[] IllegalTiles = new int[0];
    private boolean markedforDelete = false;
    private final UUID uid;
    /**
     * basic setup for Entries
    */
    public AbstractEntry(){
        AList = new StatList();
        this.Location = new Vector2f();
        this.Velocity = new Vector2f();
        this.IList = new ItemList();
        uid = UUID.randomUUID();
    }

    /**
     *
     * @param e
     */
    public AbstractEntry(EntityInfo e){
        this.Location = e.loc;
        this.Velocity = e.Vel;
        this.AList = e.aList;
        this.IList = e.IList;
        this.Size = e.size;
        this.VelocityAdjust = e.VelocityAdjust;
        uid = e.UUID;
    }

    /**
     * @param adjustement
     */
    public void setVelocityAdjustement(float adjustement){
        this.VelocityAdjust = adjustement;
    }
    public float getVelocityAdjustemenet(){
        return this.VelocityAdjust;
    }

    /**
     *
     * @param v
     */
    @Override
    public void setLocation(Vector2f v) {
        this.Location.x = v.x;
        this.Location.y = v.y;
    }

    /**
     *
     * @return
     */
    @Override
    public UUID getUUID(){
        return this.uid;
    }
    @Override
    public void setLocation(float x, float y) {
        this.Location.x = x;
        this.Location.y = y;
    }

    @Override
    public void setLocationX(float x) {
        this.Location.x = x;
    }

    @Override
    public void setLocationY(float y) {
        this.Location.y = y;
    }
    @Override
    public void addVelocityX(float x){
        this.Velocity.x += x;
    }
    @Override
    public void addVelocityY(float y){
        this.Velocity.y += y;
    }
    @Override
    public void addVelocity(float x, float y){
        this.Velocity.x += x;
        this.Velocity.y += y;
    }

    /**
     *
     * @param v
     */
    @Override
    public void addVelocity(Vector2f v){
        Vector2f.add(v, this.Velocity, this.Velocity);
    }

    /**
     *
     * @param size
     */
    @Override
    public void setSize(float size) {
        this.Size = size;
    }

    /**
     *
     * @return
     */
    @Override
    public float getLocationX() {
        return this.Location.x;
    }

    /**
     *
     * @return
     */
    @Override
    public float getLocationY() {
       return this.Location.y;
    }

    /**
     *
     * @return
     */
    @Override
    public float[] getLocation() {
        return new float[]{this.Location.x,this.Location.y};
    }

    /**
     *
     * @return
     */
    @Override
    public Vector2f getLocationVector() {
        return this.Location;
    }

    /**
     *
     * @return
     */
    @Override
    public float getScale() {
        return this.Size;
    }

    
    @Override
    public boolean Intersect(Entry e) {
        double distance = Math.sqrt(Math.pow(e.getLocationX()-this.Location.x, 2)+Math.pow(e.getLocationY()-this.Location.y, 2));
        if(this.Size < 0 || e.getScale() < 0){
            return false;
        }
        return (distance < this.Size+e.getScale());
    }

    /**
     *
     * @param x
     * @param y
     * @return
     */
    @Override
    public boolean Intersect(float x, float y) {
        double distance = Math.sqrt(Math.pow(x-this.Location.x, 2)+Math.pow(y-this.Location.y, 2));
        if(this.Size < 0){
            return false;
        }
        return (distance < this.Size);
    }
    @Override
    public StatList getAttributeList() {
        return this.AList;
    }

    /**
     *
     * @return
     */
    @Override
    public ItemList getItemList(){
        return this.IList;
    }
    //velocity counter

    /**
     *
     * @param x
     */
        @Override
    public void setVelocityX(float x){
        this.Velocity.x = x;
    }
    @Override
    public void setVelocityY(float y){
        this.Velocity.y = y;
    }
    @Override
    public void setVelocity(float x, float y){
        this.Velocity.x = x;
        this.Velocity.y = y;
    }

    /**
     *
     * @param v
     */
    @Override
    public void setVelocity(Vector2f v){
        this.Velocity.x = v.x;
        this.Velocity.y = v.y;
    }

    /**
     *
     * @return
     */
    @Override
    public float getVelocityX(){
        return this.Velocity.x;
    }
    @Override
    public float getVelocityY(){
        return this.Velocity.y;
    }

    /**
     *
     * @return
     */
    @Override
    public float[] getVelocity(){
        return new float[]{this.Velocity.x,this.Velocity.y};
    }

    /**
     *
     * @return
     */
    @Override
    public Vector2f getVelocityVector(){
        return this.Velocity;
    }

    /**
     *
     * @param c the Cell to set the entity to
     * 
     */
    @Override
    public void setCell(Cell c){
        this.c = c;
        this.IList.forceCell(c);
    }

    /**
     *
     * @return Targeted Cell
     */
    @Override
    public Cell getCell(){
        return c;
    }

    /**
     *
     * @return Basic set of SaveObjectPackage
     */
    public SaveObjectPackage getBasicPackage(){
        SaveObjectPackage pack = new SaveObjectPackage(new String[]{this.getSaveIdentity()});
        pack.addSaveObject(new BasicLocation((new StringBuilder().append(Location.x).append(",").append(Location.y).append(",").append(Velocity.x).append(",").append(Velocity.y).append(",").append(this.Size).append(",").append(VelocityAdjust).append(",").append(uid)).toString()));
        pack.addSaveObject(this.AList.getSave());
        pack.addPackage(this.IList.getSave());
        return pack;
    }
    public void setIllegalTiles(int[] Tiles){
        this.IllegalTiles = Tiles;
    }
    public int[] getIllegalTiles(){
        return this.IllegalTiles;
    }
    @Override
    public void UpdateLocation(int delta){
        int i;
        Vector2f v = new Vector2f();
        if(this.Velocity.x > this.VelocityAdjust){
            this.Velocity.x = this.VelocityAdjust;
        }
        else if(this.Velocity.x < - this.VelocityAdjust){
            this.Velocity.x = - this.VelocityAdjust;
        }
        if(this.Velocity.y > this.VelocityAdjust){
            this.Velocity.y = this.VelocityAdjust;
        }
        else if(this.Velocity.y < - this.VelocityAdjust){
            this.Velocity.y = - this.VelocityAdjust;
        }
        for(i = 0; i < delta;i++){
            v.x = this.Location.x + this.Velocity.x;
            v.y = this.Location.y + this.Velocity.y;
            if(this.checkLocation(v)){
                this.Location.x = v.x;
                this.Location.y = v.y;
            }
            if(this.Velocity.x > - this.Friction && this.Friction > this.Velocity.x){
                this.Velocity.x = 0;
            }
            else if(this.Velocity.x > this.Friction){
                this.Velocity.x -= this.Friction;
            }
            else{
                this.Velocity.x += this.Friction;
            }
            if(this.Velocity.y > - this.Friction && this.Friction > this.Velocity.y){
                this.Velocity.y = 0;
            }
            else if(this.Velocity.y > this.Friction){
                this.Velocity.y -= this.Friction;
            }
            else{
                this.Velocity.y += this.Friction;
            }
        }
        this.PeronalMovement(delta);
    }

    /**
     *
     * @param v
     * @return
     */
    @Override
    public boolean isInExistantSpace(){
        if(c == null){
            return false;
        }
        Tile[][] c = WorldUtils.getTiles(this.c, this.Location.x,this.Location.y,this.Size);
        int x,y,i;
        for(x = 0; x < c.length;x++){
            for(y = 0; y < c[x].length;y++){
                if(c[x][y] == null){
                    return false;
                }
            }
        }
        return false;
    }
    public boolean checkLocation(Vector2f v){
        if(c == null){
            return false;
        }
        Tile[][] c = WorldUtils.getTiles(this.c, v.x,v.y,this.Size);
        int x,y,i;
        for(x = 0; x < c.length;x++){
            for(y = 0; y < c[x].length;y++){
                if(c[x][y] == null){
                    return false;
                }
                for(i = 0; i < this.IllegalTiles.length;i++){
                    if(c[x][y].getGroundType() == this.IllegalTiles[i]){
                        return false;
                    }
                }
            }
        }
        List<Entry> e = this.c.getEntriesInArea(v.x, v.y,this.Size);
        if(e.isEmpty()){
           return true; 
        }
        else if(e.size() == 1){
            if(e.get(0).equals(this)){
                return true;
            }
        }
        return false;
    }
    @Override
    public void UpdateGraphicLocation(){
        
    }
    @Override
    public Vector2f getGraphicalLocation(){
        return this.Location;
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
            return e.getUUID().equals(this.uid);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return 97 * 7 + Objects.hashCode(this.uid);
    }
    
    public abstract void PeronalMovement(int delta);
    
    private class BasicLocation extends SaveObject{
        private final String data;
        public BasicLocation(String data){
            this.data = data;
            super.setName("BasicInfo");
        }
        @Override
        public void addToSave(Save pw) throws IOException {
             pw.print(data);
        }
    }
}
