/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package World;

import Holder.TextureTypes.SingleColorTexture;
import Holder.Texture;
import java.awt.Color;

/**
 *
 * @author Allazham
 */
public class ImageControl {
    public void addModdededImage(Texture img, int i){
        
    }
    public static Texture getImageByTileType(int i){
        switch(i){
            case 0:
                return SingleColorTexture.getColoredTexture(Color.red);
            case 1:
                return SingleColorTexture.getColoredTexture(Color.pink);
            case 2:
                return SingleColorTexture.getColoredTexture(Color.green);
            case 3:
                return SingleColorTexture.getColoredTexture(Color.orange);
            case 4:
                return SingleColorTexture.getColoredTexture(Color.DARK_GRAY);
            case 5:
                return SingleColorTexture.getColoredTexture(Color.blue);
            case 6:
                return SingleColorTexture.getColoredTexture(Color.CYAN);
            case 7:
                return SingleColorTexture.getColoredTexture(Color.darkGray);
            case 8:
                return SingleColorTexture.getColoredTexture(Color.yellow);
            case 9:
                return SingleColorTexture.getColoredTexture(Color.WHITE);
        }
        return SingleColorTexture.getColoredTexture(Color.getHSBColor(0, 55, 24));
    }
    public static Texture getModdedImage(int i){
        return SingleColorTexture.getColoredTexture(Color.BLACK);
    }
}
