/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package World.Entities.Bodies.PreBuilt;

import Holder.MasterLoad;
import Holder.Texture;
import Save.EntityInfo;
import World.Entities.Bodies.Body;
import World.Entities.Bodies.BodyPart;

/**
 *
 * @author Allazham
 */
public abstract class Animal extends Body{
    private BodyPart Body,Head;
    public Animal(){
        this.Build();
    }
    public Animal(EntityInfo e){
        super(e);
        this.Build();
    }
    private void Build(){
        Texture HeadTex = MasterLoad.getImage("PigHead");
        Texture ChestTex = MasterLoad.getImage("PigBody");
        Body = new BodyPart(ChestTex,0,"Body",1,0,0);
        Head = new BodyPart(HeadTex,2,"Head",.5f,1.5f,0);
        //
        super.addBodyPart(Head);
        super.addBodyPart(Body);
    }
    //Texture tex,String name,boolean flipped,int renderOrder,float distance, double degree,float distConvert,double degreeConvert){
    public BodyPart getBody(){
        return this.Body;
    }
    public BodyPart getHead(){
        return this.Head;
    }
}
