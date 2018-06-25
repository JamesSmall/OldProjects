/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GraphicalObjects;

import Forum.GraphicConditions;
import Utils.ErrorMessage;
import Utils.ImageEditor;
import Utils.SaveManager.SaveObjectPackage;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Polygon;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import javax.swing.JLabel;

/**
 * this the the Combined interfaces NodeList,Mesh,andGraphicsInterface(through graphics object). the main 
 * purpose of this is to allow Custimization of images with in set bounds.
 * @author Allazham
 */
public class NodeMesh extends GraphicsObject implements NodeList,Mesh{
    private BufferedImage img;
    private Image renderImage;
    private boolean hasLines = true, doNotUpdate = false,noNodeUpdated = false;
    private boolean nodesUpdated = true;
    private double Left,Right,Up,Down;
    private double oldLeft,oldRight,oldUp,oldDown;
    private List<List<Node>> theList = new ArrayList();
    private List<Bool> circled = new ArrayList();
    private List<Node> nodes = new ArrayList();
    private List<Line> lines = new ArrayList();
    private RequestThread RT;
    private double ScaleX = 1,ScaleY = 1;
    private Polygon p;
    public NodeMesh(){
        RT = new RequestThread();
        RT.start();
    }
    private NodeMesh(GraphicConditions gc,UUID uid,BufferedImage img,List<List<Node>> theList,List<Bool> circles,boolean showLines,double left,double right,double up, double down, double oldLeft,double oldRight,double oldUp, double oldDown){
        super(uid,gc);
        super.setDisabled(true);
        List<Node> oldList;
        List<Node> newNode;
        int i,j;
        if(img != null){
            this.img = ImageEditor.deepCopy(img);
        }
        else{
            this.img = null;
        }
        for(i = 0; i < theList.size();i++){
            newNode = new ArrayList();
            oldList = theList.get(i);
            for(j = 0;j<oldList.size();j++){
                newNode.add(oldList.get(j).getNewInstance());
            }
            this.nodes.addAll(newNode);
            this.circled.add(new Bool(circles.get(i).getValue()));
            this.theList.add(newNode);
        }
        this.Left = left;
        this.Right = right;
        this.Up = up;
        this.Down = down;
        this.oldDown = oldDown;
        this.oldLeft = oldLeft;
        this.oldRight = oldRight;
        this.oldUp = oldUp;
        this.hasLines = showLines;
        super.setImageX((Left+Right)/2);
        super.setImageY((Down+Up)/2);
        this.CreateLines();
    }
    @Override
    public void setImageX(double x){
        double xx = super.getImageX();
        int i;
        for(i = 0; i < this.nodes.size();i++){
           this.nodes.get(i).addImageX(x-xx);
        }
        this.RefixNode();
    }
    @Override
    public void setImageY(double y){
        double yy = super.getImageY();
        int i;
        for(i = 0; i < this.nodes.size();i++){
           this.nodes.get(i).addImageY(y-yy);
        }
        this.RefixNode();
    }
    @Override
    public void addImageX(double x){
        int i;
        for(i = 0; i < this.nodes.size();i++){
            this.nodes.get(i).addImageX(x);
        }
        this.RefixNode();
    }
    @Override
    public void addImageY(double y){
        int i;
        for(i = 0; i < this.nodes.size();i++){
            this.nodes.get(i).addImageY(y);
        }
        this.RefixNode();
    }
    private void RefixNode(){
        this.ConfigEnds();
        this.EffectLines();
    }
    public void doNotUpate(boolean update){
        this.doNotUpdate = update;
        this.RT.requestNodeUpdate();
    }
    public void setAllConnectedRequiremenet(boolean con){
        this.RT.requestNodeUpdate();
    }
    public void setHasLines(boolean hasLines){
        this.hasLines = hasLines;
        this.RT.requestNodeUpdate();
    }
    public boolean isShowingLines(){
        return this.hasLines;
    }
    @Override
    public void setImage(BufferedImage img){
        this.ConfigEnds();
        double[] bounds = this.getCurrentBounds();
        if(bounds[0] != 0 && bounds[1] != 0){
            this.ScaleX = Math.abs(bounds[0]/img.getWidth());
            this.ScaleY = Math.abs(bounds[1]/img.getHeight());
            this.img = img;
        }
        this.RT.requestNodeUpdate();
        repaint();
    }
    @Override
    public void linkNodes(Node n1, Node n2){
        int pos1,pos2;
        List<Node> list1,list2;
        list1 = findNodeMasterList(n1);
        list2 = findNodeMasterList(n2);
        if(list1.isEmpty() && list2.isEmpty()){
            list1.add(n1);
            list1.add(n2);
            this.nodes.add(n1);
            this.nodes.add(n2);
            this.theList.add(list1);
            this.circled.add(new Bool(false));
        }
        else if(list1.isEmpty()){
            pos2 = list2.indexOf(n2);
            this.nodes.add(n1);
            if((pos2 == list2.size()-1 &&list2.size() != 1)){
                list2.add(n1);
            }
            else if(pos2 == 0){
                list2.add(0,n1);
            }
        }
        else if(list2.isEmpty()){
            pos1 = list1.indexOf(n1);
            this.nodes.add(n2);
            if((pos1 == list1.size()-1 &&list1.size() != 1)){
                list1.add(n2);
            }
            else if(pos1 == 0){
                list1.add(0,n2);
            }
        }
        else if(list1 == list2){
            pos1 = list1.indexOf(n1);
            pos2 = list1.indexOf(n2);
            if((pos1 == 0 && pos2 == list1.size()-1)||(pos1 == list1.size()-1 && pos2 == 0)){
                this.circled.get(this.theList.indexOf(list1)).setValue(true);
            }
        }
        else{
            pos1 = list1.indexOf(n1);
            pos2 = list2.indexOf(n2);
            if(pos1 == 0 && pos2 == 0){
                pos2 = this.theList.indexOf(list2);
                this.theList.remove(pos2);
                this.circled.remove(pos2);
                list1.addAll(0,flipList(list2));
            }
            else if(pos1 == list1.size()-1 && pos2 == list2.size()-1){
                pos2 = this.theList.indexOf(list2);
                this.theList.remove(pos2);
                this.circled.remove(pos2);
                list1.addAll(flipList(list2));
            }
            else if(pos1 == 0 && pos2 == list2.size()-1){
                list1.addAll(0,list2);
                pos2 = this.theList.indexOf(list2);
                this.circled.remove(pos2);
                this.theList.remove(pos2);
            }
            else if(pos2 == 0 && pos1 == list1.size()-1){
                list2.addAll(0,list1);
                pos1 = this.theList.indexOf(list1);
                this.circled.remove(pos1);
                this.theList.remove(pos1);
            }
        }
        this.nodesUpdated = true;
    }
    @Override
    public void unlinkNodes(Node n1, Node n2){
        List<Node> list1,list2;
        int listPos;
        int i;
        int pos1,pos2;
        list1 = this.findNodeMasterList(n1);
        list2 = this.findNodeMasterList(n2);
        if(list1 == list2){
            pos1 = list1.indexOf(n1);
            pos2 = list1.indexOf(n2);
            listPos = this.theList.indexOf(list1);
            if(this.circled.get(listPos).getValue()){
                this.circled.get(listPos).setValue(false);
                if(!((pos1 == list1.size()-1&&pos2 == 0)||(pos2 == list1.size()-1&&pos1 == 0))){
                    if(pos1 > pos2){
                        list2 = list1.subList(pos2+1, list1.size());
                        list2 = newList(list2);
                        list1.removeAll(list2);
                        list1.addAll(0,list2);
                    }
                    else{
                        list1 = list1.subList(pos1+1, list1.size());
                        list1 = newList(list1);
                        list2.removeAll(list1);
                        list2.addAll(0,list1);
                    }
                }
            }
            else{
                if(pos1 > pos2){
                    list2 = list1.subList(pos1, list1.size());
                    list2 = newList(list2);
                    list1.removeAll(list2);
                    this.theList.add(list2);
                    this.circled.add(new Bool(false));
                }
                else{
                    list2 = list1.subList(pos2, list1.size());
                    list2 = newList(list2);
                    list1.removeAll(list2);
                    this.theList.add(list2);
                    this.circled.add(new Bool(false));
                }
            }
        }
        this.nodesUpdated = true;
    }
    private List<Node> newList(List<Node> n){
        List<Node> NodersList = new ArrayList();
        int i;
        for(i = 0; i < n.size(); i++){
            NodersList.add(n.get(i));
        }
        return NodersList;
    }
    @Override
    public void addNodeInBetween(Node newNode,Node nleft,Node nright){
        List<Node> loc, leftList,rightList;
        loc = this.findNodeMasterList(newNode);
        leftList = this.findNodeMasterList(nleft);
        rightList = this.findNodeMasterList(nright);
        int location;
        if(loc.size() == 1){
            this.removeNode(newNode);
        }
        else if(loc.size() > 1){
            return;
        }
        if(leftList == rightList){
            if(Math.abs(leftList.indexOf(nleft) - rightList.indexOf(nright)) == 1){
                if(leftList.indexOf(nleft) > leftList.indexOf(nright)){
                    location = leftList.indexOf(nleft);
                }
                else{
                    location = leftList.indexOf(nright);
                }
                this.nodes.add(newNode);
                leftList.add(location,newNode);
            }
            else if(leftList.indexOf(nleft) == leftList.size()-1 && leftList.indexOf(nright) == 0 || leftList.indexOf(nright) == leftList.size()-1 && leftList.indexOf(nleft) == 0){
                leftList.add(newNode);
                this.nodes.add(newNode);
            }
        }
        this.nodesUpdated = true;
    }
    private List<Node> findNodeMasterList(Node n){
        int i;
        for(i = 0; i < this.theList.size();i++){
            if(this.theList.get(i).contains(n)){
                return this.theList.get(i);
            }
        }
        return new ArrayList();
    }
    @Override
    public void addNodes(List<Node> e,boolean circled){
        this.theList.add(e);
        this.nodes.addAll(e);
        this.circled.add(new Bool(circled));
        this.RT.requestNodeUpdate();
        this.nodesUpdated = true;
    }
    @Override
    public void removeNodes(List<Node> e){
        int loc = this.theList.lastIndexOf(e);
        this.nodes.removeAll(e);
        this.theList.remove(loc);
        this.circled.remove(loc);
        this.RT.requestNodeUpdate();
        this.nodesUpdated = true;
    }
    @Override
    public void addNode(Node e){
        List<Node> n = new ArrayList();
        this.nodes.add(e);
        n.add(e);
        this.theList.add(n);
        this.RT.requestNodeUpdate();
        this.nodesUpdated = true;
    }
    @Override
    public void removeNode(Node e){
        int pos;
        List<Node> n;
        this.nodes.remove(e);
        n = this.findNodeMasterList(e);
        if(n.size() == 1){
            pos = this.theList.indexOf(n);
            this.circled.remove(pos);
            this.theList.remove(n);
        }
        else{
            n.remove(e);
        }
        this.nodesUpdated = true;
        this.RT.requestNodeUpdate();
    }
    private List<Node> flipList(List<Node> n){
        List<Node> NodersList = new ArrayList();
        int i;
        for(i = n.size()-1; i > -1; i--){
            NodersList.add(n.get(i));
        }
        return NodersList;
    }
    @Override
    public List<Node> getNodesInArea(double x, double y, double dist){
        List<Node> nodesInArea = new ArrayList();
        Node target;
        int i;
        for(i = 0; i < this.nodes.size();i++){
            target = this.nodes.get(i);
            if(Math.sqrt(Math.pow(target.getImageX() - x, 2)+Math.pow(target.getImageY() - y, 2)) < dist){
                nodesInArea.add(target);
            }
        }
        return nodesInArea;
    }
    @Override
    public Node getNode(double x, double y, double dist){
        List<Node> nodesInArea = this.getNodesInArea(x, y, dist);
        Node ret;
        int i;
        if(nodesInArea.isEmpty()){
            return null;
        }
        ret = nodesInArea.get(0);
        for(i = 1; i < nodesInArea.size();i++){
            ret = getCloserNode(x, y, ret, nodesInArea.get(i));
        }
        return ret;
    }
    private static Node getCloserNode(double x, double y, Node n1,Node n2){
       if(Math.sqrt(Math.pow(n1.getImageX() - x, 2)+Math.pow(n1.getImageY() - y, 2)) < Math.sqrt(Math.pow(n2.getImageX() - x, 2)+Math.pow(n2.getImageY() - y, 2))){
           return n1;
       }
       return n2;
    }
    private static Node getFartherNode(double x, double y, Node n1,Node n2){
       if(Math.sqrt(Math.pow(n1.getImageX() - x, 2)+Math.pow(n1.getImageY() - y, 2)) < Math.sqrt(Math.pow(n2.getImageX() - x, 2)+Math.pow(n2.getImageY() - y, 2))){
           return n2;
       }
       return n1;
    }
    private void ConfigEnds(){
        int i;
        this.oldDown = this.Down;
        this.oldLeft = this.Left;
        this.oldRight = this.Right;
        this.oldUp = this.Up;
        if(!this.nodes.isEmpty()){
            this.Down = this.nodes.get(0).getImageY();
            this.Up = this.nodes.get(0).getImageY();
            this.Left = this.nodes.get(0).getImageX();
            this.Right = this.nodes.get(0).getImageX();
        }
        else{
            this.Down = 0;
            this.Up = 0;
            this.Left = 0;
            this.Right = 0;
            return;
        }
        for(i = 1; i < this.nodes.size();i++){
            this.Down = Math.max(this.nodes.get(i).getImageY(),this.Down);
            this.Up = Math.min(this.nodes.get(i).getImageY(),this.Up);
            this.Left = Math.min(this.nodes.get(i).getImageX(),this.Left);
            this.Right = Math.max(this.nodes.get(i).getImageX(),this.Right);
        }
        super.setImageX((Left+Right)/2);
        super.setImageY((Down+Up)/2);
    }
    private void setImageEdges(){
        int[] DistalChanges = this.DescoverAdditionalDistanceChange();
        double[] values;
        if(DistalChanges.length == 0){
            if(this.canCreateImage()){
                values = this.getCurrentBounds();
                this.img = new BufferedImage((int)values[0],(int)values[1],BufferedImage.TYPE_4BYTE_ABGR);
            }
        }
        else if(DistalChanges[0] == 0 && DistalChanges[1] == 0 && DistalChanges[2] == 0 && DistalChanges[3] == 0){
        }
        else{
            this.img = ImageEditor.resizeImage(img, DistalChanges[0], DistalChanges[1], DistalChanges[2], DistalChanges[3]);
        }
    }
    private boolean canCreateImage(){
        double[]  value;
        if(this.img != null){
            return false;
        }
        else if(this.nodes.size() > 1){
            value = this.getCurrentBounds();
            if(value[0] >= 1 && value[1] >= 1){
                return true;
            }
        }
        return false;
    }
    private void setImageWithInBounds(){
        List<Node> NodesList;
        int i,j,n;
        int[] points;
        p = new Polygon();
        if(this.img == null){
            return;
        }
        else if(this.noNodeUpdated){
            this.noNodeUpdated = false;
            return;
        }
        else{
            if(this.theList.size() == 1){
                NodesList = this.theList.get(0);
                if(this.circled.get(0).getValue()){
                    for(j = 0; j < NodesList.size();j++){
                        points = this.convertToImagePoint(NodesList.get(j));
                        p.addPoint(points[0],points[1]);
                    }
                }
            }
        }
        for(i = 0; i < this.img.getWidth();i++){
            for(j = 0; j < this.img.getHeight();j++){
                if(!p.contains(i, j)){
                    this.img.setRGB(i, j, 0);
                }
            }
        }
    }
    private int[] DescoverAdditionalDistanceChange(){
        int[] configvalues = new int[4];
        if(this.img != null){
            configvalues[0] = (int) ((this.oldLeft - this.Left)/this.ScaleX);
            configvalues[2] = (int) ((this.Right - this.oldRight)/this.ScaleX);
            configvalues[1] = (int) ((this.oldUp - this.Up)/this.ScaleY);
            configvalues[3] = (int) ((this.Down - this.oldDown)/this.ScaleY);
            return configvalues;
        }
        return new int[0];
    }
    
