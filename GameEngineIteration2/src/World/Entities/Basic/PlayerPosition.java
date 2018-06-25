/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package World.Entities.Basic;

import Graphics.Forum.ActionKeys;
import Graphics.Forum.Screen;
import Graphics.UserActions.UserWorldAction;
import Graphics.Utils.Camera2D;
import Save.EntityInfo;
import Save.SaveObjectPackage;
import World.Entities.Bodies.Animation;
import World.Entities.Bodies.PreBuilt.Npc;
import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.util.vector.Vector2f;

/**
 *
 * @author Allazham
 */
public class PlayerPosition extends Npc{
    private final PlayerPosition p = this;
    private final Vector2f Direction = new Vector2f();
    public PlayerPosition(){
    }
    public PlayerPosition(EntityInfo e){
        super(e);
    }
    @Override
    public void PeronalMovement(int delta) {
        final Vector2f loc = new Vector2f();
        int i;
        for(i = 0; i < delta;i++){
            Vector2f.add(this.Direction, super.getLocationVector(), loc);
            if(super.checkLocation(loc)){
                super.setLocation(loc);
            }
        }
    }
    
    @Override
    public void Update(int delta) {
        
    }
    public void Manager(){
        Screen.getInstance().addUserAction(new LocationReader());
        Screen.getInstance().addUserAction(new AnimationTest());
        Camera2D.getCamera().LinkCameraToVector2f(this.getGraphicalLocation());
    }
    @Override
    protected void render() throws LWJGLException{
        Vector2f m = Camera2D.getCamera().getMousePointInWorld();
        super.lookAt(m);
        super.render();
    }
    @Override
    public SaveObjectPackage getSave() {
        return this.getBasicPackage();
    }

    @Override
    public String getSaveIdentity() {
        return "PlayerPosition";
    }

    @Override
    public void disposeEntry() {
        
    }
    @Override
    public void disposeGraphics() {
        
    }
    @Override
    public boolean isPersistent() {
        return false;
    }
    private class LocationReader extends UserWorldAction{

        @Override
        public void Action(ActionKeys k) {
            if(Keyboard.isKeyDown(Keyboard.KEY_W)){
                Direction.y = .005f;
            }
            else if(Keyboard.isKeyDown(Keyboard.KEY_S)){
                Direction.y = - .005f;
            }
            else{
                Direction.y = 0;
            }
            if(Keyboard.isKeyDown(Keyboard.KEY_A)){
                Direction.x = -.005f;
            }
            else if(Keyboard.isKeyDown(Keyboard.KEY_D)){
                Direction.x =  .005f;
            }
            else{
                Direction.x = 0;
            }
        }

        @Override
        public void create() {
            
        }

        @Override
        public void dispose() {
            
        }
        
    }
        private class AnimationTest extends UserWorldAction{
            private Animation a = new Animation("Hit");//double[] angle, float[] distance,String name
            
            public AnimationTest(){
                a.addAnimationSequence(new double[]{-.25,-.25,-.25},
            new float[]{1.25f,1.5f,1.25f},"HandRight");
                a.addAnimationSequence(new double[]{.25,.25,.25},
            new float[]{1.25f,1.5f,1.25f},"HandLeft");
                p.addAnimation(a);
            }
            
            @Override
            public void Action(ActionKeys k) {
                if(Mouse.isButtonDown(0)){
                    if(!p.isAnimating()){
                        p.activateAnimation("Hit");
                    }
                }
            }

            @Override
            public void create() {
                
            }

            @Override
            public void dispose() {
                
            }
        }
}
