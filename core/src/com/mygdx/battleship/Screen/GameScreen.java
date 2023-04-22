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

import java.util.*;

public class GameScreen implements Screen {


    private SpriteBatch batch;
    private OrthographicCamera camera;

    private Texture background;
    BattleShipMain game;

    ArrayList<Cell> cell;




    public GameScreen(BattleShipMain game){
        this.game = game;
        camera = new OrthographicCamera();
        camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        batch = new SpriteBatch();

        background = new Texture("whitebg.jpg");
    }
    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
       // Gdx.gl.glClearColor(1, 1, 1, 1);
       // Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        game.batch.begin();
        game.batch.draw(background, 0, 0, BattleShipMain.WIDTH, BattleShipMain.HEIGHT);
       for (int x = 0; x <= 500; x += 50) {
            for (int y = 0; y <= 500; y += 50){
                game.batch.draw(new Texture("Cell1.png"), x, y);
          }
       }

        game.batch.end();
        /*for (int x = 0; x < gridSize + 16; x++) {
            for (int y = 0; y < gridSize + 16; y++) {
                batch.draw(playerCellTexture, x * cellSize, y * cellSize);
            }
        }

        // Draw computer grid
        for (int x = 90; x < gridSize; x++) {
            for (int y = 90; y < gridSize; y++) {
                float xPos = Gdx.graphics.getWidth() - (gridSize * cellSize) + (x * cellSize);
                batch.draw(computerCellTexture, xPos, y * cellSize);
            }
        }*/


    }


    @Override
    public void resize(int width, int height) {
        camera.setToOrtho(false, width, height);
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

