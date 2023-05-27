package com.mygdx.spaceaircraft;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.spaceaircraft.screen.MainScreen;
import com.mygdx.spaceaircraft.screen.MenuScreen;
import com.mygdx.spaceaircraft.screen.PauseScreen;

public class SpaceAircraftMain extends Game {

	public static final int WIDTH = 480;
	public static final int HEIGHT = 720;
	public SpriteBatch batch;
	private Music BGM_SOUND;
	private boolean isPaused;

	@Override
	public void create() {
		Music BGM_SOUND = Gdx.audio.newMusic(Gdx.files.internal("bgm.mp3"));
		batch = new SpriteBatch();
		this.setScreen(new MenuScreen(this));
	}

	@Override
	public void render() {
		super.render();

		if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
			togglePause();
		}

		if (isPaused) {
			renderPauseScreen();
			if (Gdx.input.isKeyJustPressed(Input.Keys.ENTER)) {
				togglePause();
			}
		} else {
			// Update the game only if it's not paused
			updateGame();
		}
	}

	private void togglePause() {
		isPaused = !isPaused;

		if (isPaused) {
			setScreen(new PauseScreen(this));
		} else {
			setScreen(new MainScreen(this));
		}
	}

	private void renderPauseScreen() {
		// Render your pause screen UI here
	}

	private void updateGame() {
		// Update your game logic here
	}

	@Override
	public void dispose() {
		// Dispose any resources here
		batch.dispose();
		BGM_SOUND.dispose();
	}
}
