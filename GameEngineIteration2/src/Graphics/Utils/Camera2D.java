/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Graphics.Utils;

import Graphics.Forum.GraphicConditions;
import org.lwjgl.input.Mouse;
import org.lwjgl.util.vector.Vector2f;

/**
 *
 * @author Allazham
 */
public final class Camera2D {
    private boolean WorldRecent = false,GraphicRecent = false;
    private Vector2f lastGraphic,lastWorld;
    private boolean CameraLinked = false;
    private Vector2f NextCamrea = new Vector2f();
    private static final int WORLD_TO_PIXEL = 32;
    private final Vector2f Camera = new Vector2f();
    private float WorldScale = 1.5f;
    private GraphicConditions gc;
    private Camera2D(){
    }
    public void setup(GraphicConditions gc){
        this.gc = gc;
    }
    public void bind(){
        this.Camera.x = this.NextCamrea.x;
        this.Camera.y = this.NextCamrea.y;
        this.WorldRecent = false;
        this.GraphicRecent = false;
    }
    public void LinkCameraToVector2f(Vector2f loc){
        this.NextCamrea = loc;
        this.CameraLinked = true;
    }
    public void UnlinkCamera(){
        if(this.CameraLinked){
            this.NextCamrea = new Vector2f(Camera.x,Camera.y);
            this.CameraLinked = false;
        }
    }
    public void setLocation(Vector2f loc){
        if(this.CameraLinked){
            this.NextCamrea = new Vector2f(Camera.x,Camera.y);
            this.CameraLinked = false;
        }
        else{
            this.NextCamrea.x = loc.x;
            this.NextCamrea.y = loc.y;
        }
    }
    public void setLocation(float x, float y){
        if(this.CameraLinked){
            this.NextCamrea = new Vector2f(Camera.x,Camera.y);
            this.CameraLinked = false;
        }
        else{
            this.NextCamrea.x = x;
            this.NextCamrea.y = y;
        }
    }
    public void setLocationX(float x){
        if(this.CameraLinked){
            this.NextCamrea = new Vector2f(Camera.x,Camera.y);
            this.CameraLinked = false;
        }
        else{
            this.NextCamrea.x = x;
        }
    }
    public void setLocationY(float y){
        if(this.CameraLinked){
            this.NextCamrea = new Vector2f(Camera.x,Camera.y);
            this.CameraLinked = false;
        }
        else{
            this.NextCamrea.y = y;
        }
    }
    public void setScale(float scale){
        this.WorldScale = scale;
    }
    public Vector2f getCameraVector(){
        return this.Camera;
    }
    public float[] getCameraValues(){
        return new float[]{this.Camera.x,this.Camera.y};
    }
    public float getCameraX(){
        return this.Camera.x;
    }
    public float getCameraY(){
        return this.Camera.y;
    }
    public float getScreenWidth(){
        return gc.getScreenWidth();
    }
    public float getScreenHeight(){
        return gc.getScreenHeight();
    }
    public float getScale(){
        return this.WorldScale;
    }
    public float getInternalScreenX(){
        return this.gc.getInternalScreenX();
    }
    public float getInternalScreenY(){
        return this.gc.getInternalScreenY();
    }
    public int[] getInternalScreen(){
        return this.gc.getInternalScreen();
    }
    public Vector2f getMousePointOnGraphics(){
        if(this.GraphicRecent){
            return new Vector2f(this.lastGraphic);
        }
        Vector2f loc = new Vector2f(Mouse.getX(),Mouse.getY());
        // GL11.glOrtho(0, 800, 0, 800, 1,-1)
        //return new float[]{(400+((x-cam.getCameraX())*WORLD_TO_PIXEL*cam.getScale())),400+((y-cam.getCameraY())*WORLD_TO_PIXEL*cam.getScale())*(cam.getScreenWidth()/cam.getScreenHeight())*(cam.getScreenWidth()/cam.getScreenHeight())};
        loc.x = (loc.x/(float)gc.getScreenWidth())*gc.getInternalScreenX();
        loc.y = (loc.y/(float)gc.getScreenHeight())*gc.getInternalScreenY();//*((float)gc.getScreenWidth()/(float)gc.getScreenHeight());
        this.GraphicRecent = true;
        this.lastGraphic = loc;
        return new Vector2f(loc);
    }
    public Vector2f getMousePointInWorld(){
        if(this.WorldRecent){
            return new Vector2f(this.lastWorld);
        }
        Vector2f loc = new Vector2f(Mouse.getX(),Mouse.getY());
        loc.x /= gc.getScreenWidth();
        loc.y /= gc.getScreenHeight();
        loc.x -= .5f;
        loc.y -= .5f;
        loc.x *= (1/this.WorldScale)/WORLD_TO_PIXEL*800f*((float)gc.getScreenWidth()/(float)gc.getScreenHeight());
        loc.y *= (1/this.WorldScale)/WORLD_TO_PIXEL*800f;
        loc.x += this.getCameraX();
        loc.y += this.getCameraY();
        //cam.getInternalScreenX()/2+((x-cam.getCameraX())*WORLD_TO_PIXEL*cam.getScale()/(800f/cam.getInternalScreenX())))
//cam.getInternalScreenY()/2+((y-cam.getCameraY())*WORLD_TO_PIXEL*cam.getScale()/(800f/cam.getInternalScreenY()))*(cam.getScreenWidth()/cam.getScreenHeight())};
        return new Vector2f(loc);
    }
    
    public static Camera2D getCamera(){
        return CameraHolder.camera;
    }
    private static class CameraHolder{
        public static Camera2D camera = new Camera2D();
    }
}
