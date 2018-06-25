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
public class StoredDouble extends StoredValue{
    public static final String StoredDoubleIdentity = "StoredDouble";
    private double value;
    public StoredDouble(){
        this.value = 0;
    }
    public StoredDouble(double value){
        this.value = value;
    }
    public StoredDouble(double value,String name){
        super(name);
        this.value = value;
        
    }
    public StoredDouble(double value,String name, UUID uid){
        super(name,uid);
        this.value = value;
    }
    public double getValue(){
        return this.value;
    }
    public void setValue(double value){
        this.value = value;
    }
    @Override
    public String saveType() {
        return StoredDouble.StoredDoubleIdentity;
    }
    @Override
    public String saveValue() {
        return String.valueOf(this.value);
    }
    public static StoredDouble generateStoredValue(String value){
        String[] values = value.split(",");
        return new StoredDouble(Double.parseDouble(values[1]),values[3],UUID.fromString(values[2]));
    }
    public static StoredDouble generateStoredValue(String[] values){
        return new StoredDouble(Double.parseDouble(values[1]),values[3],UUID.fromString(values[2]));
    }
}
