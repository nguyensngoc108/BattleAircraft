package com.mygdx.battleship.Entities;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

/**
 * Ship class:
 * Defines a simple ship object that can be drawn onto the screen using libGDX's ShapeRenderer.
 * Stores information about its position, size, and color.
 */
public class Ship {
    private Rectangle rectangle; // Rectangle to represent the ship
    private Color color; // Color of the ship

    /**
     * Creates a ship with the specified position, size, and color.
     *
     * @param position The position of the ship's top-left corner.
     * @param size The size of the ship as a Vector2 representing width and height.
     * @param color The color of the ship.
     */
    public Ship(Vector2 position, Vector2 size, Color color) {
        rectangle = new Rectangle(position.x, position.y, size.x, size.y);
        this.color = color;
    }

    /**
     * Draws the ship using the provided ShapeRenderer.
     *
     * @param shapeRenderer The ShapeRenderer to use for drawing.
     */
    public void draw(ShapeRenderer shapeRenderer) {
        shapeRenderer.setColor(color);
        shapeRenderer.rect(rectangle.x, rectangle.y, rectangle.width, rectangle.height);
    }

    /**
     * Sets the position of the ship.
     *
     * @param position The new position of the ship's top-left corner.
     */
    public void setPosition(Vector2 position) {
        rectangle.setPosition(position);
    }

    /**
     * Sets the size of the ship.
     *
     * @param size The new size of the ship as a Vector2 representing width and height.
     */
    public void setSize(Vector2 size) {
        rectangle.setSize(size.x, size.y);
    }

    /**
     * Sets the color of the ship.
     *
     * @param color The new color of the ship.
     */
    public void setColor(Color color) {
        this.color = color;
    }

    /**
     * Gets the position of the ship.
     *
     * @return The position of the ship's top-left corner as a Vector2.
     */
    public Vector2 getPosition() {
        return new Vector2(rectangle.x, rectangle.y);
    }

    /**
     * Gets the size of the ship.
     *
     * @return The size of the ship as a Vector2 representing width and height.
     */
    public Vector2 getSize() {
        return new Vector2(rectangle.width, rectangle.height);
    }

    /**
     * Gets the color of the ship.
     *
     * @return The color of the ship.
     */
    public Color getColor() {
        return color;
    }
}
