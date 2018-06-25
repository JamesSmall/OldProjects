/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ToolBox.Create;

import Forum.DrawingPanel;
import Forum.FrameMain;
import GraphicalObjects.GraphicsObject;
import ToolBox.Effect;
import ToolBox.ToolGraphic;
import Utils.Config;

/**
 *
 * @author Allazham
 */
public class Delete extends ToolGraphic implements Effect{
    public Delete(){
        super(Config.DeleteImage);
        super.setToolTip("Delete Target Graphic");
    }
    @Override
    public synchronized void Affect(FrameMain fm) {
        DrawingPanel p = fm.getDrawingPanel();
        GraphicsObject g;
        if(p!= null){
            g = p.getTargetGraphic();
            if(g != null){
                p.removeGraphicsObject(g);
                p.repaint();
                p.getFrameMain().PrepareBackup();
            }
        }
    }
}
