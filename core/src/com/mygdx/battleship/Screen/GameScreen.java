package com.mygdx.battleship.Screen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.ai.GdxFileSystem;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

import com.mygdx.battleship.BattleShipMain;
import com.mygdx.battleship.Entities.Cell;
import com.mygdx.battleship.Entities.Ship;
import com.gdx.battleship.CellType;
import com.mygdx.battleship.Entities.ShipOrientation;

import java.util.*;

public class GameScreen implements Screen {


    private Texture background;
    BattleShipMain game;

    ArrayList<Cell> cell;

    ArrayList<Cell> removeCell;

    Ship ship;




    public GameScreen(BattleShipMain game){
        this.game = game;
        background = new Texture("whitebg.jpg");
        cell = new ArrayList<Cell>();
        removeCell = new ArrayList<Cell>();
        ship = new Ship(new Texture("Battleship.png"), ShipOrientation.HORIZONTAL);



        // Cell for computer
        for (int x = 0; x <= Gdx.graphics.getWidth() - 700; x += 50) {
            for (int y = 0; y <= Gdx.graphics.getHeight(); y += 50){
                cell.add(new Cell(x, y, new Texture("Cell2.png"), 50, 50));
            }
        }

        // Cell for player
        for (int x = 700; x <= Gdx.graphics.getWidth(); x += 50) {
            for (int y = 0; y <= Gdx.graphics.getHeight(); y += 50){
                cell.add(new Cell(x, y, new Texture("Cell2.png"), 50, 50));
            }
        }
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        // Gdx.gl.glClearColor(1, 1, 1, 1);
        // Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);


        if (Gdx.input.justTouched()) { // Check if screen was touched
            int touchX = Gdx.input.getX();
            int touchY = Gdx.graphics.getHeight() - Gdx.input.getY(); // Invert Y-axis

//            for (Cell cell : cells) {
//                if (cell.checkInput(touchX, touchY) == true) {
//                    // Check if the clicked cell is available for ship placement
//                    if (cell.hasShip()) {
//                        // Place ship on the clicked cell
//                        cells.setShip(new Ship(new Texture("Battleship.png"), ShipOrientation.HORIZONTAL));
//                        ; // Replace 'Ship()' with the actual ship object creation logic
//                    }
//                }
//            }

            // Loop through the list of cells and check if any cell was clicked
            for (Cell cells : cell) {
                if (cells.hasShip()) {
                    // Draw ship on the cell using appropriate texture or sprite based on the Ship object associated with the cell
                        cells.getShip();
                }
                if (ship.getOrientation() == ShipOrientation.HORIZONTAL) {
                    // Draw horizontal ship
                } else {
                    // Draw vertical ship
                    if (cells.checkInput(touchX, touchY) == true) {
                        cells.setTexture(new Texture("Battleship.png"));
                    }
                }
            }
        }

        //cell.removeAll(removeCell);
        //removeCell.clear();

        game.batch.begin();
        game.batch.draw(background, 0, 0, BattleShipMain.WIDTH, BattleShipMain.HEIGHT);

        for(Cell cell: cell){
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


    }
}

