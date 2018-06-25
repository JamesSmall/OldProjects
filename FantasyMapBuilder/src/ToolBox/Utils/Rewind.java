/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ToolBox.Utils;

import Forum.FrameMain;
import ToolBox.Effect;
import ToolBox.ToolGraphic;
import Utils.Config;

/**
 *
 * @author Allazham
 */
public class Rewind extends ToolGraphic implements Effect{
    public Rewind(){
        super(Config.Reverse);
        super.setToolTip("undo");
    }
    @Override
    public void Affect(FrameMain fm) {
        fm.rollBackward();
    }
}
