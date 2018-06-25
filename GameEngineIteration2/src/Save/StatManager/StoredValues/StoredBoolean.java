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
public class StoredBoolean extends StoredValue{
    public static final String StoredBooleanIdentity = "StoredBoolean";
    private boolean value;
    public StoredBoolean(){
        this.value = false;
    }
    public StoredBoolean(boolean value){
        this.value = value;
    }
    public StoredBoolean(boolean value,String name){
        super(name);
        this.value = value;
        
    }
    public StoredBoolean(boolean value,String name, UUID uid){
        super(name,uid);
        this.value = value;
    }
    public boolean getValue(){
        return this.value;
    }
    public void setValue(boolean value){
        this.value = value;
    }
    @Override
    public String saveType() {
        return StoredBoolean.StoredBooleanIdentity;
    }
    @Override
    public String saveValue() {
        return String.valueOf(this.value);
    }
    public static StoredBoolean generateStoredValue(String value){
        String[] values = value.split(",");
        return new StoredBoolean(Boolean.parseBoolean(values[1]),values[3],UUID.fromString(values[2]));
    }
    public static StoredBoolean generateStoredValue(String[] values){
        return new StoredBoolean(Boolean.parseBoolean(values[1]),values[3],UUID.fromString(values[2]));
    }
    
}
