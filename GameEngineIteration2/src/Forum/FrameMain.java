/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Forum;

import Graphics.Base.UI.Console;
import Graphics.Base.UI.TextArea;
import Graphics.Forum.MasterClose;
import Graphics.Forum.Screen;
import Graphics.UserActions.UIZonesManager;
import Graphics.UserActions.UserAction;
import Holder.TextureTypes.SingleColorTexture;
import Utils.Config;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.filechooser.FileFilter;

public class FrameMain extends JFrame{
    private final JFileChooser FileChoose;
    private final JMenuBar MasterBar = new JMenuBar();
    private final JMenu ToolBarMenu = new JMenu();
    private static final int MOVEX = 16,MOVEY = 71;
    private final List<ToolBoxPanel> activeboxes = new ArrayList();
    private Screen draw;
    private final JPanelCustom left,right,up,down;
    private UserAction t = null;
    public FrameMain(){
        left = new JPanelCustom(false);
        right = new JPanelCustom(false);
        down = new JPanelCustom(true);
        up = new JPanelCustom(true);
        draw = Screen.getInstance();
        
        this.add(up);
        this.add(down);
        this.add(left);
        this.add(right);
        this.add(draw.getPanel());
        this.draw.getPanel().setVisible(true);
        super.setLayout(null);
        this.setMinimumSize(new Dimension(400,400));
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                MasterClose.CloseAll();
            }
            
        });
        super.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        super.addComponentListener(new Resize());
        
        this.MasterBar.setLayout(new FlowLayout(FlowLayout.LEFT));
        this.ToolBarMenu.setText("BarsConfig");
        this.MasterBar.add(new FileMenu());
        this.MasterBar.add(this.ToolBarMenu);
        super.setJMenuBar(this.MasterBar);
        
        this.FileChoose  = new JFileChooser();
        this.FileChoose.setFileFilter(new ConstraintsMBOnly());
        this.FileChoose.setFileFilter(new ConstraintsMBBCOnly());
        this.FileChoose.setFileFilter(new ConstraintsNormal());
        this.ResizeEvent();
    }
    public void startGL() throws InterruptedException{
        this.draw = Screen.getInstance();
        new Thread(this.draw).start();
        Console a = new Console(.1f,0f,.5f,.5f,TextArea.DOWNLEFT,SingleColorTexture.getColoredTexture(Color.PINK),false);
        a.setTextHeight(.05f);
        draw.addGraphicObject(a);
        draw.addUserAction(a.getUserAction());
        draw.addUserAction(a.getLeftRightMouseWheelListener());
        UIZonesManager.addUIZone(a.getUIZone());
    }
    @Override
    public void validate(){
        super.validate();
        this.ResizeEvent();
    }
    public void setTool(UserAction t){
        if(this.t != null){
            if(this.draw != null){
                this.draw.removeUserAction(this.t);
            }
        }
        this.t = t;
        if(draw != null){
            this.draw.addUserAction(t);
        }
    }
    public UserAction getTool(){
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
            this.draw.getPanel().setLocation(this.left.getDistance(), this.up.getDistance());
            this.draw.getPanel().setSize(this.getWidth()-this.left.getDistance()-this.right.getDistance()-MOVEX,this.getHeight()-this.up.getDistance()-this.down.getDistance()-MOVEY);
        }
    }
    public synchronized void rollForward(){
        
    }
    public synchronized void rollBackward(){
       
    }
    public synchronized void PrepareBackup(){
             
    }
    public void addToolBox(ToolBoxPanel t){
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
    public Screen getDrawingPanel(){
        return this.draw;
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
    private class LocationSelector extends JMenu{
        public static final String UPCHOOSE = "Up";
        public static final String LEFTCHOOSE = "Left";
        public static final String RIGHTCHOOSE = "Right";
        public static final String DOWNCHOOSE = "Down";
        public static final String WINDOWEDCHOOSE = "Windowed";
        public static final String NONEXISTANTCHOOSE = "Remove";
        private final ToolBoxPanel t;
        private Component c = null;
        private final List<JRadioButtonMenuItem> items = new ArrayList();
        public LocationSelector(ToolBoxPanel t){
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
            if(f.getName().endsWith(".BWGF")){
                return true;
            }
            else if(f.isDirectory()){
                return true;
            }
            return false;
        }
        @Override
        public String getDescription() {
            return ".BWGF(game format)";
        }
    }
    private class ConstraintsMBBCOnly extends FileFilter{
        @Override
        public boolean accept(File f) {
            if(f.getName().endsWith(".BWGFBU")){
                return true;
            }
            else if(f.isDirectory()){
                return true;
            }
            return false;
        }
        @Override
        public String getDescription() {
            return ".BWGFBU(MapBuilder Format backup)";
        }
    }
}
