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
public class StoredUUID extends StoredValue{
    public static final String StoredUUIDIdentity = "StoredUUID";
    private UUID value;
    public StoredUUID(){
        this.value = UUID.randomUUID();
    }
    public StoredUUID(UUID value){
        this.value = value;
    }
    public StoredUUID(UUID value,String name){
        super(name);
        this.value = value;
        
    }
    public StoredUUID(UUID value,String name, UUID uid){
        super(name,uid);
        this.value = value;
    }
    public UUID getValue(){
        return this.value;
    }
    public void setValue(UUID value){
        this.value = value;
    }
    public void setValue(String value){
        this.value = UUID.fromString(value);
    }
    @Override
    public String saveType() {
        return StoredUUID.StoredUUIDIdentity;
    }
    @Override
    public String saveValue() {
        return String.valueOf(this.value);
    }
    public static StoredUUID generateStoredValue(String value){
        String[] values = value.split(",");
        return new StoredUUID(UUID.fromString(values[1]),values[3],UUID.fromString(values[2]));
    }
    public static StoredUUID generateStoredValue(String[] values){
        return new StoredUUID(UUID.fromString(values[1]),values[3],UUID.fromString(values[2]));
    }
}
