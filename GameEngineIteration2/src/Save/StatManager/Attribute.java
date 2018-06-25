/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Save.StatManager;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;

/**
 *
 * @author Allazham
 */
public final class Attribute {
    public static final String UnnamedAttributeName = "UnnamedAttribute";
    private final String name;// = Attribute.UnnamedAttributeName;
    private boolean synced = true;
    private double maxValue = 0,value = 0;
    private final ArrayList<Stat> stats = new ArrayList();
    public Attribute(Stat s){
        this.add(s);
        this.name = s.getAttributeName();
    }
    private Attribute(Collection<Stat> s,String name, boolean sync,double value){
        if(s.isEmpty()){
            throw new UnsupportedOperationException("Unsupported action taken");
        }
        this.name = name;
        this.synced = sync;
        this.value = value;
        this.add(s);
    }
    void add(Stat s){
        this.addStat0(s);
        this.checkValue();
    }
    void add(Collection<Stat> s){
        Stat[] sr = s.toArray(new Stat[]{});
        for(Stat r:sr){
            this.addStat0(r);
        }
        this.checkValue();
    }
    void remove(Stat s){
       this.removeStat0(s);
       this.checkValue();
    }
    void remove(Collection<Stat> s){
        Stat[] ss = s.toArray(new Stat[]{});
        for(Stat sz:ss){
            this.removeStat0(sz);
        }
        this.checkValue();
    }
    boolean updateTime(long time){
        ArrayList<Stat> list = new ArrayList();
        Stat[] values = this.stats.toArray(new Stat[0]);
        for(Stat s: values){
            if(s.updateTime(time)){
                list.add(s);
            }
        }
        this.remove(list);
        return this.stats.isEmpty();
    }
    public ArrayList<Stat> getStats(String name){
        ArrayList<Stat> ret = new ArrayList();
        for(Stat s:this.stats){
            if(s.getName().equals(name)){
                ret.add(s);
            }
        }
        return ret;
    }
    public ArrayList<Stat> getStats(){
        return new ArrayList(this.stats);
    }
    public boolean containsStat(Stat s){
        return this.stats.contains(s);
    }
    public boolean containsStats(Collection<Stat> s){
        return this.stats.containsAll(s);
    }
    public synchronized void resetMax(){
        maxValue = 0;
        for(Stat s: stats){
            value += s.getValue();
        }
        this.checkValue();
    }
    private void checkValue(){
        if(this.synced || this.value > maxValue){
            this.value = maxValue;
        }
    }
    private void addStat0(Stat s){
        if(!this.stats.contains(s)){
            stats.add(s);
            s.addAttribute(this);
            this.maxValue += s.getValue();
        }
    }
    private void removeStat0(Stat s){
        this.stats.remove(s);
        s.removeAttribute(this);
        this.maxValue -= s.getValue();
    }
    public String getName(){
        return this.name;
    }
    public void setSynced(boolean synced){
        this.synced = true;
    }
    public boolean isSynced(){
        return this.synced;
    }
    public boolean isEmpty(){
        return this.stats.isEmpty();
    }
    void subtractCurrent(double value){
        this.value -= value;
        this.checkValue();
    }
    void addCurrent(double value){
        this.value += value;
        this.checkValue();
    }
    @Override
    public String toString(){
        StringBuilder b = new StringBuilder(this.name).append(",").append(value).append(",");
        if(this.synced){
            b.append("1");
        }
        else{
            b.append("0");
        }
        for(Stat s: this.stats){
            b.append(":").append(s.toString());
        }
        return b.toString();
    }
    static void checkElements(Attribute a1, Attribute a2){
        if(a1.name.equals(a2.name)){
            for(Stat s1: a1.stats){
                for(Stat s2: a2.stats){
                    if(s1.equals(s2)){
                        a2.stats.remove(s2);
                        a2.stats.add(s1);
                    }
                }
            }
        }
    }
    public static Attribute generateAttribute(String Data){
        String[] data = Data.split(":");
        double value = 0;
        boolean sync = false;
        String name = null;
        LinkedList<Stat> az = new LinkedList();
        String temp[];
        for(String dat: data){
            if(name == null){
                temp = dat.split(",");
                name = temp[0];
                value = Double.parseDouble(temp[1]);
                if(temp[2].equals("1")){
                    sync = true;
                }
                else{
                    sync = false;
                }
            }
            else{
                az.add(Stat.generateStat(dat));
            }
        }
        return new Attribute(az,name,sync,value);
    }
}