    private  int[] convertToImagePoint(Node n){
        return this.convertToImagePoint(n.getImageX(), n.getImageY());
    }
    private int[] convertToImagePoint(double x, double y){
        double[] distalChanges = this.getDistalChanges();
        x -= this.Left;
        y -= this.Up;
        return new int[]{(int)(x/distalChanges[0]),(int)(y/distalChanges[1])};
    }
    private int[] convertToImageDistance(double x, double y){
        double[] distalChanges = this.getDistalChanges();
        return new int[]{(int)(x/distalChanges[0]),(int)(y/distalChanges[1])};
    }
    private double[] getCurrentBounds(){
        return new double[]{this.Right - this.Left,this.Down -this.Up};
    }
    private double[] getDistalChanges(){
        double oldX = this.Right - this.Left;
        double oldY = this.Down - this.Up;
        if(this.img != null){
            double scaleDiffX = oldX/img.getWidth();
            double scaleDiffY = oldY/img.getHeight();
            return new double[]{scaleDiffX,scaleDiffY};
        }
        return new double[]{oldX,oldY};
    }
    @Override
    public final void NodeUpdated(){
        this.RT.requestNodeUpdate();
    }
    public void refixImage(){
        if(this.img != null){
            return;
        }
        this.img = new BufferedImage(100,100,BufferedImage.TYPE_4BYTE_ABGR);
    }
    private void EffectLines(){
        if(this.nodesUpdated){
            this.CreateLines();
            this.nodesUpdated = false;
        }
        else{
            this.updateLines();
        }
    }
    private void updateLines(){
        int i;
        for(i = 0; i < this.lines.size();i++){
            this.lines.get(i).updateNodes();
        }
    }
    private void CreateLines(){
        int x,y;
        Node current,next;
        List<Node> temp;
        this.lines.clear();
        if(this.hasLines){
            for(x = 0; x < this.theList.size();x++){
                temp = this.theList.get(x);
                if(temp.size() != 1){
                    for(y = 0; y < temp.size();y++){
                        if(y == temp.size() - 1){
                            if(this.getCircled(x)){
                                current = temp.get(y);
                                next = temp.get(0);
                                this.lines.add(new Line(current,next));
                            }
                        }
                        else{
                            current = temp.get(y);
                            next = temp.get(y+1);
                            this.lines.add(new Line(current,next));
                        }
                    }
                }
            }
        }
    }
    
