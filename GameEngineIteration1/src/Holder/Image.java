/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Holder;

import java.io.File;
import java.io.IOException;
import org.lwjgl.opengl.GL11;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.util.ResourceLoader;

/**
 *
 * @author Allazham
 */
public class Image {
   private int x,y,MaxX,MaxY;
   boolean IgnoreSprite;
   private Texture tex;
   private String name;
   public Image(String name,String location,int x, int y, int MaxX,int MaxY,boolean ignore) throws IOException{
      this.tex = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream(location+".PNG"),true);
      this.name = name;
      this.x = x;
      this.MaxX = MaxX;
      this.y = y;
      this.MaxY = MaxY;
      this.IgnoreSprite = ignore;
   }
   public Image(String location,int x, int y, int MaxX,int MaxY,boolean ignore) throws IOException{
      this.tex = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream(location+".PNG"),true);
      this.name = new File(location).getName();
      this.x = x;
      this.MaxX = MaxX;
      this.y = y;
      this.MaxY = MaxY;
      this.IgnoreSprite = ignore;
   }
   private Image(Texture tex,String name,int x, int y, int MaxX,int MaxY,boolean ignore){
      this.tex = tex;
      this.name = name;
      this.x = x;
      this.MaxX = MaxX;
      this.y = y;
      this.MaxY = MaxY;
      this.IgnoreSprite = ignore;
   }
   public void setTexture(String location) throws IOException{
       this.tex = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream(location+".PNG"),true);
   }
   public void setTexture(Texture tex){
       this.tex = tex;
   }
   public Texture getTexture(){
       return this.tex;
   }
   public Image getNewInstance(){
       return new Image(this.tex,this.name,this.x,this.y,this.MaxX,this.MaxY,this.IgnoreSprite);
   }
   public void bind(){
       this.tex.bind();
   }
   public void setName(String name){
       this.name = name;
   }
   public String getName(){
       return this.name;
   }
   public void setX(int x){
       this.x = x;
   }
   public void setY(int y){
       this.y = y;
   }
   public void setMaxX(int MaxX){
       this.MaxX = MaxX;
   }
   public void setMaxY(int MaxY){
       this.MaxY = MaxY;
   }
   public int getX(){
       return this.x;
   }
   public int getZ(){
       return this.y;
   }
   public int getMaxX(){
       return this.MaxX;
   }
   public int getMaxY(){
       return this.MaxY;
   }
   public float getMinorX(){
       if(this.IgnoreSprite){
           return 0;
       }
       return ((float)this.x /(float)this.MaxX);
   }
   public float getMinorY(){
       if(this.IgnoreSprite){
           return 0;
       }
       return ((float)this.y/(float)this.MaxY);
   }
   public float getXGreaterX(){
       if(this.IgnoreSprite){
           return 1;
       }
       return ((float)(this.x+1)/(float)this.MaxX);
   }
   public float getXGreaterY(){
       if(this.IgnoreSprite){
           return 1;
       }
       return ((float)(this.y+1)/(float)this.MaxY);
   }
   public boolean isIgnoringSprite(){
       return this.IgnoreSprite;
   }
   public void setIgnoringSprite(boolean ignore){
       this.IgnoreSprite = ignore;
   }
   public void unbind(){
       GL11.glDeleteTextures(this.tex.getTextureID());
   }
}
                         