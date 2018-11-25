package com.jga.jumper.screen.game;

import com.badlogic.gdx.utils.Logger;
import com.jga.jumper.config.GameConfig;
import com.jga.jumper.entity.Planet;

public class GameController {

    // == constants ==
    private final Logger log = new Logger(GameController.class.getName(), Logger.DEBUG);

    // == attributes ==
    private Planet planet;

    // == constructor ==
    public GameController() {
        init();
    }

    // == init ==
    private void init(){
        planet = new Planet();
        planet.setPosition(GameConfig.WORLD_CENTER_X, GameConfig.WORLD_CENTER_Y);
    }

    // == public methods ==
    public void update(float delta){}

    public Planet getPlanet() {
        return planet;
    }
}
