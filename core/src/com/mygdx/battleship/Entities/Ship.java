package com.mygdx.battleship.Entities;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;


public class Ship {

    private int size; // Size of the ship
    private ShipOrientation orientation; // Orientation of the ship (horizontal or vertical)
    private int hits; // Number of hits on the ship

    public Texture texture;

    public Ship(Texture texture, ShipOrientation orientation) {
        this.texture = texture;
        this.orientation = orientation;
        this.hits = 0;
    }

    // Getter and setter methods for ship properties
    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public ShipOrientation getOrientation() {
        return orientation;
    }

    public void setOrientation(ShipOrientation orientation) {
        this.orientation = orientation;
    }

    public int getHits() {
        return hits;
    }

    public void setHits(int hits) {
        this.hits = hits;
    }

    // Method to check if the ship is sunk
    public boolean isSunk() {
        return hits == size;
    }

    // Method to increment the hit count on the ship
    public void incrementHits() {
        hits++;
    }

}
