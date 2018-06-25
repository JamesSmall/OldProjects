/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Holder;

import Graphics.Forum.Screen;
import java.awt.image.BufferedImage;
import java.nio.ByteBuffer;
import java.util.Arrays;
import org.lwjgl.BufferUtils;
import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

/**
 *
 * @author Allazham
 */
public abstract class Texture{
    //varriables
    protected int TextureBindType = GL11.GL_NEAREST;
    private final int BYTES_PER_PIXEL = 4;
    private static Screen scr = Screen.getInstance();
    private static int CURRENTBIND = 0;
    
    private boolean Bound = false;
    private int TextureID = 0;
    private int[] pixels;
    private int Width,Height;
    private String name = "UnNamed";
    protected boolean CopyInstance = false;
    //direct load
    public Texture(int[] data, int width,int height){
        if(data.length != width*height){
            throw new IndexOutOfBoundsException("Data does not match width and height");
        }
        this.pixels = data;
        this.Width = width;
        this.Height = height;
        //this.ConstructImage();
    }
    //buffered image load
    //for new instance most likely(because super is the first called in an instance)
    public Texture(int TextureID,int[] Pixels,int Width,int Height,String name){
        this.TextureID = TextureID;
        this.pixels = Pixels;
        this.Width = Width;
        this.Height = Height;
        this.name = name;
        this.Bound = true;
    }
    public Texture(BufferedImage img){
       this.ConstructImage(img);
    }
    //for easy access
    public Texture(BufferedImage img,String name){
        this.ConstructImage(img);
        this.name = name;
    }
    //expects a customized load, not for direct use
    protected Texture(){
        
    }
    public boolean TextureEquals(Texture t){
        if(super.equals(t)){
            return true;
        }
        else{
            if(!this.CopyInstance && !t.CopyInstance){
                return false;
            }
            if(this.TextureID == t.TextureID){
                return true;
            }
            return (Arrays.equals(t.pixels, this.pixels) &&this.Width == t.Width && this.Height == t.Height);
        }
    }
    public static boolean TextureEquals(Texture t1,Texture t2){
        return t1.equals(t2);
    }
    public static void setCurrentBindNotice(int bind){
        Texture.CURRENTBIND = bind;
    }
    //sets the name for targeting by Loading
    public void setName(String name){
        this.name = name;
    }
    //get the name for load detection
    public String getName(){
        return this.name;
    }
    public void setRawPixels(int[] data){
        if(data == null){
            throw new NullPointerException("Data must be an array");
        }
        if(data.length != this.Width*this.Height){
            throw new IndexOutOfBoundsException("Data length and height does not match up with the data length");
        }
        this.pixels = data;
        this.Bound = false;
    }
    public void setRawPixels(int Width,int Height,int[] data){
        if(data == null){
            throw new NullPointerException("Data must be an array");
        }
        if(data.length != Width*Height){
            throw new IndexOutOfBoundsException("Data length and height does not match up with the data length");
        }
        this.Width = Width;
        this.Height = Height;
        this.pixels = data;
        this.Bound = false;
    }
    //its final because its is essential peice to the lower api
    public void setImage(BufferedImage img){
        this.Bound = false;
        //accessor to private content
        this.ConstructImage(img);
    }
    //used to get bufferediamgefrom data
    public BufferedImage getBufferedImage(){
        BufferedImage image = new BufferedImage(this.Width,this.Height,BufferedImage.TYPE_INT_ARGB);
        int x,y;
        for(y = 0;y<Height;y++){
            for(x = 0; x < Width;x++){
                 image.setRGB(x, y,pixels[y*Width+x]);
            }
        }
        return image;
    }
    //checks and binds, does all lwjgl statements nessassary for proper rendering
    public void bind() throws LWJGLException{
        //if pixels are null, than it is impossible to bind because you cannot create an image most likely.
        if(pixels == null){
            return;
        }
        //if image need to be destroyed, than do it
        //if not Bound(created) than construct the image
        if(!this.Bound){
            this.ConstructImage();
        }
        //checks to see if bind is nessassary
        if(Texture.CURRENTBIND != this.TextureID){
            GL11.glBindTexture(GL11.GL_TEXTURE_2D, TextureID);

            GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, this.TextureBindType);
            GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, this.TextureBindType);
            Texture.CURRENTBIND = this.TextureID;
        }
    }
    public void dispose(){
        if(!this.CopyInstance){
            scr.addTextureIdToDelete(this.TextureID);
        }
    }
    private void ConstructImage(BufferedImage image){
        this.Bound = false;
        this.Width = image.getWidth();
        this.Height = image.getHeight();
        pixels = new int[image.getWidth()*image.getHeight()];
        image.getRGB(0,0,image.getWidth(),image.getHeight(),pixels,0,image.getWidth());
    }
    //construcst the image for lwjgl useage, used only once per image Unless the image is reconsturcted
    private void ConstructImage() throws LWJGLException{
        //varriables
        GL11.glMatrixMode(GL11.GL_TEXTURE);
        int x,y, pixel;
        ByteBuffer buff = BufferUtils.createByteBuffer(Width*Height*BYTES_PER_PIXEL);
        //converts image into buffer for lwjgl
        for(y = 0;y<Height;y++){
            for(x = 0; x < Width;x++){
                pixel = pixels[y*Width+x];
                buff.put((byte)((pixel >> 16)&0xFF));
                buff.put(((byte)((pixel >> 8) & 0xFF)));
                buff.put(((byte)(pixel & 0xFF)));
                buff.put(((byte)((pixel >> 24) & 0xFF)));
            }
        }
        
        buff.flip();
        //generate texture and methods
        if(this.TextureID == 0){
            this.TextureID = GL11.glGenTextures();
        }
        GL11.glBindTexture(GL11.GL_TEXTURE_2D, TextureID);
        GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_S, GL12.GL_CLAMP_TO_EDGE);
        GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_T, GL12.GL_CLAMP_TO_EDGE);
            
        GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, this.TextureBindType);
        GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, this.TextureBindType);
            
        Texture.CURRENTBIND = this.TextureID;
        GL11.glTexImage2D(GL11.GL_TEXTURE_2D,0,GL11.GL_RGBA8,Width,Height,0,GL11.GL_RGBA,GL11.GL_UNSIGNED_BYTE,buff);
        this.Bound = true;
        GL11.glMatrixMode(GL11.GL_MODELVIEW);
    }
    //for possible new instances
    public int[] getRawPixels(){
        return this.pixels;
    }
    //to get an idea what the bufferedimage was like
    public int getWidth(){
        return this.Width;
    }
    public int getHeight(){
        return this.Height;
    }
    //gets id, for possible new instances
    public int getTextureID(){
        return this.TextureID;
    }
    @Override
    protected void finalize() throws Throwable{
        this.dispose();
        super.finalize();
    }
    //for texutre detection(for animated and other types of textures)
    public abstract double getGreaterX();
    public abstract double getGreaterY();
    public abstract double getLesserX();
    public abstract double getLesserY();
    //generate a new instance, for animated textures seperate from the pack.
    public abstract Texture getNewInstance();
}
