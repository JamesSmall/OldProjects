/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Graphics.Base.Graphic;

import Graphics.Base.ScreenGraphic;
import Holder.MasterLoad;
import Holder.Texture;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.lwjgl.LWJGLException;
import org.lwjgl.Sys;

/**
 *
 * @author Allazham
 */
public class ParticleEngine extends ScreenGraphic{
    private final List<Particle> particles;
    private long lastCheck = -1;
    private ParticleEngine(){
        super(false);
        super.setPriority(90);
        particles = Collections.synchronizedList(new ArrayList());
    }
    @Override
    protected void render() throws LWJGLException {
        int i;
        if(this.lastCheck == -1){
            this.lastCheck = Sys.getTime();
        }
        if (Sys.getTime() - this.lastCheck > 1000) {
            this.lastCheck += 1000;
            for(i = 0; i < particles.size();i++){
                if(particles.get(i).check()){
                    this.particles.get(i).render(true);
                    this.particles.remove(i);
                    i--;
                }
                else{
                    this.particles.get(i).render(false);
                }
            }
        }
        else{
            for(i = 0; i < particles.size();i++){
                particles.get(i).render(false);
            }
        }
    }
    public Particle GenericParticle(String TextureName, float x, float y,float vX,float vY, float width,float height,double angle,int updates){
        Texture tex = MasterLoad.getImage(TextureName);
        if(tex != null){
            Particle p = new BasicParticle(x,y,vX,vY,width,height,angle,updates,tex);
            this.particles.add(p);
            return p;
        }
        return null;
    }
    public Particle GenericParticle(Texture tex, float x, float y,float vX,float vY, float width,float height,double angle,int updates){
        if(tex != null){
            Particle p = new BasicParticle(x,y,vX,vY,width,height,angle,updates,tex);
            this.particles.add(p);
            return p;
        }
        return null;
    }
    public Particle TileOverlay(Texture tex,int x, int y,int updates){
        if(tex != null){
            Particle p = new TileOverlay(tex,x,y,updates);
            this.particles.add(p);
            return p;
        }
        return null;
    }
    public Particle TileOverlay(String tex,int x,int y,int updates){
        return this.TileOverlay(MasterLoad.getImage(tex), x, y, updates);
    }
    public static ParticleEngine getInstance(){
        return ParticleEngineHold.e;
    }

    @Override
    public void disposeGraphics() {
        
    }
    private static class ParticleEngineHold{
        public static ParticleEngine e = new ParticleEngine();
    }
}
