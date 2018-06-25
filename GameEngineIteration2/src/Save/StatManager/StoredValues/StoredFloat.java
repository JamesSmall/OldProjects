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
public class StoredFloat extends StoredValue{
    public static final String StoredFloatIdentity = "StoredFloat";
    private float value;
    public StoredFloat(){
        this.value = 0f;
    }
    public StoredFloat(float value){
        this.value = value;
    }
    public StoredFloat(float value,String name){
        super(name);
        this.value = value;
        
    }
    public StoredFloat(float value,String name, UUID uid){
        super(name,uid);
        this.value = value;
    }
    public float getValue(){
        return this.value;
    }
    public void setValue(float value){
        this.value = value;
    }
    @Override
    public String saveType() {
        return StoredFloat.StoredFloatIdentity;
    }
    @Override
    public String saveValue() {
        return String.valueOf(this.value);
    }
    public static StoredFloat generateStoredValue(String value){
        String[] values = value.split(",");
        return new StoredFloat(Float.parseFloat(values[1]),values[3],UUID.fromString(values[2]));
    }
    public static StoredFloat generateStoredValue(String[] values){
        return new StoredFloat(Float.parseFloat(values[1]),values[3],UUID.fromString(values[2]));
    }
}
