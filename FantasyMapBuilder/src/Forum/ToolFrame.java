/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Forum;

import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import javax.swing.JFrame;

/**
 *
 * @author Allazham
 */
public class ToolFrame extends JFrame{
    private static final int MOVEX = 16,MOVEY = 38;
    private ToolBox tb;
    public ToolFrame(ToolBox tb){
        super();
        
        super.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        super.setVisible(true);
        super.setLayout(null);
        this.tb = tb;
        if(tb != null){
            super.add(tb);
            tb.setHorizinal();
            ResizeEvent();
        }
        super.addComponentListener(new Resize());
    }
    public void setToolBox(ToolBox tb){
        this.tb = tb;
        tb.setHorizinal();
    }
    public ToolBox getToolBox(){
        return this.tb;
    }
    @Override
    public void validate(){
        this.ResizeEvent();
    }
    private void ResizeEvent(){
        super.validate();
        if(this.tb != null){
            if(tb.getWidth() > 60){
                super.setSize(tb.getWidth()+MOVEX,tb.getHeight()+MOVEY);
            }
            else{
                super.setSize(60+MOVEX,20+MOVEY);
            }
        }
        else{
            super.setSize(60+MOVEX,20+MOVEY);
        }
        
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
