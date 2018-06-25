/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Forum;

import Graphics.UserActions.UserAction;
import Forum.ToolBox.Changeable;
import Forum.ToolBox.Effect;
import Forum.ToolBox.GrabTool;
import Forum.ToolBox.ToolGraphic;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import javax.swing.SwingUtilities;
import javax.swing.border.LineBorder;

/**
 *
 * @author Allazham
 */
public class ToolButton extends JButton{
    private final ToolGraphic t;
    private FrameMain fm;
    public ToolButton(ToolGraphic t){
        super.setSize(18,18);
        super.setBorder(new LineBorder(Color.black));
        this.t = t;
        super.setToolTipText(t.getToolTip());
        super.addMouseListener(new Listener());
    }
    @Override
    public void paint(Graphics g){
        super.paint(g);
        g.drawImage(t.getImage(), 1,1, null);
        
    }
    public void setFrameMain(FrameMain fm){
        this.fm = fm;
    }
    private void setToolOptions(){
        if(t instanceof Changeable){
            ((Changeable)t).showInstance();
       }
    }/*
    private void setTool(){
        Screen p = this.fm.getDrawingPanel();
        if(t instanceof Effect){
            ((Effect)t).Affect(fm);
        }
        else if(t instanceof GrabTool){
            p.setUserAction(((GrabTool)t).getToolInstance());
        }
        else if(t instanceof Tool){
            p.setuserAction(t);
        }
    }*/
    private void setTool(){
        if(t instanceof Effect){
            ((Effect)t).Affect(fm);
        }
        else if(t instanceof GrabTool){
            this.fm.setTool(((GrabTool)t).getToolInstance());
        }
        else if(t instanceof UserAction){
            this.fm.setTool((UserAction) t);
        }
    }
    private class Listener extends MouseAdapter{
        @Override
        public void mouseReleased(MouseEvent e) {
            if(SwingUtilities.isLeftMouseButton(e)){
                setTool();
            }
            else if(SwingUtilities.isRightMouseButton(e)){
                setToolOptions();
            }
        }
    
    }
}
