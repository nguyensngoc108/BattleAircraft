package com.mygdx.battleship.Entities;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;


public interface Entity {

    float x = 0,y = 0;


    void update(float deltaTime);

    void render(SpriteBatch batch);

    float getX();

    float getY();
}