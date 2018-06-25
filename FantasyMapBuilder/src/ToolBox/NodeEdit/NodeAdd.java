/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ToolBox.NodeEdit;

import Forum.DrawingPanel;
import Forum.GraphicConditions;
import Forum.UndoManager;
import GraphicalObjects.GraphicsObject;
import GraphicalObjects.Line;
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
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Allazham
 */
public class NodeAdd extends ToolGraphic implements GrabTool{
    public NodeAdd(){
        super(Config.NodeAdder);
        super.setToolTip("Add Node");
    }
    @Override
    public NodeAdderTool getToolInstance() {
       return new NodeAdderTool();
    }
    public class NodeAdderTool implements Tool,MouseListener{
        private NodeList l = null;
        private DrawingPanel p;
        @Override
        public void Setup(DrawingPanel p) {
            this.p = p;
        }

        @Override
        public void renderMap(Graphics2D g, GraphicConditions c) {
            //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public void Destoy() {
            //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            
        }

        @Override
        public void mousePressed(MouseEvent e) {
            
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            GraphicsObject g;
            Node L,R;
            Point loc = e.getPoint();
            int[] loca;
            Line line;
            g = this.p.getTargetGraphic();
            if(g instanceof NodeList){
                this.l = (NodeList) g;
                loca = p.convertMouseCordsToScreen(loc.getX(),loc.getY());
                line = l.getClosesLineInArea(loca[0],loca[1],p.convertDistanceScale(5));
                if(line != null){
                    L = line.getBackNode();
                    R = line.getFrontNode();
                    this.l.addNodeInBetween(new Node(loca[0],loca[1]),R,L);
                    this.l.NodeUpdated();
                    this.l.repaint();
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(NodeAdd.class.getName()).log(Level.SEVERE, null, ex);
                    }
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
            return Config.toolkit.createCustomCursor(Config.NodeCursorImage, p, null);
        }
    }
}
