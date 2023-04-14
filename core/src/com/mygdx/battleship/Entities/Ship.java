package com.mygdx.battleship.Entities;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;


public class Ship {
    private int size;
    private int x;
    private int y;
    private boolean isHorizontal;

    public Ship(int size, int x, int y, boolean isHorizontal) {
        this.size = size;
        this.x = x;
        this.y = y;
        this.isHorizontal = isHorizontal;
    }

    public int getSize() {
        return size;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean isHorizontal() {
        return isHorizontal;
    }

    public boolean isSunk(boolean[][] board) {
        // Check if all cells occupied by the ship have been hit
        if (isHorizontal) {
            for (int i = x; i < x + size; i++) {
                if (!board[i][y]) {
                    return false;
                }
            }
        } else {
            for (int i = y; i < y + size; i++) {
                if (!board[x][i]) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean isPlaced() {
        return x >= 0 && y >= 0 && size > 0;
    }

}
