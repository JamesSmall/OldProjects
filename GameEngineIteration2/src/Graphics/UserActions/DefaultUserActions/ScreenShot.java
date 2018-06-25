/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Graphics.UserActions.DefaultUserActions;

import Graphics.Forum.ActionKeys;
import Graphics.Forum.Screen;
import Graphics.UserActions.UserAction;
import Utils.ErrorLog;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;
import javax.imageio.ImageIO;
import org.lwjgl.BufferUtils;
import org.lwjgl.Sys;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

/**
 *
 * @author Allazham
 */
public class ScreenShot implements UserAction{
    private boolean isDisabled = false;
    private static final Screen scr = Screen.getInstance();
    private static final int BPP = 4;
    @Override
    public void Action(ActionKeys k) {
        if(k.ContainsKeyboardKey(Keyboard.KEY_F9)){
            GL11.glReadBuffer(GL11.GL_FRONT);
            int width = scr.getWidth();
            int height = scr.getHeight();
            ByteBuffer buff = BufferUtils.createByteBuffer(width * height * BPP);
            GL11.glReadPixels(0, 0, width, height, GL11.GL_RGBA, GL11.GL_UNSIGNED_BYTE, buff);
            new Thread(new CreateImage(width,height,buff)).start();
        }
    }

    @Override
    public boolean isDisabled() {
        return this.isDisabled;
    }
    public void setDisabled(boolean disabled){
        this.isDisabled = disabled;
    }
    private class CreateImage implements Runnable{
        private File f = new File("ImageTest"+Sys.getTime()+".PNG");
        private ByteBuffer buff;
        BufferedImage image;
        private int width,height;
        public CreateImage(int width, int height,ByteBuffer BB){
            this.buff = BB;
            this.width = width;
            this.height = height;
        }
        @Override
        public void run() {
            int x,y,i,r,g,b;
            image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            for(x = 0; x < width; x++){
               for(y = 0; y < height;y++){
                   i = (x + (width * y)) * BPP;
                   r = buff.get(i) & 0xFF;
                   g = buff.get(i + 1) & 0xFF;
                   b = buff.get(i + 2) & 0xFF;
                   image.setRGB(x, height - (y + 1), (0xFF << 24) | (r << 16) | (g << 8) | b);
               }
           }
            try {
                ImageIO.write(image, "PNG",f);
            } catch (IOException ex) {
                ErrorLog.error(ex);
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
