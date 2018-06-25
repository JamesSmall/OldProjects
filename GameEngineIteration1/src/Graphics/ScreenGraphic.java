/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Graphics;

/**
 *
 * @author Allazham
 */
public abstract class ScreenGraphic extends Abstract2DObject{
    public ScreenGraphic(boolean disabled){
        super(disabled);
        super.setPriority(101);
    }
    @Override
    protected void checkCondations(GraphicConditions c){
        if(!c.isCentered()){
            c.setCentered(true);
        }
        super.checkCondations(c);
    }
}
