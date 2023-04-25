package com.mygdx.battleship.Entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Ship implements Entity {

    private int length;
    private float x,y;
    private Texture texture;
    private boolean[] hit;
    private boolean isHorizontal;

    public Ship(float x, float y, boolean isHorizontal) {
        this.x = x;
        this.y = y;
        this.length = 200;
        this.texture = new Texture("badlogic.jpg");
        this.isHorizontal = isHorizontal;
    }

    @Override
    public void update(float deltaTime) {

    }

    public void render(SpriteBatch batch) {
        if(isHorizontal)
            batch.draw(texture, x, y,200,50);
        else
            batch.draw(texture, x, y,50,200);
    }

    @Override
    public float getX() {
        return x;
    }

    @Override
    public float getY() {
        return y;
    }

    public void setHit(int index) {
        hit[index] = true;
    }

    public boolean isSunk() {
        for (boolean isHit : hit) {
            if (!isHit) {
                return false;
            }
        }
        return true;
    }

    public int getLength() {
        return length;
    }

    public boolean isHorizontal() {
        return isHorizontal;
    }

}
