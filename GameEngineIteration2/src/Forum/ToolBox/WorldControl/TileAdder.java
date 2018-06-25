/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Forum.ToolBox.WorldControl;

import Graphics.Forum.ActionKeys;
import Graphics.UserActions.UserAction;
import Graphics.Utils.Camera2D;
import Forum.ToolBox.Changeable;
import Forum.ToolBox.GrabTool;
import Forum.ToolBox.ToolGraphic;
import Graphics.UserActions.UserWorldAction;
import Utils.Config;
import World.Tile;
import World.Cell;
import World.Chunk;
import World.Region;
import World.WorldControl;
import World.WorldUtils;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;
import org.lwjgl.input.Mouse;
import org.lwjgl.util.vector.Vector2f;

/**
 *
 * @author Allazham
 */
public class TileAdder extends ToolGraphic implements Changeable,GrabTool{
    private boolean disabled = false;
    private static final Camera2D cam = Camera2D.getCamera();
    private static final WorldControl wld = WorldControl.getInstance();
    private int IDChoose = 0;
    public TileAdder(){
        super(Config.PointerBasic);
        super.setToolTip("Set Tile");
    }
    @Override
    public void setDisable(boolean disable) {
        this.disabled = disable;
    }

    @Override
    public boolean isDisaled() {
        return this.disabled;
    }

    @Override
    public void showInstance() {
        new CustomPanel().setVisible(true);
    }

    @Override
    public JFrame getCustomInstance() {
        return new CustomPanel();
    }

    @Override
    public UserAction getToolInstance() {
        return new PlaceTile();
    }
    public class CustomPanel extends JFrame{
        private final JTextField TF;
        public CustomPanel(){
            super.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            super.setLocationRelativeTo(null);
            super.setSize(150,88);
            super.setResizable(false);
            super.setLayout(new FlowLayout());
            
            TF = new JTextField(10);
            
            super.add(this.TF);
            super.add(new SetB());
            super.add(new CancelB());
            
            super.validate();
            
        }
        private class SetB extends JButton{
            SetB(){
                super.addActionListener(new SB());
                super.setText("set");
                super.setVisible(true);
            }
            private class SB implements ActionListener{
                @Override
                public void actionPerformed(ActionEvent e) {
                    try{
                        IDChoose = Integer.parseInt(TF.getText());
                    }
                    catch(Exception ex){
                        
                    }
                    dispose();
                } 
            }
        }
        private class CancelB extends JButton{
            CancelB(){
                super.addActionListener(new CB());
                super.setText("cancel");
                super.setVisible(true);
            }
            private class CB implements ActionListener{
                @Override
                public void actionPerformed(ActionEvent e) {
                    dispose();
                }
            }
        }
    }
    private class PlaceTile extends UserWorldAction{
        @Override
        public void Action(ActionKeys k) {
            Region r;
            Chunk c;
            Cell cc;
            int [] loc;
            byte[] bloc;
            if(Mouse.isButtonDown(0)){
                Vector2f camera = cam.getMousePointInWorld();
                cc = wld.getPrimaryCell();
                if(cc != null){
                    
                    if(cc.isReady()){
                        r = WorldUtils.getRegionFromPosition(cc, camera.x, camera.y);
                        if(r == null){ 
                            
                            loc = WorldUtils.getRegionPos(camera.x,camera.y);
                            r = new Region(cc,loc[0],loc[1]);
                            cc.addRegion(r);
                            bloc = WorldUtils.getInternalChunkPos(camera.x,camera.y);
                            c = new Chunk(r,bloc[0],bloc[1]);
                            r.setChunk(c);
                            bloc = WorldUtils.getInternalTilePos(camera.x, camera.y);
                            c.setTileAt(new Tile(c,bloc[0],bloc[1],IDChoose));
                        }
                        else{
                            c = WorldUtils.getChunkFromPosition(r, camera.x, camera.y);
                            if(c == null){
                                bloc = WorldUtils.getInternalChunkPos(camera.x,camera.y);
                                c = new Chunk(r,bloc[0],bloc[1]);
                                r.setChunk(c);
                                bloc = WorldUtils.getInternalTilePos(camera.x, camera.y);
                                c.setTileAt(new Tile(c,bloc[0],bloc[1],IDChoose));
                            }
                            else{
                                bloc = WorldUtils.getInternalTilePos(camera.x, camera.y);
                                c.setTileAt(new Tile(c,bloc[0],bloc[1],IDChoose));
                            }
                        }
                    }
                }
            }
        }

        @Override
        public void create() {
        
        }

        @Override
        public void dispose() {
        
        }
    }
}
