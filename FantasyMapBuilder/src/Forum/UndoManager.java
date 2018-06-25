/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Forum;

import GraphicalObjects.GraphicsObject;
import FailWhale.SaveManager;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Allazham
 */
public enum UndoManager {
    Instance;
    private int i = -1;
    private int MaxNumber = 25;
    private List<String> theList = new ArrayList();
    public void add(DrawingPanel p){
        try {
            File f = File.createTempFile("undoFile",".MBBC");
            f.deleteOnExit();
            if(i != this.theList.size() - 1){
                this.theList.removeAll(this.theList.subList(i,theList.size()-1));
            }
            SaveManager.SaveData(p,f.getAbsolutePath(),true);
            this.theList.add(f.getAbsolutePath());
            i = this.theList.size()-1;
            if(this.theList.size() > this.MaxNumber){
                this.theList.remove(0);
                i--;
            }
            System.gc();
        } catch (IOException ex) {
            Logger.getLogger(UndoManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public DrawingPanel rollBack() throws IOException, Exception{
       i--;
       if(i == -1){
           i++;
       }
       System.gc();
       return SaveManager.getSaveData(this.theList.get(i));
    }
    public DrawingPanel rollForward() throws IOException, Exception{
        i++;
        if(i == this.theList.size()){
            i--;
        }
        System.gc();
        return SaveManager.getSaveData(this.theList.get(i));
    }
    public DrawingPanel getGraphicList(int i) throws IOException, Exception{
        System.gc();
        return SaveManager.getSaveData(this.theList.get(i));
    }
    public void resetList(DrawingPanel g) throws IOException{
        this.theList.clear();
        this.add(g);
        System.gc();
    }
}
