package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ArrayList;
import java.util.List;

public class Jugador {
    Animacion animacion = new Animacion(16,
            new Texture("express_0.png"),
            new Texture("express_1.png"),
            new Texture("express_0.png")
    );

    float x, y, w, h, v;
    List<Disparo> disparos = new ArrayList<>();
    int vidas = 3;
    int puntos = 0;
    boolean muerto = false;
    Temporizador temporizadorFireRate = new Temporizador(20);
    Temporizador temporizadorRespawn = new Temporizador(120, false);

    Jugador() {
        x = 100;
        y = 100;
        w = 43 * 3;
        h = 21 * 3;
        v = 10;
    }

    void update() {
        for (Disparo disparo : disparos) disparo.update();

        if (Gdx.input.isKeyPressed(Input.Keys.D)) x += v;
        if (Gdx.input.isKeyPressed(Input.Keys.A)) x -= v;
        if (Gdx.input.isKeyPressed(Input.Keys.W)) y += v;
        if (Gdx.input.isKeyPressed(Input.Keys.S)) y -= v;

        if (Gdx.input.isKeyPressed(Input.Keys.SPACE) && temporizadorFireRate.suena() && !muerto) {
            disparos.add(new Disparo(x + w  / 2, y + h -10));
            disparos.add(new Disparo(x + w / 2, y));
        }

        if (x < 0) x = 0;

        if (temporizadorRespawn.suena()) {
            muerto = false;
        }
    }

    void render(SpriteBatch batch) {
        if (muerto) batch.setColor(1,1,1,0.25f);
        batch.draw(animacion.obtenerFrame(), x, y, w, h);
        if (muerto) batch.setColor(1,1,1,1);
        for (Disparo disparo : disparos) disparo.render(batch);
    }

    public void morir() {
        vidas--;
        muerto = true;
        temporizadorRespawn.activar();
    }
}
