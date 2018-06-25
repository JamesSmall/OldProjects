/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Holder;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import org.lwjgl.BufferUtils;
import org.lwjgl.openal.AL10;
import org.lwjgl.util.WaveData;

/**
 *
 * @author Allazham
 * the basic sound engine
 */
public class Sound {
    private String Name;
    
    private WaveData sound;
    private IntBuffer buffer = BufferUtils.createIntBuffer(1);

    private boolean bound = false;
    /** Sources are points emitting sound. */
    private IntBuffer source;
    /** Position of the source sound. */
    private FloatBuffer sourcePos;
    /** Velocity of the source sound. */
    private FloatBuffer sourceVel;
    /** Position of the listener. */
    private FloatBuffer listenerPos;
    /** Velocity of the listener. */
    private FloatBuffer listenerVel;
    /** Orientation of the listener. (first 3 elements are "at", second 3 are "up") */
    private FloatBuffer listenerOri;
    
    //basic constructor
    public Sound(String name,String saveLocation) throws FileNotFoundException{
        this.Name = name;
        this.sound = WaveData.create(new BufferedInputStream(new FileInputStream(saveLocation)));
        this.source = BufferUtils.createIntBuffer(1);
        this.sourcePos = (FloatBuffer)BufferUtils.createFloatBuffer(3).put(new float[] { 0.0f, 0.0f, 0.0f }).rewind();
        this.sourceVel = (FloatBuffer)BufferUtils.createFloatBuffer(3).put(new float[] { 0.0f, 0.0f, 0.0f }).rewind();
        this.listenerPos = (FloatBuffer)BufferUtils.createFloatBuffer(3).put(new float[] { 0.0f, 0.0f, 0.0f }).rewind();
        this.listenerVel = (FloatBuffer)BufferUtils.createFloatBuffer(3).put(new float[] { 0.0f, 0.0f, 0.0f }).rewind();
        this.listenerOri = (FloatBuffer)BufferUtils.createFloatBuffer(6).put(new float[] { 0.0f, 0.0f, -1.0f,  0.0f, 1.0f, 0.0f }).rewind();
    }
    //nameless constructor
    public Sound(String saveLocation) throws FileNotFoundException{
        this.Name = "UnnamedSound";
        this.buffer = BufferUtils.createIntBuffer(1);
        this.source = BufferUtils.createIntBuffer(1);
        this.sourcePos = (FloatBuffer)BufferUtils.createFloatBuffer(3).put(new float[] { 0.0f, 0.0f, 0.0f }).rewind();
        this.sourceVel = (FloatBuffer)BufferUtils.createFloatBuffer(3).put(new float[] { 0.0f, 0.0f, 0.0f }).rewind();
        this.listenerPos = (FloatBuffer)BufferUtils.createFloatBuffer(3).put(new float[] { 0.0f, 0.0f, 0.0f }).rewind();
        this.listenerVel = (FloatBuffer)BufferUtils.createFloatBuffer(3).put(new float[] { 0.0f, 0.0f, 0.0f }).rewind();
        this.listenerOri = (FloatBuffer)BufferUtils.createFloatBuffer(6).put(new float[] { 0.0f, 0.0f, -1.0f,  0.0f, 1.0f, 0.0f }).rewind();
        
    }
    //get name, used in masterloader
    public String getName(){
        return this.Name;
    }
    //set name for sight of the master loader
    public void setName(String name){
        this.Name = name;
    }
    //new instance
    private Sound(String name,WaveData sound){
        this.sound = sound;
        this.Name = name;
    }
    //unsafe sound creation
    @Deprecated
    private Sound(String name,WaveData sound,float[] ListenerPos,float[] ListenerOrt,float[] SourcePos){
        this.sound = sound;
        this.Name = name;
        this.buffer = BufferUtils.createIntBuffer(1);
        this.source = BufferUtils.createIntBuffer(1);
        this.sourcePos = (FloatBuffer)BufferUtils.createFloatBuffer(3).put(ListenerPos).rewind();
        this.sourceVel = (FloatBuffer)BufferUtils.createFloatBuffer(3).put(new float[] { 0.0f, 0.0f, 0.0f }).rewind();
        this.listenerPos = (FloatBuffer)BufferUtils.createFloatBuffer(3).put(SourcePos).rewind();
        this.listenerVel = (FloatBuffer)BufferUtils.createFloatBuffer(3).put(new float[] { 0.0f, 0.0f, 0.0f }).rewind();
        this.listenerOri = (FloatBuffer)BufferUtils.createFloatBuffer(6).put(ListenerOrt).rewind();
    }
    public void bind(){
        AL10.alGenBuffers(buffer);
        AL10.alBufferData(buffer.get(0), sound.format, sound.data, sound.samplerate);
        //sound.samplerate
        // Bind the buffer with the source.
        AL10.alGenSources(source);
        AL10.alSourcei(source.get(0), AL10.AL_BUFFER,   buffer.get(0) );
        AL10.alSourcef(source.get(0), AL10.AL_PITCH,    1.0f          );
        AL10.alSourcef(source.get(0), AL10.AL_GAIN,     1.0f          );
        AL10.alSource (source.get(0), AL10.AL_POSITION, sourcePos     );
        AL10.alSource (source.get(0), AL10.AL_VELOCITY, sourceVel     );
        this.setListenerValues();
        this.bound = true;
    }
    public Sound GenerateNewSound(){
        return new Sound(this.Name,this.sound);
    }
    private void setListenerValues() {
        AL10.alListener(AL10.AL_POSITION,    listenerPos);
        AL10.alListener(AL10.AL_VELOCITY,    listenerVel);
        AL10.alListener(AL10.AL_ORIENTATION, listenerOri);
    }
    //used to kill data
    public void killALData() {
        AL10.alDeleteSources(source);
        AL10.alDeleteBuffers(buffer);
    }
    public void Play(){
        if(!this.bound){
            this.bind();
        }
       AL10.alSourcePlay(source);
    }
    public void Stop(){
        if(!this.bound){
            this.bind();
        }
        AL10.alSourceStop(source);
    }
    public void Pause(){
        if(!this.bound){
            this.bind();
        }
        AL10.alSourcePause(source);
    }
}
