/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Holder;


/**
 *  an abstract script, incase i tend to inheret more than javascript in the future
 * @author Allazham
 */
public abstract class Script {
    private String name;
    public Script(String name){
        this.name = name;
    }
    public Script(){
        this.name = "UnnamedScriptReader";
    }
    public void setName(String name){
        this.name = name;
    }
    public String getName(){
        return this.name;
    }
    public abstract void setErrorLog(ErrorCall e);
    
    public abstract void runScript(String loc);
    public abstract void RunScriptDirect(String loc);
    
    public abstract void execute(String name);
    public abstract void executeDirect(String name);
    
    public abstract void runLine(String line);
    public abstract void runLineDirectly(String line);
    
    public abstract void bind(String name,String loc);
    public abstract int getRunningAmounts();
    public abstract boolean isRunning();
    public abstract void put(String name, Object o);
    public abstract Script getNewInstance();
}
