/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Graphics.Base.Graphic;

import static Graphics.Base.ScreenGraphic.ConvertWorldToScreenX;
import static Graphics.Base.ScreenGraphic.ConvertWorldToScreenY;
import Holder.Texture;
import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.GL11;

/**
 *
 * @author Allazham
 */
class TileOverlay extends Particle{
    Texture image;
    int x, y;
    TileOverlay(Texture tex,int x, int y, int time){
        super(time);
        image = tex;
        this.x = x;
        this.y = y;
    }
    @Override
    void render(boolean update) throws LWJGLException {
        boolean go = true;
        if(super.Updates == -2){
            super.Updates = 0;
        }
        else if(super.Updates == 0){
            go = false;
        }
        if(go){
            float localXLesser = ConvertWorldToScreenX(x),localXGreater = ConvertWorldToScreenX(x+1),localYLesser = ConvertWorldToScreenY(y),localYGreater = ConvertWorldToScreenY(y+1);
            this.image.bind();
            GL11.glBegin(GL11.GL_QUADS);
            GL11.glTexCoord2d(image.getLesserX(),image.getLesserY());
            GL11.glVertex2f(localXLesser,localYLesser);
        
            GL11.glTexCoord2d(image.getGreaterX(),image.getLesserY());
            GL11.glVertex2f(localXGreater,localYLesser);
        
            GL11.glTexCoord2d(image.getGreaterX(),image.getGreaterY());
            GL11.glVertex2f(localXGreater,localYGreater);
        
            GL11.glTexCoord2d(image.getLesserX(),image.getGreaterY());
            GL11.glVertex2f(localXLesser,localYGreater);
            GL11.glEnd();
        }
    }
    
}
