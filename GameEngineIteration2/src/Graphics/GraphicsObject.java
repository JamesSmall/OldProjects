/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Graphics;


/**
 *
 * @author james small
 */
@SuppressWarnings("unchecked")
public abstract class GraphicsObject {
    private int Priority = 0;
    private boolean disabled = false;
    protected abstract void checkCondations(GraphicConditions c);
    protected abstract void render();
    public GraphicsObject(boolean disabled){
        this.disabled = disabled;
    }
    public void setDisabled(boolean disabled){
        this.disabled = disabled;
    }
    public boolean getDisabled(){
        return disabled;
    }
    public int getPriority(){
        return this.Priority;
    }
    public void setPriority(int priority){
        this.Priority = priority;
    }
}
