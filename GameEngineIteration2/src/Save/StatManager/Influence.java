/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Save.StatManager;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 *
 * @author Allazham
 */
public class Influence {
    public static final int add = 0;
    public static final int minus = 1;
    public static final int multiply = 2;
    public static final int divide = 3;
    private final UUID uid;
    private int effectType;
    private double value;
    private String name;
    private final ArrayList<String> eventName = new ArrayList();
    public Influence(String name,String eventName,int effectType,double value){
        if(!Influence.isValidNum(effectType)){
            throw new UnsupportedOperationException("invalid effect type");
        }
        this.eventName.add(eventName);
        this.value = value;
        this.uid = UUID.randomUUID();
        this.name = name;
    }
    public Influence(String name,List<String> eventName,int effectType,double value){
        if(!Influence.isValidNum(effectType)){
            throw new UnsupportedOperationException("invalid effect type");
        }
        this.eventName.addAll(eventName);
        this.value = value;
        this.uid = UUID.randomUUID();
        this.name = name;
    }
    public Influence(String name,List<String> eventName,int effectType,double value,UUID uid){
        if(!Influence.isValidNum(effectType)){
            throw new UnsupportedOperationException("invalid effect type");
        }
        this.eventName.addAll(eventName);
        this.value = value;
        this.uid = uid;
        this.name = name;
    }
    public void changeAction(Event e){
        switch(effectType){
            case add:
                e.value += this.value;
                break;
            case minus:
                e.value -= this.value;
                break;
            case multiply:
                e.value *= this.value;
                break;
            case divide:
                e.value /= this.value;
                break;
        }
    }
    public int getEffectType(){
        return this.effectType;
    }
    public ArrayList<String> getEventName(){
        return this.eventName;
    }
    public void setName(String name){
        this.name = name;
    }
    public String getName(){
        return this.name;
    }
    public void addEventNames(String name){
        this.eventName.add(name);
    }
    public void setEventNames(List<String> name){
        this.eventName.clear();
        this.eventName.addAll(name);
    }
    public void setEffectType(int type){
        if(Influence.isValidNum(type)){
            this.effectType = type;
        }
    }
    public boolean containsEffectName(String name){
        return this.eventName.contains(name);
    }
    public static boolean isValidNum(int effectType){
        switch(effectType){
            case add:
            case minus:
            case multiply:
            case divide:
                return true;
        }
        return false;
    }
    public boolean equalsInflunce(Influence o){
        return o.uid.equals(this.uid);
    }
    @Override
    public String toString(){
        StringBuilder b = new StringBuilder(this.uid.toString())
            .append(",").append(value).append(",").append(this.effectType)
                .append(",").append(name);
        for(String s: this.eventName){
            b.append(",").append(s);
        }
        return b.toString();
    }
    @Override
    public boolean equals(Object o){
        if(o instanceof Influence){
            return ((Influence)o).uid.equals(this.uid);
        }
        return false;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 83 * hash + this.uid.hashCode();
        return hash;
    }
    public static Influence generateInfluence(String value){
        ArrayList<String> eventName = new ArrayList();
        String[] values = value.split(",");
        UUID uid = UUID.fromString(values[0]);
        int effectType = Integer.parseInt(values[2]);
        double eventDouble = Double.parseDouble(values[1]);
        for(int i = 4;i < values.length;i++){
            eventName.add(values[i]);
        }
        return new Influence(values[3],eventName,effectType,eventDouble,uid);
    }
    
}
