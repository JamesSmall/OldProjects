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
public class PutTable {
    public PutTable(String name, Object obj){
        this.name = name;
        this.obj = obj;
    }
    Object obj;
    String name;
    @Override
    public boolean equals(Object o){
        if(o instanceof PutTable){
            PutTable pt = (PutTable) o;
            return pt.name.equals(name);
        }
        return false;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 53 * hash + Objects.hashCode(this.name);
        return hash;
    }
}
