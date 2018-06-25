/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Save.StatManager.StoredValues;

import Save.StatManager.StoredValue;
import java.util.UUID;

/**
 *
 * @author Allazham
 */
public class StoredLong extends StoredValue{
    public static final String StoredLongIdentity = "StoredLong";
    private long value;
    public StoredLong(){
        this.value = 0l;
    }
    public StoredLong(long value){
        this.value = value;
    }
    public StoredLong(long value,String name){
        super(name);
        this.value = value;
        
    }
    public StoredLong(long value,String name, UUID uid){
        super(name,uid);
        this.value = value;
    }
    public long getValue(){
        return this.value;
    }
    public void setValue(long value){
        this.value = value;
    }
    @Override
    public String saveType() {
        return StoredLong.StoredLongIdentity;
    }
    @Override
    public String saveValue() {
        return String.valueOf(this.value);
    }
    public static StoredLong generateStoredValue(String value){
        String[] values = value.split(",");
        return new StoredLong(Long.parseLong(values[1]),values[3],UUID.fromString(values[2]));
    }
    public static StoredLong generateStoredValue(String[] values){
        return new StoredLong(Long.parseLong(values[1]),values[3],UUID.fromString(values[2]));
    }
    
}
