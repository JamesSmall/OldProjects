/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ToolBox.ImageEdit;

import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;

/**
 *
 * @author Allazham
 */
public class ImageTypes{
    public static void setTypes(JFileChooser jfc){
        jfc.setFileFilter(new PNGType());
        jfc.setFileFilter(new JPEGType());
        jfc.setFileFilter(new BMPTypes());
        jfc.setFileFilter(new AllImageTypes());
    }
    private static class AllImageTypes extends FileFilter{

        @Override
        public boolean accept(File f) {
            if(f.isDirectory()){
                return true;
            }
            else if(f.getAbsolutePath().endsWith(".png")){
                return true;
            }
            else if(f.getAbsolutePath().endsWith(".jpg")){
                return true;
            }
            else if(f.getAbsolutePath().endsWith(".bmp")){
                return true;
            }
            return false;
        }

        @Override
        public String getDescription() {
            return "All loadable Images(.png,.jpg,.bmp)";
        }
        
    }
    private static class PNGType extends FileFilter{
        @Override
        public boolean accept(File f) {
            if(f.isDirectory()){
                return true;
            }
            return f.getAbsolutePath().endsWith(".png");
        }

        @Override
        public String getDescription() {
            return "PNG(.png)";
        }
        
    }
    private static class JPEGType extends FileFilter{

        @Override
        public boolean accept(File f) {
            if(f.isDirectory()){
                return true;
            }
            return f.getAbsolutePath().endsWith(".jpg");
        }

        @Override
        public String getDescription() {
            return "JPEG(.jpg)";
        }
        
    }
    private static class BMPTypes extends FileFilter{

        @Override
        public boolean accept(File f) {
            if(f.isDirectory()){
                return true;
            }
            return f.getAbsolutePath().endsWith("BMP(.bmp)");
        }

        @Override
        public String getDescription() {
            return "BMP(.bmp)";
        }
    }
}
