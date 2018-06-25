/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Holder;

import Holder.TextureTypes.GlyphTexture;
import Holder.TextureTypes.TextPacket;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;

/**
 *
 * @author Allazham
 */
public class LoaderUtils {
    public static GlyphTexture createGlyphTexture(String name,File texture,File CVS) throws IOException{
        BufferedImage img = ImageIO.read(texture);
        System.out.println(CVS.exists());
        BufferedReader br = new BufferedReader(new FileReader(CVS));
        String line = br.readLine();
        StringBuilder b = new StringBuilder();
        while(line != null){
            b.append(line);
            line = br.readLine();
        }
        line = b.toString();
        return LoaderUtils.doTexture(name,line, img);
    }
    private static String[] split(String string,char split){
        String s;
        ArrayList<IntegerHolder> test = new ArrayList();
        char[] chars = string.toCharArray();
        for(int i = 0; i < chars.length;i++){
            if(chars[i] == split){
                if(i != 0 && i != chars.length - 1){
                    if(!(chars[i-1] == '"' && chars[i+1] == '"')){
                        test.add(new IntegerHolder(i));
                    }
                }
            }
        }
        ArrayList<String> strings = new ArrayList();
        s = string.substring(0, test.get(0).hold);
        if(!s.isEmpty()){
            strings.add(s);
        }
        for(int i = 0; i < test.size()-1;i++){
            s = string.substring(test.get(i).hold+1, test.get(i+1).hold);
            if(!s.isEmpty()){
                strings.add(s);
            }
        }
        if(string.charAt(string.length()-1) == split){
            s = string.substring(test.get(test.size()-1).hold+1, string.length()-1);
            if(!s.isEmpty()){
                strings.add(s);
            }
        }
        else{
            s = string.substring(test.get(test.size()-1).hold+1, string.length());
            if(!s.isEmpty()){
                strings.add(s);
            }
        }
        return strings.toArray(new String[0]);
    }
    private static GlyphTexture doTexture(String name,String line,BufferedImage img) throws IOException{
        String[] data = line.split(":", 2);
        String[] datapoints = split(data[0],',');
        int aWidth = Integer.parseInt(datapoints[0]),aHeight = Integer.parseInt(datapoints[1]);
        ArrayList<TextPacket> packets = new ArrayList();
        for(String info: split(data[1],':')){
            packets.add(getPacket(info));
        }
        //GlyphTexture(String name,BufferedImage img,int AWidth,int AHeight,List<TextPacket> packet){
        return new GlyphTexture(name,img,aWidth,aHeight,packets);
    }
    private static TextPacket getPacket(String data) throws IOException{
        String[] info = split(data,',');
        for(String s:info){
            System.out.println(s);
        }
        if(info.length == 3){
            return new TextPacket(info[0].charAt(1),Integer.parseInt(info[1]),Integer.parseInt(info[2]));
        }
        else if(info.length == 7){
            return new TextPacket(info[0].charAt(1),Integer.parseInt(info[1]),Integer.parseInt(info[2]),Integer.parseInt(info[3]),Integer.parseInt(info[4]),Integer.parseInt(info[5]),Integer.parseInt(info[6]));
        }
        System.out.println(info.length);
        throw new IOException("file reading error, inproper format");
    }
    private static class IntegerHolder{
        private IntegerHolder(int hold){
            this.hold = hold;
        }
        int hold;
    }
}
