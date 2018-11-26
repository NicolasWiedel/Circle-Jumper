package com.jga.jumper.entity;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Pool;
import com.jga.jumper.config.GameConfig;
import com.jga.util.entity.EntityBase;

public class Coin extends EntityBase implements Pool.Poolable {

    // == attribute ==
    private float angleDeg;

    // == constructor ==
    public Coin(){
        setSize(GameConfig.COIN_SIZE, GameConfig.COIN_SIZE);
    }

    // == public methods ==
    public void setAngleDeg(float value){
        angleDeg = value % 360;

        float radius = GameConfig.PLANET_HALF_SIZE;
        float originX = GameConfig.WORLD_CENTER_X;
        float originY= GameConfig.WORLD_CENTER_Y;

        float newX = originX + MathUtils.cosDeg(-angleDeg) * radius;
        float newY = originY + MathUtils.sinDeg(-angleDeg) * radius;

        setPosition(newX, newY);
    }

    public float getAngleDeg() {
        return angleDeg;
    }

    @Override
    public void reset() {

    }
}
