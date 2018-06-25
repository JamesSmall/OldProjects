/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ToolBox.Utils;

import Forum.DrawingPanel;
import Forum.FrameMain;
import Forum.UndoManager;
import GraphicalObjects.GraphicsObject;
import ToolBox.Effect;
import ToolBox.ToolGraphic;
import Utils.Config;

/**
 *
 * @author Allazham
 */
public class ToBack extends ToolGraphic implements Effect{
    public ToBack(){
        super(Config.toBack);
        super.setToolTip("push to back");
    }
    @Override
    public void Affect(FrameMain fm) {
        DrawingPanel p = fm.getDrawingPanel();
        GraphicsObject g;
        if(p!= null){
            g = p.getTargetGraphic();
            if(g != null){
                p.toBack(g);
                UndoManager.Instance.add(p);
            }
        }
    }
}
