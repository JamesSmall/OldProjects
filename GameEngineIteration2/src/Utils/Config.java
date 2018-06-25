/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

import Forum.FrameMain;
import Forum.ToolBoxPanel;
import Forum.ToolBox.WorldControl.TileAdder;
import Forum.ToolBox.WorldControl.TileRemover;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author Allazham
 */
public final class Config {
    static{
        String versions = System.getProperty("java.version");
        int pos = 0, count = 0;
        for (; pos < versions.length() && count < 2; pos++) {
            if (versions.charAt(pos) == '.') {
                count++;
            }
        }

        --pos; //EVALUATE double

        JavaVersion = Double.parseDouble(versions.substring(0, pos));
    }
    private Config(){
        
    }
    public static final double JavaVersion;
    //forums management
    public static final int Disabled = 5;
    public static final int Windowed = 4;
    public static final int Up = 0;
    public static final int Left = 1;
    public static final int Right = 2;
    public static final int Down = 3;
    private static ToolBoxPanel TileControl;
    
    public static BufferedImage PointerBasic;
    
    public static void setupToolBox(FrameMain fm) throws IOException{
        PointerBasic = ImageIO.read(new File("Resources/Texture/Pointer/SelectorTool.png"));
        
        TileControl =  new ToolBoxPanel(Config.Up);
        TileControl.addTool(new TileAdder());
        TileControl.addTool(new TileRemover());
        fm.addToolBox(TileControl);
    }
}
