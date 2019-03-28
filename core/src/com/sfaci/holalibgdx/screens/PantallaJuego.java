package com.sfaci.holalibgdx.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;
import com.sfaci.holalibgdx.Bala;
import com.sfaci.holalibgdx.Nave;
import com.sfaci.holalibgdx.Principal;
import com.sfaci.holalibgdx.Roca;
import com.sfaci.holalibgdx.util.Constantes;

/**
 * Created by dam on 20/01/17.
 */
public class PantallaJuego implements Screen {

    Nave nave;
    Array<Roca> rocas;
    Array<Bala> balas;
    long ultimaRoca;
    Texture background;
    SpriteBatch batch;
    BitmapFont fuente = new BitmapFont(Gdx.files.internal("default.fnt"));

    private Principal juego;

    public PantallaJuego() {

        batch = new SpriteBatch();
    }

    @Override
    public void show() {

        nave = new Nave(new Texture("car2.png"), 0, 0);
        rocas = new Array<Roca>();
        ultimaRoca = TimeUtils.millis();
        balas = new Array<Bala>();

        background = new Texture("fondo2.png");
    }


    @Override
    public void render(float delta) {

        // Limpia la pantalla
        //Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        batch.draw(background, 0, 0);
        batch.end();
        // Pinta la imagen en pantalla
        batch.begin();
        //Cambiamos las coordenadas de la nave para hacer que el spwan se en la parte derechad e la pantalla
        batch.draw(nave.imagen, nave.posicion.y, nave.posicion.x);
        for (Roca roca : rocas)
            batch.draw(roca.imagen, roca.posicion.y, roca.posicion.x);
        for (Bala bala : balas)
            batch.draw(bala.imagen, bala.posicion.y, bala.posicion.x);
        //fuente.draw(batch, "Vidas", 100, 100);
        batch.end();

        moverRocas();
        moverBalas();
        generarRoca();
        comprobarInput();
    }

    private void moverRocas() {

        for (Roca roca : rocas) {
            roca.caer();
            if (roca.posicion.x + roca.imagen.getHeight() <= 0)
                rocas.removeValue(roca, true);

            if (roca.rect.overlaps(nave.rect))
                System.exit(0);

        }
    }

    private void moverBalas() {

        for (Bala bala : balas)
            bala.mover();
    }

    private void generarRoca() {

        if (TimeUtils.millis() - ultimaRoca > Constantes.TIEMPO_ENTRE_ROCAS) {
            Texture imagenRoca = new Texture("ghost.png");
            Roca roca = new Roca(imagenRoca,
                    MathUtils.random(0, Constantes.ANCHURA - imagenRoca.getWidth()),
                    Constantes.ALTURA);
            rocas.add(roca);

            ultimaRoca = TimeUtils.millis();
        }

    }

    private void comprobarInput() {
        // El usuario pulsa la tecla DERECHA
        if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
            nave.mover(new Vector2(10, 0));
        }

        // El usuario pulsa la tecla IZQUIERDA
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            nave.mover(new Vector2(-10, 0));
        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
            Bala bala = new Bala(new Texture("bullet.png"),
                    nave.posicion.x + nave.imagen.getWidth() / 2,
                    nave.posicion.y + nave.imagen.getHeight());
            balas.add(bala);
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
        batch.dispose();
        fuente.dispose();
        ((Game) Gdx.app.getApplicationListener()).setScreen(new PantallaFinJuego());
    }
}
