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
public class StoredByte extends StoredValue{
    public static final String StoredByteIdentity = "StoredByte";
    private byte value;
    public StoredByte(){
        this.value = 0;
    }
    public StoredByte(byte value){
        this.value = value;
    }
    public StoredByte(byte value,String name){
        super(name);
        this.value = value;
        
    }
    public StoredByte(byte value,String name, UUID uid){
        super(name,uid);
        this.value = value;
    }
    public byte getValue(){
        return this.value;
    }
    public void setValue(byte value){
        this.value = value;
    }
    @Override
    public String saveType() {
        return StoredByte.StoredByteIdentity;
    }
    @Override
    public String saveValue() {
        return String.valueOf(this.value);
    }
    public static StoredByte generateStoredValue(String value){
        String[] values = value.split(",");
        return new StoredByte(Byte.parseByte(values[1]),values[3],UUID.fromString(values[2]));
    }
    public static StoredByte generateStoredValue(String[] values){
        return new StoredByte(Byte.parseByte(values[1]),values[3],UUID.fromString(values[2]));
    }
    
}
