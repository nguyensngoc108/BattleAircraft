package com.mygdx.battleship.Entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.battleship.Tool.React;
import com.mygdx.battleship.Entities.Ship;

public class Cell implements Entity{

    public int height;
    public int width;

    boolean hasShip;

    public float x, y;
    public Texture texture;

    React react;

    Ship ship;

    public Cell(float x, float y, Texture texture, int width, int height){

        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.react = new React(x,y, width, height);
        this.texture = texture;
        this.ship = null;
        this.hasShip = false;


    }

    public void setTexture(Texture texture) {
        this.texture = texture;
    }
    @Override
    public void update(float deltaTime) {

    }

    @Override
    public void render(SpriteBatch batch) {
        batch.draw(texture,x,y,width,height);

    }

    @Override
    public float getX() {
        return x;
    }

    @Override
    public float getY() {
        return y;
    }

    public boolean checkInput(int xCor, int yCor) {

        // Check if input is within cell bounds
        if (xCor >= x && xCor <= x + width && yCor >= y && yCor <= y + height)
            return true;
        else
            return false;

    }

    public boolean hasShip() {
        return hasShip;
    }

    public void setHasShip(boolean hasShip) {
        this.hasShip = hasShip;
    }


    public Ship getShip() {
        return ship;
    }

    public void setShip(Ship ship) {
        this.ship = ship;
    }
}
