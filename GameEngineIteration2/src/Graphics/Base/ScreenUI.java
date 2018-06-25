/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Graphics.Base;

import Graphics.Utils.Camera2D;
import Graphics.Forum.GraphicConditions;
import Graphics.Forum.GraphicsObject;
import Graphics.UserActions.UIZone;
import Graphics.UserActions.UIZoneConnector;
import Holder.Texture;
import Holder.TextureTypes.SingleColorTexture;
import java.awt.Color;
import org.lwjgl.LWJGLException;
import org.lwjgl.util.vector.Vector2f;


/**
 * Last Edited:3/2/2013
 * @author james small
 */
public class ScreenUI extends GraphicsObject implements UIZoneConnector,UIRedirect{
    private static float Size = 1f;
    private static Camera2D cam = Camera2D.getCamera();
    public static final int CENTER = 0,LEFT = 1,UPLEFT =2,UP = 3,UPRIGHT=4,RIGHT=5,DOWNRIGHT=6,DOWN=7,DOWNLEFT=8;
    private UIManager uir;
    private int Orintation = CENTER;
    private float x=0,y=0,width=0,height = 0;
    private Texture tex = SingleColorTexture.getColoredTexture(Color.red);
    private final UIZone ui;
    public ScreenUI(boolean disabled){
        super(disabled);
        super.setPriority(200);
        this.ui = new UIZone(this);
    }
    public ScreenUI(float x, float y, float width, float height, int orintation,boolean disabled){
        super(disabled);
        super.setPriority(200);
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.Orintation = orintation;
        this.ui = new UIZone(this);
    }
    public ScreenUI(float x, float y, float width, float height, int orintation,Texture t,boolean disabled){
        super(disabled);
        super.setPriority(200);
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.tex = t;
        this.Orintation = orintation;
        this.ui = new UIZone(this);
    }
    public void setOrintation(int ort){
        if(!ScreenUI.validOrintation(ort)){
            throw new UnsupportedOperationException("orintation does not match presets");
        }
        if(this.uir != null){
            this.uir.setOrientation(ort);
        }
        this.Orintation = ort;
    }
    public int getOrintation(){
        if(this.uir != null){
            return this.uir.getOrientation();
        }
        return this.Orintation;
    }
    @Override
    public void setUIManager(UIManager ui){
        this.uir = ui;
    }
    @Override
    public UIManager getUIManager(){
        return this.uir;
    }
    @Override
    public void setX(float x){
        if(uir != null){
            this.uir.setX(x);
            return;
        }
        this.x = x;
    }
    @Override
    public void setY(float y){
        if(this.uir != null){
            uir.setY(y);
            return;
        }
        
        this.y = y;
    }
    @Override
    public float getX(){
        if(this.uir != null){
            return uir.getX();
        }
        return this.x;
    }
    @Override
    public float getY(){
        if(this.uir != null){
            return uir.getY();
        }
        return this.y;
    }
    @Override
    public void setWidth(float width){
        if(this.uir != null){
            this.uir.setWidth(width);
            return;
        }
        this.width = width;
    }
    @Override
    public void setHeight(float height){
        if(this.uir != null){
            this.uir.setHeight(height);
            return;
        }
        this.height = height;
    }
    @Override
    public float getWidth(){
        if(this.uir != null){
            return uir.getWidth();
        }
        return this.width;
    }
    @Override
    public float getHeight(){
        if(this.uir != null){
            return uir.getHeight();
        }
        return this.height;
    }
    protected float getActualX(){
        return this.x;
    }
    protected float getActualY(){
        return this.y;
    }
    protected float getActualWidth(){
        return this.width;
    }
    protected float getActualHeight(){
        return this.height;
    }
    protected int getActaulOrientation(){
        return this.Orintation;
    }
    @Override
    public void ProcessChanges(UIManager ui,float x, float y, float width, float height,int Orinentation){
        if(ui == null || this.uir == null){
            throw new NullPointerException("UI is null");
        }
        else if(ui != this.uir){
            throw new UnsupportedOperationException("UIRedirect passes is suppose to be the same as the one stored");
        }
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.Orintation = Orinentation;
    }
    public static boolean validOrintation(int ort){
        return ort == LEFT || ort == UPLEFT || ort == UP || ort == UPRIGHT || ort == RIGHT || DOWNRIGHT == ort || DOWN == ort || DOWNLEFT == ort || ort == CENTER;
    }
    public static void setSize(float size){
        Size = size;
    }
    public static float getSize(){
        return Size;
    }
    @Override
    protected void checkCondations(GraphicConditions c){
        if(!c.is2D()){
            c.set2D();
        }
    }
    public static Vector2f ConvertWorldToScreen(Vector2f p){
        return new Vector2f(p.x*cam.getInternalScreenX(),p.y*cam.getInternalScreenY());
    }
    public static float ConvertWorldToScreenX(float x, int choose){
        switch(choose){
            case DOWNLEFT:
            case UPLEFT:
            case LEFT:
                return x*cam.getInternalScreenX()*((float)cam.getScreenHeight()/(float)cam.getScreenWidth());
            case UPRIGHT:
            case DOWNRIGHT:
            case RIGHT:
                return cam.getInternalScreenX()-(cam.getInternalScreenX()*x*((float)cam.getScreenHeight()/(float)cam.getScreenWidth()));
            case UP:
            case DOWN:
            case CENTER:
                return cam.getInternalScreenX()*(x*((float)cam.getScreenHeight()/(float)cam.getScreenWidth())+.5f);
            default:
                throw new UnsupportedOperationException("not a reconized input");
        }
    }
    public static float ConvertWorldToScreenY(float y, int choose){
        switch(choose){
            case UPLEFT:
            case UPRIGHT:
            case UP:
                return cam.getInternalScreenY()-y*cam.getInternalScreenY();
            case DOWNLEFT:
            case DOWNRIGHT:
            case DOWN:
                return y*cam.getInternalScreenY();
            case LEFT:
            case RIGHT:
            case CENTER:
                return cam.getInternalScreenY()*(y+.5f);
            default:
                throw new UnsupportedOperationException("not a reconized input");
        }
    }
    public static float[] ConvertWorldToScreen(float x, float y,int choose){
        return new float[]{ConvertWorldToScreenX(x,choose),ConvertWorldToScreenY(y,choose)};
    }
    public static float[] ConvertWorldToScreen(float[] loc,int choose){
        return new float[]{ConvertWorldToScreenX(loc[0],choose),ConvertWorldToScreenY(loc[1],choose)};
    }
    public static Vector2f ConvertWorldToScreen(Vector2f v,int choose){
        return new Vector2f(ConvertWorldToScreenX(v.x,choose),ConvertWorldToScreenY(v.y,choose));
    }
    public static float ConvertWorldToScreenXDistance(float x){
        return x*cam.getInternalScreenX()*((float)cam.getScreenHeight()/(float)cam.getScreenWidth());
    }
    public static float ConvertWorldToScreenYDistance(float y){
        return y*cam.getInternalScreenY();
    }
    public static float[] ConvertWorldToScreenDistance(float x, float y){
        return new float[]{ConvertWorldToScreenXDistance(x),ConvertWorldToScreenYDistance(y)};
    }
    public static float[] ConvertWorldToScreenDistance(float[] loc){
        return new float[]{ConvertWorldToScreenXDistance(loc[0]),ConvertWorldToScreenYDistance(loc[1])};
    }
    public static Vector2f ConvertWorldToScreenDistance(Vector2f v,int choose){
        return new Vector2f(ConvertWorldToScreenXDistance(v.x),ConvertWorldToScreenYDistance(v.y));
    }
    public Vector2f[] getContrationPoints(float x, float y, float width, float height,int choose){
        width*=Size;
        height*= Size;
        if(choose != CENTER && choose != DOWN &&choose != UP){
            x += width/2;
        }
        if(choose != CENTER && choose != LEFT && choose != RIGHT){
            y += height/2;
        }
        float[] loc = ConvertWorldToScreen(x,y,choose);
        float[] size = ConvertWorldToScreenDistance(width,height);
        return new Vector2f[]{new Vector2f(loc[0]-size[0],loc[1]-size[1]),new Vector2f(loc[0]+size[0],loc[1]+size[1])};
    }
    public UIZone getUIZone(){
        return this.ui;
    }
    @Override
    public void Update(UIZone u){
        float widthTest = this.width,heightTest = this.height,xTest = this.x,yTest = this.y;
        widthTest*=Size;
        heightTest*= Size;
        if(this.isDisabled()){
            Vector2f[] v = new Vector2f[]{new Vector2f(new Vector2f(0,0))};
            u.setZonePoints(v);
        }
        else{
            if(Orintation != CENTER && Orintation != DOWN &&Orintation != UP){
                xTest += widthTest/2;
            }
            if(Orintation != CENTER && Orintation != LEFT && Orintation != RIGHT){
                yTest += heightTest/2;
            }
            float[] loc = ConvertWorldToScreen(xTest,yTest,Orintation);
            float[] dist = ConvertWorldToScreenDistance(widthTest/2,heightTest/2);
            Vector2f[] v = new Vector2f[]{new Vector2f(loc[0]-dist[0],loc[1]-dist[1]),new Vector2f(loc[0]+dist[0],loc[1]-dist[1]),new Vector2f(loc[0]+dist[0],loc[1]+dist[1]),new Vector2f(loc[0]-dist[0],loc[1]+dist[1])};
            u.setZonePoints(v);
        }
    }
    public void setTexture(Texture t){
        this.tex = t;
    }
    public Texture getTexture(){
        return this.tex;
    }
    @Override
    public void disposeGraphics(){
        this.ui.setUIZoneConnector(null);
        this.ui.setZonePoints(null);
        if(this.tex != null){
            this.tex.dispose();
        }
    }
    @Override
    public void render() throws LWJGLException{
        this.tex.bind();
        ScreenUI.DrawBasicTexturedSquare(x, y, width, height,this.tex.getLesserX(),this.tex.getLesserY(),this.tex.getGreaterX(),this.tex.getGreaterY(),this.Orintation);
    }
    public static void DrawTexturedCircle(float x, float y, float width, float height,int vertices, double TexBeginX,double texbeginY,double texEndX,double texEndY,int choose) throws LWJGLException{
        width*=Size;
        height*=Size;
        if(choose != CENTER && choose != DOWN &&choose != UP){
            x += width/2;
        }
        if(choose != CENTER && choose != LEFT && choose != RIGHT){
            y += height/2;
        }
        float[] loc = ConvertWorldToScreen(x,y,choose);
        float[] size = ConvertWorldToScreenDistance(width,height);
        ShapeDrawer.drawTextuedCircle(loc[0],loc[1], size[0],size[1], vertices, TexBeginX, texbeginY, texEndX, texEndY);
    }
    public static void DrawBasicSquare(float x, float y, float width,float height,int choose) throws LWJGLException{
        width*=Size;
        height*= Size;
        if(choose != CENTER && choose != DOWN &&choose != UP){
            x += width/2;
        }
        if(choose != CENTER && choose != LEFT && choose != RIGHT){
            y += height/2;
        }
        float[] loc = ConvertWorldToScreen(x,y,choose);
        float[] size = ConvertWorldToScreenDistance(width,height);
         ShapeDrawer.drawBasicSquare(loc[0],loc[1],size[0],size[1]);
    }
    public static void DrawCircle(float x, float y, float width, float height,int vertices,int choose) throws LWJGLException{
        width*=Size;
        height*= Size;
        if(choose != CENTER && choose != DOWN &&choose != UP){
            x += width/2;
        }
        if(choose != CENTER && choose != LEFT && choose != RIGHT){
            y += height/2;
        }
        float[] loc = ConvertWorldToScreen(x,y,choose);
        float[] size = ConvertWorldToScreenDistance(width,height);
        ShapeDrawer.drawCircle(loc[0],loc[1], size[0],size[1], vertices);
    }
    public static void DrawBasicTexturedSquare(float x, float y, float width,float height,double texLesserX,double texLesserY,double texGreaterX,double texGreaterY,int choose) throws LWJGLException{
        width*=Size;
        height*= Size;
        if(choose != CENTER && choose != DOWN &&choose != UP){
            x += width/2;
        }
        if(choose != CENTER && choose != LEFT && choose != RIGHT){
            y += height/2;
        }
        float[] loc = ConvertWorldToScreen(x,y,choose);
        float[] size = ConvertWorldToScreenDistance(width,height);
        ShapeDrawer.drawBasicTexturedSquare(loc[0],loc[1],size[0],size[1], texLesserX, texLesserY, texGreaterX, texGreaterY);
    }
    public static void DrawoffKilterSquare(float x, float y, float width,float height,int choose) throws LWJGLException{
        width*=Size;
        height*= Size;
        if(choose != CENTER && choose != DOWN &&choose != UP){
            x += width/2;
        }
        if(choose != CENTER && choose != LEFT && choose != RIGHT){
            y += height/2;
        }
        float[] loc = ConvertWorldToScreen(x,y,choose);
        float[] size = ConvertWorldToScreenDistance(width,height);
        ShapeDrawer.drawOffKilterSquare(loc[0],loc[1],size[0],size[1]);
    }
    public static void DrawOffKilterTexturedSquare(float x, float y, float width,float height,double texLesserX,double texLesserY,double texGreaterX,double texGreaterY,int choose) throws LWJGLException{
        width*=Size;
        height*= Size;
        if(choose != CENTER && choose != DOWN &&choose != UP){
            x += width/2;
        }
        if(choose != CENTER && choose != LEFT && choose != RIGHT){
            y += height/2;
        }
        float[] loc = ConvertWorldToScreen(x,y,choose);
        float[] size = ConvertWorldToScreenDistance(width,height);
        ShapeDrawer.drawOffKilterTexturedSquare(loc[0],loc[1],size[0],size[1], texLesserX, texLesserY, texGreaterX, texGreaterY);
    }
    public static void DrawAngledSquare(float x, float y, float width,float height,double angle,int choose) throws LWJGLException{
        width*=Size;
        height*= Size;
        if(choose != CENTER && choose != DOWN &&choose != UP){
            x += width/2;
        }
        if(choose != CENTER && choose != LEFT && choose != RIGHT){
            y += height/2;
        }
        float[] loc = ConvertWorldToScreen(x,y,choose);
        float[] size = ConvertWorldToScreenDistance(width,height);
        ShapeDrawer.drawAngledSquare(loc[0],loc[1],size[0],size[1],angle);
    }
    public static void DrawAngledTexturedSquare(float x, float y, float width,float height,double angle,double texLesserX,double texLesserY,double texGreaterX,double texGreaterY,int choose) throws LWJGLException{
        width*=Size;
        height*= Size;
        if(choose != CENTER && choose != DOWN &&choose != UP){
            x += width/2;
        }
        if(choose != CENTER && choose != LEFT && choose != RIGHT){
            y += height/2;
        }
        float[] loc = ConvertWorldToScreen(x,y,choose);
        float[] size = ConvertWorldToScreenDistance(width,height);
        ShapeDrawer.drawTexturedAngledSquare(loc[0],loc[1],size[0],size[1],angle,texLesserX,texLesserY,texGreaterX,texGreaterY);
    }
}
