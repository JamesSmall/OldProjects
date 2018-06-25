/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Save.StatManager;

import java.util.Objects;
import java.util.UUID;

/**
 *
 * @author Allazham
 */
public abstract class StoredValue {
    private final UUID uid;
    private String name;
    public StoredValue(){
        this.uid = UUID.randomUUID();
        this.name = "UnnamedAttriute";
    }
    public StoredValue(String name){
        this.uid = UUID.randomUUID();
        this.name = name;
    }
    public StoredValue(String name, UUID uid){
        this.uid = uid;
        this.name = name;
    }
    
    public abstract String saveValue();
    public abstract String saveType();
    
    @Override
    public String toString(){
        StringBuilder b = new StringBuilder(this.saveType())
                .append(",").append(this.saveValue()).append(",")
                .append(uid.toString()).append(",").append(name);
        return b.toString();
    }
    public UUID getUUID(){
        return this.uid;
    }
    public void setName(String name){
        this.name = name;
    }
    public String getName(){
        return this.name;
    }
    public boolean equalsStoredValue(StoredValue v){
        return this.uid.equals(v.uid);
    }
    @Override
    public boolean equals(Object o){
        if(o instanceof StoredValue){
            return this.equalsStoredValue((StoredValue) o);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return 53 * 5 + Objects.hashCode(this.uid);
    }
}
