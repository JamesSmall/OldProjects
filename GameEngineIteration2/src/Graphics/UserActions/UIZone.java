/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Graphics.UserActions;

import org.lwjgl.util.vector.Vector2f;

/**
 *
 * @author Allazham
 */
public class UIZone {
    private Vector2f[] polygon;
    private UIZoneConnector u;
    public UIZone(Vector2f[] v){
        this.polygon = v;
    }
    public UIZone(UIZoneConnector u){
        this.u = u;
    }
    public void setZonePoints(Vector2f[] polygon){
        this.polygon = polygon;
    }
    public Vector2f[] getZonePoints(){
        return this.polygon;
    }
    public boolean isDestroyable(){
        return (this.polygon == null && this.u == null);
    }
    private boolean onSegment(Vector2f p, Vector2f q, Vector2f r)
    {
        return q.x <= Math.max(p.x, r.x) && q.x >= Math.min(p.x, r.x) &&
                q.y <= Math.max(p.y, r.y) && q.y >= Math.min(p.y, r.y);
    }
    // To find orientation of ordered triplet (p, q, r).
    // The function returns following values
    // 0 --> p, q and r are colinear
    // 1 --> Clockwise
    // 2 --> Counterclockwise
    public void setUIZoneConnector(UIZoneConnector u){
        this.u = u;
    }
    private int orientation(Vector2f p, Vector2f q, Vector2f r)
    {
        int val = (int)((q.y - p.y) * (r.x - q.x) - (q.x - p.x) * (r.y - q.y));
 
        if (val == 0) 
            return 0;  // colinear
        return (val > 0)? 1: 2; // clock or counterclock wise
    }
 
    // The function that returns true if line segment 'p1q1'
    // and 'p2q2' intersect.
    private boolean doIntersect(Vector2f p1, Vector2f q1, Vector2f p2, Vector2f q2)
    {
    // Find the four orientations needed for general and
    // special cases
        int o1 = orientation(p1, q1, p2);
        int o2 = orientation(p1, q1, q2);
        int o3 = orientation(p2, q2, p1);
        int o4 = orientation(p2, q2, q1);
 
        // General case
        if (o1 != o2 && o3 != o4)
            return true;
 
        // Special Cases
        // p1, q1 and p2 are colinear and p2 lies on segment p1q1
        if (o1 == 0 && onSegment(p1, p2, q1)) 
            return true;
 
        // p1, q1 and p2 are colinear and q2 lies on segment p1q1
        if (o2 == 0 && onSegment(p1, q2, q1)) 
            return true;
 
        // p2, q2 and p1 are colinear and p1 lies on segment p2q2
        if (o3 == 0 && onSegment(p2, p1, q2)) 
            return true;
 
        return o4 == 0 && onSegment(p2, q1, q2); 
    }
 
    // Returns true if the point p lies inside the polygon[] with n vertices
    public boolean isInside(float x, float y){
        return this.isInside(new Vector2f(x,y));
    }
    public boolean isInside(Vector2f p)
    {
        this.u.Update(this);
        // There must be at least 3 vertices in polygon[]
        if (polygon.length < 3)  
            return false;
        // Create a point for line segment from p to infinite
        Vector2f extreme = new Vector2f(10000000f, p.y);
 
        // Count intersections of the above line with sides of polygon
        int count = 0, i = 0;
        do
        {
            int next = (i+1)%polygon.length;
        // Check if the line segment from 'p' to 'extreme' intersects
        // with the line segment from 'polygon[i]' to 'polygon[next]'
            if (doIntersect(polygon[i], polygon[next], p, extreme))
            {
            // If the point 'p' is colinear with line segment 'i-next',
            // then check if it lies on segment. If it lies, return true,
            // otherwise false
                if (orientation(polygon[i], p, polygon[next]) == 0)
                    return onSegment(polygon[i], p, polygon[next]);
                count++;
            }
            i = next;
        } while (i != 0);
    
        // Return true if count is odd, false otherwise
        return count%2 == 1;  // Same as (count%2 == 1)
    }
}
