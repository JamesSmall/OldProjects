/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ToolBox.ImageEdit;

import Forum.DrawingPanel;
import Forum.FrameMain;
import Forum.UndoManager;
import GraphicalObjects.GraphicsObject;
import GraphicalObjects.Mesh;
import ToolBox.Effect;
import ToolBox.ToolGraphic;
import Utils.Config;
import Utils.ImageEditor;
import java.awt.FlowLayout;
import java.awt.MouseInfo;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

/**
 *
 * @author Allazham
 */
public class LoadTexture extends ToolGraphic implements Effect{
    private JFileChooser FileChooser;
    public LoadTexture(){
        super(Config.ImageLoad);
        FileChooser = new JFileChooser();
        ImageTypes.setTypes(FileChooser);
        super.setToolTip("Load Texture");
    }
    @Override
    public void Affect(FrameMain fm) {
        int v;
        BufferedImage img;
        DrawingPanel p;
        GraphicsObject g;
        Mesh m;
        p = fm.getDrawingPanel();
        if(p != null){
            g = p.getTargetGraphic();
            if(g instanceof Mesh){
                m = (Mesh) g;
                v = FileChooser.showOpenDialog(null);
                if(v == JFileChooser.APPROVE_OPTION){
                    try {
                        img = ImageIO.read(FileChooser.getSelectedFile());
                        new Choice(p,m,img);
                    } catch (IOException ex) {
                        Logger.getLogger(LoadTexture.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    
                }
            }
        }
    }
    private class Choice extends JFrame{
        private Mesh m;
        private DrawingPanel p;
        private JLabel XL,YL, KnowledgeL,MeshL;
        private JTextField XTF, YTF;
        private JButton OkB, ExitB;
        private BufferedImage img;
        private int Width, Height;
        public Choice(DrawingPanel p,Mesh m,BufferedImage img){
            this.m = m;
            this.img = img;
            this.p = p;
            super.setLocationRelativeTo(null);
            super.setSize(190,150);
            super.setLayout(new FlowLayout());
            XL = new JLabel("Width=",10);
            YL = new JLabel("Height=",10);
            XTF = new JTextField(20);
            YTF = new JTextField(20);
            OkB = new JButton("Ok Button");
            ExitB = new JButton("Cancel Button");
            KnowledgeL = new JLabel("Image Width = " + img.getWidth() + " && Height = "+ img.getHeight());
            MeshL = new JLabel("Mesh Recommends Width = " + m.getImageWidth() + " &&  Height = "+ m.getImageHeight());
            
            OkB.addActionListener(new OkBListener());
            ExitB.addActionListener(new CancelBListener());
            
            XTF.setText(""+m.getImageWidth());
            YTF.setText(""+m.getImageHeight());
            
            super.setLayout(new FlowLayout());
            super.add(XL);
            super.add(XTF);
            super.add(YL);
            super.add(YTF);
            super.add(MeshL);
            super.add(KnowledgeL);
            super.add(OkB);
            super.add(ExitB);
            super.setTitle("Image Load Options");
            
            super.setSize(315,166);
            super.setResizable(false);
            super.setVisible(true);
        }
        private class OkBListener implements ActionListener{

            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    Width = Integer.parseInt(XTF.getText());
                    Height = Integer.parseInt(YTF.getText());
                    if(Height > 9999 && Width > 9999){
                        throw new NumberFormatException("Value config is too high for image config");
                    }
                    m.setImage(ImageEditor.rescaleImage(img, ((double)Width)/((double)img.getWidth()),((double)Height)/((double)img.getHeight())));
                    UndoManager.Instance.add(p);
                }
                catch(NumberFormatException ex){
                    
                }
                dispose();
            }
            
        }
        private class CancelBListener implements ActionListener{

            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        }
    }
}
