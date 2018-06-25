/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Graphics.UserActions;

import java.util.ArrayList;
import org.lwjgl.util.vector.Vector2f;

/**
 *
 * @author Allazham
 */
public class UIZonesManager {
    private static final ArrayList<UIZone> zones = new ArrayList();
    private UIZonesManager(){
        
    }
    public static void addUIZone(UIZone ui){
        zones.add(ui);
    }
    public static void removeUIZone(UIZone ui){
        zones.remove(ui);
    }
    public static boolean containsUIZone(UIZone ui){
        return zones.contains(ui);
    }
    public static boolean isPointInUI(Vector2f v){
        for(UIZone zg:zones){
            if(zg.isDestroyable()){
                zones.remove(zg);
            }
            else if(zg.isInside(v)){
                return true;
            }
        }
        return false;
    }
}
