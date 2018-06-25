/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Graphics.Base.Graphic;

import Graphics.Base.ScreenGraphic;
import Holder.Texture;
import org.lwjgl.LWJGLException;

/**
 *
 * @author Allazham
 */
class BasicParticle extends Particle{
    float x, y,width,height;
    float xV,yV;
    double angle;
    Texture tex;
    BasicParticle(float x, float y,float velX,float velY, float width,float height,double angle,int updates,Texture t){
        super(updates);
        this.x = x;
        this.y = y;
        this.xV = velX;
        this.yV = velY;
        this.width = width;
        this.height = height;
        this.angle = angle;
    }
    @Override
    void render(boolean update) throws LWJGLException{
        ScreenGraphic.DrawAngledTexturedSquare(x, y, width, height, angle,this.tex.getLesserX(),this.tex.getLesserY(),this.tex.getGreaterX(),this.tex.getGreaterY());
        if(update){
            this.x += this.xV;
            this.y += this.yV;
        }
    }
}
