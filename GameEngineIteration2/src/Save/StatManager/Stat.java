/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Save.StatManager;

import Save.BasicSaveObject;
import java.util.ArrayList;
import java.util.Objects;
import java.util.UUID;

/**
 *
 * @author Allazham
 */
public class Stat {
    public static final String UnnamedStatName = "UnnammedStat";
    private final ArrayList<Attribute> attri = new ArrayList();
    private double value;
    private long timeleft;
    private final UUID uid;
    private String name;
    private String AttributeName;
    public Stat(double value){
        this.value = value;
        this.timeleft = -1l;
        this.uid = UUID.randomUUID();
        this.name = Stat.UnnamedStatName;
        AttributeName = Attribute.UnnamedAttributeName;
    }
    public Stat(String AttributeName,String name,double value){
        this.value = value;
        this.timeleft = -1l;
        this.uid = UUID.randomUUID();
        this.name = name;
        this.AttributeName = AttributeName;
    }
    public Stat(String AttributeName,String name,double value, long timeleft){
        this.value = value;
        this.timeleft = timeleft;
        this.uid = UUID.randomUUID();
        this.name = name;
        this.AttributeName = AttributeName;
    }
    public Stat(String AttributeName,String name, double value, UUID uid){
        this.value = value;
        this.timeleft = -1l;
        this.uid = uid;
        this.name = name;
        this.AttributeName = AttributeName;
    }
    public Stat(String AttributeName,String name, double value,long timeleft, UUID uid){
        this.value = value;
        this.timeleft = timeleft;
        this.uid = uid;
        this.name = name;
        this.AttributeName = AttributeName;
    }
    public Stat(String AttributeName,String name, double value,long timeleft, UUID uid,Attribute a){
        this.value = value;
        this.timeleft = timeleft;
        this.uid = uid;
        this.name = name;
        this.attri.add(a);
        this.AttributeName = AttributeName;
    }
    void addAttribute(Attribute a){
        this.attri.add(a);
    }
    void removeAttribute(Attribute a){
        this.attri.remove(a);
    }
    public void add(double value){
        this.value += value;
        this.resetMax();
    }
    public void subtract(double value){
        this.value -= value;
        this.resetMax();
    }
    public void multiply(double value){
        this.value *= value;
        this.resetMax();
    }
    public void divide(double value){
        this.value /= value;
        this.resetMax();
    }
    private void resetMax(){
        for(Attribute a:this.attri){
            a.resetMax();
        }
    }
    public double getValue(){
        return value;
    }
    public void setValue(double value){
        this.value = value;
        this.resetMax();
    }
    public BasicSaveObject getSaveObject(){
        return new BasicSaveObject(this.toString(),Stat.UnnamedStatName);
    }
    @Override
    public String toString(){
        return String.valueOf(this.value).concat(",").concat(String.valueOf(this.timeleft)).concat(",").concat(this.name).concat(",").concat(this.AttributeName).concat(",").concat(this.uid.toString());
    }
    public UUID getUUID(){
        return this.uid;
    }
    public void setTimeLeft(long time){
        this.timeleft = time;
    }
    public long getTimeLeft(){
        if(timeleft < 0){
            return Long.MAX_VALUE;
        }
        return this.timeleft;
    }
    boolean updateTime(long timepast){
        if(timeleft == 0){
            return true;
        }
        else if(timeleft > 0){
            timeleft -= timepast;
            if(timeleft <= 0){
                timeleft = 0;
                return true;
            }
        }
        return false;
    }
    public void setName(String name){
        this.name = name;
    }
    public String getName(){
        return this.name;
    }
    public void setAttributeName(String name){
        this.AttributeName = name;
    }
    public String getAttributeName(){
        return this.AttributeName;
    }
    public boolean equalsName(String name){
        return this.name.equals(name);
    }
    public boolean equalsName(Stat a){
        return this.equalsName(a.name);
    }
    public boolean equalsStat(Stat a){
        return a.uid.equals(this.uid);
    }
    public boolean Compatiable(Stat a){
        return !this.equalsStat(a)&&this.equalsName(a);
    }
    @Override
    public boolean equals(Object o){
        if(o instanceof Stat){
            return this.equalsStat((Stat) o);
        }
        return false;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 41 * hash + Objects.hashCode(this.uid);
        return hash;
    }
    public static Stat generateStat(String stat){
        //return String.valueOf(this.value).concat(",").concat(String.valueOf(this.timeleft)).concat(",").concat(this.name).concat(",").concat(this.AttributeName).concat(",").concat(this.uid.toString());
        String[] s = stat.split(",");
        double value = Double.parseDouble(s[0]);
        long timeleft = Long.parseLong(s[1]);
        String name = s[2];
        String attrName = s[3];
        UUID uid = UUID.fromString(s[4]);
        return new Stat(attrName,name,value,timeleft,uid);
    }
    public static Stat generateStat(String stat,Attribute a){
        //return String.valueOf(this.value).concat(",").concat(String.valueOf(this.timeleft)).concat(",").concat(this.name).concat(",").concat(this.AttributeName).concat(",").concat(this.uid.toString());
        String[] s = stat.split(",");
        double value = Double.parseDouble(s[0]);
        long timeleft = Long.parseLong(s[1]);
        String name = s[2];
        String attrName = s[3];
        UUID uid = UUID.fromString(s[4]);
        return new Stat(attrName,name,value,timeleft,uid,a);
    }
}
