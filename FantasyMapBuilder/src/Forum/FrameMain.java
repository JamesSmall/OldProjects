/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Forum;

import GraphicalObjects.Background;
import ToolBox.Tool;
import Utils.Config;
import FailWhale.SaveManager;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.filechooser.FileFilter;

public class FrameMain extends JFrame{
    private UndoManager BR = UndoManager.Instance;
    private String fileLocation = null;
    private JFileChooser FileChoose;
    private JMenuBar MasterBar = new JMenuBar();
    private JMenu ToolBarMenu = new JMenu();
    private static final int MOVEX = 16,MOVEY = 71;
    private List<ToolBox> activeboxes = new ArrayList();
    private DrawingPanel draw;
    private JPanelCustom left,right,up,down;
    private Background bg;
    private Tool t = null;
    public FrameMain(){
        left = new JPanelCustom(false);
        right = new JPanelCustom(false);
        down = new JPanelCustom(true);
        up = new JPanelCustom(true);
   
        this.add(up);
        this.add(down);
        this.add(left);
        this.add(right);
        
        super.setLayout(null);
        
        this.setMinimumSize(new Dimension(400,400));
        super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        super.addComponentListener(new Resize());
        
        this.MasterBar.setLayout(new FlowLayout(FlowLayout.LEFT));
        this.ToolBarMenu.setText("BarsConfig");
        this.MasterBar.add(new FileMenu());
        this.MasterBar.add(this.ToolBarMenu);
        super.setJMenuBar(this.MasterBar);
        
        this.FileChoose  = new JFileChooser(Config.GeneralSaveLocation);
        this.FileChoose.setFileFilter(new ConstraintsMBOnly());
        this.FileChoose.setFileFilter(new ConstraintsMBBCOnly());
        this.FileChoose.setFileFilter(new ConstraintsNormal());
        this.ResizeEvent();
    }
    public void setDrawingPanel(DrawingPanel p){
        if(this.draw != null){
            this.draw.setFrameMain(null);
            this.draw.setTool(null);
            this.remove(this.draw);
        }
        this.draw = p;
        if(this.draw != null){
            this.draw.setTool(t);
            this.draw.setFrameMain(this);
            this.add(p);
        }
        this.validate();
    }
    @Override
    public void validate(){
        super.validate();
        this.ResizeEvent();
    }
    public void setTool(Tool t){
        this.t = t;
        if(draw != null){
            this.draw.setTool(t);
        }
    }
    public Tool getTool(){
        return this.t;
    }
    private void ResizeEvent(){
        up.setLocation(0, 0);
        up.setSize(this.getWidth(), this.up.getDistance());
        down.setLocation(0,this.getHeight()-this.down.getDistance()-MOVEY);
        down.setSize(this.getWidth(),this.down.getDistance());
        left.setLocation(0, this.up.getDistance());
        left.setSize(this.left.getDistance(), this.getHeight() - this.up.getDistance()-this.down.getDistance()-MOVEY);
        right.setLocation(this.getWidth()-this.right.getDistance()-MOVEX, this.up.getDistance());
        right.setSize(this.right.getDistance(),this.getHeight()-this.down.getDistance()-this.up.getDistance()-MOVEY);
        if(this.draw != null){
            this.draw.setLocation(this.left.getDistance(), this.up.getDistance());
            this.draw.setSize(this.getWidth()-this.left.getDistance()-this.right.getDistance()-MOVEX,this.getHeight()-this.up.getDistance()-this.down.getDistance()-MOVEY);
        }
    }
    public synchronized void rollForward(){
        try {
            this.setDrawingPanel(this.BR.rollForward());
        } catch (IOException ex) {
            Logger.getLogger(FrameMain.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(FrameMain.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public synchronized void rollBackward(){
        try {
            this.setDrawingPanel(this.BR.rollBack());
        } catch (IOException ex) {
            Logger.getLogger(FrameMain.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(FrameMain.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public synchronized void PrepareBackup(){
        synchronized(draw){
            this.BR.add(draw);
        }       
    }
    public void addToolBox(ToolBox t){
        this.activeboxes.add(t);
        t.setFrameManager(this);
        this.updateBoxDetector();
    }
    private void updateBoxDetector(){
        int i;
        LocationSelector lc;
        this.left.removeAll();
        this.right.removeAll();
        this.up.removeAll();
        this.down.removeAll();
        this.ToolBarMenu.removeAll();
        for(i = 0; i < this.activeboxes.size();i++){
            lc = new LocationSelector(this.activeboxes.get(i));
            this.ToolBarMenu.add(lc);
            lc.CallEvent(this.activeboxes.get(i).getPreferedPosition());
        }
    }
    public DrawingPanel getDrawingPanel(){
        return this.draw;
    }
    public void setupSave(){
        new saveThread(false).start();
    }
    public void quickSave(){
        new saveThread(true).start();
    }
    public void setupLoad(){
        new loadThread().start();
    }
    private class Resize extends ComponentAdapter{
        @Override
        public void componentResized(ComponentEvent e) {
            ResizeEvent();
        }
        @Override
        public void componentMoved(ComponentEvent e) {
            ResizeEvent();
        }
        @Override
        public void componentShown(ComponentEvent e) {
            ResizeEvent();
        }
    }
    public void Emergency(int value){
       if(value == DrawingPanel.OutOfMemoryError){
           value = JOptionPane.showConfirmDialog(null,"Out of Memory!!, closing do you wish to save the previous edit","Out of Memory" , JOptionPane.OK_CANCEL_OPTION,JOptionPane.ERROR_MESSAGE);
           if(value == JOptionPane.OK_OPTION){
               //this.draw.rollBackward();
               this.quickSave();
               this.setDrawingPanel(null);
           }
       }
    }
    private class LocationSelector extends JMenu{
        public static final String UPCHOOSE = "Up";
        public static final String LEFTCHOOSE = "Left";
        public static final String RIGHTCHOOSE = "Right";
        public static final String DOWNCHOOSE = "Down";
        public static final String WINDOWEDCHOOSE = "Windowed";
        public static final String NONEXISTANTCHOOSE = "Remove";
        private ToolBox t;
        private Component c = null;
        private List<JRadioButtonMenuItem> items = new ArrayList();
        public LocationSelector(ToolBox t){
            int i;
            super.setText(t.getName());
            super.setLayout(new BorderLayout());
            this.t = t;
            super.setVisible(true);
            items.add(new Adjuster(up));
            items.add(new Adjuster(left));
            items.add(new Adjuster(right));
            items.add(new Adjuster(down));
            items.add(new Adjuster(null));
            items.add(new Remover());
            items.get(0).setText(UPCHOOSE);
            items.get(1).setText(LEFTCHOOSE);
            items.get(2).setText(RIGHTCHOOSE);
            items.get(3).setText(DOWNCHOOSE);
            items.get(4).setText(WINDOWEDCHOOSE);
            items.get(5).setText(NONEXISTANTCHOOSE);
            for(i = 0; i < 6; i++){
                super.add(items.get(i));
            }
        }
        public void CallEvent(int i){
            if(i >= items.size()){
                items.get(items.size()-1).setSelected(true);
            }
            items.get(i).setSelected(true);
        }
        public int discoverEvent(String name){
            switch(name){
                case UPCHOOSE:
                    return Config.Up;
                case LEFTCHOOSE:
                    return Config.Left;
                case RIGHTCHOOSE:
                    return Config.Right;
                case DOWNCHOOSE:
                    return Config.Down;
                case WINDOWEDCHOOSE:
                    return Config.Windowed;
                case NONEXISTANTCHOOSE:
                    return Config.Disabled;
            }
            return Config.Disabled;
        }
        private class Adjuster extends JRadioButtonMenuItem{
            public Adjuster(Component c){
                super();
                this.addItemListener(new ItemTransfere(c));
            }
            private class ItemTransfere implements ItemListener{   
                private Component cc = null;
                private boolean running = true;
                public ItemTransfere(Component c){
                    this.cc = c;
                }
                    @Override
                    public void itemStateChanged (ItemEvent e){
                        int i;
                        if(isSelected()&&running){
                            this.running = false;
                            for(i = 0; i < items.size();i++){
                                items.get(i).setSelected(false);
                            }
                            if(c instanceof JFrame){
                                ((JFrame)c).remove((JPanel)t);
                                ((JFrame)c).dispose();
                            }
                            else if(c instanceof JPanelCustom){
                                ((JPanelCustom)c).removeToolBox(t);
                            }
                            if(cc == null){
                                c = new ToolFrame(t);
                            }
                            else{
                                c = cc;
                                ((JPanelCustom)c).addToolBox(t);
                            }
                            t.setPreferedPosition(discoverEvent(getText()));
                            setSelected(true);
                            this.running = true;
                            ResizeEvent();
                            //new EventResize().start();
                        }
                    }
            }
        }
        private class Remover extends JRadioButtonMenuItem{
            private boolean running = true;
            public Remover(){
                super();
                this.addItemListener(new ItemTransfere());
            }
            private class ItemTransfere implements ItemListener{
                @Override
                public void itemStateChanged(ItemEvent e) {
                    int i;
                    if(isSelected()&&running){
                        running = false;
                        for(i = 0; i < items.size();i++){
                            items.get(i).setSelected(false);
                        }
                        
                        if(c instanceof ToolFrame){
                            ((ToolFrame)c).remove(t);
                            ((ToolFrame)c).dispose();
                            c = null;
                        }
                        else if(c instanceof JPanelCustom){
                            ((JPanelCustom)c).removeToolBox(t);
                            c = null;
                        }
                        setSelected(true);
                        running = true;
                        t.setPreferedPosition(discoverEvent(getText()));
                        ResizeEvent();
                        //new EventResize().start();
                    }
                }
            }
        }
    }
    private class FileMenu extends JMenu{
        public FileMenu(){
            super.setText("File");
            super.add(new Save());
            super.add(new SavePos());
            super.add(new Load());
            super.add(new Close());
        }
        private class Save extends JMenuItem{
            public Save(){
                super.setText("Save");
                this.addActionListener(new ActionListener(){
                    @Override
                    public void actionPerformed(ActionEvent e) {
                            setupSave();
                    }
                });
            }
        }
        private class SavePos extends JMenuItem{
            public SavePos(){
                super.setText("Save Fast");
                this.addActionListener(new ActionListener(){
                    @Override
                    public void actionPerformed(ActionEvent e) {
                         new saveThread(true).start();
                    }
                });
            }
        }
        private class Load extends JMenuItem{
            public Load(){
                super.setText("Load");
                this.addActionListener(new ActionListener(){
                    @Override
                    public void actionPerformed(ActionEvent e) {
                         new loadThread().start();
                    }
                });
            }
        }
        private class Close extends JMenuItem{
            public Close(){
               super.setText("Close Panel");
               this.addActionListener(new ActionListener(){
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        setDrawingPanel(null);
                    }
                });
            }
        }
    }
    private class ConstraintsNormal extends FileFilter{
        @Override
        public boolean accept(File f) {
            if(f.getName().endsWith(".MB")){
                return true;
            }
            else if(f.getName().endsWith(".MBBC")){
                return true;
            }
            else if(f.isDirectory()){
                return true;
            }
            return false;
        }
        @Override
        public String getDescription() {
            return ".MB && .MBBC(all MapBuilder Format)";
        }
    }
    private class ConstraintsMBOnly extends FileFilter{
        @Override
        public boolean accept(File f) {
            if(f.getName().endsWith(".MB")){
                return true;
            }
            else if(f.isDirectory()){
                return true;
            }
            return false;
        }
        @Override
        public String getDescription() {
            return ".MB(MapBuilder Format only current)";
        }
    }
    private class ConstraintsMBBCOnly extends FileFilter{
        @Override
        public boolean accept(File f) {
            if(f.getName().endsWith(".MBBC")){
                return true;
            }
            else if(f.isDirectory()){
                return true;
            }
            return false;
        }
        @Override
        public String getDescription() {
            return ".MBBC(MapBuilder Format backup)";
        }
    }
    private class saveThread extends Thread{
        boolean quick;
        public saveThread(boolean quick){
            this.quick = quick;
        }
        @Override
        public void run(){
            try {
                if(this.quick){
                    this.quickSave();
                }
                else{
                    this.setupSave();
                }
            } catch (FileNotFoundException ex) {
                Logger.getLogger(FrameMain.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(FrameMain.class.getName()).log(Level.SEVERE, null, ex);
            }
            repaint();
        }
        public void setupSave() throws FileNotFoundException, IOException{
            int value;
            FileChoose.setDialogTitle("Choose Save Location");
            FileChoose.setCurrentDirectory(Config.GeneralSaveLocation);
            value = FileChoose.showSaveDialog(null);
            if(value == JFileChooser.APPROVE_OPTION){
                fileLocation = FileChoose.getSelectedFile().getAbsolutePath();
                if(!fileLocation.endsWith(".MB")){
                    fileLocation += ".MB";
                }
                else if(fileLocation.endsWith(".MBBC")){
                    fileLocation = fileLocation.replace(".MBBC",".MB");
                }
                this.quickSave();
            }
        }
        public void quickSave() throws FileNotFoundException, IOException{
            if(fileLocation != null){
                if(!fileLocation.endsWith(".MB")){
                    fileLocation += ".MB";
                }
                else if(fileLocation.endsWith(".MBBC")){
                    fileLocation = fileLocation.replace(".MBBC",".MB");
                }
                SaveManager.SaveData(draw, fileLocation,false);
            }
            else{
                this.setupSave();
            }
        }
    }
    private class loadThread extends Thread{
        @Override
        public void run(){
            FileChoose.setDialogTitle("Choose which file to open");
            FileChoose.setCurrentDirectory(Config.GeneralSaveLocation);
            int value = FileChoose.showOpenDialog(null);
            if(value == JFileChooser.APPROVE_OPTION){
                fileLocation = FileChoose.getSelectedFile().getAbsolutePath();
                try {
                    setDrawingPanel(SaveManager.getSaveData(fileLocation));
                } catch (IOException ex) {
                    Logger.getLogger(FrameMain.class.getName()).log(Level.SEVERE, null, ex);
                } catch (Exception ex) {
                    Logger.getLogger(FrameMain.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            repaint();
        }
    }
}
