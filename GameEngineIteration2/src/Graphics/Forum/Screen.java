/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Graphics.Forum;
import Graphics.Utils.DirectUpdateThread;
import Graphics.UserActions.UserAction;
import Utils.ErrorLog;
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
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.openal.AL;
import org.lwjgl.openal.AL10;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.PixelFormat;
/*
 * @author Allazham
 * lastEdited:4/2/2013
 */
@SuppressWarnings({"serial","unchecked"})
public final class Screen implements Runnable{
    private final JPanel mainPanel = new JPanel();
    private final List<DirectUpdateThread> Threader;
    private static final int WAITINGTHREAD = 600;
    private static boolean ScreenCreated = false,running = false;
    private static final Orginizer org = new Orginizer();
    private final GraphicConditions Condition = new GraphicConditions();
    private Canvas canvas;
    private JLayeredPane pane;
    private boolean resized = false,LockedListAdding = false;
    private final ArrayList<AWTManager> UI = new ArrayList();
    private final LinkedList<GraphicsObject> removerList = new LinkedList();
    private final LinkedList<GraphicsObject> adderList = new LinkedList();
    private final List<GraphicsObject> OfficalAddList;
    private final LinkedList<GraphicsObject> officalRemoveList = new LinkedList();
    private final LinkedList<GraphicsObject> officalList = new LinkedList();
    private final ArrayList<GraphicsObject> RenderList = new ArrayList();
    private final ArrayList<UserAction> actions = new ArrayList();
    private final ActionKeys keys = new ActionKeys();
    private final LinkedList<Sound> PlaySound = new LinkedList();
    private final LinkedList<Sound> PauseSound = new LinkedList();
    private final LinkedList<Sound> stopSound = new LinkedList();
    private final LinkedList<Integer> DeleteTexture = new LinkedList();
    //private List<Sound> LoadingSounds = new ArrayList();
    @Override
    public void run(){
        try {
            if(!running){
                running = true;
                this.startGL();
                this.render();
                running = false;
            }
        } catch (LWJGLException | UnsatisfiedLinkError ex) {
            //MasterClose.CloseWithError(ex);
            ErrorLog.error(ex);
            ErrorLog.WriteErrorLog();
            if(AL.isCreated()){
                AL.destroy();
            }
            if(Mouse.isCreated()){
                Mouse.destroy();
            }
            if(Keyboard.isCreated()){
                Keyboard.destroy();
            }
            if(Display.isCreated()){
                Display.destroy();
            }
            running = false;
            System.exit(1);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Missing Files(sound or images)");
            //MasterClose.CloseWithError(ex);
            if(Display.isCreated()){
                Display.destroy();
            }
            if(AL.isCreated()){
                AL.destroy();
            }
            if(Mouse.isCreated()){
                Mouse.destroy();
            }
            if(Keyboard.isCreated()){
                Keyboard.destroy();
            }
            running = false;
            System.exit(1);
        }catch(Exception ex){
            ErrorLog.error(ex);
            ErrorLog.WriteErrorLog();
            if(Display.isCreated()){
                Display.destroy();
            }
            if(AL.isCreated()){
                AL.destroy();
            }
            if(Mouse.isCreated()){
                Mouse.destroy();
            }
            if(Keyboard.isCreated()){
                Keyboard.destroy();
            }
            running = false;
            System.exit(1);
        }
        
    }
    private void startGL() throws LWJGLException,UnsatisfiedLinkError, IOException{
        //resize detector
        this.mainPanel.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e){
                resized = true;
            }
        });
        Thread.currentThread().setName("GraphicsThread");
        //prevents internal window moving and beggining setup
        this.mainPanel.setFocusable(false);
        this.mainPanel.setVisible(true);
        this.mainPanel.setSize(800, 600); //only with jpanel
        //used in gui peices, not graphical pieces
        this.pane = new JLayeredPane();
        //canvas setup
        this.canvas = new Canvas();
        this.canvas.setIgnoreRepaint(true);
        this.canvas.setVisible(true);
        this.pane.setLayout(null);
        //layout for overall panel
        this.mainPanel.setLayout(new GridLayout(1,1));
        this.pane.add(this.canvas);
        this.mainPanel.add(this.pane);
        //setup display
        
        Display.setResizable(true);
        //Display.setDisplayMode(new DisplayMode(1000,1000));
        Display.setParent(this.canvas);
        PixelFormat pf = new PixelFormat().withDepthBits(24).withSamples(16).withSRGB(true);
        Display.create(pf);
        AL.create();
        if(AL10.alGetError() != AL10.AL_NO_ERROR){
            //JOptionPane.showMessageDialog(null, "an error has occured with openal");
            throw new LWJGLException("openal had problems initilizing error ");
        }
        
        //GL11.glEnable(GL11.GL_MULT);
        GL11.glEnable(GL11.GL_TEXTURE_2D);
        //GL11.glEnable(GL13.GL_MULTISAMPLE);
        GL11.glEnable(GL11.GL_ALPHA_TEST); // allows alpha channels or transperancy
        Mouse.create();
        Keyboard.create();
        Screen.ScreenCreated = true;
        new Thread(new ManagerOfUnSeen()).start();
    }
    public JPanel getPanel(){
        return this.mainPanel;
    }
    public void addDirectUpdateThread(DirectUpdateThread t){
        this.Threader.add(t);
    }
    public void removeDirectUpdateThread(DirectUpdateThread t){
        this.Threader.remove(t);
    }
    public void endGL(){
        //shuts down screen
        running = false;
    }
    private void render(){
        //renders only values
        int i;
        Sound s;
        char c;
        while(Screen.running){
            //
            //GL11.glEnable(GL11.GL_LINE_SMOOTH);
            //GL11.glHint(GL11.GL_LINE_SMOOTH_HINT, GL11.GL_NICEST);
           // GL11.glEnable(GL11.GL_POLYGON_SMOOTH);
            
            try{
                //stops useless sounds
                while(this.stopSound.size() > 0){
                    s = this.stopSound.removeFirst();
                    s.Stop();
                }
                while(this.PauseSound.size()>0){
                    s = this.PauseSound.removeFirst();
                    s.Pause();
                }
                while(this.PlaySound.size()>0){
                    s = this.PlaySound.removeFirst();
                    s.Play();
                }
                s = null;
                while(!this.DeleteTexture.isEmpty()){
                    GL11.glDeleteTextures(this.DeleteTexture.removeFirst());
                }
                while(Keyboard.next()){
                    if(Keyboard.getEventKeyState()){
                        i = Keyboard.getEventKey();
                        c = Keyboard.getEventCharacter();
                        if(Character.valueOf(c).toString().trim().length() != 0 || i == Keyboard.KEY_SPACE){
                            this.keys.addCharButton(c);
                        }
                        this.keys.addKeyboardButton(i);
                    }
                }
                while(Mouse.next()){
                    if(Mouse.getEventButton() != -1){
                        this.keys.addMouseButton(Mouse.getEventButton());
                    }
                }
                for(i = 0; i < this.actions.size();i++){
                    if(!this.actions.get(i).isDisabled()){
                        this.actions.get(i).Action(keys);
                    }
                }
                //clearing conditions for new render
                this.keys.clear();
                //resize detector
                if(this.resized){
                    this.ResizeEventGL();
                }
                if(this.adderList.size() > 0){
                    if(!LockedListAdding){
                        LockedListAdding = true;
                            this.RenderList.addAll(this.adderList);
                            this.adderList.clear();
                                    
                            Collections.sort(this.RenderList, org);
                        LockedListAdding = false;
                    }
                }
                while(this.removerList.size() > 0){
                    this.RenderList.remove(this.removerList.removeFirst());
                }
                for(i = 0; i < this.Threader.size();i++){
                    this.Threader.get(i).Update();
                }
                this.Condition.endOfRender();
                for(i = 0; i < this.RenderList.size();i++){
                    if(!this.RenderList.get(i).isDisabled()){
                        this.RenderList.get(i).checkCondations(this.Condition);
                        this.RenderList.get(i).render();
                    }
                    else{
                        this.OfficalAddList.add(this.RenderList.remove(i));
                        i--;
                    }
                }
                //update and flush to screen
                //GL11.glHint(GL11.GL_PERSPECTIVE_CORRECTION_HINT, GL11.GL_NICEST);
                GL11.glFlush();
                Display.update();
                Display.sync(60);
            }
            catch(LWJGLException ex){
                ErrorLog.error(ex);
            }
        }
        AL.destroy();
        Mouse.destroy();
        Keyboard.destroy();
        Display.destroy();
        this.mainPanel.remove(this.pane);
        Screen.ScreenCreated = false;
    }
    public void addUserAction(UserAction ua){
        this.actions.add(ua);
        ua.create();
        if(Screen.isScreenCreated()){
            this.canvas.requestFocus();
        }
    }
    public void removeUserAction(UserAction ua){
        this.actions.remove(ua);
        ua.dispose();
    }
    public void addAwtUI(AWTManager ph){
        int i;
        ph.SetFramePoints(this.mainPanel.getWidth(), this.mainPanel.getHeight());
        this.UI.add(ph);
        for(i = 0; i < ph.GUIComp.size();i++){
            this.pane.add(ph.GUIComp.get(i),new Integer(ph.getFrameNumber()));
            ph.GUIComp.get(i).validate();
            this.mainPanel.validate();
        }
        this.pane.requestFocus();
    }
    public void removeAllAwtUI(){
        int x,z;
        for(x = 0; x < this.UI.size();x++){
            for(z = 0; z < this.UI.get(x).GUIComp.size();z++){
                this.pane.remove(this.UI.get(x).GUIComp.get(z));
            }
        }
        this.canvas.requestFocus();
        this.UI.removeAll(this.UI);
        this.mainPanel.validate();
    }
    public void removeAwtUI(AWTManager gui){
        int i;
        for(i = 0; i < gui.GUIComp.size();i++){
            this.pane.remove(gui.GUIComp.get(i));
        }
        this.UI.remove(gui);
    }
    private void ResizeEventGL() throws LWJGLException{
        int i;
        GL11.glMatrixMode(GL11.GL_PROJECTION);
        this.pane.setSize(this.mainPanel.getWidth(),this.mainPanel.getHeight());
        this.pane.validate();
        this.canvas.setSize(this.mainPanel.getWidth(),this.mainPanel.getHeight());
        this.canvas.validate();
        for(i = 0; i < this.UI.size();i++){
            this.UI.get(i).SetFramePoints(this.mainPanel.getWidth(), this.mainPanel.getHeight());
        }
        this.Condition.setScreenWidth(this.canvas.getWidth());
        this.Condition.setScreenHeight(this.canvas.getHeight());
        this.mainPanel.validate();
        GL11.glViewport(0, 0,this.canvas.getWidth(),this.canvas.getHeight());
        this.resized = false;
        GL11.glMatrixMode(GL11.GL_MODELVIEW);
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
        for(x = 0; x < this.UI.size();x++){
            for(z = 0; z < this.UI.get(x).GUIComp.size();z++){
                this.pane.add(this.UI.get(x).GUIComp.get(z),new Integer(this.UI.get(x).getFrameNumber()));
                this.UI.get(x).GUIComp.get(z).validate();
            }
            this.mainPanel.validate();
        }
    }
    public void addGraphicObject(GraphicsObject g){
        synchronized(OfficalAddList){
            this.OfficalAddList.add(g);
        }
    }
    public void addGraphicObject(List<GraphicsObject> g){
        synchronized(OfficalAddList){
           this.OfficalAddList.addAll(g);
        }
    }
    public void removeGraphicsObject(GraphicsObject g){
        this.removerList.add(g);
        officalRemoveList.add(g);
        g.disposeGraphics();
    }
    public void removeGraphicsObject(List<GraphicsObject> g){
        int i;
        this.removerList.addAll(g);
        officalRemoveList.addAll(g);
        for(i = 0; i < g.size();i++){
            g.get(i).disposeGraphics();
        }
        
    }
    public static boolean isScreenCreated(){
        return Screen.ScreenCreated;
    }
    private Screen(){
        this.OfficalAddList = Collections.synchronizedList(new ArrayList());
        this.Threader = Collections.synchronizedList(new ArrayList());
    }
    public static Screen getInstance() {
        return ScreenHolder.INSTANCE;
    }
    private static class ScreenHolder {
        private static final Screen INSTANCE = new Screen();
    }
    public int getWidth(){
        return this.mainPanel.getWidth();
    }
    public int getHeight(){
        return this.mainPanel.getHeight();
    }
    private class ManagerOfUnSeen implements Runnable{
        @Override
        public void run() {
            GraphicsObject g;
            int i;
            try {
                Thread.sleep(250);
            } catch (InterruptedException ex) {
                ErrorLog.error(ex);
            }
            while(Screen.isScreenCreated()){
                if(OfficalAddList.size() > 0){
                    synchronized(officalList){
                        
                        officalList.addAll(OfficalAddList);
                        OfficalAddList.clear();
                    }
                }
                while(officalRemoveList.size() > 0){
                    officalList.remove(officalRemoveList.removeFirst());
                }
                if(!LockedListAdding){
                    LockedListAdding = true;
                    for(i = 0; i < officalList.size();i++){
                        if(!officalList.get(i).isDisabled()){
                            adderList.add(officalList.remove(i));
                            i--;
                        }
                    }
                    LockedListAdding = false;
                }
                try {
                    Thread.sleep(WAITINGTHREAD);
                } catch (InterruptedException ex) {
                    ErrorLog.error(ex);
                }
            }
        }
    }
    public void addTextureIdToDelete(int delete){
        this.DeleteTexture.add(delete);
    }
    private static class Orginizer implements Comparator<GraphicsObject>{
        @Override
        public int compare(GraphicsObject o1, GraphicsObject o2) {
            return(o1.getPriority()<o2.getPriority() ? -1 : (o1.getPriority()==o2.getPriority() ? 0 : 1));
        }
    }
}