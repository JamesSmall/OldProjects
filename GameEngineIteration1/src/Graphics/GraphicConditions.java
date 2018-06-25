/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Graphics;

import org.lwjgl.opengl.GL11;
import org.lwjgl.util.glu.GLU;

/**
 *
 * @author Allazham
 */
public class GraphicConditions {
    private boolean Centered = false;
    private float[] eye = new float[3];
    private float pitchAngle = 0,bearingAngle = 0,rollAngle = 0;
    boolean threeD = false,twoD = false,light = false;
    private int ScreenX = 800, ScreenY = 600;
    private int Screen3DX = 100,differenceY = 1,Depth = 100,AntiDepth = 1;
    private GraphicConditions(){
    }
    public void setInternal2DScreenWidth(int x){
        this.ScreenX = x;
    }
    public void setInternal2DScreenHeight(int y){
        this.ScreenY = y;
    }
    public void setInternal3DScreenWidth(int x){
        this.Screen3DX = x;
    }
    public void setInternal3DScreenHeightDifference(int y){
        this.differenceY = y;
    }
    public void setScreenDepth(int Depth){
        this.Depth = Depth;
    }
    public void setScreenAntiDepth(int AntiDepth){
        this.AntiDepth = AntiDepth;
    }
    public void addBearing(float bearing){
        this.bearingAngle += bearing;
        if(this.bearingAngle<0){
            this.bearingAngle+=360;
        }
        else if(this.bearingAngle>360){
            this.bearingAngle-=360;
        }
        Particle.setBearing((float)Math.toRadians(this.bearingAngle));
    }
    public void addPitch(float pitch){
        this.pitchAngle+=pitch;
        if(this.pitchAngle<0){
            this.pitchAngle+=360;
        }
        else if(this.pitchAngle> 360){
            this.pitchAngle-=360;
        }
        Particle.setPitch((float)Math.toRadians(this.pitchAngle));
    }
    public void addRoll(float Roll){
        this.rollAngle += Roll;
        if(this.rollAngle < 0){
            this.rollAngle += 360;
        }
        else if(this.rollAngle > 360){
            this.rollAngle -= 360;
        }
    }
    public void setBearing(float bearing){
        this.bearingAngle = bearing;
        Particle.setBearing((float)Math.toRadians(bearing));
    }
    public void setPitch(float pitch){
        this.pitchAngle=pitch;
        Particle.setPitch((float)Math.toRadians(pitch));
    }
    public void setRoll(float Roll){
        this.rollAngle = Roll;
    }
    public void AdjustCamera(float x,float y, float z){
        this.eye[0] += x;
        this.eye[1] += y;
        this.eye[2] += z;
    }
    public void setCamera(float x,float y, float z){
        this.eye[0] = x;
        this.eye[1] = y;
        this.eye[2] = z;
    }
    public float getCameraX(){
        return this.eye[0];
    }
    public float getCameraY(){
        return this.eye[1];
    }
    public float getCameraZ(){
        return this.eye[2];
    }
    public float[] getCamera(){
        return this.eye;
    }
    public float getBearing(){
        return this.bearingAngle;
    }
    public float getPitch(){
        return this.pitchAngle;
    }
    public float getRoll(){
        return this.rollAngle;
    }
    public float getCenterX(){
        return this.ScreenX/2;
    }
    public float getCenterY(){
        return this.ScreenY/2;
    }
    public float[] getCenter(){
        float[] ret = {this.ScreenX/2,this.ScreenY/2};
        return ret;
    }
    public int getInternal2DScreenWidth(){
        return this.ScreenX;
    }
    public int getInternal2DScreenHeight(){
        return this.ScreenY;
    }
    public int[] getInternal2DScreenHeightAndWidth(){
        int[] ret = {this.ScreenX,this.ScreenY};
        return ret;
    }
    protected void setCentered(boolean center){
        this.Centered = center;
        if(this.is2D()){
            this.AdjustScreen();
        }
    }
    protected boolean isCentered(){
        return this.Centered;
    }
    protected void AdjustScreen(){
        if(this.Centered){
            GL11.glOrtho(-this.ScreenX/2,this.ScreenX/2,-this.ScreenY/2,this.ScreenY/2,1, -1);
        }
        else{
            GL11.glOrtho(0,this.ScreenX,0,this.ScreenY,1, -1);
        }
    }
    protected void endOfRender(){
        this.threeD = false;
        this.twoD = false;
        GL11.glDisable(GL11.GL_BLEND);
        GL11.glPopMatrix();
        GL11.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
        GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);			// Clear The Screen And The Depth Buffer
    }
    protected void set3D(){
        this.threeD = true;
        this.twoD = false;
        //sets graphics setting to 3d
        GL11.glMatrixMode(GL11.GL_PROJECTION); 
        GL11.glLoadIdentity();
        GLU.gluPerspective(this.Screen3DX,this.differenceY,this.AntiDepth, this.Depth);
        GL11.glEnable(GL11.GL_DEPTH_TEST);
        GL11.glPushMatrix();
            //GL11.glTranslatef(this.eye[0],this.eye[1],this.eye[2]);
            GL11.glRotatef(this.pitchAngle,1,0,0);
            GL11.glRotatef(this.bearingAngle,0,1,0);
            GL11.glRotatef(this.rollAngle, 0,0,1);
        }
    protected boolean is3D(){
        return this.threeD;
    }
    protected void set2D(){
        GL11.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);          
         this.twoD = true;
        this.threeD = false;
        this.setLighted(false);    
        //2d objects
        //GL11.glDisable(GL11.GL_DEPTH_TEST);
        //GL11.glPopMatrix();
        GL11.glEnable(GL11.GL_TEXTURE_2D);               
        
		GL11.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);          
        
        	// enable alpha blending
        	GL11.glEnable(GL11.GL_BLEND);
        	GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        
        	//GL11.glViewport(0,0,width,height);
		GL11.glMatrixMode(GL11.GL_MODELVIEW);

		GL11.glMatrixMode(GL11.GL_PROJECTION);
		GL11.glLoadIdentity();
                this.AdjustScreen();
		GL11.glMatrixMode(GL11.GL_MODELVIEW);
        
        
        
        GL11.glEnable(GL11.GL_BLEND);
    }
    protected boolean is2D(){
        return this.twoD;
    }
    protected void setLighted(boolean light){
        this.light = light;
        if(this.light){
            //sets lighted
        }
        else{
            //sets non lighted
        }
    }
    protected boolean isLighted(){
        return this.light;
    }
    public static GraphicConditions getInstance(){
        return GraphicConditionsHolder.hold;
    }
    private static class GraphicConditionsHolder{
        public static GraphicConditions hold = new GraphicConditions();
    }
}
