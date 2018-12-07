package com.jga.jumper;

import com.jga.jumper.screen.loading.LoadingScreen;
import com.jga.util.game.GameBase;

public class CircleJumperGame extends GameBase {

	@Override
	public void postCreate() {
		setScreen(new LoadingScreen(this));
	}
}
