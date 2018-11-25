package com.jga.jumper.entity;

import com.jga.jumper.config.GameConfig;
import com.jga.util.entity.EntityBase;

public class Player extends EntityBase {

    public Player(){
        setSize(GameConfig.PLAYER_SIZE, GameConfig.PLAYER_SIZE);
    }
}
