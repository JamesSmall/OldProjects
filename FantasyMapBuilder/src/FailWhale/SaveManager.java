/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package FailWhale;

import Forum.DrawingPanel;
import GraphicalObjects.Background;
import GraphicalObjects.ComplexBackground;
import GraphicalObjects.GraphicsObject;
import GraphicalObjects.Line;
import GraphicalObjects.NodeMesh;
import GraphicalObjects.Node;
import Utils.Config;
import Utils.ImageEditor;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.BufferedOutputStream;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;
import java.util.zip.ZipInputStream;

/**
 *
 * @author Allazham
 */
public class SaveManager {
    public static void SaveDataa(DrawingPanel draw,String fileLoc,boolean isTemp) throws FileNotFoundException, IOException{
        if(!isTemp){
            checkAndMakeBackups(fileLoc);
        }
    }
    public static void SaveData(DrawingPanel draw,String fileLoc,boolean isTemp) throws FileNotFoundException, IOException{
        if(!isTemp){
            checkAndMakeBackups(fileLoc);
        }
        synchronized(draw.getGraphicsList()){
        int i,j,n;
        String print;
        boolean value;
        BufferedImage b;
        Background back;
        List<GraphicsObject> save = new ArrayList(),temp = draw.getGraphicsList(); 
        FileOutputStream fout = new FileOutputStream(fileLoc);
        ZipEntry ze;
        List<List<Node>> thelist;
        List<Node> nodes;
        NodeMesh mesh;
        Node node;
        PrintWriter pw;
        for(i = 0; i < temp.size();i++){
            save.add(temp.get(i).getNewInstance());
        }
        try (ZipOutputStream zos = new ZipOutputStream(fout)) {
            pw = new PrintWriter(new BufferedOutputStream(zos));
            ze = new ZipEntry(Config.MAIN);
            zos.putNextEntry(ze);
            pw.print(save.size());
            if(draw.getTargetGraphic() != null){
                pw.println(Config.SEPERATOR+draw.getTargetGraphic().getUID().toString());
            }
            pw.flush();
            zos.closeEntry();
            back = draw.getImageBackground();
            if(back != null){
                ze = new ZipEntry("background/"+Config.MAIN);
                zos.putNextEntry(ze);
                pw.flush();
                zos.closeEntry();
                ze = new ZipEntry("background/"+"Image");
                zos.putNextEntry(ze);
                pw.println(ImageEditor.ConvertImageToSaveData(back.getImage()));
                pw.flush();
                zos.closeEntry();
                ze = new ZipEntry("background/"+"ImageSample");
                zos.putNextEntry(ze);
                pw.println(ImageEditor.ConvertImageToSaveData(back.getSampleImage()));
                pw.flush();
                zos.closeEntry();
            }
            for(i = 0; i < save.size();i++){
                if(save.get(i) instanceof NodeMesh){
                    mesh = (NodeMesh) save.get(i);
                    thelist = mesh.getNodesAsList();
                    b = mesh.getImage();
                    ze = new ZipEntry(Config.MESHLIST+i+"/"+Config.MAIN);
                    zos.putNextEntry(ze);
                    
                    if(mesh.isShowingLines()){
                        print = "1";
                    }
                    else{
                        print ="0";
                    }
                    print += Config.SEPERATOR+mesh.getUID();
                    pw.println(print);
                    pw.flush();
                    zos.closeEntry();
                    if(b!= null){
                        ze = new ZipEntry(Config.MESHLIST+i+"/Image");
                        zos.putNextEntry(ze);
                        pw.println(ImageEditor.ConvertImageToSaveData(b));
                        pw.flush();
                        zos.closeEntry();
                    }
                    for(j = 0; j < thelist.size();j++){
                        ze = new ZipEntry(Config.MESHLIST+i+"/NodeList"+j+"/Info");
                        zos.putNextEntry(ze);
                        value = mesh.getCircled(j);
                        if(value){
                            pw.println("1");
                        }
                        else{
                            pw.println("0");
                        }
                        pw.flush();
                        zos.closeEntry();
                        nodes = thelist.get(j);
                        for(n = 0; n < nodes.size();n++){
                            node = nodes.get(n);
                            ze = new ZipEntry(Config.MESHLIST+i+"/NodeList"+j+"/Node"+n);
                            zos.putNextEntry(ze);
                            pw.println(node.getUID().toString()+Config.SEPERATOR+node.getImageX()+Config.SEPERATOR+node.getImageY());
                            pw.flush();
                            zos.closeEntry();
                        }
                    }
                }
                else if(save.get(i) instanceof Node){
                    node = (Node) save.get(i);
                    ze = new ZipEntry("Node"+i+"/"+Config.MAIN);
                    pw.flush();
                    zos.closeEntry();
                }
                else if(save.get(i) instanceof Line){
                    
                }
            }
            
            zos.finish();
            pw.close();
            zos.close();
            
        }
        }
    }
    public static DrawingPanel getSaveData(String fileloc) throws IOException, Exception{
        String s;
        UUID uid = null;
        String[] ss = null;
        boolean value;
        NodeMesh ML;
        int pos, i,j;
        String baseFile;
        List<Node> nodes;
        GraphicsObject[] readin = null;
        DrawingPanel p = new DrawingPanel();
        BufferedReader inputStream = null;
        ZipFile f = new ZipFile(fileloc);
        ZipEntry ze;
        Background back;
        ze = f.getEntry(Config.MAIN);
        if(ze == null){
            throw new IOException("File does not contain its main");
        }
        ZipInputStream zis = new ZipInputStream(new FileInputStream(fileloc));
        inputStream = new BufferedReader(new InputStreamReader(f.getInputStream(ze)));
        
        while((s = inputStream.readLine()) != null){
                ss = s.split(Config.SEPERATOR);
                readin = new GraphicsObject[Integer.parseInt(ss[0])];
                if(ss.length > 1){
                    uid = UUID.fromString(ss[1]);
                }
        }
        inputStream.close();
        ze = f.getEntry("background/"+Config.MAIN);
        if(ze != null){
            back = new ComplexBackground();
            ze = f.getEntry("background/Image");
            inputStream = new BufferedReader(new InputStreamReader(f.getInputStream(ze)));
            s = inputStream.readLine();
            inputStream.close();
            ze = f.getEntry("background/ImageSample");
            inputStream = new BufferedReader(new InputStreamReader(f.getInputStream(ze)));
            back.setImage(ImageEditor.getImageFromSavedData(inputStream.readLine()), ImageEditor.getImageFromSavedData(s));
            inputStream.close();
        }
        while((ze = zis.getNextEntry())!= null){
            inputStream = new BufferedReader(new InputStreamReader(f.getInputStream(ze)));
            if(ze.getName().contains(Config.MESHLIST)&&ze.getName().contains(Config.MAIN)){
                ML = new NodeMesh();
                ML.doNotUpate(true);
                if((s = inputStream.readLine()) != null){
                    ss = s.split(Config.SEPERATOR);
                    if("1".equals(ss[0])){
                        ML.setHasLines(true);
                    }
                    else{
                        ML.setHasLines(false);
                    }
                    ML.setUID(UUID.fromString(ss[1]));
                }
                pos = Integer.parseInt(ze.getName().replace("/"+Config.MAIN,"").replace(Config.MESHLIST,""));
                baseFile = ze.getName().replace("/"+Config.MAIN, "");
                inputStream.close();
                //data reading goes here
                //circked 
                i = 0;
                while((ze = f.getEntry(baseFile+"/NodeList"+i+"/Info"))!= null){
                    inputStream = new BufferedReader(new InputStreamReader(f.getInputStream(ze)));
                    if(inputStream.readLine().equals("1")){
                        value = true;
                    }
                    else{
                        value = false;
                    }
                    inputStream.close();
                    j = 0;
                    nodes = new ArrayList();
                    while((ze = f.getEntry(baseFile+"/NodeList"+i+"/Node"+j))!= null){
                        inputStream = new BufferedReader(new InputStreamReader(f.getInputStream(ze)));
                        ss = inputStream.readLine().split(Config.SEPERATOR);
                        nodes.add(new Node(UUID.fromString(ss[0]),Double.parseDouble(ss[1]),Double.parseDouble(ss[2])));
                        j++;
                        inputStream.close();
                    }
                    ML.addNodes(nodes, value);
                    i++;
                }
                ze = f.getEntry(baseFile+"/Image");
                try{
                inputStream = new BufferedReader(new InputStreamReader(f.getInputStream(ze)));
                ML.setImage(ImageEditor.getImageFromSavedData(inputStream.readLine()));
                inputStream.close();
                ML.doNotUpate(false);
                readin[pos] = ML;
                }catch(NullPointerException ex){
                    
                }
            }
            else if(null == null) {
                
            }
        }
        
        inputStream.close();
        f.close();
        zis.close();
        p.setDrawing(readin);
        if(uid != null){
            for(i = 0; i < readin.length;i++){
                if(readin[i].getUID().toString() == null ? uid.toString() == null : readin[i].getUID().toString().equals(uid.toString())){
                    p.setTargetGraphic(readin[i]);
                    break;
                }
            }
        }
        return p;
    }
    private static void checkAndMakeBackups(String name){
        String current;
        boolean[] check = new boolean[4];
        File f = new File(name);
        File BackupTO;
        int i;
        for(i = 0; i < check.length;i++){
            check[i] = false;
        }
        if(f.exists()){
             current = name.replace(".MB", ".MBBC");
             BackupTO = new File(current);
             if(BackupTO.exists()){
                 BackupTO.delete();
             }
             check[0] = f.renameTo(BackupTO);
        }
    }
}
