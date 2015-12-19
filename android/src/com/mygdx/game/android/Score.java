package com.mygdx.game.android;

/**
 * Created by Brian Parker on 12/16/2015.
 */
public class Score {

    String name;
    String score;
    float posx;
    float posy;
    int order;

    public Score(String name, String score, float posx, float posy, int order){
        this.name = name;
        this.score = score;
        this.posx = posx;
        this.posy = posy;
        this.order = order;
    }


    public String getName(){
        return name;
    }

    public String getScore(){
        return score;
    }

    public float getPosx(){
        return posx;
    }

    public float getPosy(){
        return posy;
    }

    public int getOrder(){
        return order;
    }
}
