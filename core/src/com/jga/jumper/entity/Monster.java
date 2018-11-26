package com.jga.jumper.entity;

import com.jga.jumper.config.GameConfig;
import com.jga.util.entity.EntityBase;

public class Monster extends EntityBase {

    public Monster(){
        setSize(GameConfig.MONSTER_SIZE, GameConfig.MONSTER_SIZE);
    }
}