    @Override
    public synchronized void renderMap(Graphics2D g, GraphicConditions c) {
        int i;
        try{
        double[] scale = this.getDistalChanges();
        double WorldScale = c.getScale();
        if(this.img != null){
            g.drawImage(img, c.getStartingPointX((super.getImageX()),this.img.getWidth()*scale[0]),c.getStartingPointY(super.getImageY(), this.img.getHeight()*scale[1]),(int)(img.getWidth()*scale[0]/WorldScale),(int)(img.getHeight()*scale[1]/WorldScale),null);
        }
        
        for(i = 0; i < this.lines.size();i++){
            this.lines.get(i).renderMap(g, c);
        }
        if(c.isShowingNodes()){
            for(i = 0; i < this.nodes.size();i++){
                this.nodes.get(i).renderMap(g, c);
            }
        }
        }catch(Exception e){
            
        }
    }
    public List<List<Node>> getNodesAsList(){
        return this.theList;
    }
    public List<Node> getNodes(){
        return this.nodes;
    }
    @Override
    public Line getClosesLineInArea(double x, double y, double distance){
        List<Line> LN = this.getLinesInArea(x, y, distance);
        double d;
        int i;
        if(LN.isEmpty()){
            return null;
        }
        Line curr = LN.get(0);
        for(i = 1; i < LN.size() - 1;i++){
            d = Math.min(LN.get(i).DistancepointToLine(x, y), curr.DistancepointToLine(x, y));
            if(d == LN.get(i).DistancepointToLine(x, y)){
                curr = LN.get(i);
            }
        }
        return curr;
    }
    @Override
    public List<Line> getLinesInArea(double x,double y,double distance){
        List<Line> LineTemp = new ArrayList();
        Line l;
        int i;
        for(i = 0; i < this.lines.size(); i++){
            l = this.lines.get(i);
            if(this.lines.get(i).DistancepointToLine(x, y)< distance){
                LineTemp.add(l);
            }
        }
        return LineTemp;
    }
    @Override
    public BufferedImage getImage(){
        return this.img;
    }
    @Override
    public boolean getCircled(int i){
        return this.circled.get(i).getValue();
    }

