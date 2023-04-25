package com.mygdx.battleship;

import com.badlogic.gdx.Game;
import com.mygdx.battleship.Screen.*;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

public class BattleShipMain extends Game {

	public static final int WIDTH = 1200;
	public static final int HEIGHT = 500;
	public SpriteBatch batch;


	
	@Override
	public void create () {
		batch = new SpriteBatch();
		this.setScreen(new GameScreen(this));
	}

	@Override
	public void render () {
		super.render();
	}
	
	@Override
	public void dispose () {

	}
}
