package com.mygdx.battleship.Entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.battleship.Tool.React;
import org.w3c.dom.Text;

public class Cell implements Entity{

    public int height;
    public int width;
    public boolean isShip = false;
    public float x, y;
    public Texture texture;

    React react;

    public Cell(float x, float y, Texture texture, int width, int height){

        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.react = new React(x,y, width, height);

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

    public int getHeight(){
        return height;
    }

    public int getWidth(){
        return width;
    }
    public boolean checkInput(int xCor, int yCor) {

            // Check if input is within cell bounds
            if (xCor >= x && xCor <= x + width && yCor >= y && yCor <= y + height)
                return true;
            else
                return false;

    }

    public void setTexture(Texture texture){
        this.texture = texture;
    }
    public Texture getTexture(){
        return this.texture;
    }
    public void setIsShip(boolean boo){
        this.isShip = boo;
    }
}
