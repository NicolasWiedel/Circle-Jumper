package com.jga.jumper.entity;

import com.jga.jumper.config.GameConfig;
import com.jga.util.entity.EntityBase;

import java.awt.Rectangle;

public class Obstacle extends EntityBase {

    // == attributes ==
    private float angleDeg;
    private Rectangle sensor = new Rectangle();
    private float sensorAngleDeg;

    // == constructor ==
    public Obstacle(){
        setSize(GameConfig.OBSTACLE_SIZE, GameConfig.OBSTACLE_SIZE);
    }

    // == public methods ==
}