    @Override
    public NodeMesh getNewInstance() {
        return new NodeMesh(super.getGraphicConditions(),super.getUID(),img,theList,this.circled,this.hasLines,Left,Right,Up, Down, oldLeft,oldRight,oldUp,oldDown);
    }

    @Override
    public boolean isInsideHitBox(double x, double y) {
        if(this.Left < x && x < this.Right && this.Up < y && y < this.Down){
            return true;
        }
        return false;
    }

    @Override
    public double[][] getSurroundingPoints() {
        return new double[][]{{this.Left,this.Up},{super.getImageX(),this.Up},{this.Right,this.Up},{this.Right,this.getImageY()},{this.Right,this.Down},{super.getImageX(),this.Down},{this.Left,this.Down},{this.Left,super.getImageY()}};
    }
    @Override
    public void PaintCircle(double x, double y, double distance, int Color) {
        int[] pos = this.convertToImagePoint(x,y);
        int[] dist = this.convertToImageDistance(distance*2,distance*2);
        int xx,yy;
        Shape circle = new Ellipse2D.Double(pos[0]-dist[0],pos[1]-dist[1],dist[0],dist[1]);
        for(xx = pos[0] - dist[0];xx < pos[0] + dist[0];xx++){
            for(yy = pos[1] - dist[1];yy < pos[1] + dist[1];yy++){
                if(circle.contains(xx, yy)&& 0 <= xx && xx < this.img.getWidth()&& 0 <= yy && yy < this.img.getHeight()){
                    this.img.setRGB(xx, yy, Color);
                }
            }
        }
        repaint(pos[0] - dist[0],pos[1] - dist[1],pos[0] + dist[0],pos[1] + dist[1]);
    }

