/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ToolBox.ImageEdit;

import Forum.DrawingPanel;
import Forum.GraphicConditions;
import Forum.UndoManager;
import GraphicalObjects.GraphicsObject;
import GraphicalObjects.Mesh;
import ToolBox.Changeable;
import ToolBox.Tool;
import ToolBox.ToolGraphic;
import Utils.Config;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
;

/**
 *
 * @author Allazham
 */
public class PaintBrush extends ToolGraphic implements Tool, Changeable, MouseListener{
    private double scale = 1;
    private Color masterColor = Color.pink;
    private DrawingPanel p;
    private Mesh m;
    private static final int MOVEX = 9,MOVEY = 63;
    private boolean isPainting,square = false;
    public PaintBrush(){
        super(Config.PaintBrush);
        super.setToolTip("Paint Brush");
    }
    @Override
    public void Setup(DrawingPanel p) {
        this.p = p;
    }

    @Override
    public void renderMap(Graphics2D g, GraphicConditions c) {
     //   throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void Destoy() {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setDisable(boolean disable) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean isDisaled() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update() {
        
    }

    @Override
    public JFrame getCustomInstance() {
        return new CustomPanel();
    }
    @Override
    public void showInstance() {
        new CustomPanel();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if(p != null){
            GraphicsObject g = p.getTargetGraphic();
            if(g instanceof Mesh){
                m = (Mesh) g;
                isPainting = true;
                new RunPressEvent().start();
            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        this.isPainting = false;
        this.m = null;
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        
    }

    @Override
    public void mouseExited(MouseEvent e) {
        this.isPainting = false;
        this.m = null;
    }

    @Override
    public Cursor getCursorInstance(Point p) {
        return Config.toolkit.createCustomCursor(Config.PaintCursorImage, p, null);
    }
    private class CustomPanel extends JFrame{
        private JPanelColor ColorPanel;
        private JLabel DistanceL;
        private JTextField DistanceTF;
        private JButton OkB, CancelB,ChooseColorB;
        private JRadioButton Square,Circle;
        private Color ColorSample = Color.pink;
        private Reviveal r;
        public CustomPanel(){
            super.setLocationRelativeTo(null);
            super.setSize(190,150);
            super.setResizable(false);
            super.setLayout(new FlowLayout());
            super.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            this.DistanceL = new JLabel("Distance",10);
            this.DistanceTF = new JTextField("5",10);
            this.OkB = new JButton("Ok");
            this.CancelB = new JButton("Cancel");
            this.ChooseColorB = new JButton("Choose Color");
            this.ColorPanel = new JPanelColor();
            this.OkB.addActionListener(new OkBListener());
            this.CancelB.addActionListener(new CancelBListener());
            this.ChooseColorB.addActionListener(new ColorChooserActionListener());
            this.Square = new JRadioButton("Square");
            this.Circle = new JRadioButton("Circle");
            
            r = new Reviveal();
            r.addRadioButton(Square);
            r.addRadioButton(Circle);
            Square.addActionListener(r);
            Circle.addActionListener(r);
            Square.setSelected(true);
            
            super.add(this.DistanceL);
            super.add(this.DistanceTF);
            super.add(this.ColorPanel);
            super.add(this.ChooseColorB);
            super.add(this.Square);
            super.add(this.Circle);
            super.add(this.OkB);
            super.add(this.CancelB);
            super.setTitle("Paint Brush Options");
            
            
            super.setVisible(true);
        }
        private class OkBListener implements ActionListener{
            @Override
            public void actionPerformed(ActionEvent e) {
                masterColor = ColorSample;
                try{
                    scale = Double.parseDouble(DistanceTF.getText());
                }
                catch(NumberFormatException ex){
                    
                }
                square = Square.isSelected();
                dispose();
            }
        }
        private class CancelBListener implements ActionListener{
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        }
        private class ColorChooserActionListener implements ActionListener{
            @Override
            public void actionPerformed(ActionEvent e) {
                ColorSample = JColorChooser.showDialog(null, "Color Chooser", ColorSample);
                repaint();
            }
        }
        private class JPanelColor extends JPanel{
                @Override
                public void paint(Graphics g){
                    super.paint(g);
                    g.setColor(ColorSample);
                    g.fillRect(0, 0,16,16);
                }
            }
        private class Reviveal implements ActionListener{
            private List<JRadioButton> b = new ArrayList();
            public void addRadioButton(JRadioButton r){
                b.add(r);
            }
            @Override
            public void actionPerformed(ActionEvent e) {
               JRadioButton RB;
               int i;
               if(e.getSource() instanceof JRadioButton){
                   RB = (JRadioButton) e.getSource();
                    if(RB.isSelected()){
                        for(i = 0; i < b.size();i++){
                            b.get(i).setSelected(false);
                        }
                    }
                    RB.setSelected(true);
               }
            }
        }
    }
     private class RunPressEvent extends Thread{
            @Override
            public void run(){
                Point pp;
                Mesh mesh = m;
                int[] loc;
                while(isPainting){
                    pp = MouseInfo.getPointerInfo().getLocation();
                    loc = p.convertAbsoluteCordstoScreen(pp);
                    if(mesh != null){
                        if(!square){
                            mesh.PaintCircle(loc[0], loc[1], p.convertDistanceScale(scale), masterColor);
                        }
                        else{
                            mesh.PaintSquare(loc[0], loc[1], p.convertDistanceScale(scale), masterColor);
                        }
                    }
                   
                }
                try {
                    Thread.sleep(100);
                } catch (InterruptedException ex) {
                    Logger.getLogger(PaintBrush.class.getName()).log(Level.SEVERE, null, ex);
                }
                UndoManager.Instance.add(p);
            }
        }
}
