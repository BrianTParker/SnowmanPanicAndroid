package com.mygdx.game.android;

import android.content.Intent;
import android.util.Log;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.android.SnowmanPanicPlay.GameState;

/**
 * Created by Brian Parker on 12/12/2015.
 */
public class MainMenu implements Screen {

    SpriteBatch batch;
    Texture topImage;
    Texture bottomImage;

    SnowmanPanicPlay mainLoop;


    @Override
    public void show() {
        batch = new SpriteBatch();
        topImage = new Texture("images/snowmanpanic.png");
        bottomImage = new Texture("images/spacebar.png");

    }

    @Override
    public void render(float delta) {


        if(SnowmanPanicPlay.gameState == GameState.MENU){
            if(Gdx.input.justTouched()) {
                SnowmanPanicPlay.gameState = GameState.PLAY;
            }

            Gdx.gl.glClearColor(.1f, .3f, .5f, 0);
            Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
            batch.begin();
            batch.draw(topImage, (Gdx.graphics.getWidth() / 2) - (topImage.getWidth() / 2), Gdx.graphics.getHeight() - topImage.getHeight() - 100);
            batch.draw(bottomImage, (Gdx.graphics.getWidth()/2) - (bottomImage.getWidth()/2), bottomImage.getHeight() + 100);
            batch.end();
        }

    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
