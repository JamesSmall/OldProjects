/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Forum;

import GraphicalObjects.Background;
import GraphicalObjects.ComplexBackground;
import GraphicalObjects.GraphicsObject;
import GraphicalObjects.Line;
import GraphicalObjects.NodeMesh;
import GraphicalObjects.Node;
import GraphicalObjects.PointerImage;
import ToolBox.Tool;
import Utils.Config;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import javax.swing.AbstractAction;
import javax.swing.JComponent;
import javax.swing.JLayeredPane;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;

/**
 *
 * @author Allazham
 */
public final class DrawingPanel extends JLayeredPane{
    public static final int OutOfMemoryError = 1;
    private PointerImage upleftP,upP,uprightP,rightP,downrightP,downP,downleftP,leftP,CenterPoint;
    private FrameMain MasterControl;
    private UUID targetsUUID = null;
    private GraphicsObject target = null;
    private GraphicConditions gc = GraphicConditions.Instance;
    private List<GraphicsObject> graphicslist = new ArrayList();
    private Tool t = null;
    private int x = 0, y = 0,changeScale = 1;
    private double scale = 1;
    private Background bg;
    
    public DrawingPanel(){
        this.bg = new ComplexBackground();
        bg.setImage(Config.WaterTexture);
        bg.setGraphicCondition(gc);
        super.add(this.bg);
        //this.PrepareBackup();
        this.setBackground(Color.blue);
        CenterPoint = new PointerImage(Config.PointerCenter);
        upleftP = new PointerImage(Config.PointerLeftUp);
        upP = new PointerImage(Config.PointerUp);
        uprightP = new PointerImage(Config.PointerRightUp);
        rightP = new PointerImage(Config.PointerRight);
        downrightP = new PointerImage(Config.PointerRightDown);
        downP = new PointerImage(Config.PointerDown);
        downleftP = new PointerImage(Config.PointerLeftDown);
        leftP = new PointerImage(Config.PointerLeft);
        super.addMouseWheelListener(new Mover());
        
        
        super.setFocusable(true);
        //key listeners(which i hate with all my hate right now)
        super.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("UP"),
                            "up");
        super.getActionMap().put("up",
                             new ForwardKey());
        
