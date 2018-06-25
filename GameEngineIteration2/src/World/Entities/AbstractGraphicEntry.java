/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package World.Entities;

import Graphics.Base.ScreenGraphic;
import Graphics.Forum.GraphicsObject;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import org.lwjgl.util.vector.Vector2f;

/**
 *
 * @author Allazham
 */
public abstract class AbstractGraphicEntry extends ScreenGraphic implements Entry{
    private Cell c;
    private final StatList AList;
    private final ItemList IList;
    private final Vector2f Location;
    private final Vector2f Velocity;
    private final Vector2f GraphicLocation = new Vector2f();
    //private static final float WorldMin = .005f;
    private float Friction = .0005f;
    private float Size = 1;
    private float VelocityAdjust = .005f;
    private int[] IllegalTiles = new int[0];
    private final UUID uid;
    private boolean markedforDelete = false;
    public AbstractGraphicEntry(){
        super(false);
        AList = new StatList();
        this.Location = new Vector2f();
        this.Velocity = new Vector2f();
        this.IList = new ItemList();
        uid = UUID.randomUUID();
    }
    public AbstractGraphicEntry(EntityInfo e){
        super(false);
        this.Location = e.loc;
        this.Velocity = e.Vel;
        this.AList = e.aList;
        this.IList = e.IList;
        this.Size = e.size;
        this.VelocityAdjust = e.VelocityAdjust;
        uid = e.UUID;
    }
    public abstract void PeronalMovement(int delta);
    
    public void setVelocityAdjustement(float adjustement){
        this.VelocityAdjust = adjustement;
    }
    public float getVelocityAdjustemenet(){
        return this.VelocityAdjust;
    }
    @Override
    public void setLocation(Vector2f v) {
        this.Location.x = v.x;
        this.Location.y = v.y;
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
    public void setSize(float size) {
        this.Size = size;
    }

    @Override
    public float getLocationX() {
        return this.Location.x;
    }

    @Override
    public float getLocationY() {
       return this.Location.y;
    }

    @Override
    public float[] getLocation() {
        return new float[]{this.Location.x,this.Location.y};
    }

    @Override
    public Vector2f getLocationVector() {
        return this.Location;
    }

    @Override
    public float getScale() {
        return this.Size;
    }
    @Override
    public UUID getUUID(){
        return this.uid;
    }
    @Override
    public boolean Intersect(Entry e) {
        double distance = Math.sqrt(Math.pow(e.getLocationX()-this.Location.x, 2)+Math.pow(e.getLocationY()-this.Location.y, 2));
        if(this.Size < 0 || e.getScale() < 0){
            return false;
        }
        return (distance < this.Size+e.getScale());
    }

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
    @Override
    public List<GraphicsObject> getGraphicObjects(){
        List<GraphicsObject> g = new ArrayList();
        g.add(this);
        return g;
    }
    @Override
    public ItemList getItemList(){
        return this.IList;
    }
    
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
    @Override
    public void setVelocity(Vector2f v){
        this.Velocity.x = v.x;
        this.Velocity.y = v.y;
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
    @Override
    public void addVelocity(Vector2f v){
        Vector2f.add(v, this.Velocity, this.Velocity);
    }
    @Override
    public float getVelocityX(){
        return this.Velocity.x;
    }
    @Override
    public float getVelocityY(){
        return this.Velocity.y;
    }
    @Override
    public float[] getVelocity(){
        return new float[]{this.Velocity.x,this.Velocity.y};
    }
    @Override
    public Vector2f getVelocityVector(){
        return this.Velocity;
    }
    public SaveObjectPackage getBasicPackage(){
        SaveObjectPackage pack = new SaveObjectPackage(new String[]{this.getSaveIdentity()});
        pack.addSaveObject(new BasicLocation((new StringBuilder().append(Location.x).append(",").append(Location.y).append(",").append(Velocity.x).append(",").append(Velocity.y).append(",").append(this.Size).append(",").append(VelocityAdjust).append(",").append(uid)).toString()));
        pack.addSaveObject(this.AList.getSave());
        pack.addPackage(this.IList.getSave());
        return pack;
    }
    @Override
    public void setCell(Cell c){
        this.c = c;
        this.IList.forceCell(c);
    }
    @Override
    public Cell getCell(){
        return c;
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
        Tile[][] bc = WorldUtils.getTiles(this.c, v.x,v.y,this.Size);
        int x,y,i;
        for(x = 0; x < bc.length;x++){
            for(y = 0; y < bc[x].length;y++){
                if(bc[x][y] == null){
                    return false;
                }
                for(i = 0; i < this.IllegalTiles.length;i++){
                    if(bc[x][y].getGroundType() == this.IllegalTiles[i]){
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
        this.GraphicLocation.x = this.Location.x;
        this.GraphicLocation.y = this.Location.y;
    }
    @Override
    public Vector2f getGraphicalLocation(){
        return this.GraphicLocation;
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