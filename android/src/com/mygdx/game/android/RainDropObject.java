package com.mygdx.game.android;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

import java.util.Random;

/**
 * Created by Brian Parker on 12/12/2015.
 */
public class RainDropObject extends GameObject {

    private Random rand;
    private Texture image;

    public RainDropObject(String imageString, float speed, int posx, int posy, int width, int height){
        super(imageString, speed, posx, posy, width, height);
        rand = new Random();
    }


    public void getRandomPosition(){

        this.posx = getRandomInt(10, Gdx.graphics.getWidth() - 40);
        this.posy = getRandomInt(Gdx.graphics.getHeight() + 100, Gdx.graphics.getHeight() + 900);


    }


    private int getRandomInt(int min, int max){
        return rand.nextInt(max - min + 1) + min;
    }


}
