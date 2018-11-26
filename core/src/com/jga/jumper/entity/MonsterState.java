package com.jga.jumper.entity;

public enum MonsterState {
    WALKING,
    JUMPING,
    FALLING;

    // == public methods ==
    public boolean isWalking(){
        return this == WALKING;
    }

    public boolean isJumping(){
        return this == JUMPING;
    }

    public boolean isFalling(){
        return this == FALLING;
    }
}