    @Override
    public void PaintCircle(double x, double y, double distance, Color c) {
        this.PaintCircle(x, y, distance, c.getRGB());
    }

    @Override
    public void PaintCircle(double x, double y, double distance, int Color, boolean[][] guide) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void PaintCircle(double x, double y, double distance, Color c, boolean[][] guide) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void PaintCircle(double x, double y, double distance, int Color, int[][] guide) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void PaintCircle(double x, double y, double distance, Color c, int[][] guide) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    int r = 0;
    @Override
    public void PaintSquare(double x, double y, double distance, int Color) {
        int[] pos = this.convertToImagePoint(x,y);
        int[] dist = this.convertToImageDistance(distance/1,distance/1);
        int xx,yy;
        r++;
        System.out.println(r);
        for(xx = pos[0] - dist[0];xx < pos[0] + dist[0];xx++){
            for(yy = pos[1] - dist[1];yy < pos[1] + dist[1];yy++){
                if(0 <= xx && xx < this.img.getWidth()&& 0 <= yy && yy < this.img.getHeight()&&p.contains(xx,yy)){
                    this.img.setRGB(xx, yy, Color);
                }
            }
        }
        repaint();
        repaint(pos[0] - dist[0],pos[1] - dist[1],pos[0] + dist[0],pos[1] + dist[1]);
    }

