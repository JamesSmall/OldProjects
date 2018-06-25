/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Graphics.Forum;

import Graphics.Utils.Camera2D;
import org.lwjgl.opengl.GL11;

/**
 *
 * @author Allazham
 */ 
public final class GraphicConditions {
    private double Light = 1;
    private double red =1,green = 1,blue = 1,alpha = 1;
    private final Camera2D cam;
    private boolean twoD = false;
    private int ScreenX = 800, ScreenY = 600;
    private int InternalScreenX = 8000,InternalScreenY = 8000;
    GraphicConditions(){
        cam = Camera2D.getCamera();
        cam.setup(this);
    }
    public void setScreenWidth(int x){
        this.ScreenX = x;
    }
    public void setScreenHeight(int y){
        this.ScreenY = y;
    }
    public float getCenterX(){
        return (float) (this.ScreenX/2);
    }
    public float getCenterY(){
        return (float) (this.ScreenY/2);
    }
    public float[] getCenter(){
        float[] ret = {(int)this.ScreenX/2,(int)this.ScreenY/2};
        return ret;
    }
    public int getScreenWidth(){
        return (int)this.ScreenX;
    }
    public int getScreenHeight(){
        return (int)this.ScreenY;
    }
    public void setInternalScreenX(int x){
        this.InternalScreenX = x;
    }
    public void setInternalScreenY(int y){
        this.InternalScreenY = y;
    }
    public int getInternalScreenX(){
        return this.InternalScreenX;
    }
    public int getInternalScreenY(){
        return this.InternalScreenY;
    }
    public int[] getInternalScreen(){
        return new int[]{this.InternalScreenX,this.InternalScreenY};
    }
    public int[] getScreenHeightAndWidth(){
        int[] ret = {(int)this.ScreenX,(int)this.ScreenY};
        return ret;
    }
    public void AdjustScreen(){
        //GL11.glOrtho(50,50,50,50,1,-1);
    }
    public void endOfRender(){
        this.twoD = false;
        GL11.glColor4d(1,1,1,1);
        GL11.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
        	
        GL11.glClearColor(0.0f,0.0f,0.0f, 0.0f); 
        GL11.glClearDepth(1.0f); 
        GL11.glClear(GL11.GL_COLOR_BUFFER_BIT|GL11.GL_DEPTH_BUFFER_BIT);
    }
    public void set2D(){  
        this.twoD = true; 
        //2d objects 
        	// enable alpha blending
                GL11.glMatrixMode(GL11.GL_PROJECTION);
                GL11.glDisable(GL11.GL_DEPTH_TEST);
                this.cam.bind();
                
                GL11.glLoadIdentity();
                GL11.glOrtho(0, InternalScreenX, 0,InternalScreenY, -1,1);
                
                GL11.glMatrixMode(GL11.GL_MODELVIEW);
        	
                GL11.glEnable(GL11.GL_BLEND);
                GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		
                
                //GL11.glEnable(GL11.GL_BLEND);
                //GL11.glMatrixMode( GL11.GL_MODELVIEW );
                //this.AdjustScreen();
    }
    public void setColorChangeActive(){
        
    }
    public void disableColorChange(){
        
    }
    public boolean is2D(){
        return this.twoD;
    }
    public Camera2D getCamera(){
        return this.cam;
    }
}
