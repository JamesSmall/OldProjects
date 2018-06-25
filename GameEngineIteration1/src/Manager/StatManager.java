/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Manager;

import Graphics.ErrorLog;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Allazham
 */
public class StatManager {
    //primary stats
    private static final int base = 9;
    private int STR,DEX,STAM,INT,WIS,CHAR;
    //secondary stats
    private double HealthNormal,ManaNormal,EnergyNormal,HungerNormal,MovementSpeedNormal,CriticalLightNormal,CriticalHeavyNormal,HitLightNormal,HitHeavyNormal,DiplomacyNormal,ConquestNormal,ACNormal,SpellPowerNormal,SpiritNormal,CarryWeightNormal;
    private double Health,Mana,Energy,Hunger,MovementSpeed,CriticalLight,CriticalHeavy,HitLight,HitHeavy,Diplomacy,Conquest,AC,SpellPower,Spirit,CarryWeight;
    private List<Effect> effect;
    private List<Item> item;
    public StatManager(int level,int STR,int DEX,int STAM, int INT, int WIS, int CHAR, int SPIRIT){
        this.STAM = STAM;
        this.STR = STR;
        this.DEX = DEX;
        this.INT = INT;
        this.WIS = WIS;
        this.CHAR = CHAR;
        this.ConfigureStat();
    }
    public StatManager(int level,int STR,int DEX,int STAM, int INT, int WIS, int CHAR, int SPIRIT,List<Effect> eff){
        this.STAM = STAM;
        this.STR = STR;
        this.DEX = DEX;
        this.INT = INT;
        this.WIS = WIS;
        this.CHAR = CHAR;
        this.ConfigureStat();
        this.effect = eff;
    }
    public StatManager(List<String> args){
        this.StatManagerSetup(args);
    }
    public void ManageStatSetup(List<String> args){
        this.StatManagerSetup(args);
    }
    private void StatManagerSetup(List<String> args){
        List<String> effect;
        String s;
        String[] hold;
        int value = 0;
        int i;
        for(i = 0; i < args.size();i++){
            s = args.get(i);
            s = s.toUpperCase();
            if(args.contains("@")){
                hold = s.substring(1).split("::");
                s = hold[0];
            }
            else{
                try{
                    s = s.substring(2);
                    hold = s.split("=");
                    value = Integer.parseInt(hold[1]);
                    s = hold[0];
                }
                catch(NumberFormatException ex){
                    ErrorLog.error(ex);
                    continue;
                }
            }
            switch(s){
                //fun stuff
                case"Start":
                    effect = new ArrayList();
                    if(hold[1].contains("EFFECT")){
                        while(!s.toLowerCase().contains("@end::effect")&& i+1 != args.size()){
                            effect.add(args.get(i));
                            i++;
                        }
                        i++;
                        this.effect.add(new Effect(effect,this));
                    }
                    if(hold[1].contains("ITEM")){
                        while(!s.toLowerCase().contains("@end::item")&& i+1 != args.size()){
                            effect.add(args.get(i));
                            i++;
                        }
                        i++;
                        this.item.add(new Item(effect,this));
                    }
                    break;
                case"STR":
                    this.STR = value;
                    break;
                case"DEX":
                    this.DEX = value;
                    break;
                case"STAM":
                    this.STAM = value;
                    break;
                case"INT":
                    this.INT = value;
                    break;
                case"WIS":
                    this.WIS = value;
                    break;
                case"CHAR":
                    this.CHAR = value;
                    break;
                    /*
                     * deal with the current status
                     * 
                     */
                case"HEALTH":
                    this.Health = value;
                    break;
                case"MANA":
                    this.Mana = value;
                    break;
                case"ENERGY":
                    this.Energy = value;
                    break;
                case"HUNGER":
                    this.Hunger = value;
                    break;
                case"MOVEMENTSPEED":
                    this.MovementSpeed = value;
                    break;
                case"CRITICALLIGHT":
                    this.CriticalLight = value;
                    break;
                case"CRITICALHEAVY":
                    this.CriticalHeavy = value;
                    break;
                case"HITHEAVY":
                    this.HitHeavy = value;
                    break;
                case"HITLIGHT":
                    this.HitLight = value;
                    break;
                case"DIPLOMACY":
                    this.Diplomacy = value;
                    break;
                case"AC":
                    this.AC = value;
                    break;
                case"SPELLPOWER":
                    this.SpellPower = value;
                    break;
                case"SPIRIT":
                    this.Spirit = value;
                    break;
                case"CARRYWEIGHT":
                    this.CarryWeight = value;
                    break;
                case"HEALTHNORMAL":
                    this.HealthNormal = value;
                    break;
                case"MANANORMAL":
                    this.ManaNormal = value;
                    break;
                case"ENERGYNORMAL":
                    this.EnergyNormal = value;
                    break;
                case"HUNGERNORMAL":
                    this.HungerNormal = value;
                    break;
                case"MOVEMENTSPEEDNORMAL":
                    this.MovementSpeedNormal = value;
                    break;
                case"CRITICALLIGHTNORMAL":
                    this.CriticalLightNormal = value;
                    break;
                case"CRITICALHEAVYNORMAL":
                    this.CriticalHeavyNormal = value;
                    break;
                case"HITHEAVYNORMAL":
                    this.HitHeavyNormal = value;
                    break;
                case"HITLIGHTNORMAL":
                    this.HitLightNormal = value;
                    break;
                case"DIPLOMACYNORMAL":
                    this.DiplomacyNormal = value;
                    break;
                case"ACNORMAL":
                    this.ACNormal = value;
                    break;
                case"SPELLPOWERNORMAL":
                    this.SpellPowerNormal = value;
                    break;
                case"SPIRITNORMAL":
                    this.SpiritNormal = value;
                    break;
                case"CARRYWEIGHTNORMAL":
                    this.CarryWeightNormal = value;
                    break;
                case"END":
                    if(args.get(i).equalsIgnoreCase("@end::stats;")){
                        return;
                    }
                    break;
                default:
                    ErrorLog.error(new Exception("Error on reading file, loading into stats, unreconized operation:"+s));
                    break;
            }
        }
    }
    private void ConfigureStat(){
        this.HealthNormal = 75 + StatManager.StatAdjuster(this.STAM)*15.5;
        this.ManaNormal = 55 + StatManager.StatAdjuster(this.INT);
        this.EnergyNormal = 75 + StatManager.StatAdjuster(this.DEX)*7.25 + StatManager.StatAdjuster(this.STAM)*7.25;
        this.HungerNormal = 75 + StatManager.StatAdjuster(this.STAM)*7.25+StatManager.StatAdjuster(this.WIS)*7.25;
        this.MovementSpeedNormal = 5 + StatManager.StatAdjuster(this.DEX);
        this.CriticalLightNormal = StatManager.StatAdjuster(this.DEX);
        this.CriticalHeavyNormal = StatManager.StatAdjuster(this.STR);
        this.HitHeavyNormal =  15 + StatManager.StatAdjuster(this.STR)*2.5;
        this.HitLightNormal =  15 + StatManager.StatAdjuster(this.DEX)*4;
        this.ACNormal = 15 + StatManager.StatAdjuster(this.DEX)*3;
        this.DiplomacyNormal = StatManager.StatAdjuster(this.WIS) + StatManager.StatAdjuster(this.CHAR);
        this.ConquestNormal = StatManager.StatAdjuster(this.STR) + StatManager.StatAdjuster(this.CHAR);
        this.SpellPowerNormal = StatManager.StatAdjuster(this.INT);
        this.CarryWeightNormal =  15 + StatManager.StatAdjuster(this.STR)*2.5;
        this.SpiritNormal = 15 + StatManager.StatAdjuster(this.WIS)*2.5;
        this.setNormal();
    }
    public void setNormal(){
        this.Health = this.HealthNormal;
        this.Mana = ManaNormal;
        this.Energy = this.EnergyNormal;
        this.Hunger = this.HungerNormal;
        this.MovementSpeed = this.MovementSpeedNormal;
        this.CriticalLight = this.CriticalLightNormal;
        this.CriticalHeavy = this.CriticalHeavyNormal;
        this.HitHeavy = this.HitHeavyNormal;
        this.HitLight = this.HitLightNormal;
        this.Diplomacy = this.DiplomacyNormal;
        this.Conquest = this.ConquestNormal;
        this.AC = this.ACNormal;
        this.SpellPower = this.SpellPowerNormal;
        this.CarryWeight = this.CarryWeightNormal;
        this.Spirit = this.SpiritNormal;
    }
    private static double StatAdjuster(int stat){
        return (stat-StatManager.base)/2;
    }
    /*
     * 
     * primary statues
     */
    public int getStrength(){
        return this.STR;
    }
    public int getStanima(){
        return this.STAM;
    }
    public int getDexterity(){
        return this.DEX;
    }
    public int getWisdom(){
        return this.WIS;
    }
    public int getIntellect(){
        return this.INT;
    }
    public int getCharsima(){
        return this.CHAR;
    }
    public void setStrength(int STR){
        this.STR = STR;
        this.ConfigureStat();
    }
    public void setStanima(int STAM){
        this.STAM = STAM;
        this.ConfigureStat();
    }
    public void setDexterity(int DEX){
        this.DEX = DEX;
        this.ConfigureStat();
    }
    public void setWisdom(int WIS){
        this.WIS = WIS;
        this.ConfigureStat();
    }
    public void setIntellect(int INT){
        this.INT = INT;
        this.ConfigureStat();
    }
    public void setCharisma(int CHAR){
        this.CHAR = CHAR;
        this.ConfigureStat();
    }
    public void addStrength(int STR){
        this.STR += STR;
        this.ConfigureStat();
    }
    public void addStanima(int STAM){
        this.STAM += STAM;
        this.ConfigureStat();
    }
    public void addDexterity(int DEX){
        this.DEX += DEX;
        this.ConfigureStat();
    }
    public void addWisdom(int WIS){
        this.WIS += WIS;
        this.ConfigureStat();
    }
    public void addIntellect(int INT){
        this.INT += INT;
        this.ConfigureStat();
    }
    public void addCharisma(int CHAR){
        this.CHAR += CHAR;
        this.ConfigureStat();
    }
    public void setHealth(double Health){
        this.Health += Health;
    }
    public void setMana(double Mana){
        this.Mana = Mana;
    }
    public void setEnergy(double Energy){
        this.Energy = Energy;
    }
    public void setHunger(double Hunger){
        this.Hunger = Hunger;
    }
    public void setMovemenetSpeed(double MovementSpeed){
        this.MovementSpeed = MovementSpeed;
    }
    public void setCriticalLight(double crit){
        this.CriticalLight = crit;
    }
    public void setCriticalHeavy(double crit){
        this.CriticalHeavy = crit;
    }
    public void setHitHeavy(double hit){
        this.HitHeavy = hit;
    }
    public void setHitLight(double hit){
        this.HitLight = hit;
    }
    public void setDiplomacy(double dip){
        this.Diplomacy = dip;
    }
    public void setConquest(double Conquest){
        this.Conquest = Conquest;
    }
    public void setAC(double AC){
        this.AC = AC;
    }
    public void setSpellPower(double SpellPower){
        this.SpellPower = SpellPower;
    }
    public void setCarryWeight(double Carry){
        this.CarryWeight = Carry;
    }
    public void setSpirit(double Spirit){
        this.Spirit = Spirit;
    }
    /*
     * 
     * add Stats;
     */
    public void addHealth(double Health){
        this.Health += Health;
    }
    public void addMana(double Mana){
        this.Mana += Mana;
    }
    public void addEnergy(double Energy){
        this.Energy += Energy;
    }
    public void addHunger(double Hunger){
        this.Hunger += Hunger;
    }
    public void addMovemenetSpeed(double MovementSpeed){
        this.MovementSpeed += MovementSpeed;
    }
    public void addCriticalLight(double crit){
        this.CriticalLight += crit;
    }
    public void addCriticalHeavy(double crit){
        this.CriticalHeavy += crit;
    }
    public void addHitHeavy(double hit){
        this.HitHeavy += hit;
    }
    public void addHitLight(double hit){
        this.HitLight += hit;
    }
    public void addDiplomacy(double dip){
        this.Diplomacy += dip;
    }
    public void addConquest(double Conquest){
        this.Conquest += Conquest;
    }
    public void addAC(double AC){
        this.AC += AC;
    }
    public void addSpellPower(double SpellPower){
        this.SpellPower += SpellPower;
    }
    public void addCarryWeight(double Carry){
        this.CarryWeight += Carry;
    }
    public void addSpirit(double Spirit){
        this.Spirit += Spirit;
    }
    /*
     * 
     * get Current Stats
     */
    public double getHealth(){
        return this.Health;
    }
    public double getMana(){
        return this.Mana;
    }
    public double getEnergy(double Energy){
        return this.Energy;
    }
    public double getHunger(double Hunger){
        return this.Hunger;
    }
    public double getMovemenetSpeed(){
        return this.MovementSpeed;
    }
    public double getCriticalLight(){
        return this.CriticalLight;
    }
    public double getCriticalHeavy(){
        return this.CriticalHeavy;
    }
    public double getHitHeavy(){
        return this.HitHeavy;
    }
    public double getHitLight(){
        return this.HitLight;
    }
    public double getDiplomacy(){
        return this.Diplomacy;
    }
    public double getConquest(){
        return this.Conquest;
    }
    public double getAC(){
        return this.AC;
    }
    public double getSpellPower(){
        return this.SpellPower;
    }
    public double getCarryWeight(){
        return this.CarryWeight;
    }
    public double getSpirit(){
        return this.Spirit;
    }
    /*
     * 
     * normals set
     */
    public void setNormalHealth(double Health){
        this.HealthNormal = Health;
    }
    public void setNormalMana(double Mana){
        this.ManaNormal = Mana;
    }
    public void setNormalEnergy(double Energy){
        this.EnergyNormal = Energy;
    }
    public void setNormalHunger(double Hunger){
        this.HungerNormal = Hunger;
    }
    public void setNormalMovemenetSpeed(double MovementSpeed){
        this.MovementSpeedNormal = MovementSpeed;
    }
    public void setNormalCriticalLight(double crit){
        this.CriticalLightNormal = crit;
    }
    public void setNormalCriticalHeavy(double crit){
        this.CriticalHeavyNormal = crit;
    }
    public void setNormalHitHeavy(double hit){
        this.HitHeavyNormal = hit;
    }
    public void setNormalHitLight(double hit){
        this.HitLightNormal = hit;
    }
    public void setNormalDiplomacy(double dip){
        this.DiplomacyNormal = dip;
    }
    public void setNormalConquest(double Conquest){
        this.ConquestNormal = Conquest;
    }
    public void setNormalAC(double AC){
        this.ACNormal = AC;
    }
    public void setNormalSpellPower(double SpellPower){
        this.SpellPowerNormal = SpellPower;
    }
    public void setNormalCarryWeight(double Carry){
        this.CarryWeightNormal = Carry;
    }
    public void setNormalSpirit(double Spirit){
        this.SpiritNormal = Spirit;
    }
    public void addNormalHealth(double Health){
        this.HealthNormal += Health;
    }
    /*
     * 
     * get adding normal stats
     */
    public void addNormalMana(double Mana){
        this.ManaNormal += Mana;
    }
    public void addNormalEnergy(double Energy){
        this.EnergyNormal += Energy;
    }
    public void addNormalHunger(double Hunger){
        this.HungerNormal += Hunger;
    }
    public void addNormalMovemenetSpeed(double MovementSpeed){
        this.MovementSpeedNormal += MovementSpeed;
    }
    public void addNormalCriticalLight(double crit){
        this.CriticalLightNormal += crit;
    }
    public void addNormalCriticalHeavy(double crit){
        this.CriticalHeavyNormal += crit;
    }
    public void addNormalHitHeavy(double hit){
        this.HitHeavyNormal += hit;
    }
    public void addNormalHitLight(double hit){
        this.HitLightNormal += hit;
    }
    public void addNormalDiplomacy(double dip){
        this.DiplomacyNormal += dip;
    }
    public void addNormalConquest(double Conquest){
        this.ConquestNormal += Conquest;
    }
    public void addNormalAC(double AC){
        this.ACNormal += AC;
    }
    public void addNormalSpellPower(double SpellPower){
        this.SpellPowerNormal += SpellPower;
    }
    public void addNormalCarryWeight(double Carry){
        this.CarryWeightNormal += Carry;
    }
    public void addNormalSpirit(double Spirit){
        this.SpiritNormal += Spirit;
    }
    /*
     * 
     * get Normal Stats
     */
    public double getNormalHealth(){
        return this.HealthNormal;
    }
    public double getNormalMana(){
        return this.ManaNormal;
    }
    public double getNormalEnergy(double Energy){
        return this.EnergyNormal;
    }
    public double getNormalHunger(double Hunger){
        return this.HungerNormal;
    }
    public double getNormalMovemenetSpeed(){
        return this.MovementSpeedNormal;
    }
    public double getNormalCriticalLight(){
        return this.CriticalLightNormal;
    }
    public double getNormalCriticalHeavy(){
        return this.CriticalHeavyNormal;
    }
    public double getNormalHitHeavy(){
        return this.HitHeavyNormal;
    }
    public double getNormalHitLight(){
        return this.HitLightNormal;
    }
    public double getNormalDiplomacy(){
        return this.DiplomacyNormal;
    }
    public double getNormalConquest(){
        return this.ConquestNormal;
    }
    public double getNormalAC(){
        return this.ACNormal;
    }
    public double getNormalSpellPower(){
        return this.SpellPowerNormal;
    }
    public double getNormalCarryWeight(){
        return this.CarryWeightNormal;
    }
    public double getNormalSpirit(){
        return this.SpiritNormal;
    }
    public List<String> getSaveData(){
        int i;
        List<String> save = new ArrayList();
        //secondary fun
        save.add("@Start::STATS;");
        save.add("::STR=" + this.STR+";");
        save.add("::DEX=" + this.DEX+";");
        save.add("::STAM=" + this.STAM+";");
        save.add("::INT=" + this.INT+";");
        save.add("::WIS="+this.WIS+";");
        save.add("::CHAR="+this.CHAR+";");
        //secondary running
        save.add("::Health="+this.Health+";");
        save.add("::Mana="+this.Mana+";");
        save.add("::Energy="+this.Energy+";");
        save.add("::Hunger="+this.Hunger+";");
        save.add("::MovementSpeed="+this.MovementSpeed+";");
        save.add("::CriticalLight="+this.CriticalLight+";");
        save.add("::CriticalHeavy="+this.CriticalHeavy+";");
        save.add("::HitLight="+this.HitLight+";");
        save.add("::HitHeavy="+this.HitHeavy+";");
        save.add("::Diplomacy"+this.Diplomacy+";");
        save.add("::AC="+this.AC+";");
        save.add("::SpellPower="+this.SpellPower+";");
        save.add("::Spirit="+this.Spirit+";");
        save.add("::CarryWeight="+this.CarryWeight+";");
        //secondary normals
        save.add("::Health=Normal"+this.HealthNormal+";");
        save.add("::Mana=Normal"+this.ManaNormal+";");
        save.add("::EnergyNormal="+this.EnergyNormal+";");
        save.add("::HungerNormal="+this.HungerNormal+";");
        save.add("::MovementSpeedNormal="+this.MovementSpeedNormal+";");
        save.add("::CriticalLightNormal="+this.CriticalLightNormal+";");
        save.add("::CriticalHeavyNormal="+this.CriticalHeavyNormal+";");
        save.add("::HitLightNormal="+this.HitLightNormal+";");
        save.add("::HitHeavyNormal="+this.HitHeavyNormal+";");
        save.add("::DiplomacyNormal"+this.DiplomacyNormal+";");
        save.add("::ACNormal="+this.ACNormal+";");
        save.add("::SpellPowerNormal="+this.SpellPowerNormal+";");
        save.add("::SpiritNormal="+this.SpiritNormal+";");
        save.add("::CarryWeightNormal="+this.CarryWeightNormal+";");
        for(i = 0; i < this.effect.size();i++){
            save.addAll(this.effect.get(i).getSaveData());
        }
        for(i = 0; i < this.item.size();i++){
            save.addAll(this.item.get(i).getSaveData());
        }
        //private int STR,DEX,STAM,INT,WIS,CHAR;
        //secondary stats
        //private double HealthNormal,ManaNormal,EnergyNormal,HungerNormal,MovementSpeedNormal,CriticalLightNormal,CriticalHeavyNormal,HitLightNormal,HitHeavyNormal,DiplomacyNormal,ConquestNormal,ACNormal,SpellPowerNormal,SpiritNormal,CarryWeightNormal;
        //private double Health,Mana,Energy,Hunger,MovementSpeed,
        //CriticalLight,CriticalHeavy,HitLight,HitHeavy,Diplomacy,
        //Conquest,AC,SpellPower,Spirit,CarryWeight;
        save.add("@End::STATS;");
        return save;
    }
}
