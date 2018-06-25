/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Holder.JavaScript;

import java.util.Objects;

/**
 *
 * @author Allazham
 */
public class ExecuteTable {
    public ExecuteTable(String name, String loc){
        this.loc = loc;
        this.name = name;
    }
    String loc,name;
    @Override
    public boolean equals(Object o){
        if(o instanceof ExecuteTable){
            ExecuteTable et = (ExecuteTable) o;
            return et.loc.equals(loc)||et.name.equals(name);
        }
        return false;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.loc);
        hash = 97 * hash + Objects.hashCode(this.name);
        return hash;
    }
}
