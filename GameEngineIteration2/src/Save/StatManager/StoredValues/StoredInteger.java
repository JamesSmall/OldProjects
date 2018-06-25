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
public class StoredInteger extends StoredValue{
    public static final String StoredIntegerIdentity = "StoredInt";
    private int value;
    public StoredInteger(){
        this.value = 0;
    }
    public StoredInteger(int value){
        this.value = value;
    }
    public StoredInteger(int value,String name){
        super(name);
        this.value = value;
        
    }
    public StoredInteger(int value,String name, UUID uid){
        super(name,uid);
        this.value = value;
    }
    public int getValue(){
        return this.value;
    }
    public void setValue(int value){
        this.value = value;
    }
    @Override
    public String saveType() {
        return StoredInteger.StoredIntegerIdentity;
    }
    @Override
    public String saveValue() {
        return String.valueOf(this.value);
    }
    public static StoredInteger generateStoredValue(String value){
        String[] values = value.split(",");
        return new StoredInteger(Integer.parseInt(values[1]),values[3],UUID.fromString(values[2]));
    }
    public static StoredInteger generateStoredValue(String[] values){
        return new StoredInteger(Integer.parseInt(values[1]),values[3],UUID.fromString(values[2]));
    }
    
}
