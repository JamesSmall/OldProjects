/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Holder;

import Holder.JavaScript.JavaScriptFactory;
import Holder.TextureTypes.AnimatedTexture;
import Holder.TextureTypes.BasicTexture;
import Holder.TextureTypes.RollingHorizonalTexture;
import Holder.TextureTypes.SpriteSheet;
import Holder.TextureTypes.GlyphTexture;
import Utils.ErrorLog;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;


/**
 * the master load is suppose to be the first called in the constructor so all statements can correctly grab their javascript, images, and sound files nessassary
 * for the came to operate correctly
 * @author Allazham
 */
public class MasterLoad {
    private static GlyphTexture glyph;
    private static final List<Sound> sounds = new ArrayList();
    private static final List<Texture> images = new ArrayList();
    private static final List<Script> scripts = new ArrayList();
    private static JavaScriptFactory scriptFactory = new JavaScriptFactory();
    private MasterLoad(){
    }
    public static GlyphTexture GlyphTexture(){     
        return glyph;
    }
    public static Texture getImage(String name){
        int i;
        for(i = 0; i < MasterLoad.images.size();i++){
            if(MasterLoad.images.get(i).getName().matches(name)){
                return MasterLoad.images.get(i);
            }
        }
        return null;
    }
    public static Sound getSound(String name) throws IOException{
        int i;
        for(i = 0; i < MasterLoad.sounds.size();i++){
            if(MasterLoad.sounds.get(i).getName().equalsIgnoreCase(name)){
                return MasterLoad.sounds.get(i);
            }
        }
        return null;
    }
    public static Script getScript(String name){
        int i;
        for(i = 0; i < MasterLoad.scripts.size();i++){
            if(MasterLoad.scripts.get(i).getName().equals(name)){
                return MasterLoad.scripts.get(i);
            }
        }
        return null;
    }
    public static void load() throws InterruptedException{
        Script s = scriptFactory.getBasicEngine();
        s.put("Load", getLoadInstance());
        s.RunScriptDirect("Resources/Load.js");
    }
    public static LoadInstance getLoadInstance(){
        return new LoadInstance();
    }
    //for loading images through java script
    public static final class LoadInstance{
        private void RemoveTexture(String name){
            int i;
            for(i = 0; i < images.size();i++){
                if(images.get(i).getName().equals(name)){
                    images.remove(i);
                    return;
                }
            }
        }
        public void loadSpriteSheet(String location,int AWidth,int AHeight){
            try {
                BufferedImage img = ImageIO.read(new File(location));
                images.add(new SpriteSheet(img,AWidth,AHeight));
            } catch (Exception ex) {
                ErrorLog.error(ex);
            }
        }
        public void loadAnimatedTexture(String location,int AWidth,int AHeight){
            try {
                BufferedImage img = ImageIO.read(new File(location));
                images.add(new AnimatedTexture(img,AWidth,AHeight));
            } catch (Exception ex) {
                ErrorLog.error(ex);
            }
        }
        public void LoadGlyph(String name,String texture,String loaderFile) throws IOException{
            MasterLoad.images.add(LoaderUtils.createGlyphTexture(name, new File(texture), new File(loaderFile)));
        }
        public void createPrimaryGlyph(String name,String texture,String loaderFile) throws IOException{
            MasterLoad.glyph = LoaderUtils.createGlyphTexture(name, new File(texture), new File(loaderFile));
            MasterLoad.images.add(glyph);
        }
        public void loadAnimatedTexture(String location,int AWidth,int AHeight,long time){
            try {
                BufferedImage img = ImageIO.read(new File(location));
                images.add(new AnimatedTexture(img,AWidth,AHeight,time));
            } catch (Exception ex) {
                ErrorLog.error(ex);
            }
        }
        public void loadBasicTexture(String location){
            try {
                BufferedImage img = ImageIO.read(new File(location));
                images.add(new BasicTexture(img));
            } catch (Exception ex) {
                ErrorLog.error(ex);
            }
        }
        public void loadRollingHorizonalTexture(String location){
            try {
                BufferedImage img = ImageIO.read(new File(location));
                images.add(new RollingHorizonalTexture(img));
            } catch (Exception ex) {
                ErrorLog.error(ex);
            }
        }
        //with names
        public void loadSpriteSheet(String name,String location,int AWidth,int AHeight){
            try {
                this.RemoveTexture(name);
                BufferedImage img = ImageIO.read(new File(location));
                images.add(new SpriteSheet(name,img,AWidth,AHeight));
            } catch (Exception ex) {
                ErrorLog.error(ex);
            }
        }
        public void loadAnimatedTexture(String name,String location,int AWidth,int AHeight){
            try {
                this.RemoveTexture(name);
                BufferedImage img = ImageIO.read(new File(location));
                images.add(new AnimatedTexture(name,img,AWidth,AHeight));
            } catch (Exception ex) {
                ErrorLog.error(ex);
            }
        }
        public void loadAnimatedTexture(String name,String location,int AWidth,int AHeight,long time){
            try {
                this.RemoveTexture(name);
                BufferedImage img = ImageIO.read(new File(location));
                images.add(new AnimatedTexture(name,img,AWidth,AHeight,time));
            } catch (Exception ex) {
                ErrorLog.error(ex);
            }
        }
        public void loadBasicTexture(String name,String location){
            try {
                this.RemoveTexture(name);
                BufferedImage img = ImageIO.read(new File(location));
                images.add(new BasicTexture(name,img));
            } catch (Exception ex) {
                ErrorLog.error(ex);
            }
        }
        public void loadRollingHorizonalTexture(String name,String location){
            try {
                this.RemoveTexture(name);
                BufferedImage img = ImageIO.read(new File(location));
                images.add(new RollingHorizonalTexture(name,img));
            } catch (Exception ex) {
                ErrorLog.error(ex);
            }
        }
        public void loadSound(String loc,int seconds){
            try {
                sounds.add(new Sound(loc));
            } catch (FileNotFoundException ex) {
                ErrorLog.error(ex);
            }
        }
        public void loadBasicJavaScript(String name){
            try{
                scripts.add(scriptFactory.getBasicEngine(name));
            }
            catch(Exception ex){
                
            }
        }
        public void loadJavaScript(String name,List<String> name2, List<String> loc){
            try{
                scripts.add(scriptFactory.createEngineFromContex(name, new ArrayList(),new ArrayList(), name2,loc));
                
            }
            catch(Exception ex){
                
            }
        }
    }
}
