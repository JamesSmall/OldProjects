/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author Allazham
 */
public class ImageEditor {
    public static final int ALPHA = 0;
    public static final int RED = 1;
    public static final int GREEN = 2;
    public static final int BLUE = 3;
    
    public static final int HUE = 0;
    public static final int SATURATION = 1;
    public static final int BRIGHTNESS = 2;
    
    public static final int TRANSPARENT = 0;
    
    public static final String ImageName = "IMAGE";
    public static BufferedImage changeColor(BufferedImage img,Color mask,Color replacement){
        BufferedImage destImage = deepCopy(img);
        int i,j,rgbnew,destRGB;
        for(i = 0; i < destImage.getWidth();i++){
            for(j = 0; j < destImage.getHeight();i++){
                destRGB = destImage.getRGB(i, j);
                if(matches(mask.getRGB(),destRGB)){
                    rgbnew = getNewPixelRGB(replacement.getRGB(),destRGB);
                    destImage.setRGB(i, j, rgbnew);
                }
            }
        }
        return destImage;
    }
    public static BufferedImage resizeImage(BufferedImage img,int WidthMinus,int HeightMinus,int WidthPlus,int HeightPlus){
        if(img == null){
            return null;
        }
        else if(!(img.getWidth()+WidthMinus+WidthPlus >= 1 && img.getHeight()+HeightMinus+HeightPlus >= 1)){
            return null;
        }
        BufferedImage newImage = new BufferedImage(img.getWidth()+WidthMinus+WidthPlus,img.getHeight()+HeightMinus+HeightPlus,BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = newImage.createGraphics();
        g.drawImage(img, WidthMinus, HeightMinus,null);
        return newImage;
    }
    public static boolean matches(int maskRGB,int destRGB){
        float[] hsbMask = getHSBArray(maskRGB);
        float[] hsbDest = getHSBArray(destRGB);
        if(hsbMask[HUE] == hsbDest[HUE] && hsbMask[SATURATION] == hsbDest[SATURATION] && getRGBArray(destRGB)[ALPHA] != TRANSPARENT){
            return true;
        }
        return false;
    }
    public static int getNewPixelRGB(int replacement,int destRGB){
        float[] destHSB = getHSBArray(destRGB);
        float[] repelHSB = getHSBArray(replacement);
        int rgbnew = Color.HSBtoRGB(repelHSB[HUE],repelHSB[SATURATION], destHSB[BRIGHTNESS]);
        return rgbnew;
    }
    public static int[] getRGBArray(int rgb){
        return new int[] {(rgb >> 24) & 0xff,(rgb >> 16)&0xff,(rgb >> 8) & 0xff,rgb & 0xff };
    }
    public static float[] getHSBArray(int rgb){
        int[] rgbArr = getRGBArray(rgb);
        return Color.RGBtoHSB(rgbArr[RED], rgbArr[GREEN], rgbArr[BLUE], null);
    }
    public static int[][] ConvertImageToIntArray(BufferedImage img){
        int x, y,ret[][] = new int[img.getWidth()][img.getHeight()];
        for(x = 0; x < img.getWidth();x++){
            for(y = 0; y < img.getHeight();y++){
                ret[x][y] = img.getRGB(x, y);
            }
        }
        return ret;
    }
    public static String ConvertImageToSaveData(BufferedImage img){
        int[][] dataHold = ConvertImageToIntArray(img);
        StringBuilder sb = new StringBuilder();
        String data;
        int x, y;
        sb.append(Config.START + Config.SEPERATOR + Config.IMAGE + Config.SEPERATOR + Config.WIDTH + Config.EQUALSYM).append(img.getWidth()).append(Config.SEPERATOR + Config.HEIGHT + Config.EQUALSYM).append(img.getHeight()).append(Config.SEPERATOR);
        for(x = 0; x < img.getWidth();x++){
            if(x == 0){
                sb.append(Config.DATA + Config.STARTSYMBOL);
            }
            else{
                sb.append(Config.SEPERATORX);
            }
            for(y = 0; y < img.getHeight();y++){
                if(y != 0){
                    sb.append(Config.SEPERATORY);
                }
               sb.append(dataHold[x][y]);
            }
        }
        sb.append(Config.ENDSYMBOL+Config.SEPERATOR+Config.END);
        return sb.toString();
    }
    public static BufferedImage rescaleImage(BufferedImage bi,double scaleX,double scaleY){
        BufferedImage newbi = new BufferedImage((int)(bi.getWidth()*scaleX),(int)(bi.getHeight()*scaleY),BufferedImage.TYPE_4BYTE_ABGR);
        Graphics2D g = newbi.createGraphics();
        g.drawImage(bi, 0,0,newbi.getWidth(),newbi.getHeight(), null);
        return newbi;
    }
    public static BufferedImage rescaleImageTo(BufferedImage bi, int ScaleX,int ScaleY){
        return ImageEditor.rescaleImage(bi, ScaleX/bi.getWidth(), ScaleY/bi.getHeight());
    }
    public static BufferedImage getImageFromSavedData(String dataTrans){
        BufferedImage BI = null;
        int x, y;
        int width,height;
        try{
        String[] tree,data, lines = dataTrans.split(Config.SEPERATOR);
        width = Integer.parseInt(lines[2].split(Config.EQUALSYM)[1]);
        height = Integer.parseInt(lines[3].split(Config.EQUALSYM)[1]);
        BI = new BufferedImage(width,height,BufferedImage.TYPE_4BYTE_ABGR);
        lines[4] = lines[4].replace(Config.DATA+Config.STARTSYMBOL, "");
        lines[4] = lines[4].replace(Config.ENDSYMBOL, "");
        tree = lines[4].split(Config.SEPERATORX);
        for(x = 0;x < width;x++){
            data = tree[x].split(Config.SEPERATORY);
            for(y = 0; y < height;y++){
                BI.setRGB(x, y,Integer.parseInt(data[y])); 
            }
        }}
        catch(Exception e){
            
        }
        return BI;
    }
    public static boolean[][] deepCopy(boolean[][] copy){
        if(copy.length == 0){
            return new boolean[0][0];
        }
        int x,y;
        boolean[][] ret = new boolean[copy.length][copy[0].length];
        for(x = 0; x < copy.length;x++){
            for(y = 0; y < copy[x].length;y++){
                ret[x][y] = copy[x][y];
            }
        }
        return ret;
    }
    public static BufferedImage deepCopy(BufferedImage bi) {
        BufferedImage superbi = new BufferedImage(bi.getWidth(),bi.getHeight(),BufferedImage.TYPE_4BYTE_ABGR);
        int x,y;
        for(x = 0; x < bi.getWidth();x++){
            for(y = 0; y < bi.getHeight();y++){
                superbi.setRGB(x, y, bi.getRGB(x, y));
            }
        }
        return superbi;
    }
  }