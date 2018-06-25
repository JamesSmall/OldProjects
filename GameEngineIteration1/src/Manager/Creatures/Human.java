/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Manager.Creatures;

import Holder.Image;
import Holder.MasterLoad;
import Manager.CreatureManager;
import Manager.Interaction;
import Manager.StatManager;
import Manager.WorldGPS;
import java.util.List;

/**
 *
 * @author Allazham
 */
public class Human extends WorldGPS implements CreatureManager {
    private Image img;
    StatManager stat;
    public Human(float x, float y,float WidthAndHeight, boolean disabled){
        super(x,y,WidthAndHeight,null,disabled);
        this.img = MasterLoad.getImage("wolf").getNewInstance();
        super.setImage(img);
    }
    @Override
    public void Update() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void Update(String args) {
        //unsuppurted
    }

    @Override
    public StatManager getStatManager() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Interaction getInteractionManager() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<String> getSaveData() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
