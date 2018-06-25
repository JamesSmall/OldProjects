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
public class LinkLines extends ToolGraphic implements Tool,MouseListener{
    private NodeList o;
    private Node n1 = null,n2 = null;
    public LinkLines(){
        super(Config.AddLine);
        super.setToolTip("Link Nodes");
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
            GraphicsObject g = this.p.getTargetGraphic();
            if(g instanceof NodeList){
                this.o = (NodeList) g;
                loc = p.convertMouseCordsToScreen(e.getX(), e.getY());
                if(!this.o.containsNode(n1)){
                    if(this.n1 != null){
                        this.n1.setColor(Config.defaultColor);
                    }
                    this.n1 = this.o.getNode(loc[0],loc[1], p.convertDistanceScale(5));
                    if(this.n1 != null){
                        this.n1.setColor(Config.DefaultTargetColor);
                        this.o.repaint();
                    }
                }
                else{
                    this.n2 = this.o.getNode(loc[0],loc[1], p.convertDistanceScale(5));
                    if(this.n2 != null){
                        this.n1.setColor(Config.defaultColor);
                        this.o.linkNodes(n1, n2);
                        this.n1 = null;
                        this.n2 = null;
                        this.o.NodeUpdated();
                        this.o.repaint();
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
