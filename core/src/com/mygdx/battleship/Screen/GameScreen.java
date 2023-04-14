package com.mygdx.battleship.Screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.mygdx.battleship.Entities.Ship;
import com.gdx.battleship.CellType;

public class GameScreen implements InputProcessor {
    private static final int BOARD_SIZE = 10;
    private static final int CELL_SIZE = 32;

    private SpriteBatch batch;
    private Texture boardTexture;
    private Texture shipTexture;
    private Texture hitTexture;
    private Texture missTexture;

    private CellType[][] board = new CellType[BOARD_SIZE][BOARD_SIZE];
    private boolean[][] boardChecked;
    private List<Ship> ships;
    private int numShipsSunk;
    private int numShipsPlaced;
    private boolean isGameOver;

    public GameScreen(SpriteBatch batch) {
        this.batch = batch;

        // Load textures
        boardTexture = new Texture(Gdx.files.internal("board.png"));
        shipTexture = new Texture(Gdx.files.internal("ship.png"));
        hitTexture = new Texture(Gdx.files.internal("hit.png"));
        missTexture = new Texture(Gdx.files.internal("miss.png"));

        // Initialize board and ships
        board = new int[BOARD_SIZE][BOARD_SIZE];
        boardChecked = new boolean[BOARD_SIZE][BOARD_SIZE];
        ships = new ArrayList<>();
        numShipsSunk = 0;
        numShipsPlaced = 0;
        isGameOver = false;

        // Initialize board with water cells
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                board[i][j] = CellType.WATER.ordinal();
            }
        }
    }

    public void draw() {
        batch.draw(boardTexture, 0, 0);

        // Draw ships
        for (Ship ship : ships) {
            if (ship.isPlaced()) {
                TextureRegion region = new TextureRegion(shipTexture);
                region.flip(ship.isHorizontal(), false);
                batch.draw(region, ship.getX() * CELL_SIZE, ship.getY() * CELL_SIZE);
            }
        }

        // Draw hits and misses
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                if (boardChecked[i][j]) {
                    Texture texture;
                    if (board[i][j] == CellType.SHIP) {
                        texture = hitTexture;
                    } else {
                        texture = missTexture;
                    }
                    batch.draw(texture, i * CELL_SIZE, j * CELL_SIZE);
                }
            }
        }
    }

    public void placeShip(int x, int y, boolean isHorizontal) {
        Ship ship = new Ship(x, y, isHorizontal);
        if (canPlaceShip(ship)) {
            for (int i = 0; i < ship.getLength(); i++) {
                int cellX = ship.getX() + (isHorizontal ? i : 0);
                int cellY = ship.getY() + (isHorizontal ? 0 : i);
                board[cellX][cellY] = CellType.SHIP;
            }
            ships.add(ship);
            numShipsPlaced++;
        }
    }

    public boolean canPlaceShip(Ship ship) {
        if (numShipsPlaced >= GameConstants.MAX_NUM_SHIPS) {
            return false;
        }
        if (ship.getX() < 0 || ship.getX() >= BOARD_SIZE || ship.getY() < 0 || ship.getY() >= BOARD_SIZE) {
            return false;
        }
        for (int i = 0; i < ship.getLength(); i++) {
            int cellX = ship.getX() + (ship.isHorizontal() ? i : 0);
            int cellY = ship.getY() + (ship.isHorizontal() ? 0 : i);
            if (cellX < 0 || cellX >= BOARD_SIZE || cellY < 0 || cellY >= BOARD_SIZE) {
                return false;
            }
            if (board[cellX][cellY] != CellType.WATER) {
                return false;
            }
        }
        return true;
    }

    public void checkCell(int x, int y) {
        if (boardChecked[x][y]) {
            return;
        }
        boardChecked[x][y] = true;
        if (board[x][y] == CellType.SHIP) {
            for (Ship ship : ships) {
                if (ship.isHit(x, y)) {
                    if (ship.isSunk()) {
                        numShipsSunk++;
                        if (numShipsSunk >= GameConstants.MAX_NUM_SHIPS) {
                            isGameOver = true;
                        }
                    }
                    break;
                }
            }
        }
    }

    public boolean isGameOver() {
        return isGameOver;
    }

    public void dispose() {
        boardTexture.dispose();
        shipTexture.dispose();
        hitTexture.dispose();
        missTexture.dispose();
    }

    // InputProcessor interface methods
    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        if (isGameOver) {
            return false;
        }
        int cellX = screenX / CELL_SIZE;
        int cellY = (SCREEN_HEIGHT - screenY) / CELL_SIZE;
        if (boardChecked[cellX][cellY]) {
            return false;
        }
        checkCell(cellX, cellY);
        return true;
    }

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(float amountX, float amountY) {
        return false;
    }
}
