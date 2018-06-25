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
public class Forward extends ToolGraphic implements Effect{
    public Forward(){
        super(Config.Forward);
        super.setToolTip("redo");
    }

    @Override
    public void Affect(FrameMain fm) {
        fm.rollForward();
    }
}
