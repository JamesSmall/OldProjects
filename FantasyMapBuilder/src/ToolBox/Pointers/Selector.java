/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ToolBox.Pointers;

import Forum.DrawingPanel;
import Forum.GraphicConditions;
import GraphicalObjects.GraphicsObject;
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
public class Selector extends ToolGraphic implements GrabTool{
    public Selector(){
        super(Config.Pointer);
        super.setToolTip("set Target");
    }
    @Override
    public SelectorTool getToolInstance() {
        return new SelectorTool();
    }
    public class SelectorTool implements Tool, MouseListener{
        private GraphicsObject g;
        private DrawingPanel p;
        @Override
        public void mouseClicked(MouseEvent e) {
            g = p.getGraphicsObject(e.getX(),e.getY());
            p.setTargetGraphic(g);
            p.repaint();
        }

        @Override
        public void mousePressed(MouseEvent e) {
        }

        @Override
        public void mouseReleased(MouseEvent e) {
        }
        @Override
        public void mouseEntered(MouseEvent e) {
        }

        @Override
        public void mouseExited(MouseEvent e) {
            
        }

        @Override
        public void renderMap(Graphics2D g, GraphicConditions c) {
            
        }

        @Override
        public void Setup(DrawingPanel p) {
            this.p = p;
        }
        @Override
        public void Destoy() {
            
        }

        @Override
        public Cursor getCursorInstance(Point p) {
            return Cursor.getDefaultCursor();
        }
    }
}
