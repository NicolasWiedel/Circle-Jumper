package com.jga.jumper.screen.menu;

import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.jga.jumper.assets.RegionNames;

public class GameOverOverlay extends Table {

    // == attributes ==
    private final OverlayCallback callback;

    private Label scoreLabel;
    private Label HighScoreLabel;

    // == constructor ==
    public GameOverOverlay(Skin skin, OverlayCallback callback){
        super(skin);
        this.callback = callback;
        init();
    }

    // == init ==
    private void init(){
        defaults().pad(20);

        Image gameOverImage = new Image(getSkin(), RegionNames.GAME_OVER);
    }
}
