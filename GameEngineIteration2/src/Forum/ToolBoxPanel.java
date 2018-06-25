/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Forum;

import Forum.ToolBox.ToolGraphic;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;

/**
 *
 * @author Allazham
 */
public class ToolBoxPanel extends JPanel{
    private boolean horizional = false;
    private final List<ToolButton> t = new ArrayList();
    private final Dimension d = new Dimension(0,0);
    private int pos = 0;
    public ToolBoxPanel(int pos){
        this.pos = pos;
        super.setBackground(Color.WHITE);
        super.setLayout(null);
        super.addComponentListener(new Resize());
        super.setMaximumSize(d);
        super.setMinimumSize(d);
        super.setPreferredSize(d);
        super.setSize(d);
    }
    public int getPreferedPosition(){
        return this.pos;
    }
    public void setPreferedPosition(int i){
        this.pos = i;
    }
    public void setFrameManager(FrameMain fm){
        int i;
        for(i = 0; i < this.t.size();i++){
            this.t.get(i).setFrameMain(fm);
        }
    }
    public Dimension getDimension(){
        return this.d;
    }
    public void setHorizional(boolean hor){
        this.horizional = hor;
        ResizeEvent();
    }
    public boolean isHorizional(){
        return this.horizional;
    }
    public boolean isVertical(){
        return !this.horizional;
    }
    public void setHorizinal(boolean horz){
        this.horizional = horz;
        ResizeEvent();
    }
    public void setVertical(boolean virt){
        this.horizional = !virt;
        ResizeEvent();
    }
    public void setHorizinal(){
        this.horizional = true;
    }
    public void setVertical(){
        this.horizional = false;
        ResizeEvent();
    }
    @Override
    public void validate(){
        ResizeEvent();
    }
    public void addTool(ToolGraphic t){
        ToolButton tb = new ToolButton(t);
        this.t.add(tb);
        super.add(tb);
    }
    public synchronized void ResizeEvent(){
        int i;
        int x = 0;
        if(this.horizional){
            for(i = 0; i < this.t.size();i++){
                t.get(i).setLocation(x+1, 1);
                x += 20;
            }
            d.setSize(x, 20);
        }
        else{
            for(i = 0; i < this.t.size();i++){
                t.get(i).setLocation(1,x+1);
                x += 20;
            }
            d.setSize(20, x);
        }
        super.setSize(d);
        super.repaint();
    }
    
    private class Resize implements ComponentListener{
        @Override
        public void componentResized(ComponentEvent e) {
            ResizeEvent();
        }
        @Override
        public void componentMoved(ComponentEvent e) {
            ResizeEvent();
        }
        @Override
        public void componentShown(ComponentEvent e) {
            ResizeEvent();
        }
        @Override
        public void componentHidden(ComponentEvent e) {
            
        } 
    }
}
