/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Manager;

import java.util.List;

/**
 *
 * @author Allazham
 */
public interface CreatureManager {
    public void Update();
    public void Update(String args);
    public StatManager getStatManager();
    public Interaction getInteractionManager();
    public void setX(float x);
    public void setY(float y);
    public float getX();
    public float getY();
    public List<String> getSaveData();
    public boolean isInScreenPersonalBox(float x, float y);
    public boolean isInScreenHitBox(float x, float y);
}

