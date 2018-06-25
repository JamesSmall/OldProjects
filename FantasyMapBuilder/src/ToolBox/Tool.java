/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ToolBox;

import Forum.DrawingPanel;
import Forum.GraphicConditions;
import java.awt.Cursor;
import java.awt.Graphics2D;
import java.awt.Point;

/**
 *
 * @author Allazham
 */
public interface Tool{
    public abstract void Setup(DrawingPanel p);
    public abstract void renderMap(Graphics2D g, GraphicConditions c);
    public abstract void Destoy();
    public abstract Cursor getCursorInstance(Point p);
}
