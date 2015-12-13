package com.mygdx.game.android;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

/**
 * Created by Brian Parker on 12/12/2015.
 */
public class GameObject {

    protected Texture image;
    protected float speed;
    protected float posx;
    protected float posy;
    protected int width;
    protected int height;
    protected String imageString;

    public GameObject(){};
    public GameObject(String imageString, float speed, float posx, float posy, int width, int height){

        this.imageString = imageString;
        this.speed = speed;
        this.posx = posx;
        this.posy = posy;
        this.width = width;
        this.height = height;
        this.image = new Texture(imageString);
    }

    //Getters
    public Texture getImage(){
        return image;
    }

    public float getSpeed(){
        return speed;
    }

    public float getPosx(){
        return posx;
    }

    public float getPosy(){
        return posy;
    }

    public int getWidth(){
        return width;
    }

    public int getHeight(){
        return height;
    }


    //setters
    public void setSpeed(float speed){
        this.speed = speed;
    }



    //Movement
    public void moveRight(float speed){
        this.posx += speed;
    }

    public void moveLeft(float speed){
        this.posx -= speed;
    }

    public void moveUp(float speed){
        this.posy += speed;
    }

    public void moveDown(float speed){
        this.posy -= speed;
    }






}
