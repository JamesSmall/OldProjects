/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package World.Entities.ScriptManaged;

import Holder.Script;
import Save.Save;
import Save.SaveObject;
import Save.SaveObjectPackage;
import World.Cell;
import World.Entities.AbstractPuppetMasterEntry;
import World.Entry;
import java.io.IOException;

/**
 *
 * @author Allazham
 */
public class ScriptedEntity extends AbstractPuppetMasterEntry{
    private boolean firstRun = true;
    private Cell c;
    private Entry e;
    private Script script;
    private String onLoad,onUpdate,onSave;
    public ScriptedEntity(Entry e,Script base,String onLoad,String onUpdate,String onSave){
        super(e);
        this.e = e;
        this.c = e.getCell();
        this.onLoad = onLoad;
        this.onUpdate = onUpdate;
        this.onSave = onSave;
        this.script = base.getNewInstance();
        this.script.put("ent", e);
        this.script.put("cell", c);
        this.script.put("AttributeList", super.getAttributeList());
        this.script.put("ItemList", super.getItemList());
    }
    @Override
    public void Update(int delta) {
        //this.script.put("delta", delta);
        if(this.firstRun){
            this.script.executeDirect(onLoad);
            this.firstRun = false;
        }
        this.script.executeDirect(onUpdate);
    }
    @Override
    public Cell getCell() {
        return super.getCell();
    }

    @Override
    public void setCell(Cell c) {
        super.setCell(c);
        c = this.c;
        this.script.put("cell", c);
    }
    @Override
    public SaveObjectPackage getSave() {
        this.script.executeDirect(this.onSave);
        SaveObjectPackage pack = super.getEntry().getSave();
        pack.setMainData(new String[]{this.getSaveIdentity()});
        pack.addSaveObject(new SaveObjectScript(script,this.onLoad,this.onUpdate,this.onSave));
        return pack;
    }
    
    @Override
    public String getSaveIdentity() {
        return "ScriptedEntry,"+e.getSaveIdentity();
    }

    @Override
    public void disposeEntry() {
        
    }

    @Override
    public boolean isPersistent() {
        return false;
    }
    public class SaveObjectScript extends SaveObject{
        String info;
        public SaveObjectScript(Script script, String onLoad,String onUpdate,String onSave){
            info = new StringBuilder(script.getName()).append(",").append(onLoad).append(",").append(onUpdate).append(",").append(onSave).toString();
        }
        @Override
        public void addToSave(Save pw) throws IOException {
            pw.print(info);
        }
    }
}
