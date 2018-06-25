/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Graphics.Base.UI.Pack;

import Graphics.Base.UI.Panel;
import Holder.Texture;

/**
 *
 * @author Allazham
 */
public class InventoryTrade extends Panel{
    public InventoryTrade(boolean disabled){
        super(disabled);
    }
    public InventoryTrade(float x, float y, float width, float height, int orintation,boolean disabled){
        super(x,y,width,height,orintation,disabled);
    }
    public InventoryTrade(float x, float y, float width, float height, int orintation,Texture t,boolean disabled){
        super(x,y,width,height,orintation,t,disabled);
    }
}
