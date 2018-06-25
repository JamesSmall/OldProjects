/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Graphics.Base.UI.Pack;

import Graphics.Base.ScreenUI;
import Graphics.Base.UI.Panel;
import Holder.Texture;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Allazham
 */
public class Inventory extends Panel{
    private ArrayList<ItemMoving> items = new ArrayList();
    private ArrayList<ItemSlot> slot = new ArrayList();
    private int InventorySize = 40;
    private int SlotWidth = 10;
    public Inventory(boolean disabled){
        super(disabled);
    }
    public Inventory(float x, float y, float width, float height, int orintation,boolean disabled){
        super(x,y,width,height,orintation,disabled);
    }
    public Inventory(float x, float y, float width, float height, int orintation,Texture t,boolean disabled){
        super(x,y,width,height,orintation,t,disabled);
    }
    public double getPredictedValues(){
        return 1/SlotWidth;
    }
    public double getPredictedSpace(){
        return 0;
    }
    @Override
    public void disposeGraphics(){
        int i;
        for(i = 0; i < items.size();i++){
            items.get(i).disposeGraphics();
        }
        for(i = 0; i < slot.size();i++){
            slot.get(i).disposeGraphics();
        }
        super.disposeGraphics();
    }
    private class ItemMoving extends ScreenUI{
        public ItemMoving(boolean disabled){
            super(disabled);
        }
        public ItemMoving(float x, float y, float width, float height, int orintation,boolean disabled){
            super(x,y,width,height,orintation,disabled);
        }
        public ItemMoving(float x, float y, float width, float height, int orintation,Texture t,boolean disabled){
            super(x,y,width,height,orintation,t,disabled);
        }
    }
    private class ItemSlot extends ScreenUI{
        public ItemSlot(boolean disabled){
            super(disabled);
        }
        public ItemSlot(float x, float y, float width, float height, int orintation,boolean disabled){
            super(x,y,width,height,orintation,disabled);
        }
        public ItemSlot(float x, float y, float width, float height, int orintation,Texture t,boolean disabled){
            super(x,y,width,height,orintation,t,disabled);
        }
    }
}
