/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GraphicalObjects;

import java.util.List;

/**
 *unfinished data
 * @author Allazham
 */
public interface DataPoint {
    public void setName(String name);
    public String getName();
    public List<String> getData();
    public void setData(List<String> data);
    public void addData(List<String> data);
}
