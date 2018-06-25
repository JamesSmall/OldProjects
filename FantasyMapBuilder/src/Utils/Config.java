/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

import Forum.FrameMain;
import Forum.ToolBox;
import ToolBox.Create.Delete;
import ToolBox.Create.NodeMeshCreator;
import ToolBox.ImageEdit.Eraser;
import ToolBox.ImageEdit.LoadTexture;
import ToolBox.ImageEdit.PaintBrush;
import ToolBox.NodeEdit.LinkLines;
import ToolBox.NodeEdit.NodeAdd;
import ToolBox.NodeEdit.NodeDestroy;
import ToolBox.NodeEdit.NodeSelector;
import ToolBox.NodeEdit.UnlinkLines;
import ToolBox.Pointers.MoveTool;
import ToolBox.Pointers.Selector;
import ToolBox.Utils.Forward;
import ToolBox.Utils.Load;
import ToolBox.Utils.MoveBack;
import ToolBox.Utils.MoveForward;
import ToolBox.Utils.Rewind;
import ToolBox.Utils.Save;
import ToolBox.Utils.ToBack;
import ToolBox.Utils.ToFront;
import java.awt.Color;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author Allazham
 */
public class Config {
    public static Color defaultColor = Color.YELLOW;
    public static Color DefaultTargetColor = Color.red;
    
    public static File GeneralSaveLocation;
    public static File FrameMainSave;
    public static File ImportedImage;
    public static File ConfigFile;
    public static boolean  FirstRun = false;
    public static final String MAIN = "main";
    
    public static final String SEPERATOR = "::";
    public static final String SEPERATORX = "'";
    public static final String SEPERATORY = ",";
    public static final String STARTSYMBOL = "<";
    public static final String ENDSYMBOL = ">";
    
    public static final String WIDTH = "width";
    public static final String HEIGHT = "height";
    
    public static final String EQUALSYM = "=";
    
    public static final String ENDLINE = ";";
    
    public static final String START = "@start";
    public static final String END = "@end";
    
    public static final String IMAGE = "image";
    public static final String MESHLIST = "meshlist";
    public static final String LINE = "line";
    public static final String NODE = "NODE";
    public static final String DATA = "data";
    
    //forums management
    public static final int Disabled = 5;
    public static final int Windowed = 4;
    public static final int Up = 0;
    public static final int Left = 1;
    public static final int Right = 2;
    public static final int Down = 3;
    
    public static final String LS = System.getProperty("line.separator");
    //public static BufferedImage SELECORIMAGE = null;
    
    //images loaded
    //images
    public static BufferedImage Pointer;
    public static BufferedImage NodePointer;
    public static BufferedImage PaintBrush;
    public static BufferedImage NodeDestroyer;
    public static BufferedImage NodeAdder;
    public static BufferedImage PointerMover;
    public static BufferedImage Eraser;
    //function
    public static BufferedImage Reverse;
    public static BufferedImage Forward;
    public static BufferedImage Save;
    public static BufferedImage Load;
    public static BufferedImage toFront;
    public static BufferedImage toBack;
    public static BufferedImage moveForward;
    public static BufferedImage moveBackward;
    
    //pointer
    public static BufferedImage PointerCenter;
    public static BufferedImage PointerLeftUp;
    public static BufferedImage PointerLeftDown;
    public static BufferedImage PointerRightUp;
    public static BufferedImage PointerRightDown;
    public static BufferedImage PointerUp;
    public static BufferedImage PointerDown;
    public static BufferedImage PointerRight;
    public static BufferedImage PointerLeft;
    public static BufferedImage PointerSquare;
    public static BufferedImage ImageLoad;
    
    public static BufferedImage MeshImage;
    public static BufferedImage DeleteImage;
    
    public static BufferedImage PaintCursorImage;
    public static BufferedImage NodeCursorImage;
            
    public static BufferedImage AddLine;
    public static BufferedImage RemoveLine;
    public static BufferedImage WaterTexture;
    
