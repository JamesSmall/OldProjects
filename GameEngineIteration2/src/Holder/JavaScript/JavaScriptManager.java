/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Holder.JavaScript;

import Holder.ErrorCall;
import Holder.Script;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import javax.script.ScriptEngine;
import javax.script.ScriptException;

/**
 *
 * @author Allazham
 */
public final class JavaScriptManager extends Script{
    private static int ThreadCount = 0;
    private int currentRunning = 0;
    private final ScriptEngine e;
    private final JavaScriptFactory factory;
    private ErrorCall EC;
    final ArrayList<ExecuteTable> ET = new ArrayList();
    final ArrayList<PutTable> PT = new ArrayList();
    JavaScriptManager(JavaScriptFactory factory,ScriptEngine e,String name){
        super(name);
        this.factory = factory;
        this.e = e;
        this.EC = factory;
        this.setupScript(e);
    }
    JavaScriptManager(JavaScriptFactory factory,ScriptEngine e,String name,List<PutTable> PT,List<ExecuteTable> ET){
        super(name);
        this.factory = factory;
        this.e = e;
        this.EC = factory;
        for(PutTable p:PT){
            this.addPutTable0(p);
        }
        for(ExecuteTable ee: ET){
            this.addExecuteTable0(ee);
        }
        this.setupScript(e);
    }
    private void setupScript(ScriptEngine e){
        e.put("Script", this);
    }
    private void addPutTable0(PutTable p){
        if(!this.PT.contains(p)){
            this.PT.add(p);
            this.e.put(p.name, p.obj);
        }
    }
    private void addExecuteTable0(ExecuteTable et){
        if(!this.ET.contains(et)){
            this.ET.add(et);
        }
    }
    @Override
    public void setErrorLog(ErrorCall e) {
        this.EC = e;
    }
    private String getLocationByName(String name){
        for(ExecuteTable e:this.ET){
            if(e.name.equals(name)){
                return e.loc;
            }
        }
        return null;
    }
    @Override
    public void runScript(final String loc) {
       new Thread(new Runnable(){
           @Override
           public void run() {
               currentRunning++;
               RunScriptDirect(loc);
               currentRunning--;
           }
       },"JavaScriptThread"+JavaScriptManager.ThreadCount).start();
        JavaScriptManager.ThreadCount++;
    }

    @Override
    public void RunScriptDirect(String loc) {
        try {
            this.e.eval(new FileReader(loc));
        } catch (ScriptException |FileNotFoundException ex) {
            this.EC.Error(ex);
        }
    }

    @Override
    public void execute(final String name) {
        new Thread(new Runnable(){
           @Override
           public void run() {
               currentRunning++;
               executeDirect(name);
               currentRunning--;
           }
       },"JavaScriptThread"+JavaScriptManager.ThreadCount).start();
        JavaScriptManager.ThreadCount++;
    }

    @Override
    public void executeDirect(String name) {
        String loc = this.getLocationByName(name);
        if(loc != null){
            try {
                this.e.eval(new FileReader(loc));
            } catch (ScriptException |FileNotFoundException ex) {
                this.EC.Error(ex);
            }
        }
    }

    @Override
    public void runLine(final String line) {
        new Thread(new Runnable(){
           @Override
           public void run() {
               currentRunning++;
               runLineDirectly(line);
               currentRunning--;
           }
       },"JavaScriptThread"+JavaScriptManager.ThreadCount).start();
        JavaScriptManager.ThreadCount++;
    }

    @Override
    public void runLineDirectly(String line) {
        try {
            this.e.eval(line);
        } catch (ScriptException ex) {
            this.EC.Error(ex);
        }
    }
    @Override
    public void bind(String name, String loc) {
        this.addExecuteTable0(new ExecuteTable(name,loc));
    }

    @Override
    public int getRunningAmounts() {
        return this.currentRunning;
    }

    @Override
    public boolean isRunning() {
        return (this.currentRunning > 0);
    }

    @Override
    public void put(String name, Object o) {
        this.addPutTable0(new PutTable(name,o));
    }

    @Override
    public JavaScriptManager getNewInstance() {
        return factory.createEngineFromContex(super.getName(), PT, ET);
    }
}
