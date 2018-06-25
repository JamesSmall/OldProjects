/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ToolBox.NodeEdit;

import Forum.DrawingPanel;
import Forum.GraphicConditions;
import Forum.UndoManager;
import GraphicalObjects.GraphicsObject;
import GraphicalObjects.Node;
import GraphicalObjects.NodeList;
import ToolBox.GrabTool;
import ToolBox.Tool;
import ToolBox.ToolGraphic;
import Utils.Config;
import java.awt.Cursor;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 *
 * @author Allazham
 */
public class NodeDestroy extends ToolGraphic implements GrabTool{
    public NodeDestroy(){
        super(Config.NodeDestroyer);
        super.setToolTip("Remove Nodes");
    }
    @Override
    public NodeDestroyer getToolInstance() {
        return new NodeDestroyer();
    }
    public class NodeDestroyer implements Tool, MouseListener{
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
            
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            int[] loc;
            GraphicsObject g = this.p.getTargetGraphic();
            if(g instanceof NodeList){
                this.o = (NodeList) g;
                loc = p.convertMouseCordsToScreen(e.getX(), e.getY());
                this.target = this.o.getNode(loc[0],loc[1], p.convertDistanceScale(5));
                if(target != null){
                    this.o.removeNode(target);
                    UndoManager.Instance.add(p);
                }
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
    }
}
