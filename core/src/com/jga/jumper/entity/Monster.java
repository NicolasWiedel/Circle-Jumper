package com.jga.jumper.entity;

import com.badlogic.gdx.math.MathUtils;
import com.jga.jumper.config.GameConfig;
import com.jga.util.entity.EntityBase;

public class Monster extends EntityBase {

    // == attributes ==
    private float angleDeg = GameConfig.MONSTER_START_ANGLE;
    private float angleDegSpeed = GameConfig.MONSTER_START_ANG_SPEED;
    private float speed = 0;
    private float acceleration = GameConfig.MONSTER_START_ACC;
    

    // == constructor ==
    public Monster(){

        setSize(GameConfig.MONSTER_SIZE, GameConfig.MONSTER_SIZE);
    }

    // == public methods ==
    public void update(float delta){
        angleDeg += angleDegSpeed * delta;
        angleDeg = angleDeg % 360;

        float radius = GameConfig.PLANET_HALF_SIZE;
        float originX = GameConfig.WORLD_CENTER_X;
        float originY = GameConfig.WORLD_CENTER_Y;

        float newX = originX + MathUtils.cosDeg(-angleDeg) * radius;
        float newY = originY + MathUtils.sinDeg(-angleDeg) * radius;

        setPosition(newX, newY);
    }

    public float getAngleDeg() {
        return angleDeg;
    }
}
