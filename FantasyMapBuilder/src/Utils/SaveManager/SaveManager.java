/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils.SaveManager;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;

/**
 *
 * @author Allazham
 */
public final class SaveManager {
    private String saveDirectory = "";
    private static final String Save = "Chunk";
    private static final String EndSave = ".SGS";
   public void doSave(SaveObject obj,String fileLoc) throws FileNotFoundException, IOException{
        FileOutputStream fout = new FileOutputStream(fileLoc);
        ZipOutputStream zos = new ZipOutputStream(fout);
        PrintWriter pw;
        ZipEntry ze;
        ze = new ZipEntry(obj.getName());
        zos.putNextEntry(ze);
        pw = new PrintWriter(new BufferedOutputStream(zos));
        obj.addToSave(new Save(pw));
        pw.flush();
        zos.finish();
        pw.close();
        zos.closeEntry();
    }
    public void doSave(SaveObjectPackage obj, String fileLoc) throws FileNotFoundException,IOException{
        try (ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(fileLoc))) {
            obj.save("", zos);
            zos.finish();
            zos.close();
        }
    }
}
