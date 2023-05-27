package com.mygdx.spaceaircraft.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.mygdx.spaceaircraft.SpaceAircraftMain;

public class PauseScreen implements Screen {

    private SpaceAircraftMain game;
    private Stage stage;
    private SpriteBatch spriteBatch;

    public PauseScreen(SpaceAircraftMain game) {
        this.game = game;
        stage = new Stage(new StretchViewport(SpaceAircraftMain.WIDTH, SpaceAircraftMain.HEIGHT));
        spriteBatch = new SpriteBatch();
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);
        createUI();
    }

    private void createUI() {
        Texture backgroundTexture = new Texture(Gdx.files.internal("BackgroundLevel1.jpg"));
        Image background = new Image(backgroundTexture);

        Table table = new Table();
        table.setFillParent(true);
        table.center().top();
        table.padTop(200f);
        table.add(background);

        stage.addActor(table);
    }

    @Override
    public void render(float delta) {
        clearScreen();
        stage.act(delta);
        stage.draw();
    }

    private void clearScreen() {
        Gdx.gl.glClearColor(0.2f, 0.2f, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
    }

    @Override
    public void pause() {
        // Implement any necessary pause logic here
    }

    @Override
    public void resume() {
        // Implement any necessary resume logic here
    }

    @Override
    public void hide() {
        // Implement any necessary hide logic here
    }

    @Override
    public void dispose() {
        stage.dispose();
        spriteBatch.dispose();
    }
}
