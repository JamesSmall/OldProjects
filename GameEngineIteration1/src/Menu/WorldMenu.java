/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Menu;

import Graphics.GUIManager;
import Graphics.Screen;
import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author Allazham
 */
public class WorldMenu {
    private GUIManager gui;
    private JFileChooser jfc;
    private static Screen scr = Screen.getInstance();
    private List<String> fileNames = new ArrayList();
    private List<JPanelCustom> panel = new ArrayList();
    private static final String NEWWORLD = "NEW";
    private static final String LOADWORLD = "LOAD";
    private static final String CANCEL = "CANCEL";
    private static final String NEWWORLDCUSTDIR = "NEWCUSTDIR";
    private static final String LOADWORLDCUSTDIR = "LOADCUSTDIR";
    private static final String DELETE = "CANCEL";
    private static final String RESET = "RESET";
    private static final String UP = "UP";
    private static final String DOWN = "DOWN";
    private double[][] selectable1 = new double[2][2],selectable2 = new double[2][2],selectable3 = new double[2][2];
    private int Set = 0;
    private int MAXSET = 0;
    FileNameExtensionFilter filter = new FileNameExtensionFilter("God war file Format(.GWTF)","GWTF");
    private WorldMenu() {
        double[][] GUIPoints = {{.33,.66},{.33,.66}};
        Functions f = new Functions();
        this.gui = new GUIManager(100,GUIPoints);
        JButton b = new JButton("New World");
        b.setActionCommand(WorldMenu.NEWWORLD);
        b.addActionListener(f);
        double[][] CompPoints = new double[2][2];
        CompPoints = new double[2][2];
        CompPoints[0][0] = 0;
        CompPoints[0][1] = .32;
        CompPoints[1][0] = .8;
        CompPoints[1][1] = 1;
        gui.addGUIComponenet(b, CompPoints);
        
        b = new JButton("load world");
        b.setActionCommand(WorldMenu.LOADWORLD);
        b.addActionListener(f);
        CompPoints = new double[2][2];
        CompPoints[0][0] = .33;
        CompPoints[0][1] = .66;
        CompPoints[1][0] = .8;
        CompPoints[1][1] = 1;
        gui.addGUIComponenet(b, CompPoints);
        
        b = new JButton("cancel");
        b.setActionCommand(WorldMenu.CANCEL);
        b.addActionListener(f);
        CompPoints = new double[2][2];
        CompPoints[0][0] = .67;
        CompPoints[0][1] = 1;
        CompPoints[1][0] = .8;
        CompPoints[1][1] = 1;
        gui.addGUIComponenet(b, CompPoints);
        
        b = new multiLineJButton("Custom directory","new world");
        b.setActionCommand(WorldMenu.NEWWORLD);
        b.addActionListener(f);
        CompPoints = new double[2][2];
        CompPoints[0][0] = 0;
        CompPoints[0][1] = .32;
        CompPoints[1][0] = .61;
        CompPoints[1][1] = .79;
        gui.addGUIComponenet(b, CompPoints);
        
        b = new multiLineJButton("Custom Directory","load world");
        b.setActionCommand(WorldMenu.LOADWORLD);
        b.addActionListener(f);
        CompPoints = new double[2][2];
        CompPoints[0][0] = .33;
        CompPoints[0][1] = .66;
        CompPoints[1][0] = .61;
        CompPoints[1][1] = .79;
        gui.addGUIComponenet(b, CompPoints);
        
        b = new JButton("delete");
        b.setActionCommand(WorldMenu.CANCEL);
        b.addActionListener(f);
        CompPoints = new double[2][2];
        CompPoints[0][0] = .67;
        CompPoints[0][1] = 1;
        CompPoints[1][0] = .61;
        CompPoints[1][1] = .79;
        gui.addGUIComponenet(b, CompPoints);
        
        b = new JButton("UP");
        b.setActionCommand(WorldMenu.UP);
        b.addActionListener(f);
        CompPoints = new double[2][2];
        CompPoints[0][0] = .67;
        CompPoints[0][1] = 1;
        CompPoints[1][0] = .5;
        CompPoints[1][1] = .59;
        gui.addGUIComponenet(b, CompPoints);
        
        b = new JButton("DOWN");
        b.setActionCommand(WorldMenu.DOWN);
        b.addActionListener(f);
        CompPoints = new double[2][2];
        CompPoints[0][0] = .33;
        CompPoints[0][1] = .66;
        CompPoints[1][0] = .5;
        CompPoints[1][1] = .59;
        gui.addGUIComponenet(b, CompPoints);
        
        b = new JButton("reset");
        b.setActionCommand(WorldMenu.RESET);
        b.addActionListener(f);
        CompPoints = new double[2][2];
        CompPoints[0][0] = 0;
        CompPoints[0][1] = .32;
        CompPoints[1][0] = .5;
        CompPoints[1][1] = .59;
        gui.addGUIComponenet(b, CompPoints);
        
        this.selectable1[0][0] = 0;
        this.selectable1[0][1] = 1;
        this.selectable1[1][0] = .34;
        this.selectable1[1][1] = .49;
        
        this.selectable2[0][0] = 0;
        this.selectable2[0][1] = 1;
        this.selectable2[1][0] = .17;
        this.selectable2[1][1] = .33;
        
        this.selectable3[0][0] = 0;
        this.selectable3[0][1] = 1;
        this.selectable3[1][0] = 0;
        this.selectable3[1][1] = .16;
        
        //focuser
        /*
        JPanelCustom fer = new JPanelCustom(new File("tree"));
        fer.addMouseListener(focuser);
        this.panel.add(fer);
        fer = new JPanelCustom(new File("tree"));
        fer.addMouseListener(focuser);
        this.panel.add(fer);
        fer = new JPanelCustom(new File("tree"));
        fer.addMouseListener(focuser);
        this.panel.add(fer);
        fer = new JPanelCustom(new File("tree"));
        fer.addMouseListener(focuser);
        this.panel.add(fer);
        fer = new JPanelCustom(new File("tree"));
        fer.addMouseListener(focuser);
        this.panel.add(fer); */
        this.setupFile();
        this.jfc = new JFileChooser();
        this.jfc.setMultiSelectionEnabled(false);
        this.jfc.setAcceptAllFileFilterUsed(false);
        this.jfc.setFileFilter(this.filter);
        
    }
    public void setupFile(){
        Listener focuser = new Listener();
        int i;
        if(!(this.MAXSET == 0)){
            //remove old componets
            this.gui.removeGUIComponenet(this.panel.get(this.Set*3));
            if(this.panel.size() > (this.Set*3)+1){
                this.gui.removeGUIComponenet(this.panel.get((this.Set*3)+1));
                if(this.panel.size() > (this.Set*3)+2){
                    this.gui.removeGUIComponenet(this.panel.get((this.Set*3)+2));
                }
            }
        }
        this.panel.clear();
        List<File> f = LoadDetect.getFiles();
        if(f == null){
            return;
        }
        JPanelCustom fer;
        for(i = 0; i < f.size();i++){
            fer = new JPanelCustom(f.get(i));
            fer.addMouseListener(focuser);
            this.panel.add(fer); 
        }
        this.setTotal();
    }
    public void AddToScreen(){
        WorldMenu.scr.addGUI(this.gui);
    }
    public void RemoveFromScreen(){
        WorldMenu.scr.removeGUI(this.gui);
    }
    private void setTotal(){
        this.MAXSET = (this.panel.size())/3;
        this.setMapSetting(0);
    }
    private void setMapSetting(int set){
        if(this.MAXSET == 0){
            return;
        }
        if(set < this.MAXSET+1 && set > -1){
            //remove old componets
            this.gui.removeGUIComponenet(this.panel.get(this.Set*3));
            if(this.panel.size() > (this.Set*3)+1){
                this.gui.removeGUIComponenet(this.panel.get((this.Set*3)+1));
                if(this.panel.size() > (this.Set*3)+2){
                    this.gui.removeGUIComponenet(this.panel.get((this.Set*3)+2));
                }
            }
            //add new componets
            this.Set = set;
            this.gui.addGUIComponenet(this.panel.get(set*3), selectable1);
            if(this.panel.size() > (this.Set*3)+1){
                this.gui.addGUIComponenet(this.panel.get((set*3)+1), selectable2);
            }
            if(this.panel.size() > (this.Set*3)+2){
                this.gui.addGUIComponenet(this.panel.get((set*3)+2), selectable3);
            }
            this.gui.reset();
            Screen.getInstance().ResetUI(); 
        }
    }
    public static WorldMenu getInstance() {
        return WorldMenuHolder.INSTANCE;
    }
    private static class WorldMenuHolder {
        private static final WorldMenu INSTANCE = new WorldMenu();
    }
    private class Functions implements ActionListener {
        private MainMenu mnu = MainMenu.getInstance();
        @Override
        public void actionPerformed(ActionEvent e) {
            int choice;
            switch (e.getActionCommand()){
                case WorldMenu.CANCEL:
                    RemoveFromScreen();
                    this.mnu.AddToScreen();
                    break;
                case WorldMenu.LOADWORLD:
                    break;
                case WorldMenu.NEWWORLD:
                    choice = jfc.showSaveDialog(scr);
                    if(JFileChooser.APPROVE_OPTION == choice)
                    {
                        System.out.println("yay");
                    }
                    break;
                case WorldMenu.UP:
                    setMapSetting(Set+1);
                    break;
                case WorldMenu.DOWN:
                    setMapSetting(Set-1);
                    break;
            }
        }
    }
    private class Listener implements MouseListener{
        @Override
        public void mouseClicked(MouseEvent e) {
            int i;
            for(i = 0; i < panel.size();i++){
                panel.get(i).setFocused(false);
            }
            System.out.println("focused gained");
            ((JPanelCustom) e.getSource()).setFocused(true);
        }

