package com.mygdx.game.android;

import com.badlogic.gdx.Gdx;

import java.util.Random;

/**
 * Created by Brian Parker on 12/12/2015.
 */
public class SnowflakeObject extends GameObject{

    Random rand;
    private String direction;
    float initialPos;

    public SnowflakeObject(String imageString, float speed, float posx, float posy, int width, int height){
        super(imageString, speed, posx, posy, width, height);

        direction = "RIGHT";
        rand = new Random();
        initialPos = posx;
    }


    public String getDirection(){
        return direction;
    }

    public float getInitialPos(){
        return initialPos;
    }

    public void setDirection(String direction){
        this.direction = direction;
    }


    public void changeDirection(){
        if(this.direction == "RIGHT"){
            this.direction = "LEFT";
        }else{
            this.direction = "RIGHT";
        }
    }

    public void getRandomPosition(){

        this.posx = getRandomInt(10, Gdx.graphics.getWidth() - 40);
        this.posy = Gdx.graphics.getHeight() + 100;
        initialPos = posx;


    }

    private int getRandomInt(int min, int max){
        return rand.nextInt(max - min + 1) + min;
    }
}
