package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Fondo {
    Texture texture = new Texture("fondoEspacial.jpeg");

    public void render(SpriteBatch batch) {
        batch.draw(texture, 0, 0, 1920, 1080);
    }
}
