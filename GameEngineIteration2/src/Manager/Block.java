/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Manager;

import Holder.Image;
import Holder.MasterLoad;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Allazham
 */
public class Block extends WorldGPS{
    private int x,y;
    private int GroundType,TerrianType;
    //this is for debug purposes only
    public Block(int x,int y,Image img, boolean disabled){
        super(x*16,y*16,8,img,disabled);
        this.x = x;
        this.y = y;
        super.setPriority(90);
        this.GroundType = -1;
        this.TerrianType = -1;
    }
    public Block(int x,int y,int Type, boolean disabled){
        super(x*16,y*16,8,Block.getImagedBasedOnType(Type),disabled);
        this.x = x;
        this.y = y;
        this.GroundType = Type;
        super.setPriority(90);
        this.ConfigTerrianType(Type);
    }
    private void ConfigTerrianType(int type){
        switch(type)
        {
            case 5:
            case 2:
                //desert && obsidian
                this.TerrianType = 2;
                break;
            case 1:
                this.TerrianType = 1;
                break;
            case 3:
                //water
                this.TerrianType = 3;
                break;
            case 4:
                //lava
                this.TerrianType = 4;
                break;
        }
    }
    public void setGroundType(int type){
        this.GroundType = type;
    }
    public void setTerrianType(int terrian){
        this.TerrianType = terrian;
    }
    public int getTerrianType(){
        return this.TerrianType;
    }
    public int getGroundType(){
        return this.GroundType;
    }
    /*
     * personal entity Position
     * 
     */
    public void setX(int x){
        super.setX(x*16);
        this.x = x;
    }
    public void setY(int y){
        super.setY(y*16);
        this.y = y;
    }
    public void addX(int x){
        super.addX(x*16);
    }
    public void addY(int y){
        super.addY(y*16);
    }
    public int getBlockX(){
        return this.x;
    }
    public int getBlockY(){
        return this.y;
    }
    /*
     * Objects own scale
     * 
     */
    @Override
    public void setScale(float scale){
        super.setScale(scale*16);
    }
    @Override
    public void addScale(float scale){
        super.addScale(scale*16);
    }
    @Override
    public void multipleScale(float scale){
        super.multipleScale(scale*16);
    }
    /*
     * minor image management
     * 
     */
    //height && width manager
    
    @Override
    public void setHeight(float height){
        this.setHeight(height*16);
    }
    @Override
    public void setWidth(float width){
        super.setWidth(width*16);
    }
    @Override
    public void multiplyHeight(float height){
        super.multiplyHeight(height*16);
    }
    @Override
    public void multiplyWidth(float width){
        super.multiplyWidth(width*16);
    }
    @Override
    public void addWidth(float width){
        super.addWidth(width*16);
    }
    @Override
    public void addHeight(float height){
        super.addHeight(height*16);
    }
    public List<String> getSaveData(){
        List<String> data = new ArrayList();
        data.add("@Start::block");
        data.add("::x="+(this.x));
        data.add("::y="+(this.y));
        data.add("::GroundType="+this.GroundType);
        data.add("@End::block");
        return data;
    }
    private static Image getImagedBasedOnType(int type){
        switch(type){
            case 1:
                return MasterLoad.getImage("grass");
           case 2:
                 return MasterLoad.getImage("desert");
            case 3:
               return MasterLoad.getImage("water");
            case 4:
                 return MasterLoad.getImage("lava");
           case 5:
                return MasterLoad.getImage("obsidian");
            default:
                return null;
        }
    }
}
