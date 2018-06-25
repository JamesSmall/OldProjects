/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Graphics;
import Holder.MasterLoad;
import Holder.Sound;
import java.awt.Canvas;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.WindowConstants;
import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.openal.AL;
import org.lwjgl.openal.AL10;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;
/*
 * @author Allazham
 * lastEdited:4/2/2013
 */
@SuppressWarnings({"serial","unchecked"})
public class Screen extends JFrame implements Runnable{
    private static boolean ScreenCreated = false;
    private static Orginizer org = new Orginizer();
    private GraphicConditions Condition = GraphicConditions.getInstance();
    private Canvas canvas;
    private JLayeredPane pane;
    private boolean running = false, resized = false;
    private List<GUIManager> GUI = new ArrayList();
    private List<GraphicsObject> RenderList = new ArrayList();
    private List<UserActionKeyboard> actionsKeyboard = new ArrayList();
    private List<UserActionMouse> actionsMouse = new ArrayList();
    private List<NonButtonUserAction> ActionNonButton = new ArrayList();
    private List<Sound> PlaySound = new LinkedList();
    private List<Sound> PauseSound = new LinkedList();
    private List<Sound> stopSound = new LinkedList();
    //private List<Sound> LoadingSounds = new ArrayList();
    @Override
    public void run(){
        try {   
            this.startGL();
            this.render();
        } catch (LWJGLException | UnsatisfiedLinkError ex) {
            MasterClose.CloseWithError(ex);
            System.exit(1);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Missing Files(sound or images)");
            MasterClose.CloseWithError(ex);
            System.exit(1);
        }
    }
    private void startGL() throws LWJGLException,UnsatisfiedLinkError, IOException{
        this.addWindowStateListener(new WindowAdapter() {
            @Override
            public void windowStateChanged(WindowEvent e){
                resized = true;
            }
            @Override
            public void windowDeiconified(WindowEvent e) {
                resized = true;
            }
            @Override
            public void windowDeactivated(WindowEvent e) {
                resized = true;
            }  
        });
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                MasterClose.CloseAll();
            }
            
        });
        this.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e){
                resized = true;
            }
        });
        this.setFocusable(false);
        this.running = true;
        this.setVisible(true);
        this.setSize(800, 600);
        //canvas
        this.pane = new JLayeredPane();
        this.canvas = new Canvas();
        this.pane.setOpaque(true);
        this.canvas.setIgnoreRepaint(true);
        this.canvas.setBackground(new java.awt.Color(0,0,0,0));
        this.canvas.setForeground(new java.awt.Color(0,0,0,0));
        this.canvas.setVisible(true);
        this.pane.setLayout(null);
        this.pane.setBackground(new java.awt.Color(0,0,0,0));
        this.setLayout(new GridLayout(1,1));
        this.pane.add(this.canvas);
        this.add(this.pane);
        Display.setResizable(true);
        Display.setParent(this.canvas);
        Display.create();
        AL.create();
        if(AL10.alGetError() != AL10.AL_NO_ERROR){
            JOptionPane.showMessageDialog(null, "an error has occured with openal");
        }
        Mouse.create();
        Keyboard.create();
        MasterLoad.load();
        Screen.ScreenCreated = true;
    }
    public void endGL(){
        running = false;
    }
    private void render() throws LWJGLException{
        int i;
        Sound s;
        while(this.running){
            if(this.resized){
               this.ResizeEventGL();
            }
            while(this.stopSound.size() > 0){
                s = this.stopSound.remove(0);
                s.Stop();
            }
            while(this.PauseSound.size()>0){
                s = this.PauseSound.remove(0);
                s.Pause();
            }
            while(this.PlaySound.size()>0){
                s = this.PlaySound.remove(0);
                s.Play();
            }
            s = null;
            while(Keyboard.next()){
                for(i = 0; i < this.actionsKeyboard.size();i++){
                    this.actionsKeyboard.get(i).action(Keyboard.getEventKey());
                }
            }
            while(Mouse.next()){
                for(i = 0; i < this.actionsMouse.size();i++){
                    this.actionsMouse.get(i).action(Mouse.getEventButton());
                }
            }
            for(i = 0; i < this.ActionNonButton.size(); i ++){
                this.ActionNonButton.get(i).action();
            }
            GL11.glClear(GL11.GL_COLOR_BUFFER_BIT);
            for(i = 0; i < this.RenderList.size();i++){
                if(!this.RenderList.get(i).getDisabled()){
                    this.RenderList.get(i).checkCondations(this.Condition);
                    this.RenderList.get(i).render();
                }
            }
            Display.update();
            GL11.glFlush();
            Display.sync(60);
            this.Condition.endOfRender();
        }
        AL.destroy();
        Mouse.destroy();
        Keyboard.destroy();
        Display.destroy();
        this.remove(this.pane);
        Screen.ScreenCreated = false;
    }
    public void addGUI(GUIManager ph){
        int i;
        ph.SetFramePoints(this.getWidth(), this.getHeight());
        this.GUI.add(ph);
        for(i = 0; i < ph.GUIComp.size();i++){
            this.pane.add(ph.GUIComp.get(i),new Integer(ph.getFrameNumber()));
            ph.GUIComp.get(i).validate();
            this.validate();
        }
        this.pane.requestFocus();
    }
    public void removeAllGUI(){
        int x,z;
        for(x = 0; x < this.GUI.size();x++){
            for(z = 0; z < this.GUI.get(x).GUIComp.size();z++){
                this.pane.remove(this.GUI.get(x).GUIComp.get(z));
            }
        }
        this.canvas.requestFocus();
        this.GUI.removeAll(this.GUI);
        this.validate();
    }
    public void removeGUI(GUIManager gui){
        int i;
        for(i = 0; i < gui.GUIComp.size();i++){
            this.pane.remove(gui.GUIComp.get(i));
        }
        this.GUI.remove(gui);
    }
    private void ResizeEventGL() throws LWJGLException{
        int i;
        this.pane.setSize(this.getWidth(),this.getHeight());
        this.pane.validate();
        this.canvas.setSize(this.getWidth(),this.getHeight());
        this.canvas.validate();
        for(i = 0; i < this.GUI.size();i++){
            this.GUI.get(i).SetFramePoints(this.getWidth(), this.getHeight());
        }
        this.Condition.setInternal2DScreenWidth(this.canvas.getWidth());
        this.Condition.setInternal2DScreenHeight(this.canvas.getHeight());
        this.validate();
        GL11.glViewport(0, 0,super.getWidth(),super.getHeight());
        this.resized = false;
    }
    public void addSoundToPause(Sound s){
        this.PauseSound.add(s);
    }
    public void addSoundToPlay(Sound s){
        this.PlaySound.add(s);
    }
    public void addSoundToStop(Sound s){
        this.stopSound.add(s);
    }
    public void ResetUI(){
        int x,z;
        Component[] c = this.pane.getComponents();
        for(x = 0; x < c.length; x++){
            if(!(c[x] == this.canvas)){
                this.pane.remove(c[x]);
            }
        }
        for(x = 0; x < this.GUI.size();x++){
            for(z = 0; z < this.GUI.get(x).GUIComp.size();z++){
                this.pane.add(this.GUI.get(x).GUIComp.get(z),new Integer(this.GUI.get(x).getFrameNumber()));
                this.GUI.get(x).GUIComp.get(z).validate();
            }
            this.validate();
        }
    }
    public void addGraphicsObject(GraphicsObject g){
        this.RenderList.add(g);
        Collections.sort(this.RenderList,Screen.org);
    }
    public void addGraphicObject(List<GraphicsObject> g){
        this.RenderList.addAll(g);
        Collections.sort(this.RenderList,Screen.org);
    }
    public void removeGraphicsObject(GraphicsObject g){
        this.RenderList.remove(g);
    }
    public void removeGraphicsObject(List<GraphicsObject> g){
        this.RenderList.removeAll(g);
    }
    public void addKeyBoardAction(UserActionKeyboard k){
        this.actionsKeyboard.add(k);
    }
    public void addMouseAction(UserActionMouse m){
        this.actionsMouse.add(m);
    }
    public void addNonButtonAction(NonButtonUserAction n){
        this.ActionNonButton.add(n);
    }
    public void removeKeyBoardAction(UserActionKeyboard k){
        this.actionsKeyboard.remove(k);
    }
    public void removeMouseAction(UserActionMouse m){
        this.actionsMouse.remove(m);
    }
    public void removeNonButtonAction(NonButtonUserAction n){
        this.ActionNonButton.remove(n);
    }
    public static boolean isScreenCreated(){
        return Screen.ScreenCreated;
    }
    private Screen(){
        this.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
    }
    public static Screen getInstance() {
        return ScreenHolder.INSTANCE;
    }
    private static class ScreenHolder {
        private static final Screen INSTANCE = new Screen();
    }
    private static class Orginizer implements Comparator<GraphicsObject>{
        @Override
        public int compare(GraphicsObject o1, GraphicsObject o2) {
            return(o1.getPriority()>o2.getPriority() ? -1 : (o1.getPriority()==o2.getPriority() ? 0 : 1));
        }
    }
}