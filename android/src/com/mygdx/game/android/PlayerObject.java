package com.mygdx.game.android;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

/**
 * Created by Brian Parker on 12/12/2015.
 */
public class PlayerObject extends GameObject {

    int score;
    int health;

    public PlayerObject(String imageString, float speed, float posx, float posy, int width, int height){
        super(imageString, speed, posx, posy, width, height);
        health = 3;
        score = 0;
        this.updateImage();
    }

    //Getters
    public int getHealth(){
        return health;
    }

    public int getScore(){
        return score;
    }


    //Setters
    public void subtractHealth(){
        health--;
        if(health < 0){
            health = 0;
        }
        this.updateImage();
    }

    public void addHealth(){
        health++;
        if(health > 3){
            health = 3;
        }
        this.updateImage();
    }

    public void updateImage(){
        if(health == 3){
            image = new Texture("images/snowman.png");
            height = 237;

        }else if(health == 2){
            image = new Texture("images/slightly_melted2.png");
            height = 210;

        }else if(health == 1){
            image = new Texture("images/mostly_melted2.png");
            height = 140;

        }else if(health == 0){
            image = new Texture("images/melted.png");
            height = 237;

        }
    }
}
