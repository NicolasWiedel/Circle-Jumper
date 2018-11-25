package com.jga.jumper.screen.game;

import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.assets.AssetManager;
import com.jga.jumper.CircleJumperGame;

public class GameScreen extends ScreenAdapter {

    // == attributes ==
    private final CircleJumperGame game;
    private final AssetManager assetManager;

    // == constructor ==
    public GameScreen(CircleJumperGame game){
        this.game = game;
        assetManager = game.getAssetManager();
    }
}
