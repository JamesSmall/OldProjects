/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Graphics.Base.UI;

import Graphics.Base.ScreenUI;
import Graphics.Base.UIManager;
import Graphics.Forum.ActionKeys;
import Graphics.UserActions.UIZone;
import Graphics.UserActions.UserInterfaceAction;
import Graphics.Utils.Camera2D;
import Holder.Texture;
import org.lwjgl.input.Mouse;
import org.lwjgl.util.vector.Vector2f;

/**
 *
 * @author Allazham
 */
public class ScrollBar extends Panel{
    private final ScreenUI Tile;
    private static Camera2D cam = Camera2D.getCamera();
    private int mouseB;
    private UserInterfaceAction uia;
    private boolean horizonal;
    private double barSize= 1,totalSize = 12;
    private double greater = 1, lesser = 0;
    public ScrollBar(boolean disabled,boolean horizonal){
        super(disabled);
        this.horizonal = horizonal;
        this.Tile = new ScreenUI(0,0,1,1,CENTER,false);
        super.addRenderObject(Tile);
        this.setup();
    }
    public ScrollBar(float x, float y, float width, float height, int orintation,boolean disabled,boolean horizonal){
        super(x,y,width,height,orintation,disabled);
        this.horizonal = horizonal;
        this.Tile = new ScreenUI(0,0,1,1,CENTER,false);
        super.addRenderObject(Tile);
        this.setup();
    }
    public ScrollBar(float x, float y, float width, float height, int orintation,Texture t,boolean disabled,boolean horizonal){
        super(x,y,width,height,orintation,t,disabled);
        this.horizonal = horizonal;
        this.Tile = new ScreenUI(0,0,1,1,CENTER,false);
        super.addRenderObject(Tile);
        this.setup();
    }
    public void setBoxTexture(Texture t){
        this.Tile.setTexture(t);
    }
    public Texture getBoxTexture(){
        return this.Tile.getTexture();
    }
    public void setHorizional(){
        this.horizonal = true;
        this.setup();
    }
    public void setVertical(){
        this.horizonal = false;
        this.setup();
    }
    public boolean isHorizional(){
        return this.horizonal;
    }
    public boolean isVertical(){
        return !this.horizonal;
    }
    public double getLesser(){
        return lesser;
    }
    public double getGreater(){
        return greater;
    }
    public UserInterfaceAction getScrollAction(){
        if(uia == null){
            this.uia = new Scroll(super.getUIZone());
        }
        return uia;
    }
    public void setTotalSize(double size){
        this.totalSize = size;
        this.setup();
    }
    public void setBarSize(double size){
        this.barSize = size;
        this.setup();
    }
    public final void setup(){
        ActionTaken(new Vector2f(1,1));
    }
    private void ActionTaken(Vector2f v){
        double DFC = barSize/totalSize;
        double lesserV,greaterV,loc,dist,preDist;
        float width = super.getActualWidth();
        float height = super.getActualHeight();
        int choose = this.getActaulOrientation();
        float x = super.getActualX(),y = super.getActualY();
        System.out.println(height);
        if(DFC < 1){
            if(this.horizonal){
                if(choose != CENTER && choose != DOWN &&choose != UP){
                    x += width/2;
                }
                this.Tile.setWidth((float) (DFC));
                if(choose == CENTER || choose == LEFT || choose == RIGHT){
                    this.Tile.setY(0);
                }
                else{
                    this.Tile.setY(-.5f);
                }
                this.Tile.setHeight(1);
            
                lesserV = ScreenUI.ConvertWorldToScreenX(x, choose)-ScreenUI.ConvertWorldToScreenXDistance(width);
                greaterV = ScreenUI.ConvertWorldToScreenX(x, choose)+ScreenUI.ConvertWorldToScreenXDistance(width);
                loc = v.x - lesserV;
                dist = greaterV - lesserV;
                preDist = loc/dist;
                DFC = 1 -DFC;
                DFC /= 2;
            
                preDist -=.5;
                preDist *= 2;
            
                if(preDist > DFC){
                    preDist = DFC;
                }
                else if(preDist < -DFC){
                    preDist = -DFC;
                }
                DFC = .5 - DFC;
                if(choose == CENTER || choose == DOWN || choose == UP){
                    this.Tile.setX((float) (preDist));
                }
                else{
                    this.Tile.setX((float) (preDist-DFC));
                }
                
            
                this.lesser = ((preDist-DFC)+.5);
                this.greater = ((preDist+DFC)+.5);
            }
            else{
                if(choose != CENTER && choose != LEFT && choose != RIGHT){
                    y += height/2;
                }
                this.Tile.setHeight((float) (DFC));
                if(choose == CENTER || choose == UP || choose == DOWN){
                    this.Tile.setX(0);
                }
                else{
                    this.Tile.setX(-.5f);
                }
                this.Tile.setHeight(1);
            
                lesserV = ScreenUI.ConvertWorldToScreenX(x, choose)-ScreenUI.ConvertWorldToScreenXDistance(width);
                greaterV = ScreenUI.ConvertWorldToScreenX(x, choose)+ScreenUI.ConvertWorldToScreenXDistance(width);
                loc = v.x - lesserV;
                dist = greaterV - lesserV;
                preDist = loc/dist;
                DFC = 1 -DFC;
                DFC /= 2;
            
                preDist -=.5;
                preDist *= 2;
            
                if(preDist > DFC){
                    preDist = DFC;
                }
                else if(preDist < -DFC){
                    preDist = -DFC;
                }
                DFC = .5 - DFC;
                if(choose == CENTER || choose == DOWN || choose == UP){
                    this.Tile.setY((float) (preDist));
                }
                else{
                    this.Tile.setY((float) (preDist-DFC));
                }
            
                this.lesser = ((preDist-DFC)+.5);
                this.greater = ((preDist+DFC)+.5);
            }
        }
        else{
            if(choose == CENTER || choose == LEFT || choose == RIGHT){
                this.Tile.setY(0);
            }
            else{
                this.Tile.setY(-.5f);
            }
            if(choose == CENTER||choose == UP||choose == DOWN){
                this.Tile.setX(0);
            }
            else{
                this.Tile.setX(-.5f);
            }
            this.Tile.setWidth(1);
            this.Tile.setHeight(1);
            this.lesser = 0;
            this.greater = DFC;
        }
        super.update();
    }
    @Override
    public void setUIManager(UIManager ui){
        super.setUIManager(ui);
        this.setup();
    }
    
    @Override
    public void ProcessChanges(UIManager ui,float x, float y, float width, float height,int Orinentation){
        super.ProcessChanges(ui, x, y, width, height, Orinentation);
        this.setup();
    }
    private class Scroll extends UserInterfaceAction{
        public Scroll(UIZone uiz){
            super(uiz);
        }
        @Override
        public void Action(ActionKeys k) {
            if(Mouse.isButtonDown(mouseB)){
                ActionTaken(cam.getMousePointOnGraphics());
            }
        }
    }
}
