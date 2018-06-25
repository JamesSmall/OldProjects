/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Graphics;

/**
 * Last Edited:3/2/2013
 *the purpose of this gui is for moving objects based on mouse OR meters such as health, this is not ment for buttons and
 * highly active gui componets
 * @author james small
 */
public abstract class UniqueGUI extends Abstract2DObject{
    private UserActionMouse m;
    public UniqueGUI(boolean disabled){
        super(disabled);
        super.setPriority(200);
    }
    @Override
    protected void checkCondations(GraphicConditions c){
        if(c.isCentered()){
            c.setCentered(false);
        }
        super.checkCondations(c);
    }
    public abstract String getInstruction();
}
