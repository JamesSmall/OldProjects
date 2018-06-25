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
 */
public class Sound {
    private String Name;
    private WaveData sound;
    private IntBuffer buffer = BufferUtils.createIntBuffer(1);

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
    
    public Sound(String name,String saveLocation) throws FileNotFoundException{
        this.Name = name;
        this.sound = WaveData.create(new BufferedInputStream(new FileInputStream(saveLocation+".wav")));
        this.buffer = BufferUtils.createIntBuffer(1);
        this.source = BufferUtils.createIntBuffer(1);
        this.sourcePos = (FloatBuffer)BufferUtils.createFloatBuffer(3).put(new float[] { 0.0f, 0.0f, 0.0f }).rewind();
        this.sourceVel = (FloatBuffer)BufferUtils.createFloatBuffer(3).put(new float[] { 0.0f, 0.0f, 0.0f }).rewind();
        this.listenerPos = (FloatBuffer)BufferUtils.createFloatBuffer(3).put(new float[] { 0.0f, 0.0f, 0.0f }).rewind();
        this.listenerVel = (FloatBuffer)BufferUtils.createFloatBuffer(3).put(new float[] { 0.0f, 0.0f, 0.0f }).rewind();
        this.listenerOri = (FloatBuffer)BufferUtils.createFloatBuffer(6).put(new float[] { 0.0f, 0.0f, -1.0f,  0.0f, 1.0f, 0.0f }).rewind();
        
        AL10.alGenBuffers(buffer);
        AL10.alBufferData(buffer.get(0), sound.format, sound.data, sound.samplerate);
        //sound.samplerate
        // Bind the buffer with the source.
        AL10.alGenSources(source);
        AL10.alSourcei(source.get(0), AL10.AL_BUFFER,   buffer.get(0) );
        AL10.alSourcef(source.get(0), AL10.AL_PITCH,    1.0f          );
        AL10.alSourcef(source.get(0), AL10.AL_GAIN,     1.0f          );
        AL10.alSource(source.get(0), AL10.AL_POSITION, sourcePos     );
        AL10.alSource(source.get(0), AL10.AL_VELOCITY, sourceVel     );
        this.setListenerValues();
    }
    public String getName(){
        return this.Name;
    }
    public void setName(String name){
        this.Name = name;
    }
    private Sound(String name,WaveData sound){
        this.sound = sound;
        this.Name = name;
        this.buffer = BufferUtils.createIntBuffer(1);
        this.source = BufferUtils.createIntBuffer(1);
        this.sourcePos = (FloatBuffer)BufferUtils.createFloatBuffer(3).put(new float[] { 0.0f, 0.0f, 0.0f }).rewind();
        this.sourceVel = (FloatBuffer)BufferUtils.createFloatBuffer(3).put(new float[] { 0.0f, 0.0f, 0.0f }).rewind();
        this.listenerPos = (FloatBuffer)BufferUtils.createFloatBuffer(3).put(new float[] { 0.0f, 0.0f, 0.0f }).rewind();
        this.listenerVel = (FloatBuffer)BufferUtils.createFloatBuffer(3).put(new float[] { 0.0f, 0.0f, 0.0f }).rewind();
        this.listenerOri = (FloatBuffer)BufferUtils.createFloatBuffer(6).put(new float[] { 0.0f, 0.0f, -1.0f,  0.0f, 1.0f, 0.0f }).rewind();
        
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
    }
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
    }
    public Sound GenerateNewSound(){
        return new Sound(this.Name,this.sound);
    }
    private void setListenerValues() {
        AL10.alListener(AL10.AL_POSITION,    listenerPos);
        AL10.alListener(AL10.AL_VELOCITY,    listenerVel);
        AL10.alListener(AL10.AL_ORIENTATION, listenerOri);
    }
    public void killALData() {
        AL10.alDeleteSources(source);
        AL10.alDeleteBuffers(buffer);
    }
    public void Play(){
       AL10.alSourcePlay(source);
    }
    public void Stop(){
        AL10.alSourceStop(source);
    }
    public void Pause(){
        AL10.alSourcePause(source);
    }
    @Override
    protected void finalize() throws Throwable{
        this.killALData();
        super.finalize();
    }
}
