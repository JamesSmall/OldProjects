/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Graphics;

/*
 * @author james small
 */
public abstract class Abstract2DObject extends GraphicsObject{
    public Abstract2DObject(boolean disabled){
       super(disabled); 
       super.setPriority(500);
    }
    @Override
    protected void checkCondations(GraphicConditions c){
        if(!c.is2D()){
            c.set2D();
        }
    }
}