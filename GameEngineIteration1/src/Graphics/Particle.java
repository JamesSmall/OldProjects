/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Graphics;

/**
 *last Edited:3/2/2013
 * @author james small
 */
public class Particle extends Square3D{
    private static final float HALFPI = (float) ((1*Math.PI)/2);
    private static float pitch = 0, bearing = 0;
    private float textureDistance;
    public Particle(float x, float y, float z,float texDist,boolean disabled){
        //texture without sprite;
        super(x,y,z,null,disabled);
        this.textureDistance = texDist;
    }
    @Override
    protected void render(){
        float[][] part = {
            {(this.textureDistance*-(float)Math.cos(Particle.bearing))+(this.textureDistance*-(float)Math.sin(Particle.pitch))*(float)Math.cos(Particle.bearing+Particle.HALFPI),(this.textureDistance*(float)Math.cos(Particle.pitch)),(this.textureDistance*-(float)Math.sin(Particle.bearing))+(this.textureDistance*-(float)Math.sin(Particle.pitch))*(float)Math.sin(Particle.bearing+Particle.HALFPI)},
            {(this.textureDistance*-(float)Math.cos(Particle.bearing))+(this.textureDistance*(float)Math.sin(Particle.pitch))*(float)Math.cos(Particle.bearing+Particle.HALFPI),(this.textureDistance*-(float)Math.cos(Particle.pitch)),(this.textureDistance*-(float)Math.sin(Particle.bearing))+(this.textureDistance*(float)Math.sin(Particle.pitch))*(float)Math.sin(Particle.bearing+Particle.HALFPI)},
            {(this.textureDistance*(float)Math.cos(Particle.bearing))+(this.textureDistance*(float)Math.sin(Particle.pitch))*(float)Math.cos(Particle.bearing+Particle.HALFPI),(this.textureDistance*-(float)Math.cos(Particle.pitch)),(this.textureDistance*(float)Math.sin(Particle.bearing))+(this.textureDistance*(float)Math.sin(Particle.pitch))*(float)Math.sin(Particle.bearing+Particle.HALFPI)},
            {(this.textureDistance*(float)Math.cos(Particle.bearing))+(this.textureDistance*-(float)Math.sin(Particle.pitch))*(float)Math.cos(Particle.bearing+Particle.HALFPI),(this.textureDistance*(float)Math.cos(Particle.pitch)),(this.textureDistance*(float)Math.sin(Particle.bearing))+(this.textureDistance*-(float)Math.sin(Particle.pitch))*(float)Math.sin(Particle.bearing+Particle.HALFPI)}};
        super.setPoints(part);
        super.render();
    }
    public void setTextureDistance(float dist){
        this.textureDistance = dist;
    }
    public float getTextureDistance(){
        return this.textureDistance;
    }
    public static void setBearing(float bearing){
        Particle.bearing = bearing;
    }
    public static void setPitch(float pitch){
        Particle.pitch = pitch;
    }
}
