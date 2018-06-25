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
public class UnlinkLines extends ToolGraphic implements Tool,MouseListener{
    private NodeList o;
    private Node n1 = null,n2 = null;
    public UnlinkLines(){
        super(Config.RemoveLine);
        super.setToolTip("unlink nodes");
    }
    DrawingPanel p;
    @Override
    public void Setup(DrawingPanel p) {
        this.p = p;
    }

    @Override
    public void renderMap(Graphics2D g, GraphicConditions c) {
        
    }

    @Override
    public void Destoy() {
        if(n1 != null){
            this.n1.setColor(Config.defaultColor);
            this.n1 = null;
        }
        if(n2 != null){
            this.n2 = null;
        }
    }

    @Override
    public Cursor getCursorInstance(Point p) {
        return Cursor.getDefaultCursor();
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
        Line l;
            GraphicsObject g = this.p.getTargetGraphic();
            if(g instanceof NodeList){
                this.o = (NodeList) g;
                loc = p.convertMouseCordsToScreen(e.getX(), e.getY());
                l = o.getClosesLineInArea(loc[0], loc[1], p.convertDistanceScale(5));
                if(l != null){
                    n1 = l.getFrontNode();
                    n2 = l.getBackNode();
                    if(n1 != null && n2 != null){
                        o.unlinkNodes(n1, n2);
                        o.NodeUpdated();
                        o.repaint();
                        UndoManager.Instance.add(p);
                    }
                }
            }
    }

    @Override
    public void mouseEntered(MouseEvent e) {
       
    }

    @Override
    public void mouseExited(MouseEvent e) {
        
    }
}
