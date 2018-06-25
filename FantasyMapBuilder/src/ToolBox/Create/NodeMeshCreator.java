/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ToolBox.Create;

import Forum.DrawingPanel;
import Forum.GraphicConditions;
import Forum.UndoManager;
import GraphicalObjects.NodeMesh;
import GraphicalObjects.Node;
import ToolBox.Tool;
import ToolBox.ToolGraphic;
import Utils.Config;
import java.awt.Cursor;
import java.awt.Graphics2D;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Allazham
 */
public class NodeMeshCreator extends ToolGraphic implements Tool,MouseListener{
    private RunObject runable = new RunObject();
    private boolean running = false;
    private DrawingPanel p;
    private NodeMesh m;
    public NodeMeshCreator(){
        super(Config.MeshImage);
        super.setToolTip("create a Node mesh");
    }
    
    @Override
    public void Setup(DrawingPanel p) {
       this.p = p; 
    }

    @Override
    public void renderMap(Graphics2D g, GraphicConditions c) {
        
    }

    @Override
    public void Destoy() {
        
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        
    }

    @Override
    public synchronized void mousePressed(MouseEvent e) {
        if(runable.isMoving()){
            return;
        }
        List<Node> nodes = new ArrayList();
        m = new NodeMesh();
        m.doNotUpate(true);
        nodes.add(new Node(-100000,-100000));
        nodes.add(new Node(-100010,-100000));
        nodes.add(new Node(-100010,-100010));
        nodes.add(new Node(-100000,-100010));
        m.addNodes(nodes, true);
        m.doNotUpate(false);
        if(p!= null){
            
            this.p.addGraphicsObject(m);
            this.p.setTargetGraphic(m);
            this.p.repaint();
            this.running = true;
            new Thread(runable).start();
        }
        
    }

    @Override
    public synchronized void mouseReleased(MouseEvent e) {
        
        this.running = false;
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        
    }

    @Override
    public void mouseExited(MouseEvent e) {
        
    }

    @Override
    public Cursor getCursorInstance(Point p) {
        return Cursor.getDefaultCursor();
    }
    private class RunObject implements Runnable{
        private boolean Moving = false;
        @Override
        public synchronized void run(){
            Moving = true;
            Point pp;
                int[] loc;
                while(running){
                    pp = MouseInfo.getPointerInfo().getLocation();
                    loc = p.convertAbsoluteCordstoScreen(pp);
                    m.setImageX(loc[0]);
                    m.setImageY(loc[1]);
                    m.repaint();
                }
                UndoManager.Instance.add(p);
                m.repaint();
                Moving = false;
        }
        public boolean isMoving(){
            return Moving;
        }
    }
}
