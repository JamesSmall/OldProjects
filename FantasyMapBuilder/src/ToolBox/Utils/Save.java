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
public class Save extends ToolGraphic implements Effect{
    public Save(){
        super(Config.Save);
        super.setToolTip("quick save");
    }

    @Override
    public void Affect(FrameMain fm) {
            fm.quickSave();
    }
}
