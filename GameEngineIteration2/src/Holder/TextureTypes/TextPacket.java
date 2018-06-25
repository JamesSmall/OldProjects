/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Holder.TextureTypes;

/**
 *
 * @author Allazham
 */
public class TextPacket {
    final char c;
    int x,y,dx,dy,width,height;
    public TextPacket(char c,int x, int y){
        this.c = c;
        this.x = x;
        this.y = y;
        this.dx = 0;
        this.dy = 0;
        this.width = 0;
        this.height = 0;
    }
    public TextPacket(char c,int x, int y,int dx,int dy,int width, int height){
        this.c = c;
        this.x = x;
        this.y = y;
        this.dx = dx;
        this.dy = dy;
        this.width = width;
        this.height = height;
    }
    @Override
    public boolean equals(Object o){
        if(o instanceof TextPacket){
            return this.equals(((TextPacket)o).c);
        }
        return false;
    }
    public boolean equals(char c){
        return c == this.c;
    }
    @Override
    public int hashCode() {
        return 83 * 7 + this.c;
    }
}
