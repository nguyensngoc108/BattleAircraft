package com.mygdx.battleship.Screen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.mygdx.battleship.BattleShipMain;
import com.mygdx.battleship.Entities.Cell;
import com.mygdx.battleship.Entities.Ship;

import java.util.ArrayList;

public class GameScreen implements Screen {

    private Texture background;
    private BattleShipMain game;

    private ArrayList<Cell> cells;
    private ArrayList<Cell> removeCells;

    public GameScreen(BattleShipMain game) {
        this.game = game;
        background = new Texture("whitebg.jpg");
        cells = new ArrayList<Cell>();
        removeCells = new ArrayList<Cell>();

        // Create cells in the left panel
        for (int x = 0; x <= 450; x += 50) {
            for (int y = 0; y <= 450; y += 50) {
                cells.add(new Cell(x, y, new Texture("Cell2.png"), 50, 50));
            }
        }

        // Create cells in the right panel
        for (int x = 700; x <= 1150; x += 50) {
            for (int y = 0; y <= 450; y += 50) {
                cells.add(new Cell(x, y, new Texture("Cell2.png"), 50, 50));
            }
        }
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        if (Gdx.input.justTouched()) { // Check if screen was touched
            int touchX = Gdx.input.getX();
            int touchY = Gdx.graphics.getHeight() - Gdx.input.getY(); // Invert Y-axis

            // Loop through the list of cells and check if any cell was clicked
            for (Cell cell : cells) {
                if (cell.checkInput(touchX, touchY)) { // No need to compare with true
                    removeCells.add(cell);
                    cell.setTexture(new Texture("CellBlack.jpg"));
                }
            }
        }




        // Render cells
        game.batch.begin();
        game.batch.draw(background, 0, 0, BattleShipMain.WIDTH, BattleShipMain.HEIGHT);

        for (Cell cell : cells) {
            cell.render(game.batch);
        }

        game.batch.end();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        // Dispose textures
        background.dispose();
        for (Cell cell : cells) {
            cell.getTexture().dispose();
        }
    }
}
