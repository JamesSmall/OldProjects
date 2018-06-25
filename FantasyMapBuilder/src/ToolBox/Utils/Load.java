/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ToolBox.Utils;

import Forum.FrameMain;
import ToolBox.Effect;
import ToolBox.ToolGraphic;
import Utils.Config;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Allazham
 */
public class Load extends ToolGraphic implements Effect{
    public Load(){
        super(Config.Load);
        super.setToolTip("Load");
    }
    @Override
    public void Affect(FrameMain fm) {
        fm.setupLoad();
    }
}
