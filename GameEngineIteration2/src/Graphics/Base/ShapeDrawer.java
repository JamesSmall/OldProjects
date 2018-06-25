/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Graphics.Base;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.GL11;

/**
 *
 * @author Allazham
 */
class ShapeDrawer {
    private static final double TWOPI = (Math.PI*2);
    private static final double HALFPI = Math.PI/2;
    private static final double QUARTERPI = Math.PI/4;
    private ShapeDrawer(){
        
    }
    static void drawTextuedCircle(float x,float y, float width,float height,int VerticesCount,double texBeginX,double texBeginY,double texEndX,double texEndY)  throws LWJGLException{
        int i;
        width /= 2;
        height /= 2;
        double[] texCenter = new double[]{(texBeginX+texEndX)/2, (texBeginY+texEndY)/2};
        double[] texDistance = new double[]{(texEndX-texBeginX)/2,(texEndY-texBeginY)/2};
        double angle = 0;
        double textureCordsX = Math.cos(angle)*texDistance[0]+texCenter[0], textureCordsY = Math.sin(angle)*texDistance[1]+texCenter[1];
        float DrawCordsX = (float)Math.cos(angle)*width+x, DrawCordsY = (float)Math.sin(angle)*-height+y;
        for(i = 0; i <= VerticesCount; i++){ //NUM_PIZZA_SLICES decides how round the circle looks.
            GL11.glBegin(GL11.GL_TRIANGLES);
            
                GL11.glTexCoord2d(texCenter[0], texCenter[1]);
                GL11.glVertex2f(x, y);
                
                GL11.glTexCoord2d(textureCordsX,textureCordsY);
                GL11.glVertex2f(DrawCordsX, DrawCordsY);
                
                //new location math
                angle = ((double)i+1)/((double)VerticesCount) * TWOPI;
                textureCordsX = Math.cos(angle)*texDistance[0]+texCenter[0];
                textureCordsY = Math.sin(angle)*texDistance[1]+texCenter[1];
                DrawCordsX = (float)Math.cos(angle)*width+x;
                DrawCordsY = (float)Math.sin(angle)*-height+y;
                
                GL11.glTexCoord2d(textureCordsX,textureCordsY);
                GL11.glVertex2f(DrawCordsX, DrawCordsY); 
                
            GL11.glEnd();
        }
    }
    static void drawCircle(float x,float y, float width,float height,int VerticesCount) throws LWJGLException{
        int i;
        double angle = 0;
        width /= 2;
        height /= 2;
        float DrawCordsX = (float)Math.cos(angle)*width+x, DrawCordsY = (float)Math.sin(angle)*height+y;
        for(i = 0; i <= VerticesCount; i++){ //NUM_PIZZA_SLICES decides how round the circle looks.
            GL11.glBegin(GL11.GL_TRIANGLES);
            
                GL11.glVertex2f(x, y);
                
                GL11.glVertex2f(DrawCordsX, DrawCordsY);
                
                //new location math
                angle = ((double)i+1)/((double)VerticesCount) * TWOPI;
                DrawCordsX = (float)Math.cos(angle)*width+x;
                DrawCordsY = (float)Math.sin(angle)*height+y;
                
                GL11.glVertex2f(DrawCordsX, DrawCordsY); 
                
            GL11.glEnd();
        }
    }
    
