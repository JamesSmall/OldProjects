/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Save.StatManager;

import Save.StatManager.StoredValues.StoredBoolean;
import Save.StatManager.StoredValues.StoredByte;
import Save.StatManager.StoredValues.StoredDouble;
import Save.StatManager.StoredValues.StoredFloat;
import Save.StatManager.StoredValues.StoredInteger;
import Save.StatManager.StoredValues.StoredLong;
import Save.StatManager.StoredValues.StoredString;
import Save.StatManager.StoredValues.StoredUUID;

/**
 *
 * @author Allazham
 */
public abstract class StoredValueBuilder {
    public StoredValue getStoredValue(String value){
        String data[] = value.split(",");
        switch(data[0]){
            case StoredBoolean.StoredBooleanIdentity:
                return StoredBoolean.generateStoredValue(data);
            case StoredByte.StoredByteIdentity:
                return StoredByte.generateStoredValue(data);
            case StoredDouble.StoredDoubleIdentity:
                return StoredDouble.generateStoredValue(data);
            case StoredFloat.StoredFloatIdentity:
                return StoredFloat.generateStoredValue(data);
            case StoredInteger.StoredIntegerIdentity:
                return StoredInteger.generateStoredValue(data);
            case StoredLong.StoredLongIdentity:
                return StoredLong.generateStoredValue(data);
            case StoredString.StoredStringIdentity:
                return StoredString.generateStoredValue(data);
            case StoredUUID.StoredUUIDIdentity:
                return StoredUUID.generateStoredValue(value);
            default:
                return this.getModdedStoredValue(data);
        }
    }
    public abstract StoredValue getModdedStoredValue(String[] s);
}
