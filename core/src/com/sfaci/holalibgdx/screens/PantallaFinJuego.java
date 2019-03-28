package com.sfaci.holalibgdx.screens;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created by dam on 20/01/17.
 */
public class PantallaFinJuego implements Screen {

    Texture background;
    SpriteBatch batch;

    @Override
    public void show() {
        batch = new SpriteBatch();
        background = new Texture("fondo1.png");
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
}
