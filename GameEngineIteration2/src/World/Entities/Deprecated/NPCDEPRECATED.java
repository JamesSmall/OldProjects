/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package World.Entities.Deprecated;

import Graphics.Utils.Camera2D;
import Graphics.Base.ScreenGraphic;
import Holder.MasterLoad;
import Holder.Texture;
import Holder.TextureTypes.RollingHorizonalTexture;
import World.Entities.AbstractGraphicEntry;
import org.lwjgl.LWJGLException;
import org.lwjgl.Sys;
import org.lwjgl.util.vector.Vector2f;

/**
 *
 * @author Allazham
 */
public abstract class NPCDEPRECATED extends AbstractGraphicEntry{
    private float TimePast;
    private static final double TWOPI = Math.PI*2;
    public static final int STALL = 0;
    public static final int WALK = 1;
    public static final int RUN = 2;
    private static final double GROUND = 0;
    private int FootRender = 1;
    private double DirectionAngle = 0;
    private double FootDegree1 = 0,FootDegree2 = 0;
    private double CurrhandDegreeL = -.5d, CurrhandDegreeR = .5d;
    private double HandDegreeL = -.5d,HandDegreeR = .5d;
    private boolean HandAnimatingL = false,HandAnimatingR = false;
    //private boolean BodyAnimating = false;
    private float Size = 1f;
    private static final float WALKADD = .1f,RUNADD = .2f;
    private Vector2f GraphicLocation;
    private Texture handL,handR;
    private RollingHorizonalTexture Feet,Chest;
    public NPCDEPRECATED(){
        GraphicLocation = super.getGraphicalLocation();
        Feet = (RollingHorizonalTexture) MasterLoad.getImage("FootlessGear");
        Chest = (RollingHorizonalTexture) MasterLoad.getImage("ChestlessGear");
        handL = MasterLoad.getImage("HandlessGear");
        handR = MasterLoad.getImage("HandlessGear");
    }
    @Override
    protected void render() throws LWJGLException {
        Vector2f cam = Camera2D.getCamera().getMousePointInWorld();
        TimePast = Sys.getTime() - TimePast;
        if(TimePast > 1000){
            TimePast = -1;
        }
        
        this.DirectionAngle = Math.atan2(cam.y, -cam.x)+Math.PI*3/2;
        //make within resonable range
        int distConvert = (int) Math.floor(this.DirectionAngle/TWOPI);
        this.DirectionAngle = this.DirectionAngle - TWOPI*distConvert;
        distConvert = (int) Math.floor(this.FootDegree1/TWOPI);
        this.FootDegree1 = this.FootDegree1 - TWOPI*distConvert;
        distConvert = (int) Math.floor(this.FootDegree2/TWOPI);
        this.FootDegree2 = this.FootDegree2 - TWOPI*distConvert;

        
        if(!(Math.PI*3/2 > this.HandDegreeL+this.DirectionAngle &&this.HandDegreeL+this.DirectionAngle > Math.PI/2)){
            this.handL.bind();
            this.renderHandsIdleL();
        }
        if(!(Math.PI*3/2 > this.HandDegreeR+this.DirectionAngle && this.HandDegreeR+this.DirectionAngle > Math.PI/2)){
            this.handR.bind();
            this.renderHandsIdleR();
        }
        
        this.Chest.bind();
        ScreenGraphic.DrawTexturedCircle(this.GraphicLocation.x, this.GraphicLocation.y, Size*5, Size*5,30,this.Chest.getLesserX(),this.Chest.getLesserY(),this.Chest.getGreaterX(),this.Chest.getGreaterY());
        RenderFeet();
        
        if(Math.PI*3/2 > this.HandDegreeL+this.DirectionAngle &&this.HandDegreeL+this.DirectionAngle > Math.PI/2){
            this.handL.bind();
            this.renderHandsIdleL();
        }
        if(Math.PI*3/2 > this.HandDegreeR+this.DirectionAngle && this.HandDegreeR+this.DirectionAngle > Math.PI/2){
            this.handR.bind();
            this.renderHandsIdleR();
        }
        //FootRender(GraphicLocation,1f,.6f,angle2,distance,Feet);
        //FootRender(GraphicLocation,-1f,.6f,angle2,distance2,Feet);
    }
    private void RenderFeet() throws LWJGLException{
        final Vector2f loc = new Vector2f();
        loc.x = GraphicLocation.x;
        loc.y = GraphicLocation.y - Size*4;
        
        switch(this.FootRender){
            case STALL:
                RenderFeetIdle(loc);
                break;
            case WALK:
                RenderFeetWalk(loc);
                break;
            case RUN:
                RenderFeetRun(loc);
                break;
            default:
                RenderFeetIdle(loc);
        }
    }
    private void RenderFeetIdle(Vector2f loc) throws LWJGLException{
        if(FootDegree1 != GROUND){
            if((GROUND + WALKADD > this.FootDegree1 && this.FootDegree1 > GROUND - WALKADD)||(GROUND-WALKADD+TWOPI < this.FootDegree1&& 0 > GROUND-WALKADD)||(GROUND+WALKADD-TWOPI > this.FootDegree1)&& TWOPI > GROUND+WALKADD){
                this.FootDegree1 = GROUND;
            }
            else{
                this.FootDegree1 += WALKADD;
            }
        }
        if(FootDegree2 != GROUND){
            if((GROUND + WALKADD > this.FootDegree2 && this.FootDegree2 > GROUND - WALKADD)||(GROUND-WALKADD+TWOPI < this.FootDegree2&& 0 > GROUND-WALKADD)||(GROUND+WALKADD-TWOPI > this.FootDegree2)&& TWOPI > GROUND+WALKADD){
                this.FootDegree2 = GROUND;
            }
            else{
                this.FootDegree2 += WALKADD;
            }
        }
        this.Feet.setAngleRadians(this.DirectionAngle + Math.PI+1.6);
        FootRender(loc,1f*Size,this.DirectionAngle,this.FootDegree1,this.Feet);
        FootRender(loc,-1f*Size,this.DirectionAngle,this.FootDegree2,this.Feet);
    }
    private void RenderFeetWalk(Vector2f loc) throws LWJGLException{
        this.FootDegree1+= WALKADD;
        if(Math.abs(this.FootDegree1)  < Math.abs(this.FootDegree2) + Math.PI){
            this.FootDegree2 += WALKADD;
        }
        this.Feet.setAngleRadians(this.DirectionAngle + Math.PI+1.6);
        FootRender(loc,1f*Size,this.DirectionAngle,this.FootDegree1,this.Feet);
        FootRender(loc,-1f*Size,this.DirectionAngle,this.FootDegree2,this.Feet);
    }
    private void RenderFeetRun(Vector2f loc) throws LWJGLException{
        this.FootDegree1 += RUNADD;
        if(this.FootDegree1  < this.FootDegree2 + Math.PI){
            this.FootDegree2 += RUNADD;
        }
        this.Feet.setAngleRadians(this.DirectionAngle + Math.PI+1.6);
        FootRender(loc,1f*Size,this.DirectionAngle,this.FootDegree1,this.Feet);
        FootRender(loc,-1f*Size,this.DirectionAngle,this.FootDegree2,this.Feet);
    }
    public void FootRender(Vector2f center,float distance,double angle1,double angle2,RollingHorizonalTexture t) throws LWJGLException{
        float distance2 = distance*1;
        t.bind();
        ScreenGraphic.DrawTexturedCircle(center.x+(distance*(float)Math.cos(angle1))+(float)(distance2*Math.cos(angle2)*Math.sin(angle1+Math.PI)),center.y+(float)Math.sin(angle2)*distance2, this.Size,this.Size, 15,t.getLesserX(),t.getLesserY(),t.getGreaterX(),t.getGreaterY());
    }
    public void renderHandsIdleL() throws LWJGLException{
        this.RenderHand(this.GraphicLocation.x, this.GraphicLocation.y, Size,1, this.CurrhandDegreeL+this.DirectionAngle,this.handL, true);
    }
    public void renderHandsIdleR() throws LWJGLException{
        this.RenderHand(this.GraphicLocation.x, this.GraphicLocation.y, Size,1, this.CurrhandDegreeR+this.DirectionAngle,this.handR, false);
    }
    public void RenderHand(float x, float y, float size,float distance,double angle,Texture hand,boolean textureFlipped)throws LWJGLException{
        float xx = ((float)Math.sin(angle))*distance*5,yy = ((float)Math.cos(angle))*distance;
        if(textureFlipped){
            ScreenGraphic.DrawAngledTexturedSquare(xx, yy, size, size,-angle,hand.getGreaterX(),hand.getGreaterY(),hand.getLesserX(),hand.getLesserY());
        }
        else{
            ScreenGraphic.DrawAngledTexturedSquare(xx, yy, size, size,-angle,hand.getLesserX(),hand.getGreaterY(),hand.getGreaterX(),hand.getLesserY());
        }
    }
}
