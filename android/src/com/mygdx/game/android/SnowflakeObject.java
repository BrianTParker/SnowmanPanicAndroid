package com.mygdx.game.android;

/**
 * Created by Brian Parker on 12/12/2015.
 */
public class SnowflakeObject extends GameObject{


    private String direction;

    public SnowflakeObject(String imageString, float speed, int posx, int posy, int width, int height){
        super(imageString, speed, posx, posy, width, height);

        direction = "RIGHT";
    }


    public void changeDirection(){
        if(this.direction == "RIGHT"){
            this.direction = "LEFT";
        }else{
            this.direction = "RIGHT";
        }
    }
}
