/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Graphics.Base;

import Graphics.Utils.Camera2D;
import Graphics.Forum.GraphicConditions;
import Graphics.Forum.GraphicsObject;
import org.lwjgl.LWJGLException;
import org.lwjgl.util.vector.Vector2f;

/**
 *
 * @author Allazham
 */
public abstract class ScreenGraphic extends GraphicsObject{
    private static final float WORLD_TO_PIXEL = 32;
    private static final Camera2D cam = Camera2D.getCamera();
    public ScreenGraphic(boolean disabled){
        super(disabled);
        super.setPriority(101);
    }
    @Override
    protected void checkCondations(GraphicConditions c){
        if(!c.is2D()){
            c.set2D();
        }
    }
    public static Vector2f ConvertWorldToScreen(Vector2f p){
        return new Vector2f(ConvertWorldToScreenX(p.x),ConvertWorldToScreenY(p.y));
    }
    public static float[] ConvertWorldToScreen(float[] loc){
        return new float[]{ConvertWorldToScreenX(loc[0]),ConvertWorldToScreenY(loc[1])};
    }
    public static float[] ConvertWorldToScreen(float x, float y){
        return new float[]{ConvertWorldToScreenX(x),ConvertWorldToScreenY(y)};
    }
    public static float ConvertWorldToScreenX(float x){
        return cam.getInternalScreenX()/2f+((x-cam.getCameraX())*WORLD_TO_PIXEL*cam.getScale()/(800f/cam.getInternalScreenX())/(cam.getScreenWidth()/cam.getScreenHeight()));
    }
    public static float ConvertWorldToScreenY(float y){
        return cam.getInternalScreenY()/2f+((y-cam.getCameraY())*WORLD_TO_PIXEL*cam.getScale()/(800f/cam.getInternalScreenY()));
    }
    public static Vector2f ConvertWorldToScreenDistance(Vector2f p){
        return new Vector2f(ConvertWorldToScreenXDistance(p.x),ConvertWorldToScreenYDistance(p.y));
    }
    public static float[] ConvertWorldToScreenDistance(float[] loc){
        return new float[]{ConvertWorldToScreenXDistance(loc[0]),ConvertWorldToScreenYDistance(loc[1])};
    }
    public static float[] ConvertWorldToScreenDistance(float x, float y){
        return new float[]{ConvertWorldToScreenXDistance(x),ConvertWorldToScreenYDistance(y)};
    }
    public static float ConvertWorldToScreenXDistance(float x){
        return (x*WORLD_TO_PIXEL*cam.getScale()/(800f/cam.getInternalScreenX()))/(cam.getScreenWidth()/cam.getScreenHeight());
    }
    public static float ConvertWorldToScreenYDistance(float y){
        return (y*WORLD_TO_PIXEL*cam.getScale()/(800f/cam.getInternalScreenY()));
    }
    public static void DrawTexturedCircle(float x, float y, float width, float height,int vertices, double TexBeginX,double texbeginY,double texEndX,double texEndY) throws LWJGLException{
        float[] loc = ConvertWorldToScreen(x,y);
        float[] size = ConvertWorldToScreenDistance(width,height);
        ShapeDrawer.drawTextuedCircle(loc[0],loc[1], size[0],size[1], vertices, TexBeginX, texbeginY, texEndX, texEndY);
    }
    public static void DrawCircle(float x, float y, float width, float height,int vertices) throws LWJGLException{
        float[] loc = ConvertWorldToScreen(x,y);
        float[] size = ConvertWorldToScreenDistance(width,height);
        ShapeDrawer.drawCircle(loc[0],loc[1], size[0],size[1], vertices);
    }
    public static void DrawBasicSquare(float x, float y, float width,float height) throws LWJGLException{
        float[] loc = ConvertWorldToScreen(x,y);
        float[] size = ConvertWorldToScreenDistance(width,height);
        ShapeDrawer.drawBasicSquare(loc[0],loc[1],size[0],size[1]);
    }
    public static void DrawBasicTexturedSquare(float x, float y, float width,float height,double texLesserX,double texLesserY,double texGreaterX,double texGreaterY) throws LWJGLException{
        float[] loc = ConvertWorldToScreen(x,y);
        float[] size = ConvertWorldToScreenDistance(width,height);
        ShapeDrawer.drawBasicTexturedSquare(loc[0],loc[1],size[0],size[1], texLesserX, texLesserY, texGreaterX, texGreaterY);
    }
    public static void DrawoffKilterSquare(float x, float y, float width,float height) throws LWJGLException{
        float[] loc = ConvertWorldToScreen(x,y);
        float[] size = ConvertWorldToScreenDistance(width,height);
        ShapeDrawer.drawOffKilterSquare(loc[0],loc[1],size[0],size[1]);
    }
    public static void DrawOffKilterTexturedSquare(float x, float y, float width,float height,double texLesserX,double texLesserY,double texGreaterX,double texGreaterY) throws LWJGLException{
        float[] loc = ConvertWorldToScreen(x,y);
        float[] size = ConvertWorldToScreenDistance(width,height);
        ShapeDrawer.drawOffKilterTexturedSquare(loc[0],loc[1],size[0],size[1], texLesserX, texLesserY, texGreaterX, texGreaterY);
    }
    public static void DrawAngledSquare(float x, float y, float width,float height,double angle) throws LWJGLException{
        float[] loc = ConvertWorldToScreen(x,y);
        float[] size = ConvertWorldToScreenDistance(width,height);
        ShapeDrawer.drawAngledSquare(loc[0],loc[1],size[0],size[1],angle);
    }
    public static void DrawAngledTexturedSquare(float x, float y, float width,float height,double angle,double texLesserX,double texLesserY,double texGreaterX,double texGreaterY) throws LWJGLException{
        float[] loc = ConvertWorldToScreen(x,y);
        float[] size = ConvertWorldToScreenDistance(width,height);
        ShapeDrawer.drawTexturedAngledSquare(loc[0],loc[1],size[0],size[1],angle,texLesserX,texLesserY,texGreaterX,texGreaterY);
    }
}
