package com.mygdx.game.android;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import com.mygdx.game.android.SnowmanPanicPlay.GameState;

/**
 * Created by Brian Parker on 12/18/2015.
 */

public class DeathScreen implements Screen {

    MyTextInputListener listener;
    String score;
    public DeathScreen(String score){
        this.score = score;
    }
    boolean displayNameBox;
    Handler handler;
    @Override
    public void show() {
        listener = new MyTextInputListener();
        handler = new Handler(Looper.getMainLooper());
        displayNameBox = true;
        handler.postDelayed(new Runnable() {
            public void run() {
                Gdx.input.getTextInput(listener, "Enter Name", "", "Enter Name for leaderboard");
            }
        }, 500);

    }

    @Override
    public void render(float delta) {



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

    public class MyTextInputListener implements Input.TextInputListener {
        @Override
        public void input (String text) {
            try {
                String json = readUrl("http://database.bp72520.webfactional.com/insert_score.php?name=" + text + "&score=" + score + "&key=hunter2");
                JSONObject mainObject = new JSONObject(json.trim());
                SnowmanPanicPlay.gameState = GameState.RESET;
                displayNameBox = true;
            } catch (Exception e) {
                e.printStackTrace();

            }
        }

        @Override
        public void canceled () {
            SnowmanPanicPlay.gameState = GameState.RESET;
            displayNameBox = true;
        }
    }

    private static String readUrl(String urlString) throws Exception {
        BufferedReader reader = null;
        try {
            URL url = new URL(urlString);
            reader = new BufferedReader(new InputStreamReader(url.openStream()));
            StringBuffer buffer = new StringBuffer();
            int read;
            char[] chars = new char[1024];
            while ((read = reader.read(chars)) != -1)
                buffer.append(chars, 0, read);

            return buffer.toString();
        } finally {
            if (reader != null)
                reader.close();
        }
    }
}
