/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import Graphics.Forum.Screen;
import Graphics.Base.ScreenUI;
import static Graphics.Base.ScreenUI.CENTER;
import Graphics.Forum.MasterClose;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.nio.ByteBuffer;
import javax.swing.JFrame;

import org.lwjgl.BufferUtils;
import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.GL12;

import static org.lwjgl.opengl.GL11.*;

public class BasicTextureTest {
   
   private static final int WIDTH = 800, HEIGHT = 600;
   
   public static void maisn(String[] args) throws InterruptedException{
      
      JFrame j = new JFrame();
      j.setSize(500,500);
      j.add(Screen.getInstance().getPanel());
      j.setVisible(true);
      j.addWindowListener(MasterClose.getDestroyOnCloseListener());
      j.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
      new Thread(Screen.getInstance()).start();
      while(!Screen.isScreenCreated()){
          Thread.sleep(1);
      }
      //It's of course also possible to just load an image using ImageIO.load()
      final BufferedImage test = new BufferedImage(128, 128, BufferedImage.TYPE_INT_ARGB);
      Graphics2D g2d = test.createGraphics();

      g2d.setColor(new Color(1.0f, 1.0f, 1.0f, 0.5f));
      g2d.fillRect(0, 0, 128, 128); //A transparent white background
      
      g2d.setColor(Color.red);
      g2d.drawRect(0, 0, 127, 127); //A red frame around the image
      g2d.fillRect(10, 10, 10, 10); //A red box 
      
      g2d.setColor(Color.blue);
      g2d.drawString("Test image", 10, 64); //Some blue text
      
      
      Screen.getInstance().addGraphicObject(new ScreenUI(false) {

          @Override
          public void render() throws LWJGLException {
              final int textureID = loadTexture(test);
              
              glBindTexture(GL_TEXTURE_2D, textureID);
              ScreenUI.DrawBasicTexturedSquare(0f,0f,-.5f,-.5f,1d,1d,0d,0d,CENTER);
          }
      });
   }
   
   private static final int BYTES_PER_PIXEL = 4;
   public static int loadTexture(BufferedImage image){
      
      int[] pixels = new int[image.getWidth() * image.getHeight()];
        image.getRGB(0, 0, image.getWidth(), image.getHeight(), pixels, 0, image.getWidth());

        ByteBuffer buffer = BufferUtils.createByteBuffer(image.getWidth() * image.getHeight() * BYTES_PER_PIXEL); //4 for RGBA, 3 for RGB
        
        for(int y = 0; y < image.getHeight(); y++){
            for(int x = 0; x < image.getWidth(); x++){
                int pixel = pixels[y * image.getWidth() + x];
                buffer.put((byte) ((pixel >> 16) & 0xFF));     // Red component
                buffer.put((byte) ((pixel >> 8) & 0xFF));      // Green component
                buffer.put((byte) (pixel & 0xFF));               // Blue component
                buffer.put((byte) ((pixel >> 24) & 0xFF));    // Alpha component. Only for RGBA
            }
        }

        buffer.flip(); //FOR THE LOVE OF GOD DO NOT FORGET THIS

        // You now have a ByteBuffer filled with the color data of each pixel.
        // Now just create a texture ID and bind it. Then you can load it using 
        // whatever OpenGL method you want, for example:

      int textureID = glGenTextures(); //Generate texture ID
        glBindTexture(GL_TEXTURE_2D, textureID); //Bind texture ID
        
        //Setup wrap mode
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_S, GL12.GL_CLAMP_TO_EDGE);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL12.GL_CLAMP_TO_EDGE);

        //Setup texture scaling filtering
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_LINEAR);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_LINEAR);
        
        //Send texel data to OpenGL
        glTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA8, image.getWidth(), image.getHeight(), 0, GL_RGBA, GL_UNSIGNED_BYTE, buffer);
      
        //Return the texture ID so we can bind it later again
      return textureID;
   }
}

