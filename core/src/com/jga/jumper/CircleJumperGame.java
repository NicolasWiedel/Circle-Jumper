package com.jga.jumper;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Logger;
import com.jga.jumper.screen.game.GameScreen;
import com.jga.util.game.GameBase;

public class CircleJumperGame extends GameBase {

	@Override
	public void postCreate() {
		setScreen(new GameScreen(this));
	}
}