    public static Toolkit toolkit;
    private static ToolBox Pointers,NodeEditor,OverallUtils,ImageEdit,CreateObject;
    
    
    public static void Intilize() throws IOException{
        Pointer = ImageIO.read(new File("Resources\\ToolBox\\BaseTools\\SelectorTool.png"));
        NodePointer = ImageIO.read(new File("Resources\\ToolBox\\BaseTools\\NodeSelector.png"));
        PaintBrush = ImageIO.read(new File("Resources\\ToolBox\\BaseTools\\PaintBrush.png"));
        NodeAdder = ImageIO.read(new File("Resources\\ToolBox\\BaseTools\\NodeAdder.png"));
        NodeDestroyer = ImageIO.read(new File("Resources\\ToolBox\\BaseTools\\NodeDestroyer.png"));
        PointerMover = ImageIO.read(new File("Resources\\ToolBox\\BaseTools\\MoverTool.png"));
        ImageLoad = ImageIO.read(new File("Resources\\ToolBox\\BaseTools\\ImageLoad.png"));
        Eraser = ImageIO.read(new File("Resources\\ToolBox\\BaseTools\\Eraser.png"));
        AddLine = ImageIO.read(new File("Resources\\ToolBox\\BaseTools\\AddLine.png"));
        RemoveLine = ImageIO.read(new File("Resources\\ToolBox\\BaseTools\\RemoveLine.png"));
        
        
        Load = ImageIO.read(new File("Resources\\ToolBox\\EffectTools\\Load.png"));
        Save = ImageIO.read(new File("Resources\\ToolBox\\EffectTools\\Save.png"));
        Forward = ImageIO.read(new File("Resources\\ToolBox\\EffectTools\\Next.png"));
        Reverse = ImageIO.read(new File("Resources\\ToolBox\\EffectTools\\Back.png"));
        
        toFront = ImageIO.read(new File("Resources\\ToolBox\\EffectTools\\toFront.png"));
        toBack = ImageIO.read(new File("Resources\\ToolBox\\EffectTools\\toBack.png"));
        moveForward = ImageIO.read(new File("Resources\\ToolBox\\EffectTools\\moveUp.png"));
        moveBackward = ImageIO.read(new File("Resources\\ToolBox\\EffectTools\\moveBack.png"));
        
        
        PointerCenter = ImageIO.read(new File("Resources\\ItemSelectors\\PointerCenter.png"));
        PointerLeftUp = ImageIO.read(new File("Resources\\ItemSelectors\\PointLeftUp.png"));
        PointerDown  = ImageIO.read(new File("Resources\\ItemSelectors\\PointDown.png"));
        PointerLeft  = ImageIO.read(new File("Resources\\ItemSelectors\\PointLeft.png"));
        PointerLeftDown = ImageIO.read(new File("Resources\\ItemSelectors\\PointLeftDown.png"));
        PointerRight  = ImageIO.read(new File("Resources\\ItemSelectors\\PointRight.png"));
        PointerRightDown  = ImageIO.read(new File("Resources\\ItemSelectors\\PointRightDown.png"));
        PointerRightUp = ImageIO.read(new File("Resources\\ItemSelectors\\PointRightUp.png"));
        PointerUp  = ImageIO.read(new File("Resources\\ItemSelectors\\PointUp.png"));
        PointerSquare  = ImageIO.read(new File("Resources\\ItemSelectors\\Square.png"));
        
        //the image
        MeshImage = ImageIO.read(new File("Resources\\ToolBox\\CreateImage\\Mesh.png"));
        DeleteImage = ImageIO.read(new File("Resources\\ToolBox\\CreateImage\\Delete.png"));
        
        //cursor
        PaintCursorImage = ImageIO.read(new File("Resources\\Cursor\\PaintBrush.png"));
        NodeCursorImage = ImageIO.read(new File("Resources\\Cursor\\NodeAdd.png"));
        
        WaterTexture = ImageIO.read(new File("Resources\\TextureBox\\Water.png"));
        
        toolkit = Toolkit.getDefaultToolkit();
        
    }
    public static void setUp(){
        //set up;
        OverallUtils = new ToolBox(Config.Up);
        Pointers = new ToolBox(Config.Left);
        NodeEditor = new ToolBox(Config.Left);
        ImageEdit = new ToolBox(Config.Right);
        CreateObject = new ToolBox(Config.Down);
        
        //set name
        OverallUtils.setName("Utils");
        NodeEditor.setName("Node Editor");
        Pointers.setName("Pointer");
        ImageEdit.setName("ImageEditor");
        CreateObject.setName("Create Object");
        
        Pointers.addTool(new Selector());
        Pointers.addTool(new MoveTool());
        
        NodeEditor.addTool(new NodeAdd());
        NodeEditor.addTool(new NodeDestroy());
        NodeEditor.addTool(new NodeSelector());
        NodeEditor.addTool(new LinkLines());
        NodeEditor.addTool(new UnlinkLines());
        
        OverallUtils.addTool(new Save());
        OverallUtils.addTool(new Load());
        OverallUtils.addTool(new Rewind());
        OverallUtils.addTool(new Forward());
        OverallUtils.addTool(new MoveForward());
        OverallUtils.addTool(new MoveBack());
        OverallUtils.addTool(new ToFront());
        OverallUtils.addTool(new ToBack());
        
        
        ImageEdit.addTool(new PaintBrush());
        ImageEdit.addTool(new Eraser());
        ImageEdit.addTool(new LoadTexture());
        
        CreateObject.addTool(new NodeMeshCreator());
        CreateObject.addTool(new Delete());
    }
    public static void setupToolBox(FrameMain fm) throws IOException{
        //Intilize();
        //setUp();
        //checkFileDirectory();
        fm.addToolBox(Pointers);
        fm.addToolBox(OverallUtils);
        fm.addToolBox(NodeEditor);
        fm.addToolBox(ImageEdit);
        fm.addToolBox(CreateObject);
        fm.validate();
        fm.repaint();
        //fm.addToolBox(ImageEdit);
    }
    public static void checkFileDirectory() throws IOException{
        ConfigFile = new File("Resources/Config.ini");
        GeneralSaveLocation = new File("Save");
        FrameMainSave = new File("Resources/FrameMainConfig.ini");
        ImportedImage = new File("Resources/ImportedImage");
        if(!Config.ImportedImage.exists()||!ConfigFile.exists() || !GeneralSaveLocation.exists()){
            FirstRun = true;
            ConfigFile.createNewFile();
            GeneralSaveLocation.mkdir();
            ImportedImage.mkdir();
        }
        else{
            
        }
    }
}