        @Override
        public void mousePressed(MouseEvent e) {
        }

        @Override
        public void mouseReleased(MouseEvent e) {
        }

        @Override
        public void mouseEntered(MouseEvent e) {
        }

        @Override
        public void mouseExited(MouseEvent e) {
        }
    }
    private class multiLineJButton extends JButton{
        private JLabel start,end;
        public multiLineJButton(String start, String end){
            this.start = new JLabel(start);
            this.end = new JLabel(end);
            super.setLayout(new BorderLayout());
            super.add(BorderLayout.NORTH, this.start);
            super.add(BorderLayout.SOUTH,this.end);
        }
    }
    private class JPanelCustom extends JPanel{
        private String FileName,MapLocation;
        boolean clicked;
        private File f;
        private boolean Focus;
        public JPanelCustom(File f){
            super.setFocusable(true);
            super.setRequestFocusEnabled(true);
            super.setLayout(new BorderLayout());
            super.add(new JLabel(f.getName()),BorderLayout.CENTER);
            this.f = f;
            this.FileName = f.getName();
            this.MapLocation= f.getAbsolutePath();
        }
        public String getMapName(){
            return this.FileName;
        }
        public String getMapLocation(){
            return this.MapLocation;
        }
        public File getFile(){
            return this.f;
        }
        public boolean isFocused(){
            return this.Focus;
        }
        public void setFocused(boolean focus){
            this.Focus = focus;
            this.repaint();
        }
        @Override
        public void paint(Graphics g){
            super.paint(g);
            if(this.Focus){
                Graphics2D g2 = (Graphics2D) g;
                g.drawString("Targeted",this.getWidth()-50,this.getHeight()/2 +5);
            }
        }
    }
}
