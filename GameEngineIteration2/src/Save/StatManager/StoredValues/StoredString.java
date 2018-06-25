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
public class StoredString extends StoredValue{
    public static final String StoredStringIdentity = "StoredString";
    private String value;
    public StoredString(){
        this.value = "";
    }
    public StoredString(String value){
        this.value = value;
    }
    public StoredString(String value,String name){
        super(name);
        this.value = value;
        
    }
    public StoredString(String value,String name, UUID uid){
        super(name,uid);
        this.value = value;
    }
    public String getValue(){
        return this.value;
    }
    public void setValue(String value){
        this.value = value;
    }
    @Override
    public String saveType() {
        return StoredString.StoredStringIdentity;
    }
    @Override
    public String saveValue() {
        return String.valueOf(this.value);
    }
    public static StoredString generateStoredValue(String value){
        String[] values = value.split(",");
        return new StoredString(values[1],values[3],UUID.fromString(values[2]));
    }
    public static StoredString generateStoredValue(String[] values){
        return new StoredString(values[1],values[3],UUID.fromString(values[2]));
    }
}
