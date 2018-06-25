/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GraphicalObjects;

import java.util.List;

/**
 *this is a manager for adding and removing nodes, this interface
 * is so allow node editing tools to edit any type of list with nodes
 * without an issue of polymorphic means
 * @author Allazham
 */
public interface NodeList {
    public void NodeUpdated();
    public void linkNodes(Node n1,Node n2);
    public void unlinkNodes(Node n1,Node n2);
    public void addNodeInBetween(Node newNode,Node nleft, Node nright);
    public void addNodes(List<Node> e, boolean circled);
    public void removeNodes(List<Node> e);
    public void addNode(Node e);
    public void removeNode(Node e);
    public List<Node> getNodesInArea(double x, double y, double dist);
    public Node getNode(double x, double y, double dist);
    public Line getClosesLineInArea(double x, double y, double distance);
    public List<Line> getLinesInArea(double x,double y,double distance);
    public boolean getCircled(int i);
    public NodeList getNewInstance();
    public void repaint();
    public boolean containsNode(Node n);
    public boolean containsLine(Node n);
}
