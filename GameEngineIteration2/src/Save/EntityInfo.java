/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Save;

import Item.ItemList;
import Save.StatManager.StatList;
import java.util.UUID;
import org.lwjgl.util.vector.Vector2f;

/**
 *
 * @author Allazham
 */
public class EntityInfo {
        public Vector2f loc,Vel;
        public float size,VelocityAdjust;
        public ItemList IList;
        public StatList aList;
        public UUID UUID;
}
