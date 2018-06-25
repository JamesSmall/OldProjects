/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Forum;

import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;

/*
 * @author Allazham
 */
public class JPanelCustom extends JPanel{
    
    private int Distance = 0;
    private boolean Horizionial = false;
    private List<ToolBox> bos = new ArrayList();
    public JPanelCustom(boolean Horizional){
        this.Horizionial = Horizional;
        super.setLayout(null);
        super.addComponentListener(new Resize());
    }
    @Override
    public void removeAll(){
        this.bos.clear();
    }
    public void addToolBox(ToolBox comp){
        comp.setHorizinal(Horizionial);
        this.bos.add(comp);
        super.add(comp);
        ResizeEvent();
    }
    public void removeToolBox(ToolBox comp){
        super.remove(comp);
        this.bos.remove(comp);
        ResizeEvent();
    }
    protected int getDistance(){
        return this.Distance;
    }
    @Override
    public void validate(){
        this.ResizeEvent();
    }
    private synchronized void ResizeEvent(){
        this.Distance = 0;
        int i;
        int x = 0, y = 0;
        super.validate();
        if(!this.bos.isEmpty()){
            this.Distance += 20;
        }
        if(this.Horizionial){
            for(i = 0; i < this.bos.size();i++){
                this.bos.get(i).ResizeEvent();
                //x += this.bos.get(i).getWidth();
                if(x > super.getWidth()){
                    x = 0;
                    y += 20;
                    this.Distance += 20;
                }
                this.bos.get(i).setLocation(x, y);
                x += this.bos.get(i).getWidth();
            }
        }
        else{
            for(i = 0; i < this.bos.size();i++){
                this.bos.get(i).ResizeEvent();
                //y += this.bos.get(i).getWidth();
                if(y > super.getHeight()){
                    y = 0;
                    x += 20;
                    this.Distance += 20;
                }
                this.bos.get(i).setLocation(x, y);
                y += this.bos.get(i).getHeight();
            }
        }
        super.repaint();
    }
    private class Resize extends ComponentAdapter{
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
    }
}
