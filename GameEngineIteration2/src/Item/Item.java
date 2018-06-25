/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Item;

import Save.Load;
import Save.SaveObjectPackage;
import Save.StatManager.StatList;
import World.Cell;
import java.io.IOException;
import java.util.UUID;

/**
 *
 * @author Allazham
 */
public class Item {
    public static final int UNKNOWN = 0; 
    public static final int ARTIFACT = 1;
    public static final int WEAPON = 2;
    public static final int ARMOR = 3;
    public static final int TOOL = 4;
    
    public static final int LESSTHANSTACK = -1;
    public static final int EQUALTOSTACK = 0;
    public static final int GREATERTHANSTACK = 1;
    
    private final UUID uid;
    private final StatList al;
    private String name;
    private int ID = 0;
    private double weight;
    private int Stack = 1;
    private boolean stackable = true;
    public Item(String name,StatList al,int id,int stack, double weight,boolean stackable){
        this.name = name;
        this.stackable = stackable;
        this.al = al;
        this.ID = id;
        this.weight = weight;
        this.Stack = stack;
        uid = UUID.randomUUID();
    }
    public UUID getUUID(){
        return this.uid;
    }
    public StatList getStatList(){
        return this.al;
    }
    public void setName(String name){
        this.name = name;
    }
    public String getName(){
        return this.name;
    }
    public void setType(int type){
        this.ID = type;
    }
    public int getType(){
        return this.ID;
    }
    public int getStackSize(){
        return this.Stack;
    }
    public void setStackable(boolean stack){
        this.stackable = stack;
    }
    public boolean isStackable(){
        return this.stackable;
    }
    public void setStackSize(int stack){
        if(!this.stackable){
            throw new UnsupportedOperationException("item is not stackable");
        }
        this.Stack = stack;
    }
    public void setWeight(double weight){
        this.weight = weight;
    }
    public double getWeight(){
        return this.weight;
    }
    
    public void forceCell(Cell c){
    }
    public boolean MergeStack(Item a){
        return false;
    }
    public boolean canBreak(int i){
        return (BreakResult(i) == LESSTHANSTACK);
    }
    public int BreakResult(int sample){
        if(sample < this.Stack){
            return LESSTHANSTACK;
        }
        else if(sample == this.Stack){
            return Item.EQUALTOSTACK;
        }
        else{
            return GREATERTHANSTACK;
        }
    }
    public Item getNewInstance(){
        return new Item(this.name,al,ID,Stack,weight,stackable);
    }
    public static Item loadItem(Load l) throws IOException{
        return null;
    }
}
