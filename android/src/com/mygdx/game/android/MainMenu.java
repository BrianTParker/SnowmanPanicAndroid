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
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.Json;
import com.mygdx.game.android.SnowmanPanicPlay.GameState;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;


/**
 * Created by Brian Parker on 12/12/2015.
 */
public class MainMenu implements Screen {

    SpriteBatch batch;
    Texture topImage;
    Texture bottomImage;
    Texture snowmanHead1;
    Texture snowmanHead2;
    URL url;
    SnowmanPanicPlay mainLoop;
    ArrayList<Score> scores;



    BitmapFont font;



    @Override
    public void show() {

        float starPos = Gdx.graphics.getHeight() - 300;
        int count = 1;

        batch = new SpriteBatch();
        topImage = new Texture("images/snowmanpanic.png");
        bottomImage = new Texture("images/spacebar.png");
        snowmanHead1 = new Texture("images/snowman_head.png");
        snowmanHead2 = new Texture("images/snowman_head.png");


        scores = new ArrayList<Score>();

        font = new BitmapFont();
        font.getData().setScale(3, 3);




        try {

            String json = readUrl("http://database.bp72520.webfactional.com/get_scores.php");



            JSONObject mainObject = new JSONObject(json.trim());
            JSONArray JSONscores = mainObject.getJSONArray("scores");
            for (int i = 0; i < JSONscores.length(); ++i) {
                JSONObject val = JSONscores.getJSONObject(i);
                String name = val.getString("name");
                String score = val.getString("score");

                Score newScore = new Score(name, score, Gdx.graphics.getWidth()/2 - 150, starPos -= 50, count);
                scores.add(newScore);
                count ++;

            }






        } catch (MalformedURLException e) {
            Log.d("errormessage", e.getMessage());

        }catch(Exception ex){
            Log.d("errormessage", ex.getMessage());
        }

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
            for(Score score : scores){
                font.draw(batch, score.getOrder() + ". " + score.getName() + "  " + score.getScore(), score.getPosx(), score.getPosy());
            }

            batch.draw(topImage, (Gdx.graphics.getWidth() / 2) - (topImage.getWidth() / 2), Gdx.graphics.getHeight() - topImage.getHeight() - 100);
            batch.draw(bottomImage, (Gdx.graphics.getWidth()/2) - (bottomImage.getWidth()/2), bottomImage.getHeight() + 200);
            batch.draw(snowmanHead1, 50, 500);
            batch.draw(snowmanHead2, Gdx.graphics.getWidth() - 230, 500);
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
