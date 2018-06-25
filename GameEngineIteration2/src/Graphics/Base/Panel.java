/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Graphics.Base;

import Graphics.Forum.GraphicConditions;
import Graphics.Forum.GraphicsObject;
import org.lwjgl.LWJGLException;

/**
 *
 * @author Allazham
 */
public class Panel extends GraphicsObject{
    public static final int LEFT = 0, RIGHT = 1, UP = 2, DOWN = 3, UPLEFT = 4, UPRIGHT = 5,DOWNLEFT = 6, DOWNRIGHT = 7;

    public Panel(boolean disabled) {
        super(disabled);
    }

    @Override
    protected void checkCondations(GraphicConditions c) {
        if(!c.is2D()){
            c.set2D();
        }
    }

    @Override
    protected void render() throws LWJGLException {
        
    }

    @Override
    public void disposeGraphics() {
        
    }
    protected void renderWithin(){
        
    }
}
