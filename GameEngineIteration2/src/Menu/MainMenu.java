/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Menu;

import Graphics.GUIManager;
import Graphics.MasterClose;
import Graphics.Screen;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JOptionPane;

/**
 *
 * @author Allazham
 */
public class MainMenu {
    private GUIManager gui;
    private MainMenu.Function fun = new MainMenu.Function();
    private static final String STARTWORLD = "STR";
    private static final String OPTIONS = "OPT";
    private static final String EXIT = "EXIT";
    private static Screen scr = Screen.getInstance();
    private MainMenu() {
        double[][] GUIPoints = {{.33,.66},{.33,.66}};
        double[][] CompPoints;
        this.gui = new GUIManager(100,GUIPoints);
        
        JButton b = new JButton("start world");
        b.setActionCommand(MainMenu.STARTWORLD);
        b.addActionListener(this.fun);
        CompPoints = new double[2][2];
        CompPoints[0][0] = 0;
        CompPoints[0][1] = 1;
        CompPoints[1][0] = 0;
        CompPoints[1][1] = .2;
        this.gui.addGUIComponenet(b, CompPoints);
        
        b = new JButton("Options");
        b.setActionCommand(MainMenu.OPTIONS);
        b.addActionListener(this.fun);
        CompPoints = new double[2][2];
        CompPoints[0][0] = 0;
        CompPoints[0][1] = 1;
        CompPoints[1][0] = .4;
        CompPoints[1][1] = .6;
        this.gui.addGUIComponenet(b, CompPoints);
        
        b = new JButton("exit");
        b.setActionCommand(MainMenu.EXIT);
        b.addActionListener(this.fun);
        CompPoints = new double[2][2];
        CompPoints[0][0] = 0;
        CompPoints[0][1] = 1;
        CompPoints[1][0] = .8;
        CompPoints[1][1] = 1;
        this.gui.addGUIComponenet(b, CompPoints);
    }
    public void AddToScreen(){
        MainMenu.scr.addGUI(this.gui);
    }
    public void RemoveFromScreen(){
        MainMenu.scr.removeGUI(this.gui);
    }
    public static MainMenu getInstance() {
        return MainMenu.MainMenuHolder.INSTANCE;
    }
    private static class MainMenuHolder {
        private static final MainMenu INSTANCE = new MainMenu();
    }
    private class Function implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            switch(e.getActionCommand()){
                case MainMenu.STARTWORLD:
                    RemoveFromScreen();
                    WorldMenu.getInstance().AddToScreen();
                    break;
                case MainMenu.OPTIONS:
                    JOptionPane.showMessageDialog(null, "not supported yet");
                    break;
                case MainMenu.EXIT:
                    MasterClose.CloseAll();
                    break;
            }
        }
    }
}