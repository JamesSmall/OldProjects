
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Save.StatManager;

/**
 *
 * @author Allazham
 */
public class Event {
    public static final int add = 0;
    public static final int minus = 1;
    public static final int neturalMinus = 3;
    public static final int neturalAdd = 2;
    private String eventName;
    private String AttributeName;
    private int type = 0;
    double value;
    public Event(String eventName,String AttributeName,double value, int type){
        if(!Event.isValidType(type)){
            throw new UnsupportedOperationException("value must be supported");
        }
        this.eventName = eventName;
        this.AttributeName = AttributeName;
        this.value = value;
        this.type = type;
    }
    void executeEvent(Attribute a){
        a.setSynced(false);
        if(this.isAdd()){
            if(!this.isSpecific() || this.value > 0){
                a.addCurrent(value);
            }
        }
        else{
            if(!this.isSpecific() || this.value > 0){
                a.subtractCurrent(value);
            }
        }
    }
    public String getAttributeName(){
        return this.AttributeName;
    }
    public boolean isMinus(){
        return this.type == Event.minus || this.type == Event.neturalMinus;
    }
    public boolean isAdd(){
        return this.type == Event.add || this.type == Event.neturalMinus; 
    }
    public boolean isSpecific(){
        return this.type == Event.add || this.type == Event.minus;
    }
    public String getEventName(){
        return this.eventName;
    }
    public static boolean isValidType(int value){
        return value == Event.add || value == Event.minus || value == Event.neturalAdd || value == Event.neturalMinus;
    }
}
