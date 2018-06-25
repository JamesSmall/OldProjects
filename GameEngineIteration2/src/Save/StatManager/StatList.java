/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Save.StatManager;

import Save.BasicSaveObject;
import Save.Load;
import Save.StatManager.StoredValues.StoredBoolean;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 *
 * @author Allazham
 */
public class StatList {
    private String name = "StatList";
    private final ArrayList<Influence> inf = new ArrayList();
    private final ArrayList<Attribute> Attributes = new ArrayList();
    private final ArrayList<Stat> storedStatList = new ArrayList();
    private final ArrayList<StoredValue> StoredValues = new ArrayList();
    private static StoredValueBuilder SVB = new StoredValueBuilder() {
        @Override
        public StoredValue getModdedStoredValue(String[] s) {
            return null;
        }
    };
    public StatList(){
        
    }
    private StatList(String name,List<Attribute> attr,List<Stat> stat,List<Influence> inf, ArrayList<StoredValue> values){
        this.Attributes.addAll(attr);
        this.storedStatList.addAll(stat);
        this.inf.addAll(inf);
        this.StoredValues.addAll(values);
        this.name = name;
    }
    
    public void addStatToAttributes(Stat s){
        if(s == null){
            throw new NullPointerException("Stat is null");
        }
        Attribute a = this.getAttribute(s.getAttributeName());
        if(a != null){
            a.add(s);
        }
        else{
            this.Attributes.add(new Attribute(s));
        }
    }
    public void storeStat(Stat s){
        if(s != null){
            this.storedStatList.add(s);
        }
    }
    public void removeStat(Stat s){
        Attribute a = this.getAttribute(s.getAttributeName());
        if(a != null){
            a.remove(s);
            if(a.isEmpty()){
                this.Attributes.remove(a);
            }
        }
        this.storedStatList.remove(s);
    }
    public ArrayList<Stat> getStats(String name){
        ArrayList<Stat> stats = new ArrayList();
        for(Attribute a: this.Attributes){
            stats.addAll(a.getStats(name));
        }
        for(Stat s: this.storedStatList){
            if(s.getName().equals(name)){
                stats.add(s);
            }
        }
        return stats;
    }
    public ArrayList<Stat> getStatsByAttributeName(String name){
        ArrayList<Stat> stat = new ArrayList();
        Attribute a = this.getAttribute(name);
        if(a != null){
            stat.addAll(a.getStats());
        }
        return stat;
    }
    public void setSyncedAttribute(String name,boolean sync){
        for(Attribute a:this.Attributes){
            if(a.getName().equals(name)){
                a.setSynced(sync);
                return;
            }
        }
    }
    public Attribute getAttribute(String name){
        for(Attribute a: this.Attributes){
            if(a.getName().equals(name)){
                return a;
            }
        }
        return null;
    }
    public void addInfluence(Influence inf){
        this.inf.add(inf);
        Collections.sort(this.inf, new Sorter());
    }
    public void removeInfluence(Influence inf){
        this.inf.remove(inf);
    }
    public void addStoredValue(StoredValue v){
        if(this.getStoredValue(v.getName()) == null){
            this.StoredValues.add(v);
        }
    }
    public void removeStoredValue(String value){
        StoredValue v = this.getStoredValue(value);
        if(v != null){
            this.StoredValues.remove(v);
        }
    }
    public void removeStoredValue(StoredValue v){
        this.StoredValues.remove(v);
    }
    public StoredValue getStoredValue(String name){
        for(StoredValue v:this.StoredValues){
            if(v.getName().equals(name)){
                return v;
            }
        }
        return null;
    }
    public <T> T getStoredValue(String name,T sample){
        StoredValue s = this.getStoredValue(name);
        if(s != null){
            if(s.getClass().equals(sample.getClass())){
                return (T) s;
            }
        }
        return null;
    }
    public void processEvent(Event e){
        Attribute a = this.getAttribute(e.getAttributeName());
        if(a != null){
            for(Influence infl: this.inf){
                if(infl.containsEffectName(e.getEventName())){
                    infl.changeAction(e);
                }
            }
            e.executeEvent(a);
        }
    }
    public void setName(String name){
        this.name = name;
    }
    public String getName(){
        return this.name;
    }
    public void clear(){
        this.Attributes.clear();
        this.StoredValues.clear();
        this.inf.clear();
        this.storedStatList.clear();
    }
    public BasicSaveObject getSave(){
        ArrayList<String> strings = new ArrayList();
        strings.add(this.name+","+this.Attributes.size()+","+this.storedStatList.size()+","+this.inf.size()+","+this.StoredValues.size());
        for(Attribute a:this.Attributes){
            strings.add(a.toString());
        }
        for(Stat s: this.storedStatList){
            strings.add(s.toString());
        }
        for(Influence infl: this.inf){
            strings.add(infl.toString());
        }
        for(StoredValue v:this.StoredValues){
            strings.add(v.toString());
        }
        return new BasicSaveObject(strings.toArray(new String[0]),"StatList");
    }
    public static void setStoredValueBuilder(StoredValueBuilder SVB){
        StatList.SVB = SVB;
    }
    public static StatList generateStatList(Load l) throws IOException{
        ArrayList<Attribute> attr = new ArrayList();
        ArrayList<Stat> StoredStats = new ArrayList();
        ArrayList<Influence> infl = new ArrayList();
        ArrayList<StoredValue> SValue = new ArrayList();
        List<String> strings = l.readFromInputFile();
        int Loc = 0;
        String[] temp = strings.get(0).split(",");
        String name = temp[0];
        int stat = Integer.parseInt(temp[2]),inf = Integer.parseInt(temp[3]),storedvalue= Integer.parseInt(temp[4]),attribute = Integer.parseInt(temp[1]);
        for(int temp0 = 0;temp0 < attribute;temp0++){
            Loc++;
            attr.add(Attribute.generateAttribute(strings.get(Loc)));
        } 
        for(int temp0 = 0;temp0 < stat;temp0++){
            Loc++;
            StoredStats.add(Stat.generateStat(strings.get(Loc)));
        }
        for(int temp0 = 0; temp0 < inf;temp0++){
            Loc++;
            infl.add(Influence.generateInfluence(strings.get(Loc)));
        }
        for(int temp0 = 0; temp0 < storedvalue;temp0++){
            Loc++;
            SValue.add(SVB.getStoredValue(strings.get(Loc)));
        }
        return new StatList(name,attr,StoredStats,infl,SValue);
    }
    public static void CheckElements(StatList sl1, StatList sl2){
        for(Attribute a1:sl1.Attributes){
            for(Attribute a2:sl2.Attributes){
                Attribute.checkElements(a1, a2);
            }
        }
        for(Stat s1: sl1.storedStatList){
            for(Stat s2: sl2.storedStatList){
                if(s1.equals(s2)){
                    sl2.storedStatList.remove(s2);
                    sl2.storedStatList.add(s1);
                }
            }
        }
        for(Influence i1: sl1.inf){
            for(Influence i2: sl2.inf){
                if(i1.equals(i2)){
                    sl2.inf.remove(i2);
                    sl2.inf.add(i1);
                }
            }
            Collections.sort(sl2.inf, new Sorter());
        }
        for(StoredValue v1: sl1.StoredValues){
            for(StoredValue v2:sl2.StoredValues){
                if(v1.equals(v2)){
                    sl2.StoredValues.remove(v2);
                    sl2.StoredValues.add(v1);
                }
            }
        }
    }
    private static class Sorter implements Comparator<Influence>{
        @Override
        public int compare(Influence o1, Influence o2) {
            return(o1.getEffectType()<o2.getEffectType() ? -1 : (o1.getEffectType()==o2.getEffectType()? 0 : 1));
        }
    }
}