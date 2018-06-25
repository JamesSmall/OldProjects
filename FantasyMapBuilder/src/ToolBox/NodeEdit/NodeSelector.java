/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ToolBox.NodeEdit;

import Forum.DrawingPanel;
import Forum.GraphicConditions;
import Forum.UndoManager;
import GraphicalObjects.GraphicsObject;
import GraphicalObjects.NodeList;
import GraphicalObjects.Node;
import ToolBox.GrabTool;
import ToolBox.Tool;
import ToolBox.ToolGraphic;
import Utils.Config;
import java.awt.Cursor;
import java.awt.Graphics2D;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Allazham
 */
public class NodeSelector extends ToolGraphic implements GrabTool{
    private static final int MOVEX = 9,MOVEY = 63;
    public NodeSelector(){
        super(Config.NodePointer);
        super.setToolTip("Move Node");
    }
    @Override
    public NodeSelectorTool getToolInstance() {
        return new NodeSelectorTool();
    }
    public class NodeSelectorTool implements Tool, MouseListener{
        private DrawingPanel p;
        private NodeList o;
        private Node target;
        boolean currMoving = false;
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
        public void mousePressed(MouseEvent e) {
            int[] loc;
            GraphicsObject g = this.p.getTargetGraphic();
            if(g instanceof NodeList){
                this.o = (NodeList) g;
                loc = p.convertMouseCordsToScreen(e.getX(), e.getY());
                this.target = this.o.getNode(loc[0],loc[1], p.convertDistanceScale(5));
                
                if(target == null){
                    return;
                }
                currMoving = true;
                new RunPressEvent().start();
            }
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            currMoving = false;
            if(o != null){
                o.NodeUpdated();
                o.repaint();
                o = null;
                try {
                    Thread.sleep(100);
                } catch (InterruptedException ex) {
                    Logger.getLogger(NodeSelector.class.getName()).log(Level.SEVERE, null, ex);
                }
                UndoManager.Instance.add(p);
            }
        }

        @Override
        public void mouseEntered(MouseEvent e) {
            //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            
        }

        @Override
        public void mouseExited(MouseEvent e) {
            //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public Cursor getCursorInstance(Point p) {
            return Cursor.getDefaultCursor();
        }
        private class RunPressEvent extends Thread{
            @Override
            public void run(){
                Point pp;
                int[] loc;
                while(currMoving){
                    pp = MouseInfo.getPointerInfo().getLocation();
                    loc = new int[]{(int)(pp.getX()-p.getX()-p.getFrameMain().getX()-MOVEX),(int)(pp.getY()-p.getY()-p.getFrameMain().getY()-MOVEY)};
                    loc = p.convertMouseCordsToScreen(loc[0],loc[1]);
                    //this.target = this.o.getNode(loc[0],loc[1], 5);
                    target.setImageX(loc[0]);
                    target.setImageY(loc[1]);
                    if(o!= null){
                        o.repaint();
                    }
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(NodeSelector.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        }
    }
}
