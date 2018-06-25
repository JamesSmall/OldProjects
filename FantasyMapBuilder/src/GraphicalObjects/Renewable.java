/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GraphicalObjects;

/**
 * this interface main purpose is so it can be called by non graphics objects as a singleton method, this is likey to be replaced 
 * by a greater method
 * @author Allazham
 */
public interface Renewable {
    public Renewable getNewInstance();
}
