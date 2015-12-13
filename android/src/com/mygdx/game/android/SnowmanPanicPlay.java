package com.mygdx.game.android;

import android.util.Log;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ArrayList;

/**
 * Created by Brian Parker on 12/12/2015.
 */
public class SnowmanPanicPlay extends Game {

    public enum GameState {MENU, PLAY, DEATH}

    public static GameState gameState = GameState.MENU;

    PlayerObject player;
    SpriteBatch batch;
    public Screen curScreen;
    public Screen previousScreen;
    ArrayList<RainDropObject> dropList;

    @Override
    public void create () {
        gameState = GameState.MENU;
        player = new PlayerObject("images/snowman.png", 10, Gdx.graphics.getWidth()/2 - 156/2, 237 + 50, 156, 237);
        dropList = new ArrayList();
        for(int i = 0; i < 7; i++){
            RainDropObject newDrop = new RainDropObject("images/drop.png", 13, 0, 0, 20, 40);
            newDrop.getRandomPosition();
            dropList.add(newDrop);
        }

        batch = new SpriteBatch();

        changeScreen(new MainMenu());
    }

    @Override
    public void render () {
        super.render();





        if(gameState == GameState.PLAY){

            for(RainDropObject drop : dropList){
                drop.moveDown(drop.getSpeed());

                if(drop.getPosy() < 0){
                    drop.getRandomPosition();
                }
            }


            if(Gdx.input.isTouched()) {
                if(Gdx.input.getX() >= player.getPosx() + (player.getWidth())){
                    if(player.getPosx() <= Gdx.graphics.getWidth() - player.getWidth()){
                        player.moveRight(player.getSpeed());
                    }


                }else if (Gdx.input.getX() <= player.getPosx() + 30){
                    if(player.getPosx() >= 0){
                        player.moveLeft(player.getSpeed());
                    }

                }
            }




            Gdx.gl.glClearColor(.1f, .3f, .5f, 0);
            Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
            batch.begin();
            batch.draw(player.getImage(), player.getPosx(), player.getPosy());
            for(RainDropObject drop : dropList){
                batch.draw(drop.getImage(), drop.getPosx(), drop.getPosy());
            }
            batch.end();
        }





    }

    public void changeScreen(Screen s){

        if(curScreen != null){
            curScreen.hide();
            curScreen.dispose();
        }
        curScreen = s;
        setScreen(curScreen);

    }
}
