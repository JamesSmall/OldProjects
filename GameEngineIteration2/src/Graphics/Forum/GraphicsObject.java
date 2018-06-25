/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Graphics.Forum;

import org.lwjgl.LWJGLException;


/**
 *
 * @author james small
 */
@SuppressWarnings("unchecked")
public abstract class GraphicsObject {
    private int Priority = 0;
    private boolean disabled = false;
    protected abstract void checkCondations(GraphicConditions c);
    protected abstract void render() throws LWJGLException;
    public abstract void disposeGraphics();
    public GraphicsObject(boolean disabled){
        this.disabled = disabled;
    }
    public void setDisabled(boolean disabled){
        this.disabled = disabled;
    }
    public boolean isDisabled(){
        return disabled;
    }
    public int getPriority(){
        return this.Priority;
    }
    public void setPriority(int priority){
        this.Priority = priority;
    }
}
