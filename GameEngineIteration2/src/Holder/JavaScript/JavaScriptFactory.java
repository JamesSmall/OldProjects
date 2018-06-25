/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Holder.JavaScript;

import Holder.ErrorCall;
import World.WorldControl;
import java.util.ArrayList;
import java.util.List;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

/**
 *
 * @author Allazham
 */
public class JavaScriptFactory implements ErrorCall{
    private static final WorldControl w = WorldControl.getInstance();
    private final ScriptEngineManager engineFactory = new ScriptEngineManager();
    private ScriptEngine buildEngine(){
        ScriptEngine e = engineFactory.getEngineByName("javascript");
        if(e == null){
            throw new UnsupportedOperationException("Javascript is not supported");
        }
        try {
            e.eval("var Tile = Packages.World.Tile");
            e.eval("var Cell = Packages.World.Cell");
            e.eval("var Chunk = Packages.World.Chunk");
            e.eval("var Entry = Packages.World.Entry");
            e.eval("var Region = Packages.World.Region");
            e.eval("var WorldUtils = Packages.World.WorldUtils");
            e.eval("var WorldControl = Packages.World.WorldControl");
            
            e.eval("var AbstractEntry = Packages.World.Entities.AbstractEntry");
            e.eval("var AbstractGraphicEntry = Packages.World.Entities.AbstractPuppetMasterEntry");
            
            e.eval("var Body = Packages.World.Entities.Bodies.Body");
            e.eval("var BodyPart = Packages.World.Entities.Bodies.BodyPart");
            e.eval("var BodyPartUtil = Packages.World.Entities.Bodies.BodyPartUtil");
            e.eval("var NextLocation = Packages.World.Entities.Bodies.NextLocation");
            e.eval("var PlannedAnimation = Packages.World.Entities.Bodies.PlannedAnimation");
            e.eval("var Follower = Packages.World.Entities.Basic.Follower");
            
            e.eval("function sleep(time){java.lang.Thread.sleep(time);}");
            e.put("World", w);
        } catch (ScriptException ex) {
            
        }
        return e;
    }
    public JavaScriptManager getBasicEngine(){
        return new JavaScriptManager(this,buildEngine(),"unnamedJavaScriptManager");
    }
    public JavaScriptManager getBasicEngine(String name){
        return new JavaScriptManager(this,buildEngine(),name);
    }
    public JavaScriptManager createEngineFromContex(String name, List<PutTable> pt,List<ExecuteTable> et){
        return new JavaScriptManager(this,buildEngine(),name,pt,et);
    }
    public JavaScriptManager createEngineFromContex(String name, List<String> PName, List<Object> obj, List<String> EName, List<String> loc){
        if(PName.size() == obj.size() && EName.size() == loc.size()){
            ArrayList<PutTable> PT = new ArrayList();
            ArrayList<ExecuteTable> ET = new ArrayList();
            int i;
            for(i = 0; i < PName.size();i++){
                PT.add(new PutTable(PName.get(i),obj.get(i)));
            }
            for(i = 0; i < EName.size();i++){
                ET.add(new ExecuteTable(EName.get(i),loc.get(i)));
            }
            this.createEngineFromContex(name, PT, ET);
        }
        throw new UnsupportedOperationException("put table or execute table is unbalanced");
    }
    @Override
    public void Error(Throwable e) {
        
    }
}