    static void drawBasicSquare(float x, float y, float Width, float Height) throws LWJGLException{
        GL11.glPushMatrix();
        GL11.glTranslatef(x-Width/2, y-Height/2, 0);
        GL11.glBegin(GL11.GL_QUADS);
            GL11.glVertex2f(0, Height);
            GL11.glVertex2f(Width, Height);
            GL11.glVertex2f(Width, 0);
            GL11.glVertex2f(0, 0);
        GL11.glEnd();
        GL11.glPopMatrix();
    }
    /*
    static void drawBasicSquare(float x, float y, float Width, float Height) throws LWJGLException{
        GL11.glBegin(GL11.GL_QUADS);
            Width /= 2;
            Height /= 2;
            GL11.glVertex2f(x-Width, y+Height);
            GL11.glVertex2f(x+Width, y+Height);
            GL11.glVertex2f(x+Width, y-Height);
            GL11.glVertex2f(x-Width, y-Height);
        GL11.glEnd();
    }
    */
    static void drawBasicTexturedSquare(float x, float y, float Width, float Height,double texBeginX,double texBeginY,double texEndX,double texEndY) throws LWJGLException{
        GL11.glPushMatrix();
        GL11.glTranslatef(x-Width/2, y-Height/2, 0);
        GL11.glBegin(GL11.GL_TRIANGLES);
            GL11.glTexCoord2d(texBeginX, texBeginY);
            GL11.glVertex2f(0, Height);
            
            GL11.glTexCoord2d(texEndX, texBeginY);
            GL11.glVertex2f(Width, Height);
            
            GL11.glTexCoord2d(texEndX, texEndY);
            GL11.glVertex2f(Width, 0);
            
            //second triangle
            GL11.glTexCoord2d(texEndX, texEndY);
            GL11.glVertex2f(Width, 0);
            
            GL11.glTexCoord2d(texBeginX, texEndY);
            GL11.glVertex2f(0, 0);
            
            GL11.glTexCoord2d(texBeginX, texBeginY);
            GL11.glVertex2f(0, Height);
            
        GL11.glEnd();
        GL11.glPopMatrix();
        //4056.
    }
    /*
     static void drawBasicTexturedSquare(float x, float y, float Width, float Height,double texBeginX,double texBeginY,double texEndX,double texEndY) throws LWJGLException{
        GL11.glBegin(GL11.GL_QUADS);
            Width /= 2;
            Height /= 2;
            GL11.glTexCoord2d(texBeginX, texBeginY);
            GL11.glVertex2f(x-Width, y+Height);
            
            GL11.glTexCoord2d(texEndX, texBeginY);
            GL11.glVertex2f(x+Width, y+Height);
            
            GL11.glTexCoord2d(texEndX, texEndY);
            GL11.glVertex2f(x+Width, y-Height);
            
            GL11.glTexCoord2d(texBeginX, texEndY);
            GL11.glVertex2f(x-Width, y-Height);
        GL11.glEnd();
    }
    */
    static void drawAngledSquare(float x, float y, float Width, float Height,double angle) throws LWJGLException{
        GL11.glBegin(GL11.GL_QUADS);
            Width /= 2;
            Height /= 2;
            angle -= QUARTERPI;
            GL11.glVertex2f(x- (float) (Width*Math.cos(angle)),y-(float)(Height*Math.sin(angle)));
            
            angle -= HALFPI;
            GL11.glVertex2f(x- (float) (Width*Math.cos(angle)),y-(float)(Height*Math.sin(angle)));
            
            angle -= HALFPI;
            GL11.glVertex2f(x- (float) (Width*Math.cos(angle)),y-(float)(Height*Math.sin(angle)));
            
            angle -= HALFPI;
            GL11.glVertex2f(x- (float) (Width*Math.cos(angle)),y-(float)(Height*Math.sin(angle)));
        GL11.glEnd();
    }
    static void drawTexturedAngledSquare(float x, float y, float Width, float Height,double angle,double texBeginX,double texBeginY,double texEndX,double texEndY) throws LWJGLException{
        GL11.glBegin(GL11.GL_QUADS);
            Width /= 2;
            Height /= 2;
            angle -= QUARTERPI;
            GL11.glTexCoord2d(texBeginX, texBeginY);
            GL11.glVertex2f(x- (float) (Width*Math.cos(angle)),y-(float)(Height*Math.sin(angle)));
            
            angle -= HALFPI;
            GL11.glTexCoord2d(texEndX, texBeginY);
            GL11.glVertex2f(x- (float) (Width*Math.cos(angle)),y-(float)(Height*Math.sin(angle)));
            
            angle -= HALFPI;
            GL11.glTexCoord2d(texEndX, texEndY);
            GL11.glVertex2f(x- (float) (Width*Math.cos(angle)),y-(float)(Height*Math.sin(angle)));
            
            angle -= HALFPI;
            GL11.glTexCoord2d(texBeginX, texEndY);
            GL11.glVertex2f(x- (float) (Width*Math.cos(angle)),y-(float)(Height*Math.sin(angle)));
        GL11.glEnd();
    }
    static void drawOffKilterSquare(float x, float y, float Width, float Height) throws LWJGLException{
        GL11.glBegin(GL11.GL_QUADS);
            Width /= 2;
            Height /= 2;
            GL11.glVertex2f(x, y+Height);
            GL11.glVertex2f(x+Width, y+Height);
            GL11.glVertex2f(x+Width, y);
            GL11.glVertex2f(x, y);
        GL11.glEnd();
    }
    static void drawOffKilterTexturedSquare(float x, float y, float Width, float Height,double texBeginX,double texBeginY,double texEndX,double texEndY) throws LWJGLException{
        GL11.glBegin(GL11.GL_QUADS);
            GL11.glTexCoord2d(texBeginX, texBeginY);
            GL11.glVertex2f(x, y+Height);
            
            GL11.glTexCoord2d(texEndX, texBeginY);
            GL11.glVertex2f(x+Width, y+Height);
            
            GL11.glTexCoord2d(texEndX, texEndY);
            GL11.glVertex2f(x+Width, y);
            
            GL11.glTexCoord2d(texBeginX, texEndY);
            GL11.glVertex2f(x, y);
        GL11.glEnd();
    }
}
