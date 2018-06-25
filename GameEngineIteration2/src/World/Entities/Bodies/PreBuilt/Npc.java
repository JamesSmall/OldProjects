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
public abstract class Npc extends Body{
//    private LinkedList<requestLocation> LeftHandAnimi = new LinkedList();
    private BodyPart Chest,Head,HandLeft,HandRight;
    public Npc(){
        this.Build();
    }
    public Npc(EntityInfo e){
        super(e);
        this.Build();
    }
    private void Build(){
        Texture HeadTex = MasterLoad.getImage("NakedHead");
        Texture HandTex = MasterLoad.getImage("NakedHand");
        Texture ChestTex = MasterLoad.getImage("NakedChest");
        System.out.println(HandTex);
        Chest = new BodyPart(ChestTex,0,"Chest",1,0,0);
        Head = new BodyPart(HeadTex,2,"Head",.5f,0,0);
        HandRight = new BodyPart(HandTex,1,"HandRight",.5f,1.25f,-.2d);
        HandRight.setTextureFlipped(true);
        HandLeft = new BodyPart(HandTex,1,"HandLeft",.5f,1.25f,.2d);
        
        super.addBodyPart(Head);
        super.addBodyPart(Chest);
        super.addBodyPart(HandRight);
        super.addBodyPart(HandLeft);
    }
    //Texture tex,String name,boolean flipped,int renderOrder,float distance, double degree,float distConvert,double degreeConvert){
    public BodyPart getChest(){
        return this.Chest;
    }
    public BodyPart getHead(){
        return this.Head;
    }
    public BodyPart getHandRight(){
        return this.HandRight;
    }
    public BodyPart getHandLeft(){
        return this.HandLeft;
    }
}
