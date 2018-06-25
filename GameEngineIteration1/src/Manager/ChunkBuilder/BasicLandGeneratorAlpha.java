/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Manager.ChunkBuilder;

import Manager.Chunk;

/**
 *
 * @author Allazham
 */
public class BasicLandGeneratorAlpha extends ChunkBuilderBase{
    private int BiomeDistanceX,BiomeDistanceY;
    private int BiomeDisntaceRandomizor;
    private double varriantChanceCalculator;
    private double Varriant; //under 1;
    private static final int maxBiome = 5;
    public BasicLandGeneratorAlpha(){
        this.BiomeDistanceX = (int) (8*Math.random());
        this.BiomeDistanceY = (int) (8*Math.random());
        this.BiomeDisntaceRandomizor = (int) (3*Math.random());
        this.varriantChanceCalculator = (172*Math.random());
        this.Varriant = Math.random();
        if(this.Varriant < .75){
            this.Varriant = .75;
        }
    }

    @Override
    public Chunk buildChunk(int x, int y) {
      
        return new Chunk(x,y,this.configureChunk(x, y));
    }
    private int configureRandomDistance(double value){
        return (int)(value*this.BiomeDisntaceRandomizor)+(int)value;
    }
    private int[] configBiome(int x, int y){
        int[] ret = new int[5];
        int valueX = this.configureRandomDistance((double) x/(double)BiomeDistanceX);
        int valueY = this.configureRandomDistance((double)y/(double)BiomeDistanceY);
        ret[0] = this.configPersonalBiome(valueX,valueY);
        valueX = this.configureRandomDistance((double) x/(double)BiomeDistanceX);
        valueY = this.configureRandomDistance((double)(y+1)/(double)BiomeDistanceY);
        ret[1] = this.configPersonalBiome(valueX,valueY);
        valueX = this.configureRandomDistance((double) (x+1)/(double)BiomeDistanceX);
        valueY = this.configureRandomDistance((double)y/(double)BiomeDistanceY);
        ret[2] = this.configPersonalBiome(valueX,valueY);
        valueX = this.configureRandomDistance((double) x/(double)BiomeDistanceX);
        valueY = this.configureRandomDistance((double)(y-1)/(double)BiomeDistanceY);
        ret[3] = this.configPersonalBiome(valueX,valueY);
        valueX = this.configureRandomDistance((double) (x-1)/(double)BiomeDistanceX);
        valueY = this.configureRandomDistance((double)y/(double)BiomeDistanceY);
        ret[4] = this.configPersonalBiome(valueX,valueY);
        return ret;
    }
    private int configPersonalBiome(int x, int y){
        if(x < 0){
            x = (int)(Math.abs(x)*.89);
        }
        if(y < 0){
            y = (int)(Math.abs(y)*.89);
        }
        System.out.println(this.varriantChanceCalculator);
        x = (int)this.varriantChanceCalculator*x;
        y = (int) this.varriantChanceCalculator*y + x;
        x = x+y;
        System.out.println(x);
        if(x > maxBiome){
            x %= maxBiome;
        }
        if(x == 0){
            x = 1;
        }
        System.out.println(x);
        return x;
    }
    private int[][] configureChunk(int x, int y){
        int[][] ret =  new int[16][16];
        int[] value = this.configBiome(x, y);
        int i;
        /*
        biomesUsed[0] = value[0];
        for(i = 1; i < value.length;i++){
            breakable:{
                for(ii = 0; ii < biomes;ii++){
                    if(biomesUsed[ii] == value[i]){
                        break breakable;
                    }
                }
                biomesUsed[ii] = value[i];
                biomes++;
            }
        } */
        for(i = 0; i < 16; i++){
            ret = this.brush(ret, 7, 7, i, value[0]);
            ret = this.brush(ret, 7, 15,i, value[1]);
            ret = this.brush(ret, 15,7, i, value[2]);
            ret = this.brush(ret, 7, 0, i, value[3]);
            ret = this.brush(ret, 0, 7, i, value[4]);
        }
        return ret;
    }
    private int[][] brush(int[][] ret,int x,int y,int round,int brush){
        double chanceMeter;
        int xx, yy;
        for(xx = x - round; xx < x + round; xx++){
            for(yy = y - round; yy < y + round;yy++){
                if(xx > -1 && 16 > xx && 16 > yy && yy > -1){
                    ret[xx][yy] = brush;
                }
            }
        }
        return ret;
    }
}
