package com.mygdx.battleship.Entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Ship implements Entity {
    Texture cellTexture;
    private int length;
    private float x,y;

    private Cell[] cells;
    private Texture texture;
    private boolean[] hit;
    private boolean isHorizontal;

    private int size;
    private boolean isAlive;

    public Ship(float x, float y, boolean isHorizontal, int size) {
        this.x = x;
        this.y = y;
        this.length=1;
        this.texture = new Texture("badlogic.jpg");
        this.isHorizontal = isHorizontal;
        this.isAlive = true;
        switch (size) {
            case 0:
                length = 2;
                break;
            case 1:
                length = 3;
                break;
            case 2:
                length = 3;
                break;
            case 3:
                length = 4;
                break;
        }

    }

    @Override
    public void update(float deltaTime) {

    }

    public void render(SpriteBatch batch) {
        if(isHorizontal)
            batch.draw(texture, x, y,50*length,50);
        else
            batch.draw(texture, x, y,50,50*length);
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

    public int getSize() {
        return size;
    }

    public boolean isHorizontal() {
        return isHorizontal;
    }

    public Cell[] getOccupiedCells() {
        Cell[] cells = new Cell[size];
        int cellSize = 50; // size of each cell
        for (int i = 0; i < size; i++) {
            int cellX = (int) x / cellSize;
            int cellY = (int) y / cellSize;
            if (isHorizontal) {
                cellX += i;
            } else {
                cellY += i;
            }

            cells[i] = new Cell(10f, 20f, cellTexture, 50, 50);
        }
        return cells;
    }


}