    @Override
    public void PaintSquare(double x, double y, double distance, Color c) {
        this.PaintSquare(x, y, distance, c.getRGB());
    }

    @Override
    public void PaintSquare(double x, double y, double distance, int Color, boolean[][] guide) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void PaintSquare(double x, double y, double distance, Color c, boolean[][] guide) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void PaintSquare(double x, double y, double distance, int Color, int[][] guide) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void PaintSquare(double x, double y, double distance, Color c, int[][] guide) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int[] getDimension() {
        return new int[]{(int)(this.Down - this.Up),(int)(this.Right-this.Left)};
    }

    @Override
    public int getImageWidth() {
        return (int)(this.Right - this.Left);
    }

    @Override
    public int getImageHeight() {
        return (int)(this.Down- this.Up);
    }
    @Override
    public void setDisabled(boolean disabled){
        super.setDisabled(disabled);
        if(!disabled){
            if(this.RT != null){
                this.RT.kill();
            }
            this.RT = new RequestThread();
            this.RT.start();
        }
    }
    
    @Override
    public void Destroy() {
        this.RT.kill();
    }

    @Override
    public boolean containsNode(Node n) {
        return this.nodes.contains(n);
    }

    @Override
    public boolean containsLine(Node n) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public SaveObjectPackage getSaveObjectPackage() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    private class Bool{
        private boolean value;
        public Bool(boolean value){
            this.value = value;
        }
        public void setValue(boolean value){
            this.value = value;
        }
        public boolean getValue(){
            return this.value;
        }
    }
    public class RequestThread extends Thread{
        private List<Thread> waitingThread = new ArrayList();
        private boolean alive = true;
        private boolean waitActive = false;
        private boolean isrequestRepaint = false;
        private boolean isrequestNodeUpdated = false;
        public void kill(){
            alive = false;
            this.interrupt();
        }
        public void waitfor(){
            if(this.waitActive){
                this.waitingThread.add(Thread.currentThread());
                try {
                    Thread.sleep(10000000);
                } catch (InterruptedException ex) {
                    
                }
            }
        }
        public void requestRepaint(){
            this.isrequestRepaint = true;
            this.interrupt();
        }
        public void requestNodeUpdate(){
            this.isrequestNodeUpdated = true;
            this.interrupt();
        }
        @Override
        public void run(){
            int i;
            while(alive){
                try{
                this.waitActive = true;
                if(this.isrequestNodeUpdated){
                    this.setPriority(Thread.MAX_PRIORITY);
                    this.nodesUpdated();
                }
                else if(this.isrequestRepaint){
                    this.setPriority(Thread.NORM_PRIORITY);
                    this.RepaintUpdated();
                }
                repaint();
                try {
                    this.setPriority(Thread.MIN_PRIORITY);
                    this.waitingThread.clear();
                    Thread.sleep(10000);
                } catch (InterruptedException ex) {
                    
                }
            }
            catch(Exception ex){
                
            }
            }
        }
        private void nodesUpdated(){
            this.isrequestNodeUpdated = false;
            this.isrequestRepaint = false;
            if(!doNotUpdate){
                    EffectLines();
                    ConfigEnds();
                    setImageEdges();
                    repaint();
                    setImageWithInBounds();
                    repaint();
            }
            this.waitActive = false;
            
        }
        private void RepaintUpdated(){
            this.isrequestRepaint = false;
            setImageWithInBounds();
            this.waitActive = false;
        }
    }
}
