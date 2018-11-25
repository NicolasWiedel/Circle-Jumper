package com.jga.jumper.screen.game;

import com.badlogic.gdx.utils.Logger;
import com.jga.jumper.config.GameConfig;
import com.jga.jumper.entity.Planet;
import com.jga.jumper.entity.Player;

public class GameController {

    // == constants ==
    private final Logger log = new Logger(GameController.class.getName(), Logger.DEBUG);

    // == attributes ==
    private Planet planet;
    private Player player;

    private float playerStartX;
    private float playerStartY;

    // == constructor ==
    public GameController() {
        init();
    }

    // == init ==
    private void init(){
        planet = new Planet();
        planet.setPosition(GameConfig.WORLD_CENTER_X, GameConfig.WORLD_CENTER_Y);

        playerStartX= GameConfig.WORLD_CENTER_X - GameConfig.PLANET_HALF_SIZE;


        player = new Player();
        player.setPosition(
                playerStartX,
                GameConfig.WORLD_CENTER_Y + GameConfig.PLANET_HALF_SIZE + GameConfig.PLAYER_HALF_SIZE);
    }

    // == public methods ==
    public void update(float delta){}

    public Planet getPlanet() {
        return planet;
    }

    public Player getPlayer() {
        return player;
    }
}
