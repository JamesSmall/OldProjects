/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ToolBox.Pointers;

import Forum.DrawingPanel;
import Forum.GraphicConditions;
import Forum.UndoManager;
import GraphicalObjects.GraphicsObject;
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

/**
 *
 * @author Allazham
 */
public class MoveTool extends ToolGraphic implements GrabTool{
    public MoveTool(){
        super(Config.PointerMover);
        super.setToolTip("Move Object Tool");
    }

    @Override
    public MoveTheTool getToolInstance() {
        return new MoveTheTool();
    }
    public class MoveTheTool implements Tool, MouseListener{
        private DrawingPanel p;
        private int[] points;
        boolean running = false;
        GraphicsObject g;
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
            if(p!= null){
                g = p.getGraphicsObject(e.getX(), e.getY());
                p.setTargetGraphic(g);
                points = p.convertMouseCordsToScreen(e.getX(), e.getY());
                if(g != null){
                    points[0] -= g.getImageX();
                    points[1] -= g.getImageY();
                    this.running = true;
                    new Running().start();
                }
            }
        }

        @Override
        public void mouseReleased(MouseEvent e) {
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
        public class Running extends Thread{
            @Override
            public void run(){
                Point pp;
                int[] loc;
                while(running){
                    pp = MouseInfo.getPointerInfo().getLocation();
                    loc = p.convertAbsoluteCordstoScreen(pp);
                    g.setImageX(loc[0]);
                    g.setImageY(loc[1]);
                    g.repaint();
                }
                UndoManager.Instance.add(p);
                g.repaint();
            }
        }
    }
}
