/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package World.Entities.ScriptManaged;

import Save.SaveObjectPackage;
import World.Entities.Bodies.Body;

/**
 *
 * @author Allazham
 */
public class ScriptControlledBody extends Body{

    @Override
    public void PeronalMovement(int delta) {
        
    }

    @Override
    public void disposeGraphics() {
        
    }

    @Override
    public void Update(int delta) {
        
    }

    @Override
    public SaveObjectPackage getSave() {
        return super.getBasicPackage();
    }

    @Override
    public void disposeEntry() {
        
    }

    @Override
    public String getSaveIdentity() {
        return "ScriptControlledBody";
    }
    @Override
    public boolean isPersistent() {
        return false;
    }
}