        super.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("DOWN"),
                            "down");
        super.getActionMap().put("down",
                             new BackwardsKey());
        
        super.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("LEFT"),
                            "left");
        super.getActionMap().put("left",
                             new LeftKey());
        
        super.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("RIGHT"),
                            "right");
        super.getActionMap().put("right",
                             new RightKey());
        
    }
    public void setScreenX(int x){
        this.x = x;
    }
    public void setFrameMain(FrameMain fm){
        this.MasterControl = fm;
    }
    public FrameMain getFrameMain(){
        return this.MasterControl;
    }
    public void setScreenY(int y){
        this.y = y;
    }
    public int getScreenX(){
        return this.x;
    }
    public int getScreenY(){
        return this.y;
    }
    private void setTarget(){
        int i;
        for(i = 0; i < this.graphicslist.size();i++){
            if(this.graphicslist.get(i).getUID() == this.targetsUUID){
                this.target = this.graphicslist.get(i);
                return;
            }
        }
        this.target = null;
    }
    @Override
    public void paint(Graphics g){
        int PointMoveX = 0, PointMoveY = 0;
        int Adjuster = 10;
        double[][] CirclePoints;this.gc.setConditions(x, y, this.getWidth(), this.getHeight(),scale);
        this.gc.setConditions(x, y, this.getWidth(), this.getHeight(),scale);
        super.paint(g);
        if(this.target != null){
            super.paint(g);
            CirclePoints = this.target.getSurroundingPoints();
            this.upleftP.RenderMap(g, this.gc.getStartingPointXUnscaled(CirclePoints[0][0]-Adjuster, 8)+PointMoveX, this.gc.getStartingPointYUnscaled(CirclePoints[0][1]-Adjuster, 8)+PointMoveY);
            this.upP.RenderMap(g, this.gc.getStartingPointXUnscaled(CirclePoints[1][0], 8)+PointMoveX, this.gc.getStartingPointYUnscaled(CirclePoints[1][1]-Adjuster, 8)+PointMoveY);
            this.uprightP.RenderMap(g, this.gc.getStartingPointXUnscaled(CirclePoints[2][0]+Adjuster, 6)+PointMoveX, this.gc.getStartingPointYUnscaled(CirclePoints[2][1]-Adjuster, 8)+PointMoveY);
            this.rightP.RenderMap(g, this.gc.getStartingPointXUnscaled(CirclePoints[3][0]+Adjuster, 8)+PointMoveX, this.gc.getStartingPointYUnscaled(CirclePoints[3][1], 8)+PointMoveY);
            this.downrightP.RenderMap(g, this.gc.getStartingPointXUnscaled(CirclePoints[4][0]+Adjuster, 8)+PointMoveX, this.gc.getStartingPointYUnscaled(CirclePoints[4][1]+Adjuster, 8)+PointMoveY);
            this.downP.RenderMap(g, this.gc.getStartingPointXUnscaled(CirclePoints[5][0], 8)+PointMoveX, this.gc.getStartingPointYUnscaled(CirclePoints[5][1]+Adjuster, 8)+PointMoveY);
            this.downleftP.RenderMap(g, this.gc.getStartingPointXUnscaled(CirclePoints[6][0]-Adjuster, 8)+PointMoveX, this.gc.getStartingPointYUnscaled(CirclePoints[6][1]+Adjuster, 8)+PointMoveY);
            this.leftP.RenderMap(g, this.gc.getStartingPointXUnscaled(CirclePoints[7][0]-Adjuster, 8)+PointMoveX, this.gc.getStartingPointYUnscaled(CirclePoints[7][1], 8)+PointMoveY);
            this.CenterPoint.RenderMap(g, this.gc.getStartingPointXUnscaled(this.target.getImageX(), 8)+PointMoveX, this.gc.getStartingPointYUnscaled(this.target.getImageY(), 8)+PointMoveY);
        }
        //Draw((Graphics2D) g);
    }
    public void Draw(Graphics2D g){
        final RenderingHints RH1 = new RenderingHints(RenderingHints.KEY_RENDERING,
             RenderingHints.VALUE_RENDER_SPEED);
        final RenderingHints RH2 = new RenderingHints(RenderingHints.KEY_COLOR_RENDERING,
             RenderingHints.VALUE_COLOR_RENDER_QUALITY);
        final RenderingHints RH3 = new RenderingHints(RenderingHints.KEY_INTERPOLATION,
             RenderingHints.VALUE_INTERPOLATION_NEAREST_NEIGHBOR);
        g.addRenderingHints(RH1);
        g.addRenderingHints(RH2);
        g.addRenderingHints(RH3);
        this.gc.setConditions(x, y, this.getWidth(), this.getHeight(),scale);
        super.paint(g);
        int i;
        if(this.bg != null){
            bg.renderMap(g, gc);
        }
        for(i = 0; i < this.graphicslist.size();i++){
            this.graphicslist.get(i).renderMap(g, gc);
        }
    }
    public double convertDistanceScale(double scale){
        return scale*this.gc.getScale();
    }
    public void setTargetGraphic(GraphicsObject g){
        this.target = g;
        if(this.target != null){
            this.targetsUUID = g.getUID();
        }
        else{
            this.targetsUUID = null;
        }
    }
    public GraphicsObject getTargetGraphic(){
        return this.target;
    }
    public void addGraphicsObject(GraphicsObject g){
        this.graphicslist.add(g);
        this.gc.setConditions(x, y, this.getWidth(), this.getHeight(),scale);
        g.setGraphicCondition(gc);
        super.add(g);
        this.reorginizeList();
    }
    public void addGraphicsObjects(List<GraphicsObject> g){
        int i;
        this.graphicslist.addAll(g);
        for(i = 0; i < g.size();i++){
            this.gc.setConditions(x, y, this.getWidth(), this.getHeight(),scale);
            g.get(i).setGraphicCondition(gc);
            super.add(g.get(i));
            this.reorginizeList();
        }
        this.reorginizeList();
    }
    public void removeGraphicsObject(GraphicsObject g){
        if(this.target == g){
            this.target = null;
        }
        this.graphicslist.remove(g);
        super.remove(g);
        this.reorginizeList();
    }
    public void removeGraphicsObject(List<GraphicsObject> g){
        int i;
        this.graphicslist.removeAll(g);
        for(i = 0; i < g.size();i++){
            if(this.target == g.get(i)){
                this.target = null;
            }
            super.remove(g.get(i));
        }
        super.validate();
        this.reorginizeList();
    }
    public void pushBack(GraphicsObject g){
        int i = this.graphicslist.indexOf(g);
        if(i != -1 && i != 0){
            this.graphicslist.remove(g);
            this.graphicslist.add(i-1,g);
            //this.PrepareBackup();
        }
        this.reorginizeList();
        super.validate();
        super.repaint();
    }
    public void reorginizeList(){
        int i,j = 0;
        for(i = this.graphicslist.size()-1;i > -1; i--){
            super.setComponentZOrder(this.graphicslist.get(i), JLayeredPane.DEFAULT_LAYER+j);
            j++;
        }
        if(this.bg != null){
            super.setComponentZOrder(this.bg, JLayeredPane.DEFAULT_LAYER+j);
        }
    }
    public void pushForward(GraphicsObject g){
        int i = this.graphicslist.indexOf(g);
        if(i != -1 && i != this.graphicslist.size()-1){
            this.graphicslist.remove(g);
            this.graphicslist.add(i+1,g);
            //this.PrepareBackup();
        }
        this.reorginizeList();
        super.repaint();
    }
    public void toFront(GraphicsObject g){
        int i = this.graphicslist.indexOf(g);
        if(i != -1){
            this.graphicslist.remove(g);
            this.graphicslist.add(g);
            //this.PrepareBackup();
        }
        this.reorginizeList();
        super.repaint();
    }
    public void toBack(GraphicsObject g){
        int i = this.graphicslist.indexOf(g);
        if(i != -1){
            this.graphicslist.remove(g);
            this.graphicslist.add(0,g);
            //this.PrepareBackup();
        }
        this.reorginizeList();
        super.repaint();
    }
    public GraphicsObject getGraphicsObject(int x, int y){
       int i;
       for(i = this.graphicslist.size()-1; -1 < i;i--){
           if(this.graphicslist.get(i).isInsideHitBox(gc.MouseToScreenCordsX(x),gc.MouseToScreenCordsY(y))){
                return this.graphicslist.get(i);
           }
       }
       return null;
    }
    
    public int[] convertAbsoluteCordstoScreen(Point p){
        SwingUtilities.convertPointFromScreen(p, this);
        return convertMouseCordsToScreen(p.getX(),p.getY());
    }
    public int[] convertMouseCordsToScreen(double x, double y){
        return new int[]{gc.MouseToScreenCordsX(x),gc.MouseToScreenCordsY(y)};
    }
    public List<GraphicsObject> getGraphicsList(){
        return this.graphicslist;
    }
    public List<NodeMesh> getMeshLists(){
        int i;
        List<NodeMesh> mesh = new ArrayList();
        for(i = 0; i < this.graphicslist.size();i++){
            if(this.graphicslist.get(i) instanceof NodeMesh){
                mesh.add((NodeMesh) this.graphicslist.get(i));
            }
        }
        return mesh;
    }
    public List<Line> getLineLists(){
        int i;
        List<Line> mesh = new ArrayList();
        for(i = 0; i < this.graphicslist.size();i++){
            if(this.graphicslist.get(i) instanceof Line){
                mesh.add((Line) this.graphicslist.get(i));
            }
        }
        return mesh;
    }
    public List<Node> getNodeLists(){
        int i;
        List<Node> mesh = new ArrayList();
        for(i = 0; i < this.graphicslist.size();i++){
            if(this.graphicslist.get(i) instanceof Node){
                mesh.add((Node) this.graphicslist.get(i));
            }
        }
        return mesh;
    }
    public void setDrawing(GraphicsObject[] g){
        int i;
        for(i = 0; i < g.length;i++){
            this.addGraphicsObject(g[i]);
        }
    }
    
    public void setBackground(Background bg){
        if(this.bg != null){
            super.remove(bg);
        }
        this.bg = bg;
        if(bg != null){
            super.add(bg);
        }
    }
    public Background getImageBackground(){
        return this.bg;
    }
    public void setTool(Tool t){
        if(this.t != null){
            if(this.t instanceof MouseListener){
                super.removeMouseListener((MouseListener) this.t);
            }
            this.t.Destoy();
        }
        if(t == null){
            super.setCursor(Cursor.getDefaultCursor());
        }
        else if(t instanceof MouseListener){
            if(t instanceof MouseListener){
                super.addMouseListener((MouseListener) t);
            }
            t.Setup(this);
            super.setCursor(t.getCursorInstance(super.getLocation()));
            this.t = t;
        }
    }
    private class ForwardKey extends AbstractAction{
        @Override
        public void actionPerformed(ActionEvent e) {
            y -= changeScale;
            repaint();
        }
    }
    private class BackwardsKey extends AbstractAction{
        @Override
        public void actionPerformed(ActionEvent e) {
            y += changeScale;
            repaint();
        }
    }
    private class LeftKey extends AbstractAction{
        @Override
        public void actionPerformed(ActionEvent e) {
            x -= changeScale;
            repaint();
        }
    }
    private class RightKey extends AbstractAction{
        @Override
        public void actionPerformed(ActionEvent e) {
            x += changeScale;
            repaint();
        }
    }
    private class Mover implements MouseWheelListener{
        @Override
        public void mouseWheelMoved(MouseWheelEvent e) {
            double notches = e.getPreciseWheelRotation()/50;
            scale += notches;
            if(scale <= .5){
                scale = .5;
            }
            repaint();
        }
    }
}
