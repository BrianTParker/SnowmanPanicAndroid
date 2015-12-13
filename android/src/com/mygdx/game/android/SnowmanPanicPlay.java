package com.mygdx.game.android;

import android.util.Log;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

import java.security.KeyStore;
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
    SnowflakeObject snowflake;
    long previousTime;

    int hitBuffer;
    boolean justGotHit;
    boolean dropSnowflake;

    long startTime;
    long currentTime;

    Sound hitSound;
    Sound snowflakeSound;
    Music menuSong;
    Music mainSong;
    Music deathSong;

    @Override
    public void create () {
        gameState = GameState.MENU;
        player = new PlayerObject("images/snowman.png", 12, Gdx.graphics.getWidth()/2 - 156/2, 237 + 50, 156, 237);
        dropList = new ArrayList();
        for(int i = 0; i < 7; i++){
            RainDropObject newDrop = new RainDropObject("images/drop.png", 13, 0, 0, 20, 40);
            newDrop.getRandomPosition();
            dropList.add(newDrop);
        }

        startTime = System.currentTimeMillis();
        previousTime = 0;
        hitBuffer = 0;
        justGotHit = false;

        batch = new SpriteBatch();

        changeScreen(new MainMenu());
        snowflake = new SnowflakeObject("images/snowflake.png", 7, 0, 0, 20, 40);
        snowflake.getRandomPosition();

        dropSnowflake = false;

        hitSound = Gdx.audio.newSound(Gdx.files.internal("sfx/hit.wav"));
        snowflakeSound = Gdx.audio.newSound(Gdx.files.internal("sfx/snowflake.wav"));
        menuSong = Gdx.audio.newMusic(Gdx.files.internal("music/menutrack.mp3"));
        menuSong.setLooping(true);
        mainSong = Gdx.audio.newMusic(Gdx.files.internal("music/mainsong.mp3"));
        mainSong.setLooping(true);
        deathSong = Gdx.audio.newMusic(Gdx.files.internal("music/deadtrack.mp3"));
        deathSong.setLooping(true);
    }

    @Override
    public void render () {
        super.render();


        currentTime = System.currentTimeMillis();



        if(gameState == GameState.MENU && !menuSong.isPlaying()){
            menuSong.play();
        }

        if(gameState == GameState.PLAY){

            if(menuSong.isPlaying()){
                menuSong.stop();
                menuSong.dispose();
            }
            if(!mainSong.isPlaying()){
                mainSong.play();
            }


            long tDelta = (currentTime - startTime)/1000;

            long thisTime = tDelta;
            if(tDelta % 1 == 0 && thisTime != previousTime){
                previousTime = thisTime;
                if(tDelta % 2 == 0){
                    for(RainDropObject drop : dropList){
                        drop.setSpeed(drop.getSpeed() + 1f);
                    }
                }
                if(tDelta % 20 == 0){
                    dropSnowflake = true;
                    snowflake = new SnowflakeObject("images/snowflake.png", 7, 0, 0, 20, 40);
                    snowflake.getRandomPosition();
                }
            }

            if(justGotHit == true){
                hitBuffer += 1;
                if(hitBuffer >= 50){
                    hitBuffer = 0;
                    justGotHit = false;
                }
            }

            if(dropSnowflake == true){
                if(snowflake.getDirection().equals("RIGHT")){
                    snowflake.moveRight(snowflake.getSpeed()/2);
                }else if (snowflake.getDirection().equals("LEFT")){
                    snowflake.moveLeft(snowflake.getSpeed()/2);
                }
                if(snowflake.getPosx() >= snowflake.getInitialPos() + 30){
                    snowflake.setDirection("LEFT");
                }
                if(snowflake.getPosx() <= snowflake.getInitialPos() - 30){
                    snowflake.setDirection("RIGHT");
                }
                snowflake.moveDown(snowflake.getSpeed());


                if(checkForCollision(snowflake) == true){
                    dropSnowflake = false;
                    player.addHealth();
                    snowflakeSound.play();
                }else if(snowflake.getPosy() <= 0){
                    dropSnowflake = false;
                }
            }

            for(RainDropObject drop : dropList){
                drop.moveDown(drop.getSpeed());

                if(drop.getPosy() < 0){
                    drop.getRandomPosition();
                }

                if(checkForCollision(drop) == true && justGotHit == false){
                    player.subtractHealth();
                    justGotHit = true;
                    hitSound.play();

                    if(player.getHealth() == 0){
                        gameState = GameState.DEATH;
                    }
                }
            }


            if(Gdx.input.isTouched()) {
                if(Gdx.input.getX() >= Gdx.graphics.getWidth()/2){
                    if(player.getPosx() <= Gdx.graphics.getWidth() - player.getWidth()){
                        player.moveRight(player.getSpeed());
                    }


                }else{
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
            if(dropSnowflake == true){
                batch.draw(snowflake.getImage(), snowflake.getPosx(), snowflake.getPosy());
            }
            batch.end();
        }

        if(gameState == GameState.DEATH){
            mainSong.stop();
            mainSong.dispose();
            if(deathSong.isPlaying() == false){
                deathSong.play();
            }



            Gdx.gl.glClearColor(.1f, .3f, .5f, 0);
            Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
            batch.begin();
            batch.draw(player.getImage(), player.getPosx(), player.getPosy());
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

    public boolean checkForCollision(GameObject obj){
        boolean intersect = false;
        Rectangle rect1 = new Rectangle(obj.getPosx(), obj.getPosy(), obj.getWidth(), obj.getHeight());
        Rectangle rect2 = new Rectangle(player.getPosx(), player.getPosy() - 40, player.getImage().getWidth() - 20, player.getImage().getHeight() - 80);

        if(rect2.overlaps(rect1)){
            intersect = true;
        }

        return intersect;
    }
}
