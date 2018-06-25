/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Graphics.Forum;

import java.util.ArrayList;

/**
 *
 * @author Allazham
 */
public class ActionKeys {
    private final ArrayList<KeyHolder> Mouse = new ArrayList();
    private final ArrayList<KeyHolder> Keyboard = new ArrayList();
    private final ArrayList<charHolder> KeyoardChars = new ArrayList();
    ActionKeys(){
        
    }
    public boolean ContainsMouseKey(int key){
        int i;
        for(i = 0; i < Mouse.size();i++){
            if(Mouse.get(i).equals(key)){
                return true;
            }
        }
        return false;
    }
    public boolean ContainsKeyboardKey(int key){
        int i;
        for(i = 0; i < this.Keyboard.size();i++){
            if(this.Keyboard.get(i).equals(key)){
                return true;
            }
        }
        return false;
    }
    public boolean ContainsMouseKeys(int[] key){
        int i,ii;
        for(i = 0; i < key.length;i++){
            Breakable:{
                for(ii = 0; ii < Mouse.size();ii++){
                    if(this.Mouse.get(ii).equals(key[i])){
                        break Breakable;
                    }
                }
                return false;
            }
        }
        return true;
    }
    public boolean ContainsKeyboardKeys(int[] key){
        int i,ii;
        for(i = 0; i < key.length;i++){
            Breakable:{
                for(ii = 0; ii < Keyboard.size();ii++){
                    if(this.Keyboard.get(ii).equals(key[i])){
                        break Breakable;
                    }
                }
                return false;
            }
        }
        return true;
    }
    void addCharKey(){
        
    }
    public int[] getKeyoardKeys(){
        int[] ret = new int[this.Keyboard.size()];
        int i;
        for(i = 0; i < this.Keyboard.size();i++){
            ret[i] = this.Keyboard.get(i).Key;
        }
        return ret;
    }
    public char[] getCharKeys(){
        char ret[] = new char[this.KeyoardChars.size()];
        int i;
        for(i = 0; i < this.KeyoardChars.size();i++){
            ret[i] = this.KeyoardChars.get(i).Key;
        }
        return ret;
    }
    void addMouseButton(int Mouse){
        this.Mouse.add(new KeyHolder(Mouse));
    }
    void addKeyboardButton(int Keyboard){
        this.Keyboard.add(new KeyHolder(Keyboard));
    }
    void addCharButton(char c){
        this.KeyoardChars.add(new charHolder(c));
    }
    void clear(){
       Mouse.clear();
       
       Keyboard.clear();
       this.KeyoardChars.clear();
    }
    private class KeyHolder{
        private int Key;
        private KeyHolder(int key){
            this.Key = key;
        }
        public int getKey(){
            return this.Key;
        }
        public boolean equals(int i){
            return Key == i;
        }
    }
    private class charHolder{
        private char Key;
        private charHolder(char key){
            this.Key = key;
        }
        public char getKey(){
            return this.Key;
        }
        public boolean equals(char i){
            return Key == i;
        }
    }
}
